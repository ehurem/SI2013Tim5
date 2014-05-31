package org.eclipse.wb.swing.Serviser;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JRadioButton;
import javax.swing.JButton;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.dialect.unique.InformixUniqueDelegate;

import controller.UnosZahtjeva;
import controller.ZahtjevController;
import Models.Klijent;
import Models.Zahtjev;
import tim5.si.unsa.ba.Tim5Projekat.HibernateUtil;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JScrollPane;

public class ZatvaranjeZahtjeva {

	private JFrame frmZatvaranjeZahtjeva;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private ArrayList<String> listRez;
	private JRadioButton rdbtnDa;
	private JRadioButton rdbtnNe;
	
	private JTextArea textArea;
	
	private ZahtjevController kontroler;
	
	public static String zahtjev_id;
	public static Long zaposlenik_id;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args, String idzahtjeva, Long zaposlenik) {
		zahtjev_id = idzahtjeva;
		zaposlenik_id = zaposlenik;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ZatvaranjeZahtjeva window = new ZatvaranjeZahtjeva();
					window.frmZatvaranjeZahtjeva.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/*public static void main(long id) {
		//zahtjev_id = args.length
		long a = id;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ZatvaranjeZahtjeva window = new ZatvaranjeZahtjeva();
					window.frmZatvaranjeZahtjeva.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	*/
	public void Show()
	{
		frmZatvaranjeZahtjeva.setVisible(true);
	}
	
	public void Close()
	{
		frmZatvaranjeZahtjeva.dispose();
	}

	/**
	 * Create the application.
	 */
	public ZatvaranjeZahtjeva() {
		
		initialize();
		kontroler = new ZahtjevController();
		listRez = new ArrayList<String>();
		try {
			listRez = kontroler.PopuniPodatke(textField, textField_1, textField_2, textField_3, textArea, zahtjev_id, rdbtnDa, rdbtnNe);
		} catch (Exception e) {
			infoBox(e.getLocalizedMessage(), "Greška");
		}
		finally
		{
			textField.setText(listRez.get(0));
			textField_1.setText(listRez.get(1));
			textField_2.setText(listRez.get(2));
			textField_3.setText(listRez.get(3));
			textArea.setText(listRez.get(4));
			if(listRez.get(0).equals("true"))
			{
				rdbtnDa.setSelected(true);
				rdbtnNe.setSelected(false);
			}
			else
			{
				rdbtnDa.setSelected(false);
				rdbtnNe.setSelected(true);
			}
		}
		
		//PopuniPodatke();
	}
	
	/*public ZatvaranjeZahtjeva() {
		
		initialize();
		//PopuniPodatke(id);
	}*/
	
	public static void infoBox(String infoMessage, String naslov)
    {
        JOptionPane.showMessageDialog(null, infoMessage, "" + naslov, JOptionPane.INFORMATION_MESSAGE);
    }
	
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmZatvaranjeZahtjeva = new JFrame();
		frmZatvaranjeZahtjeva.setResizable(false);
		frmZatvaranjeZahtjeva.setTitle("Zatvaranje zahtjeva");
		frmZatvaranjeZahtjeva.setBounds(100, 100, 336, 373);
		//frmZatvaranjeZahtjeva.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		
		//editovanje zahtjeva
		JButton btnZatvoriZahtjev = new JButton("Zatvori zahtjev");
		btnZatvoriZahtjev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Session session = HibernateUtil.getSessionFactory().openSession();
				//Transaction t = session.beginTransaction();
				if(ZahtjevController.validirajPrazno(textField_3) && ZahtjevController.validirajPrazno(textArea) && ZahtjevController.validirajCijenu(textField_3.getText(), rdbtnDa.isSelected()))
				{
					try {
						kontroler.UpisivanjeZatvorenogZahtjeva(zahtjev_id, textField_3.getText(), textArea.getText(), zaposlenik_id);
						infoBox("Zahtjev uspješno zatvoren", "Zatvoren zahtjev");
					}
					catch (Exception ex) {
						infoBox(ex.getLocalizedMessage(), "Greška");
					}
					finally
					{
						//session.close();
					}
					
					frmZatvaranjeZahtjeva.dispose();

				}
				else
				{
					infoBox("Neispravni podaci - cijena ne smije biti prazna i mora biti broj, komentar ne smije biti prazan", "Greška");
				}
			}
		});
		GroupLayout groupLayout = new GroupLayout(frmZatvaranjeZahtjeva.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 297, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(13, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(221, Short.MAX_VALUE)
					.addComponent(btnZatvoriZahtjev)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 266, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnZatvoriZahtjev)
					.addContainerGap(37, Short.MAX_VALUE))
		);
		
		JLabel lblImeIPrezime = new JLabel("Ime i prezime klijenta:");
		lblImeIPrezime.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblTipUreaja = new JLabel("Tip ure\u0111aja:");
		lblTipUreaja.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblIdZahtjeva = new JLabel("ID zahtjeva:");
		lblIdZahtjeva.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblKomentar = new JLabel("Komentar:");
		lblKomentar.setHorizontalAlignment(SwingConstants.RIGHT);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		
		JLabel lblGarancija = new JLabel("Garancija:");
		lblGarancija.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		JLabel lblCijena = new JLabel("Cijena:");
		lblCijena.setHorizontalAlignment(SwingConstants.RIGHT);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblKomentar, GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
						.addComponent(lblImeIPrezime, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblIdZahtjeva, GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
						.addComponent(lblTipUreaja, GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
						.addComponent(lblGarancija, GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
						.addComponent(lblCijena, GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
							.addComponent(textField_3)
							.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(textField_2)
							.addComponent(textField_1)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE))
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE))
					.addGap(43))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblIdZahtjeva)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblImeIPrezime))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTipUreaja))
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblCijena))
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(13)
							.addComponent(lblGarancija)
							.addPreferredGap(ComponentPlacement.RELATED, 127, Short.MAX_VALUE)
							.addComponent(lblKomentar)))
					.addContainerGap())
		);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		rdbtnDa = new JRadioButton("Da");
		rdbtnDa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnDa.isSelected()) {
					textField_3.setText("0.00");
					textField_3.setEditable(false);
				}
			}
		});
		rdbtnDa.setSelected(true);
		rdbtnNe = new JRadioButton("Ne");
		rdbtnNe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnNe.isSelected()) {
					textField_3.setEditable(true);
				}
			}
		});
		
		final ButtonGroup grupaGarancija = new ButtonGroup();
		grupaGarancija.add(rdbtnDa);
		grupaGarancija.add(rdbtnNe);
		
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(17)
					.addComponent(rdbtnDa)
					.addGap(5)
					.addComponent(rdbtnNe)
					.addContainerGap(22, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(rdbtnDa)
						.addComponent(rdbtnNe))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		panel.setLayout(gl_panel);
		frmZatvaranjeZahtjeva.getContentPane().setLayout(groupLayout);
	}
}
