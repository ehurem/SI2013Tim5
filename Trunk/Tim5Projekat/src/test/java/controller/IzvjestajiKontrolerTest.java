package controller;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import junit.framework.Assert;

import org.junit.Test;

import Models.Zahtjev;

public class IzvjestajiKontrolerTest {

	@Test
	public void testirajKonverzijuDatumaTacno() {
		Date prvi = new Date();
		java.sql.Date date = new java.sql.Date(prvi.getTime());
		Calendar drugi = Calendar.getInstance();
		drugi.setTime(prvi);
		Assert.assertEquals(IzvjestajiKontroler.dateToCalendar(date), drugi);
	}
	@Test
	public void testirajKonverzijuDatumaNetacno() {
		Date prvi = new Date();
		java.sql.Date date = new java.sql.Date(prvi.getTime());
		date.setYear(3000);
		Calendar drugi = Calendar.getInstance();
		Assert.assertNotSame(IzvjestajiKontroler.dateToCalendar(date), drugi);
	}
	
	@Test (expected = java.lang.Exception.class)
	public void testirajRacunanjeZaradeNetacno() throws Exception  {
		ArrayList<Zahtjev> zahtjevi = null;
		Zahtjev prvi = new Zahtjev();
		Zahtjev drugi = new Zahtjev();
		prvi.set_cijena(200);
		drugi.set_cijena(300);
	    java.sql.Date jsqlD1 = java.sql.Date.valueOf( "2014-01-01" );
	    java.sql.Date jsqlD2 = java.sql.Date.valueOf( "2014-01-01" );
	    Calendar proba = Calendar.getInstance();
	    proba.setTime(jsqlD1);
		prvi.setDatumZatvaranja(jsqlD1);
		drugi.setDatumZatvaranja(jsqlD2);
		zahtjevi.add(prvi);
		zahtjevi.add(drugi);
		
		IzvjestajiKontroler.sabiranjeCijenaZahtjevaZaOdabranuSedmicu(zahtjevi,proba.get(Calendar.WEEK_OF_YEAR));
		
		
		
	}
	@Test
    public void testirajRacunanjeZaradeTacno() throws Exception {
		ArrayList<Zahtjev> zahtjevi = new ArrayList<Zahtjev>();
		Zahtjev prvi = new Zahtjev();
		Zahtjev drugi = new Zahtjev();
		prvi.set_cijena(200);
		drugi.set_cijena(300);
	    java.sql.Date jsqlD1 = java.sql.Date.valueOf( "2014-01-01" );
	    java.sql.Date jsqlD2 = java.sql.Date.valueOf( "2014-01-01" );
	    Calendar proba = Calendar.getInstance();
	    proba.setTime(jsqlD1);
		prvi.setDatumZatvaranja(jsqlD1);
		drugi.setDatumZatvaranja(jsqlD1);
		zahtjevi.add(prvi);
		zahtjevi.add(drugi);
		
		Assert.assertEquals(IzvjestajiKontroler.sabiranjeCijenaZahtjevaZaOdabranuSedmicu(zahtjevi,proba.get(Calendar.WEEK_OF_YEAR)),500);
		
		
	}
	
}
