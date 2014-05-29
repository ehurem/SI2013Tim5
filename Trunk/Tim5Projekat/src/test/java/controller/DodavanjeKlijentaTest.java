package controller;

import javax.swing.JTextField;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import junit.framework.Assert;
import junit.framework.TestCase;

//import controller.DodavanjeKlijenta;
@RunWith(JUnit4.class)
public class DodavanjeKlijentaTest extends TestCase {
	
	@Rule
	public ExpectedException exception = ExpectedException.none();

	//validacija praznog polja - nije prazno polje
	@Test
	public final void testValidirajPrazno() throws Exception {
		
		JTextField t = new JTextField();		
		t.setText("Ovo nije prazan string");
		
		//try {
			Assert.assertTrue(DodavanjeKlijenta.validirajPrazno(t));
		/*} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	
	
	//@Test(expected = java.lang.IllegalArgumentException.class)
	public final void testValidirajPraznoPogresan() {
		
		DodavanjeKlijenta c= new DodavanjeKlijenta();
		
		//@SuppressWarnings("unused")
		//exception.except
		exception.expect(java.lang.IllegalArgumentException.class);
		exception.expectMessage("Bla");
		Boolean validirajPrazno = c.validirajPrazno(new JTextField(""));
		
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
						
			try {
				Assert.assertTrue(DodavanjeKlijenta.validirajTelefon(t));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
		}
		
	//ispravan mail	
	@Test
	public final void testValidirajMail() {
		
		JTextField t = new JTextField();
		t.setText("123456789");
					
		try {
			Assert.assertFalse(DodavanjeKlijenta.validirajMail(t));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}
	
	//neispravan mail	
	@Test
	public final void testValidirajMailNeispravno() {
			
			JTextField t = new JTextField();
			t.setText("123/456-789");
						
			try {
				Assert.assertTrue(DodavanjeKlijenta.validirajMail(t));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
		}
	
	@Test
	public final void testUnesiKlijentaUBazu() {
		fail("Not yet implemented"); // TODO
	}


}
