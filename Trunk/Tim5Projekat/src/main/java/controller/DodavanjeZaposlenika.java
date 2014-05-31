package controller;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.Formatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import org.hibernate.Session;
import org.hibernate.Transaction;

import tim5.si.unsa.ba.Tim5Projekat.HibernateUtil;
import Models.Zaposlenik;

public class DodavanjeZaposlenika {

	public static Long DodajZaposlenik(JTextField t_imeIPrezime, JTextField t_mjestoStanovanja,
			JTextField t_brojTelefona, JTextField t_emailAdresa, JTextField t_korisnickaSifra,
			JTextField t_korisnickoIme, @SuppressWarnings("rawtypes") JComboBox c_privilegije,
			JTextField t_datumRodjenja) throws Exception  {
		try {
			
			Session session = HibernateUtil.getSessionFactory().openSession();
			
			try {
				Transaction t = session.beginTransaction();
				Zaposlenik novi = new Zaposlenik();
				novi.set_imeIPrezime(t_imeIPrezime.getText());
				novi.setAdresa(t_mjestoStanovanja.getText());
				novi.setBrojTelefona(t_brojTelefona.getText());
				novi.setEmail(t_emailAdresa.getText());
				novi.setKorisnickaSifra(encryptPassword(t_korisnickaSifra.getText()));
				novi.setKorisnickoIme(t_korisnickoIme.getText());
				novi.setPrivilegija(c_privilegije.getSelectedItem().toString());
				
				Pattern pattern = Pattern.compile("^[A-Z]{1}[a-z]{2,}\\s[A-Z][a-z]{2,}$");
				Matcher matcher = pattern.matcher(novi.get_imeIPrezime());
				boolean dobro = matcher.matches();
				if (!dobro) {
					throw new IllegalArgumentException("Ime i prezime nisu u dobrom formatu" );
				}
				if (novi.getEmail().equals("")) {
					throw new IllegalArgumentException("Email nije u dobrom formatu");
				}
				if (novi.getAdresa().equals("")) {
					throw new IllegalArgumentException("Adresa nije u dobrom formatu");
				}
				
				if (novi.getBrojTelefona().equals("")) {
					throw new IllegalArgumentException("Broj telefona nije u dobrom formatu");
				}
				
				if (t_korisnickaSifra.equals("")) {
					throw new IllegalArgumentException("Korisnicka sifra nije u dobrom formatu");
				}
				if (novi.getKorisnickoIme().equals("")) {
					throw new IllegalArgumentException("Korisnicko ime nije u dobrom formatu");
				}
				if (t_datumRodjenja.getText().equals("")) {
					throw new IllegalArgumentException("Datum rodjenja nije u dobrom formatu");
				}
				
				java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
				java.util.Date trenutno = sdf.parse(t_datumRodjenja.getText());
				@SuppressWarnings("deprecation")
				Timestamp datum = new Timestamp(trenutno.getYear(), trenutno.getMonth(), trenutno.getDate(), 0,0,0, 0);
				//infoBox(datum.toString(), null);
				
				novi.set_status(true);
				novi.set_datumRodjenja(datum);
				
				novi.setId( (Long ) session.save(novi));
				
				t.commit();
				
				t_imeIPrezime.setText("");
				t_brojTelefona.setText("");
				t_emailAdresa.setText("");
				t_datumRodjenja.setText("YYYY-MM-DD");
				t_korisnickaSifra.setText("");
				t_korisnickoIme.setText("");
				t_mjestoStanovanja.setText("");
				
				
				return  novi.getId();
			}
			catch (Exception e2) {
				throw e2;
			}
			finally {
				if (session != null) 
					try {
						session.close();
					}
					catch (Exception e3) {
						throw e3;
					}
			}
			
			
		}
		catch (Exception ex) {
			throw ex;
		}
	}
	
	
	
	public static String encryptPassword(String password)
	{
		String sha1 = "";
	    try
	    {
	        MessageDigest crypt = MessageDigest.getInstance("SHA-1");
	        crypt.reset();
	        crypt.update(password.getBytes("UTF-8"));
	        sha1 = byteToHex(crypt.digest());
	    }
	    catch(NoSuchAlgorithmException e)
	    {
	        e.printStackTrace();
	    }
	    catch(UnsupportedEncodingException e)
	    {
	        e.printStackTrace();
	    }
	    return sha1;
	}
	public static String byteToHex(final byte[] hash)
	{
	    Formatter formatter = new Formatter();
	    for (byte b : hash)
	    {
	        formatter.format("%02x", b);
	    }
	    String result = formatter.toString();
	    formatter.close();
	    return result;
	}
	
}
