package controller;

import static org.junit.Assert.*;

import java.sql.Date;

import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.internal.jaxb.mapping.hbm.JaxbTuplizerElement;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import tim5.si.unsa.ba.Tim5Projekat.HibernateUtil;
import Models.Klijent;
import Models.Zahtjev;

public class UnosZahtjevaTest {
	
	/*unesiZahtjevUBazu(Long _zaposlenik, JComboBox comboBox_1,
	 *  JComboBox comboBox_3, JTextField textField_2, Boolean dugme, JTextArea textArea) throws Exception{*/
	
	private static Long _zaposlenik;
	private static Long c1;
	private static Integer c3;
	private static JTextField textField_2;
	private static Boolean dugme;
	private static JTextArea textArea;
	
	private static Long id;
	private static Long idKlijenta;
	
	
	//setup
		@BeforeClass
		public static void setUpBeforeClass() throws Exception {
			_zaposlenik = new Long(1);
			c1 = new Long(1);
			c3 =  new Integer(1);
			textField_2 = new JTextField();
			dugme = new Boolean(true);
			textArea = new JTextArea();
			id = new Long(0);
			idKlijenta = new Long(0);
	}
		
	@Test
	public final void testValidirajPraznoField() {
		
		textField_2.setText("Ovo nije prazan text field");
		
		assertTrue(UnosZahtjeva.validirajPrazno(textField_2));
	}

	@Test (expected = IllegalArgumentException.class)
	public final void testValidirajPraznoFieldNeg() {
		
		UnosZahtjeva.validirajPrazno(new JTextField(""));		
	}
	
	@Test
	public final void testValidirajPraznoArea() {
		
		
		textField_2.setText("Ovo nije prazan text field");
		
		assertTrue(UnosZahtjeva.validirajPrazno(textField_2));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public final void testValidirajPraznoAreaNeg() {
		
		UnosZahtjeva.validirajPrazno(new JTextArea(""));	
	}
	
	@Test
	public final void testUnesiZahtjevOK() throws Exception {
		
		//dodati klijenta u bazu
		
		Klijent k = new Klijent();
		
		JTextField t = new JTextField("Niko Nikic");
		JTextField t1 = new JTextField("033/123-456");
		JTextField t2 = new JTextField("niko@nesto.com");
		JTextField t3 = new JTextField("Negdje daleko");
		
		idKlijenta = DodavanjeKlijenta.unesiKlijentaUBazu(k, t, t1, t2, t3);
		
		
		textField_2.setText("veš mašina");
		textArea.setText("Skače pri centrifugi");
		
		
		id = UnosZahtjeva.unesiZahtjevUBazu(_zaposlenik, idKlijenta, c3, textField_2, dugme, textArea);
		
		assertNotEquals(Long.valueOf(0), id);
		
	}
	
	@Test (expected = Exception.class)
	public final void testUnesiZahtjevPrazno() throws Exception {
		
		textField_2.setText("");
		UnosZahtjeva.unesiZahtjevUBazu(_zaposlenik, c1, c3, textField_2, dugme, textArea);
	}
	
	@AfterClass
	public static void tearDown()
	{
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		
		Zahtjev uneseni = (Zahtjev) session.get(Zahtjev.class, id); 
		session.delete(uneseni); 
		
		Klijent testniKlijent = (Klijent) session.get(Klijent.class, idKlijenta);
		session.delete(testniKlijent);
		
		tx.commit();
		session.close();
	}
	
}
