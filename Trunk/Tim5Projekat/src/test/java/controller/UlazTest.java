package controller;

import javax.swing.JTextField;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import junit.framework.Assert;
import junit.framework.TestCase;

@RunWith(JUnit4.class)
public class UlazTest extends TestCase {

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
		JTextField t_korisnickoIme = new JTextField("operater");
		JTextField t_sifra = new JTextField("sifra");
		Assert.assertEquals(Ulaz.provjeraUlaznihPodataka(t_korisnickoIme, t_sifra), "Operater");
	}
	@Test
	public void testProvjeraUlaznihPodatakaUspjesnaServiser() throws Exception {
		JTextField t_korisnickoIme = new JTextField("serviser");
		JTextField t_sifra = new JTextField("sifra");
		Assert.assertEquals(Ulaz.provjeraUlaznihPodataka(t_korisnickoIme, t_sifra), "Serviser");
	}

	
	public void testEncryptPassword() {
		String password = "sifra";
		String sha1PASSWORD = "4259031dc85f451a2b7731e8f5ea93193dad63ad";
		Assert.assertEquals(password, sha1PASSWORD);
	}
}
