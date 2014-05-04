package org.eclipse.wb.swing.Operater;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.ButtonGroup;
import javax.swing.ComboBoxEditor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import Models.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.sql.Date;
import java.util.Enumeration;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class MainOperater {

	private JFrame frmInterfejsZaOperatera;
	private JTextField textField_1;
	private JTextField textField_2;
	private static ArrayList<Klijent>_klijenti;
	private static ArrayList<Zahtjev>_zahtjevi;
	//the client we make if there is no one in the database already
	private static Klijent _novi;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					set_klijenti(new ArrayList<Klijent>());
					set_zahtjevi(new ArrayList<Zahtjev>());
					set_novi(new Klijent());

					//an example of a client that we input into list
					Klijent primjer = new Klijent();
					primjer.set_imeIPrezime("Mujo Mujic");
					primjer.set_adresa("Skenderija");
					primjer.setBrojTelefona("012345678");
					primjer.setEmail("mujom@gmail.com");
					
					get_klijenti().add(primjer);
					
					MainOperater window = new MainOperater();
					window.frmInterfejsZaOperatera.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainOperater() {
		initialize();
	}

	/**
	 * Message Box form.
	 */
	public static void infoBox(String infoMessage, String naslov)
    {
        JOptionPane.showMessageDialog(null, infoMessage, "" + naslov, JOptionPane.INFORMATION_MESSAGE);
    }
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frmInterfejsZaOperatera = new JFrame();
		frmInterfejsZaOperatera.setTitle("Interfejs za operatera");
		frmInterfejsZaOperatera.setBounds(100, 100, 377, 433);
		//frmInterfejsZaOperatera.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GroupLayout groupLayout = new GroupLayout(frmInterfejsZaOperatera.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)
		);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Unos zahtjeva", null, panel, null);
		
		final JComboBox comboBox_1 = new JComboBox();
		for (Klijent k : _klijenti) {
			comboBox_1.addItem(k);
		}
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		JButton btnNoviKlijent = new JButton("Novi klijent");
		
		btnNoviKlijent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DodajKlijenta zaDodavanje = new DodajKlijenta();
				zaDodavanje.main(null, get_klijenti(), get_novi());
			}
		});
		
		JLabel label = new JLabel("ID zahtjeva:");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel label_1 = new JLabel("Ime i prezime klijenta:");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		JLabel label_2 = new JLabel("Komentar:");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel label_3 = new JLabel("Tip ure\u0111aja:");
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel label_4 = new JLabel("Garancija:");
		label_4.setHorizontalAlignment(SwingConstants.RIGHT);
		
		final JTextArea textArea = new JTextArea();
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		final JRadioButton radioButton = new JRadioButton("Da");
		
		JRadioButton radioButton_1 = new JRadioButton("Ne");
		
		//we need to group JRadioButtons in order to get only one of them selected at the time
		final ButtonGroup grupaGarancija = new ButtonGroup();
		grupaGarancija.add(radioButton);
		grupaGarancija.add(radioButton_1);
		
		JButton button = new JButton("Unesi");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					Zahtjev noviZahtjev = new Zahtjev();
					noviZahtjev.setID(Long.parseLong(textField_1.getText()));
					
					//try{String imeIzabranog = Object.toString(comboBox_1.getSelectedItem());
					/*for(Klijent k : _klijenti){
						if(k.get_imeIPrezime() == imeIzabranog){
							noviZahtjev.setKlijent(k);
							break;
						}
					}}
					catch(Exception ex) {infoBox(ex.getMessage(), "greska kod konverzije");}
					*/
					
					noviZahtjev.setKlijent((Klijent)comboBox_1.getSelectedItem());
					noviZahtjev.setTipUredaja(textField_2.getText());

					Enumeration elementiGrupe = grupaGarancija.getElements();
					while(elementiGrupe.hasMoreElements()){
						JRadioButton dugme = (JRadioButton)elementiGrupe.nextElement();
						if(dugme.isSelected()){
							if(dugme == radioButton) noviZahtjev.setGarancija(true);
							else noviZahtjev.setGarancija(false);
							break;
						}
					}
					
					noviZahtjev.setKomentar(textArea.getText());
					
					java.util.Date today = new java.util.Date();
					java.sql.Date datumO = new java.sql.Date(today.getTime());
					
					noviZahtjev.setDatumOtvaranja(datumO);
					
					//infoBox(Integer.toString(noviZahtjev.getDatumOtvaranja().getYear()), "Datum otvaranja");
					
					noviZahtjev.setStatus("otvoren");
					
					get_zahtjevi().add(noviZahtjev);
					
				}
				catch(Exception izuzetak)
				{
					infoBox(izuzetak.getMessage(), "Greska u unosu !");
				}
			}
		});
		
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addGap(15)
					.addComponent(radioButton)
					.addGap(36)
					.addComponent(radioButton_1)
					.addContainerGap(50, Short.MAX_VALUE))
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(radioButton)
						.addComponent(radioButton_1))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_3.setLayout(gl_panel_3);
		
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(label_2, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
						.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING, false)
							.addComponent(label_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(label, GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE))
						.addComponent(label_3, GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
						.addComponent(label_4, GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING, false)
								.addComponent(panel_3, 0, 0, Short.MAX_VALUE)
								.addComponent(textField_2)
								.addComponent(textField_1, GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
								.addComponent(btnNoviKlijent, Alignment.TRAILING)
								.addComponent(comboBox_1, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addContainerGap(23, Short.MAX_VALUE))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(textArea, GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
							.addGap(22))))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(12)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1)
						.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(btnNoviKlijent)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_3)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(label_4)
						.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
							.addComponent(label_2)
							.addGap(66))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(6)
							.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
		);
		panel_2.setLayout(gl_panel_2);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE)
						.addComponent(button, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 316, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(button)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Unos \u017Ealbe", null, panel_1, null);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		JButton btnZabiljeialbu = new JButton("Zabilje\u017Ei \u017Ealbu");
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
					.addContainerGap(27, Short.MAX_VALUE)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnZabiljeialbu)
						.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE))
					.addGap(29))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(24)
					.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, 278, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnZabiljeialbu)
					.addContainerGap(16, Short.MAX_VALUE))
		);
		
		JLabel lblZaposlenik = new JLabel("Zaposlenik:");
		lblZaposlenik.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setEditable(true);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Niko Niki\u0107"}));
		
		JLabel lblKomentar = new JLabel("Komentar:");
		lblKomentar.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JTextArea textArea_1 = new JTextArea();
		GroupLayout gl_panel_4 = new GroupLayout(panel_4);
		gl_panel_4.setHorizontalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_4.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblZaposlenik, GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
						.addComponent(lblKomentar, GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING, false)
						.addComponent(comboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(textArea_1, GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE))
					.addGap(23))
		);
		gl_panel_4.setVerticalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_4.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblZaposlenik)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_4.createParallelGroup(Alignment.BASELINE)
						.addComponent(textArea_1, GroupLayout.PREFERRED_SIZE, 226, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblKomentar))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_4.setLayout(gl_panel_4);
		panel_1.setLayout(gl_panel_1);
		frmInterfejsZaOperatera.getContentPane().setLayout(groupLayout);
	}

	private static ArrayList<Klijent> get_klijenti() {
		return _klijenti;
	}

	private static void set_klijenti(ArrayList<Klijent> _klijenti) {
		MainOperater._klijenti = _klijenti;
	}

	private static ArrayList<Zahtjev> get_zahtjevi() {
		return _zahtjevi;
	}

	private static void set_zahtjevi(ArrayList<Zahtjev> _zahtjevi) {
		MainOperater._zahtjevi = _zahtjevi;
	}

	public static Klijent get_novi() {
		return _novi;
	}

	public static void set_novi(Klijent _novi) {
		MainOperater._novi = _novi;
	}
}
