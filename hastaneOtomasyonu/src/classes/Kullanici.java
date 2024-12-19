package classes;
import baglanti.DbConnection;
public class Kullanici { //Superclass olarak kullanici var ve bu doktor personel ve hastaya kalitim sagalayacaktir
	 String ad;
	 String soyad;
	 private int id;
	 String TC_kim;
	 String parola;
	 String type;
	 DbConnection conK = new DbConnection();
	public Kullanici(int id, String tC_kim,String ad, String soyad,String parola, String type) {
		this.ad = ad;
		this.soyad = soyad;
		this.id = id;
		this.TC_kim = tC_kim;
		this.parola = parola;
		this.type = type;
	}
	Kullanici(){	
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAd() {
		return ad;
	}
	public void setAd(String ad) {
		this.ad = ad;
	}
	public String getSoyad() {
		return soyad;
	}
	public void setSoyad(String soyad) {
		this.soyad = soyad;
	}
	public String getTC_kim() {
		return TC_kim;
	}
	public void setTC_kim(String tC_kim) {
		TC_kim = tC_kim;
	}
	public String getParola() {
		return parola;
	}
	public void setParola(String parola) {
		this.parola = parola;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
