package main_app;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import db_process.ConnectDatabase;
import db_process.ReadDatabase;
import ui.CreateLabel;
import ui.CreateSeparator;
import utils.CreateTime;
import utils.LoginDataDisplay;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

public class PlantRegistration extends JFrame implements LoginDataDisplay
{
	private static final long	serialVersionUID	= 5276473839635208953L;
	private JPanel				contentPane;
	
	private JTextField			txtBitkiAdi;
	private JTextField			txtBitkiCinsi;
	private JTextField			textField;
	private JTextField			txtYetismeSuresi;
	private JTextField			txtHasatZamani;
	private JTextField			txtEkimZamani;
	private JTextField			txtHasatBoy;
	private JTextField			txtHasatAgirlik;
	private JTextField			txtHasatSatisFiyati;
	private JTextField			txtTohumSatici;
	private JTextField			txtTohumFiyat;
	private JTextField			txtFideSatici;
	private JTextField			txtFideFiyat;
	private JTextField			txtIsikSiddeti;
	private JTextField			txtIsikDalgaboyu;
	private JTextField			txtGunduzIsikSure;
	private JTextField			txtGeceIsikSure;
	private JTextField			txtGunduzOrtamIsi;
	private JTextField			txtGeceOrtamIsi;
	private JTextField			txtGunduzNemOran;
	private JTextField			txtGeceNemOran;
	private JTextField			txtGunduzO2Oran;
	private JTextField			txtGeceO2Oran;
	private JTextField			txtGunCO2Oran;
	private JTextField			txtGeceCO2Oran;
	private JTextField			txtGunCansuyuIsi;
	private JTextField			txtGeceCansuyuIsi;
	private JTextField			textField_1;
	private JTextField			txtGunduzGidaA;
	private JTextField			txtGunduzGidaB;
	private JTextField			txtGunduzGidaC;
	private JTextField			txtGunduzGidaD;
	private JTextField			txtGunduzGidaE;
	private JTextField			txtGunduzGidaF;
	private JTextField			txtGeceGidaA;
	private JTextField			txtGeceGidaB;
	private JTextField			txtGeceGidaC;
	private JTextField			txtGeceGidaD;
	private JTextField			txtGeceGidaE;
	private JTextField			txtGeceGidaF;

	private JComboBox<String>	comboBitkiUlke;
	private JComboBox<String>	comboDikimSekli;
	private JComboBox<String>	comboSatisSekli;
	private JComboBox<String>	comboBitkiTuru;

	private JLabel				lblUsername;

	private CreateLabel			createLabel;
	private CreateSeparator		createSeparator;

	private int					_id;

	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					PlantRegistration frame = new PlantRegistration();
					frame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	public PlantRegistration()
	{
		setTitle("Yeni Bitki Ekle");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1034, 799);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Etiket nesnesi olustur
		createLabel = new CreateLabel();
		// Separator nesnesi olustur
		createSeparator = new CreateSeparator();

		contentPane.add(createLabel.generateLabel("Bu ekrandan yeni bitki ekleyebilirsiniz", false, 1, 1, 15, 10, 11, 553, 20));
		contentPane.add(createSeparator.generateSeparator(10, 42, 993));
		contentPane.add(createLabel.generateLabel("Bitkinin Adı", false, 1, 1, 13, 10, 55, 100, 25));
		contentPane.add(createLabel.generateLabel("Bitkinin Cinsi", false, 1, 1, 13, 306, 55, 100, 25));
		contentPane.add(createLabel.generateLabel("Bitkinin Türü", false, 1, 1, 13, 10, 91, 75, 25));
		contentPane.add(createLabel.generateLabel("Bitkinin Resmi", false, 1, 1, 13, 306, 91, 85, 25));
		contentPane.add(createLabel.generateLabel("Bitkinin Ülkesi", false, 1, 1, 13, 10, 127, 85, 25));
		contentPane.add(createLabel.generateLabel("Bitkinin Yöresi", false, 1, 1, 13, 306, 127, 85, 25));
		contentPane.add(createSeparator.generateSeparator(10, 163, 993));

