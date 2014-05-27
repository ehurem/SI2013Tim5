package controller;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

import javax.swing.JTextField;

import org.eclipse.wb.swing.Administrator.Main;
import org.eclipse.wb.swing.Operater.MainOperater;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import tim5.si.unsa.ba.Tim5Projekat.HibernateUtil;
import Models.Zaposlenik;

public class Ulaz {
	
	private static long _zaposlenik;
	
	public static String provjeraUlaznihPodataka(JTextField t_korisnickoIme, JTextField t_sifra) throws Exception {
		String username = t_korisnickoIme.getText();
		String password = t_sifra.getText();
		String id = null;
		if (username.equals("") || password.equals("")) throw new Exception("Pogrešni podaci za prijavu");
		else {
			Session session = HibernateUtil.getSessionFactory().openSession();
			try {
				Transaction t = session.beginTransaction();
				Criteria criteria = session.createCriteria(Zaposlenik.class);
				criteria.add(Restrictions.eq("_korisnickoIme", username));
				Zaposlenik zaposlenik = (Zaposlenik) criteria.uniqueResult();
				if (zaposlenik != null && zaposlenik.getKorisnickaSifra().equals(encryptPassword(password)))  {
					if (zaposlenik.getPrivilegija().equals("Administrator"))						
						id = "Administrator";
					else if (zaposlenik.getPrivilegija().equals("Operater"))
						id = "Operater";
					else if (zaposlenik.getPrivilegija().equals("Serviser"))
						id = "Serviser";
					set_zaposlenik(zaposlenik.getId());
				}
				else {
					throw new Exception("Unijeli ste neispravne korisnicke podatke");
					//infoBox ("Unijeli ste neispravne korisnicke podatke", null);
				}
				//infoBox (encryptPassword(t_sifra.getText()), t_korisnickoIme.getText());
				t.commit();
			}
			catch (Exception ex) {
				throw new Exception("Greška: " + ex.toString());
			}
			finally {
				if (session != null) 
					try {
						session.close();
					}
					catch (Exception e2) {
						throw new Exception("Greška: " + e2.toString());
					}
			}
		}
		return id;
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

	public static long get_zaposlenik() {
		return _zaposlenik;
	}

	public static void set_zaposlenik(long _zaposlenik) {
		Ulaz._zaposlenik = _zaposlenik;
	}
	
}
