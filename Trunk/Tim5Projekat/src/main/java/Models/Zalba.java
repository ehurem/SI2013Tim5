package Models;

import java.sql.Date;

public class Zalba {
	private long id;
	private Zaposlenik _zaposlenik;
	private String _komentar;
	private Date _datumPodnosenja;
	private Klijent _klijent;
	
	public Zalba(){}
	
	public Zaposlenik getZaposlenik() {
		return _zaposlenik;
	}

	public void setZaposlenik(Zaposlenik _zaposlenik) {
		this._zaposlenik = _zaposlenik;
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

	public Klijent get_klijent() {
		return _klijent;
	}

	public void set_klijent(Klijent _klijent) {
		this._klijent = _klijent;
	}

	private long getId() {
		return id;
	}

	private void setId(long id) {
		this.id = id;
	}
}
