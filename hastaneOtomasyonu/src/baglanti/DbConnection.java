package baglanti;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
	static final String DB_URL = "jdbc:mysql://localhost:3306/";
	static final String USER = "root";
	static final String PASS = "root";
	Connection con = null;
	public DbConnection() {}//Bos bir constructor olusturdum
	public Connection conDB()  { //Baglantiyi saglamak icin her classta bir tane fonksiyon olusturdum ve geri donus degeri olarak connection gonderen
		try {	 
		this.con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hastaneotomasyonu","root","root");
		return con;
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return con;
	}
	//asd
}