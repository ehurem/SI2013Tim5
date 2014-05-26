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

import Models.Klijent;
import Models.Zahtjev;
import tim5.si.unsa.ba.Tim5Projekat.HibernateUtil;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Date;
import java.util.List;

public class ZatvaranjeZahtjeva {

	private JFrame frmZatvaranjeZahtjeva;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	
	private JRadioButton rdbtnDa;
	private JRadioButton rdbtnNe;
	
	private JTextArea textArea;
	
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
		PopuniPodatke();
	}
	
	/*public ZatvaranjeZahtjeva() {
		
		initialize();
		//PopuniPodatke(id);
	}*/
	
	public static void infoBox(String infoMessage, String naslov)
    {
        JOptionPane.showMessageDialog(null, infoMessage, "" + naslov, JOptionPane.INFORMATION_MESSAGE);
    }
	
	boolean garancija;
	
	public void PopuniPodatke()
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		try {
			//Session session = HibernateUtil.getSessionFactory().openSession();
			//Transaction t = session.beginTransaction();
			
			String idZahtjeva = zahtjev_id;
			
			Query queryZahtjev;
			Query queryZahtjevid;
			Query queryZahtjevklijent;
			List<Zahtjev> listZahtjev;
			
			if(idZahtjeva!=null)
			{
				queryZahtjevid = session.createQuery("from Zahtjev where id='"+idZahtjeva+"'");
				listZahtjev = (List<Zahtjev>)queryZahtjevid.list();
			}
			else
			{
				queryZahtjev = session.createQuery("from Zahtjev where _status='U izvrsavanju'");
				listZahtjev = (List<Zahtjev>)queryZahtjev.list();
			}
			
			String s1 = String.valueOf(listZahtjev.get(0).getKlijent());
			
			queryZahtjevklijent = session.createQuery("from Klijent where id='" + s1  + "'");
			List <Klijent> k = queryZahtjevklijent.list();
			
			String s5 = k.get(0).get_imeIPrezime();
			
			//
			
			
			//List<Zahtjev> listZahtjev = (List<Zahtjev>)queryZahtjevid.list();
			
			String s = String.valueOf(listZahtjev.get(0).getID());
			//String s1 = String.valueOf(listZahtjev.get(0).getKlijent());
			String s2 = String.valueOf(listZahtjev.get(0).getTipUredaja());
			String s3 = String.valueOf(listZahtjev.get(0).get_cijena());
			String s4 = String.valueOf(listZahtjev.get(0).getKomentar());
			
			garancija = listZahtjev.get(0).getGarancija();

			rdbtnDa = new JRadioButton("Da");
			rdbtnNe = new JRadioButton("Ne");
			
			rdbtnDa.setEnabled(false);
			rdbtnNe.setEnabled(false);
			
			//rdbtnDa.setFocusable(false);
			
			rdbtnDa.setSelected(garancija);
			rdbtnNe.setSelected(!garancija);
			
			textField.setText(s);
			textField_1.setText(s5);
			textField_2.setText(s2);
			textField_3.setText(s3);
			textArea.setText(s4);
			
			
			
			
			
			
			t.commit();
			
			//session.close();
			
		}
		catch (Exception ex) {
			infoBox(ex.toString(), "UZBUNA");
		}
		finally
		{
			session.close();
		}
        
        
	}
	


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmZatvaranjeZahtjeva = new JFrame();
		frmZatvaranjeZahtjeva.setResizable(false);
		frmZatvaranjeZahtjeva.setTitle("Zatvaranje zahtjeva");
		frmZatvaranjeZahtjeva.setBounds(100, 100, 336, 373);
		frmZatvaranjeZahtjeva.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		
		//editovanje zahtjeva
		JButton btnZatvoriZahtjev = new JButton("Zatvori zahtjev");
		btnZatvoriZahtjev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Session session = HibernateUtil.getSessionFactory().openSession();
				Transaction t = session.beginTransaction();
				try {
					
					String idZahtjeva = zahtjev_id;
					
					Query queryZahtjevid;
					List<Zahtjev> listZahtjev;
					
					queryZahtjevid = session.createQuery("from Zahtjev where id='"+idZahtjeva+"'");
					listZahtjev = (List<Zahtjev>)queryZahtjevid.list();
					Zahtjev z = listZahtjev.get(0);
					z.setStatus("Zatvoren");
					
					Double cij = Double.parseDouble(textField_3.getText());
					z.set_cijena(cij);
					
					String kom = textArea.getText();
					z.setKomentar(kom);
					
					z.setZaposlenik(zaposlenik_id);
					//z.setZaposlenik((long) 1);
					
					Date d = new Date();
					java.sql.Date dat = new java.sql.Date(d.getTime()); 
					z.setDatumZatvaranja(dat);
				
				
					t.commit();
					
					//session.close();

					
				}
				catch (Exception ex) {
					infoBox(ex.toString(), "UZBUNA");
				}
				finally
				{
					session.close();
				}
				
				serviser s = new serviser();
				frmZatvaranjeZahtjeva.dispose();
				s.Show();
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
		
		textArea = new JTextArea();
		
		JLabel lblGarancija = new JLabel("Garancija:");
		lblGarancija.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		JLabel lblCijena = new JLabel("Cijena:");
		lblCijena.setHorizontalAlignment(SwingConstants.RIGHT);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblKomentar, GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
						.addComponent(lblImeIPrezime, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
						.addComponent(lblIdZahtjeva, GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
						.addComponent(lblTipUreaja, GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
						.addComponent(lblGarancija, GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
						.addComponent(lblCijena, GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
							.addComponent(textField_3)
							.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(textField_2)
							.addComponent(textField_1)
							.addComponent(textArea))
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE))
					.addGap(43))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(18, Short.MAX_VALUE)
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
							.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(13)
							.addComponent(lblGarancija)
							.addPreferredGap(ComponentPlacement.RELATED, 126, Short.MAX_VALUE)
							.addComponent(lblKomentar)))
					.addContainerGap())
		);
		
		rdbtnDa = new JRadioButton("Da");
		rdbtnDa.setSelected(true);
		rdbtnNe = new JRadioButton("Ne");
		
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
