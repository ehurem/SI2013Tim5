package org.eclipse.wb.swing.Operater;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.JTextField;

import org.hibernate.exception.DataException;
import org.hibernate.exception.SQLGrammarException;

import Models.*;
import controller.DodavanjeKlijenta;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DodajKlijenta {

	private JFrame frmUnosNovogKlijenta;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	
	private static Klijent _klijent;
	
	private static DodavanjeKlijenta _kontroler;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args, final Klijent klijent) {
		
		set_klijent(klijent);
		_kontroler = new DodavanjeKlijenta();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DodajKlijenta window = new DodajKlijenta();
					window.frmUnosNovogKlijenta.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DodajKlijenta() {
		initialize();
		 
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	public static void infoBox(String infoMessage, String naslov)
    {
        JOptionPane.showMessageDialog(null, infoMessage, "" + naslov, JOptionPane.INFORMATION_MESSAGE);
    }
	
	private void initialize() {
		frmUnosNovogKlijenta = new JFrame();
		frmUnosNovogKlijenta.setResizable(false);
		frmUnosNovogKlijenta.setTitle("Unos novog klijenta");
		frmUnosNovogKlijenta.setBounds(100, 100, 298, 217);
		//frmUnosNovogKlijenta.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		
		JButton btnUnesi = new JButton("Unesi");
		btnUnesi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Long vratio =_kontroler.unesiKlijentaUBazu(_klijent, textField, textField_2, textField_3, textField_1 );
					
					if(vratio != 0){
						infoBox("Uspješno unesen klijent", "Unos OK");
						frmUnosNovogKlijenta.dispose();
					}
				} 
				catch (Exception e1) {
					e1.printStackTrace();
					if(e1.getMessage().equals("could not execute statement"))
						infoBox("Klijent sa tim podacima već postoji !", "Greška");
					else infoBox(e1.getLocalizedMessage(), "Greška");
				}
			}
		});
		GroupLayout groupLayout = new GroupLayout(frmUnosNovogKlijenta.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(24, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(205, Short.MAX_VALUE)
					.addComponent(btnUnesi, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnUnesi)
					.addContainerGap(36, Short.MAX_VALUE))
		);
		
		JLabel lblImeIPrezime = new JLabel("Ime i prezime:");
		lblImeIPrezime.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblAdresa = new JLabel("Adresa:");
		lblAdresa.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblBrojTelefona = new JLabel("Broj telefona:");
		lblBrojTelefona.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblEmailAdresa = new JLabel("E-mail adresa:");
		lblEmailAdresa.setHorizontalAlignment(SwingConstants.RIGHT);
		
		textField = new JTextField();
		textField.setColumns(10);
		
		textField_1 = new JTextField();		
		
		textField_1.setColumns(10);
		
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		
		textField_3.setColumns(10);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(23)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblAdresa, GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
								.addComponent(lblImeIPrezime, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(textField_1)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblBrojTelefona, GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblEmailAdresa, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)))
					.addGap(28))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblImeIPrezime)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAdresa)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblBrojTelefona)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEmailAdresa)
						.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(22, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		frmUnosNovogKlijenta.getContentPane().setLayout(groupLayout);
	}

	private Klijent get_klijent() {
		return _klijent;
	}

	public static void set_klijent(Klijent _klijent) {
		DodajKlijenta._klijent = _klijent;
	}
}
