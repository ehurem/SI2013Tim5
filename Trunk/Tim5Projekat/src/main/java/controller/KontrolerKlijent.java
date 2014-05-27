package controller;

import org.eclipse.wb.swing.Operater.DodajKlijenta;
import org.hibernate.Session;
import org.hibernate.Transaction;

import Models.Klijent;
import tim5.si.unsa.ba.Tim5Projekat.HibernateUtil;


public class KontrolerKlijent {
	
	public KontrolerKlijent(){}
	
	public void unesiKlijentaUBazu(Klijent _klijent)
	{
		Session sesija = HibernateUtil.getSessionFactory().openSession(); 
		
		Transaction transakcija = sesija.beginTransaction(); 
		
		try
		{
			
			_klijent.set_imeIPrezime(textField.getText());
			_klijent.setBrojTelefona(textField_2.getText());
			_klijent.setEmail(textField_3.getText());
			_klijent.set_adresa(textField_1.getText());		
			
			if(validirajPrazno(textField) && validirajPrazno(textField_1) && validirajPrazno(textField_2) && validirajPrazno(textField_3) 
					&& validirajTelefon(textField_2) && validirajMail(textField_3))
			{
				Long id = (Long)sesija.save(_klijent); 
				transakcija.commit(); 
			
				infoBox("Klijent " + get_klijent().get_imeIPrezime() + " je unesen", "UnesenKLijent");
				frmUnosNovogKlijenta.dispose();
			}
			else if (!validirajPrazno(textField) || !validirajPrazno(textField_1) || !validirajPrazno(textField_2) || !validirajPrazno(textField_3))
			{
				infoBox("Svi unosi moraju biti prisutni !", "Prazno polje");
			}
			
		}
		catch(Exception e1)
		{
			infoBox(e1.getMessage(), "Greska !");
		}
		finally
		{
			sesija.close();
		}
	}

}
