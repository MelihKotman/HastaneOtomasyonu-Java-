package Ekranlar;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import classes.personel;

import javax.swing.JTabbedPane;
import javax.swing.JButton;
import java.awt.Font;
import java.sql.SQLException;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import baglanti.DbConnection;
import baglanti.YardimMesaji;
public class personelEkrani extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	static personel Personel = new personel(); //mainden bagimsiz bir nesne olacagi icin eger bagimli olursa sıkıntı cikarabilir
	private JTextField textyeniDocAd;
	private JTextField textyeniDocSoy;
	private JTextField textyeniDocPass;
	private JTextField textyeniDocTc;
	private JTextField textyeniDocID;
	private DefaultTableModel doctorListele = null;
	private Object[] doctorDatala = null;
	private JTable Doktorlistesi;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
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
		
		
		setTitle("e-Hastane Uygulaması");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 865, 538);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton cikisbutonu = new JButton("Çıkış Yap");
		cikisbutonu.setBounds(686, 11, 138, 33);
		cikisbutonu.setBackground(new Color(255, 0, 0));
		cikisbutonu.setFont(new Font("Verdana Pro Cond Semibold", Font.PLAIN, 11));
		contentPane.add(cikisbutonu);
		
		JLabel personeletiket = new JLabel("Hoş Geldiniz " + Personel.getAd() + "" + Personel.getSoyad());
		personeletiket.setBounds(22, 11, 225, 39);
		personeletiket.setFont(new Font("Verdana Pro Cond Semibold", Font.ITALIC, 13));
		contentPane.add(personeletiket);
		
		JTabbedPane anaPane = new JTabbedPane(JTabbedPane.TOP);
		anaPane.setBounds(22, 57, 802, 403);
		contentPane.add(anaPane);
		
		JPanel doktoryonetim = new JPanel();
		doktoryonetim.setBackground(new Color(255, 255, 255));
		anaPane.addTab("Yeni Doktor Ekle/Çıkar", null, doktoryonetim, null);
		doktoryonetim.setLayout(null);
		
		JLabel yeniDokAd = new JLabel("Doktor Adı");
		yeniDokAd.setFont(new Font("Verdana Pro Cond Semibold", Font.BOLD, 13));
		yeniDokAd.setBounds(616, 11, 91, 27);
		doktoryonetim.add(yeniDokAd);
		
		textyeniDocAd = new JTextField();
		textyeniDocAd.setBounds(616, 38, 146, 20);
		doktoryonetim.add(textyeniDocAd);
		textyeniDocAd.setColumns(10);
		
		JLabel yeniDokSoy = new JLabel("Doktor Soyadı");
		yeniDokSoy.setFont(new Font("Verdana Pro Cond Semibold", Font.PLAIN, 13));
		yeniDokSoy.setBounds(616, 69, 91, 14);
		doktoryonetim.add(yeniDokSoy);
		
		textyeniDocSoy = new JTextField();
		textyeniDocSoy.setBounds(616, 86, 146, 20);
		doktoryonetim.add(textyeniDocSoy);
		textyeniDocSoy.setColumns(10);
		
		JLabel yeniDokPass = new JLabel("Şifre");
		yeniDokPass.setFont(new Font("Verdana Pro Cond Semibold", Font.PLAIN, 13));
		yeniDokPass.setBounds(616, 117, 46, 20);
		doktoryonetim.add(yeniDokPass);
		
		textyeniDocPass = new JTextField();
		textyeniDocPass.setBounds(616, 142, 146, 20);
		doktoryonetim.add(textyeniDocPass);
		textyeniDocPass.setColumns(10);
		
		JLabel yeniDokTc = new JLabel("T.C. Kimlik Numarası");
		yeniDokTc.setFont(new Font("Verdana Pro Cond Semibold", Font.PLAIN, 13));
		yeniDokTc.setBounds(616, 173, 146, 14);
		doktoryonetim.add(yeniDokTc);
		
		textyeniDocTc = new JTextField();
		textyeniDocTc.setBounds(616, 198, 146, 20);
		doktoryonetim.add(textyeniDocTc);
		textyeniDocTc.setColumns(10);
		
		JButton KaydetButton = new JButton("Kaydet");
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
		KaydetButton.setBackground(new Color(1, 254, 172));
		KaydetButton.setBounds(616, 229, 146, 23);
		doktoryonetim.add(KaydetButton);
		
		JLabel DokSilID = new JLabel("Kullanıcı ID Numarası");
		DokSilID.setFont(new Font("Verdana Pro Cond Semibold", Font.PLAIN, 13));
		DokSilID.setBounds(616, 263, 146, 14);
		doktoryonetim.add(DokSilID);
		
		textyeniDocID = new JTextField();
		textyeniDocID.setBounds(616, 288, 146, 20);
		doktoryonetim.add(textyeniDocID);
		textyeniDocID.setColumns(10);
		
		JButton SilButton = new JButton("Sil");
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
		SilButton.setBackground(new Color(255, 0, 0));
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
		Doktorlistesi.setBackground(new Color(227, 250, 251));
		Doktorlistesi.setFont(new Font("Verdana Pro Cond Semibold", Font.PLAIN, 13));
		scrollPane_liste.setViewportView(Doktorlistesi);
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
}

