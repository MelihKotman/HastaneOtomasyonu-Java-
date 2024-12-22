package classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import baglanti.DbConnection;

public class randevu {
	DbConnection conR = new DbConnection();
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement pst = null;
	Connection con = conR.conDB();
	
	private int id,doctor_id,hasta_id;
	private String doktorAd,doktorSoyad,hastaAd,hastaSoyad,tarih;
	public randevu() {
		
	}
	
	public randevu(int id, int doctor_id, int hasta_id, String doktorAd, String doktorSoyad, String hastaAd,
			String hastaSoyad, String tarih) {
		super();
		this.id = id;
		this.doctor_id = doctor_id;
		this.hasta_id = hasta_id;
		this.doktorAd = doktorAd;
		this.doktorSoyad = doktorSoyad;
		this.hastaAd = hastaAd;
		this.hastaSoyad = hastaSoyad;
		this.tarih = tarih;
	}

	public ArrayList<randevu> getHastaRanList(int hastaID) throws SQLException {
		ArrayList<randevu> liste3 = new ArrayList<randevu>();
        randevu obje3;
        Connection con = conR.conDB();
        try {
             st = con.createStatement();
             rs = st.executeQuery("SELECT * FROM randevu WHERE hasta_id = "+ hastaID );
			while(rs.next()) {
				obje3 = new randevu();
				obje3.setId(rs.getInt("id"));
				obje3.setDoctor_id(rs.getInt("doktor_id"));
				obje3.setDoktorAd(rs.getString("doktor_ad"));
				obje3.setDoktorSoyad(rs.getString("doktor_soyad"));
				obje3.setHasta_id(rs.getInt("hasta_id"));
				obje3.setHastaAd(rs.getString("hasta_ad"));
				obje3.setHastaSoyad(rs.getString("hasta_soyad"));
				obje3.setTarih(rs.getString("tarih"));
				liste3.add(obje3);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return liste3;
	}

	public ArrayList<randevu> getDocRanList(int doctorID) throws SQLException {
	    ArrayList<randevu> liste3 = new ArrayList<randevu>();
	    randevu obje3;
	    Connection con = conR.conDB();
	    if (con == null || con.isClosed()) {
	        System.out.println("Veritabanı bağlantısı başarısız!");
	        return liste3;
	    }
	    String query = "SELECT * FROM randevu WHERE doktor_id = ?";
	    try (PreparedStatement pst = con.prepareStatement(query)) {
	        pst.setInt(1, doctorID);
	        rs = pst.executeQuery();
	        while (rs.next()) {
	            obje3 = new randevu();
	            obje3.setId(rs.getInt("id"));
	            obje3.setDoctor_id(rs.getInt("doktor_id"));
	            obje3.setDoktorAd(rs.getString("doktor_ad"));
	            obje3.setDoktorSoyad(rs.getString("doktor_soyad"));
	            obje3.setHasta_id(rs.getInt("hasta_id"));
	            obje3.setHastaAd(rs.getString("hasta_ad"));
	            obje3.setHastaSoyad(rs.getString("hasta_soyad"));
	            obje3.setTarih(rs.getString("tarih"));
	            liste3.add(obje3);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.println("SQL Hatası: " + e.getMessage());
	    } finally {
	        if (rs != null) rs.close();
	        if (con != null) con.close();
	    }
	    return liste3;
	}
	public void silRandevu(String name,String surname,String date) {
		Connection con = conR.conDB();
		try {
			st = con.createStatement();
			String query1 = "DELETE FROM randevu WHERE tarih='" + date + "'";
			String query2 = "UPDATE calsaat SET status= 'A' WHERE doktor_ad='" + name + "' AND doktor_soyad='" + surname + "' AND workday='" + date + "'";
			pst = con.prepareStatement(query1);
			pst.executeUpdate();

			pst = con.prepareStatement(query2);
			pst.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDoctor_id() {
		return doctor_id;
	}
	public void setDoctor_id(int doctor_id) {
		this.doctor_id = doctor_id;
	}
	public int getHasta_id() {
		return hasta_id;
	}
	public void setHasta_id(int hasta_id) {
		this.hasta_id = hasta_id;
	}
	public String getDoktorAd() {
		return doktorAd;
	}
	public void setDoktorAd(String doktorAd) {
		this.doktorAd = doktorAd;
	}
	public String getDoktorSoyad() {
		return doktorSoyad;
	}
	public void setDoktorSoyad(String doktorSoyad) {
		this.doktorSoyad = doktorSoyad;
	}
	public String getHastaAd() {
		return hastaAd;
	}
	public void setHastaAd(String hastaAd) {
		this.hastaAd = hastaAd;
	}
	public String getHastaSoyad() {
		return hastaSoyad;
	}
	public void setHastaSoyad(String hastaSoyad) {
		this.hastaSoyad = hastaSoyad;
	}
	public String getTarih() {
		return tarih;
	}
	public void setTarih(String tarih) {
		this.tarih = tarih;
	}
	
}
