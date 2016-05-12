package main_app;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import project_const.ButtonArgs;
import utils.AuthConverter;
import utils.CreateTime;
import utils.LoginDataDisplay;
import db_process.ConnectDatabase;
import db_process.ReadDatabase;
import db_process.WriteDatabase;

public class UserEdit extends JFrame implements LoginDataDisplay
{
	private static final long	serialVersionUID	= -3210363336601295928L;
	private JPanel				contentPane;

	private JTextField			txtUserFirstName;
	private JTextField			txtUserLastName;
	private JTextField			txtLoginName;
	private JTextField			pswUserPass;
	private JTextField			txtPhone1;
	private JTextField			txtPhone2;
	private JTextField			txtPhone3;
	private JTextField			userEMail;
	private JComboBox<String>	comboBox;

	private JLabel				lblUsername;
	private JLabel				lblNewLabel;

	private ConnectDatabase		connectDatabase;
	private Connection			connection;

	private int					_id;
	private int					userIDForEdit;
	private String				user_name;

	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					UserEdit frame = new UserEdit();
					frame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	public UserEdit()
	{
		// Veritabani baglantisi yap
		try
		{
			connectDatabase = new ConnectDatabase(true);
			connection = connectDatabase.getMysqlConnection();
		}
		catch (SQLException e1)
		{
			e1.printStackTrace();
		}

		setTitle("Kullanıcı Düzenle");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(300, 300, 466, 337);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblUsername = new JLabel((String) null);
		lblUsername.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsername.setForeground(Color.RED);
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblUsername.setBounds(240, 11, 200, 20);
		lblUsername.setText(user_name);
		contentPane.add(lblUsername);

		JLabel label_1 = new JLabel("2016/05/04 14:23");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setForeground(Color.RED);
		label_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_1.setBounds(240, 33, 200, 20);
		label_1.setText(CreateTime.getCurrentTime());

		contentPane.add(label_1);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 56, 429, 2);
		contentPane.add(separator);

		JLabel lblKullaniciAdi = new JLabel("Adi");
		lblKullaniciAdi.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblKullaniciAdi.setBounds(10, 69, 75, 25);
		contentPane.add(lblKullaniciAdi);

		JLabel lblSoyadi = new JLabel("Soyadi");
		lblSoyadi.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSoyadi.setBounds(224, 69, 75, 25);
		contentPane.add(lblSoyadi);

		JLabel lblKullaniciAdi_1 = new JLabel("Kullanici Adi");
		lblKullaniciAdi_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblKullaniciAdi_1.setBounds(10, 105, 75, 25);
		contentPane.add(lblKullaniciAdi_1);

		JLabel lblYetki = new JLabel("Yetki");
		lblYetki.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblYetki.setBounds(224, 105, 75, 25);
		contentPane.add(lblYetki);

		JLabel lblSifre = new JLabel("Sifre");
		lblSifre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSifre.setBounds(10, 139, 75, 25);
		contentPane.add(lblSifre);

		JLabel lblTelefon = new JLabel("Telefon");
		lblTelefon.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTelefon.setBounds(10, 175, 75, 25);
		contentPane.add(lblTelefon);

		JLabel lblMailAdresi = new JLabel("e-Mail");
		lblMailAdresi.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMailAdresi.setBounds(10, 211, 75, 25);
		contentPane.add(lblMailAdresi);

		txtUserFirstName = new JTextField();
		txtUserFirstName.setBounds(95, 69, 120, 25);
		contentPane.add(txtUserFirstName);
		txtUserFirstName.setColumns(10);

		txtUserLastName = new JTextField();
		txtUserLastName.setBounds(319, 69, 120, 25);
		contentPane.add(txtUserLastName);
		txtUserLastName.setColumns(10);

		txtLoginName = new JTextField();
		txtLoginName.setEditable(false);
		txtLoginName.setBounds(95, 105, 120, 25);
		contentPane.add(txtLoginName);
		txtLoginName.setColumns(10);

		String[] yetkiler = {"", "Sistem kaydedici", "Administrator", "Mali sorumlu", "Teknik sorumlu", "Yetiştirici", "İzleyici", "Misafir"};
		comboBox = new JComboBox<String>(yetkiler);
		comboBox.setBounds(319, 105, 120, 25);
		contentPane.add(comboBox);

		pswUserPass = new JTextField();
		pswUserPass.setBounds(95, 139, 120, 25);
		contentPane.add(pswUserPass);

		txtPhone1 = new JTextField();
		txtPhone1.setBounds(106, 176, 30, 25);
		contentPane.add(txtPhone1);
		txtPhone1.setColumns(10);

		JLabel label_2 = new JLabel("0");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_2.setBounds(95, 177, 10, 25);
		contentPane.add(label_2);

		JLabel label_3 = new JLabel("-");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_3.setBounds(140, 175, 5, 25);
		contentPane.add(label_3);

		txtPhone2 = new JTextField();
		txtPhone2.setBounds(150, 176, 30, 25);
		contentPane.add(txtPhone2);
		txtPhone2.setColumns(10);

		JLabel label_4 = new JLabel("-");
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_4.setBounds(185, 175, 5, 25);
		contentPane.add(label_4);

