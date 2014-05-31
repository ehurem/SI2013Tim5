package controller;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Models.Zahtjev;

import org.junit.Test;

public class serviserKontrolerTest {

	
	@Test
	public final void testUcitajOtvoreneZahtjeve() {
		List <Zahtjev> zahtjevi = null;
		boolean test=true;
		zahtjevi = serviserKontroler.ucitajOtvoreneZahtjeve();
		for (int i=0; i<zahtjevi.size(); i++) {
			if (!zahtjevi.get(i).getStatus().equals("otvoren")) {
				test=false;
				break;
			}
		}
		assertTrue(test);
	}

	@Test
	public final void testPopuniTabelu() {
		List <Zahtjev> zahtjevi = null;
		JTable tabela = new JTable();
		DefaultTableModel tmodel = new DefaultTableModel();
		tabela.setModel(tmodel);
		zahtjevi = serviserKontroler.ucitajOtvoreneZahtjeve();
		assertTrue(serviserKontroler.popuniTabelu(tabela,zahtjevi));
	}


	@Test   //ako nema otvorenih zahtjeva u bazi baca exception
	public final void testUzmiZahtjevNaIzvrsavanje() throws Exception {
		List <Zahtjev> zahtjevi = serviserKontroler.ucitajOtvoreneZahtjeve();
		int size = zahtjevi.size();
		JTable tabela = new JTable();
		DefaultTableModel tmodel = new DefaultTableModel();
		tabela.setModel(tmodel);
	    serviserKontroler.popuniTabelu(tabela, zahtjevi);
	    serviserKontroler.uzmiZahtjevNaIzvrsavanje(tabela, zahtjevi, 1, zahtjevi.size()-1);
	    assertEquals(zahtjevi.size(),size-1);
	//	}
	}

}
