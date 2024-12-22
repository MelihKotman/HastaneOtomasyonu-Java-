package classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import baglanti.DbConnection;

public class Calsaat  {
	DbConnection conA = new DbConnection();
	private int id;
	private int doctor_id;
	private String saat,status,date,ad,soyad;

	Statement st = null;
	ResultSet rs = null;
	PreparedStatement pst = null;
	Connection con = conA.conDB();
	public Calsaat() {
		super();
	}

	public Calsaat(int id, int doctor_id, String saat, String status, String date) {
		super();
		this.id = id;
		this.doctor_id = doctor_id;
		this.saat = saat;
		this.status = status;
		this.date = date;
	}
	public ArrayList<Calsaat> getCalSaatList(int doctor_id) throws SQLException {
		ArrayList<Calsaat> liste = new ArrayList<Calsaat>();
		Calsaat obje;
		try {
		Connection con = conA.conDB();
		st = con.createStatement();
		rs = st.executeQuery("SELECT * FROM calsaat WHERE status = 'A' AND doktor_id = " + doctor_id);
			while(rs.next()) {
				obje = new Calsaat();
				obje.setId(rs.getInt("id"));
				obje.setDoctor_id(rs.getInt("doktor_id"));
                obje.setAd(rs.getString("doktor_ad"));
                obje.setSoyad(rs.getString("doktor_soyad"));
				obje.setDate(rs.getString("workday"));
				obje.setStatus(rs.getString("status"));
	            liste.add(obje);

			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return liste;
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

	public String getSaat() {
		return saat;
	}

	public void setSaat(String saat) {
		this.saat = saat;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
}
