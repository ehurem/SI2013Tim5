package controller;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import tim5.si.unsa.ba.Tim5Projekat.HibernateUtil;
import Models.Zaposlenik;

public class IzmjenaZaposlenika {

	public Zaposlenik izmjeni(String adresa,String telefon,String eMail, String KorisnickoIme, String datumRodjenja,Zaposlenik imePrezime,String sifra,String privilegija){
		Zaposlenik novi = new Zaposlenik();
		Session session = HibernateUtil.getSessionFactory().openSession();
		boolean provjera=true;
		if(adresa.equals("")){
			provjera = false;
			System.out.println("Niste unijeli Adresu stanovanja");
		}
		
		else if(telefon.equals("")){
			provjera = false;
			System.out.println("Niste unijeli broj telefona");
		}
		
		else if(eMail.equals("")){
			provjera = false;
			System.out.println("Niste unijeli email");
		}
		
		else if(KorisnickoIme.equals("")){
			provjera = false;
			System.out.println("Niste unijeli Korisnicko ime");
		}
		else if(!telefon.matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{3}")){
			
			provjera = false;
			System.out.println("Niste unijeli pravilno telefonski broj.");
		}
		
		//System.out.println("Tab changed to: " + telefon.matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}"));
		
		
		
		if(provjera){
		try {
			
			
			Transaction t = session.beginTransaction();
			long EmployeeID = ((Zaposlenik) imePrezime).getId();
			novi = (Zaposlenik)session.get(Zaposlenik.class, EmployeeID); 
			/*Provjera da li postoji zaposlenik sa tim korisnickim imenom.*/
			Query query = session.createQuery("from Zaposlenik where korisnickoIme = :ime ");
	        query.setParameter("ime", KorisnickoIme);
	        List<Zaposlenik> list = (List<Zaposlenik>) query.list();
	        
	        
	        if(list.size() == 0){
	        	novi.setAdresa(adresa);
				novi.setBrojTelefona(telefon);
				novi.setEmail(eMail);
					
				if(!sifra.equals("")){
					novi.setKorisnickaSifra(DodavanjeZaposlenika.encryptPassword(sifra));
				}
					
				novi.setKorisnickoIme(KorisnickoIme);
				novi.setPrivilegija(privilegija);
				
				java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
				java.util.Date trenutno = sdf.parse(datumRodjenja);
				@SuppressWarnings("deprecation")
				Timestamp datum = new Timestamp(trenutno.getYear(), trenutno.getMonth(), trenutno.getDate(), 0,0,0, 0);
				novi.set_datumRodjenja(datum);
				
				session.update(novi);
				System.out.println("Uspjesno ste izmjenili zaposlenika: " + novi.get_imeIPrezime() + "");
				
				t.commit();
	        }
	        else{
	        if((list.get(0).getId() == novi.getId())  || ((list.get(0).getKorisnickoIme() == KorisnickoIme) && (list.get(0).getId() == novi.getId()))){
	        
	        novi.setAdresa(adresa);
			novi.setBrojTelefona(telefon);
			novi.setEmail(eMail);
				
			if(!sifra.equals("")){
				novi.setKorisnickaSifra(DodavanjeZaposlenika.encryptPassword(sifra));
			}
				
			novi.setKorisnickoIme(KorisnickoIme);
			novi.setPrivilegija(privilegija);
			
			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
			java.util.Date trenutno = sdf.parse(datumRodjenja);
			@SuppressWarnings("deprecation")
			Timestamp datum = new Timestamp(trenutno.getYear(), trenutno.getMonth(), trenutno.getDate(), 0,0,0, 0);
			novi.set_datumRodjenja(datum);
			
			session.update(novi);
			System.out.println("Uspjesno ste izmjenili zaposlenika: " + novi.get_imeIPrezime() + "");
			
			t.commit();
	        }
	        else{
	        	
	        	System.out.println("Korisnicko ime je zauzeto!");
	        }
	        }
		}
		catch (Exception ex) {
			
			if(ex.toString().equals("java.text.ParseException: Unparseable date: \"YYYY-MM-DD\"")){
				System.out.println("Nepravilno ste unijeli datum. \nUnesite datum u formatu yyyy-mm-dd.");
			}
			else{
			System.out.println(ex.toString());
		}}
		finally {
	         session.close(); 
	      }}
		return novi;
	}
	
	public Zaposlenik obrisiZaposlenika(Zaposlenik z){
		Session session = HibernateUtil.getSessionFactory().openSession();
	      Transaction tx = null;
	      /*Dobavljanje id-a zaposlenika koji se treba brisati*/
	      long EmployeeID = z.getId();
	      Zaposlenik employee = new Zaposlenik();
	      try{
	    	/*Brisanje zaposlenika iz baze*/
	        tx = session.beginTransaction();
	        employee = (Zaposlenik) session.get(Zaposlenik.class, EmployeeID); 
	        
	        session.delete(employee); 
	        /*Malo ja testiram da li mogu komitati sve ovo*/
	        int b = 12345;
	        
	        
	        
	        
	        
			
			/*Komitanje u bazu izmjena nad zaposlenicima*/
	        tx.commit();
	         
	        System.out.println("Uspjesno ste deaktivirali racun: " + employee.get_imeIPrezime());
	      }
	      catch (Exception ex) {
				System.out.println(ex.toString());
	      }
	      finally {
	         session.close(); 
	      }
		return employee;
	}
}
