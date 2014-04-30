package Models;

public class Klijent {
	private String _ime;
	private String _prezime;
	private String _brojTelefona;
	private String _email;

	public String getPrezime() {
		return _prezime;
	}
	public void setPrezime(String _prezime) {
		this._prezime = _ime;
	}
	public String getIme() {
		return _ime;
	}
	public void setIme(String _ime) {
		this._ime = _ime;
	}
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

}
