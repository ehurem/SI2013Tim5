package org.eclipse.wb.swing.Administrator;

import java.awt.EventQueue;
import java.sql.Date;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;

import Models.Zahtjev;
import Models.Zaposlenik;

public class FinansijskiIzvjestaj {

	private JFrame frmFinansijskiIzvjestaj;
	private JTable table;
	private JTextField textField;
	//kreiranje liste zahtjeva
	private  ArrayList<Zahtjev> zahtjevi;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FinansijskiIzvjestaj window = new FinansijskiIzvjestaj();
					window.frmFinansijskiIzvjestaj.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FinansijskiIzvjestaj() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	private void initialize() {
		//dodavanje zahtjeva u listu
		setZahtjevi(new ArrayList<Zahtjev>());
	    Zahtjev zahtjev1 = new Zahtjev();
	    Zahtjev zahtjev2 = new Zahtjev();
	    Zahtjev zahtjev3 = new Zahtjev();
	    zahtjev1.setID(1);
	    zahtjev2.setID(2);
	    zahtjev3.setID(3);
	    Date now1 = new Date(2013,3,18);
	    Date now2 = new Date(2013,3,21);
	    Date now3 = new Date(2013,4,18);
		zahtjev1.setDatumZatvaranja(now1);
		zahtjev2.setDatumZatvaranja(now2);
		zahtjev3.setDatumZatvaranja(now3);
		zahtjev1.set_cijena(100);
		zahtjev2.set_cijena(200);
		zahtjev3.set_cijena(50000);
        zahtjevi.add(zahtjev1);
        zahtjevi.add(zahtjev2);
        zahtjevi.add(zahtjev3);


		frmFinansijskiIzvjestaj = new JFrame();
		frmFinansijskiIzvjestaj.setTitle("Finansijski izvjestaj");
		frmFinansijskiIzvjestaj.setBounds(100, 100, 367, 346);
		frmFinansijskiIzvjestaj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblUkupnaZaradaOdabrane = new JLabel("Ukupna zarada odabrane sedmice:");
		
		//za potrebe prototipa uneseno, inaèe se raèuna
		textField = new JTextField("5300");
		textField.setEditable(false);
		textField.setColumns(10);
		GroupLayout groupLayout = new GroupLayout(frmFinansijskiIzvjestaj.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(lblUkupnaZaradaOdabrane, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 332, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 241, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUkupnaZaradaOdabrane)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(37, Short.MAX_VALUE))
		);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{zahtjevi.get(0).getID(), zahtjevi.get(0).getDatumZatvaranja(), zahtjevi.get(0).get_cijena()},
				{zahtjevi.get(1).getID(), zahtjevi.get(1).getDatumZatvaranja(), zahtjevi.get(1).get_cijena()},
				{zahtjevi.get(2).getID(), zahtjevi.get(2).getDatumZatvaranja(), zahtjevi.get(2).get_cijena()},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"ID zahtjeva", "Datum zatvaranja zahtjeva", "Iznos naplate"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(1).setPreferredWidth(151);
		scrollPane.setViewportView(table);
		frmFinansijskiIzvjestaj.getContentPane().setLayout(groupLayout);
	}

	private ArrayList<Zahtjev> getZahtjevi() {
		return zahtjevi;
	}

	private void setZahtjevi(ArrayList<Zahtjev> zahtjevi) {
		this.zahtjevi = zahtjevi;
	}
}
