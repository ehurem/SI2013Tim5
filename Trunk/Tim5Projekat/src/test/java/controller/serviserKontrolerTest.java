package controller;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Models.Klijent;
import Models.Zahtjev;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import tim5.si.unsa.ba.Tim5Projekat.HibernateUtil;

public class serviserKontrolerTest {
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
	public final void testUcitajOtvoreneZahtjeve() throws Exception {
		List <Zahtjev> zahtjevi = null;
		boolean test=true;
		zahtjevi = serviserKontroler.ucitajOtvoreneZahtjeve();
		for (int i=0; i<zahtjevi.size(); i++) {
			if (!zahtjevi.get(i).getStatus().equals("otvoren")) {
				test=false;
				break;
			}
		}
		assertTrue(test);
	}

	@Test
	public final void testPopuniTabelu() throws Exception {
		List <Zahtjev> zahtjevi = null;
		JTable tabela = new JTable();
		DefaultTableModel tmodel = new DefaultTableModel();
		tabela.setModel(tmodel);
		zahtjevi = serviserKontroler.ucitajOtvoreneZahtjeve();
		assertTrue(serviserKontroler.popuniTabelu(tabela,zahtjevi));
	}


   @Test
	public final void testUzmiZahtjevNaIzvrsavanje() throws Exception {

		Klijent k = new Klijent();
		JTextField t = new JTextField("Niko Nikic");
		JTextField t1 = new JTextField("033/123-456");
		JTextField t2 = new JTextField("niko@nesto.com");
		JTextField t3 = new JTextField("Negdje daleko");
		idKlijenta = DodavanjeKlijenta.unesiKlijentaUBazu(k, t, t1, t2, t3);
		textField_2.setText("veš mašina");
		textArea.setText("Skače pri centrifugi");		
		id = UnosZahtjeva.unesiZahtjevUBazu(_zaposlenik, idKlijenta, c3, textField_2, dugme, textArea);
		List <Zahtjev> zahtjevi = serviserKontroler.ucitajOtvoreneZahtjeve();
		int size = zahtjevi.size();
		JTable tabela = new JTable();
		DefaultTableModel tmodel = new DefaultTableModel();
		tabela.setModel(tmodel);
	    serviserKontroler.popuniTabelu(tabela, zahtjevi);
	    serviserKontroler.uzmiZahtjevNaIzvrsavanje(tabela, zahtjevi, 1, zahtjevi.size()-1);
	    assertEquals(zahtjevi.size(),size-1);
	//	}
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
