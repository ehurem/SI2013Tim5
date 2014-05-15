package Models;

import java.sql.Date;

public class Zalba {
	private long id;
	private long _zaposlenikId;
	private String _komentar;
	private Date _datumPodnosenja;
	private long _klijentId;
	
	public Zalba(){}
	
	public long getZaposlenik() {
		return _zaposlenikId;
	}

	public void setZaposlenik(long _zaposlenik) {
		this._zaposlenikId = _zaposlenik;
	}

	public String getKomentar() {
		return _komentar;
	}

	public void setKomentar(String _komentar) {
		this._komentar = _komentar;
	}

	public Date getDatumPodnosenja() {
		return _datumPodnosenja;
	}

	public void setDatumPodnosenja(Date _datumPodnosenja) {
		this._datumPodnosenja = _datumPodnosenja;
	}

	public long get_klijent() {
		return _klijentId;
	}

	public void set_klijent(long _klijent) {
		this._klijentId = _klijent;
	}

	private long getId() {
		return id;
	}

	private void setId(long id) {
		this.id = id;
	}
}
