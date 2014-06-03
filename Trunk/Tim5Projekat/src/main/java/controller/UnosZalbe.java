package controller;

import java.awt.Color;
import java.sql.Date;
import java.util.List;
import java.util.regex.*;


import javax.swing.JComboBox;
import javax.swing.JTextArea;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import Models.Klijent;
import Models.Zalba;
import Models.Zaposlenik;
import tim5.si.unsa.ba.Tim5Projekat.HibernateUtil;

public class UnosZalbe {
	
	public UnosZalbe() {}
	
	public static void popuniCombo(JComboBox comboBox, JComboBox comboBox_2) throws Exception{
		comboBox.removeAllItems();
        comboBox_2.removeAllItems();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
			
			Transaction t = session.beginTransaction();
			
			Query queryZaposlenik = session.createQuery("from Zaposlenik where privilegija != 'Administrator' and status = 1");
			List listZaposlenik = queryZaposlenik.list();
			
			Query queryKlijent = session.createQuery("from Klijent");
			List listKlijent = queryKlijent.list();
			
			for(int i=0;i<listZaposlenik.size();i++){
			comboBox.addItem(listZaposlenik.get(i));;
			}
			
			for(int i=0;i<listKlijent.size();i++){
				comboBox_2.addItem(listKlijent.get(i));;
				}
			
			t.commit();
		}
		catch (Exception ex) {
			throw ex;
		}
        finally{
        	session.close();
        }
		
	}
	
	public static Long unesiZalbuUBazu(JTextArea textArea_1, JComboBox comboBox, JComboBox comboBox_2) throws Exception{
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			
			Transaction t = session.beginTransaction();
			java.util.Date utilDate = new java.util.Date();
			
            Zalba nova = new Zalba();
			
			nova.setKomentar(textArea_1.getText());
			nova.setDatumPodnosenja(dajSQLDate(utilDate));
			Zaposlenik z = (Zaposlenik)comboBox.getSelectedItem();
			Klijent k = (Klijent)comboBox_2.getSelectedItem();
			nova.set_klijent( (Long)k.getId());
			nova.setZaposlenik( (Long)z.getId() );
			
			if(validirajPrazno(textArea_1)){
			
				nova.setId( (Long ) session.save(nova));
				t.commit();
				
				return nova.getId();
			}
			else
			{
				throw new Exception("Morate popuniti formu!");
			}	
		}
		catch (Exception ex) {
			throw ex;
		}
		finally{
			session.close();
		}
	}
	
public static Boolean validirajPrazno(JTextArea t1) {
		
		Boolean izlaz = false;
		String pattern = "^[a-zA-Z0-9].*";
		String text = t1.getText();      
		Pattern p = Pattern.compile(pattern);       
		Matcher m = p.matcher(text);

		if(t1.getText().equals("") || t1.getText().length()<10 || !(m.matches()))
		{
			t1.setBackground(new Color(216,210,139));
			throw new IllegalArgumentException("Morate unijeti komentar žalbe ne kraći od 10 karaktera!");
		}
		else
		{
			izlaz = true;
			t1.setBackground(new Color(255,255,255));
		}
	
		return izlaz;
	}

public static Date dajSQLDate(java.util.Date utilDate){
	
	java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
	return sqlDate;
}

public static void resetPolja(JTextArea ta)
{
	ta.setText("");;
}

}
