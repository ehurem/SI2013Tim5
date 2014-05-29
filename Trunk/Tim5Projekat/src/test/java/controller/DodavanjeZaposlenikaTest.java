package controller;

import static org.junit.Assert.*;

import java.util.Random;

import javax.crypto.EncryptedPrivateKeyInfo;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import junit.framework.Assert;
import junit.framework.TestCase;

@RunWith(JUnit4.class)
public class DodavanjeZaposlenikaTest {

	@Test
	public void testDodajZaposlenikDobriPodaci() throws Exception {
		Random random  = new Random();
		JTextField t_imeIPrezime = new JTextField("Alen Ismic" );
		JTextField t_mjestoStanovanja= new JTextField("Mjesto");
		JTextField t_brojTelefona= new JTextField("12312311232");
		JTextField t_emailAdresa= new JTextField("nekimanil.com");
		JTextField t_korisnickaSifra= new JTextField("sifra");
		JTextField t_korisnickoIme= new JTextField("alen" + (random.nextInt(30)+97));
		String[] niz = new String[1];
		niz[0] = "Administrator";
		JComboBox c_privilegije = new JComboBox(niz);
		JTextField t_datumRodjenja = new JTextField("1991-06-01");
		Boolean test = DodavanjeZaposlenika.DodajZaposlenik(t_imeIPrezime, t_mjestoStanovanja, t_brojTelefona, t_emailAdresa, t_korisnickaSifra, t_korisnickoIme, c_privilegije, t_datumRodjenja) !=0;
		Assert.assertTrue(test);
	}

	@Test
	public void testEncryptPassword() {
		String password = "sifra";
		String sha1PASSWORD = "4259031dc85f451a2b7731e8f5ea93193dad63ad";
		Assert.assertEquals(sha1PASSWORD, DodavanjeZaposlenika.encryptPassword(password));
	}

}
