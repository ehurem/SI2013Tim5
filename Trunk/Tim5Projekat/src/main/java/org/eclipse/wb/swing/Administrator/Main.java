package org.eclipse.wb.swing.Administrator;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import java.awt.BorderLayout;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import Models.*;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.PopupMenuListener;
import javax.swing.event.PopupMenuEvent;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import controller.DodavanjeZaposlenika;
import controller.IzmjenaZaposlenika;
import tim5.si.unsa.ba.Tim5Projekat.HibernateUtil;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
	@SuppressWarnings("rawtypes")
	private JComboBox c_privilegije;
	
	private static Long _zaposlenik;
	private static String [] niz = new String[1000];
	private static int [] brojevi = new int [Calendar.getInstance().get(Calendar.WEEK_OF_YEAR)];
	private static Calendar sada;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args, Long zaposlenik) {
		set_zaposlenik(zaposlenik); 
		niz[0] = "";
		//dodavanje sedmica u jlist
	    sada=Calendar.getInstance();
		for (int i=0;i<sada.get(Calendar.WEEK_OF_YEAR);i++){
			brojevi[i]=i+1;
		}
		for(int i = 0; i <brojevi.length/2; i++)
		{
		    int temp = brojevi[i];
		    brojevi[i] = brojevi[brojevi.length - i - 1];
		    brojevi[brojevi.length - i - 1] = temp;
		}
	

		//for (int i = 0; i < get_zaposlenici().size(); i++) niz[i+1] = get_zaposlenici().get(i).get_imeIPrezime();
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
	@SuppressWarnings({ "rawtypes", "unchecked", "serial" })
	private void initialize() {
		frmDodavanjeZaposlenika = new JFrame();
		frmDodavanjeZaposlenika.setResizable(false);
		frmDodavanjeZaposlenika.setTitle("Administratorski panel");
		frmDodavanjeZaposlenika.setBounds(100, 100, 579, 284);
		//frmDodavanjeZaposlenika.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDodavanjeZaposlenika.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frmDodavanjeZaposlenika.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Dodavanje zaposlenika", null, panel, null);
		
		JButton btnUnesi = new JButton("Unesi");
		
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
		t_datumRodjenja.setText("YYYY-MM-DD");
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
							.addGap(18)
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
					.addGap(40))
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
		t_i_DatumRodjenja.setText("YYYY-MM-DD");
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
		
		final JComboBox c_i_Privilegije = new JComboBox();
		c_i_Privilegije.setModel(new DefaultComboBoxModel(new String[] {"Administrator", "Serviser", "Operater"}));
		
		final JComboBox c_i_ImeIPrezime = new JComboBox(niz);
		
		
		c_i_ImeIPrezime.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent arg0) {
			}
			public void popupMenuWillBecomeInvisible(PopupMenuEvent arg0) {
				
				
				
				Zaposlenik s = (Zaposlenik) c_i_ImeIPrezime.getSelectedItem();
				//System.out.println("Tab changed to: " + s.getId());
				
				try {
					
		        	if(s != null)
					{
						t_i_brojTelefona.setText(s.getBrojTelefona());
						t_i_adresaStanovanja.setText(s.getAdresa());
						t_i_email.setText(s.getEmail());
						if(s.get_datumRodjenja() == null){
							t_i_DatumRodjenja.setText("YYYY-MM-DD");
						}
						else{
							t_i_DatumRodjenja.setText(s.get_datumRodjenja().toString());
						}
						t_i_korisnickoIme.setText(s.getKorisnickoIme());
						if(s.getPrivilegija().equals("Administrator")){
							c_i_Privilegije.setSelectedIndex(0);
						}
						else if(s.getPrivilegija().equals("Serviser")){
							c_i_Privilegije.setSelectedIndex(1);
						}
						else{
							c_i_Privilegije.setSelectedIndex(2);
						}
					}
					
					
				
					
				}
				catch (Exception ex) {
					infoBox(ex.getLocalizedMessage(), "Greška");
				}
					
			}
			public void popupMenuWillBecomeVisible(PopupMenuEvent arg0) {
				
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
	
		/*
		btnDeaktivirajRaun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				infoBox("Nije implementirano", null);
			}
		});
		*/
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
		
		
		final DefaultListModel model = new DefaultListModel() {
			String[] values = niz;
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}};
		
		JScrollPane scrollPane_1 = new JScrollPane();

		GroupLayout gl_panel_6 = new GroupLayout(panel_6);
		gl_panel_6.setHorizontalGroup(
			gl_panel_6.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_6.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblIzaberiZaposlenike)
					.addGap(10)
					.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 225, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(10, Short.MAX_VALUE))
		);
		gl_panel_6.setVerticalGroup(
			gl_panel_6.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_6.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_6.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblIzaberiZaposlenike))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		final JList list_zaposlenici = new JList(model);
		scrollPane_1.setViewportView(list_zaposlenici);
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
		final DefaultListModel model2 = new DefaultListModel() {
			int[] values = brojevi;
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}};
		
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setPreferredSize(new Dimension(129, 100));
			
			GroupLayout gl_panel_8 = new GroupLayout(panel_8);
			gl_panel_8.setHorizontalGroup(
				gl_panel_8.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel_8.createSequentialGroup()
						.addContainerGap()
						.addComponent(lblIzaberiSedmicu)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(22, Short.MAX_VALUE))
			);
			gl_panel_8.setVerticalGroup(
				gl_panel_8.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel_8.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_panel_8.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblIzaberiSedmicu)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(111, Short.MAX_VALUE))
			);
			
			final JList sedmice = new JList(model2);
			scrollPane.setViewportView(sedmice);
			
			//promjene na listi sedmica
			sedmice.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			sedmice.setLayoutOrientation(JList.HORIZONTAL_WRAP);
			sedmice.setVisibleRowCount(-1);
			panel_8.setLayout(gl_panel_8);
		
		JButton btnNewButton = new JButton("Izvje\u0161taj o nalozima");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IzvjestajOPoslovanju.main(sedmice.getSelectedIndex());
			}
		});
		
		JButton btnFinansijskiIzvjetaj = new JButton("Finansijski izvje\u0161taj");
		btnFinansijskiIzvjetaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FinansijskiIzvjestaj.main(sedmice.getSelectedIndex());
			}
		});
		
		JButton btnZalbe = new JButton("\u017Dalbe");
		btnZalbe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Zalbe forma = new Zalbe();
				forma.main(null);
			}
		});
		btnUnesi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Long id = DodavanjeZaposlenika.DodajZaposlenik(t_imeIPrezime, t_mjestoStanovanja, t_brojTelefona, t_emailAdresa, t_korisnickaSifra, t_korisnickoIme, c_privilegije, t_datumRodjenja);
					if (id == 0) throw new Exception("Nešto je krenulo po zlu!!!");
					else infoBox("Uspješno dodan novi zaposlenik", "Poruka");
				} catch (Exception e) {
					infoBox(e.getLocalizedMessage(), "Greška");
				}
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

		btnIzmjeni.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				Session session = HibernateUtil.getSessionFactory().openSession();
				boolean provjera=true;
				Zaposlenik novi = new Zaposlenik();
				
				/*Validacija podataka koji se unose*/
				try{
					IzmjenaZaposlenika.ValidirajDodavanje(t_i_adresaStanovanja.getText(), t_i_brojTelefona.getText(), t_i_email.getText(), t_i_korisnickoIme.getText(),t_i_korisnickaSifra.getText());
				}
				catch(Exception b){
					infoBox(b.getLocalizedMessage(),"Greška");
					provjera = false;
				}
	
				
				if(provjera){
				try {
					
					
					Transaction t = session.beginTransaction();
					long EmployeeID = ((Zaposlenik) c_i_ImeIPrezime.getSelectedItem()).getId();
					
					novi = (Zaposlenik)session.get(Zaposlenik.class, EmployeeID); 
					
					/*Provjera da li postoji zaposlenik sa tim korisnickim imenom.*/
					Query query = session.createQuery("from Zaposlenik where korisnickoIme = :ime ");
			        query.setParameter("ime", t_i_korisnickoIme.getText());
			        List<Zaposlenik> list = (List<Zaposlenik>) query.list();
			        
			        /*U slucaju da ne postoji zaposlenik sa tim korisnickim imenom izmjenjuju se podaci u bazi*/
			        if(list.size() == 0){
			        	novi.setAdresa(t_i_adresaStanovanja.getText());
						novi.setBrojTelefona(t_i_brojTelefona.getText());
						novi.setEmail(t_i_email.getText());
							
						
						novi.setKorisnickaSifra(DodavanjeZaposlenika.encryptPassword(t_i_korisnickaSifra.getText()));
						
							
						novi.setKorisnickoIme(t_i_korisnickoIme.getText());
						novi.setPrivilegija(c_i_Privilegije.getSelectedItem().toString());
						java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
						java.util.Date trenutno = sdf.parse(t_i_DatumRodjenja.getText());
						@SuppressWarnings("deprecation")
						Timestamp datum = new Timestamp(trenutno.getYear(), trenutno.getMonth(), trenutno.getDate(), 0,0,0, 0);
						novi.set_datumRodjenja(datum);
						
						
						infoBox("Uspjesno ste izmjenili zaposlenika: " + novi.get_imeIPrezime() + "", "Izmjena uspješna");
						
						
						
			        }
			        else{
			        /*Ako u bazi postoji korisnik sa tim imenom, provjerava se da li je to isti koji se mjenja ili neki drugi.*/
			        if((list.get(0).getId() == novi.getId())  || ((list.get(0).getKorisnickoIme() == t_i_korisnickoIme.getText()) && (list.get(0).getId() == novi.getId()))){
			        
			        novi.setAdresa(t_i_adresaStanovanja.getText());
					novi.setBrojTelefona(t_i_brojTelefona.getText());
					novi.setEmail(t_i_email.getText());
						
					if(!t_i_korisnickaSifra.getText().equals("")){
						novi.setKorisnickaSifra(DodavanjeZaposlenika.encryptPassword(t_i_korisnickaSifra.getText()));
					}
						
					novi.setKorisnickoIme(t_i_korisnickoIme.getText());
					novi.setPrivilegija(c_i_Privilegije.getSelectedItem().toString());
					
					java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
					java.util.Date trenutno = sdf.parse(t_i_DatumRodjenja.getText());
					@SuppressWarnings("deprecation")
					Timestamp datum = new Timestamp(trenutno.getYear(), trenutno.getMonth(), trenutno.getDate(), 0,0,0, 0);
					novi.set_datumRodjenja(datum);
					
					
					infoBox("Uspjesno ste izmjenili zaposlenika: " + novi.get_imeIPrezime() + "", "Izmjena uspješna");
					IzmjenaZaposlenika.resetujPolja(t_i_DatumRodjenja, t_i_adresaStanovanja, t_i_brojTelefona, t_emailAdresa, 
							t_i_korisnickoIme, t_i_korisnickaSifra);
					
			        }
			        else{
			        	
			        	infoBox("Korisnicko ime je zauzeto!", "Greška");
			        }
			        }
				}
				catch (Exception ex) {
					
					if(ex.toString().equals("java.text.ParseException: Unparseable date: \"YYYY-MM-DD\"")){
						infoBox("Nepravilno ste unijeli datum. \nUnesite datum u formatu yyyy-mm-dd.", "UZBUNA");
					}
					else{
					infoBox(ex.getLocalizedMessage(), "Greška");
				}}
				finally {
						/*Nakon prikuljanja podataka iz baze spremno je sve za izmjenu podataka.*/
			         session.close();
			         try{
			         IzmjenaZaposlenika.izmjeni(novi);
			         }
			         catch(Exception ex){
			        	 infoBox("Dogodila se greska prilikom izmjene zaposlenika.", "Greška");
			         }
			      }
			}}
		});
		panel_7.setLayout(gl_panel_7);
		panel_2.setLayout(gl_panel_2);
		c_i_ImeIPrezime.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		ChangeListener changeListener = new ChangeListener() {
		      public void stateChanged(ChangeEvent changeEvent) {
		    	JTabbedPane sourceTabbedPane = (JTabbedPane) changeEvent.getSource();
		        int index = sourceTabbedPane.getSelectedIndex();
		       
		          
		        if(sourceTabbedPane.getTitleAt(index).equals("Izmjena zaposlenika")){
		    	c_i_ImeIPrezime.removeAllItems();
		    	
		    	try {
		        	int itemCount = c_i_ImeIPrezime.getItemCount();

		            for(int i=0;i<itemCount;i++){
		            	c_i_ImeIPrezime.removeItemAt(0);
		             }
					Session session = HibernateUtil.getSessionFactory().openSession();
					Transaction t = session.beginTransaction();
					
					Query queryZaposlenik = session.createQuery("from Zaposlenik");
					List listZaposlenik = queryZaposlenik.list();
					
			
					
					for(int i=0;i<listZaposlenik.size();i++){
						c_i_ImeIPrezime.addItem(listZaposlenik.get(i));;
					}
					
				
					
					t.commit();
					
					session.close();
					
				}
				catch (Exception ex) {
					infoBox(ex.getLocalizedMessage(), "Greška");
				}
		        
		        
		     
		        
		      }
		      else if(sourceTabbedPane.getTitleAt(index).equals("Deaktivacija računa")){
		    	  /*Kada se otvori tab za deaktivaciju racuna ucitaju se iz baze svi aktivni zaposlenici BEGIN*/
		    	  try {
			        	Session session = HibernateUtil.getSessionFactory().openSession();
						Transaction t = session.beginTransaction();
						
						Query queryZaposlenik = session.createQuery("from Zaposlenik");
						List<Zaposlenik> listZaposlenik =(List<Zaposlenik>) queryZaposlenik.list();
						String[] elements = new String[listZaposlenik.size()];
						
						
						for(int i=0;i<listZaposlenik.size();i++){
							if(listZaposlenik.get(i).get_status()){
								model.addElement(listZaposlenik.get(i));
								elements[i] = listZaposlenik.get(i).toString();
								}
						}
						list_zaposlenici.setListData(elements);
						
						t.commit();
						session.close();
						
					}
				catch (Exception ex) {
						infoBox(ex.getLocalizedMessage(), "Greška");
					}
			        
			        
			     
		    	  
		      }
		      /*Kada se otvori tab za deaktivaciju racuna ucitaju se iz baze svi aktivni zaposlenici END*/
		      
		      }
		 
		    };
		tabbedPane.addChangeListener(changeListener);
		btnDeaktivirajRaun.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				  Session session = HibernateUtil.getSessionFactory().openSession();
			      Transaction tx = null;
			      /*Dobavljanje id-a zaposlenika koji se treba brisati*/
			      long EmployeeID = ((Zaposlenik)model.get(list_zaposlenici.getSelectedIndex())).getId();
			      
			      try{
			    	/*Brisanje zaposlenika iz baze*/
			        tx = session.beginTransaction();
			        IzmjenaZaposlenika.obrisiZaposlenika(EmployeeID);
			         
			        /*Obnavljanje liste zaposlenika*/
			     	Query queryZaposlenik = session.createQuery("from Zaposlenik");
					List<Zaposlenik> listZaposlenik =(List<Zaposlenik>) queryZaposlenik.list();
					
					String[] elements = new String[listZaposlenik.size()];
					
					for(int i=0;i<listZaposlenik.size();i++){
						if(listZaposlenik.get(i).get_status()){
						model.addElement(listZaposlenik.get(i));
						elements[i] = listZaposlenik.get(i).toString();
						}
						
					}
					
					list_zaposlenici.setListData(elements);
					tx.commit();
			         
			        infoBox("Uspjesno ste deaktivirali racun", "Deaktivacija uspješna");
			      }
			      catch (Exception ex) {
						infoBox(ex.getLocalizedMessage(), "Greška");
			      }
			      finally {
			         session.close(); 
			      }
				
				
			}
		});
		
	}
	public static Long get_zaposlenik() {
		return _zaposlenik;
	}
	public static void set_zaposlenik(Long _zaposlenik) {
		Main._zaposlenik = _zaposlenik;
	}

}
