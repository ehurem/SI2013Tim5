package controller;


import javax.swing.JComboBox;
import javax.swing.JTextField;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.AfterClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import tim5.si.unsa.ba.Tim5Projekat.HibernateUtil;
import Models.Klijent;
import Models.Zaposlenik;
import junit.framework.Assert;

@RunWith(JUnit4.class)
public class DodavanjeZaposlenikaTest {
	
	private static Long id;
	
	@Test (expected = Exception.class)
	public void testDodajZaposlenikaUnosLos() throws Exception {
		JTextField t_imeIPrezime = new JTextField("" );
		JTextField t_mjestoStanovanja= new JTextField("Mjesto");
		JTextField t_brojTelefona= new JTextField("12312311232");
		JTextField t_emailAdresa= new JTextField("nekimanil.com");
		JTextField t_korisnickaSifra= new JTextField("sifra");
		JTextField t_korisnickoIme= new JTextField("alen");
		String[] niz = new String[1];
		niz[0] = "Administrator";
		@SuppressWarnings({ "rawtypes", "unchecked" })
		JComboBox c_privilegije = new JComboBox(niz);
		JTextField t_datumRodjenja = new JTextField("1991-06-01");
		id = DodavanjeZaposlenika.DodajZaposlenik(t_imeIPrezime, t_mjestoStanovanja, t_brojTelefona, t_emailAdresa, t_korisnickaSifra, t_korisnickoIme, c_privilegije, t_datumRodjenja);
		Boolean test =  id != 0;
		Assert.assertTrue(test);
		
	}
	@Test
	public void testDodajZaposlenikDobriPodaci() throws Exception {
		JTextField t_imeIPrezime = new JTextField("Alen Ismic" );
		JTextField t_mjestoStanovanja= new JTextField("Mjesto");
		JTextField t_brojTelefona= new JTextField("12312311232");
		JTextField t_emailAdresa= new JTextField("nekimanil.com");
		JTextField t_korisnickaSifra= new JTextField("sifra");
		JTextField t_korisnickoIme= new JTextField("alen");
		String[] niz = new String[1];
		niz[0] = "Administrator";
		@SuppressWarnings({ "rawtypes", "unchecked" })
		JComboBox c_privilegije = new JComboBox(niz);
		JTextField t_datumRodjenja = new JTextField("1991-06-01");
		id = DodavanjeZaposlenika.DodajZaposlenik(t_imeIPrezime, t_mjestoStanovanja, t_brojTelefona, t_emailAdresa, t_korisnickaSifra, t_korisnickoIme, c_privilegije, t_datumRodjenja);
		Boolean test =  id != 0;
		Assert.assertTrue(test);
		
	}
	
	@AfterClass
	public static void tearDown()
	{
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		
		Zaposlenik zaposlenik = (Zaposlenik) session.get(Zaposlenik.class, id); 
		session.delete(zaposlenik); 
		tx.commit();
		session.close();
	}

	@Test
	public void testEncryptPassword() {
		String password = "sifra";
		String sha1PASSWORD = "4259031dc85f451a2b7731e8f5ea93193dad63ad";
		Assert.assertEquals(sha1PASSWORD, DodavanjeZaposlenika.encryptPassword(password));
	}

}
