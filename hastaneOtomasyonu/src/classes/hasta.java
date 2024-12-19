package classes;

public class hasta extends Kullanici {
	public hasta(int id,String tC_kim,String ad, String soyad,   String parola, String type) {
		super(id,tC_kim,ad, soyad,  parola, type);
		setAd(ad);
		setSoyad(soyad);
		setId(id);
		setTC_kim(tC_kim);
		setParola(parola);
		setType(type);
	}
	public hasta() {
		
	}

}
