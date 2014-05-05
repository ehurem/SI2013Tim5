package org.eclipse.wb.swing.Administrator;

import java.awt.EventQueue;
import java.awt.List;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;

import Models.Zahtjev;
public class IzvjestajOPoslovanju {

	private JFrame frmIzvjestajOPoslovanju;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTable table;
	private ArrayList<Zahtjev> zahtjevi;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
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
		zahtjevi=new ArrayList<Zahtjev>();
	  
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmIzvjestajOPoslovanju = new JFrame();
		frmIzvjestajOPoslovanju.setTitle("Izvje\u0161taj o poslovanju");
		frmIzvjestajOPoslovanju.setBounds(100, 100, 452, 394);
		frmIzvjestajOPoslovanju.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		
		JScrollPane scrollPane = new JScrollPane();
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		JLabel label = new JLabel("Ukupno:");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setColumns(10);
		
		JLabel label_1 = new JLabel("Zatvorenih:");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		
		JLabel label_2 = new JLabel("Otvorenih:");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		
		textField_2 = new JTextField();
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
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"ID zahtjeva", "Datum otvaranja", "Datum zatvaranja", "org.eclipse.wb.swing.Serviser"
			}
		));
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
	}
}
