package controller;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import tim5.si.unsa.ba.Tim5Projekat.HibernateUtil;
import Models.Zahtjev;

public class serviserKontroler {
	
	
	public static List <Zahtjev> ucitajOtvoreneZahtjeve () {
		List <Zahtjev> zahtjevi = null;
		Session sesija = HibernateUtil.getSessionFactory().openSession();
		   try {
				Transaction tr = sesija.beginTransaction();
				Query queryZahtjev = sesija.createQuery("from Zahtjev where _status='otvoren'");
				zahtjevi = queryZahtjev.list();
				tr.commit();
		   }
		   catch (Exception ex) { 
			   JOptionPane.showMessageDialog(null, ex.toString());
		   }
		   finally { 
			   sesija.close();
			   }
		return zahtjevi;
	}
	public static boolean popuniTabelu (JTable tabela, List <Zahtjev> zahtjevi) {
		for(int i=0;i<zahtjevi.size();i++){
			((DefaultTableModel) tabela.getModel()).addRow(new Object[] {( zahtjevi.get(i)).getID(), ( zahtjevi.get(i)).getPrioritet()});
	}
		return tabela.getRowCount()==zahtjevi.size();
	}
	public static void uzmiZahtjevNaIzvrsavanje (JTable tabela, List <Zahtjev> zahtjevi, long zaposlenik, int red) {
		zahtjevi.get(red).setStatus("u izvrsavanju");
		zahtjevi.get(red).setZaposlenik(zaposlenik);
		((DefaultTableModel)tabela.getModel()).removeRow(red);
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			Transaction tr = session.beginTransaction();	
			session.update(zahtjevi.get(red));
			tr.commit();
		}
		catch (Exception ex) { 
			JOptionPane.showMessageDialog(tabela, ex.toString());
	   }
		finally { 
			session.close();
		}
		zahtjevi.remove(red);
	}
}	
