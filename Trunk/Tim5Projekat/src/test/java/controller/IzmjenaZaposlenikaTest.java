package controller;

import static org.junit.Assert.*;

import javax.swing.JTextField;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import tim5.si.unsa.ba.Tim5Projekat.HibernateUtil;
import Models.Klijent;
import Models.Zaposlenik;

public class IzmjenaZaposlenikaTest extends IzmjenaZaposlenika {

	private static String t;
	private static String t1;
	private static String t2;
	private static String t3;
	private static Zaposlenik _Zaposlenik;

	
	//setup
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		t = new String();
		t1 = new String();
		t2 = new String();
		t3 = new String();
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		_Zaposlenik  = (Zaposlenik)session.get(Zaposlenik.class, new Long(2)); 
		session.close();
		
	}
	
	@Test
	public final void testIzmjeni() throws Exception {
		
		IzmjenaZaposlenika.izmjeni(_Zaposlenik);
		
	}
	
	@Test(expected = Exception.class)
	public final void testIzmjeniException() throws Exception {
		IzmjenaZaposlenika.izmjeni(new Zaposlenik());
	}

	@Test(expected = Exception.class)
	public final void testObrisiZaposlenikaException() throws Exception {
		IzmjenaZaposlenika.obrisiZaposlenika(99999);
		
	}
	@Test
	public final void testObrisiZaposlenika() throws Exception {
	
		IzmjenaZaposlenika.obrisiZaposlenika(_Zaposlenik.getId());
		
	}
	@Test (expected = Exception.class)
	public final void testValidirajDodavanje() throws Exception{
		assertTrue(IzmjenaZaposlenika.ValidirajDodavanje(t,t1,t2,t3,"sifra"));
		
	}
	@Test(expected = Exception.class)
	public final void testValidirajDodavanjeException() throws Exception{
		IzmjenaZaposlenika.ValidirajDodavanje(t,t1,t2,t3,"");
	}
	//za ponovno setovanje statusa zaposlenika
		@AfterClass
		public static void tearDown()
		{
			
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			try{
			Zaposlenik zaposlenik = (Zaposlenik) session.get(Zaposlenik.class, _Zaposlenik.getId()); 
			zaposlenik.set_status(true);
			session.update(zaposlenik); 
			tx.commit();
			}
			catch(Exception e){
				
			}
			finally{
				
				session.close();
			}
		}

}
