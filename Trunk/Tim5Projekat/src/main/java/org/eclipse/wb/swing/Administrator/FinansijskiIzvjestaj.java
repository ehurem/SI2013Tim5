package org.eclipse.wb.swing.Administrator;

import java.awt.EventQueue;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import controller.IzvjestajiKontroler;
import tim5.si.unsa.ba.Tim5Projekat.HibernateUtil;
import Models.Zahtjev;
import Models.Zaposlenik;

public class FinansijskiIzvjestaj {

	private JFrame frmFinansijskiIzvjestaj;
	private JTable table;
	private JTextField textField;
	//kreiranje liste zahtjeva
	private  ArrayList<Zahtjev> zahtjevi;
	private static int broj;
	private static double zarada;
	private IzvjestajiKontroler kontroler;



	/**
	 * Launch the application.
	 */
	public static void main(final int broj_sedmice) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					broj=broj_sedmice+1;
					zarada=0;
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
		//dodavanje zahtjeva u listu iz baze
		try {
		zahtjevi = kontroler.iscitajListuZahtjevaIzBaze();
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(table, ex.toString());
		}

		frmFinansijskiIzvjestaj = new JFrame();
		frmFinansijskiIzvjestaj.setResizable(false);
		frmFinansijskiIzvjestaj.setTitle("Finansijski izvjestaj");
		frmFinansijskiIzvjestaj.setBounds(100, 100, 367, 346);
		//frmFinansijskiIzvjestaj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblUkupnaZaradaOdabrane = new JLabel("Ukupna zarada odabrane sedmice:");
		
		//izracunavanje ukupne zarade za prikazane zahtjeve
		try {
	    zarada= kontroler.sabiranjeCijenaZahtjevaZaOdabranuSedmicu(zahtjevi, broj);
		}
		catch(Exception ex)
		{
			//JOptionPane.showMessageDialog(null, "Nema zahtjeva u odabranoj sedmici", "InfoBox: " + ex.toString(), JOptionPane.INFORMATION_MESSAGE);
		}
		
		
		textField = new JTextField(Double.toString(zarada));
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
		
		 final DefaultTableModel tmodel = new DefaultTableModel() {
	    	 // zabranjeno editovanje celije u tabeli kad se dva puta klikne na celiju
	    	public boolean isCellEditable(int row, int column){
	    		return false;
	    		}
	   	    
	    };
		table = new JTable();
		table.setModel(tmodel);
		tmodel.addColumn("ID Zahtjeva");
		tmodel.addColumn("Datum zatvaranja zahtjeva");
		tmodel.addColumn("Iznos naplate");
		table.getColumnModel().getColumn(1).setPreferredWidth(151);
		scrollPane.setViewportView(table);
		frmFinansijskiIzvjestaj.getContentPane().setLayout(groupLayout);
		//ispis zahtjeva u tabelu
		try {
		for (int i=0;i<zahtjevi.size();i++){
			Calendar c = kontroler.dateToCalendar(zahtjevi.get(i).getDatumZatvaranja());
			if (c.get(Calendar.WEEK_OF_YEAR)==broj) {
				tmodel.addRow(new Object[] {(zahtjevi.get(i).getID()), (zahtjevi.get(i).getDatumZatvaranja()), (zahtjevi.get(i).get_cijena())} );		}
		}	
		}
		catch(Exception ex)
		{
			//JOptionPane.showMessageDialog(null, "Nema zahtjeva u odabranoj sedmici", "InfoBox: " + ex.toString(), JOptionPane.INFORMATION_MESSAGE);
		}
		
	
	}

	private ArrayList<Zahtjev> getZahtjevi() {
		return zahtjevi;
	}

	private void setZahtjevi(ArrayList<Zahtjev> zahtjevi) {
		this.zahtjevi = zahtjevi;
	}
}
