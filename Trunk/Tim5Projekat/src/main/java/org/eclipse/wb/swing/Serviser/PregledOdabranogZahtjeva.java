
package org.eclipse.wb.swing.Serviser;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;
import javax.swing.JButton;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import tim5.si.unsa.ba.Tim5Projekat.HibernateUtil;
import Models.Klijent;
import Models.Zahtjev;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;

public class PregledOdabranogZahtjeva {

	private JFrame frmPregledZahtjeva;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private static String imeiprezime;
	private static String adresa;
	private static String tipuredaja;
	private static String komentar;
	private static Klijent klijent;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args, Zahtjev z) {
		 
		   try {
			   	Session session = HibernateUtil.getSessionFactory().openSession();
				Transaction tr = session.beginTransaction();
				Query queryKlijent= session.createQuery("from Klijent where id='"+z.getKlijent()+"'");
				klijent=(Klijent) queryKlijent.uniqueResult();
				imeiprezime=klijent.get_imeIPrezime();
				adresa=klijent.get_adresa();
				tr.commit();
				session.close();
			}
			catch (Exception ex) {
				JOptionPane.showMessageDialog(null, ex.toString());
			}
	
		  
		   tipuredaja=(z.getTipUredaja());
		   komentar=(z.getKomentar());
		   
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PregledOdabranogZahtjeva window = new PregledOdabranogZahtjeva();
					window.frmPregledZahtjeva.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
		  
	}

	/**
	 * Create the application.
	 */
	public PregledOdabranogZahtjeva() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPregledZahtjeva = new JFrame();
		frmPregledZahtjeva.setResizable(false);
		frmPregledZahtjeva.setTitle("Pregled zahtjeva");
		frmPregledZahtjeva.setBounds(100, 100, 329, 311);
		//frmPregledZahtjeva.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	
		JButton btnZatvori = new JButton("Zatvori");
		btnZatvori.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmPregledZahtjeva.dispose();
			}
		});
		GroupLayout groupLayout = new GroupLayout(frmPregledZahtjeva.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnZatvori, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnZatvori)
					.addContainerGap(12, Short.MAX_VALUE))
		);
		
		JLabel lblImeIPrezime = new JLabel("Ime i prezime klijenta:");
		lblImeIPrezime.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblAdresaStanovanja = new JLabel("Adresa stanovanja:");
		lblAdresaStanovanja.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblTipUreaja = new JLabel("Tip ure\u0111aja:");
		lblTipUreaja.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblDodatniKomentar = new JLabel("Dodatni komentar:");
		lblDodatniKomentar.setHorizontalAlignment(SwingConstants.RIGHT);

		textField = new JTextField(imeiprezime);
		textField.setEditable(false);
		textField.setColumns(10);
		
		textField_1 = new JTextField(adresa);
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField(tipuredaja);
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		JScrollPane scrollPane = new JScrollPane();

		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setLineWrap(true);
		textArea.setText(komentar);
		textArea.setEditable(false);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblDodatniKomentar, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblTipUreaja, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblAdresaStanovanja, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblImeIPrezime, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
							.addComponent(textField_2)
							.addComponent(textField_1)
							.addComponent(textField, GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE))
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(41, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblImeIPrezime)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAdresaStanovanja)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTipUreaja)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDodatniKomentar)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(83, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		frmPregledZahtjeva.getContentPane().setLayout(groupLayout);
	}



}
