package Ekranlar;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import baglanti.YardimMesaji;
import classes.hasta;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JButton;

public class kayitEkrani extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textAd;
	private JTextField textSoyad;
	private JTextField textKimlik;
	private JTextField texParola;
	private hasta Hasta = new hasta();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					kayitEkrani frame = new kayitEkrani();
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
	public kayitEkrani() {
		setTitle("e-Hastane Uygulaması");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 392, 460);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Yeni Kayıt için Lütfen Bilgileri Giriniz");
		lblNewLabel.setForeground(new Color(44, 75, 86));
		lblNewLabel.setFont(new Font("Verdana Pro Cond Semibold", Font.PLAIN, 14));
		lblNewLabel.setBounds(76, 45, 239, 27);
		contentPane.add(lblNewLabel);
		
		JLabel adEtkiet = new JLabel("Ad :");
		adEtkiet.setForeground(new Color(44, 75, 86));
		adEtkiet.setFont(new Font("Verdana Pro Cond Semibold", Font.PLAIN, 14));
		adEtkiet.setBounds(29, 83, 61, 27);
		contentPane.add(adEtkiet);
		
		textAd = new JTextField();
		textAd.setFont(new Font("Verdana Pro Cond Semibold", Font.PLAIN, 13));
		textAd.setBounds(29, 110, 117, 27);
		contentPane.add(textAd);
		textAd.setColumns(10);
		
		JButton KaydolButton = new JButton("Kayıt Ol");
		KaydolButton.setForeground(new Color(44, 75, 86));
		KaydolButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
              if(textAd.getText().length() == 0 || textSoyad.getText().length() == 0 || textKimlik.getText().length() == 0 || texParola.getText().length() == 0) {
            	  YardimMesaji.gosterMesaj("tumdoldur");
              }
              else {
            	  try {
            	  boolean controlet6 = Hasta.register(textAd.getText(),textSoyad.getText(),textKimlik.getText(),texParola.getText());
						if (controlet6) {
							YardimMesaji.gosterMesaj("Basarili");
							girisEkrani login = new girisEkrani();
							login.setVisible(true);
							dispose();
						} else {
							YardimMesaji.gosterMesaj("Basarisiz");
						}
            	  }
					catch (Exception ex) {
						ex.printStackTrace();
					}
              }
			}
		});
		KaydolButton.setFont(new Font("Verdana Pro Cond Semibold", Font.PLAIN, 13));
		KaydolButton.setBorderPainted(false);
		KaydolButton.setBackground(new Color(122, 205, 203));
		KaydolButton.setBounds(49, 289, 286, 44);
		contentPane.add(KaydolButton);
		
		JLabel lblNewLabel_1_1 = new JLabel("Soyad:");
		lblNewLabel_1_1.setForeground(new Color(44, 75, 86));
		lblNewLabel_1_1.setFont(new Font("Verdana Pro Cond Semibold", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(236, 83, 61, 27);
		contentPane.add(lblNewLabel_1_1);
		
		textSoyad = new JTextField();
		textSoyad.setFont(new Font("Verdana Pro Cond Semibold", Font.PLAIN, 13));
		textSoyad.setColumns(10);
		textSoyad.setBounds(236, 110, 117, 27);
		contentPane.add(textSoyad);
		
		JLabel lblNewLabel_1_2 = new JLabel("T.C. Kimlik  Numarası :");
		lblNewLabel_1_2.setForeground(new Color(44, 75, 86));
		lblNewLabel_1_2.setFont(new Font("Verdana Pro Cond Semibold", Font.PLAIN, 14));
		lblNewLabel_1_2.setBounds(29, 148, 164, 27);
		contentPane.add(lblNewLabel_1_2);
		
		textKimlik = new JTextField();
		textKimlik.setFont(new Font("Verdana Pro Cond Semibold", Font.PLAIN, 13));
		textKimlik.setColumns(10);
		textKimlik.setBounds(29, 173, 324, 27);
		contentPane.add(textKimlik);
		
		texParola = new JTextField();
		texParola.setFont(new Font("Verdana Pro Cond Semibold", Font.PLAIN, 13));
		texParola.setColumns(10);
		texParola.setBounds(29, 238, 324, 27);
		contentPane.add(texParola);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Şifre :");
		lblNewLabel_1_2_1.setForeground(new Color(44, 75, 86));
		lblNewLabel_1_2_1.setFont(new Font("Verdana Pro Cond Semibold", Font.PLAIN, 14));
		lblNewLabel_1_2_1.setBounds(29, 211, 164, 27);
		contentPane.add(lblNewLabel_1_2_1);
		
		JButton btnGeriDn = new JButton("Geri Dön");
		btnGeriDn.setForeground(new Color(255, 255, 255));
		btnGeriDn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                girisEkrani login = new girisEkrani();
                login.setVisible(true);
                dispose();
            }
		});
		btnGeriDn.setFont(new Font("Verdana Pro Cond Semibold", Font.PLAIN, 13));
		btnGeriDn.setBorderPainted(false);
		btnGeriDn.setBackground(new Color(128, 128, 128));
		btnGeriDn.setBounds(49, 344, 286, 44);
		contentPane.add(btnGeriDn);
	}
}
