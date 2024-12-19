package classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import baglanti.DbConnection;
public class poliklinik {
	DbConnection conC = new DbConnection();
	private int id_klinik;
	private String klinik_ad;
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement pst = null;
	Connection con = conC.conDB();
	
	public poliklinik() {
		
	}
	public poliklinik(int id_klinik, String klinik_ad) {
		super();
		this.id_klinik = id_klinik;
		this.klinik_ad = klinik_ad;
	}
	public int getId_klinik() {
		return id_klinik;
	}
	public void setId_klinik(int id_klinik) {
		this.id_klinik = id_klinik;
	}
	public String getKlinik_ad() {
		return klinik_ad;
	}
	public void setKlinik_ad(String klinik_ad) {
		this.klinik_ad = klinik_ad;
	}
	public ArrayList<poliklinik> getPoliList() throws SQLException {
		ArrayList<poliklinik> liste2 = new ArrayList<poliklinik>();
		poliklinik obje2;
		try {
		st = con.createStatement();
		rs = st.executeQuery("SELECT * FROM poliklinik");
			while(rs.next()) {
				obje2 = new poliklinik();
				obje2.setId_klinik(rs.getInt("id_poliklinik"));
				obje2.setKlinik_ad(rs.getString("klinik_adi"));
				liste2.add(obje2);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return liste2;
	}
	public boolean ekleClinic(String klinik_ad) {
		String query = "INSERT INTO poliklinik " + "(klinik_adi) VALUES" + "(?)";
		boolean gerceklestiMi = false;
		try {
			st = con.createStatement();
			pst = con.prepareStatement(query);
			pst.setString(1, klinik_ad);
			pst.executeUpdate();
			gerceklestiMi = true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return gerceklestiMi;
	}
	public boolean cikarClinic(int id_klinik) {
		String query = "DELETE FROM poliklinik WHERE id_poliklinik = ?";
		boolean gerceklestiMi = false;
		try {
			st = con.createStatement();
			pst = con.prepareStatement(query);
			pst.setInt(1, id_klinik);
			pst.executeUpdate();
			gerceklestiMi = true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return gerceklestiMi;
	}
	public boolean guncelleClinic(int id_klinik,String klinik_ad) {
		String query = "UPDATE poliklinik SET klinik_adi = ? WHERE id_poliklinik = ?";
		boolean gerceklestiMi = false;
		try {
			pst = con.prepareStatement(query);
			pst.setString(1,klinik_ad);
			pst.setInt(2, id_klinik);
			pst.executeUpdate();
			gerceklestiMi = true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return gerceklestiMi;
		
	}
	public poliklinik getFetch(int id_klinik) {
		poliklinik p = new poliklinik();
		Connection con = conC.conDB();
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM poliklinik where id_poliklinik = "+id_klinik);
			while(rs.next()) {
				p = new poliklinik();
				p.setId_klinik(rs.getInt("id_poliklinik"));
				p.setKlinik_ad(rs.getString("klinik_adi"));
				break;
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return p;
	}
}
