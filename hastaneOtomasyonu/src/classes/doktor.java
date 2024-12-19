package classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class doktor extends Kullanici {

	Statement st = null;
	ResultSet rs = null;
	PreparedStatement pst = null;
	Connection con = conK.conDB();

	public doktor() {
	}

	public doktor(int id, String tC_kim, String ad, String soyad, String parola, String type) {
		super(id, tC_kim, ad, soyad, parola, type);
	}
	public ArrayList<Calsaat> getCalSaatList(int clinic_id) throws SQLException {
		ArrayList<Calsaat> liste = new ArrayList<Calsaat>();
		Calsaat obje;
		try {
		st = con.createStatement();
		rs = st.executeQuery("SELECT * FROM calsaat WHERE status = 'A' AND doktor_id = " + clinic_id);
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
	public boolean ekleSaat(int doktor_id, String doctorName, String doctorSurname, String wDate) {
		int key = 0;
		int count = 0;
		String query = "INSERT INTO calsaat" + "(doktor_id,doktor_ad,doktor_soyad,workday) VALUES" + "(?,?,?,?)";
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM calsaat WHERE status = 'A' AND doktor_id = " + doktor_id
					+ " AND workday = '" + wDate + "'");
			while (rs.next()) {
				count++;
				break;
				} 
			if (count == 0) {
			pst = con.prepareStatement(query);
			pst.setInt(1, doktor_id);
			pst.setString(2, doctorName);
			pst.setString(3, doctorSurname);
			pst.setString(4, wDate);
			pst.executeUpdate();
			}
			key = 1;
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (key == 1) {
			return true;
		}
		else{
		return false;
		}
	}
	public boolean cikarSaat(int id) {
		String query = "DELETE FROM calsaat WHERE id = ?";
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
		return gerceklestiMi;
		
	}
}
