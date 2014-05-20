package Models;

public class Zaposlenik {
	private long id;
	private String _imeIPrezime;
	private String _brojTelefona;
	private String _email;
	private String _adresaStanovanja;
	private String _korisnickoIme;
	private String _korisnickaSifra;
	private String _privilegija;	
	private java.sql.Timestamp _datumRodjenja;

	
	public String getBrojTelefona() {
		return _brojTelefona;
	}
	public void setBrojTelefona(String _brojTelefona) {
		this._brojTelefona = _brojTelefona;
	}
	public String getEmail() {
		return _email;
	}
	public void setEmail(String _email) {
		this._email = _email;
	}
	public String getAdresa() {
		return _adresaStanovanja;
	}
	public void setAdresa(String _adresaStanovanja) {
		this._adresaStanovanja = _adresaStanovanja;
	}
	public String getKorisnickoIme() {
		return _korisnickoIme;
	}
	public void setKorisnickoIme(String _korisnickoIme) {
		this._korisnickoIme = _korisnickoIme;
	}
	public String getKorisnickaSifra() {
		return _korisnickaSifra;
	}
	public void setKorisnickaSifra(String _korisnickaSifra) {
		this._korisnickaSifra = _korisnickaSifra;
	}
	public String getPrivilegija() {
		return _privilegija;
	}
	public void setPrivilegija(String _privilegija) {
		this._privilegija = _privilegija;
	}
	
	@Override
	public String toString(){
		return _imeIPrezime;
	}
	public String get_imeIPrezime() {
		return _imeIPrezime;
	}
	public void set_imeIPrezime(String _imeIPrezime) {
		this._imeIPrezime = _imeIPrezime;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public java.sql.Timestamp get_datumRodjenja() {
		return _datumRodjenja;
	}
	public void set_datumRodjenja(java.sql.Timestamp _datumRodjenja) {
		this._datumRodjenja = _datumRodjenja;
	}
}
