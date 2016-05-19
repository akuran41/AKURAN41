package main_app;

import java.awt.EventQueue;
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
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import ui.CreateButton;
import ui.CreateInput;
import ui.CreateLabel;
import ui.CreateSeparator;
import utils.AuthConverter;
import utils.CreateTime;
import utils.DisplayError;
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

	private ConnectDatabase		connectDatabase		= null;
	private CreateLabel			createLabel;
	private CreateInput			createInput;
	private CreateButton		createButton;
	private CreateSeparator		createSeparator;
	private DisplayError		displayError;

	private int					_id;
	private int					userIDForEdit;

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

		// Etiket nesnesi olustur
		createLabel = new CreateLabel();
		// Input nesnesi olustur
		createInput = new CreateInput();
		// Dugme nesnesini olustur
		createButton = new CreateButton();
		// Separator nesnesi olustur
		createSeparator = new CreateSeparator();
		// Hata mesajı nesnesini olustur.
		displayError = new DisplayError(contentPane);

		lblUsername = createLabel.generateLabel("", true, 1, 3, 15, 240, 11, 200, 20);
		contentPane.add(lblUsername);
		contentPane.add(createLabel.generateLabel(CreateTime.getCurrentTime(), true, 1, 3, 13, 240, 33, 200, 20));
		contentPane.add(createSeparator.generateSeparator(10, 56, 429));
		contentPane.add(createLabel.generateLabel("Ad", false, 1, 1, 13, 10, 69, 75, 25));
		contentPane.add(createLabel.generateLabel("Soyad", false, 1, 1, 13, 224, 69, 75, 25));
		contentPane.add(createLabel.generateLabel("Kullanıcı Adı", false, 1, 1, 13, 10, 105, 75, 25));
		contentPane.add(createLabel.generateLabel("Yetki", false, 1, 1, 13, 224, 105, 75, 25));
		contentPane.add(createLabel.generateLabel("Şifre", false, 1, 1, 13, 10, 139, 75, 25));
		contentPane.add(createLabel.generateLabel("Telefon", false, 1, 1, 13, 10, 175, 75, 25));
		contentPane.add(createLabel.generateLabel("e-Mail", false, 1, 1, 13, 10, 211, 75, 25));
		contentPane.add(createLabel.generateLabel("0", false, 1, 1, 15, 95, 177, 10, 25));
		contentPane.add(createLabel.generateLabel("-", false, 1, 1, 15, 140, 175, 5, 25));
		contentPane.add(createLabel.generateLabel("-", false, 1, 1, 15, 185, 175, 5, 25));
		contentPane.add(createLabel.generateLabel("Kayıt Tarihi : ", false, 1, 1, 13, 10, 11, 80, 25));
		lblNewLabel = createLabel.generateLabel("", true, 1, 1, 13, 90, 11, 121, 25);
		contentPane.add(lblNewLabel);
		contentPane.add(createLabel.generateLabel("Şifre değişmeyecekse boş bırakın", true, 1, 1, 13, 224, 146, 216, 14));
		contentPane.add(createSeparator.generateSeparator(10, 247, 429));

		txtUserFirstName = createInput.generateTextField(false, 95, 69, 120);
		contentPane.add(txtUserFirstName);

		txtUserLastName = createInput.generateTextField(false, 319, 69, 120);
		contentPane.add(txtUserLastName);

		txtLoginName = createInput.generateTextField(false, 95, 105, 120);
		txtLoginName.setEditable(false);
		contentPane.add(txtLoginName);

		String[] yetkiler = {"", "Sistem kaydedici", "Administrator", "Mali sorumlu", "Teknik sorumlu", "Yetiştirici", "İzleyici", "Misafir"};
		comboBox = new JComboBox<String>(yetkiler);
		comboBox.setBounds(319, 105, 120, 25);
		contentPane.add(comboBox);

		pswUserPass = createInput.generateTextField(false, 95, 139, 120);
		contentPane.add(pswUserPass);

		txtPhone1 = createInput.generateTextField(false, 106, 176, 30);
		contentPane.add(txtPhone1);

		txtPhone2 = createInput.generateTextField(false, 150, 176, 30);
		contentPane.add(txtPhone2);

		txtPhone3 = createInput.generateTextField(false, 196, 175, 75);
		contentPane.add(txtPhone3);

		userEMail = createInput.generateTextField(false, 95, 211, 344);
		contentPane.add(userEMail);

		JButton btnNewButton = createButton.generateButton("Düzenle", 320, 260);
		btnNewButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				String userFirstAndLastName = txtUserFirstName.getText().toString() + " " + txtUserLastName.getText().toString();
				String userLoginName = txtLoginName.getText().toString();

				int userAuthentication = AuthConverter.convertReadableAuthToId(comboBox.getSelectedItem().toString());

				String userPassword = pswUserPass.getText().toString();
				String userPhone = txtPhone1.getText().toString() + "-" + txtPhone2.getText().toString() + "-" + txtPhone3.getText().toString();
				String userMail = userEMail.getText().toString();

				// Sifrenin degisip degismedigini kontrol et.
				boolean passChanged = false;
				if (pswUserPass.getText().toString().length() > 4)
				{
					int x = displayError.showConfirmDialog("Şifre değişti. Yeni şifreyi kayıt etmek istiyormusunuz?", "Uyarı", JOptionPane.YES_NO_OPTION);

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
				Connection connection = connectDatabase.getMysqlConnection();
				WriteDatabase writeLog = new WriteDatabase(connection);
				writeLog.executeQuery(registerQuery);

				String userLogEntry = "INSERT INTO user_log(user_id, login_time, user_process) " + "VALUES('" + _id + "', '" + CreateTime.getCurrentTime()
						+ "', '" + userFirstAndLastName + " isimli kullanıcının bilgilerini güncelledi.')";
				writeLog.executeQuery(userLogEntry);

				dispose();
			}
		});
		contentPane.add(btnNewButton);

		JButton btnIptal = createButton.generateButton("İptal", 10, 260);
		btnIptal.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				dispose();
			}
		});
		contentPane.add(btnIptal);
	}

	public void setUserIdForEdit(Object str)
	{
		this.userIDForEdit = (int) str;

		String query = "SELECT auth_id, user_name, user_id, user_mail, user_phone, user_register FROM user WHERE _id = '" + userIDForEdit + "' LIMIT 1";

		Connection connection = connectDatabase.getMysqlConnection();
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
		this.lblUsername.setText(user_name);
	}

	@Override
	public void setAuthID(int auth_id)
	{
		// Auto-generated method stub
	}
}