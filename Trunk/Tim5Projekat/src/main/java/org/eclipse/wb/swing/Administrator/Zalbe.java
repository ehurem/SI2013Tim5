package org.eclipse.wb.swing.Administrator;

import java.awt.EventQueue;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ComboBoxEditor;
import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;

import Models.Klijent;
import Models.Zalba;
import Models.Zaposlenik;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextField;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import tim5.si.unsa.ba.Tim5Projekat.HibernateUtil;

public class Zalbe {

	private JFrame frmzalbe;
	//private List<Zalba> listZalbe;
	private Long id;
	String komentar;
	

	private JTable table;

	/**
	 * Launch the application.
	 */
	public void main(String[] args) {
		
		 
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
		//frmzalbe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblZaposlenik = new JLabel("Zaposlenik:");
		lblZaposlenik.setHorizontalAlignment(SwingConstants.RIGHT);
		
		table = new JTable();
		final DefaultTableModel tmodel = new DefaultTableModel() {
	    	public boolean isCellEditable(int row, int column){return false;}
	   	    
	    };
	    
		table.setModel(tmodel);
	    
	    tmodel.addColumn("Klijent");
		tmodel.addColumn("Komentar");
		tmodel.addColumn("Datum podnošenja");
		
		final JComboBox comboBox = new JComboBox();
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			
			Transaction tr = session.beginTransaction();
			
			
			
			Query queryZaposlenik = session.createQuery("from Zaposlenik");
			List<Zaposlenik> listZaposlenik = (List<Zaposlenik>)queryZaposlenik.list();
			
			
			
			for(int i=0;i<listZaposlenik.size();i++){
				comboBox.addItem(listZaposlenik.get(i));
			}
			tr.commit();
			
			
		}
		catch (Exception ex) {
			JOptionPane.showMessageDialog(table, ex.toString());
		}
		finally{
			session.close();
		}
		
		
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				for(int i=0;i<tmodel.getRowCount();i++)
					tmodel.removeRow(i);
				
				Session session = HibernateUtil.getSessionFactory().openSession();
				   try {
						
						Transaction tr = session.beginTransaction();
						
						
						Zaposlenik z = (Zaposlenik) comboBox.getSelectedItem();
						setId(z.getId());											
						Query queryZalbe = session.createQuery("from Zalba where _zaposlenikId = "+id);
						List<Zalba> listZalbe = (List<Zalba>) queryZalbe.list();
						
						
						Query queryImeKlijenta = session.createQuery("from Klijent");
						List<Klijent> klijenti = queryImeKlijenta.list();
						for(int i=0;i<listZalbe.size();i++){
							if(listZalbe.get(i).get_klijent() == klijenti.get(i).getId()){
								
								String imeKlijenta = (klijenti.get(i)).get_imeIPrezime();
								String komentar1 = listZalbe.get(i).getKomentar();
								komentar = komentar1;
								Date datum = listZalbe.get(i).getDatumPodnosenja();
								tmodel.addRow(new Object[] { imeKlijenta, komentar1, datum});
							}
						}
						
						tr.commit();
						
						
					}
					catch (Exception ex) {
						JOptionPane.showMessageDialog(table, ex.toString());
					}
				   finally{
					   session.close();				  
				   }
				
				
				
			}
				
		});
		
		
		
		
		
		JButton btnOk = new JButton("Zatvori");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmzalbe.dispose();
				
				
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				Komentar forma = new Komentar(komentar);
				forma.main(null, komentar);
			}
		});
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
		
		
		scrollPane.setViewportView(table);
		frmzalbe.getContentPane().setLayout(groupLayout);
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public static void infoBox(String infoMessage, String naslov)
    {
        JOptionPane.showMessageDialog(null, infoMessage, "" + naslov, JOptionPane.INFORMATION_MESSAGE);
    }
	
}
