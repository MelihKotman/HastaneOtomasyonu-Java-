package classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import baglanti.YardimMesaji;

public class hasta extends Kullanici {
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement pst = null;
	Connection con = conK.conDB();
	
	public hasta(int id,String tC_kim,String ad, String soyad,   String parola, String type) {
		super(id,tC_kim,ad, soyad,  parola, type);
		
	}
	public hasta() {
		
	}

	public boolean register(String Ad,String Soyad,String tcno,String Password) {
		String query = "INSERT INTO user " + "(TCKimlik,Ad,Soyad,Password,KullaniciTipi) VALUES" + "(?,?,?,?,?)";
		boolean duplicate = false;
		int key = 0;
		try {
		    st = con.createStatement();
		    rs = st.executeQuery("SELECT * FROM user WHERE TCKimlik = '"+ tcno +"'");
			while (rs.next()) {
				duplicate = true;
				YardimMesaji.gosterMesaj("Bu TC Kimlik Numarası zaten kayıtlı.");
				break;
			}
			if (!duplicate) {
				pst = con.prepareStatement(query);
				pst.setString(1, tcno);
				pst.setString(2, Ad);
				pst.setString(3, Soyad);
				pst.setString(4, Password);
				pst.setString(5,"hasta");
				pst.executeUpdate();
				key = 1;
			}
		
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(key == 1) {
            return true;
		} else {
			return false;
		}
	}
	public  boolean alRandevu(int doctor_id, int hasta_id, String doktorAd, String doktorSoyad, String hastaAd,
			String hastaSoyad, String tarih) throws SQLException {
		String query = "INSERT INTO randevu " + "(doktor_id,doktor_ad,doktor_soyad,hasta_id,"
				+ "hasta_ad,hasta_soyad,tarih) VALUES" + "(?,?,?,?,?,?,?)";
		int key = 0;
		try {
				pst = con.prepareStatement(query);
				pst.setInt(1, doctor_id);
				pst.setString(2, doktorAd);
				pst.setString(3, doktorSoyad);
				pst.setInt(4, hasta_id);
				pst.setString(5, hastaAd);
				pst.setString(6, hastaSoyad);
				pst.setString(7, tarih);
				pst.executeUpdate();
				key = 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(key == 1) {
            return true;
		} else {
			return false;
		}
	}
	public  boolean guncelleStatus(int doctor_id, String tarih) throws SQLException {
		int key = 0;
		String query = "UPDATE calsaat SET status = ? WHERE doktor_id = ? AND workday= ?";
		try {
				pst = con.prepareStatement(query);
				pst.setString(1,"P");
				pst.setInt(2, doctor_id);
				pst.setString(3, tarih);
				pst.executeUpdate();
				key = 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(key == 1) {
            return true;
		} else {
			return false;
		}
	}

}
