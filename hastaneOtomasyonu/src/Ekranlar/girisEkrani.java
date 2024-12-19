package Ekranlar;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import baglanti.YardimMesaji;
import classes.doktor;
import classes.hasta;
import classes.personel;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.DropMode;
import javax.swing.border.BevelBorder;
import javax.swing.JPasswordField;
import baglanti.DbConnection;
public class girisEkrani extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel w_pane;
	private JTextField textTCHasta;
	private JTextField tcDoktor;
	private JPasswordField passHasta;
	private JPasswordField passDoktor;
	private DbConnection conH = new DbConnection();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					girisEkrani frame = new girisEkrani();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public girisEkrani() {
		setTitle("e-Hastane Uygulaması");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 509, 468);
		w_pane = new JPanel();
		w_pane.setBackground(new Color(255, 255, 255));
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("e-Hastane Uygulaması");
		lblNewLabel.setFont(new Font("Verdana Pro Cond Semibold", Font.BOLD, 15));
		lblNewLabel.setBounds(250, 22, 161, 40);
		w_pane.add(lblNewLabel);
		
		JLabel logo = new JLabel(new ImageIcon(getClass().getResource("/logolar/hastane5.png")));
		logo.setBounds(10, 0, 230, 148);
		w_pane.add(logo);
		
		JTabbedPane w_Pane = new JTabbedPane(JTabbedPane.TOP);
		w_Pane.setFont(new Font("Verdana", Font.BOLD, 11));
		w_Pane.setBorder(null);
		w_Pane.setBounds(42, 185, 407, 233);
		w_pane.add(w_Pane);
		
		JPanel girishasta_panel = new JPanel();
		girishasta_panel.setBackground(new Color(255, 255, 255));
		w_Pane.addTab("Hasta Girişi", null, girishasta_panel, null);
		girishasta_panel.setLayout(null);
		
		JLabel tc_kimlik_hasta = new JLabel("T.C. Kimlik Numarası :");
		tc_kimlik_hasta.setFont(new Font("Verdana Pro Cond Semibold", Font.BOLD, 14));
		tc_kimlik_hasta.setBounds(10, 36, 146, 27);
		girishasta_panel.add(tc_kimlik_hasta);
		
		textTCHasta = new JTextField();
		textTCHasta.setFont(new Font("Verdana Pro Cond Semibold", Font.PLAIN, 14));
		textTCHasta.setBounds(166, 36, 146, 27);
		girishasta_panel.add(textTCHasta);
		textTCHasta.setColumns(10);
		
		JLabel sifre_hasta = new JLabel("Şifre :");
		sifre_hasta.setFont(new Font("Verdana Pro Cond Semibold", Font.BOLD, 14));
		sifre_hasta.setBounds(10, 105, 146, 27);
		girishasta_panel.add(sifre_hasta);
		
		JButton girisButon = new JButton("Giriş Yap");
		girisButon.setBorderPainted(false);
		girisButon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textTCHasta.getText().length() == 0 || passHasta.getText().length() == 0) {
					YardimMesaji.gosterMesaj("tumdoldur");
				}
				else{ 
					boolean controlHasta = true;
					try {
						Connection cone = conH.conDB();
						Statement st = cone.createStatement();
						ResultSet rs = st.executeQuery("SELECT * FROM user");
						while(rs.next()) {
							if(textTCHasta.getText().equals(rs.getString("TCKimlik")) && passHasta.getText().equals(rs.getString("Password"))) {
								hasta Hasta = new hasta();
								Hasta.setId(rs.getInt("id"));
								Hasta.setTC_kim(rs.getString("TCKimlik"));
								Hasta.setAd(rs.getString("Ad"));
								Hasta.setSoyad(rs.getString("Soyad"));
								Hasta.setParola(rs.getString("Password"));
								Hasta.setType(rs.getString("KullaniciTipi"));
								System.out.println(Hasta.getAd() + Hasta.getSoyad());
								hastaEkrani hGUI = new hastaEkrani(); //Duzelitecek...
								hGUI.setVisible(true);
								dispose();
							}
						}
					}catch(SQLException e1) {
						e1.printStackTrace();
					}
					if(controlHasta) {
						YardimMesaji.gosterMesaj("Böyle bir hasta bulunamadı. Lütfen tekrar kayıt olunuz.");
					}
				}
			}
		});
		girisButon.setBackground(new Color(255, 111, 111));
		girisButon.setBounds(40, 150, 116, 44);
		girishasta_panel.add(girisButon);
		
		
		JButton KaydolButton = new JButton("Kayıt Ol");
		KaydolButton.setBorderPainted(false);
		KaydolButton.setBackground(new Color(81, 255, 190));
		KaydolButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				kayitEkrani kGUI = new kayitEkrani();
				kGUI.setVisible(true);
				dispose();
			}
		});
		KaydolButton.setBounds(230, 150, 116, 44);
		girishasta_panel.add(KaydolButton);
		
		passHasta = new JPasswordField();
		passHasta.setBounds(166, 105, 146, 27);
		girishasta_panel.add(passHasta);
		
		JPanel girisdoktor_panel = new JPanel();
		girisdoktor_panel.setBackground(new Color(255, 255, 255));
		w_Pane.addTab("Doktor/Personel Girişi", null, girisdoktor_panel, null);
		girisdoktor_panel.setLayout(null);
		
		JButton girisButonDoktor = new JButton("Giriş Yap");
		girisButonDoktor.setBorderPainted(false);
		girisButonDoktor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tcDoktor.getText().length() == 0 || passDoktor.getText().length() == 0) {
					YardimMesaji.gosterMesaj("tumdoldur");
				}
				else{
					try {
						Connection cone = conH.conDB();
						Statement st = cone.createStatement();
						ResultSet rs = st.executeQuery("SELECT * FROM user");
						 boolean userFound = false;
						while(rs.next()) {
							if(tcDoktor.getText().equals(rs.getString("TCKimlik")) && passDoktor.getText().equals(rs.getString("Password"))) {
								userFound = true;
								if(rs.getString("KullaniciTipi").equals("personel")){
									personel Persona = new personel();
									Persona.setId(rs.getInt("id"));
									Persona.setTC_kim(rs.getString("TCKimlik"));
									Persona.setAd(rs.getString("Ad"));
									Persona.setSoyad(rs.getString("Soyad"));
									Persona.setParola(rs.getString("Password"));
									Persona.setType(rs.getString("KullaniciTipi"));
									System.out.println(Persona.getAd() + Persona.getSoyad());
									personelEkrani pGUI = new personelEkrani(Persona);
									pGUI.setVisible(true);
									dispose();
								}else if(rs.getString("KullaniciTipi").equals("doktor")){
									doktor Doktor = new doktor();
                                    Doktor.setId(rs.getInt("id"));
                                    Doktor.setTC_kim(rs.getString("TCKimlik"));
                                    Doktor.setAd(rs.getString("Ad"));
                                    Doktor.setSoyad(rs.getString("Soyad"));
                                    Doktor.setParola(rs.getString("Password"));
                                    Doktor.setType(rs.getString("KullaniciTipi"));
                                    System.out.println(Doktor.getAd() + Doktor.getSoyad());
                                    doktorEkrani dGUI = new doktorEkrani(Doktor);
                                    dGUI.setVisible(true);
                                    dispose();
								}
								break;
							}
						}
						if (!userFound) {
		                    YardimMesaji.gosterMesaj("Böyle bir kullanıcı bulunamadı. Lütfen tekrar kontrol edin.");
		                }
					}catch(SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		girisButonDoktor.setBackground(new Color(255, 111, 111));
		girisButonDoktor.setBounds(63, 150, 284, 44);
		girisdoktor_panel.add(girisButonDoktor);
		
		
		
		JLabel tc_kimlik_doktor = new JLabel("T.C. Kimlik Numarası :");
		tc_kimlik_doktor.setFont(new Font("Verdana Pro Cond Semibold", Font.BOLD, 14));
		tc_kimlik_doktor.setBounds(10, 37, 146, 27);
		girisdoktor_panel.add(tc_kimlik_doktor);
		
		tcDoktor = new JTextField();
		tcDoktor.setFont(new Font("Verdana Pro Cond Semibold", Font.PLAIN, 14));
		tcDoktor.setColumns(10);
		tcDoktor.setBounds(166, 37, 146, 27);
		girisdoktor_panel.add(tcDoktor);
		
		JLabel sifre_doktor = new JLabel("Şifre :");
		sifre_doktor.setFont(new Font("Verdana Pro Cond Semibold", Font.BOLD, 14));
		sifre_doktor.setBounds(10, 106, 146, 27);
		girisdoktor_panel.add(sifre_doktor);
		
		passDoktor = new JPasswordField();
		passDoktor.setBounds(166, 106, 146, 27);
		girisdoktor_panel.add(passDoktor);
		
		JLabel hosgeldinizyazisi = new JLabel("Hoş geldiniz");
		hosgeldinizyazisi.setFont(new Font("Verdana Pro Cond Semibold", Font.PLAIN, 13));
		hosgeldinizyazisi.setBounds(42, 148, 118, 26);
		w_pane.add(hosgeldinizyazisi);
	}
}
