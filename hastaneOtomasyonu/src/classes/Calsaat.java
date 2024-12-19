package classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

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
