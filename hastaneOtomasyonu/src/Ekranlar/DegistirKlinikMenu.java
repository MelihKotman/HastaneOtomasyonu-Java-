package Ekranlar;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import baglanti.YardimMesaji;
import classes.poliklinik;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;

public class DegistirKlinikMenu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textDegistir;
	private static poliklinik Clinic;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DegistirKlinikMenu frame = new DegistirKlinikMenu(Clinic);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public DegistirKlinikMenu(poliklinik Clinic) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 224, 129);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Poliklinik Adı :");
		lblNewLabel.setFont(new Font("Verdana Pro Cond Semibold", Font.PLAIN, 13));
		lblNewLabel.setBounds(61, 11, 84, 14);
		contentPane.add(lblNewLabel);
		
		textDegistir = new JTextField();
		textDegistir.setBounds(10, 36, 189, 20);
		contentPane.add(textDegistir);
		textDegistir.setText(Clinic.getKlinik_ad());
		textDegistir.setColumns(10);
		
		JButton butonDegistir = new JButton("Değiştir");
		butonDegistir.setBorderPainted(false);
		butonDegistir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				Clinic.guncelleClinic(Clinic.getId_klinik(), textDegistir.getText());
				YardimMesaji.gosterMesaj("Basarili");
				dispose();}
				catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		butonDegistir.setBackground(new Color(44, 175, 239));
		butonDegistir.setBounds(61, 67, 89, 23);
		contentPane.add(butonDegistir);
	}

}
