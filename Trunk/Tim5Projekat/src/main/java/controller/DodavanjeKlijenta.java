package controller;

import java.awt.Color;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JTextField;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.DataException;
import org.hibernate.exception.SQLGrammarException;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

import Models.Klijent;
import tim5.si.unsa.ba.Tim5Projekat.HibernateUtil;


public class DodavanjeKlijenta {
		
	public DodavanjeKlijenta(){}
	
	public static Long unesiKlijentaUBazu(Klijent _klijent, JTextField textField, JTextField textField_2, JTextField textField_3, JTextField textField_1 ) throws Exception
	{
		Session sesija = HibernateUtil.getSessionFactory().openSession(); 
		
		Transaction transakcija = sesija.beginTransaction(); 
		
		try
		{
			
			_klijent.set_imeIPrezime(textField.getText());
			_klijent.setBrojTelefona(textField_2.getText());
			_klijent.setEmail(textField_3.getText());
			_klijent.set_adresa(textField_1.getText());		
			
			if(validirajPrazno(textField) && validirajPrazno(textField_1) && validirajPrazno(textField_2) && validirajPrazno(textField_3) 
					&& validirajTelefon(textField_2) && validirajMail(textField_3))
			{
				Long id = (Long)sesija.save(_klijent); 
				transakcija.commit(); 
				return id;
				//infoBox("Klijent " + get_klijent().get_imeIPrezime() + " je unesen", "UnesenKLijent");
			}
			else if (!validirajPrazno(textField) || !validirajPrazno(textField_1) || !validirajPrazno(textField_2) || !validirajPrazno(textField_3))
			{
				throw new IllegalArgumentException("Svi unosi moraju biti prisutni !");
			}
			
		}
		catch(IllegalArgumentException e1)
		{
			throw e1;
		}
		catch(Exception e2)
		{
			throw e2;
		}
		finally
		{
			sesija.close();
		}
		
		return new Long(0);
	}
	
public static Boolean validirajPrazno(JTextField t1) throws IllegalArgumentException {
		Boolean izlaz = false;
	
		if(t1.getText().equals(""))
		{
			t1.setBackground(new Color(216,210,139));
			throw new IllegalArgumentException("Sva polja moraju biti popunjena");
		}
		else
		{
			izlaz = true;
			t1.setBackground(new Color(255,255,255));
		}
	
	return izlaz;
	
}

public static Boolean validirajTelefon(JTextField t) throws IllegalArgumentException
{
	Boolean izlaz = false;
      Pattern pattern = Pattern.compile("\\d{3}/\\d{3}-\\d{3}");
      Matcher matcher = pattern.matcher(t.getText());
 
      if (matcher.matches()) {
    	  
    	  t.setBackground(new Color(255,255,255));
    	  izlaz = true;
      }
      else
      {
    	  t.setBackground(new Color(216,210,139));
    	  throw new IllegalArgumentException("Pogrešan format broja telefona. Prihvaćeni format je xxx/xxx-xxx");
      }
      
      return izlaz;
}

public static Boolean validirajMail(JTextField t) throws IllegalArgumentException
{
	Boolean izlaz = false;
	
	 Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
      Matcher matcher = pattern.matcher(t.getText());
 
      if (matcher.matches()) {
    	  
    	  t.setBackground(new Color(255,255,255));
    	  izlaz = true;
      }
      else
      {
    	  t.setBackground(new Color(216,210,139));
    	  throw new IllegalArgumentException("Pogrešan format e-maila. Prihvaćeni format je abc1@abc2.ab");
      }
      
      return izlaz;
}

}
