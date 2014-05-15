package org.eclipse.wb.swing.Administrator;

import java.awt.EventQueue;
import java.sql.Date;
import java.util.ArrayList;

import javax.swing.ComboBoxEditor;
import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;

import Models.Zalba;
import Models.Zaposlenik;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;

public class Zalbe {

	private JFrame frmzalbe;
	static ArrayList<Zalba> _zalbe;
	static ArrayList<Zaposlenik> _zaposlenici;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args, ArrayList<Zalba> _zal, ArrayList<Zaposlenik> _zap) {
		_zalbe = new ArrayList<Zalba>();
		 Zalba _nova_zalba = new Zalba();
		 Date dat = new Date(System.currentTimeMillis());
		 _nova_zalba.setDatumPodnosenja(dat);
		 _nova_zalba.setKomentar("Ovo je komentar �albe!");
		// _nova_zalba.setZaposlenik(_zap.get(0));
		 
		 _zalbe.add(_nova_zalba);
		 
		
		 _zaposlenici=_zap;
		 
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Zalbe window = new Zalbe();
					window.frmzalbe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Zalbe() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmzalbe = new JFrame();
		frmzalbe.setResizable(false);
		frmzalbe.setTitle("Pregled \u017Ealbi");
		frmzalbe.setBounds(100, 100, 405, 350);
		frmzalbe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblZaposlenik = new JLabel("Zaposlenik:");
		lblZaposlenik.setHorizontalAlignment(SwingConstants.RIGHT);
		final DefaultTableModel t = new DefaultTableModel() {
	   	    // zabranjeno editovanje celije u tabeli kad se dva puta klikne na celiju
	    	public boolean isCellEditable(int row, int column){
	    		return false;
	    		}
	   	    
	    };
		t.addColumn("Datum podno�enja");
		t.addColumn("Komentar");
		
		
		final JComboBox comboBox = new JComboBox();
		
		for(Zaposlenik z: _zaposlenici){
			comboBox.addItem(z);
		}
		
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Zaposlenik z = new Zaposlenik();
				z = (Zaposlenik) comboBox.getSelectedItem();
				/*for (Zalba zalba: _zalbe ){
					if(zalba.getZaposlenik() == _zaposlenici.get(0))
					{
						t.addRow(new Object[] { zalba.getDatumPodnosenja(), zalba.getKomentar()});
					}
				}*/
			}
				
		});
		
		
		
		
		
		JButton btnOk = new JButton("Zatvori");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmzalbe.dispose();
				
				
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(frmzalbe.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(10)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnOk, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 373, GroupLayout.PREFERRED_SIZE))
							.addContainerGap(35, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(lblZaposlenik)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
							.addGap(141))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblZaposlenik)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(13)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnOk)
					.addGap(18))
		);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
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
				"Klijent", "Komentar", "Datum podno\u0161enja"
			}
		));
		scrollPane.setViewportView(table);
		frmzalbe.getContentPane().setLayout(groupLayout);
	}
}
