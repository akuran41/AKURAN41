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
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import ui.CreateButton;
import ui.CreateInput;
import ui.CreateLabel;
import ui.CreateSeparator;
import utils.CreateTime;
import utils.DateLabelFormatter;
import utils.DisplayError;
import utils.LoginDataDisplay;
import utils.MonthConverter;
import db_process.ConnectDatabase;
import db_process.ReadDatabase;
import db_process.WriteDatabase;
import files.FileUtility;

public class PlantRegistration extends JFrame implements LoginDataDisplay
{
	private static final long	serialVersionUID	= 5276473839635208953L;
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

	private CreateLabel			createLabel;
	private CreateInput			createInput;
	private CreateButton		createButton;
	private CreateSeparator		createSeparator;
	private DisplayError		displayError;

	private File				file;
	private JFileChooser		fileChooser;

	private JDatePickerImpl		datePickerForEkim;
	private JDatePickerImpl		datePickerForHasat;

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
		// Error message
		displayError = new DisplayError(contentPane);

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
				int option = fileChooser.showOpenDialog(PlantRegistration.this);

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
		btnGozAt.setBounds(410, 91, 89, 23);
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

		// Datepicker for Hasat zamani
		myDatePickerForHasat();
		// Datepicker for Ekim zamani
		myDatePickerForEkim();

		String[] dikimSekli = {"TOHUM", "FİDE", "SOĞAN"};
		comboDikimSekli = new JComboBox<String>(dikimSekli);
		comboDikimSekli.setBounds(107, 369, 175, 25);
		contentPane.add(comboDikimSekli);

		String[] satisSekli = {"ADET", "DEMET", "KASA", "KG"};
		comboSatisSekli = new JComboBox<String>(satisSekli);
		comboSatisSekli.setBounds(410, 369, 175, 25);
		contentPane.add(comboSatisSekli);

		JButton btnKayit = createButton.generateButton("Kayıt", 880, 727);
		btnKayit.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				// Bitki kayit et.
				try
				{
					boolean isNull = false;

					// Check for mandatory fields
					if (txtBitkiAdi.getText().toString().equals("") || txtBitkiCinsi.getText().toString().equals("")
							|| txtBitkiYore.getText().toString().equals("") || txtYetismeSuresi.getText().toString().equals(""))
						isNull = true;

					// If everything is fine then execute
					if (!isNull)
					{
						registerMyPlant();

						// Pencereyi kapat
						dispose();
					}
					else
					{
						// If not then display error message
						displayError.showMessageDialog("Kırmızı ile işaretli alanların doldurulması zorunludur.", "HATA", JOptionPane.ERROR_MESSAGE);
					}
				}
				catch (SQLException | IOException e1)
				{
					e1.printStackTrace();
				}
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
		contentPane.add(createLabel.generateLabel("Kırmızı ile işaretli alanların doldurulması zorunludur.", false, 1, 1, 15, 10, 11, 553, 20));

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
		contentPane.add(createLabel.generateLabel("gün", false, 1, 1, 11, 489, 182, 46, 25));
		contentPane.add(createLabel.generateLabel("Hasat Zamanı", false, 1, 1, 13, 10, 212, 85, 25));
		contentPane.add(createLabel.generateLabel("cm", false, 1, 1, 11, 489, 212, 75, 25));
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
		contentPane.add(createLabel.generateLabel("saat", false, 1, 1, 11, 489, 454, 46, 25));
		contentPane.add(createLabel.generateLabel("Gün Ortam Isı", false, 1, 1, 13, 10, 490, 85, 25));
		contentPane.add(createLabel.generateLabel("C°", false, 1, 1, 11, 185, 490, 46, 25));
		contentPane.add(createLabel.generateLabel("Gece Ortam Isı", false, 1, 1, 13, 306, 490, 85, 25));
		contentPane.add(createLabel.generateLabel("C°", false, 1, 1, 11, 489, 490, 46, 25));
		contentPane.add(createLabel.generateLabel("Gün Nem Oranı", false, 1, 1, 13, 10, 526, 95, 25));
		contentPane.add(createLabel.generateLabel("RH", false, 1, 1, 11, 185, 526, 46, 25));
		contentPane.add(createLabel.generateLabel("Gece Nem Oranı", false, 1, 1, 13, 306, 526, 95, 25));
		contentPane.add(createLabel.generateLabel("RH", false, 1, 1, 11, 489, 526, 46, 25));
		contentPane.add(createLabel.generateLabel("Gün O2 Oranı", false, 1, 1, 13, 10, 562, 85, 25));
		contentPane.add(createLabel.generateLabel("%", false, 1, 1, 11, 185, 562, 46, 25));
		contentPane.add(createLabel.generateLabel("Gece O2 Oranı", false, 1, 1, 13, 306, 562, 85, 25));
		contentPane.add(createLabel.generateLabel("%", false, 1, 1, 11, 489, 562, 46, 25));
		contentPane.add(createLabel.generateLabel("Gün CO2 Oranı", false, 1, 1, 13, 10, 598, 95, 25));
		contentPane.add(createLabel.generateLabel("%", false, 1, 1, 11, 185, 598, 46, 25));
		contentPane.add(createLabel.generateLabel("Gece CO2 Oranı", false, 1, 1, 13, 306, 598, 95, 25));
		contentPane.add(createLabel.generateLabel("%", false, 1, 1, 11, 489, 598, 46, 25));
		contentPane.add(createLabel.generateLabel("Gün Cansuyu Isı", false, 1, 1, 13, 10, 634, 95, 25));
		contentPane.add(createLabel.generateLabel("C°", false, 1, 1, 11, 185, 634, 46, 25));
		contentPane.add(createLabel.generateLabel("Gece Cansuyu Isı", false, 1, 1, 12, 306, 634, 105, 25));
		contentPane.add(createLabel.generateLabel("C°", false, 1, 1, 11, 489, 634, 46, 25));
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

