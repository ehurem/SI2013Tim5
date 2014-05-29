package controller;

import java.awt.List;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import tim5.si.unsa.ba.Tim5Projekat.HibernateUtil;
import Models.Klijent;
import Models.Zahtjev;

public class IzvjestajiKontroler {
	
	public IzvjestajiKontroler() {}
	
	public ArrayList<Zahtjev> iscitajListuZahtjevaIzBaze() throws Exception
	{
		ArrayList<Zahtjev> zahtjevi;
		Session sesija = HibernateUtil.getSessionFactory().openSession();
		Transaction t = sesija.beginTransaction();
		try
		{
			Query queryZahtjev = sesija.createQuery("from Zahtjev");
			zahtjevi = (ArrayList<Zahtjev>) queryZahtjev.list();
			t.commit();
		}
		catch(Exception ex)
		{
			throw ex;
		}
		finally
		{
			sesija.close();
		}		
		return zahtjevi;
	}
	
	public static Calendar dateToCalendar(Date date){ 
		  Calendar cal = Calendar.getInstance();
		  cal.setTime(date);
		  return cal;
		}
		
	public static int sabiranjeCijenaZahtjevaZaOdabranuSedmicu(ArrayList<Zahtjev> zahtjevi, int broj) throws Exception{
		int zarada = 0;
		try {
			for (int i=0;i<zahtjevi.size();i++){
				Calendar c = dateToCalendar(zahtjevi.get(i).getDatumZatvaranja());
				if (c.get(Calendar.WEEK_OF_YEAR)==broj) {
				zarada += zahtjevi.get(i).get_cijena();
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
