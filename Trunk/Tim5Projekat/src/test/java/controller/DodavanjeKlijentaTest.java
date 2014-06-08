package controller;

import static org.junit.Assert.*;

import javax.swing.JTextField;

import junit.framework.Assert;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import tim5.si.unsa.ba.Tim5Projekat.HibernateUtil;
import Models.Klijent;

public class DodavanjeKlijentaTest{
	
	private static JTextField t;
	private static JTextField t1;
	private static JTextField t2;
	private static JTextField t3;
	private static Klijent _klijent;
	private static Long id;
	
	//setup
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		t = new JTextField();
		t1 = new JTextField();
		t2 = new JTextField();
		t3 = new JTextField();
		_klijent = new Klijent();
	}
	
	//validacija praznog polja - nije prazno polje
	@Test
	public final void testValidirajPrazno() {
				
		t.setText("Ovo nije prazan string");
		
		assertTrue(DodavanjeKlijenta.validirajPrazno(t));
	}	
	
	@Test (expected = IllegalArgumentException.class)
	public final void testValidirajPraznoNeg() {
		
		DodavanjeKlijenta.validirajPrazno(new JTextField(""));
		
	}
	
	
	//validacija broja telefona - neispravan format
	@Test(expected = IllegalArgumentException.class)
	public final void testValidirajTelefon() {
	
		t.setText("123456789");
		
		DodavanjeKlijenta.validirajTelefon(t);
	}
	
	//validacija broja telefona - ispravan format
	@Test	
	public final void testValidirajTelefonIspravno() 
		{
			t.setText("123/456-789");
			
			assertTrue(DodavanjeKlijenta.validirajTelefon(t));
		}
		
	//ispravan mail	
	@Test
	public final void testValidirajMail() {
		
		t.setText("123456789@hotmail.com");
		
		assertTrue(DodavanjeKlijenta.validirajMail(t));
	}
	
	//neispravan mail	
	@Test(expected = IllegalArgumentException.class)
	public final void testValidirajMailNeispravno() {
			t.setText("123/456-789");
			
			assertFalse(DodavanjeKlijenta.validirajMail(t));
		}
	
	@Test(expected = Exception.class)
	public final void testUnesiKlijentaPrazno() throws Exception {		
		
		t.setText("");
		t1.setText("");
		t2.setText("");
		t3.setText("");
		DodavanjeKlijenta.unesiKlijentaUBazu(_klijent, t, t2, t3, t1);
	}
	
	@Test
	public final void testprovjeraImena(){
		t.setText("Veliki Mujo");
		assertTrue(DodavanjeKlijenta.provjeraImena(t));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public final void testprovjeraImenaNeispravan(){
		t.setText("mali mujo");
		DodavanjeKlijenta.provjeraImena(t);
	}
	
	@Test
	public final void testprovjeraAdrese(){
		t.setText("Kovači do broja 21");
		assertTrue(DodavanjeKlijenta.validirajAdresu(t));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public final void testprovjeraAdreseNeispravan(){
		t.setText("hum");
		DodavanjeKlijenta.validirajAdresu(t);
	}
	
	@Test (expected = Exception.class)
	//svi prisutni, jedan u neispravnom formatu
	public final void testUnesiKlijenta() throws Exception {
		t.setText("Ime i prezime");
		t2.setText("123456789");
		t3.setText("mail1@mail.com");
		t1.setText("Neka adresa");
				
		DodavanjeKlijenta.unesiKlijentaUBazu(_klijent, t, t2, t3, t1);
	}
	
	//unos ok
	@Test 
	public final void testUnesiKlijentaOK() throws Exception
	{
		t.setText("Ime i prezime");
		t2.setText("123/456-789");
		t3.setText("mail5@mail.com");
		t1.setText("Neka adresa");
				
		id = DodavanjeKlijenta.unesiKlijentaUBazu(_klijent, t, t2, t3, t1);
		//Assert.assertNotEquals(Long.valueOf(0), id);
		assertNotNull(id);
	}
	
	//za brisanje iz baze klijenta koji se napravi zbog testa	
	@AfterClass
	public static void tearDown()
	{
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		
		Klijent klijent = (Klijent) session.get(Klijent.class, id); 
		session.delete(klijent); 
		tx.commit();
		session.close();
	}
		


}
