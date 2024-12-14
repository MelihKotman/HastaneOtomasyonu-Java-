package classes;

import java.util.ArrayList;
import java.sql.*;
import baglanti.DbConnection;

public class personel extends Kullanici {
	
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement pst = null;
	Connection con = conK.conDB();
	public personel() {
	}

	public personel(int id,String tC_kim,String ad, String soyad,   String parola, String type) {
		super(id,tC_kim,ad, soyad,  parola, type);
		setAd(ad);
		setSoyad(soyad);
		setId(id);
		setTC_kim(tC_kim);
		setParola(parola);
		setType(type);
	}
	public ArrayList<Kullanici> getDoctorList() throws SQLException {
		ArrayList<Kullanici> liste = new ArrayList<Kullanici>();
		Kullanici obje;
		try {
		st = con.createStatement();
		rs = st.executeQuery("SELECT * FROM user WHERE KullaniciTipi = 'doktor'");
			while(rs.next()) {
				obje = new Kullanici(rs.getInt("id"),rs.getString("TCKimlik"),rs.getString("Ad"),rs.getString("Soyad"),rs.getString("Password"),rs.getString("KullaniciTipi"));
				//yeni bir doktor kaydi olustururken kullanici class'inda bir nesne olusturduk
				liste.add(obje);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return liste;
	}
	//Doktor ekleme ve çıkarma ekranindan doktor ekleme fonksiyonu
	public boolean ekleDoktor(String tcno,String Ad,String Soyad,String Password) {
		String query = "INSERT INTO user " + "(TCKimlik,Ad,Soyad,Password,KullaniciTipi) VALUES" + "(?,?,?,?,?)";
		boolean gerceklestiMi = false;
		try {
			st = con.createStatement();
			pst = con.prepareStatement(query);
			pst.setString(1, tcno);
			pst.setString(2, Ad);
			pst.setString(3, Soyad);
			pst.setString(4, Password);
			pst.setString(5,"doktor");
			pst.executeUpdate();
			gerceklestiMi = true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(gerceklestiMi) {
			return true;
		}
		else {
			return false;
		}
	}
	public boolean cikarDoktor(int id) {
		String query = "DELETE FROM user WHERE id = ?";
		boolean gerceklestiMi = false;
		try {
			st = con.createStatement();
			pst = con.prepareStatement(query);
			pst.setInt(1, id);
			pst.executeUpdate();
			gerceklestiMi = true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(gerceklestiMi) {
			return true;
		}
		else {
			return false;
		}
		
	}
	public boolean guncelleDoktor(int id,String tcno,String Ad,String Soyad,String Password) {
		String query = "UPDATE user SET TCKimlik = ?, Ad = ?, Soyad = ?, Password = ? WHERE id = ?";
		boolean gerceklestiMi = false;
		try {
			st = con.createStatement();
			pst = con.prepareStatement(query);
			pst.setString(1, tcno);
			pst.setString(2, Ad);
			pst.setString(3, Soyad);
			pst.setString(4, Password);
			pst.setInt(5, id);
			pst.executeUpdate();
			gerceklestiMi = true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(gerceklestiMi) {
			return true;
		}
		else {
			return false;
		}
		
	}
}

