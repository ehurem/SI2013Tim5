package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.eclipse.wb.swing.Serviser.serviser;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import tim5.si.unsa.ba.Tim5Projekat.HibernateUtil;
import Models.Klijent;
import Models.Zahtjev;

public class ZahtjevController {

	public ZahtjevController() {
	}

	public ArrayList<String> PopuniPodatke(JTextField textField, JTextField textField_1,
			JTextField textField_2, JTextField textField_3, JTextArea textArea,
			String zahtjev_id, JRadioButton rdbtnDa, JRadioButton rdbtnNe)
			throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		ArrayList<String> listRez = new ArrayList<String>();
		try {
			boolean garancija;
			String idZahtjeva = zahtjev_id;

			Query queryZahtjev;
			Query queryZahtjevid;
			Query queryZahtjevklijent;
			List<Zahtjev> listZahtjev;
			

			if (idZahtjeva != null) {
				queryZahtjevid = session.createQuery("from Zahtjev where id='"
						+ idZahtjeva + "'");
				listZahtjev = (List<Zahtjev>) queryZahtjevid.list();
			} else {
				queryZahtjev = session
						.createQuery("from Zahtjev where _status='U izvrsavanju'");
				listZahtjev = (List<Zahtjev>) queryZahtjev.list();
			}

			String s1 = String.valueOf(listZahtjev.get(0).getKlijent());

			queryZahtjevklijent = session.createQuery("from Klijent where id='"
					+ s1 + "'");
			List<Klijent> k = queryZahtjevklijent.list();

			String s5 = k.get(0).get_imeIPrezime();

			String s = String.valueOf(listZahtjev.get(0).getID());
			// String s1 = String.valueOf(listZahtjev.get(0).getKlijent());
			String s2 = String.valueOf(listZahtjev.get(0).getTipUredaja());
			String s3 = String.valueOf(listZahtjev.get(0).get_cijena());
			String s4 = String.valueOf(listZahtjev.get(0).getKomentar());

			garancija = listZahtjev.get(0).getGarancija();

			rdbtnDa = new JRadioButton("Da");
			rdbtnNe = new JRadioButton("Ne");

			rdbtnDa.setEnabled(false);
			rdbtnNe.setEnabled(false);

			// rdbtnDa.setFocusable(false);

			rdbtnDa.setSelected(garancija);
			rdbtnNe.setSelected(!garancija);

			textField.setText(s);
			textField_1.setText(s5);
			textField_2.setText(s2);
			textField_3.setText(s3);
			textArea.setText(s4);
			
			listRez.add(s);
			listRez.add(s5);
			listRez.add(s2);
			listRez.add(s3);
			listRez.add(s4);
			listRez.add(String.valueOf(garancija));
			
			

			t.commit();
			
			
		} catch (Exception ex) {
			throw ex;
		} finally {
			session.close();
		}
		
		return listRez;
	}

	private static Boolean validirajPrazno(JTextField t1) {
		if (t1.getText().equals("")) {
			t1.setBackground(new Color(216, 210, 139));
			return false;
		} else {
			t1.setBackground(new Color(255, 255, 255));
		}
		return true;
	}

	private static Boolean validirajPrazno(JTextArea t1) {
		if (t1.getText().equals("")) {
			t1.setBackground(new Color(216, 210, 139));
			return false;
		} else {
			t1.setBackground(new Color(255, 255, 255));
		}
		return true;
	}

	public boolean UpisivanjeZatvorenogZahtjeva(String zahtjev_id, String cijena, String komentar, long zaposlenik_id)
			throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		boolean status = false;
		try {
			Query queryZahtjevid;
			List<Zahtjev> listZahtjev;
			queryZahtjevid = session.createQuery("from Zahtjev where id='"
					+ zahtjev_id + "'");
			listZahtjev = (List<Zahtjev>) queryZahtjevid.list();
			Zahtjev z = listZahtjev.get(0);
			z.setStatus("Zatvoren");

			Double cij = Double.parseDouble(cijena);
			z.set_cijena(cij);
			z.setKomentar(komentar);
			z.setZaposlenik(zaposlenik_id);

			Date d = new Date();
			java.sql.Date dat = new java.sql.Date(d.getTime());
			z.setDatumZatvaranja(dat);

			t.commit();

			
			status = true;
		} catch (Exception ex) {
			 status = false;
			throw ex;
		} finally {
			session.close();
		}
		return status;
	}

	public DefaultListModel PopunjavanjeListePreuzetihZahtjeva() throws Exception {
		final JList list = new JList();
		final DefaultListModel listModel = new DefaultListModel();

		listModel.clear();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();

		try {
			Query queryZahtjev = session
					.createQuery("from Zahtjev where _status='U izvrsavanju'");
			List listZahtjev = queryZahtjev.list();

			for (int i = 0; i < listZahtjev.size(); i++) {
				listModel.addElement(((Zahtjev) listZahtjev.get(i)).getID());
			}

			list.setModel(listModel);
			// Zahtjev z = (Zahtjev) list.getSelectedValue();
			// z.getID();
			t.commit();
		} catch (Exception e) {
			//
			throw e;
		} finally {
			session.close();
		}
		return listModel;

	}
}