		contentPane.add(createLabel.generateLabel("*", true, 2, 2, 14, 91, 58, 15, 25));
		contentPane.add(createLabel.generateLabel("*", true, 2, 2, 14, 395, 58, 15, 25));
		contentPane.add(createLabel.generateLabel("*", true, 2, 2, 14, 395, 128, 15, 25));
		contentPane.add(createLabel.generateLabel("*", true, 2, 2, 14, 91, 178, 15, 25));
		contentPane.add(createLabel.generateLabel("*", true, 2, 2, 14, 91, 214, 15, 25));
		contentPane.add(createLabel.generateLabel("*", true, 2, 2, 14, 91, 93, 15, 25));
		contentPane.add(createLabel.generateLabel("*", true, 2, 2, 14, 91, 127, 15, 25));
		contentPane.add(createLabel.generateLabel("*", true, 2, 2, 14, 395, 183, 15, 25));

		contentPane.add(createLabel.generateLabel("nm", false, 1, 2, 11, 483, 420, 26, 25));
		contentPane.add(createLabel.generateLabel("C°", false, 1, 2, 11, 182, 670, 20, 25));
		contentPane.add(createLabel.generateLabel("ppm", false, 1, 2, 11, 785, 55, 25, 25));
		contentPane.add(createLabel.generateLabel("ppm", false, 1, 2, 11, 785, 91, 25, 25));
		contentPane.add(createLabel.generateLabel("ppm", false, 1, 2, 11, 785, 127, 25, 25));
		contentPane.add(createLabel.generateLabel("ppm", false, 1, 2, 11, 785, 176, 25, 25));
		contentPane.add(createLabel.generateLabel("ppm", false, 1, 2, 11, 785, 212, 25, 25));
		contentPane.add(createLabel.generateLabel("ppm", false, 1, 2, 11, 785, 248, 25, 25));
		contentPane.add(createLabel.generateLabel("ppm", false, 1, 2, 11, 988, 55, 25, 25));
		contentPane.add(createLabel.generateLabel("ppm", false, 1, 2, 11, 988, 91, 25, 25));
		contentPane.add(createLabel.generateLabel("ppm", false, 1, 2, 11, 988, 127, 25, 25));
		contentPane.add(createLabel.generateLabel("ppm", false, 1, 2, 11, 988, 176, 25, 25));
		contentPane.add(createLabel.generateLabel("ppm", false, 1, 2, 11, 988, 212, 25, 25));
		contentPane.add(createLabel.generateLabel("ppm", false, 1, 2, 11, 988, 248, 25, 25));
	}

	private void createInputArea()
	{
		txtBitkiAdi = createInput.generateTextField(false, 107, 56, 175);
		contentPane.add(txtBitkiAdi);
		txtBitkiCinsi = createInput.generateTextField(false, 410, 56, 175);
		contentPane.add(txtBitkiCinsi);
		txtBitkiYore = createInput.generateTextField(false, 410, 127, 175);
		contentPane.add(txtBitkiYore);
		txtYetismeSuresi = createInput.generateTextField(true, 410, 182, 75);
		contentPane.add(txtYetismeSuresi);
		txtHasatBoy = createInput.generateTextField(true, 410, 212, 75);
		contentPane.add(txtHasatBoy);
		txtHasatAgirlik = createInput.generateTextField(true, 107, 248, 75);
		contentPane.add(txtHasatAgirlik);
		txtHasatSatisFiyati = createInput.generateTextField(true, 410, 248, 75);
		contentPane.add(txtHasatSatisFiyati);
		txtTohumSatici = createInput.generateTextField(false, 107, 297, 175);
		contentPane.add(txtTohumSatici);
		txtTohumFiyat = createInput.generateTextField(false, 410, 297, 75);
		contentPane.add(txtTohumFiyat);
		txtFideSatici = createInput.generateTextField(false, 107, 333, 175);
		contentPane.add(txtFideSatici);
		txtFideFiyat = createInput.generateTextField(false, 410, 333, 75);
		contentPane.add(txtFideFiyat);
		txtIsikSiddeti = createInput.generateTextField(true, 107, 418, 75);
		contentPane.add(txtIsikSiddeti);
		txtIsikDalgaboyu = createInput.generateTextField(true, 410, 418, 75);
		contentPane.add(txtIsikDalgaboyu);
		txtGunduzIsikSure = createInput.generateTextField(true, 107, 454, 75);
		contentPane.add(txtGunduzIsikSure);
		txtGeceIsikSure = createInput.generateTextField(true, 410, 454, 75);
		contentPane.add(txtGeceIsikSure);
		txtGunduzOrtamIsi = createInput.generateTextField(true, 107, 490, 75);
		contentPane.add(txtGunduzOrtamIsi);
		txtGeceOrtamIsi = createInput.generateTextField(true, 410, 490, 75);
		contentPane.add(txtGeceOrtamIsi);
		txtGunduzNemOran = createInput.generateTextField(true, 107, 526, 75);
		contentPane.add(txtGunduzNemOran);
		txtGeceNemOran = createInput.generateTextField(true, 410, 526, 75);
		contentPane.add(txtGeceNemOran);
		txtGunduzO2Oran = createInput.generateTextField(true, 107, 562, 75);
		contentPane.add(txtGunduzO2Oran);
		txtGeceO2Oran = createInput.generateTextField(true, 410, 562, 75);
		contentPane.add(txtGeceO2Oran);
		txtGunCO2Oran = createInput.generateTextField(true, 107, 598, 75);
		contentPane.add(txtGunCO2Oran);
		txtGeceCO2Oran = createInput.generateTextField(true, 410, 598, 75);
		contentPane.add(txtGeceCO2Oran);
		txtGunCansuyuIsi = createInput.generateTextField(true, 107, 634, 75);
		contentPane.add(txtGunCansuyuIsi);
		txtGeceCansuyuIsi = createInput.generateTextField(true, 410, 634, 75);
		contentPane.add(txtGeceCansuyuIsi);
		txtCansuyuPh = createInput.generateTextField(true, 107, 670, 75);
		contentPane.add(txtCansuyuPh);

		txtGunduzGidaA = createInput.generateTextField(false, 706, 55, 75);
		contentPane.add(txtGunduzGidaA);
		txtGunduzGidaB = createInput.generateTextField(false, 909, 55, 75);
		contentPane.add(txtGunduzGidaB);
		txtGunduzGidaC = createInput.generateTextField(false, 706, 91, 75);
		contentPane.add(txtGunduzGidaC);
		txtGunduzGidaD = createInput.generateTextField(false, 909, 91, 75);
		contentPane.add(txtGunduzGidaD);
		txtGunduzGidaE = createInput.generateTextField(false, 706, 127, 75);
		contentPane.add(txtGunduzGidaE);
		txtGunduzGidaF = createInput.generateTextField(false, 909, 127, 75);
		contentPane.add(txtGunduzGidaF);
		txtGeceGidaA = createInput.generateTextField(false, 706, 176, 75);
		contentPane.add(txtGeceGidaA);
		txtGeceGidaB = createInput.generateTextField(false, 909, 176, 75);
		contentPane.add(txtGeceGidaB);
		txtGeceGidaC = createInput.generateTextField(false, 706, 212, 75);
		contentPane.add(txtGeceGidaC);
		txtGeceGidaD = createInput.generateTextField(false, 909, 212, 75);
		contentPane.add(txtGeceGidaD);
		txtGeceGidaE = createInput.generateTextField(false, 706, 248, 75);
		contentPane.add(txtGeceGidaE);
		txtGeceGidaF = createInput.generateTextField(false, 909, 248, 75);
		contentPane.add(txtGeceGidaF);
	}

	private void myDatePickerForEkim()
	{
		Calendar calendar = Calendar.getInstance(Locale.getDefault());

		UtilDateModel model = new UtilDateModel();
		model.setDate(calendar.get(Calendar.YEAR), MonthConverter.convertMonth(calendar.get(Calendar.MONTH)), calendar.get(Calendar.DAY_OF_MONTH));
		model.setSelected(true);

		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");

		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		datePickerForEkim = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		datePickerForEkim.setBounds(107, 176, 175, 25);
		contentPane.add(datePickerForEkim);

		contentPane.validate();
	}

	private void myDatePickerForHasat()
	{
		Calendar calendar = Calendar.getInstance(Locale.getDefault());

		UtilDateModel model = new UtilDateModel();
		model.setDate(calendar.get(Calendar.YEAR), MonthConverter.convertMonth(calendar.get(Calendar.MONTH)), calendar.get(Calendar.DAY_OF_MONTH));
		model.setSelected(true);

		Properties p = new Properties();
		p.put("text.today", "Bugün");
		p.put("text.month", "Ay");
		p.put("text.year", "Yıl");

		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		datePickerForHasat = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		datePickerForHasat.setBounds(107, 212, 175, 25);
		contentPane.add(datePickerForHasat);

		JLabel label = new JLabel("New label");
		datePickerForHasat.add(label);

		contentPane.validate();
	}

	private void registerMyPlant() throws SQLException, IOException
	{
		// Bitki SQL sorgusu

		txtEkimZamani = (Date) datePickerForEkim.getModel().getValue();
		txtHasatZamani = (Date) datePickerForHasat.getModel().getValue();

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String ekim = df.format(txtEkimZamani);
		String hasat = df.format(txtHasatZamani);

		String query = "INSERT INTO bitki(bitki_adi, bitki_cinsi, bitki_turu, bitki_resim, bitki_ulke, bitki_yore, bitki_ekme_zamani, bitki_yetisme_suresi, bitki_hasat_zamani, "
				+ "bitki_tohum_satici, bitki_tohum_fiyat, bitki_fide_satici, bitki_fide_fiyat, bitki_hasat_boy, bitki_hasat_agirlik, bitki_hasat_fiyat, bitki_satis_tip, bitki_dikim_tip, "
				+ "bitki_isik_siddet, bitki_isik_dalgaboy, bitki_gunduz_isik_sure, bitki_gece_isik_sure, bitki_gunduz_ortam_isi, bitki_gece_ortam_isi, bitki_gunduz_nem, bitki_gece_nem, "
				+ "bitki_gunduz_o2, bitki_gece_o2, bitki_gunduz_c2o, bitki_gece_c2o, bitki_gunduz_cansuyu_isi, bitki_gece_cansuyu_isi, bitki_cansuyu_ph, bitki_gunduz_gida_a, "
				+ "bitki_gunduz_gida_b, bitki_gunduz_gida_c, bitki_gunduz_gida_d, bitki_gunduz_gida_e, bitki_gunduz_gida_f, bitki_gece_gida_a, bitki_gece_gida_b, bitki_gece_gida_c, "
				+ "bitki_gece_gida_d, bitki_gece_gida_e, bitki_gece_gida_f) "
				+ "VALUES ('"
				+ txtBitkiAdi.getText()
				+ "', '"
				+ txtBitkiCinsi.getText()
				+ "', '"
				+ comboBitkiTuru.getSelectedItem() + "', '";

		if (fileChooser.getSelectedFile() != null)
			query += file.getName();
		else
			query += "";

		query += "', '" + comboBitkiUlke.getSelectedItem() + "', " + "'" + txtBitkiYore.getText() + "', '" + ekim + "', '" + txtYetismeSuresi.getText()
				+ "', '" + hasat + "', '" + txtTohumSatici.getText() + "', " + "'" + txtTohumFiyat.getText() + "', '" + txtFideSatici.getText() + "', '"
				+ txtFideFiyat.getText() + "', '" + txtHasatBoy.getText() + "', '" + txtHasatAgirlik.getText() + "', '" + txtHasatSatisFiyati.getText()
				+ "', '" + comboSatisSekli.getSelectedItem() + "', '" + comboDikimSekli.getSelectedItem() + "', " + "'" + txtIsikSiddeti.getText() + "', '"
				+ txtIsikDalgaboyu.getText() + "', '" + txtGunduzIsikSure.getText() + "', '" + txtGeceIsikSure.getText() + "', '" + txtGunduzOrtamIsi.getText()
				+ "', '" + txtGeceOrtamIsi.getText() + "', '" + txtGunduzNemOran.getText() + "', '" + txtGeceNemOran.getText() + "', " + "'"
				+ txtGunduzO2Oran.getText() + "', '" + txtGeceO2Oran.getText() + "', '" + txtGunCO2Oran.getText() + "', '" + txtGeceCO2Oran.getText() + "', '"
				+ txtGunCansuyuIsi.getText() + "', '" + txtGeceCansuyuIsi.getText() + "', '" + txtCansuyuPh.getText() + "', '" + txtGunduzGidaA.getText()
				+ "', " + "'" + txtGunduzGidaB.getText() + "', '" + txtGunduzGidaC.getText() + "', '" + txtGunduzGidaD.getText() + "', '"
				+ txtGunduzGidaE.getText() + "', '" + txtGunduzGidaF.getText() + "', '" + txtGeceGidaA.getText() + "', '" + txtGeceGidaB.getText() + "', '"
				+ txtGeceGidaC.getText() + "', " + "'" + txtGeceGidaD.getText() + "', '" + txtGeceGidaE.getText() + "', '" + txtGeceGidaF.getText() + "')";

		ConnectDatabase connectDatabase = new ConnectDatabase(true);
		Connection connection = connectDatabase.getMysqlConnection();
		WriteDatabase writeLog = new WriteDatabase(connection);

		writeLog.executeQuery(query);
		writeLog.executeQuery("INSERT INTO user_log(user_id, login_time, user_process) " + "VALUES('" + _id + "', '" + CreateTime.getCurrentTime() + "', '"
				+ txtBitkiAdi.getText() + " isimli bitkinin giriş kaydını oluşturdu.')");

		if (fileChooser.getSelectedFile() != null)
		{
			// Secilen resmi C:\sera\resimler dizini altina kopyala
			FileUtility.copyMyFile(file);
		}
	}

	private String[] getCountryList() throws SQLException
	{
		String[] countryList = new String[246];

		ConnectDatabase connectDatabase = new ConnectDatabase(true);
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
}