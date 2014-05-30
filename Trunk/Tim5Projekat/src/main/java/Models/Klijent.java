package Models;

public class Klijent implements java.io.Serializable {
	private Long id;
	private String _imeIPrezime;
	private String _adresa;
	private String _brojTelefona;
	private String _email;

	public Klijent()
	{
		
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


	public String get_imeIPrezime() {
		return _imeIPrezime;
	}


	public void set_imeIPrezime(String _imeIPrezime) {
		this._imeIPrezime = _imeIPrezime;
	}


	public String get_adresa() {
		return _adresa;
	}


	public void set_adresa(String _adresa) {
		this._adresa = _adresa;
	}
	
	@Override
	public String toString()
	{
		return _imeIPrezime;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}
	

}
