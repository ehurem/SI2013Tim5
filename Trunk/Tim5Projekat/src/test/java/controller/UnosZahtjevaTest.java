package controller;

import static org.junit.Assert.*;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.junit.Test;

public class UnosZahtjevaTest {

	
	
	@Test
	public final void testUnesiZahtjevUBazu() {
		fail("Not yet implemented"); // TODO
	}

	
	@Test
	public final void testValidirajPraznoField() {
		
		JTextField t = new JTextField();
		t.setText("Ovo nije prazan text field");
		
		assertTrue(UnosZahtjeva.validirajPrazno(t));
	}

	@Test (expected = IllegalArgumentException.class)
	public final void testValidirajPraznoFieldNeg() {
		
		UnosZahtjeva.validirajPrazno(new JTextField(""));		
	}
	
	@Test
	public final void testValidirajPraznoArea() {
		
		JTextArea t = new JTextArea();
		t.setText("Ovo nije prazan text field");
		
		assertTrue(UnosZahtjeva.validirajPrazno(t));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public final void testValidirajPraznoAreaNeg() {
		
		UnosZahtjeva.validirajPrazno(new JTextArea(""));	
	}
}
