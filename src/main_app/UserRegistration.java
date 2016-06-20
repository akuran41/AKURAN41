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
import utils.ErrorLog;
import utils.LoginDataDisplay;
import db_process.ConnectDatabase;
import db_process.ReadDatabase;
import db_process.WriteDatabase;

public class UserRegistration extends JFrame implements LoginDataDisplay
{
	private static final long	serialVersionUID	= 8308062062681951772L;
	
	private ErrorLog			errorLog			= null;
	
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

	private CreateLabel			createLabel;
	private CreateInput			createInput;
	private CreateButton		createButton;
	private CreateSeparator		createSeparator;

	private int					_id;
	private String				user_name;

	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					UserRegistration frame = new UserRegistration();
					frame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	public UserRegistration()
	{
		errorLog = new ErrorLog();
		
		setTitle("Yeni Kullanıcı Ekle");
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

		lblUsername = createLabel.generateLabel(user_name, true, 1, 3, 15, 240, 11, 200, 20);
		contentPane.add(lblUsername);

		contentPane.add(createLabel.generateLabel(CreateTime.getCurrentTime(), true, 1, 3, 13, 240, 33, 200, 20));
		contentPane.add(createSeparator.generateSeparator(10, 56, 429));
		contentPane.add(createLabel.generateLabel("Adi", false, 1, 1, 13, 10, 69, 75, 25));
		contentPane.add(createLabel.generateLabel("Soyadi", false, 1, 1, 13, 224, 69, 75, 25));
		contentPane.add(createLabel.generateLabel("Kullanici Adi", false, 1, 1, 13, 10, 105, 75, 25));
		contentPane.add(createLabel.generateLabel("Yetki", false, 1, 1, 13, 224, 105, 75, 25));
		contentPane.add(createLabel.generateLabel("Sifre", false, 1, 1, 13, 10, 139, 75, 25));
		contentPane.add(createLabel.generateLabel("Telefon", false, 1, 1, 13, 10, 175, 75, 25));
		contentPane.add(createLabel.generateLabel("e-Mail", false, 1, 1, 13, 10, 211, 75, 25));
		contentPane.add(createLabel.generateLabel("0", false, 1, 1, 15, 95, 177, 10, 25));
		contentPane.add(createLabel.generateLabel("-", false, 1, 1, 15, 140, 175, 5, 25));
		contentPane.add(createLabel.generateLabel("-", false, 1, 1, 15, 185, 175, 5, 25));
		contentPane.add(createSeparator.generateSeparator(10, 247, 429));

		txtUserFirstName = createInput.generateTextField(false, 95, 69, 120);
		contentPane.add(txtUserFirstName);

		txtUserLastName = createInput.generateTextField(false, 319, 69, 120);
		contentPane.add(txtUserLastName);

		txtLoginName = createInput.generateTextField(false, 95, 105, 120);
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

		JButton btnNewButton = createButton.generateButton("Ekle", 320, 260);
		btnNewButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				boolean isUserExists = false;

				String userFirstAndLastName = txtUserFirstName.getText().toString() + " " + txtUserLastName.getText().toString();
				String userLoginName = txtLoginName.getText().toString();

				int userAuthentication = AuthConverter.convertReadableAuthToId(comboBox.getSelectedItem().toString());

				String userPassword = pswUserPass.getText().toString();
				String userPhone = txtPhone1.getText().toString() + "-" + txtPhone2.getText().toString() + "-" + txtPhone3.getText().toString();
				String userMail = userEMail.getText().toString();

				try
				{
					ConnectDatabase connectDatabase = new ConnectDatabase(true);
					Connection connection = connectDatabase.getMysqlConnection();

					// Kullanici adini kontrol et
					ReadDatabase readDatabase = new ReadDatabase(connection);
					ResultSet rsForLoginName = readDatabase.getData("SELECT COUNT(_id) FROM user WHERE user_id = '" + userLoginName + "' LIMIT 1");

					while (rsForLoginName.next())
					{
						if (rsForLoginName.getInt(1) != 0)
							isUserExists = true;
					}

					// Mail adresi kontrol et
					ResultSet rsForMailAddress = readDatabase.getData("SELECT COUNT(_id) FROM user WHERE user_mail = '" + userMail + "' LIMIT 1");

					while (rsForMailAddress.next())
					{
						if (rsForMailAddress.getInt(1) != 0)
							isUserExists = true;
					}

					if (isUserExists)
						new DisplayError(contentPane).showMessageDialog("Kullanıcı Adı veya Mail adresi sistemde zaten mevcut.", "HATA",
								JOptionPane.WARNING_MESSAGE);
					else
					{
						String registerQuery = "INSERT INTO user(auth_id, user_name, user_id, user_pass, user_mail, user_phone, user_register, user_last_login, isActive) "
								+ "VALUES ('"
								+ userAuthentication
								+ "', '"
								+ userFirstAndLastName
								+ "', '"
								+ userLoginName
								+ "', '"
								+ userPassword
								+ "', '"
								+ userMail + "', '" + userPhone + "', '" + CreateTime.getCurrentTime() + "', '0', '1')";

						// Kullanici giris log kayit
						WriteDatabase writeLog = new WriteDatabase(connection);
						writeLog.executeQuery(registerQuery);

						writeLog.executeQuery("INSERT INTO user_log(user_id, login_time, user_process) VALUES('" + _id + "', '" + CreateTime.getCurrentTime()
								+ "', 'Yeni kullanici olusturdu.')");

						dispose();
					}
				}
				catch (SQLException e)
				{
					errorLog.generateLog(e);
				}
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