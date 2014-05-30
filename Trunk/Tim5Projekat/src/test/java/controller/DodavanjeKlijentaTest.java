package controller;

import static org.junit.Assert.*;

import javax.swing.JTextField;

import org.junit.Test;


public class DodavanjeKlijentaTest{
	
	//validacija praznog polja - nije prazno polje
	@Test
	public final void testValidirajPrazno() {
		
		JTextField t = new JTextField();		
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
		
		JTextField t = new JTextField();
		t.setText("123456789");
		
		DodavanjeKlijenta.validirajTelefon(t);
	}
	
	//validacija broja telefona - ispravan format
	@Test	
	public final void testValidirajTelefonIspravno() 
		{
			JTextField t = new JTextField();
			t.setText("123/456-789");
			
			assertTrue(DodavanjeKlijenta.validirajTelefon(t));
		}
		
	//ispravan mail	
	@Test
	public final void testValidirajMail() {
		
		JTextField t = new JTextField();
		t.setText("123456789@hotmail.com");
		
		assertTrue(DodavanjeKlijenta.validirajMail(t));
	}
	
	//neispravan mail	
	@Test(expected = IllegalArgumentException.class)
	public final void testValidirajMailNeispravno() {
			
			JTextField t = new JTextField();
			t.setText("123/456-789");
			
			assertFalse(DodavanjeKlijenta.validirajMail(t));
		}
	
	@Test
	public final void testUnesiKlijentaUBazu() {
		fail("Not yet implemented"); // TODO
	}


}
