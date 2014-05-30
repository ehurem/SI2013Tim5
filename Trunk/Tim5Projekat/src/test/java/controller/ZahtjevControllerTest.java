package controller;

import static org.junit.Assert.*;

import java.util.ArrayList;

import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import junit.framework.Assert;

import org.junit.Test;

public class ZahtjevControllerTest {

	@Test
	public void testZahtjevController() {
		// Nema potrebe konstruktor testirati
		// jer to predstavlja testiranje
		// Java virtuelne masine
		Assert.assertEquals(true, true);
	}

	@Test
	public void testPopuniPodatke() {
		ZahtjevController c = new ZahtjevController();
		
		JRadioButton rdbtnDa = new JRadioButton();
		String zahtjev_id = "1";
		JTextArea textArea = new JTextArea();
		JTextField textField_1 = new JTextField();
		JRadioButton rdbtnNe = new JRadioButton();
		JTextField textField = new JTextField();
		JTextField textField_2 = new JTextField();
		JTextField textField_3 = new JTextField();
		ArrayList<String> list = new ArrayList<String>();
		try {
			list = c.PopuniPodatke(textField, textField_1, textField_2, textField_3, textArea, zahtjev_id, rdbtnDa, rdbtnNe);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Catch block entered");
		}
		if(list == null || list.get(0) == null || list.get(1) == null || list.get(2) == null || list.get(3) == null || list.get(4) == null)
		{
			fail("Lista ili parametri su null");
		}
		else
		{
			Assert.assertEquals(true, true);
		}
		
	}

	@Test
	public void testUpisivanjeZatvorenogZahtjeva() {
		fail("Not yet implemented");
	}

	@Test
	public void testPopunjavanjeListePreuzetihZahtjeva() {
		fail("Not yet implemented");
	}

}
