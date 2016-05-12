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

	private JLabel				lblUsername;
	private JComboBox<String>	comboBitkiUlke;

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
		setBounds(100, 100, 632, 533);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Etiket nesnesi olustur
		createLabel = new CreateLabel();
		// Separator nesnesi olustur
		createSeparator = new CreateSeparator();

		contentPane.add(createLabel.generateLabel("Bu ekrandan yeni bitki ekleyebilirsiniz", false, 1, 1, 15, 10, 11, 553, 20));
		contentPane.add(createSeparator.generateSeparator(10, 42, 600));
		contentPane.add(createLabel.generateLabel("Bitkinin Adi", false, 1, 1, 13, 10, 55, 100, 25));
		contentPane.add(createLabel.generateLabel("Bitkinin Cinsi", false, 1, 1, 13, 306, 55, 100, 25));

		txtBitkiAdi = new JTextField();
		txtBitkiAdi.setBounds(107, 56, 175, 25);
		contentPane.add(txtBitkiAdi);
		txtBitkiAdi.setColumns(10);

		txtBitkiCinsi = new JTextField();
		txtBitkiCinsi.setBounds(401, 56, 175, 25);
		contentPane.add(txtBitkiCinsi);
		txtBitkiCinsi.setColumns(10);

		JLabel lblBitkininTuru = new JLabel("Bitkinin Turu");
		lblBitkininTuru.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblBitkininTuru.setBounds(10, 91, 75, 25);
		contentPane.add(lblBitkininTuru);

		String[] boxOptions = {"YAPRAK", "KÖK", "SEBZE", "MEYVE", "ÇİÇEK"};
		JComboBox<String> comboBitkiTuru = new JComboBox(boxOptions);
		comboBitkiTuru.setBounds(107, 91, 175, 25);
		contentPane.add(comboBitkiTuru);

		JLabel lblBitkininResmi = new JLabel("Bitkinin Resmi");
		lblBitkininResmi.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblBitkininResmi.setBounds(306, 91, 75, 25);
		contentPane.add(lblBitkininResmi);

		JButton btnGozAt = new JButton("Goz At...");
		btnGozAt.setBounds(401, 91, 89, 23);
		contentPane.add(btnGozAt);

		JLabel lblBitkininUlkesi = new JLabel("Bitkinin Ulkesi");
		lblBitkininUlkesi.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblBitkininUlkesi.setBounds(10, 127, 85, 25);
		contentPane.add(lblBitkininUlkesi);

		try
		{
			//	Ulkelerin listesini getir
			comboBitkiUlke = new JComboBox(getCountryList());
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		comboBitkiUlke.setBounds(107, 127, 175, 25);
		contentPane.add(comboBitkiUlke);

		JLabel lblBitkininYoresi = new JLabel("Bitkinin Yoresi");
		lblBitkininYoresi.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblBitkininYoresi.setBounds(306, 127, 85, 25);
		contentPane.add(lblBitkininYoresi);

		textField = new JTextField();
		textField.setBounds(401, 127, 175, 25);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblEkmeZamani = new JLabel("Ekme Zamani");
		lblEkmeZamani.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEkmeZamani.setBounds(10, 176, 85, 25);
		contentPane.add(lblEkmeZamani);

		JLabel lblYetismeSuresi = new JLabel("Yetisme Suresi");
		lblYetismeSuresi.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblYetismeSuresi.setBounds(306, 182, 85, 25);
		contentPane.add(lblYetismeSuresi);

		txtYetismeSuresi = new JTextField();
		txtYetismeSuresi.setBounds(401, 182, 75, 25);
		contentPane.add(txtYetismeSuresi);
		txtYetismeSuresi.setColumns(10);

		JLabel lblGun = new JLabel("gun");
		lblGun.setBounds(479, 182, 46, 25);
		contentPane.add(lblGun);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 163, 600, 2);
		contentPane.add(separator_1);

		JLabel lblHasatZamani = new JLabel("Hasat Zamani");
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
		lblUsername.setBounds(410, 4, 200, 20);
		contentPane.add(lblUsername);

		JLabel label_1 = new JLabel(CreateTime.getCurrentTime());
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setForeground(Color.RED);
		label_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_1.setBounds(410, 24, 200, 20);
		contentPane.add(label_1);
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