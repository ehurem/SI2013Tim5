package controller;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;

import javax.swing.JTextArea;

import org.junit.Test;

import Models.Zalba;

public class UnosZalbeTest {

	@Test
	public final void testUnosZalbe() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testUnesiZalbuUBazu() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testValidirajPraznoSaTekstom() {
		JTextArea textArea = new JTextArea();
		textArea.setText("Test tekst!");
		assertTrue(UnosZalbe.validirajPrazno(textArea));
	}
	
	@Test (expected=IllegalArgumentException.class)
	public final void testExceptionValidirajPrazno() {
		JTextArea textArea = new JTextArea();
		textArea.setText("");
		UnosZalbe.validirajPrazno(textArea);
	}
	
	@Test(expected=ClassCastException.class)
	public final void testSQLDateAndUtilDateNotSame(){
		Zalba f = new Zalba();
		Calendar cal = Calendar.getInstance();
		Date now = cal.getTime();
		f.setDatumPodnosenja((java.sql.Date) now); //expected exception here since it's not possible to cast to sql
		
	}
	


}
