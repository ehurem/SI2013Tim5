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

	@Test
	public final void testUzmiZahtjevNaIzvrsavanje() {
		List <Zahtjev> zahtjevi = serviserKontroler.ucitajOtvoreneZahtjeve();
		JTable tabela = new JTable();
		DefaultTableModel tmodel = new DefaultTableModel();
		tabela.setModel(tmodel);
		Zahtjev z = new Zahtjev();
		z.setID(3);
		z.setPrioritet(1);
		z.set_cijena(9);
		z.setGarancija(false);
		z.setTipUredaja("aaa");
		z.setKomentar("kkkk");
		long i=1;
		z.setKlijent(i);
		z.setStatus("otvoren");
		Date d = new Date();
		java.sql.Date dat = new java.sql.Date(d.getTime());
		z.setDatumOtvaranja(dat);
		z.setDatumZatvaranja(dat);
		zahtjevi.clear();
		z.setZaposlenik(i);
		zahtjevi.add(z);
		z.setID(4);
		zahtjevi.add(z);
	    serviserKontroler.popuniTabelu(tabela, zahtjevi);
	    serviserKontroler.uzmiZahtjevNaIzvrsavanje(tabela, zahtjevi, i, 1);
	    //assertTrue(zahtjevi.size()==1);
	    assertEquals(zahtjevi.size(), 1);
	}

}
