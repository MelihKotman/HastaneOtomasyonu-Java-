package Ekranlar;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

public class kayitEkrani extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 476);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Yeni Kayıt için Lütfen Bilgileri Giriniz");
		lblNewLabel.setFont(new Font("Verdana Pro Cond Semibold", Font.PLAIN, 13));
		lblNewLabel.setBounds(104, 11, 220, 27);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Ad :");
		lblNewLabel_1.setFont(new Font("Verdana Pro Cond Semibold", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(10, 86, 61, 27);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Soyad :");
		lblNewLabel_1_1.setFont(new Font("Verdana Pro Cond Semibold", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(10, 138, 61, 27);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("T.C. Kimlik Numarası  :");
		lblNewLabel_1_2.setFont(new Font("Verdana Pro Cond Semibold", Font.PLAIN, 14));
		lblNewLabel_1_2.setBounds(10, 199, 147, 27);
		contentPane.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Şifre :");
		lblNewLabel_1_3.setFont(new Font("Verdana Pro Cond Semibold", Font.PLAIN, 14));
		lblNewLabel_1_3.setBounds(10, 256, 61, 27);
		contentPane.add(lblNewLabel_1_3);
		
		textField = new JTextField();
		textField.setBounds(177, 91, 147, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(177, 143, 147, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(177, 204, 147, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(177, 261, 147, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
	}
}
