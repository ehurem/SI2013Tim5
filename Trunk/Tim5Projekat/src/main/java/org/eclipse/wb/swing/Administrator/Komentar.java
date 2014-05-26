package org.eclipse.wb.swing.Administrator;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextArea;

public class Komentar {

	private JFrame frmKomentar;
	String komentar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args, final String komentar) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					Komentar window = new Komentar(komentar);
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
	public Komentar(String kom){
		komentar = kom;
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmKomentar = new JFrame();
		frmKomentar.setResizable(false);
		frmKomentar.setTitle("Komentar");
		frmKomentar.setBounds(100, 100, 322, 247);
		frmKomentar.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTextArea textArea = new JTextArea();
		textArea.setText(komentar);
		textArea.setEditable(false);
		GroupLayout groupLayout = new GroupLayout(frmKomentar.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 281, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(15, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(17, Short.MAX_VALUE))
		);
		frmKomentar.getContentPane().setLayout(groupLayout);
	}
}
