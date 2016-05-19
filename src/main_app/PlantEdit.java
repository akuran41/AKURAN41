package main_app;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.commons.io.FileUtils;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import ui.CreateButton;
import ui.CreateInput;
import ui.CreateLabel;
import ui.CreateSeparator;
import utils.CreateTime;
import utils.DateLabelFormatter;
import utils.LoginDataDisplay;
import db_process.ConnectDatabase;
import db_process.ReadDatabase;
import db_process.WriteDatabase;
import files.FilePath;

public class PlantEdit extends JFrame implements LoginDataDisplay
{
	private static final long	serialVersionUID	= -4622095388382931747L;
	private JPanel				contentPane;

	private JTextField			txtBitkiAdi;
	private JTextField			txtBitkiCinsi;
	private JTextField			txtBitkiYore;
	private JTextField			txtYetismeSuresi;
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
	private JTextField			txtCansuyuPh;
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

	private Date				txtHasatZamani;
	private Date				txtEkimZamani;

	private JLabel				lblUsername;
	private JLabel				lblForImageHolder;

	private ConnectDatabase		connectDatabase		= null;
	private CreateLabel			createLabel;
	private CreateInput			createInput;
	private CreateButton		createButton;
	private CreateSeparator		createSeparator;

	private File				file;
	private JFileChooser		fileChooser;

	private JDatePickerImpl		datePickerForEkim;
	private JDatePickerImpl		datePickerForHasat;

	private int					_id;
	private int					plantIdForEdit;
	private String				originalFileName;

	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					PlantEdit frame = new PlantEdit();
					frame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	public PlantEdit()
	{
		try
		{
			// Create DB connection
			connectDatabase = new ConnectDatabase(true);
		}
		catch (SQLException e2)
		{
			e2.printStackTrace();
		}

		setTitle("Yeni Bitki Ekle");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1034, 805);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Etiket nesnesi olustur
		createLabel = new CreateLabel();
		// Input nesnesi olustur
		createInput = new CreateInput();
		// Dugme nesnesini olustur
		createButton = new CreateButton();
		// Separator nesnesi olustur
		createSeparator = new CreateSeparator();

		// Label olustur
		createLabelArea();
		// Inputlari olustur
		createInputArea();

		String[] boxOptions = {"YAPRAK", "KÖK", "SEBZE", "MEYVE", "ÇİÇEK"};
		comboBitkiTuru = new JComboBox<String>(boxOptions);
		comboBitkiTuru.setBounds(107, 91, 175, 25);
		contentPane.add(comboBitkiTuru);

		// File chooser
		fileChooser = new JFileChooser();

		JButton btnGozAt = new JButton("Göz At...");
		btnGozAt.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

				fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Image Files", "jpg", "png"));

				// Get Integer type result from DialogPane
				int option = fileChooser.showOpenDialog(PlantEdit.this);

