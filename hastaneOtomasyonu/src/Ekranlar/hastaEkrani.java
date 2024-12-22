package Ekranlar;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import baglanti.Item;
import baglanti.YardimMesaji;
import classes.Calsaat;
import classes.doktor;
import classes.hasta;
import classes.poliklinik;
import classes.randevu;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.JTabbedPane;
import javax.swing.JDesktopPane;
import javax.swing.JLayeredPane;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JTable;

public class hastaEkrani extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	static hasta Hasta = new hasta();
	
	
	private poliklinik Poliklinik = new poliklinik();
	private doktor Doktor = new doktor();
	private DefaultTableModel doctorModel;
	private Object[] doctorData = null;
	private Calsaat calsaat = new Calsaat();
	private DefaultTableModel saatModel;
	private Object[] saatData = null;
	private JTable tableDocAd;
	private JTable tableDocSaat;
	private int selectDoctorID = 0;
	private String selectDoctorAd = null;
	private String selectDoctorSoyad = null;
	static randevu Randevu = new randevu();
	private DefaultTableModel randevuModel;
	private Object[] randevuData = null;
	private JTable table_ran;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					hastaEkrani frame = new hastaEkrani(Hasta);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public hastaEkrani(hasta Hasta) throws SQLException {
		doctorModel = new DefaultTableModel();
		Object[] colDoctor = new Object[3];
		colDoctor[0] = "ID";
		colDoctor[1] = "Ad";
		colDoctor[2] = "Soyad";
		doctorModel.setColumnIdentifiers(colDoctor);
		doctorData = new Object[3];
		
		saatModel = new DefaultTableModel();
		Object[] colSaat = new Object[2];
		colSaat[0] = "ID";
		colSaat[1] = "Tarih";
		saatModel.setColumnIdentifiers(colSaat);
		saatData = new Object[2];
		
        randevuModel = new DefaultTableModel();
        Object[] colRan = new Object[3];
        colRan[0] = "ID";
        colRan[1] = "Doktor";
        colRan[2] = "Tarih";
        randevuModel.setColumnIdentifiers(colRan);
        randevuData = new Object[3];
        List<randevu> randevuList = Randevu.getHastaRanList(Hasta.getId());
        for (int i = 0; i < randevuList.size(); i++) {
            Object[] randevuData = new Object[3];
            randevuData[0] = randevuList.get(i).getId();
            randevuData[1] = randevuList.get(i).getDoktorAd() + " " + randevuList.get(i).getDoktorSoyad();
            randevuData[2] = randevuList.get(i).getTarih();
            randevuModel.addRow(randevuData);
        }


		
		setTitle("e-Hastane Uygulaması");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 692, 515);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Hoşgeldiniz, Sayın "+Hasta.getAd() + " " + Hasta.getSoyad());
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Verdana Pro Cond Semibold", Font.PLAIN, 14));
		lblNewLabel.setBounds(21, 434, 295, 21);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Çıkış Yap");
		btnNewButton.setFont(new Font("Verdana Pro Cond Semibold", Font.PLAIN, 11));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				girisEkrani login = new girisEkrani();
				login.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBorderPainted(false);
		btnNewButton.setBackground(new Color(255, 0, 0));
		btnNewButton.setBounds(557, 428, 109, 37);
		contentPane.add(btnNewButton);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Verdana Pro Cond Semibold", Font.PLAIN, 11));
		tabbedPane.setBounds(21, 11, 632, 406);
		contentPane.add(tabbedPane);
		
		JPanel getApoint_pane = new JPanel();
		getApoint_pane.setBackground(new Color(255, 255, 255));
		tabbedPane.addTab("Randevu Al", null, getApoint_pane, null);
		getApoint_pane.setLayout(null);
		
		JScrollPane scrollDocPane = new JScrollPane();
		scrollDocPane.setBounds(10, 28, 237, 339);
		getApoint_pane.add(scrollDocPane);
		
		tableDocAd = new JTable(doctorModel);
		scrollDocPane.setViewportView(tableDocAd);
		
		JLabel lblNewLabel_1 = new JLabel("Doktor Listesi");
		lblNewLabel_1.setFont(new Font("Verdana Pro Cond Semibold", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(10, 11, 91, 16);
		getApoint_pane.add(lblNewLabel_1);
		
		JScrollPane scrollSaatPane = new JScrollPane();
		scrollSaatPane.setBounds(257, 28, 232, 339);
		getApoint_pane.add(scrollSaatPane);
		
		tableDocSaat = new JTable(saatModel);
		tableDocSaat.getColumnModel().getColumn(0).setPreferredWidth(5);
		scrollSaatPane.setViewportView(tableDocSaat);
		
		
		JLabel lblNewLabel_1_1 = new JLabel("Saat Listesi");
		lblNewLabel_1_1.setFont(new Font("Verdana Pro Cond Semibold", Font.PLAIN, 13));
		lblNewLabel_1_1.setBounds(257, 11, 118, 16);
		getApoint_pane.add(lblNewLabel_1_1);
		
		JComboBox comboPoliBox = new JComboBox();
		comboPoliBox.setFont(new Font("Verdana Pro Cond Semibold", Font.PLAIN, 11));
		comboPoliBox.addItem("Poliklinik Seçiniz");
		for (int i = 0; i < Poliklinik.getPoliList().size(); i++) {
			comboPoliBox.addItem(new Item(Poliklinik.getPoliList().get(i).getId_klinik(), Poliklinik.getPoliList().get(i).getKlinik_ad()));
		}
		comboPoliBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
			if(comboPoliBox.getSelectedIndex() != 0) {
				JComboBox com = (JComboBox) e.getSource();
				Item item = (Item) com.getSelectedItem();
				//System.out.println(item.getKey() + " - " + item.getValue());
				DefaultTableModel clearModel = (DefaultTableModel)  tableDocAd.getModel();
				clearModel.setRowCount(0);
				try {
					for(int i = 0; i < Poliklinik.getClinicDoctorList(item.getKey()).size(); i++) {
						doctorData[0] = Poliklinik.getClinicDoctorList(item.getKey()).get(i).getId();
					    doctorData[1] = Poliklinik.getClinicDoctorList(item.getKey()).get(i).getAd();
					    doctorData[2] = Poliklinik.getClinicDoctorList(item.getKey()).get(i).getSoyad();
					    doctorModel.addRow(doctorData);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else {
				DefaultTableModel clearModel = (DefaultTableModel)  tableDocAd.getModel();
                clearModel.setRowCount(0);
			}
			}
	});
		comboPoliBox.setBounds(499, 53, 118, 22);
		getApoint_pane.add(comboPoliBox);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Poliklinik Adı:");
		lblNewLabel_1_1_1.setFont(new Font("Verdana Pro Cond Semibold", Font.PLAIN, 13));
		lblNewLabel_1_1_1.setBounds(499, 26, 118, 16);
		getApoint_pane.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Doktor Seç:");
		lblNewLabel_1_1_1_1.setFont(new Font("Verdana Pro Cond Semibold", Font.PLAIN, 13));
		lblNewLabel_1_1_1_1.setBounds(499, 153, 118, 16);
		getApoint_pane.add(lblNewLabel_1_1_1_1);
		
		JButton ButonSelDoc = new JButton("Seç");
		ButonSelDoc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = tableDocAd.getSelectedRow();
				if(row >= 0) {
					String value = tableDocAd.getModel().getValueAt(row, 0).toString();
					int id = Integer.parseInt(value);
					DefaultTableModel clearModel = (DefaultTableModel)  tableDocSaat.getModel();
					clearModel.setRowCount(0);
					try {
						for(int i = 0; i < calsaat.getCalSaatList(id).size(); i++) {
						    saatData[0] = calsaat.getCalSaatList(id).get(i).getId();
						    saatData[1] = calsaat.getCalSaatList(id).get(i).getDate();
						    saatModel.addRow(saatData);
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					tableDocSaat.setModel(saatModel);
					selectDoctorID = id;
					selectDoctorAd = tableDocAd.getModel().getValueAt(row, 1).toString();
					selectDoctorSoyad = tableDocAd.getModel().getValueAt(row, 2).toString();
					System.out.println(selectDoctorID + " - " + selectDoctorAd + " - " + selectDoctorSoyad);
			}
				else {
					YardimMesaji.gosterMesaj("Lütfen bir doktor seçiniz!");
				}
		}
		});
		ButonSelDoc.setBorderPainted(false);
		ButonSelDoc.setBackground(new Color(7, 164, 248));
		ButonSelDoc.setBounds(499, 180, 118, 23);
		getApoint_pane.add(ButonSelDoc);
		
		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Randevu Al:");
		lblNewLabel_1_1_1_1_1.setFont(new Font("Verdana Pro Cond Semibold", Font.PLAIN, 13));
		lblNewLabel_1_1_1_1_1.setBounds(499, 295, 118, 16);
		getApoint_pane.add(lblNewLabel_1_1_1_1_1);
		
		JButton ButonAlRan = new JButton("Al");
		ButonAlRan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow = tableDocSaat.getSelectedRow();
				if(selRow >= 0) {
					String date = tableDocSaat.getModel().getValueAt(selRow, 1).toString();
					try {
						boolean controlet7 = Hasta.alRandevu(selectDoctorID, Hasta.getId(),
								selectDoctorAd, selectDoctorSoyad, Hasta.getAd(), Hasta.getSoyad(), date);
						if (controlet7) {
							YardimMesaji.gosterMesaj("Randevu alındı.");
							Hasta.guncelleStatus(selectDoctorID, date);
							guncelSaatModel(selectDoctorID);
							guncelRanModel(Hasta.getId());
						} else {
							YardimMesaji.gosterMesaj("Randevu alınamadı.");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
				else {
					YardimMesaji.gosterMesaj("Lütfen geçerli bir tarih seçiniz!");
				}

			}
		});
		ButonAlRan.setBorderPainted(false);
		ButonAlRan.setBackground(new Color(7, 164, 248));
		ButonAlRan.setBounds(499, 321, 118, 23);
		getApoint_pane.add(ButonAlRan);

		
		JPanel myApoint_pane = new JPanel();
		tabbedPane.addTab("Randevularım", null, myApoint_pane, null);
		myApoint_pane.setLayout(null);
		
		JScrollPane RandevuMenu = new JScrollPane();
		RandevuMenu.setBounds(10, 54, 607, 313);
		myApoint_pane.add(RandevuMenu);
		
		table_ran = new JTable(randevuModel);
		table_ran.setFont(new Font("Verdana Pro Cond Semibold", Font.PLAIN, 12));
		RandevuMenu.setViewportView(table_ran);
		
		JButton btnSilRan = new JButton("Sil");
		btnSilRan.setFont(new Font("Verdana Pro Cond Semibold", Font.PLAIN, 13));
		btnSilRan.setBorderPainted(false);
		btnSilRan.setBackground(new Color(10, 139, 245));
		btnSilRan.setBounds(494, 15, 123, 28);
		myApoint_pane.add(btnSilRan);
	}
	public void guncelSaatModel(int doctor_id) throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel)  tableDocSaat.getModel();
		clearModel.setRowCount(0);
		for(int i = 0; i < calsaat.getCalSaatList(doctor_id).size(); i++) {
		    saatData[0] = calsaat.getCalSaatList(doctor_id).get(i).getId();
		    saatData[1] = calsaat.getCalSaatList(doctor_id).get(i).getDate();
		    saatModel.addRow(saatData);
		}
	}
	public void guncelRanModel(int hasta_id) throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel)  table_ran.getModel();
		clearModel.setRowCount(0);
		try{
			for (int i = 0; i < Randevu.getHastaRanList(hasta_id).size(); i++) {
			Object[] randevuData = new Object[3];
			randevuData[0] = Randevu.getHastaRanList(hasta_id).get(i).getId();
			randevuData[1] = Randevu.getHastaRanList(hasta_id).get(i).getDoktorAd() + " " + Randevu.getHastaRanList(hasta_id).get(i).getDoktorSoyad();
			randevuData[2] = Randevu.getHastaRanList(hasta_id).get(i).getTarih();
			randevuModel.addRow(randevuData);
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	}
}
