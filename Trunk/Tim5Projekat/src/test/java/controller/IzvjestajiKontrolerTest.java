package controller;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
    public void testirajRacunanjeZaradeTacno() {
		List <Zahtjev> zahtjevi = new ArrayList<Zahtjev>();
		Zahtjev prvi = new Zahtjev();
		Zahtjev drugi = new Zahtjev();
	    java.sql.Date jsqlD1 = java.sql.Date.valueOf( "2014-01-01" );
	    java.sql.Date jsqlD2 = java.sql.Date.valueOf( "2014-01-01" );
	    Calendar proba = Calendar.getInstance();
	    proba.setTime(jsqlD1);
		prvi.setDatumZatvaranja(jsqlD1);
		drugi.setDatumZatvaranja(jsqlD1);
		prvi.setDatumOtvaranja(jsqlD1);
		drugi.setDatumOtvaranja(jsqlD1);
		prvi.setGarancija(true);
		drugi.setGarancija(true);
		prvi.setID(1);
		drugi.setID(1);
		prvi.set_cijena(200);
		drugi.set_cijena(300);
		prvi.setKomentar("dada");
        drugi.setKomentar("nene");
        prvi.setStatus("otvoren");
        drugi.setStatus("otvoren");
        prvi.setKlijent(2);
        drugi.setKlijent(2);
        prvi.setPrioritet(1);
        drugi.setPrioritet(1);
        prvi.setTipUredaja("masina");
        drugi.setTipUredaja("masina");
        prvi.setZaposlenik((long) 300);
        drugi.setZaposlenik((long) 3);
        zahtjevi.clear();
		zahtjevi.add(prvi);
		zahtjevi.add(drugi);		
		try {
			Assert.assertEquals(IzvjestajiKontroler.sabiranjeCijenaZahtjevaZaOdabranuSedmicu(zahtjevi,proba.get(Calendar.WEEK_OF_YEAR)),500.0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	@Test
	public void dajZatvoreneTacno() {
		List <Zahtjev> zahtjevi = new ArrayList<Zahtjev>();
		Zahtjev prvi = new Zahtjev();
		Zahtjev drugi = new Zahtjev();
	    java.sql.Date jsqlD1 = java.sql.Date.valueOf( "2014-01-01" );
	    java.sql.Date jsqlD2 = java.sql.Date.valueOf( "2014-01-01" );
	    Calendar proba = Calendar.getInstance();
	    proba.setTime(jsqlD1);
		prvi.setDatumZatvaranja(jsqlD1);
		drugi.setDatumZatvaranja(jsqlD1);
		prvi.setDatumOtvaranja(jsqlD1);
		drugi.setDatumOtvaranja(jsqlD1);
		prvi.setGarancija(true);
		drugi.setGarancija(true);
		prvi.setID(1);
		drugi.setID(1);
		prvi.set_cijena(200);
		drugi.set_cijena(300);
		prvi.setKomentar("dada");
        drugi.setKomentar("nene");
        prvi.setStatus("otvoren");
        drugi.setStatus("otvoren");
        prvi.setKlijent(2);
        drugi.setKlijent(2);
        prvi.setPrioritet(1);
        drugi.setPrioritet(1);
        prvi.setTipUredaja("masina");
        drugi.setTipUredaja("masina");
        prvi.setZaposlenik((long) 300);
        drugi.setZaposlenik((long) 3);
        zahtjevi.clear();
		zahtjevi.add(prvi);
		zahtjevi.add(drugi);
		Assert.assertEquals(IzvjestajiKontroler.dajBrojZatvorenih(zahtjevi, proba.get(Calendar.WEEK_OF_YEAR)),2);
	}
	@Test
	public void dajZatvoreneNetacno() {
		List <Zahtjev> zahtjevi = new ArrayList<Zahtjev>();
		Zahtjev prvi = new Zahtjev();
		Zahtjev drugi = new Zahtjev();
	    java.sql.Date jsqlD1 = java.sql.Date.valueOf( "2014-01-01" );
	    java.sql.Date jsqlD2 = java.sql.Date.valueOf( "2014-01-01" );
	    Calendar proba = Calendar.getInstance();
	    proba.setTime(jsqlD1);
		prvi.setDatumZatvaranja(jsqlD1);
		drugi.setDatumZatvaranja(jsqlD1);
		prvi.setDatumOtvaranja(jsqlD1);
		drugi.setDatumOtvaranja(jsqlD1);
		prvi.setGarancija(true);
		drugi.setGarancija(true);
		prvi.setID(1);
		drugi.setID(1);
		prvi.set_cijena(200);
		drugi.set_cijena(300);
		prvi.setKomentar("dada");
        drugi.setKomentar("nene");
        prvi.setStatus("otvoren");
        drugi.setStatus("otvoren");
        prvi.setKlijent(2);
        drugi.setKlijent(2);
        prvi.setPrioritet(1);
        drugi.setPrioritet(1);
        prvi.setTipUredaja("masina");
        drugi.setTipUredaja("masina");
        prvi.setZaposlenik((long) 300);
        drugi.setZaposlenik((long) 3);
        zahtjevi.clear();
		zahtjevi.add(prvi);
		zahtjevi.add(drugi);
		Assert.assertNotSame(IzvjestajiKontroler.dajBrojZatvorenih(zahtjevi, proba.get(Calendar.WEEK_OF_YEAR)),100);
	}
	
	
	@Test
	public void dajOtvoreneNetacno() {
		List <Zahtjev> zahtjevi = new ArrayList<Zahtjev>();
		Zahtjev prvi = new Zahtjev();
		Zahtjev drugi = new Zahtjev();
	    java.sql.Date jsqlD1 = java.sql.Date.valueOf( "2014-01-01" );
	    java.sql.Date jsqlD2 = java.sql.Date.valueOf( "2014-10-01" );
	    Calendar proba = Calendar.getInstance();
	    proba.setTime(jsqlD1);
		prvi.setDatumZatvaranja(jsqlD2);
		drugi.setDatumZatvaranja(jsqlD2);
		prvi.setDatumOtvaranja(jsqlD1);
		drugi.setDatumOtvaranja(jsqlD1);
		prvi.setGarancija(true);
		drugi.setGarancija(true);
		prvi.setID(1);
		drugi.setID(1);
		prvi.set_cijena(200);
		drugi.set_cijena(300);
		prvi.setKomentar("dada");
        drugi.setKomentar("nene");
        prvi.setStatus("otvoren");
        drugi.setStatus("otvoren");
        prvi.setKlijent(2);
        drugi.setKlijent(2);
        prvi.setPrioritet(1);
        drugi.setPrioritet(1);
        prvi.setTipUredaja("masina");
        drugi.setTipUredaja("masina");
        prvi.setZaposlenik((long) 300);
        drugi.setZaposlenik((long) 3);
        zahtjevi.clear();
		zahtjevi.add(prvi);
		zahtjevi.add(drugi);
		Assert.assertNotSame(IzvjestajiKontroler.dajBrojZatvorenih(zahtjevi, proba.get(Calendar.WEEK_OF_YEAR)),100);
	}
	
	@Test
	public void dajOtvoreneTacno() {
		List <Zahtjev> zahtjevi = new ArrayList<Zahtjev>();
		Zahtjev prvi = new Zahtjev();
		Zahtjev drugi = new Zahtjev();
	    java.sql.Date jsqlD1 = java.sql.Date.valueOf( "2014-01-01" );
	    java.sql.Date jsqlD2 = java.sql.Date.valueOf( "2014-10-01" );
	    Calendar proba = Calendar.getInstance();
	    proba.setTime(jsqlD1);
		prvi.setDatumZatvaranja(jsqlD2);
		drugi.setDatumZatvaranja(jsqlD2);
		prvi.setDatumOtvaranja(jsqlD1);
		drugi.setDatumOtvaranja(jsqlD1);
		prvi.setGarancija(true);
		drugi.setGarancija(true);
		prvi.setID(1);
		drugi.setID(1);
		prvi.set_cijena(200);
		drugi.set_cijena(300);
		prvi.setKomentar("dada");
        drugi.setKomentar("nene");
        prvi.setStatus("otvoren");
        drugi.setStatus("otvoren");
        prvi.setKlijent(2);
        drugi.setKlijent(2);
        prvi.setPrioritet(1);
        drugi.setPrioritet(1);
        prvi.setTipUredaja("masina");
        drugi.setTipUredaja("masina");
        prvi.setZaposlenik((long) 300);
        drugi.setZaposlenik((long) 3);
        zahtjevi.clear();
		zahtjevi.add(prvi);
		zahtjevi.add(drugi);
		Assert.assertNotSame(IzvjestajiKontroler.dajBrojZatvorenih(zahtjevi, proba.get(Calendar.WEEK_OF_YEAR)),2);
	}
	
	
	
}