		txtPhone3 = new JTextField();
		txtPhone3.setBounds(196, 175, 75, 25);
		contentPane.add(txtPhone3);
		txtPhone3.setColumns(10);

		userEMail = new JTextField();
		userEMail.setBounds(95, 211, 344, 25);
		contentPane.add(userEMail);
		userEMail.setColumns(10);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 247, 429, 2);
		contentPane.add(separator_1);

		JButton btnNewButton = new JButton("DUZENLE");
		btnNewButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				String userFirstAndLastName = txtUserFirstName.getText().toString() + " " + txtUserLastName.getText().toString();
				String userLoginName = txtLoginName.getText().toString();

				int userAuthentication = AuthConverter.convertReadableAuthToId(comboBox.getSelectedItem().toString());

				String userPassword = pswUserPass.getText().toString();
				String userPhone = txtPhone1.getText().toString() + "-" +  txtPhone2.getText().toString() + "-" + txtPhone3.getText().toString();
				String userMail = userEMail.getText().toString();

				// Sifrenin degisip degismedigini kontrol et.
				boolean passChanged = false;
				if (pswUserPass.getText().toString().length() > 4)
				{
					int x = JOptionPane.showConfirmDialog(contentPane, "Şifre değişti. Yeni şifreyi kayıt etmek istiyormusunuz?", "Uyarı",
							JOptionPane.YES_NO_OPTION);

					if (x == JOptionPane.YES_OPTION)
						passChanged = true;
				}

				String registerQuery = "UPDATE user SET " + "auth_id = '" + userAuthentication + "', " + " user_name = '" + userFirstAndLastName + "', "
						+ " user_id = '" + userLoginName + "', ";

				if (passChanged)
					registerQuery += " user_pass = '" + userPassword + "', ";

				registerQuery += " user_mail = '" + userMail + "', " + " user_phone = '" + userPhone + "'" + " WHERE _id = '" + userIDForEdit + "' "
						+ "LIMIT 1";

				// Kullanici giris log kayit
				WriteDatabase writeLog = new WriteDatabase(connection);
				writeLog.executeQuery(registerQuery);

				String userLogEntry = "INSERT INTO user_log(user_id, login_time, user_process) "
						+ "VALUES('" + _id + "', '" + CreateTime.getCurrentTime() + "', '" + userFirstAndLastName + " isimli kullanıcının bilgilerini güncelledi.')";
				writeLog.executeQuery(userLogEntry);

				dispose();
			}
		});
		btnNewButton.setFont(new Font(ButtonArgs._FONT, Font.PLAIN, ButtonArgs._SIZE));
		btnNewButton.setBounds(320, 260, ButtonArgs._WIDTH, ButtonArgs._HEIGHT);
		contentPane.add(btnNewButton);

		JButton btnIptal = new JButton("IPTAL");
		btnIptal.setFont(new Font(ButtonArgs._FONT, Font.PLAIN, ButtonArgs._SIZE));
		btnIptal.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				dispose();
			}
		});
		btnIptal.setBounds(10, 260, ButtonArgs._WIDTH, ButtonArgs._HEIGHT);
		contentPane.add(btnIptal);

		JLabel lblKayitTarihi = new JLabel("Kayit Tarihi : ");
		lblKayitTarihi.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblKayitTarihi.setBounds(10, 11, 80, 25);
		contentPane.add(lblKayitTarihi);

		lblNewLabel = new JLabel("New label");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(90, 11, 121, 25);
		contentPane.add(lblNewLabel);

		JLabel lblfreyiDeitirmeyeceksenizBo = new JLabel("Şifreyi değişmeyecekse boş bırakın");
		lblfreyiDeitirmeyeceksenizBo.setForeground(Color.RED);
		lblfreyiDeitirmeyeceksenizBo.setBounds(224, 146, 216, 14);
		contentPane.add(lblfreyiDeitirmeyeceksenizBo);
	}

	public void setUserIdForEdit(Object str)
	{
		this.userIDForEdit = (int) str;

		String query = "SELECT auth_id, user_name, user_id, user_mail, user_phone, user_register FROM user WHERE _id = '" + userIDForEdit + "' LIMIT 1";

		ReadDatabase getUser = new ReadDatabase(connection);
		ResultSet rs = null;

		try
		{
			rs = getUser.getData(query);

			while (rs.next())
			{
				String[] explodeName = rs.getString(2).split(" ");
				txtUserFirstName.setText(explodeName[0]);
				txtUserLastName.setText(explodeName[1]);
				txtLoginName.setText(rs.getString(3));

				String[] userPhone = rs.getString(5).split("-");
				txtPhone1.setText(userPhone[0]);
				txtPhone2.setText(userPhone[1]);
				txtPhone3.setText(userPhone[2]);

				userEMail.setText(rs.getString(4));

				lblNewLabel.setText(rs.getString(6));

				comboBox.setSelectedItem(AuthConverter.convertIdToReadableAuth(rs.getInt(1)));
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void setUserID(int _id)
	{
		this._id = _id;
	}

	@Override
	public void setUserName(String user_name)
	{
		this.user_name = user_name;
		this.lblUsername.setText(user_name);
	}

	@Override
	public void setAuthID(int auth_id)
	{
		// Auto-generated method stub
	}
}