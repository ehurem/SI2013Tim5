
package org.eclipse.wb.swing.Serviser;
import java.awt.EventQueue;
import java.awt.Point;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import tim5.si.unsa.ba.Tim5Projekat.HibernateUtil;
import Models.Klijent;
import Models.Zahtjev;
import Models.Zaposlenik;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class serviser {

	private JFrame frmInterfejsZaServisera;
	private JTable table;
	private static Long _zaposlenik; //ID u bazi logovanog zaposlenika
	List<Zahtjev> listZahtjev;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args, Long zaposlenik) {
		set_zaposlenik(zaposlenik);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					serviser window = new serviser();
					window.frmInterfejsZaServisera.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void Show()
	{
		frmInterfejsZaServisera.setVisible(true);
	}
	
	public static void infoBox(String infoMessage, String naslov)
    {
        JOptionPane.showMessageDialog(null, infoMessage, "" + naslov, JOptionPane.INFORMATION_MESSAGE);
    }
	
	/**
	 * Create the application.
	 */
	public serviser() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
	
		frmInterfejsZaServisera = new JFrame();
		frmInterfejsZaServisera.setResizable(false);
		frmInterfejsZaServisera.setTitle("Interfejs za servisera");
		frmInterfejsZaServisera.setBounds(100, 100, 332, 357);
		//frmInterfejsZaServisera.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GroupLayout groupLayout = new GroupLayout(frmInterfejsZaServisera.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
		);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Pregled otvorenih zahtjeva", null, panel, null);
	    final DefaultTableModel tmodel = new DefaultTableModel() {
	    	 // zabranjeno editovanje celije u tabeli kad se dva puta klikne na celiju
	    	public boolean isCellEditable(int row, int column){
	    		return false;
	    		}
	   	    
	    };
		table = new JTable();
		table.setModel(tmodel);
		tmodel.addColumn("ID Zahtjeva");
		tmodel.addColumn("Prioritet");
		Session session = HibernateUtil.getSessionFactory().openSession();
		   try {
				
				Transaction tr = session.beginTransaction();
				Query queryZahtjev = session.createQuery("from Zahtjev where _status='Otvoren'");
				listZahtjev = queryZahtjev.list();
				for(int i=0;i<listZahtjev.size();i++){
					if((listZahtjev.get(i)).getStatus().equals("Otvoren"))
						tmodel.addRow(new Object[] {( listZahtjev.get(i)).getID(), ( listZahtjev.get(i)).getPrioritet()});
				}
				tr.commit();
				//session.close();
				
			}
			catch (Exception ex) {
				JOptionPane.showMessageDialog(table, ex.toString());
			}
		finally { 
			session.close();
		}
		   // dozvoliti selekciju samo jednog reda u tabeli
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			
			// klasa za event za klik na zaglavlje tabele
			class TableHeaderMouseListener extends MouseAdapter {
			     
			    private JTable table;
			     
			    public TableHeaderMouseListener(JTable table) {
			        this.table = table;
			    }
			     
			    public void mouseClicked(MouseEvent event) {
			        Point point = event.getPoint();
			        int column = table.columnAtPoint(point);
			      
			        if (column==0) {   
			        	tmodel.setRowCount(0);
			            Collections.sort(listZahtjev);
			        	for(int i=0; i<listZahtjev.size(); i++) {
			        		 tmodel.addRow(new Object[] {( listZahtjev.get(i)).getID(), ( listZahtjev.get(i)).getPrioritet()});
					        }
			        }
			    
			        else {
			        	Collections.sort(listZahtjev, new Zahtjev.PoPrioritetu());
			        	tmodel.setRowCount(0);
			        	for(int i=0; i<listZahtjev.size(); i++) {
			        		 tmodel.addRow(new Object[] {( listZahtjev.get(i)).getID(), ( listZahtjev.get(i)).getPrioritet()});
					        }
			        }
			        	
			    }
			}
			JTableHeader header = table.getTableHeader();
			header.addMouseListener(new TableHeaderMouseListener(table));
			// centriran tekst u tabeli
			DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
			centerRenderer.setHorizontalAlignment( JLabel.CENTER );
			table.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
			table.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );
			// zabranjeno mijenjanje redoslijeda kolona
			table.getTableHeader().setReorderingAllowed(false);
			// zabranjeno mijenjanje veličine kolona u tabeli
			table.getTableHeader().setResizingAllowed(false);
        // Button za promjenu statusa zahtjeva iz "Otvoren" u "U izvrsavanju" 
		JButton btnStatus = new JButton("Odaberi");
		btnStatus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int i=-1;
			 i=table.getSelectedRow();  
			 if(i>=0) {
				 String s = table.getValueAt(i, 0).toString();
				 int k=-1;
				  Session session = HibernateUtil.getSessionFactory().openSession();
				  try {
					
					Transaction tr = session.beginTransaction();
					Query queryZahtjev = session.createQuery("from Zahtjev where _status='Otvoren'");
					listZahtjev = queryZahtjev.list();
					for (int j=0; j<listZahtjev.size(); j++) {
						if (listZahtjev.get(j).getID()==Integer.parseInt(s)) {
							listZahtjev.get(j).setStatus("U izvrsavanju");
							listZahtjev.get(j).setZaposlenik(get_zaposlenik());
							((DefaultTableModel)table.getModel()).removeRow(i);
							JOptionPane.showMessageDialog(table, "Uspješno ste uzeli zahtjev na izvršavanje!");
							k=j;
							break;
		        	 } 
		         }
				
				session.save(listZahtjev.get(k));
				tr.commit();
				listZahtjev.remove(k);
				//session.close();
				 }
				  catch (Exception ex) {
					  JOptionPane.showMessageDialog(table, ex.toString());
				  }
				finally { 
					session.close(); 
					}
			 }
			 else JOptionPane.showMessageDialog(table, "Niste odabrali nijedan red!");
			}
		});
		
		// event za dvostruki klik na red tabele
				 table.addMouseListener(new MouseAdapter() {
					   public void mouseClicked(MouseEvent e) {
						   JTable target = (JTable)e.getSource();
						   if (e.getClickCount() == 2) {
					         int row = target.getSelectedRow();
					         int column = target.getSelectedColumn();
					         String s = target.getValueAt(row, 0).toString();
					         int index = 0;
					         for (int i=0; i<listZahtjev.size(); i++) {
					        	 if (listZahtjev.get(i).getID()==Integer.parseInt(s)) {
					        		 index=i;
					        		 break;
					        	 } 
					         }
					         PregledOdabranogZahtjeva forma = new PregledOdabranogZahtjeva();
					         forma.main(null, listZahtjev.get(index));
					         }
					      
					   }
					});
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(37)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnStatus)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 242, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(43, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 241, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnStatus)
					.addContainerGap(42, Short.MAX_VALUE))
		);
	
		table.getColumnModel().getColumn(0).setPreferredWidth(101);
		table.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		scrollPane.setViewportView(table);

		
		panel.setLayout(gl_panel);
		

		final JList list = new JList();
		final DefaultListModel listModel = new DefaultListModel();
		
		ChangeListener changeListener = new ChangeListener() {
		      public void stateChanged(ChangeEvent changeEvent) {
		        JTabbedPane sourceTabbedPane = (JTabbedPane) changeEvent.getSource();
		        int index = sourceTabbedPane.getSelectedIndex();
		        listModel.clear();
		        
		        try {
					Session session = HibernateUtil.getSessionFactory().openSession();
					Transaction t = session.beginTransaction();
					
					Query queryZahtjev = session.createQuery("from Zahtjev where _status='U izvrsavanju'");
					List listZahtjev = queryZahtjev.list();
					
					
					for(int i=0;i<listZahtjev.size();i++){
					listModel.addElement(((Zahtjev) listZahtjev.get(i)).getID());
					}
					
					list.setModel(listModel);
					
					//Zahtjev z = (Zahtjev) list.getSelectedValue();
					//z.getID();
					t.commit();
					
					session.close();
					
				}
				catch (Exception ex) {
					infoBox(ex.toString(), "UZBUNA");
				}
		        
		        
		       // comboBox.addItem(_zaposlenik);
		       // comboBox.addItem(_zaposlenik2);
		        
		      }
		    };
		tabbedPane.addChangeListener(changeListener);
		

		
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Zatvaranje zahtjeva", null, panel_1, null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		JButton btnNewButton = new JButton("Odaberi");
		/* Launches ZatvaranjeZahtjeva.java with selected Zahtjev */
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Code is here
				//list.getSelectedValue()
				String id_zahtjeva = String.valueOf(list.getSelectedValue());
	//			Zahtjev z = (Zahtjev) list.getSelectedValue();
				//String info[] = new String[2];
				//info[0] = String.valueOf(z.getID());
				//info[0] = "2";
				//info[1] = "1";
				ZatvaranjeZahtjeva jf = new ZatvaranjeZahtjeva();
				frmInterfejsZaServisera.dispose();
				jf.main(null, id_zahtjeva);
				//jf.Show();
			}
		});
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(33)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnNewButton)
						.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 253, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(36, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(btnNewButton)
					.addContainerGap())
		);
		
		JLabel lblZahtjeviUIzvavanju = new JLabel("Zahtjevi u izvr\u0161avanju");
		
//		JList list = new JList();
//		list.setModel(new AbstractListModel() {
//			String[] values = new String[] {"Zahtjev 1", "Zahtjev 2", "Zahtjev 3"};
//			public int getSize() {
//				return values.length;
//			}
//			public Object getElementAt(int index) {
//				return values[index];
//			}
//		});
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addComponent(list, GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
						.addComponent(lblZahtjeviUIzvavanju, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblZahtjeviUIzvavanju)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(list, GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
					.addContainerGap())
		);
		panel_3.setLayout(gl_panel_3);
		panel_1.setLayout(gl_panel_1);
		frmInterfejsZaServisera.getContentPane().setLayout(groupLayout);
	}

	private static Long get_zaposlenik() {
		return _zaposlenik;
	}

	private static  void set_zaposlenik(Long _zaposlenik) {
		serviser._zaposlenik = _zaposlenik;
	}
}
