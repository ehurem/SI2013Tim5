package org.eclipse.wb.swing.Administrator;

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import Models.*;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

import javax.swing.event.PopupMenuListener;
import javax.swing.event.PopupMenuEvent;

public class Main {

	private JFrame frmDodavanjeZaposlenika;
	private JTextField t_imeIPrezime;
	private JTextField t_datumRodjenja;
	private JTextField t_mjestoStanovanja;
	private JTextField t_brojTelefona;
	private JTextField t_emailAdresa;
	private JTextField t_korisnickoIme;
	private JTextField t_korisnickaSifra;
	private JTextField t_i_DatumRodjenja;
	private JTextField t_i_adresaStanovanja;
	private JTextField t_i_brojTelefona;
	private JTextField t_i_email;
	private JTextField t_i_korisnickoIme;
	private JTextField t_i_korisnickaSifra;
	private JComboBox c_privilegije;
	
	private static ArrayList<Zaposlenik> _zaposlenici;
	private static String [] niz = new String[1000];
	private static ArrayList<Zalba> _listaZalbi;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args, ArrayList<Zaposlenik> zaposlenik) {
		set_zaposlenici(zaposlenik);
		niz[0] = "";
		for (int i = 0; i < get_zaposlenici().size(); i++) niz[i+1] = get_zaposlenici().get(i).get_imeIPrezime();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frmDodavanjeZaposlenika.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}
	
	public static void infoBox(String infoMessage, String naslov)
    {
        JOptionPane.showMessageDialog(null, infoMessage, "" + naslov, JOptionPane.INFORMATION_MESSAGE);
    }
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmDodavanjeZaposlenika = new JFrame();
		frmDodavanjeZaposlenika.setTitle("Administratorski panel");
		frmDodavanjeZaposlenika.setBounds(100, 100, 579, 284);
		//frmDodavanjeZaposlenika.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDodavanjeZaposlenika.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frmDodavanjeZaposlenika.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Dodavanje zaposlenika", null, panel, null);
		
		
		
		JButton btnUnesi = new JButton("Unesi");
		btnUnesi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Zaposlenik novi = new Zaposlenik();
					novi.setAdresa(t_mjestoStanovanja.getText());
					novi.setBrojTelefona(t_brojTelefona.getText());
					novi.setEmail(t_emailAdresa.getText());
					novi.set_imeIPrezime(t_imeIPrezime.getText());
					novi.setKorisnickaSifra(t_korisnickaSifra.getText());
					novi.setKorisnickoIme(t_korisnickoIme.getText());
					novi.setPrivilegija(c_privilegije.getSelectedItem().toString());
					get_zaposlenici().add(novi);
					
					infoBox("Uspje�no dodan novi zaposlenik", get_zaposlenici().size() + "");
				}
				catch (Exception ex) {
					infoBox(ex.toString(), "UZBUNA");
				}
			}
		});
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnUnesi)
						.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, 523, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(25, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnUnesi)
					.addGap(176))
		);
		
		JLabel lblNewLabel = new JLabel("Ime i prezime:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblAdresaStanovanja = new JLabel("Adresa stanovanja:");
		lblAdresaStanovanja.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblDatumRoenja = new JLabel("Datum ro\u0111enja:");
		lblDatumRoenja.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblBrojTelefona = new JLabel("Broj telefona:");
		lblBrojTelefona.setHorizontalAlignment(SwingConstants.RIGHT);
		
		t_imeIPrezime = new JTextField();
		t_imeIPrezime.setColumns(10);
		
		t_datumRodjenja = new JTextField();
		t_datumRodjenja.setColumns(10);
		
		t_mjestoStanovanja = new JTextField();
		t_mjestoStanovanja.setColumns(10);
		
		t_brojTelefona = new JTextField();
		t_brojTelefona.setColumns(10);
		
		JLabel label_8 = new JLabel("Korisni\u010Dka \u0161ifra:");
		label_8.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel label_9 = new JLabel("Privilegije:");
		label_9.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel label_10 = new JLabel("E-mail adresa:");
		label_10.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel label_11 = new JLabel("Korisni\u010Dko ime:");
		label_11.setHorizontalAlignment(SwingConstants.RIGHT);
		
		t_emailAdresa = new JTextField();
		t_emailAdresa.setColumns(10);
		
		t_korisnickoIme = new JTextField();
		t_korisnickoIme.setColumns(10);
		
		t_korisnickaSifra = new JTextField();
		t_korisnickaSifra.setColumns(10);
		
		
		c_privilegije = new JComboBox();
		c_privilegije.setModel(new DefaultComboBoxModel(new String[] {"Administrator", "Serviser", "Operater"}));
		GroupLayout gl_panel_4 = new GroupLayout(panel_4);
		gl_panel_4.setHorizontalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_4.createSequentialGroup()
							.addGap(20)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(t_imeIPrezime, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(label_10, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(t_emailAdresa, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_4.createSequentialGroup()
							.addGap(10)
							.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_4.createSequentialGroup()
									.addGap(10)
									.addComponent(lblDatumRoenja, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblAdresaStanovanja, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel_4.createSequentialGroup()
									.addGap(10)
									.addComponent(lblBrojTelefona, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)))
							.addGap(10)
							.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
								.addComponent(t_datumRodjenja, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
								.addComponent(t_mjestoStanovanja, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
								.addComponent(t_brojTelefona, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
								.addComponent(label_11, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel_4.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(label_9, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(label_8, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_4.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(t_korisnickaSifra, Alignment.LEADING)
									.addComponent(t_korisnickoIme, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE))
								.addComponent(c_privilegije, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE))))
					.addGap(186))
		);
		gl_panel_4.setVerticalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addGap(13)
					.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_4.createSequentialGroup()
							.addGap(3)
							.addComponent(lblNewLabel))
						.addGroup(gl_panel_4.createParallelGroup(Alignment.BASELINE)
							.addComponent(t_imeIPrezime, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(t_emailAdresa, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(label_10)))
					.addGap(11)
					.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_4.createSequentialGroup()
							.addComponent(t_korisnickoIme, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(11)
							.addComponent(t_korisnickaSifra, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(11)
							.addComponent(c_privilegije, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_4.createSequentialGroup()
							.addGap(3)
							.addComponent(lblDatumRoenja)
							.addGap(17)
							.addComponent(lblAdresaStanovanja)
							.addGap(17)
							.addComponent(lblBrojTelefona))
						.addGroup(gl_panel_4.createSequentialGroup()
							.addGroup(gl_panel_4.createParallelGroup(Alignment.BASELINE)
								.addComponent(t_datumRodjenja, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_11))
							.addGap(11)
							.addGroup(gl_panel_4.createParallelGroup(Alignment.BASELINE)
								.addComponent(t_mjestoStanovanja, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_8))
							.addGap(11)
							.addGroup(gl_panel_4.createParallelGroup(Alignment.BASELINE)
								.addComponent(t_brojTelefona, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_9)))))
		);
		panel_4.setLayout(gl_panel_4);
		panel.setLayout(gl_panel);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Izmjena zaposlenika", null, panel_1, null);
		
		JButton btnIzmjeni = new JButton("Sa\u010Duvaj promjene");
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		JLabel label_12 = new JLabel("Broj telefona:");
		label_12.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel label_13 = new JLabel("Adresa stanovanja:");
		label_13.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel label_14 = new JLabel("Ime i prezime:");
		label_14.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel label_15 = new JLabel("Datum ro\u0111enja:");
		label_15.setHorizontalAlignment(SwingConstants.RIGHT);
		
		t_i_DatumRodjenja = new JTextField();
		t_i_DatumRodjenja.setText("nije implementirano");
		t_i_DatumRodjenja.setColumns(10);
		
		t_i_adresaStanovanja = new JTextField();
		t_i_adresaStanovanja.setColumns(10);
		
		t_i_brojTelefona = new JTextField();
		t_i_brojTelefona.setColumns(50);
		
		JLabel label_16 = new JLabel("Korisni\u010Dka \u0161ifra:");
		label_16.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel label_17 = new JLabel("Privilegije:");
		label_17.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel label_18 = new JLabel("E-mail adresa:");
		label_18.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel label_19 = new JLabel("Korisni\u010Dko ime:");
		label_19.setHorizontalAlignment(SwingConstants.RIGHT);
		
		t_i_email = new JTextField();
		t_i_email.setColumns(10);
		
		t_i_korisnickoIme = new JTextField();
		t_i_korisnickoIme.setColumns(10);
		
		t_i_korisnickaSifra = new JTextField();
		t_i_korisnickaSifra.setColumns(10);
		
		JComboBox c_i_Privilegije = new JComboBox();
		c_i_Privilegije.setModel(new DefaultComboBoxModel(new String[] {"Administrator", "org.eclipse.wb.swing.Serviser", "org.eclipse.wb.swing.Operater"}));
		
		final JComboBox c_i_ImeIPrezime = new JComboBox(niz);
		c_i_ImeIPrezime.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent arg0) {
			}
			public void popupMenuWillBecomeInvisible(PopupMenuEvent arg0) {
				String selektovan = c_i_ImeIPrezime.getSelectedItem().toString();
				int index = -1;
				for (int i = 0; i<get_zaposlenici().size(); i++) 
					if (selektovan.equals(get_zaposlenici().get(i).get_imeIPrezime())) {
						index = i;
					}
				if (index == -1) return;
				//infoBox(index+ "", null);
				t_i_brojTelefona.setText(get_zaposlenici().get(index).getBrojTelefona());
				t_i_adresaStanovanja.setText(get_zaposlenici().get(index).getAdresa());
				t_i_email.setText(get_zaposlenici().get(index).getEmail());
				t_i_korisnickaSifra.setText(get_zaposlenici().get(index).getKorisnickaSifra());
				t_i_korisnickoIme.setText(get_zaposlenici().get(index).getKorisnickoIme());
				
				
			}
			public void popupMenuWillBecomeVisible(PopupMenuEvent arg0) {
				//c_i_ImeIPrezime.add(new Compon);
			}
		});

		GroupLayout gl_panel_5 = new GroupLayout(panel_5);
		gl_panel_5.setHorizontalGroup(
			gl_panel_5.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_5.createSequentialGroup()
					.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_5.createSequentialGroup()
							.addGap(10)
							.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_5.createSequentialGroup()
									.addGap(10)
									.addComponent(label_15, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE))
								.addComponent(label_13, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel_5.createSequentialGroup()
									.addGap(10)
									.addComponent(label_12, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)))
							.addGap(10)
							.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
								.addComponent(t_i_DatumRodjenja, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
								.addComponent(t_i_adresaStanovanja, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
								.addComponent(t_i_brojTelefona, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)))
						.addGroup(Alignment.TRAILING, gl_panel_5.createSequentialGroup()
							.addContainerGap()
							.addComponent(label_14, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(c_i_ImeIPrezime, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_5.createSequentialGroup()
							.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
								.addComponent(label_19, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel_5.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(label_17, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(label_16, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_5.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(t_i_korisnickaSifra, Alignment.LEADING)
									.addComponent(t_i_korisnickoIme, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE))
								.addComponent(c_i_Privilegije, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel_5.createSequentialGroup()
							.addComponent(label_18, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(t_i_email, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)))
					.addGap(186))
		);
		gl_panel_5.setVerticalGroup(
			gl_panel_5.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_5.createSequentialGroup()
					.addGap(16)
					.addGroup(gl_panel_5.createParallelGroup(Alignment.BASELINE)
						.addComponent(t_i_email, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_18)
						.addComponent(c_i_ImeIPrezime, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_14))
					.addGap(11)
					.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_5.createSequentialGroup()
							.addComponent(t_i_korisnickoIme, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(11)
							.addComponent(t_i_korisnickaSifra, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(11)
							.addComponent(c_i_Privilegije, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_5.createSequentialGroup()
							.addGap(3)
							.addComponent(label_15)
							.addGap(17)
							.addComponent(label_13)
							.addGap(17)
							.addComponent(label_12))
						.addGroup(gl_panel_5.createSequentialGroup()
							.addGroup(gl_panel_5.createParallelGroup(Alignment.BASELINE)
								.addComponent(t_i_DatumRodjenja, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_19))
							.addGap(11)
							.addGroup(gl_panel_5.createParallelGroup(Alignment.BASELINE)
								.addComponent(t_i_adresaStanovanja, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_16))
							.addGap(11)
							.addGroup(gl_panel_5.createParallelGroup(Alignment.BASELINE)
								.addComponent(t_i_brojTelefona, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_17)))))
		);
		panel_5.setLayout(gl_panel_5);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel_5, GroupLayout.PREFERRED_SIZE, 523, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnIzmjeni))
					.addContainerGap(25, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_5, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnIzmjeni)
					.addContainerGap(17, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Deaktivacija ra\u010Duna", null, panel_3, null);
		
		JButton btnDeaktivirajRaun = new JButton("Deaktiviraj ra\u010Dun");
		btnDeaktivirajRaun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				infoBox("Nije implementirano", null);
			}
		});
		
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addGap(84)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnDeaktivirajRaun)
						.addComponent(panel_6, GroupLayout.PREFERRED_SIZE, 354, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(120, Short.MAX_VALUE))
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_6, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnDeaktivirajRaun)
					.addContainerGap(30, Short.MAX_VALUE))
		);
		
		JLabel lblIzaberiZaposlenike = new JLabel("Izaberi zaposlenike:");
		
		
		JList list_zaposlenici = new JList(niz);
		list_zaposlenici.setModel(new AbstractListModel() {
			String[] values = niz;
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		GroupLayout gl_panel_6 = new GroupLayout(panel_6);
		gl_panel_6.setHorizontalGroup(
			gl_panel_6.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_6.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblIzaberiZaposlenike)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(list_zaposlenici, GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel_6.setVerticalGroup(
			gl_panel_6.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_6.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_6.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblIzaberiZaposlenike)
						.addComponent(list_zaposlenici, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(133, Short.MAX_VALUE))
		);
		panel_6.setLayout(gl_panel_6);
		panel_3.setLayout(gl_panel_3);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Pregled izvje\u0161taja", null, panel_2, null);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		JPanel panel_8 = new JPanel();
		panel_8.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(21)
					.addComponent(panel_8, GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(panel_7, GroupLayout.PREFERRED_SIZE, 209, GroupLayout.PREFERRED_SIZE)
					.addGap(67))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(33)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel_8, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
						.addComponent(panel_7, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(58, Short.MAX_VALUE))
		);
		
		JLabel lblIzaberiSedmicu = new JLabel("Izaberi sedmicu:");
		
		JList list_1 = new JList();
		GroupLayout gl_panel_8 = new GroupLayout(panel_8);
		gl_panel_8.setHorizontalGroup(
			gl_panel_8.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_8.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblIzaberiSedmicu)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(list_1, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_panel_8.setVerticalGroup(
			gl_panel_8.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_8.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_8.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblIzaberiSedmicu)
						.addComponent(list_1, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(95, Short.MAX_VALUE))
		);
		panel_8.setLayout(gl_panel_8);
		
		JButton btnNewButton = new JButton("Izvje\u0161taj o nalozima");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IzvjestajOPoslovanju.main(null);
			}
		});
		
		JButton btnFinansijskiIzvjetaj = new JButton("Finansijski izvje\u0161taj");
		btnFinansijskiIzvjetaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FinansijskiIzvjestaj.main(null);
			}
		});
		
		JButton btnZalbe = new JButton("\u017Dalbe");
		btnZalbe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				Zalbe.main(null, get_listaZalbi(), get_zaposlenici());
			}
		});
		GroupLayout gl_panel_7 = new GroupLayout(panel_7);
		gl_panel_7.setHorizontalGroup(
			gl_panel_7.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_7.createSequentialGroup()
					.addGap(41)
					.addGroup(gl_panel_7.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(btnZalbe, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnFinansijskiIzvjetaj, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnNewButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addContainerGap(37, Short.MAX_VALUE))
		);
		gl_panel_7.setVerticalGroup(
			gl_panel_7.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_7.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnNewButton)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnFinansijskiIzvjetaj)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnZalbe)
					.addContainerGap(20, Short.MAX_VALUE))
		);
		panel_7.setLayout(gl_panel_7);
		panel_2.setLayout(gl_panel_2);
	}

	private static ArrayList<Zaposlenik> get_zaposlenici() {
		return _zaposlenici;
	}

	private static void set_zaposlenici(ArrayList<Zaposlenik> _zaposlenici) {
		Main._zaposlenici = _zaposlenici;
	}

	public static ArrayList<Zalba> get_listaZalbi() {
		return _listaZalbi;
	}

	public static void set_listaZalbi(ArrayList<Zalba> _listaZalbi) {
		Main._listaZalbi = _listaZalbi;
	}
}
