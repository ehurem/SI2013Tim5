package controller;


import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collections;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Models.Zahtjev;

public class TableHeaderMouseListener extends MouseAdapter {
  private serviserKontroler kontroler;  
  private JTable tabela;
  private List <Zahtjev> zahtjevi;
   public TableHeaderMouseListener(JTable tabela, List <Zahtjev> zahtjevi) {
       this.tabela = tabela;
       this.zahtjevi = zahtjevi;
       kontroler = new serviserKontroler();
   }
    
   public void mouseClicked(MouseEvent event) {
       Point point = event.getPoint();
       int column = tabela.columnAtPoint(point);
     
       if (column==0) {   
    	   ((DefaultTableModel) tabela.getModel()).setRowCount(0);
           Collections.sort(zahtjevi);
           kontroler.popuniTabelu(tabela, zahtjevi);
       }
   
       else {
    	   Collections.sort(zahtjevi, new Zahtjev.PoPrioritetu());
    	   ((DefaultTableModel) tabela.getModel()).setRowCount(0);
    	   kontroler.popuniTabelu(tabela, zahtjevi);
       }
       	
   }
}
