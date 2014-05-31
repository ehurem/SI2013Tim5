package controller;


import java.sql.Date;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.*;

import tim5.si.unsa.ba.Tim5Projekat.HibernateUtil;
import Models.*;

public class IzvjestajiKontroler {
	
	public IzvjestajiKontroler() {}
	
	public static java.util.List<Zahtjev> iscitajListuZahtjevaIzBaze() throws Exception
	{
		
		List zahtjevi = null;
		Session sesija = HibernateUtil.getSessionFactory().openSession();
		   try {
				Transaction tr = sesija.beginTransaction();
				Query queryZahtjev = sesija.createQuery("from Zahtjev");
				zahtjevi =  queryZahtjev.list();
				tr.commit();
		   }
		   catch (Exception ex) { 
			   throw ex;
		   }
		   finally { 
			   sesija.close();
			   }
		   
		return zahtjevi;
	}
	
	public static Calendar dateToCalendar(Date date){ 
		  Calendar cal = Calendar.getInstance();
		  cal.setTime(date);
		  return cal;
		}
		
	public static double sabiranjeCijenaZahtjevaZaOdabranuSedmicu(java.util.List<Zahtjev> zahtjevi, int broj) throws Exception{
		double zarada = 0;
		try {
			for (int i=0;i<zahtjevi.size();i++){
				if (zahtjevi.get(i).getDatumZatvaranja()!=null) {
				Calendar c = dateToCalendar(zahtjevi.get(i).getDatumZatvaranja());
				if (c.get(Calendar.WEEK_OF_YEAR)==broj) {
				zarada += zahtjevi.get(i).get_cijena();
				}
				}
			}
			}
			catch(Exception ex)
			{
				throw ex;
				//JOptionPane.showMessageDialog(null, "Nema zahtjeva u odabranoj sedmici", "InfoBox: " + ex.toString(), JOptionPane.INFORMATION_MESSAGE);
			}
		return zarada;
	}
}