				// If Folder selected
				if (option == JFileChooser.APPROVE_OPTION)
				{
					// Then assign Folder name to file
					file = fileChooser.getSelectedFile();
					// Display textField

					ImageIcon imageIcon = new ImageIcon(file.toString());
					lblForImageHolder.setIcon(imageIcon);
				}
			}
		});
		btnGozAt.setBounds(401, 91, 89, 23);
		contentPane.add(btnGozAt);

		try
		{
			// Ulkelerin listesini getir
			comboBitkiUlke = new JComboBox<String>(getCountryList());
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		comboBitkiUlke.setBounds(107, 127, 175, 25);
		contentPane.add(comboBitkiUlke);

		String[] dikimSekli = {"TOHUM", "FİDE", "SOĞAN"};
		comboDikimSekli = new JComboBox<String>(dikimSekli);
		comboDikimSekli.setBounds(107, 369, 175, 25);
		contentPane.add(comboDikimSekli);

		String[] satisSekli = {"ADET", "DEMET", "KASA", "KG"};
		comboSatisSekli = new JComboBox<String>(satisSekli);
		comboSatisSekli.setBounds(401, 369, 175, 25);
		contentPane.add(comboSatisSekli);

		JButton btnKayit = createButton.generateButton("Düzenle", 880, 727);
		btnKayit.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				// Bitki kayit et.
				try
				{
					registerMyPlant();
				}
				catch (SQLException | IOException e1)
				{
					e1.printStackTrace();
				}

				// Pencereyi kapat
				dispose();
			}
		});
		contentPane.add(btnKayit);

		JButton btnIptal = createButton.generateButton("İptal", 10, 727);
		btnIptal.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				dispose();
			}
		});
		contentPane.add(btnIptal);
	}

	private void createLabelArea()
	{
		contentPane.add(createLabel.generateLabel("Bu ekrandan yeni bitki ekleyebilirsiniz", false, 1, 1, 15, 10, 11, 553, 20));

		lblUsername = createLabel.generateLabel("", true, 1, 3, 15, 803, 4, 200, 20);
		contentPane.add(lblUsername);
		contentPane.add(createLabel.generateLabel(CreateTime.getCurrentTime(), true, 1, 3, 13, 803, 24, 200, 20));

		contentPane.add(createSeparator.generateSeparator(10, 42, 993));
		contentPane.add(createLabel.generateLabel("Bitkinin Adı", false, 1, 1, 13, 10, 55, 100, 25));
		contentPane.add(createLabel.generateLabel("Bitkinin Cinsi", false, 1, 1, 13, 306, 55, 100, 25));
		contentPane.add(createLabel.generateLabel("Bitkinin Türü", false, 1, 1, 13, 10, 91, 75, 25));
		contentPane.add(createLabel.generateLabel("Bitkinin Resmi", false, 1, 1, 13, 306, 91, 85, 25));
		contentPane.add(createLabel.generateLabel("Bitkinin Ülkesi", false, 1, 1, 13, 10, 127, 85, 25));
		contentPane.add(createLabel.generateLabel("Bitkinin Yöresi", false, 1, 1, 13, 306, 127, 85, 25));

		contentPane.add(createSeparator.generateSeparator(10, 163, 993));
		contentPane.add(createLabel.generateLabel("Ekme Zamanı", false, 1, 1, 13, 10, 176, 85, 25));
		contentPane.add(createLabel.generateLabel("Yetişme Süresi", false, 1, 1, 13, 306, 182, 85, 25));
		contentPane.add(createLabel.generateLabel("gün", false, 1, 1, 11, 479, 182, 46, 25));
		contentPane.add(createLabel.generateLabel("Hasat Zamanı", false, 1, 1, 13, 10, 212, 85, 25));
		contentPane.add(createLabel.generateLabel("cm", false, 1, 1, 11, 479, 212, 75, 25));
		contentPane.add(createLabel.generateLabel("Hasat Ağırlığı", false, 1, 1, 13, 10, 248, 85, 25));
		contentPane.add(createLabel.generateLabel("gr", false, 1, 1, 11, 185, 248, 46, 25));
		contentPane.add(createLabel.generateLabel("Hasat Satış Fiyatı", false, 1, 1, 12, 306, 248, 110, 25));
		contentPane.add(createLabel.generateLabel("Hasat Boyu", false, 1, 1, 13, 306, 212, 85, 25));

		contentPane.add(createSeparator.generateSeparator(10, 284, 993));
		contentPane.add(createLabel.generateLabel("Tohum Satıcısı", false, 1, 1, 13, 10, 297, 85, 25));
		contentPane.add(createLabel.generateLabel("Tohum Fiyatı", false, 1, 1, 13, 306, 297, 85, 25));
		contentPane.add(createLabel.generateLabel("Fide Satıcısı", false, 1, 1, 13, 10, 333, 85, 25));
		contentPane.add(createLabel.generateLabel("Fide Fiyatı", false, 1, 1, 13, 306, 333, 85, 25));
		contentPane.add(createLabel.generateLabel("Dikim Şekli", false, 1, 1, 13, 10, 369, 85, 25));
		contentPane.add(createLabel.generateLabel("Satış Şekli", false, 1, 1, 13, 306, 369, 85, 25));

		contentPane.add(createSeparator.generateSeparator(10, 405, 600));
		contentPane.add(createLabel.generateLabel("Işık Şiddeti", false, 1, 1, 13, 10, 418, 85, 25));
		contentPane.add(createLabel.generateLabel("Işık Dalga Boyu", false, 1, 1, 13, 306, 418, 105, 25));
		contentPane.add(createLabel.generateLabel("Gün Işık Süre", false, 1, 1, 13, 10, 454, 105, 25));
		contentPane.add(createLabel.generateLabel("saat", false, 1, 1, 11, 185, 454, 46, 25));
		contentPane.add(createLabel.generateLabel("Gece Işık Süresi", false, 1, 1, 13, 306, 454, 105, 25));
		contentPane.add(createLabel.generateLabel("saat", false, 1, 1, 11, 479, 454, 46, 25));
		contentPane.add(createLabel.generateLabel("Gün Ortam Isı", false, 1, 1, 13, 10, 490, 85, 25));
		contentPane.add(createLabel.generateLabel("C°", false, 1, 1, 11, 185, 490, 46, 25));
		contentPane.add(createLabel.generateLabel("Gece Ortam Isı", false, 1, 1, 13, 306, 490, 85, 25));
		contentPane.add(createLabel.generateLabel("C°", false, 1, 1, 11, 479, 490, 46, 25));
		contentPane.add(createLabel.generateLabel("Gün Nem Oranı", false, 1, 1, 13, 10, 526, 95, 25));
		contentPane.add(createLabel.generateLabel("RH", false, 1, 1, 11, 185, 526, 46, 25));
		contentPane.add(createLabel.generateLabel("Gece Nem Oranı", false, 1, 1, 13, 306, 526, 95, 25));
		contentPane.add(createLabel.generateLabel("RH", false, 1, 1, 11, 479, 526, 46, 25));
		contentPane.add(createLabel.generateLabel("Gün O2 Oranı", false, 1, 1, 13, 10, 562, 85, 25));
		contentPane.add(createLabel.generateLabel("%", false, 1, 1, 11, 185, 562, 46, 25));
		contentPane.add(createLabel.generateLabel("Gece O2 Oranı", false, 1, 1, 13, 306, 562, 85, 25));
		contentPane.add(createLabel.generateLabel("%", false, 1, 1, 11, 479, 562, 46, 25));
		contentPane.add(createLabel.generateLabel("Gün CO2 Oranı", false, 1, 1, 13, 10, 598, 95, 25));
		contentPane.add(createLabel.generateLabel("%", false, 1, 1, 11, 185, 598, 46, 25));
		contentPane.add(createLabel.generateLabel("Gece CO2 Oranı", false, 1, 1, 13, 306, 598, 95, 25));
		contentPane.add(createLabel.generateLabel("%", false, 1, 1, 11, 479, 598, 46, 25));
		contentPane.add(createLabel.generateLabel("Gün Cansuyu Isı", false, 1, 1, 13, 10, 634, 95, 25));
		contentPane.add(createLabel.generateLabel("C°", false, 1, 1, 11, 185, 634, 46, 25));
		contentPane.add(createLabel.generateLabel("Gece Cansuyu Isı", false, 1, 1, 12, 306, 634, 105, 25));
		contentPane.add(createLabel.generateLabel("C°", false, 1, 1, 11, 479, 634, 46, 25));
		contentPane.add(createLabel.generateLabel("Cansuyu PH", false, 1, 1, 13, 10, 670, 85, 25));

		contentPane.add(createSeparator.generateSeparator(10, 706, 993));

		JSeparator separator_4 = new JSeparator();
		separator_4.setOrientation(SwingConstants.VERTICAL);
		separator_4.setBounds(609, 42, 2, 665);
		contentPane.add(separator_4);

		contentPane.add(createLabel.generateLabel("Gün Gıda A", false, 1, 1, 13, 621, 55, 85, 25));
		contentPane.add(createLabel.generateLabel("Gün Gıda B", false, 1, 1, 13, 834, 55, 85, 25));
		contentPane.add(createLabel.generateLabel("Gün Gıda C", false, 1, 1, 13, 621, 91, 85, 25));
		contentPane.add(createLabel.generateLabel("Gün Gıda D", false, 1, 1, 13, 834, 91, 85, 25));
		contentPane.add(createLabel.generateLabel("Gün Gıda E", false, 1, 1, 13, 621, 127, 85, 25));
		contentPane.add(createLabel.generateLabel("Gün Gıda F", false, 1, 1, 13, 834, 127, 85, 25));
		contentPane.add(createLabel.generateLabel("Gece Gıda A", false, 1, 1, 13, 621, 176, 85, 25));
		contentPane.add(createLabel.generateLabel("Gece Gıda B", false, 1, 1, 13, 834, 176, 85, 25));
		contentPane.add(createLabel.generateLabel("Gece Gıda C", false, 1, 1, 13, 621, 212, 85, 25));
		contentPane.add(createLabel.generateLabel("Gece Gıda D", false, 1, 1, 13, 834, 212, 85, 25));
		contentPane.add(createLabel.generateLabel("Gece Gıda E", false, 1, 1, 13, 621, 248, 85, 25));
		contentPane.add(createLabel.generateLabel("Gece Gıda F", false, 1, 1, 13, 834, 248, 85, 25));

		lblForImageHolder = new JLabel("");
		lblForImageHolder.setIcon(new ImageIcon(PlantRegistration.class.getResource("/main_app/no_pic.png")));
		lblForImageHolder.setBounds(621, 297, 382, 398);
		contentPane.add(lblForImageHolder);
	}

	private void createInputArea()
	{
		txtBitkiAdi = createInput.generateTextField(false, 107, 56, 175);
		contentPane.add(txtBitkiAdi);
		txtBitkiCinsi = createInput.generateTextField(false, 401, 56, 175);
		contentPane.add(txtBitkiCinsi);
		txtBitkiYore = createInput.generateTextField(false, 401, 127, 175);
		contentPane.add(txtBitkiYore);
		txtYetismeSuresi = createInput.generateTextField(true, 401, 182, 75);
		contentPane.add(txtYetismeSuresi);
		txtHasatBoy = createInput.generateTextField(true, 401, 212, 75);
		contentPane.add(txtHasatBoy);
		txtHasatAgirlik = createInput.generateTextField(true, 107, 248, 75);
		contentPane.add(txtHasatAgirlik);
		txtHasatSatisFiyati = createInput.generateTextField(true, 401, 248, 75);
		contentPane.add(txtHasatSatisFiyati);
		txtTohumSatici = createInput.generateTextField(false, 107, 297, 175);
		contentPane.add(txtTohumSatici);
		txtTohumFiyat = createInput.generateTextField(false, 401, 297, 75);
		contentPane.add(txtTohumFiyat);
		txtFideSatici = createInput.generateTextField(false, 107, 333, 175);
		contentPane.add(txtFideSatici);
		txtFideFiyat = createInput.generateTextField(false, 401, 333, 75);
		contentPane.add(txtFideFiyat);
		txtIsikSiddeti = createInput.generateTextField(true, 107, 418, 75);
		contentPane.add(txtIsikSiddeti);
		txtIsikDalgaboyu = createInput.generateTextField(true, 401, 418, 75);
		contentPane.add(txtIsikDalgaboyu);
		txtGunduzIsikSure = createInput.generateTextField(true, 107, 454, 75);
		contentPane.add(txtGunduzIsikSure);
		txtGeceIsikSure = createInput.generateTextField(true, 401, 454, 75);
		contentPane.add(txtGeceIsikSure);
		txtGunduzOrtamIsi = createInput.generateTextField(true, 107, 490, 75);
		contentPane.add(txtGunduzOrtamIsi);
		txtGeceOrtamIsi = createInput.generateTextField(true, 401, 490, 75);
		contentPane.add(txtGeceOrtamIsi);
		txtGunduzNemOran = createInput.generateTextField(true, 107, 526, 75);
		contentPane.add(txtGunduzNemOran);
		txtGeceNemOran = createInput.generateTextField(true, 401, 526, 75);
		contentPane.add(txtGeceNemOran);
		txtGunduzO2Oran = createInput.generateTextField(true, 107, 562, 75);
		contentPane.add(txtGunduzO2Oran);
		txtGeceO2Oran = createInput.generateTextField(true, 401, 562, 75);
		contentPane.add(txtGeceO2Oran);
		txtGunCO2Oran = createInput.generateTextField(true, 107, 598, 75);
		contentPane.add(txtGunCO2Oran);
		txtGeceCO2Oran = createInput.generateTextField(true, 401, 598, 75);
		contentPane.add(txtGeceCO2Oran);
		txtGunCansuyuIsi = createInput.generateTextField(true, 107, 634, 75);
		contentPane.add(txtGunCansuyuIsi);
		txtGeceCansuyuIsi = createInput.generateTextField(true, 401, 634, 75);
		contentPane.add(txtGeceCansuyuIsi);
		txtCansuyuPh = createInput.generateTextField(true, 107, 670, 75);
		contentPane.add(txtCansuyuPh);
		txtGunduzGidaA = createInput.generateTextField(false, 716, 55, 75);
		contentPane.add(txtGunduzGidaA);
		txtGunduzGidaB = createInput.generateTextField(false, 929, 55, 75);
		contentPane.add(txtGunduzGidaB);
		txtGunduzGidaC = createInput.generateTextField(false, 716, 91, 75);
		contentPane.add(txtGunduzGidaC);
		txtGunduzGidaD = createInput.generateTextField(false, 929, 91, 75);
		contentPane.add(txtGunduzGidaD);
		txtGunduzGidaE = createInput.generateTextField(false, 716, 127, 75);
		contentPane.add(txtGunduzGidaE);
		txtGunduzGidaF = createInput.generateTextField(false, 929, 127, 75);
		contentPane.add(txtGunduzGidaF);
		txtGeceGidaA = createInput.generateTextField(false, 716, 176, 75);
		contentPane.add(txtGeceGidaA);
		txtGeceGidaB = createInput.generateTextField(false, 929, 176, 75);
		contentPane.add(txtGeceGidaB);
		txtGeceGidaC = createInput.generateTextField(false, 716, 212, 75);
		contentPane.add(txtGeceGidaC);
		txtGeceGidaD = createInput.generateTextField(false, 929, 212, 75);
		contentPane.add(txtGeceGidaD);
		txtGeceGidaE = createInput.generateTextField(false, 716, 248, 75);
		contentPane.add(txtGeceGidaE);
		txtGeceGidaF = createInput.generateTextField(false, 929, 248, 75);
		contentPane.add(txtGeceGidaF);
	}

	private void registerMyPlant() throws SQLException, IOException
	{
		txtEkimZamani = (Date) datePickerForEkim.getModel().getValue();
		txtHasatZamani = (Date) datePickerForHasat.getModel().getValue();

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String ekim = df.format(txtEkimZamani);
		String hasat = df.format(txtHasatZamani);

		String query = "UPDATE bitki SET " + "bitki_adi = '" + txtBitkiAdi.getText() + "', " + "bitki_cinsi = '" + txtBitkiCinsi.getText() + "', "
				+ "bitki_turu = '" + comboBitkiTuru.getSelectedItem() + "', ";

		if (fileChooser.getSelectedFile() != null)
			query += "bitki_resim = '" + file.getName() + "', ";

		query += "bitki_ulke = '" + comboBitkiUlke.getSelectedItem() + "', " + "bitki_yore = '" + txtBitkiYore.getText() + "', " + "bitki_ekme_zamani = '"
				+ ekim + "', " + "bitki_yetisme_suresi = '" + txtYetismeSuresi.getText() + "', " + "bitki_hasat_zamani = '" + hasat + "', "
				+ "bitki_tohum_satici = '" + txtTohumSatici.getText() + "', " + "bitki_tohum_fiyat = '" + txtTohumFiyat.getText() + "', "
				+ "bitki_fide_satici = '" + txtFideSatici.getText() + "', " + "bitki_fide_fiyat = '" + txtFideFiyat.getText() + "', " + "bitki_hasat_boy = '"
				+ txtHasatBoy.getText() + "', " + "bitki_hasat_agirlik = '" + txtHasatAgirlik.getText() + "', " + "bitki_hasat_fiyat = '"
				+ txtHasatSatisFiyati.getText() + "', " + "bitki_satis_tip = '" + comboSatisSekli.getSelectedItem() + "', " + "bitki_dikim_tip = '"
				+ comboDikimSekli.getSelectedItem() + "', " + "bitki_isik_siddet = '" + txtIsikSiddeti.getText() + "', " + "bitki_isik_dalgaboy = '"
				+ txtIsikDalgaboyu.getText() + "', " + "bitki_gunduz_isik_sure = '" + txtGunduzIsikSure.getText() + "', " + "bitki_gece_isik_sure = '"
				+ txtGeceIsikSure.getText() + "', " + "bitki_gunduz_ortam_isi = '" + txtGunduzOrtamIsi.getText() + "', " + "bitki_gece_ortam_isi = '"
				+ txtGeceOrtamIsi.getText() + "', " + "bitki_gunduz_nem = '" + txtGunduzNemOran.getText() + "', " + "bitki_gece_nem = '"
				+ txtGeceNemOran.getText() + "', " + "bitki_gunduz_o2 = '" + txtGunduzO2Oran.getText() + "', " + "bitki_gece_o2 = '" + txtGeceO2Oran.getText()
				+ "', " + "bitki_gunduz_c2o = '" + txtGunCO2Oran.getText() + "', " + "bitki_gece_c2o = '" + txtGeceCO2Oran.getText() + "', "
				+ "bitki_gunduz_cansuyu_isi = '" + txtGunCansuyuIsi.getText() + "', " + "bitki_gece_cansuyu_isi = '" + txtGeceCansuyuIsi.getText() + "', "
				+ "bitki_cansuyu_ph = '" + txtCansuyuPh.getText() + "', " + "bitki_gunduz_gida_a = '" + txtGunduzGidaA.getText() + "', "
				+ "bitki_gunduz_gida_b = '" + txtGunduzGidaB.getText() + "', " + "bitki_gunduz_gida_c = '" + txtGunduzGidaC.getText() + "', "
				+ "bitki_gunduz_gida_d = '" + txtGunduzGidaD.getText() + "', " + "bitki_gunduz_gida_e = '" + txtGunduzGidaE.getText() + "', "
				+ "bitki_gunduz_gida_f = '" + txtGunduzGidaF.getText() + "', " + "bitki_gece_gida_a = '" + txtGeceGidaA.getText() + "', "
				+ "bitki_gece_gida_b = '" + txtGeceGidaB.getText() + "', " + "bitki_gece_gida_c = '" + txtGeceGidaC.getText() + "', " + "bitki_gece_gida_d = '"
				+ txtGeceGidaD.getText() + "', " + "bitki_gece_gida_e = '" + txtGeceGidaE.getText() + "', " + "bitki_gece_gida_f =  '" + txtGeceGidaF.getText()
				+ "'" + "WHERE _id = '" + plantIdForEdit + "'";

		Connection connection = connectDatabase.getMysqlConnection();
		WriteDatabase writeLog = new WriteDatabase(connection);

		writeLog.executeQuery(query);
		writeLog.executeQuery("INSERT INTO user_log(user_id, login_time, user_process) " + "VALUES('" + _id + "', '" + CreateTime.getCurrentTime() + "', '"
				+ txtBitkiAdi.getText() + " isimli bitkinin bigilerini düzenledi.')");

		if (fileChooser.getSelectedFile() != null)
		{
			//	Delete original file first
			File fileForDelete = new File(FilePath.getImageFolder() + "\\" + originalFileName);
			fileForDelete.delete();
			
			// Secilen resmi C:\sera\resimler dizini altina kopyala
			File destLocation = new File(FilePath.getImageFolder() + "\\" + file.getName());
			FileUtils.moveFile(file, destLocation);
		}
	}

	private String[] getCountryList() throws SQLException
	{
		String[] countryList = new String[246];

		Connection connection = connectDatabase.getMysqlConnection();

		ReadDatabase readDatabase = new ReadDatabase(connection);

		ResultSet rs = readDatabase.getData("SELECT country_name FROM countries ORDER BY _id ASC");

		int x = 0;
		while (rs.next())
		{
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

	public void setPlantIdForEdit(Object str)
	{
		this.plantIdForEdit = (int) str;

		String query = "SELECT _id, bitki_adi, bitki_cinsi, bitki_turu, bitki_resim, bitki_ulke, bitki_yore, bitki_ekme_zamani, bitki_yetisme_suresi, bitki_hasat_zamani, "
				+ "bitki_tohum_satici, bitki_tohum_fiyat, bitki_fide_satici, bitki_fide_fiyat, bitki_hasat_boy, bitki_hasat_agirlik, bitki_hasat_fiyat, bitki_satis_tip, bitki_dikim_tip, bitki_isik_siddet, "
				+ "bitki_isik_dalgaboy, bitki_gunduz_isik_sure, bitki_gece_isik_sure, bitki_gunduz_ortam_isi, bitki_gece_ortam_isi, bitki_gunduz_nem, bitki_gece_nem, bitki_gunduz_o2, bitki_gece_o2, bitki_gunduz_c2o, "
				+ "bitki_gece_c2o, bitki_gunduz_cansuyu_isi, bitki_gece_cansuyu_isi, bitki_cansuyu_ph, "
				+ "bitki_gunduz_gida_a, bitki_gunduz_gida_b, bitki_gunduz_gida_c, bitki_gunduz_gida_d, bitki_gunduz_gida_e, bitki_gunduz_gida_f, "
				+ "bitki_gece_gida_a, bitki_gece_gida_b, bitki_gece_gida_c, bitki_gece_gida_d, bitki_gece_gida_e, bitki_gece_gida_f "
				+ "FROM bitki WHERE _id = '" + plantIdForEdit + "' LIMIT 1";

		Connection connection = connectDatabase.getMysqlConnection();
		ReadDatabase readDatabase = new ReadDatabase(connection);

		ResultSet rs;
		try
		{
			rs = readDatabase.getData(query);

			while (rs.next())
			{
				txtBitkiAdi.setText(rs.getString(2));
				txtBitkiCinsi.setText(rs.getString(3));
				txtBitkiYore.setText(rs.getString(7));
				txtYetismeSuresi.setText(rs.getString(9));
				txtHasatBoy.setText(rs.getString(15));
				txtHasatAgirlik.setText(rs.getString(16));
				txtHasatSatisFiyati.setText(rs.getString(17));
				txtTohumSatici.setText(rs.getString(11));
				txtTohumFiyat.setText(rs.getString(12));
				txtFideSatici.setText(rs.getString(13));
				txtFideFiyat.setText(rs.getString(14));

				txtIsikSiddeti.setText(rs.getString(20));
				txtIsikDalgaboyu.setText(rs.getString(21));
				txtGunduzIsikSure.setText(rs.getString(22));
				txtGeceIsikSure.setText(rs.getString(23));
				txtGunduzOrtamIsi.setText(rs.getString(24));
				txtGeceOrtamIsi.setText(rs.getString(25));
				txtGunduzNemOran.setText(rs.getString(26));
				txtGeceNemOran.setText(rs.getString(27));
				txtGunduzO2Oran.setText(rs.getString(28));
				txtGeceO2Oran.setText(rs.getString(29));
				txtGunCO2Oran.setText(rs.getString(30));
				txtGeceCO2Oran.setText(rs.getString(31));
				txtGunCansuyuIsi.setText(rs.getString(32));
				txtGeceCansuyuIsi.setText(rs.getString(33));
				txtCansuyuPh.setText(rs.getString(34));
				txtGunduzGidaA.setText(rs.getString(35));
				txtGunduzGidaB.setText(rs.getString(36));
				txtGunduzGidaC.setText(rs.getString(37));
				txtGunduzGidaD.setText(rs.getString(38));
				txtGunduzGidaE.setText(rs.getString(39));
				txtGunduzGidaF.setText(rs.getString(40));
				txtGeceGidaA.setText(rs.getString(41));
				txtGeceGidaB.setText(rs.getString(42));
				txtGeceGidaC.setText(rs.getString(43));
				txtGeceGidaD.setText(rs.getString(44));
				txtGeceGidaE.setText(rs.getString(45));
				txtGeceGidaF.setText(rs.getString(46));

				comboBitkiUlke.setSelectedItem(rs.getString(6));
				comboDikimSekli.setSelectedItem(rs.getString(19));
				comboSatisSekli.setSelectedItem(rs.getString(18));
				comboBitkiTuru.setSelectedItem(rs.getString(4));

				String[] hasat = rs.getDate(10).toString().split("-");
				String[] ekim = rs.getDate(8).toString().split("-");

				UtilDateModel modelForEkim = new UtilDateModel();
				modelForEkim.setDate(Integer.parseInt(ekim[0]), Integer.parseInt(ekim[1]), Integer.parseInt(ekim[2]));
				modelForEkim.setSelected(true);

				Properties pForEkim = new Properties();
				pForEkim.put("text.today", "Today");
				pForEkim.put("text.month", "Month");
				pForEkim.put("text.year", "Year");

				JDatePanelImpl datePanelForEkim = new JDatePanelImpl(modelForEkim, pForEkim);
				datePickerForEkim = new JDatePickerImpl(datePanelForEkim, new DateLabelFormatter());
				datePickerForEkim.setBounds(107, 176, 175, 25);
				contentPane.add(datePickerForEkim);

				UtilDateModel modelForHasat = new UtilDateModel();
				modelForHasat.setDate(Integer.parseInt(hasat[0]), Integer.parseInt(hasat[1]), Integer.parseInt(hasat[2]));
				modelForHasat.setSelected(true);

				Properties pForHasat = new Properties();
				pForHasat.put("text.today", "Today");
				pForHasat.put("text.month", "Month");
				pForHasat.put("text.year", "Year");

				JDatePanelImpl datePanelForHasat = new JDatePanelImpl(modelForHasat, pForHasat);
				datePickerForHasat = new JDatePickerImpl(datePanelForHasat, new DateLabelFormatter());
				datePickerForHasat.setBounds(107, 212, 175, 25);
				contentPane.add(datePickerForHasat);

				// Resim
				if(rs.getString(5).length() > 4)
				{
					this.originalFileName = rs.getString(5);
					File registeredFile = new File(FilePath.getImageFolder() + "\\" + rs.getString(5));
					ImageIcon imageIcon = new ImageIcon(registeredFile.toString());
					lblForImageHolder.setIcon(imageIcon);
				}
				else
				{
					lblForImageHolder.setIcon(new ImageIcon(PlantRegistration.class.getResource("/main_app/no_pic.png")));
				}

				contentPane.validate();
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
}