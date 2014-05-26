package org.eclipse.wb.swing.Administrator;
import java.sql.Date;


import java.awt.EventQueue;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import tim5.si.unsa.ba.Tim5Projekat.HibernateUtil;
import Models.Zahtjev;
import Models.Zaposlenik;
public class IzvjestajOPoslovanju {

	private JFrame frmIzvjestajOPoslovanju;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTable table;
	//kreiranje liste zahtjeva
	private  ArrayList<Zahtjev> zahtjevi;
	private ArrayList<Zaposlenik> zaposlenici;
	private static int broj;
    private Zaposlenik z;


	/**
	 * Launch the application.
	 */
	public static void main(final int broj_sedmice) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					broj=broj_sedmice+1;
					IzvjestajOPoslovanju window = new IzvjestajOPoslovanju();
					window.frmIzvjestajOPoslovanju.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public IzvjestajOPoslovanju() {
		initialize();
	}

	public static Calendar dateToCalendar(Date date){ 
		  Calendar cal = Calendar.getInstance();
		  cal.setTime(date);
		  return cal;
		};
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//dodavanje zahtjeva u listu iz baze
		Session sesija = HibernateUtil.getSessionFactory().openSession();
		Transaction t = sesija.beginTransaction();
		try
		{
			Query queryZahtjev = sesija.createQuery("from Zahtjev");
			Query queryZaposlenik = sesija.createQuery("from Zaposlenik");
            zaposlenici =(ArrayList<Zaposlenik>) queryZaposlenik.list();
			zahtjevi = (ArrayList<Zahtjev>) queryZahtjev.list();
			t.commit();
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(table, ex.toString());
		}
		finally
		{
			sesija.close();
		}		
		
		        
		frmIzvjestajOPoslovanju = new JFrame();
		frmIzvjestajOPoslovanju.setResizable(false);
		frmIzvjestajOPoslovanju.setTitle("Izvje\u0161taj o poslovanju");
		frmIzvjestajOPoslovanju.setBounds(100, 100, 452, 394);
		frmIzvjestajOPoslovanju.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		
		JScrollPane scrollPane = new JScrollPane();
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		JLabel label = new JLabel("Ukupno:");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		
		//za potrebe prototipa uneseno, ina�e se ra�una
        textField = new JTextField("3");
		textField.setEditable(false);
		textField.setColumns(10);
		
		JLabel label_1 = new JLabel("Zatvorenih:");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		
		//za potrebe prototipa uneseno, ina�e se ra�una
		textField_1 = new JTextField("2");
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		
		JLabel label_2 = new JLabel("Otvorenih:");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		
		//za potrebe prototipa uneseno, ina�e se ra�una
		textField_2 = new JTextField("1");
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGap(0, 193, Short.MAX_VALUE)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(textField, 0, 0, Short.MAX_VALUE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(textField_1, 0, 0, Short.MAX_VALUE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(33, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGap(0, 101, Short.MAX_VALUE)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_2)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(6)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(6)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(32))
		);
		panel_1.setLayout(gl_panel_1);
		
		JButton btnZatvori = new JButton("Zatvori");
		btnZatvori.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmIzvjestajOPoslovanju.dispose();
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 434, Short.MAX_VALUE)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 414, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE)
							.addGap(139)
							.addComponent(btnZatvori, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 339, Short.MAX_VALUE)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(78)
							.addComponent(btnZatvori)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		final DefaultTableModel tmodel = new DefaultTableModel() {
	    	 // zabranjeno editovanje celije u tabeli kad se dva puta klikne na celiju
	    	public boolean isCellEditable(int row, int column){
	    		return false;
	    		}
	   	    
	    };
		table = new JTable();
		table.setModel(tmodel);
		tmodel.addColumn("ID Zahtjeva");
		tmodel.addColumn("Datum otvaranja zahtjeva");
		tmodel.addColumn("Iznos zatvaranja zahtjeva");
		tmodel.addColumn("Serviser");


		table.getColumnModel().getColumn(1).setPreferredWidth(103);
		table.getColumnModel().getColumn(2).setPreferredWidth(109);
		table.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		scrollPane.setViewportView(table);
		panel.setLayout(gl_panel);
		GroupLayout groupLayout = new GroupLayout(frmIzvjestajOPoslovanju.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 434, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE)
		);
		frmIzvjestajOPoslovanju.getContentPane().setLayout(groupLayout);
		//ispis zahtjeva u tabelu
				for (int i=0;i<zahtjevi.size();i++){
					Calendar c = dateToCalendar(zahtjevi.get(i).getDatumZatvaranja());
					if (c.get(Calendar.WEEK_OF_YEAR)==broj) {
						for (int j=0;j<zaposlenici.size();j++){
							//pretraga zaposlenika po ID radi ispisa u tabelu
							if (zahtjevi.get(i).getZaposlenik()==zaposlenici.get(j).getId()) {
							z= zaposlenici.get(j);
							}
						}
						tmodel.addRow(new Object[] {(zahtjevi.get(i).getID()),(zahtjevi.get(i).getDatumOtvaranja()), (zahtjevi.get(i).getDatumZatvaranja()), (z.get_imeIPrezime())} );		}
				}	
		
	}

	private java.util.List getZahtjevi() {
		return zahtjevi;
	}

	private void setZahtjevi(ArrayList<Zahtjev> zahtjevi) {
		this.zahtjevi = zahtjevi;
	}
}
