package controller;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import tim5.si.unsa.ba.Tim5Projekat.HibernateUtil;
import Models.Klijent;
import Models.Zaposlenik;
import junit.framework.Assert;
import junit.framework.TestCase;

//@RunWith(JUnit4.class)
public class UlazTest{
	
	private static Long id;
	private static Long id2;

	@Test(expected = java.lang.Exception.class)
	public void testProvjeraUlaznihPodatakaBezUnosa() throws Exception {
		JTextField t_korisnickoIme = new JTextField("");
		JTextField t_sifra = new JTextField("");
		Ulaz.provjeraUlaznihPodataka(t_korisnickoIme, t_sifra);
	}
	@Test(expected = java.lang.Exception.class)
	public void testProvjeraUlaznihPodatakaNepostojeciKorisnik() throws Exception {
		JTextField t_korisnickoIme = new JTextField("Korisnik");
		JTextField t_sifra = new JTextField("lijevaSifra");
		Ulaz.provjeraUlaznihPodataka(t_korisnickoIme, t_sifra);
	}	
	
	@Test
	public void testProvjeraUlaznihPodatakaUspjesnaAdministrator() throws Exception {
		JTextField t_korisnickoIme = new JTextField("admin");
		JTextField t_sifra = new JTextField("sifra");
		Assert.assertEquals(Ulaz.provjeraUlaznihPodataka(t_korisnickoIme, t_sifra), "Administrator");
	}
	@Test
	public void testProvjeraUlaznihPodatakaUspjesnaOperater() throws Exception {
		
		JTextField t_korisnickaSifra= new JTextField("sifra");
		JTextField t_korisnickoIme= new JTextField("merima1");
		String[] niz = new String[1];
		niz[0] = "Operater";
		@SuppressWarnings({ "rawtypes", "unchecked" })
		JComboBox c_privilegije = new JComboBox(niz);
		JTextField t_datumRodjenja = new JTextField("1991-06-01");
		
		id = DodavanjeZaposlenika.DodajZaposlenik(new JTextField("Merima Hadzic"), 
				new JTextField("Nova Adresa"), new JTextField("012/345-678"), new JTextField("neki_mail@mail.com"), 
				new JTextField("sifra"), new JTextField("merima1"), c_privilegije, t_datumRodjenja);
		
		t_korisnickaSifra.setText("sifra");
		t_korisnickoIme.setText("merima1");
		
		
		Assert.assertEquals(Ulaz.provjeraUlaznihPodataka(t_korisnickoIme, t_korisnickaSifra), "Operater");
		
	}
	@Test
	public void testProvjeraUlaznihPodatakaUspjesnaServiser() throws Exception {
		JTextField t_korisnickaSifra= new JTextField("sifra");
		JTextField t_korisnickoIme= new JTextField("alen2");
		String[] niz = new String[1];
		niz[0] = "Serviser";
		@SuppressWarnings({ "rawtypes", "unchecked" })
		JComboBox c_privilegije = new JComboBox(niz);
		JTextField t_datumRodjenja = new JTextField("1991-06-01");
		
		id2 = DodavanjeZaposlenika.DodajZaposlenik(new JTextField("Alenko Ismic"), 
				new JTextField("Nova Adresa"), new JTextField("012/345-678"), new JTextField("neki_mail_opet@mail.com"), 
				new JTextField("sifra"), new JTextField("alen2"), c_privilegije, t_datumRodjenja);
		
		t_korisnickaSifra.setText("sifra");
		t_korisnickoIme.setText("alen2");
		
		
		Assert.assertEquals(Ulaz.provjeraUlaznihPodataka(t_korisnickoIme, t_korisnickaSifra), "Serviser");
	}
	
	
	
	public void testEncryptPassword() {
		String password = "sifra";
		String sha1PASSWORD = "4259031dc85f451a2b7731e8f5ea93193dad63ad";
		Assert.assertEquals(password, sha1PASSWORD);
	}
	
	@AfterClass
	public static void tearDown()
	{
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		
		Zaposlenik zaposlenik = (Zaposlenik) session.get(Zaposlenik.class, id); 
		session.delete(zaposlenik); 
		
		Zaposlenik zaposlenik2 = (Zaposlenik) session.get(Zaposlenik.class, id2); 
		session.delete(zaposlenik2); 
				
		tx.commit();
		session.close();
	}



}
