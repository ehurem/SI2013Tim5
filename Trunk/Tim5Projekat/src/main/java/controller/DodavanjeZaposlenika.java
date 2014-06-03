package controller;

import java.awt.Color;
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

				if (!provjeraImena(novi.get_imeIPrezime())) {
					throw new IllegalArgumentException("Ime i prezime nisu u dobrom formatu" );
				}
				if (!validirajMail(novi.getEmail())) {
					throw new IllegalArgumentException("Email nije u dobrom formatu");
				}
				if (!validirajAdresu(novi.getAdresa())) {
					throw new IllegalArgumentException("Adresa nije u dobrom formatu");
				}
				
				if (!validirajTelefon(novi.getBrojTelefona())) {
					throw new IllegalArgumentException("Broj telefona nije u dobrom formatu XXX/XXX-XXX");
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
	public static Boolean validirajTelefon(String t)
	{
		Pattern pattern = Pattern.compile("\\d{3}/\\d{3}-\\d{3}");
	    Matcher matcher = pattern.matcher(t);
	    if (matcher.matches()) return true;
	    else return false;
	}
	
	private static Boolean validirajAdresu(String t) {
		if (t.length() > 44) return false;
		if (t.equals("")) return false;
		if (t.length() < 4 ) return false;
		return true;
	}
	
	private static Boolean validirajMail(String t)
	{
		if (t.length() > 35) return false;
		Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
					+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		Matcher matcher = pattern.matcher(t);
		if (matcher.matches()) return true;
		else return false;
	}
	
	private static Boolean provjeraImena(String ime) {
		if (ime.length() > 30) return false;
		String[] niz = ime.split(" ");
		
		for (int i = 0; i<niz.length; i++) {
			String dio = niz[i];
			String[] patt = dio.split("-");
			for (int j= 0; j<patt.length; j++) {
				if (!patt[j].equals("di") && !patt[j].equals("I") &&
						!patt[j].equals("II") && !patt[j].equals("III") &&
						!patt[j].equals("IV") && !patt[j].equals("V")) {
					Pattern pattern = Pattern.compile("^[A-Z|Č|Ć|Ž|Š|Đ]{1}[a-z|č|ć|ž|š|đ]{2,}$");
					Matcher matcher = pattern.matcher(patt[j]);
					Boolean istina =  matcher.matches();
					if (!istina) return false;
				}
			}
		}
		return true;
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
