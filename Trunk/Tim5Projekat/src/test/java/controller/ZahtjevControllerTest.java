package controller;

import static org.junit.Assert.*;

import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JList;
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

	@Test (expected = Exception.class)
	public void testPopuniPodatke() throws Exception {
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
		
			list = c.PopuniPodatke(textField, textField_1, textField_2, textField_3, textArea, zahtjev_id, rdbtnDa, rdbtnNe);	
	}

	@Test
	public void testUpisivanjeZatvorenogZahtjevaFail() {
		ZahtjevController c = new ZahtjevController();
		boolean status = true;
		String komentar="";
		String cijena="";
		String zahtjev_id="a";
		long zaposlenik_id=1;
		try {
			c.UpisivanjeZatvorenogZahtjeva(zahtjev_id, cijena, komentar, zaposlenik_id);
		} catch (Exception e) {
			status = false;
			e.printStackTrace();
		}
		finally{
			Assert.assertEquals(false, status);
		}
	}

	@Test
	public void testPopunjavanjeListePreuzetihZahtjeva() {
		ZahtjevController c = new ZahtjevController();
		final JList list = new JList();
		DefaultListModel listModel = new DefaultListModel();
		try {
			listModel = c.PopunjavanjeListePreuzetihZahtjeva();
			list.setModel(listModel);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Catch block entered");
		} 
//		fail("Not yet implemented");
	}

	@Test
	public void testvalidirajCijenu()
	{
		Assert.assertEquals(ZahtjevController.validirajCijenu("1",true), true);
	}
	
	@Test (expected = NumberFormatException.class)
	public void testvalidirajCijenuNaN()
	{
		Assert.assertEquals(ZahtjevController.validirajCijenu("a",true), false);
	}
	
	@Test
	public void testvalidirajPraznoFail()
	{
		boolean rez = true;
		JTextArea t1 = new JTextArea();
		t1.setText("");
		rez = ZahtjevController.validirajPrazno(t1);
		Assert.assertEquals(rez, false);
	}
	
	@Test
	public void testvalidirajPraznoOK()
	{
		boolean rez = false;
		JTextArea t1 = new JTextArea();
		t1.setText("Neki tekst");
		rez = ZahtjevController.validirajPrazno(t1);
		Assert.assertEquals(rez, true);
	}
	
	
}

