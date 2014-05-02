package Models;

import java.sql.Date;

public class Zalba {

	private Zaposlenik _zaposlenik;
	private String _komentar;
	private Date _datumPodnosenja;
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
}
