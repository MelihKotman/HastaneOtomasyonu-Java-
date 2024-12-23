package Ekranlar;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import classes.personel;
import classes.poliklinik;

import javax.swing.JTabbedPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Point;
import java.sql.SQLException;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

import baglanti.Item;
import baglanti.YardimMesaji;
import javax.swing.JComboBox;
public class personelEkrani extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	static poliklinik Clinic = new poliklinik();
	static personel Personel = new personel(); //mainden bagimsiz bir nesne olacagi icin eger bagimli olursa sıkıntı cikarabilir
	private JTextField textyeniDocAd;
	private JTextField textyeniDocSoy;
	private JTextField textyeniDocPass;
	private JTextField textyeniDocTc;
	private JTextField textyeniDocID;
	private DefaultTableModel clinicListele = null;
	private DefaultTableModel doctorListele = null;
	private Object[] clinicDatala = null;
	private Object[] doctorDatala = null;
	private JTable Doktorlistesi;
	private JTable poliklinikListesi;
	private JTextField textPolCli;
	private JPopupMenu clinicMenu;
	private JTable table_calisan;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					personelEkrani frame = new personelEkrani(Personel);
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
	public personelEkrani(personel Personel) throws SQLException {
		
		
		doctorListele = new DefaultTableModel();
		Object[] colDoctorName = new Object[5];
		colDoctorName[0] = "ID";
		colDoctorName[1] = "TC Kimlik";
		colDoctorName[2] = "Ad";
		colDoctorName[3] = "Soyad";
		colDoctorName[4] = "Parola";
		doctorListele.setColumnIdentifiers(colDoctorName);
		doctorDatala = new Object[5];
		for(int i = 0; i < Personel.getDoctorList().size(); i++) {
			doctorDatala[0] = Personel.getDoctorList().get(i).getId();
			doctorDatala[1] = Personel.getDoctorList().get(i).getTC_kim();
			doctorDatala[2] = Personel.getDoctorList().get(i).getAd();
			doctorDatala[3] = Personel.getDoctorList().get(i).getSoyad();
			doctorDatala[4] = Personel.getDoctorList().get(i).getParola();
			doctorListele.addRow(doctorDatala);
		}
		
		clinicListele = new DefaultTableModel();
		Object[] colClinic = new Object[2];
		colClinic[0] = "Poliklinik Numarası";
		colClinic[1] = "Poliklinik Adı";
		clinicListele.setColumnIdentifiers(colClinic);
		clinicDatala = new Object[2];
		for(int i = 0; i < Clinic.getPoliList().size(); i++) {
			clinicDatala[0] = Clinic.getPoliList().get(i).getId_klinik();
			clinicDatala[1] = Clinic.getPoliList().get(i).getKlinik_ad();
			clinicListele.addRow(clinicDatala);
		}
		
		DefaultTableModel calisanListele = new DefaultTableModel();
		Object[] colCalisan = new Object[2];
		colCalisan[0] = "Çalışan ID";
		colCalisan[1] = "Ad Soyad";
		calisanListele.setColumnIdentifiers(colCalisan);
		Object[] calisanDatala = new Object[2];
		
		setTitle("e-Hastane Uygulaması");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 865, 538);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton cikisbutonu = new JButton("Çıkış Yap");
		cikisbutonu.setForeground(new Color(255, 255, 255));
		cikisbutonu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				girisEkrani login = new girisEkrani();
				login.setVisible(true);
				dispose();
			}
		});
		cikisbutonu.setBorderPainted(false);
		cikisbutonu.setBounds(686, 11, 138, 33);
		cikisbutonu.setBackground(new Color(128, 128, 128));
		cikisbutonu.setFont(new Font("Verdana Pro Cond Semibold", Font.PLAIN, 13));
		contentPane.add(cikisbutonu);
		
		JLabel personeletiket = new JLabel("Hoş Geldiniz, " + Personel.getAd() + " " + Personel.getSoyad());
		personeletiket.setBounds(23, 11, 255, 39);
		personeletiket.setFont(new Font("Verdana Pro Cond Semibold", Font.ITALIC, 14));
		contentPane.add(personeletiket);
		
		JTabbedPane anaPane = new JTabbedPane(JTabbedPane.TOP);
		anaPane.setFont(new Font("Verdana Pro Cond Semibold", Font.PLAIN, 12));
		anaPane.setBounds(22, 57, 802, 403);
		contentPane.add(anaPane);
		
		JPanel doktoryonetim = new JPanel();
		doktoryonetim.setBackground(new Color(255, 255, 255));
		anaPane.addTab("Yeni Doktor Ekle/Çıkar", null, doktoryonetim, null);
		doktoryonetim.setLayout(null);
		
		JLabel yeniDokAd = new JLabel("Doktor Adı :");
		yeniDokAd.setFont(new Font("Verdana Pro Cond Semibold", Font.BOLD, 13));
		yeniDokAd.setBounds(616, 11, 91, 27);
		doktoryonetim.add(yeniDokAd);
		
		textyeniDocAd = new JTextField();
		textyeniDocAd.setFont(new Font("Verdana Pro Cond Semibold", Font.PLAIN, 12));
		textyeniDocAd.setBounds(616, 38, 146, 20);
		doktoryonetim.add(textyeniDocAd);
		textyeniDocAd.setColumns(10);
		
		JLabel yeniDokSoy = new JLabel("Doktor Soyadı :");
		yeniDokSoy.setFont(new Font("Verdana Pro Cond Semibold", Font.PLAIN, 13));
		yeniDokSoy.setBounds(616, 69, 91, 14);
		doktoryonetim.add(yeniDokSoy);
		
		textyeniDocSoy = new JTextField();
		textyeniDocSoy.setFont(new Font("Verdana Pro Cond Semibold", Font.PLAIN, 12));
		textyeniDocSoy.setBounds(616, 86, 146, 20);
		doktoryonetim.add(textyeniDocSoy);
		textyeniDocSoy.setColumns(10);
		
		JLabel yeniDokPass = new JLabel("Şifre :");
		yeniDokPass.setFont(new Font("Verdana Pro Cond Semibold", Font.PLAIN, 13));
		yeniDokPass.setBounds(616, 117, 46, 20);
		doktoryonetim.add(yeniDokPass);
		
		textyeniDocPass = new JTextField();
		textyeniDocPass.setFont(new Font("Verdana Pro Cond Semibold", Font.PLAIN, 12));
		textyeniDocPass.setBounds(616, 142, 146, 20);
		doktoryonetim.add(textyeniDocPass);
		textyeniDocPass.setColumns(10);
		
		JLabel yeniDokTc = new JLabel("T.C. Kimlik Numarası :");
		yeniDokTc.setFont(new Font("Verdana Pro Cond Semibold", Font.PLAIN, 13));
		yeniDokTc.setBounds(616, 173, 146, 14);
		doktoryonetim.add(yeniDokTc);
		
		textyeniDocTc = new JTextField();
		textyeniDocTc.setFont(new Font("Verdana Pro Cond Semibold", Font.PLAIN, 12));
		textyeniDocTc.setBounds(616, 198, 146, 20);
		doktoryonetim.add(textyeniDocTc);
		textyeniDocTc.setColumns(10);
		

		
		JButton KaydetButton = new JButton("Kaydet");
		KaydetButton.setForeground(new Color(45, 75, 86));
		KaydetButton.setFont(new Font("Verdana Pro Cond Semibold", Font.PLAIN, 13));
		KaydetButton.setBorderPainted(false);
		KaydetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textyeniDocAd.getText().length() == 0 || textyeniDocSoy.getText().length() == 0 || textyeniDocPass.getText().length() == 0 || textyeniDocTc.getText().length() == 0) {
					YardimMesaji.gosterMesaj("tumdoldur");
				}
				else {
					try{boolean controlet = Personel.ekleDoktor(textyeniDocTc.getText(),textyeniDocAd.getText() ,textyeniDocSoy.getText(),textyeniDocPass.getText());
					if(controlet) {
						YardimMesaji.gosterMesaj("Basarili");
						textyeniDocAd.setText(null);
						textyeniDocSoy.setText(null);
						textyeniDocTc.setText(null);
						textyeniDocPass.setText(null);
						guncelDoktorListe();

					}
					}catch(SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		KaydetButton.setBackground(new Color(122, 205, 203));
		KaydetButton.setBounds(616, 229, 146, 23);
		doktoryonetim.add(KaydetButton);
		
		JLabel DokSilID = new JLabel("Kullanıcı ID Numarası :");
		DokSilID.setFont(new Font("Verdana Pro Cond Semibold", Font.PLAIN, 13));
		DokSilID.setBounds(616, 263, 146, 14);
		doktoryonetim.add(DokSilID);
		
		textyeniDocID = new JTextField();
		textyeniDocID.setFont(new Font("Verdana Pro Cond Semibold", Font.PLAIN, 12));
		textyeniDocID.setBounds(616, 288, 146, 20);
		doktoryonetim.add(textyeniDocID);
		textyeniDocID.setColumns(10);
		
		JButton SilButton = new JButton("Sil");
		SilButton.setForeground(new Color(255, 255, 255));
		SilButton.setFont(new Font("Verdana Pro Cond Semibold", Font.PLAIN, 13));
		SilButton.setBorderPainted(false);
		SilButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		if(textyeniDocID.getText().length() == 0) {
			YardimMesaji.gosterMesaj("Lutfen geçerli bir doktor seçiniz!");
		}
		else {
			int seciliID = Integer.parseInt(textyeniDocID.getText()); //sectigimiz id'yi alan
			try{
				boolean controlet2 = Personel.cikarDoktor(seciliID); // buradanda sql'de silmeyi sagalayan fonksiyon
				if(controlet2) {
					YardimMesaji.gosterMesaj("Basarili");
					textyeniDocID.setText(null);
					guncelDoktorListe();

				}
			}
			catch(SQLException e1){
				e1.printStackTrace();
					}
				}
			}
		});
		SilButton.setBackground(new Color(0, 64, 64));
		SilButton.setBounds(614, 319, 148, 23);
		doktoryonetim.add(SilButton);
		
		JPanel doktorluPanel = new JPanel();
		doktorluPanel.setBackground(new Color(247, 247, 247));
		doktorluPanel.setBounds(589, 11, 198, 353);
		doktoryonetim.add(doktorluPanel);
		
		JScrollPane scrollPane_liste = new JScrollPane();
		scrollPane_liste.setBounds(10, 11, 569, 353);
		doktoryonetim.add(scrollPane_liste);
		
		Doktorlistesi = new JTable(doctorListele);
		Doktorlistesi.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				try{//koyma sebebim sildikten sonra hata almamak icin try catch'e koydum ve o hatayi alinca bir mesaj vermeyecek bana...
					textyeniDocID.setText(Doktorlistesi.getValueAt(Doktorlistesi.getSelectedRow(), 0).toString());
				}catch(Exception ex) {
					
				}
				//Burada tikladigimiz satirin id'sini textyeniDocID'de gosterecek	
				}
			});
		Doktorlistesi.getModel().addTableModelListener(new TableModelListener() {
			
			public void tableChanged(TableModelEvent e) {
				if(e.getType() == TableModelEvent.UPDATE) {
					int seciliID2 = Integer.parseInt(Doktorlistesi.getValueAt(Doktorlistesi.getSelectedRow(), 0).toString());
					String seciliTC = Doktorlistesi.getValueAt(Doktorlistesi.getSelectedRow(), 1).toString();
					String seciliAD = Doktorlistesi.getValueAt(Doktorlistesi.getSelectedRow(), 2).toString();
					String seciliSoy = Doktorlistesi.getValueAt(Doktorlistesi.getSelectedRow(), 3).toString();
					String seciliPass = Doktorlistesi.getValueAt(Doktorlistesi.getSelectedRow(), 4).toString();
				
				try{
					boolean controlet2 = Personel.guncelleDoktor(seciliID2,seciliTC,seciliAD,seciliSoy,seciliPass); // buradanda sql'de silmeyi sagalayan fonksiyon
					if(controlet2) {
						YardimMesaji.gosterMesaj("Basarili");
						textyeniDocID.setText(null);
						guncelDoktorListe();
					}
				}
				catch(SQLException e1){
					e1.printStackTrace();
						}
					}
			}
		});
		Doktorlistesi.setBackground(new Color(255, 255, 255));
		Doktorlistesi.setFont(new Font("Verdana Pro Cond Semibold", Font.PLAIN, 11));
		scrollPane_liste.setViewportView(Doktorlistesi);
		
		JPanel poliyonetim = new JPanel();
		poliyonetim.setBackground(new Color(255, 255, 255));
		anaPane.addTab("Poliklinik Yönetimi", null, poliyonetim, null);
		poliyonetim.setLayout(null);
		
		JScrollPane poliklinik_pane = new JScrollPane();
		poliklinik_pane.setBounds(10, 11, 307, 353);
		poliyonetim.add(poliklinik_pane);
		
		JPanel panel = new JPanel();
		panel.setBounds(598, 11, 189, 353);
		poliyonetim.add(panel);
		panel.setLayout(null);
		
		JLabel lblPoliklinikAd = new JLabel("Poliklinik Adı:");
		lblPoliklinikAd.setBounds(52, 11, 80, 17);
		panel.add(lblPoliklinikAd);
		lblPoliklinikAd.setFont(new Font("Verdana Pro Cond Semibold", Font.BOLD, 13));
		
		textPolCli = new JTextField();
		textPolCli.setBounds(21, 33, 146, 20);
		panel.add(textPolCli);
		textPolCli.setFont(new Font("Verdana Pro Cond Semibold", Font.PLAIN, 12));
		textPolCli.setColumns(10);
		
		clinicMenu = new JPopupMenu();
		JMenuItem updateMenu = new JMenuItem("Değiştir");
		JMenuItem deleteMenu = new JMenuItem("Sil");
		clinicMenu.add(updateMenu);
		clinicMenu.add(deleteMenu);
		updateMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int seciliID3 = Integer.parseInt(poliklinikListesi.getValueAt(poliklinikListesi.getSelectedRow(), 0).toString());
				poliklinik seciliClinic = Clinic.getFetch(seciliID3);
				DegistirKlinikMenu degistir = new DegistirKlinikMenu(seciliClinic);
				degistir.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				degistir.setVisible(true);
				degistir.addWindowListener(new WindowAdapter(){
					public void windowClosed(WindowEvent e) {
					try {
						guncelKlinikListe();
					}
					catch(SQLException e1) {
						e1.printStackTrace();
					}
				}
			});
		}	
	});
	deleteMenu.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			int seciliID4 = Integer.parseInt(poliklinikListesi.getValueAt(poliklinikListesi.getSelectedRow(), 0).toString());
			try {
				boolean controlet3 = Clinic.cikarClinic(seciliID4);
				if (controlet3) {
					YardimMesaji.gosterMesaj("Basarili");
					guncelKlinikListe();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	});
		poliklinikListesi = new JTable(clinicListele);
		poliklinikListesi.setComponentPopupMenu(clinicMenu);
		poliklinikListesi.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				Point point = e.getPoint();
				int secilisira = poliklinikListesi.rowAtPoint(point);
				poliklinikListesi.setRowSelectionInterval(secilisira, secilisira);
			}
		});	
		poliklinikListesi.setFont(new Font("Verdana Pro Cond Semibold", Font.PLAIN, 11));
		poliklinik_pane.setViewportView(poliklinikListesi);
		
		JButton ButonClinic = new JButton("Kaydet");
		ButonClinic.setForeground(new Color(45, 75, 86));
		ButonClinic.setFont(new Font("Verdana Pro Cond Semibold", Font.PLAIN, 13));
		ButonClinic.setBorderPainted(false);
		ButonClinic.setBounds(21, 64, 146, 23);
		panel.add(ButonClinic);
		ButonClinic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textPolCli.getText().length() == 0) {
					YardimMesaji.gosterMesaj("tumdoldur");
				}
				else {
					try {
						if(Clinic.ekleClinic(textPolCli.getText())) {
							YardimMesaji.gosterMesaj("Basarili");
							textPolCli.setText(null);
							guncelKlinikListe();
						}
					}catch(SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		
		JComboBox secComboBox = new JComboBox();
		secComboBox.setFont(new Font("Verdana Pro Cond Semibold", Font.PLAIN, 11));
		for (int i = 0; i < Personel.getDoctorList().size(); i++) {
			personel p = new personel();
			secComboBox.addItem(new Item(p.getDoctorList().get(i).getId(),p.getDoctorList().get(i).getAd()));
		}
		secComboBox.setBounds(21, 152, 146, 22);
		panel.add(secComboBox);



		
		
		ButonClinic.setBackground(new Color(122, 205, 203));
		JButton ButonWorker = new JButton("Ekle");
		ButonWorker.setForeground(new Color(45, 75, 86));
		ButonWorker.setFont(new Font("Verdana Pro Cond Semibold", Font.PLAIN, 13));
		ButonWorker.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int selRow = poliklinikListesi.getSelectedRow();
					int selDoc = ((Item)secComboBox.getSelectedItem()).getKey();
					if(selRow >= 0) {
						String selClinic = poliklinikListesi.getModel().getValueAt(selRow, 0).toString();
						int selClinicID = Integer.parseInt(selClinic);
						Item itemDoc = (Item)secComboBox.getSelectedItem();
						boolean controlet4 = Personel.ekleCalisan(itemDoc.getKey(),selClinicID);
						try {
							if(controlet4){
								YardimMesaji.gosterMesaj("Basarili");
		                        DefaultTableModel calisanModel = (DefaultTableModel) table_calisan.getModel();
		                        calisanModel.setRowCount(0);
		        
									for(int i = 0; i < Personel.getClinicDoctorList(selClinicID).size(); i++) {
									    calisanDatala[0] = Personel.getClinicDoctorList(selClinicID).get(i).getId();
									    calisanDatala[1] = Personel.getClinicDoctorList(selClinicID).get(i).getAd() + " " + Personel.getClinicDoctorList(selClinicID).get(i).getSoyad();
									    calisanModel.addRow(calisanDatala);
									}
								}
							
							else {
								YardimMesaji.gosterMesaj("Basarisiz");
							}
						}
					 catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						}
					else {
							YardimMesaji.gosterMesaj("Lutfen bir poliklinik seçiniz!");
						}
					}
				});
		ButonWorker.setBorderPainted(false);
		ButonWorker.setBackground(new Color(122, 205, 203));
		ButonWorker.setBounds(21, 185, 146, 23);
		panel.add(ButonWorker);
		
		JButton butonSecCLi = new JButton("Seç");
		butonSecCLi.setForeground(new Color(45, 75, 86));
		butonSecCLi.setFont(new Font("Verdana Pro Cond Semibold", Font.PLAIN, 13));
		butonSecCLi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow = poliklinikListesi.getSelectedRow();
				if(selRow >= 0) {
                    String selClinic = poliklinikListesi.getModel().getValueAt(selRow, 0).toString();
                    int selClinicID = Integer.parseInt(selClinic);
                    DefaultTableModel temizleModel = (DefaultTableModel) table_calisan.getModel();
                    temizleModel.setRowCount(0);
                    try {
                        DefaultTableModel calisanModel = (DefaultTableModel) table_calisan.getModel();
                        calisanModel.setRowCount(0);
                        for(int i = 0; i < Personel.getClinicDoctorList(selClinicID).size(); i++) {
                            calisanDatala[0] = Personel.getClinicDoctorList(selClinicID).get(i).getId();
                            calisanDatala[1] = Personel.getClinicDoctorList(selClinicID).get(i).getAd() + " " + Personel.getClinicDoctorList(selClinicID).get(i).getSoyad();
                            calisanModel.addRow(calisanDatala);
                        }
                        table_calisan.setModel(calisanListele);
                    }catch(SQLException e1) {
                        e1.printStackTrace();
                    }
                    table_calisan.setModel(calisanListele);
                }
                else {
                    YardimMesaji.gosterMesaj("Lutfen bir poliklinik seçiniz!");
			}
			}
	});
		butonSecCLi.setBorderPainted(false);
		butonSecCLi.setBackground(new Color(122, 205, 203));
		butonSecCLi.setBounds(21, 297, 146, 23);
		panel.add(butonSecCLi);
		
		JLabel lblPoliklinikAd_1 = new JLabel("Poliklinik Adı:");
		lblPoliklinikAd_1.setFont(new Font("Verdana Pro Cond Semibold", Font.BOLD, 13));
		lblPoliklinikAd_1.setBounds(52, 269, 80, 17);
		panel.add(lblPoliklinikAd_1);
		
		JLabel lblDoktorAd = new JLabel("Doktor Adı:");
		lblDoktorAd.setFont(new Font("Verdana Pro Cond Semibold", Font.BOLD, 13));
		lblDoktorAd.setBounds(52, 123, 80, 17);
		panel.add(lblDoktorAd);
		

		JScrollPane calisan_pane = new JScrollPane();
		calisan_pane.setBounds(327, 11, 260, 353);
		poliyonetim.add(calisan_pane);
		
		table_calisan = new JTable();
		table_calisan.setFont(new Font("Verdana Pro Cond Semibold", Font.PLAIN, 11));
		calisan_pane.setViewportView(table_calisan);
		
		}

	public void guncelDoktorListe() throws SQLException {
		DefaultTableModel temizleModel= (DefaultTableModel) Doktorlistesi.getModel();
		temizleModel.setRowCount(0);
		for(int i = 0; i < Personel.getDoctorList().size(); i++) {
			doctorDatala[0] = Personel.getDoctorList().get(i).getId();
			doctorDatala[1] = Personel.getDoctorList().get(i).getTC_kim();
			doctorDatala[2] = Personel.getDoctorList().get(i).getAd();
			doctorDatala[3] = Personel.getDoctorList().get(i).getSoyad();
			doctorDatala[4] = Personel.getDoctorList().get(i).getParola();
			doctorListele.addRow(doctorDatala);
		}
	}
	public void guncelKlinikListe()throws SQLException{
		DefaultTableModel temizleModel = (DefaultTableModel) poliklinikListesi.getModel();
		temizleModel.setRowCount(0);
		for(int i = 0; i < Clinic.getPoliList().size(); i++) {
			clinicDatala[0] = Clinic.getPoliList().get(i).getId_klinik();
			clinicDatala[1] = Clinic.getPoliList().get(i).getKlinik_ad();
			clinicListele.addRow(clinicDatala);
		}
		
	}

}

