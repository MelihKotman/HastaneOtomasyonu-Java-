package baglanti;

import javax.swing.JOptionPane;

public class YardimMesaji {
	public static void gosterMesaj(String str) {
	String mesaj;
	
	switch(str) {
		case "tumdoldur":
			mesaj = "Lütfen tüm boşlukları doldurunuz!";
			break;
		case "Basarili":
			mesaj = "İşleminiz Başarılı!";
			break;
		default:
			mesaj = str;
			
		}
	JOptionPane.showMessageDialog(null, mesaj ,"Mesaj",JOptionPane.INFORMATION_MESSAGE);
	}
}