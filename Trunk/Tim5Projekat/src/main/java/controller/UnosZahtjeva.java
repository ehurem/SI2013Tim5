package controller;

import java.awt.Color;
import java.awt.TextField;
import java.sql.Date;
import java.util.Enumeration;

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
	
	public Long unesiZahtjevUBazu(Long _zaposlenik, JComboBox comboBox_1, JComboBox comboBox_3, JTextField textField_2, Boolean dugme, JTextArea textArea) throws Exception{
		
		Session sesija = HibernateUtil.getSessionFactory().openSession(); //otvorena sesija, omogućena komunikacija
		Transaction transakcija = sesija.beginTransaction(); //otvara vezu sa bazom
		
		try
		{				
			Zahtjev noviZahtjev = new Zahtjev();
								
			noviZahtjev.setKlijent(((Klijent)comboBox_1.getSelectedItem()).getId());
			noviZahtjev.setTipUredaja(textField_2.getText());

			noviZahtjev.setGarancija(dugme);
			
			noviZahtjev.setKomentar(textArea.getText());
			
			
			Date dat = new Date(System.currentTimeMillis());
			
			noviZahtjev.setDatumOtvaranja(dat);
			
			noviZahtjev.set_cijena(0);
			noviZahtjev.setPrioritet(1);
			noviZahtjev.setStatus("otvoren");
			
			noviZahtjev.setZaposlenik(_zaposlenik);
			//get_zahtjevi().add(noviZahtjev);
			
			if(validirajPrazno(textField_2) && validirajPrazno(textArea))
			{
				Long id = (Long)sesija.save(noviZahtjev); //spašava u bazu
				
				transakcija.commit(); //završava transakciju
				sesija.close();
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
			if(sesija!=null)
			sesija.close();
		}
	}

	private static Boolean validirajPrazno(JTextField t1) {
		
		if(t1.getText().equals(""))
		{
			t1.setBackground(new Color(216,210,139));
			return false;
		}
		else
		{
			t1.setBackground(new Color(255,255,255));
		}
		
	return true;
	
	
	}
	
	private static Boolean validirajPrazno(JTextArea t1) {
		
		if(t1.getText().equals(""))
		{
			t1.setBackground(new Color(216,210,139));
			return false;
		}
		else
		{
			t1.setBackground(new Color(255,255,255));
		}
		
	return true;
	
	
	}
}
