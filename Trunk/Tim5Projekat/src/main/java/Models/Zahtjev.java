package Models;

import java.sql.Date;
import java.util.Comparator;



public class Zahtjev implements Comparable<Zahtjev> {
	private long id;
	private long _klijentId;
	private String _tipUredaja;
	private boolean _garancija;
	private String _komentar;
	private Date _datumZatvaranja;
	private Date _datumOtvaranja;
	private long _zaposlenikId;
	private String _status;
	private int _prioritet;
	private double _cijena;
	
	/* Komparator implementacija za sortiranje po prioritetu */
	
	public static class PoPrioritetu implements Comparator<Zahtjev> {

	    public int compare(Zahtjev z1, Zahtjev z2) {
            return z1.getPrioritet() > z2.getPrioritet() ? -1 : (z1.getPrioritet() < z2.getPrioritet() ? 1 : 0);
        }
    }
	/*Sortiranje po idu zahtjeva kao prirodno sortiranje za Zahtjev*/
	
    public int compareTo(Zahtjev z) {
	        return this.id > z.getID() ? 1 : (this.id < z.getID() ? -1 : 0);
	    }
	public Zahtjev(){}
    
	public long getID() {
		return id;
	}

	public void setID(long _id) {
		this.id = _id;
	}

	public long getKlijent() {
		return _klijentId;
	}

	public void setKlijent(long _klijent) {
		this._klijentId = _klijent;
	}

	public String getTipUredaja() {
		return _tipUredaja;
	}

	public void setTipUredaja(String _tipUredaja) {
		this._tipUredaja = _tipUredaja;
	}

	public boolean getGarancija() {
		return _garancija;
	}

	public void setGarancija(boolean _garancija) {
		this._garancija = _garancija;
	}

	public String getKomentar() {
		return _komentar;
	}

	public void setKomentar(String _komentar) {
		this._komentar = _komentar;
	}

	public Date getDatumZatvaranja() {
		return _datumZatvaranja;
	}

	public void setDatumZatvaranja(Date _datumZatvaranja) {
		this._datumZatvaranja = _datumZatvaranja;
	}

	public Date getDatumOtvaranja() {
		return _datumOtvaranja;
	}

	public void setDatumOtvaranja(Date _datumOtvaranja) {
		this._datumOtvaranja = _datumOtvaranja;
	}

	public Long getZaposlenik() {
		return _zaposlenikId;
	}

	public void setZaposlenik(Long _zaposlenik) {
		this._zaposlenikId = _zaposlenik;
	}

	public String getStatus() {
		return _status;
	}

	public void setStatus(String status) {
		_status = status;
	}

	public int getPrioritet() {
		return _prioritet;
	}

	public void setPrioritet(int _prioritet) {
		this._prioritet = _prioritet;
	}

	public double get_cijena() {
		return _cijena;
	}

	public void set_cijena(double _cijena) {
		this._cijena = _cijena;
	}
}
