package Ekranlar;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JToolBar;
import javax.swing.JTabbedPane;
import javax.swing.JDesktopPane;
import javax.swing.JLayeredPane;

public class hastaEkrani extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					hastaEkrani frame = new hastaEkrani();
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
	public hastaEkrani() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 692, 515);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Hoşgeldiniz");
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblNewLabel.setBounds(21, 434, 151, 21);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Çıkış Yap");
		btnNewButton.setBackground(new Color(255, 0, 0));
		btnNewButton.setBounds(557, 428, 109, 37);
		contentPane.add(btnNewButton);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(21, 11, 632, 406);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Randevu Al", null, panel, null);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Randevularım", null, panel_1, null);
	}
}
