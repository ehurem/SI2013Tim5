package org.eclipse.wb.swing.Administrator;

import java.awt.EventQueue;
import java.awt.Dialog.ModalityType;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;

public class Komentar {

	private JDialog frmKomentar;
	static String komentar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args, final String komentar1) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					komentar = komentar1;
					Komentar window = new Komentar();
					window.frmKomentar.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	
	//ispravljen konstruktor
	public Komentar(){
		
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmKomentar = new JDialog();
		frmKomentar.setResizable(false);
		frmKomentar.setTitle("Komentar");
		frmKomentar.setBounds(100, 100, 322, 247);
		frmKomentar.setModalityType(ModalityType.APPLICATION_MODAL);
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(frmKomentar.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 281, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(25, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(25, Short.MAX_VALUE))
		);
		//frmKomentar.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setLineWrap(true);
		textArea.setText(komentar);
		textArea.setEditable(false);
		frmKomentar.getContentPane().setLayout(groupLayout);
	}
}
