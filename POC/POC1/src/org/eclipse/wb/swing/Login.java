package org.eclipse.wb.swing;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;

import java.awt.Component;
import java.awt.Window.Type;

import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import org.eclipse.wb.swing.Administrator.*;

import Models.*;

public class Login {

	private JFrame frmLogin;
	private JTextField t_korisnickoIme;
	private JPasswordField t_sifra;
	private static ArrayList<Zaposlenik> zaposlenici;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					zaposlenici = new ArrayList<Zaposlenik>();
					Zaposlenik novi = new Zaposlenik();
					novi.setAdresa("Sarajevo bb");
					novi.setBrojTelefona("387 69 999 999");
					novi.setEmail("mail@com.com");
					novi.setIme("Mujo");
					novi.setKorisnickaSifra("sifra");
					novi.setKorisnickoIme("admin");
					novi.setPrezime("Mujica");
					novi.setPrivilegija("administrator");
					zaposlenici.add(novi);
					Login window = new Login();
					window.frmLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	public static void infoBox(String infoMessage, String naslov)
    {
        JOptionPane.showMessageDialog(null, infoMessage, "" + naslov, JOptionPane.INFORMATION_MESSAGE);
    }
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLogin = new JFrame();
		frmLogin.setResizable(false);
		frmLogin.setType(Type.UTILITY);
		frmLogin.setTitle("Login");
		frmLogin.setBounds(100, 100, 349, 178);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		
		JButton btnPrijava = new JButton("Prijava");
		btnPrijava.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = t_korisnickoIme.getText();
				String password = t_sifra.getText();
				Boolean nadjen = false;
				//infoBox(password, "SIFRA");
				for (int i = 0; i<zaposlenici.size(); i++) {
					//infoBox(zaposlenici.get(i).getKorisnickoIme() , zaposlenici.get(i).getKorisnickaSifra());
					if (zaposlenici.get(i).getKorisnickoIme().equals(username) 
							&& zaposlenici.get(i).getKorisnickaSifra().equals(password)) 
						nadjen = true;
				}
				if (nadjen) {
					frmLogin.setVisible(false);
					Main forma = new Main();
					forma.main(null);
					frmLogin.setVisible(true);
				}
				else infoBox("Pogrešni podaci za prijavu", "Greška");
			}
		});
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\merima\\eclipseProjekti\\Interfejsi\\Screenshot_2.png"));
		
		GroupLayout groupLayout = new GroupLayout(frmLogin.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 235, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnPrijava))
					.addGap(8)
					.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(15)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnPrijava)))
					.addContainerGap(22, Short.MAX_VALUE))
		);
		
		
		
		
		JLabel lblKorisnikoIme = new JLabel("Korisni\u010Dko ime:");
		
		JLabel lblLozinka = new JLabel("\u0160ifra:");
		
		t_korisnickoIme = new JTextField();
		t_korisnickoIme.setColumns(10);
		
		t_sifra = new JPasswordField();
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(22)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblLozinka)
						.addComponent(lblKorisnikoIme))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(t_sifra, GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
						.addComponent(t_korisnickoIme, GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(20)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblKorisnikoIme)
						.addComponent(t_korisnickoIme, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblLozinka)
						.addComponent(t_sifra, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(29, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		frmLogin.getContentPane().setLayout(groupLayout);
		frmLogin.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{frmLogin.getContentPane(), panel, lblLozinka, lblKorisnikoIme, t_korisnickoIme}));
	}
}
