package Ekranlar;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JCalendar;

import classes.Calsaat;
import classes.doktor;
import classes.randevu;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

import com.toedter.calendar.JDateChooser;

import baglanti.YardimMesaji;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class doktorEkrani extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel doctorPane;
	private static doktor Doktor = new doktor();
	private static Calsaat cal = new Calsaat();
	private JTable table_calsaat;
	private DefaultTableModel calModel;
	private Object[] calData = null;
	private JTable Randevu_table;
	private static randevu Ran = new randevu();
	private DefaultTableModel ranModel;
	private Object[] ranData = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					doktorEkrani frame = new doktorEkrani(Doktor);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param Doktor 
	 */
	public doktorEkrani(doktor Doktor) throws SQLException {
		
		calModel = new DefaultTableModel();
		Object[] calCol = new Object[2];
		calCol[0] = "ID";
		calCol[1] = "Tarih";
		calModel.setColumnIdentifiers(calCol);
		calData = new Object[2];
		for (int i = 0; i < Doktor.getCalSaatList(Doktor.getId()).size(); i++) {
			calData[0] = Doktor.getCalSaatList(Doktor.getId()).get(i).getId();
			calData[1] = Doktor.getCalSaatList(Doktor.getId()).get(i).getDate();
			calModel.addRow(calData);
		}
		ranModel = new DefaultTableModel();
		Object[] ranCol = new Object[4];
		ranCol[0] = "ID";
		ranCol[1] = "Hasta Ad";
		ranCol[2] = "Hasta Soyad";
		ranCol[3] = "Tarih";
		ranModel.setColumnIdentifiers(ranCol);
		ranData = new Object[4];
		for (int i = 0; i < Ran.getDocRanList(Doktor.getId()).size(); i++) {
			ranData[0] = Ran.getDocRanList(Doktor.getId()).get(i).getId();
			ranData[1] = Ran.getDocRanList(Doktor.getId()).get(i).getHastaAd();
			ranData[2] = Ran.getDocRanList(Doktor.getId()).get(i).getHastaSoyad();
			ranData[3] = Ran.getDocRanList(Doktor.getId()).get(i).getTarih();
			ranModel.addRow(ranData);
		}
		
		setTitle("e-Hastane Uygulaması");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 865, 538);
		doctorPane = new JPanel();
		doctorPane.setBackground(new Color(255, 255, 255));
		doctorPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(doctorPane);
		doctorPane.setLayout(null);
		
		JLabel personeletiket = new JLabel("Hoş Geldiniz, " + Doktor.getAd() + " " + Doktor.getSoyad());
		personeletiket.setForeground(new Color(45, 75, 86));
		personeletiket.setBounds(23, 11, 227, 33);
		personeletiket.setFont(new Font("Verdana Pro Cond Semibold", Font.ITALIC, 14));
		doctorPane.add(personeletiket);
		
		JButton cikisbutonu = new JButton("Çıkış Yap");
		cikisbutonu.setForeground(new Color(255, 255, 255));
		cikisbutonu.setBounds(688, 12, 138, 33);
		cikisbutonu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				girisEkrani login = new girisEkrani();
				login.setVisible(true);
				dispose();
			}
		});
		cikisbutonu.setFont(new Font("Verdana Pro Cond Semibold", Font.PLAIN, 13));
		cikisbutonu.setBorderPainted(false);
		cikisbutonu.setBackground(new Color(128, 128, 128));
		doctorPane.add(cikisbutonu);
		
		JTabbedPane anaPane = new JTabbedPane(JTabbedPane.TOP);
		anaPane.setFont(new Font("Verdana Pro Cond Semibold", Font.PLAIN, 12));
		anaPane.setBounds(10, 63, 829, 403);
		doctorPane.add(anaPane);
		
		JPanel WorHour = new JPanel();
		anaPane.addTab("Çalışma Saatleri", null, WorHour, null);
		WorHour.setLayout(null);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setForeground(new Color(45, 75, 86));
		dateChooser.setBackground(new Color(255, 255, 255));
		dateChooser.getCalendarButton().setBackground(new Color(255, 255, 255));
		dateChooser.setBounds(10, 11, 129, 28);
		WorHour.add(dateChooser);
		
		JComboBox zamSec = new JComboBox();
		zamSec.setForeground(new Color(45, 75, 86));
		zamSec.setFont(new Font("Verdana Pro Cond Semibold", Font.PLAIN, 13));
		zamSec.setModel(new DefaultComboBoxModel(new String[] {"9.30", "10.00", "10.30", "11.00", "11.30", "12.00", "12.30", "13.30", "14.00", "14.30", "15.00", "15.30", "16.00", "16.30", "17.00"}));
		zamSec.setBounds(149, 11, 112, 28);
		WorHour.add(zamSec);
		
		JButton saatEkle = new JButton("Saat Ekle");
		saatEkle.setForeground(new Color(45, 75, 86));
		saatEkle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String date = "";
				try{
					date = sdf.format(dateChooser.getDate());
				}catch(Exception e1) {
                    YardimMesaji.gosterMesaj("tarihsec");
                }
				if(date.length() == 0) {
                    YardimMesaji.gosterMesaj("tarihsec");
                }
				else {
					String time = zamSec.getSelectedItem().toString() + ":00";
					try{
						String selectedDate = date + " " + time;
					boolean controlet5 = Doktor.ekleSaat(Doktor.getId(), Doktor.getAd(), Doktor.getSoyad(), selectedDate);
					if (controlet5) {
						YardimMesaji.gosterMesaj("Basarili");
						guncelSaat(Doktor);
						} 
						else {
							YardimMesaji.gosterMesaj("Basarisiz");
							}
					}catch (Exception e1) {
                        e1.printStackTrace();
                    
					}
				}
			}
		});
		saatEkle.setBorderPainted(false);
		saatEkle.setBackground(new Color(122, 205, 203));
		saatEkle.setFont(new Font("Verdana Pro Cond Semibold", Font.PLAIN, 13));
		saatEkle.setBounds(271, 11, 123, 28);
		WorHour.add(saatEkle);
		
		JScrollPane w_scrollPane = new JScrollPane();
		w_scrollPane.setBounds(10, 51, 804, 313);
		WorHour.add(w_scrollPane);
		
		table_calsaat = new JTable(calModel);
		table_calsaat.setFont(new Font("Verdana Pro Cond Semibold", Font.PLAIN, 11));
		w_scrollPane.setViewportView(table_calsaat);
		

		
		JButton btnSil = new JButton("Saat Sil");
		btnSil.setForeground(new Color(255, 255, 255));
		btnSil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table_calsaat.getSelectedRow();
				if (selectedRow >= 0) {
					String seciliRow =  table_calsaat.getModel().getValueAt(selectedRow, 0).toString();
					int selID = Integer.parseInt(seciliRow);
					boolean controlet6;
				 try{
						controlet6 = Doktor.cikarSaat(selID);
						if (controlet6) {
							YardimMesaji.gosterMesaj("Basarili");
							guncelSaat(Doktor);
						} else {
							YardimMesaji.gosterMesaj("Basarisiz");
						}
					}catch (Exception e1) {
						e1.printStackTrace();
					}
				}
				else {
					YardimMesaji.gosterMesaj("Lütfen bir tarih seçiniz.");
				}
			}
		});
		btnSil.setFont(new Font("Verdana Pro Cond Semibold", Font.PLAIN, 13));
		btnSil.setBorderPainted(false);
		btnSil.setBackground(new Color(0, 64, 64));
		btnSil.setBounds(691, 11, 123, 28);
		WorHour.add(btnSil);
		
		JPanel RandevuPan = new JPanel();
		anaPane.addTab("Randevularım", null, RandevuPan, null);
		RandevuPan.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 50, 804, 314);
		RandevuPan.add(scrollPane);
		
		Randevu_table = new JTable(ranModel);
		Randevu_table.setFont(new Font("Verdana Pro Cond Semibold", Font.PLAIN, 11));
		scrollPane.setViewportView(Randevu_table);
		
		JButton btnSilRan = new JButton("Randevu İptal Et");
		btnSilRan.setForeground(new Color(255, 255, 255));
		btnSilRan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String selDate = (String) Randevu_table.getValueAt(Randevu_table.getSelectedRow(), 3);
					String seldocsurName = Doktor.getSoyad();
					String seldocName = Doktor.getAd();
					Ran.silRandevu(seldocName,seldocsurName,selDate);
					guncelRandevu(Doktor);
					guncelSaat(Doktor);
					YardimMesaji.gosterMesaj("Randevu başarıyla iptal edildi.");

				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}

		});
		btnSilRan.setFont(new Font("Verdana Pro Cond Semibold", Font.PLAIN, 13));
		btnSilRan.setBorderPainted(false);
		btnSilRan.setBackground(new Color(0, 64, 64));
		btnSilRan.setBounds(671, 11, 143, 28);
		RandevuPan.add(btnSilRan);
	}	
		public void guncelSaat(doktor Doktor)throws SQLException{
			DefaultTableModel temizleModel = (DefaultTableModel) table_calsaat.getModel();
			temizleModel.setRowCount(0);
			for (int i = 0; i < Doktor.getCalSaatList(Doktor.getId()).size(); i++) {
				calData[0] = Doktor.getCalSaatList(Doktor.getId()).get(i).getId();
				calData[1] = Doktor.getCalSaatList(Doktor.getId()).get(i).getDate();
				calModel.addRow(calData);
			}
	}

	public void guncelRandevu(doktor Doktor)throws SQLException{
            DefaultTableModel temizleModel = (DefaultTableModel) Randevu_table.getModel();
            temizleModel.setRowCount(0);
            for (int i = 0; i < Ran.getDocRanList(Doktor.getId()).size(); i++) {
                ranData[0] = Ran.getDocRanList(Doktor.getId()).get(i).getId();
                ranData[1] = Ran.getDocRanList(Doktor.getId()).get(i).getHastaAd();
                ranData[2] = Ran.getDocRanList(Doktor.getId()).get(i).getHastaSoyad();
                ranData[3] = Ran.getDocRanList(Doktor.getId()).get(i).getTarih();
                ranModel.addRow(ranData);
            }
		}
	}