		txtBitkiAdi = new JTextField();
		txtBitkiAdi.setBounds(107, 56, 175, 25);
		contentPane.add(txtBitkiAdi);
		txtBitkiAdi.setColumns(10);

		txtBitkiCinsi = new JTextField();
		txtBitkiCinsi.setBounds(401, 56, 175, 25);
		contentPane.add(txtBitkiCinsi);
		txtBitkiCinsi.setColumns(10);

		String[] boxOptions = {"YAPRAK", "KÖK", "SEBZE", "MEYVE", "ÇİÇEK"};
		comboBitkiTuru = new JComboBox(boxOptions);
		comboBitkiTuru.setBounds(107, 91, 175, 25);
		contentPane.add(comboBitkiTuru);

		JButton btnGozAt = new JButton("Göz At...");
		btnGozAt.setBounds(401, 91, 89, 23);
		contentPane.add(btnGozAt);

		try
		{
			// Ulkelerin listesini getir
			comboBitkiUlke = new JComboBox(getCountryList());
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		comboBitkiUlke.setBounds(107, 127, 175, 25);
		contentPane.add(comboBitkiUlke);

		textField = new JTextField();
		textField.setBounds(401, 127, 175, 25);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblEkmeZamani = new JLabel("Ekme Zamanı");
		lblEkmeZamani.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEkmeZamani.setBounds(10, 176, 85, 25);
		contentPane.add(lblEkmeZamani);

		JLabel lblYetismeSuresi = new JLabel("Yetişme Süresi");
		lblYetismeSuresi.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblYetismeSuresi.setBounds(306, 182, 85, 25);
		contentPane.add(lblYetismeSuresi);

		txtYetismeSuresi = new JTextField();
		txtYetismeSuresi.setHorizontalAlignment(SwingConstants.RIGHT);
		txtYetismeSuresi.setBounds(401, 182, 75, 25);
		contentPane.add(txtYetismeSuresi);
		txtYetismeSuresi.setColumns(10);

		JLabel lblGun = new JLabel("gun");
		lblGun.setBounds(479, 182, 46, 25);
		contentPane.add(lblGun);

		JLabel lblHasatZamani = new JLabel("Hasat Zamanı");
		lblHasatZamani.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblHasatZamani.setBounds(10, 212, 85, 25);
		contentPane.add(lblHasatZamani);

		txtHasatZamani = new JTextField();
		txtHasatZamani.setEditable(false);
		txtHasatZamani.setBounds(107, 212, 155, 25);
		contentPane.add(txtHasatZamani);
		txtHasatZamani.setColumns(10);

		JButton btnNewButton = new JButton("...");
		btnNewButton.setBounds(265, 212, 25, 25);
		contentPane.add(btnNewButton);

		txtEkimZamani = new JTextField();
		txtEkimZamani.setEditable(false);
		txtEkimZamani.setBounds(107, 176, 155, 25);
		contentPane.add(txtEkimZamani);
		txtEkimZamani.setColumns(10);

		JButton btnNewButton_1 = new JButton("...");
		btnNewButton_1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				myDataPicker(txtEkimZamani);
			}
		});
		btnNewButton_1.setBounds(265, 176, 25, 25);
		contentPane.add(btnNewButton_1);

		lblUsername = new JLabel();
		lblUsername.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsername.setForeground(Color.RED);
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblUsername.setBounds(803, 4, 200, 20);
		contentPane.add(lblUsername);

		JLabel label_1 = new JLabel(CreateTime.getCurrentTime());
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setForeground(Color.RED);
		label_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_1.setBounds(803, 24, 200, 20);
		contentPane.add(label_1);

		JLabel lblHasatBoyu = new JLabel("Hasat Boyu");
		lblHasatBoyu.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblHasatBoyu.setBounds(306, 212, 85, 25);
		contentPane.add(lblHasatBoyu);

		txtHasatBoy = new JTextField();
		txtHasatBoy.setHorizontalAlignment(SwingConstants.RIGHT);
		txtHasatBoy.setBounds(401, 212, 75, 25);
		contentPane.add(txtHasatBoy);
		txtHasatBoy.setColumns(10);

		JLabel lblCm = new JLabel("cm");
		lblCm.setBounds(479, 212, 75, 25);
		contentPane.add(lblCm);

		JLabel lblHasatArl = new JLabel("Hasat Ağırlığı");
		lblHasatArl.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblHasatArl.setBounds(10, 248, 75, 25);
		contentPane.add(lblHasatArl);

		txtHasatAgirlik = new JTextField();
		txtHasatAgirlik.setHorizontalAlignment(SwingConstants.RIGHT);
		txtHasatAgirlik.setBounds(107, 248, 75, 25);
		contentPane.add(txtHasatAgirlik);
		txtHasatAgirlik.setColumns(10);

		JLabel lblGr = new JLabel("gr");
		lblGr.setBounds(185, 248, 46, 25);
		contentPane.add(lblGr);

		JLabel lblHasatSatFiyat = new JLabel("Hasat Satış Fiyatı");
		lblHasatSatFiyat.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblHasatSatFiyat.setBounds(306, 248, 110, 25);
		contentPane.add(lblHasatSatFiyat);

		txtHasatSatisFiyati = new JTextField();
		txtHasatSatisFiyati.setHorizontalAlignment(SwingConstants.RIGHT);
		txtHasatSatisFiyati.setBounds(401, 248, 75, 25);
		contentPane.add(txtHasatSatisFiyati);
		txtHasatSatisFiyati.setColumns(10);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 284, 993, 2);
		contentPane.add(separator);

		JLabel lblTohumSatcs = new JLabel("Tohum Satıcısı");
		lblTohumSatcs.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTohumSatcs.setBounds(10, 297, 85, 25);
		contentPane.add(lblTohumSatcs);

		txtTohumSatici = new JTextField();
		txtTohumSatici.setBounds(107, 297, 175, 25);
		contentPane.add(txtTohumSatici);
		txtTohumSatici.setColumns(10);

		JLabel lblTohumFiyat = new JLabel("Tohum Fiyatı");
		lblTohumFiyat.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTohumFiyat.setBounds(306, 297, 85, 25);
		contentPane.add(lblTohumFiyat);

		txtTohumFiyat = new JTextField();
		txtTohumFiyat.setBounds(401, 297, 75, 25);
		contentPane.add(txtTohumFiyat);
		txtTohumFiyat.setColumns(10);

		JLabel lblFideSatcs = new JLabel("Fide Satıcısı");
		lblFideSatcs.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblFideSatcs.setBounds(10, 333, 85, 25);
		contentPane.add(lblFideSatcs);

		txtFideSatici = new JTextField();
		txtFideSatici.setBounds(107, 333, 175, 25);
		contentPane.add(txtFideSatici);
		txtFideSatici.setColumns(10);

		JLabel lblFideFiyat = new JLabel("Fide Fiyatı");
		lblFideFiyat.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblFideFiyat.setBounds(306, 333, 85, 25);
		contentPane.add(lblFideFiyat);

		txtFideFiyat = new JTextField();
		txtFideFiyat.setBounds(401, 333, 75, 25);
		contentPane.add(txtFideFiyat);
		txtFideFiyat.setColumns(10);

		JLabel lblDikimekli = new JLabel("Dikim Şekli");
		lblDikimekli.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDikimekli.setBounds(10, 369, 85, 25);
		contentPane.add(lblDikimekli);

		String[] dikimSekli = {"ADET", "DEMET", "KASA", "KG"};
		comboDikimSekli = new JComboBox(dikimSekli);
		comboDikimSekli.setBounds(107, 369, 175, 25);
		contentPane.add(comboDikimSekli);

		JLabel lblSatekli = new JLabel("Satış Şekli");
		lblSatekli.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSatekli.setBounds(306, 369, 85, 25);
		contentPane.add(lblSatekli);

		String[] satisSekli = {"TOHUM", "FİDE", "SOĞAN"};
		comboSatisSekli = new JComboBox(satisSekli);
		comboSatisSekli.setBounds(401, 369, 175, 25);
		contentPane.add(comboSatisSekli);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(10, 405, 600, 2);
		contentPane.add(separator_2);

		JLabel lblIkiddeti = new JLabel("Işık Şiddeti");
		lblIkiddeti.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblIkiddeti.setBounds(10, 418, 85, 25);
		contentPane.add(lblIkiddeti);

		txtIsikSiddeti = new JTextField();
		txtIsikSiddeti.setHorizontalAlignment(SwingConstants.RIGHT);
		txtIsikSiddeti.setBounds(107, 418, 75, 25);
		contentPane.add(txtIsikSiddeti);
		txtIsikSiddeti.setColumns(10);

		JLabel lblNewLabel = new JLabel("Işık Dalga Boyu");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(306, 418, 105, 25);
		contentPane.add(lblNewLabel);

		txtIsikDalgaboyu = new JTextField();
		txtIsikDalgaboyu.setHorizontalAlignment(SwingConstants.RIGHT);
		txtIsikDalgaboyu.setBounds(401, 418, 75, 25);
		contentPane.add(txtIsikDalgaboyu);
		txtIsikDalgaboyu.setColumns(10);

		JLabel lblGndzIkSre = new JLabel("Gün Işık Süre");
		lblGndzIkSre.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblGndzIkSre.setBounds(10, 454, 105, 25);
		contentPane.add(lblGndzIkSre);

		txtGunduzIsikSure = new JTextField();
		txtGunduzIsikSure.setHorizontalAlignment(SwingConstants.RIGHT);
		txtGunduzIsikSure.setBounds(107, 454, 75, 25);
		contentPane.add(txtGunduzIsikSure);
		txtGunduzIsikSure.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("saat");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1.setBounds(185, 454, 46, 25);
		contentPane.add(lblNewLabel_1);

		JLabel lblGeceIkSresi = new JLabel("Gece Işık Süresi");
		lblGeceIkSresi.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblGeceIkSresi.setBounds(306, 454, 105, 25);
		contentPane.add(lblGeceIkSresi);

		txtGeceIsikSure = new JTextField();
		txtGeceIsikSure.setHorizontalAlignment(SwingConstants.RIGHT);
		txtGeceIsikSure.setBounds(401, 454, 75, 25);
		contentPane.add(txtGeceIsikSure);
		txtGeceIsikSure.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("saat");
		lblNewLabel_2.setBounds(479, 454, 46, 25);
		contentPane.add(lblNewLabel_2);

		JLabel lblGnOrtamIs = new JLabel("Gün Ortam Isı");
		lblGnOrtamIs.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblGnOrtamIs.setBounds(10, 490, 85, 25);
		contentPane.add(lblGnOrtamIs);

		txtGunduzOrtamIsi = new JTextField();
		txtGunduzOrtamIsi.setHorizontalAlignment(SwingConstants.RIGHT);
		txtGunduzOrtamIsi.setBounds(107, 490, 75, 25);
		contentPane.add(txtGunduzOrtamIsi);
		txtGunduzOrtamIsi.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("C°");
		lblNewLabel_3.setBounds(185, 490, 46, 25);
		contentPane.add(lblNewLabel_3);

		JLabel lblGeceOrtamIs = new JLabel("Gece Ortam Isı");
		lblGeceOrtamIs.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblGeceOrtamIs.setBounds(306, 490, 85, 25);
		contentPane.add(lblGeceOrtamIs);

		txtGeceOrtamIsi = new JTextField();
		txtGeceOrtamIsi.setHorizontalAlignment(SwingConstants.RIGHT);
		txtGeceOrtamIsi.setBounds(401, 490, 75, 25);
		contentPane.add(txtGeceOrtamIsi);
		txtGeceOrtamIsi.setColumns(10);

		JLabel lblC = new JLabel("C°");
		lblC.setBounds(479, 490, 46, 25);
		contentPane.add(lblC);

		JLabel lblGnNem = new JLabel("Gün Nem Oranı");
		lblGnNem.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblGnNem.setBounds(10, 526, 95, 25);
		contentPane.add(lblGnNem);

		txtGunduzNemOran = new JTextField();
		txtGunduzNemOran.setHorizontalAlignment(SwingConstants.RIGHT);
		txtGunduzNemOran.setBounds(107, 526, 75, 25);
		contentPane.add(txtGunduzNemOran);
		txtGunduzNemOran.setColumns(10);

		JLabel lblRh = new JLabel("RH");
		lblRh.setBounds(185, 526, 46, 25);
		contentPane.add(lblRh);

		JLabel lblGeceNemOran = new JLabel("Gece Nem Oranı");
		lblGeceNemOran.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblGeceNemOran.setBounds(306, 526, 95, 25);
		contentPane.add(lblGeceNemOran);

		txtGeceNemOran = new JTextField();
		txtGeceNemOran.setHorizontalAlignment(SwingConstants.RIGHT);
		txtGeceNemOran.setBounds(401, 526, 75, 25);
		contentPane.add(txtGeceNemOran);
		txtGeceNemOran.setColumns(10);

		JLabel lblRh_1 = new JLabel("RH");
		lblRh_1.setBounds(479, 526, 46, 25);
		contentPane.add(lblRh_1);

		JLabel lblGnOOran = new JLabel("Gün O2 Oranı");
		lblGnOOran.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblGnOOran.setBounds(10, 562, 85, 25);
		contentPane.add(lblGnOOran);

		txtGunduzO2Oran = new JTextField();
		txtGunduzO2Oran.setHorizontalAlignment(SwingConstants.RIGHT);
		txtGunduzO2Oran.setBounds(107, 562, 75, 25);
		contentPane.add(txtGunduzO2Oran);
		txtGunduzO2Oran.setColumns(10);

		JLabel label = new JLabel("%");
		label.setBounds(185, 562, 46, 25);
		contentPane.add(label);

		JLabel lblGeceOOran = new JLabel("Gece O2 Oranı");
		lblGeceOOran.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblGeceOOran.setBounds(306, 562, 85, 25);
		contentPane.add(lblGeceOOran);

		txtGeceO2Oran = new JTextField();
		txtGeceO2Oran.setHorizontalAlignment(SwingConstants.RIGHT);
		txtGeceO2Oran.setBounds(401, 562, 75, 25);
		contentPane.add(txtGeceO2Oran);
		txtGeceO2Oran.setColumns(10);

		JLabel label_2 = new JLabel("%");
		label_2.setBounds(479, 562, 46, 25);
		contentPane.add(label_2);

		JLabel lblGnCoOran = new JLabel("Gün CO2 Oranı");
		lblGnCoOran.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblGnCoOran.setBounds(10, 598, 95, 25);
		contentPane.add(lblGnCoOran);

		txtGunCO2Oran = new JTextField();
		txtGunCO2Oran.setHorizontalAlignment(SwingConstants.RIGHT);
		txtGunCO2Oran.setBounds(107, 598, 75, 25);
		contentPane.add(txtGunCO2Oran);
		txtGunCO2Oran.setColumns(10);

		JLabel label_3 = new JLabel("%");
		label_3.setBounds(185, 598, 46, 25);
		contentPane.add(label_3);

		JLabel lblGeceCoOran = new JLabel("Gece CO2 Oranı");
		lblGeceCoOran.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblGeceCoOran.setBounds(306, 598, 95, 25);
		contentPane.add(lblGeceCoOran);

		txtGeceCO2Oran = new JTextField();
		txtGeceCO2Oran.setHorizontalAlignment(SwingConstants.RIGHT);
		txtGeceCO2Oran.setBounds(401, 598, 75, 25);
		contentPane.add(txtGeceCO2Oran);
		txtGeceCO2Oran.setColumns(10);

		JLabel label_4 = new JLabel("%");
		label_4.setBounds(479, 598, 46, 25);
		contentPane.add(label_4);

		JLabel lblGnCansuyuIs = new JLabel("Gün Cansuyu Isı");
		lblGnCansuyuIs.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblGnCansuyuIs.setBounds(10, 634, 95, 25);
		contentPane.add(lblGnCansuyuIs);

		txtGunCansuyuIsi = new JTextField();
		txtGunCansuyuIsi.setHorizontalAlignment(SwingConstants.RIGHT);
		txtGunCansuyuIsi.setBounds(107, 634, 75, 25);
		contentPane.add(txtGunCansuyuIsi);
		txtGunCansuyuIsi.setColumns(10);

		JLabel lblC_1 = new JLabel("C°");
		lblC_1.setBounds(185, 634, 46, 25);
		contentPane.add(lblC_1);

		JLabel lblGeceCansuyuIs = new JLabel("Gece Cansuyu Isı");
		lblGeceCansuyuIs.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblGeceCansuyuIs.setBounds(306, 634, 105, 25);
		contentPane.add(lblGeceCansuyuIs);

		txtGeceCansuyuIsi = new JTextField();
		txtGeceCansuyuIsi.setHorizontalAlignment(SwingConstants.RIGHT);
		txtGeceCansuyuIsi.setBounds(401, 634, 75, 25);
		contentPane.add(txtGeceCansuyuIsi);
		txtGeceCansuyuIsi.setColumns(10);

		JLabel lblC_2 = new JLabel("C°");
		lblC_2.setBounds(479, 634, 46, 25);
		contentPane.add(lblC_2);

		JLabel lblCansuyuPh = new JLabel("Cansuyu PH");
		lblCansuyuPh.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCansuyuPh.setBounds(10, 670, 85, 25);
		contentPane.add(lblCansuyuPh);

		textField_1 = new JTextField();
		textField_1.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_1.setBounds(107, 670, 75, 25);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(10, 706, 993, 2);
		contentPane.add(separator_3);

		JSeparator separator_4 = new JSeparator();
		separator_4.setOrientation(SwingConstants.VERTICAL);
		separator_4.setBounds(609, 42, 2, 665);
		contentPane.add(separator_4);

		JLabel lblGnGdaA = new JLabel("Gün Gıda A");
		lblGnGdaA.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblGnGdaA.setBounds(621, 55, 85, 25);
		contentPane.add(lblGnGdaA);

		txtGunduzGidaA = new JTextField();
		txtGunduzGidaA.setBounds(716, 55, 75, 25);
		contentPane.add(txtGunduzGidaA);
		txtGunduzGidaA.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Gün Gıda B");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_4.setBounds(834, 55, 85, 25);
		contentPane.add(lblNewLabel_4);

		txtGunduzGidaB = new JTextField();
		txtGunduzGidaB.setBounds(929, 55, 75, 25);
		contentPane.add(txtGunduzGidaB);
		txtGunduzGidaB.setColumns(10);

		JLabel lblGnGdaC = new JLabel("Gün Gıda C");
		lblGnGdaC.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblGnGdaC.setBounds(621, 91, 85, 25);
		contentPane.add(lblGnGdaC);

		txtGunduzGidaC = new JTextField();
		txtGunduzGidaC.setBounds(716, 91, 75, 25);
		contentPane.add(txtGunduzGidaC);
		txtGunduzGidaC.setColumns(10);

		JLabel lblGnGdaD = new JLabel("Gün Gıda D");
		lblGnGdaD.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblGnGdaD.setBounds(834, 91, 85, 25);
		contentPane.add(lblGnGdaD);

		txtGunduzGidaD = new JTextField();
		txtGunduzGidaD.setBounds(929, 91, 75, 25);
		contentPane.add(txtGunduzGidaD);
		txtGunduzGidaD.setColumns(10);

		JLabel lblGnGdaE = new JLabel("Gün Gıda E");
		lblGnGdaE.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblGnGdaE.setBounds(621, 127, 85, 25);
		contentPane.add(lblGnGdaE);

		txtGunduzGidaE = new JTextField();
		txtGunduzGidaE.setBounds(716, 127, 75, 25);
		contentPane.add(txtGunduzGidaE);
		txtGunduzGidaE.setColumns(10);

		JLabel lblGnGdaF = new JLabel("Gün Gıda F");
		lblGnGdaF.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblGnGdaF.setBounds(834, 127, 85, 25);
		contentPane.add(lblGnGdaF);

		txtGunduzGidaF = new JTextField();
		txtGunduzGidaF.setBounds(929, 127, 75, 25);
		contentPane.add(txtGunduzGidaF);
		txtGunduzGidaF.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("Gece Gıda A");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_5.setBounds(621, 176, 85, 25);
		contentPane.add(lblNewLabel_5);

		txtGeceGidaA = new JTextField();
		txtGeceGidaA.setBounds(716, 176, 75, 25);
		contentPane.add(txtGeceGidaA);
		txtGeceGidaA.setColumns(10);

		JLabel lblGeceGdaB = new JLabel("Gece Gıda B");
		lblGeceGdaB.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblGeceGdaB.setBounds(834, 176, 85, 25);
		contentPane.add(lblGeceGdaB);

		txtGeceGidaB = new JTextField();
		txtGeceGidaB.setBounds(929, 176, 75, 25);
		contentPane.add(txtGeceGidaB);
		txtGeceGidaB.setColumns(10);

		JLabel lblGeceGdaC = new JLabel("Gece Gıda C");
		lblGeceGdaC.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblGeceGdaC.setBounds(621, 212, 85, 25);
		contentPane.add(lblGeceGdaC);

		txtGeceGidaC = new JTextField();
		txtGeceGidaC.setBounds(716, 212, 75, 25);
		contentPane.add(txtGeceGidaC);
		txtGeceGidaC.setColumns(10);

		JLabel lblGeceGdaD = new JLabel("Gece Gıda D");
		lblGeceGdaD.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblGeceGdaD.setBounds(834, 212, 85, 25);
		contentPane.add(lblGeceGdaD);

		txtGeceGidaD = new JTextField();
		txtGeceGidaD.setBounds(929, 212, 75, 25);
		contentPane.add(txtGeceGidaD);
		txtGeceGidaD.setColumns(10);

		JLabel lblNewLabel_6 = new JLabel("Gece Gıda E");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_6.setBounds(621, 248, 85, 25);
		contentPane.add(lblNewLabel_6);

		txtGeceGidaE = new JTextField();
		txtGeceGidaE.setBounds(716, 248, 75, 25);
		contentPane.add(txtGeceGidaE);
		txtGeceGidaE.setColumns(10);

		JLabel lblGeceGdaF = new JLabel("Gece Gıda F");
		lblGeceGdaF.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblGeceGdaF.setBounds(834, 248, 85, 25);
		contentPane.add(lblGeceGdaF);

		txtGeceGidaF = new JTextField();
		txtGeceGidaF.setBounds(929, 248, 75, 25);
		contentPane.add(txtGeceGidaF);
		txtGeceGidaF.setColumns(10);

		JButton btnNewButton_2 = new JButton("KAYIT");
		btnNewButton_2.setBounds(914, 727, 89, 23);
		contentPane.add(btnNewButton_2);

		JButton btnIptal = new JButton("İPTAL");
		btnIptal.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				dispose();
			}
		});
		btnIptal.setBounds(10, 727, 89, 23);
		contentPane.add(btnIptal);
		
		JLabel lblNewLabel_7 = new JLabel("");
		lblNewLabel_7.setIcon(new ImageIcon(PlantRegistration.class.getResource("/main_app/kirmizi-lahana.jpg")));
		lblNewLabel_7.setBounds(621, 297, 382, 398);
		contentPane.add(lblNewLabel_7);
	}

	private String myDataPicker(JTextField txtEkimZamani2)
	{
		UtilDateModel model = new UtilDateModel();
		JDatePanelImpl datePanel = new JDatePanelImpl(model, null);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, null);
		datePicker.setBounds(220, 350, 120, 30);
		contentPane.add(datePicker);

		return null;
	}

	private String[] getCountryList() throws SQLException
	{
		// boolean isInit = false;
		String[] countryList = new String[246];

		ConnectDatabase connectDatabase = new ConnectDatabase(true);
		Connection connection = connectDatabase.getMysqlConnection();

		ReadDatabase readDatabase = new ReadDatabase(connection);

		ResultSet rs = readDatabase.getData("SELECT country_name FROM countries ORDER BY _id ASC");

		int x = 0;
		while (rs.next())
		{
			// if(!isInit)
			// {
			// int y = rs.getInt(1);
			// countryList = new String[y];
			// isInit = true;
			// }

			countryList[x] = rs.getString(1);
			x++;
		}

		return countryList;
	}

	@Override
	public void setUserID(int _id)
	{
		this._id = _id;
	}

	@Override
	public void setUserName(String user_name)
	{
		this.lblUsername.setText(user_name);
	}

	@Override
	public void setAuthID(int auth_id)
	{
		// Auto-generated method stub
	}
}