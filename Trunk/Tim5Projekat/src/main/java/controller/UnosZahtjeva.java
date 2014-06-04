package controller;

import java.awt.Color;
import java.awt.TextField;
import java.sql.Date;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.hibernate.Session;
import org.hibernate.Transaction;

import tim5.si.unsa.ba.Tim5Projekat.HibernateUtil;
import Models.Klijent;
import Models.Zahtjev;


public class UnosZahtjeva {
	
	public UnosZahtjeva() {}
	
	//c1 - id klijenta
	
	public static Long unesiZahtjevUBazu(Long _zaposlenik, Long c1, Integer c3, JTextField textField_2, Boolean dugme, JTextArea textArea) throws Exception{
		
		Session sesija = HibernateUtil.getSessionFactory().openSession(); //otvorena sesija, omogućena komunikacija
		Transaction transakcija = sesija.beginTransaction(); //otvara vezu sa bazom
		
		try
		{				
			Zahtjev noviZahtjev = new Zahtjev();
								
			noviZahtjev.setKlijent(c1);
			noviZahtjev.setTipUredaja(textField_2.getText());

			noviZahtjev.setGarancija(dugme);
			
			noviZahtjev.setKomentar(textArea.getText());
			
			
			Date dat = new Date(System.currentTimeMillis());
			
			noviZahtjev.setDatumOtvaranja(dat);
			
			noviZahtjev.set_cijena(0);
			noviZahtjev.setPrioritet(c3);
			noviZahtjev.setStatus("otvoren");
			
			noviZahtjev.setZaposlenik(_zaposlenik);
			//get_zahtjevi().add(noviZahtjev);
			
			if(validirajPrazno(textField_2) && validirajPrazno(textArea))
			{
				Long id = (Long)sesija.save(noviZahtjev); //spašava u bazu
				
				transakcija.commit(); //završava transakciju
				return id;
				
			}
			
			else
			{
				throw new Exception("Svi unosi moraju biti prisutni !");
			}					
		}
		catch(Exception izuzetak)
		{
			throw izuzetak;
		}
		finally
		{
			sesija.close();
		}
	}

	public static Boolean validirajPrazno(JTextField t1) throws IllegalArgumentException {
		Boolean izlaz = false;
		String pattern = "^[a-zA-Z0-9].*";
		String text = t1.getText();      
		Pattern p = Pattern.compile(pattern);       
		Matcher m = p.matcher(text);

		if(t1.getText().equals("") || !(m.matches()))
		{
			t1.setBackground(new Color(216,210,139));
			throw new IllegalArgumentException("Polja ne smiju biti prazna! ");
		}
		else
		{
			izlaz = true;
			t1.setBackground(new Color(255,255,255));
		}

		return izlaz;
		
	}
	
	public static Boolean validirajPrazno(JTextArea t1) {
		
		Boolean izlaz = false;
		String pattern = "^[a-zA-Z0-9].*";
		String text = t1.getText();      
		Pattern p = Pattern.compile(pattern);       
		Matcher m = p.matcher(text);

		if(t1.getText().equals("") || !(m.matches()))
		{
			t1.setBackground(new Color(216,210,139));
			throw new IllegalArgumentException("Polja ne smiju biti prazna! ");
		}
		else
		{
			izlaz = true;
			t1.setBackground(new Color(255,255,255));
		}

		return izlaz;
	}
	
	public static void resetPolja(JTextArea ta, JTextField tf){
		ta.setText("");
		tf.setText("");
	}
}
