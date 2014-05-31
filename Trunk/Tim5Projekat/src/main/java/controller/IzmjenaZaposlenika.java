package controller;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import tim5.si.unsa.ba.Tim5Projekat.HibernateUtil;
import Models.Zaposlenik;

public class IzmjenaZaposlenika {

	public static Zaposlenik izmjeni(Zaposlenik novi) throws Exception{
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		try{
			
			Transaction t = session.beginTransaction();
			session.update(novi);
			t.commit();
		
		}
		catch(Exception ex){
			throw ex;
		}
		finally{
			session.close(); 
		}
		return novi;
	}
	
	public static boolean ValidirajDodavanje(String adresa,String brojTel,String Email,String korisnickoIme) throws Exception{
		if(adresa.equals("")){
			throw new Exception("Niste unijeli Adresu stanovanja");
			
		}
		
		else if(brojTel.equals("")){
			
			throw new Exception("Niste unijeli broj telefona");
		}
		
		else if(Email.equals("")){
			throw new Exception("Niste unijeli email");
		}
		
		else if(korisnickoIme.equals("")){
			
			throw new Exception("Niste unijeli Korisnicko ime");
		}
		else if(!brojTel.matches("\\d{3}/\\d{3}-\\d{3}")){
			
			throw new Exception("Niste unijeli pravilno telefonski broj.");
		}
		
		return true;
		
	}
	
	public static boolean obrisiZaposlenika(long z) throws Exception{
		Session session = HibernateUtil.getSessionFactory().openSession();
		   Zaposlenik employee = new Zaposlenik();
	      try{
	    	 Transaction tx = null; 
	    	/*Brisanje zaposlenika iz baze*/
	        tx = session.beginTransaction();
	        employee = (Zaposlenik) session.get(Zaposlenik.class, z); 
	        employee.set_status(false);
	        session.update(employee); 
	        
	        /*Komitanje u bazu izmjena nad zaposlenicima*/
	        tx.commit();
	         
	        System.out.println("Uspjesno ste deaktivirali racun: " + employee.get_imeIPrezime());
	      }
	      catch (Exception ex) {
				throw ex;
	      }
	      finally {
	         session.close(); 
	      }
		return true;
	}
}
