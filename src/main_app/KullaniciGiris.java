package main_app;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import project_const.WindowArgs;
import ui.CreateButton;
import ui.CreateLabel;
import ui.CreateSeparator;
import utils.CreateTime;
import utils.DisplayError;
import db_process.ConnectDatabase;
import db_process.ReadDatabase;
import db_process.WriteDatabase;

public class KullaniciGiris extends JFrame
{
	private static final long		serialVersionUID	= 427129700743249907L;
	private JPanel					contentPane;
	private static KullaniciGiris	frame;
	private JTextField				textField;
	private JPasswordField			passwordField;

	private CreateLabel				createLabel;
	private CreateButton			createButton;
	private CreateSeparator			createSeparator;

	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					frame = new KullaniciGiris();
					frame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	public KullaniciGiris()
	{
		setResizable(WindowArgs._ISRESIZE);
		setAlwaysOnTop(WindowArgs._ONTOP);
		setTitle("Kullanıcı Girişi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(WindowArgs._WINDOWX, WindowArgs._WINDOWY, 362, 226);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Etiket nesnesi olustur
		createLabel = new CreateLabel();
		// Dugme nesnesini olustur
		createButton = new CreateButton();
		// Separator nesnesi olustur
		createSeparator = new CreateSeparator();

		contentPane.add(createLabel.generateLabel("Sistemi kullanmak için lütfen giriş yapınız.", false, 1, 2, 15, 10, 11, 336, 30));
		contentPane.add(createLabel.generateLabel("Kullanıcı Adı", false, 1, 3, 15, 10, 68, 115, 25));
		contentPane.add(createLabel.generateLabel("Şifre", false, 1, 3, 15, 10, 104, 115, 25));
		contentPane.add(createLabel.generateLabel("Varsayılan kullanıcı adı ve şifresi : admin ", true, 1, 3, 13, 20, 45, 269, 17));

		textField = new JTextField();
		textField.setBounds(135, 68, 154, 25);
		contentPane.add(textField);
		textField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(135, 104, 154, 25);
		contentPane.add(passwordField);

		contentPane.add(createSeparator.generateSeparator(10, 140, 336));

		JButton btnIptal = createButton.generateButton("İptal", 10, 153);
		btnIptal.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				// Execute Garbage Collection
				System.gc();
				// Exit System
				System.exit(1);
			}
		});
		contentPane.add(btnIptal);

		JButton btnGiris = createButton.generateButton("Giriş", 226, 153);
		btnGiris.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String query = "SELECT COUNT(_id), _id, auth_id, user_name FROM user WHERE user_id = '" + textField.getText().toString()
						+ "' AND user_pass = '" + new String(passwordField.getPassword()) + "' LIMIT 1";

				try
				{
					ConnectDatabase connectDatabase = new ConnectDatabase(true);
					Connection connection = connectDatabase.getMysqlConnection();

					ReadDatabase readDatabase = new ReadDatabase(connection);

					ResultSet rs = readDatabase.getData(query);

					while (rs.next())
					{
						if (rs.getInt(1) != 0)
						{
							// Kullanici giris log kayit
							WriteDatabase writeLog = new WriteDatabase(connection);
							writeLog.executeQuery("INSERT INTO user_log(user_id, login_time, user_process) VALUES('" + rs.getInt(2) + "', '"
									+ CreateTime.getCurrentTime() + "', 'Kullanici giris yapti.')");

							// Kullanici adi veya sifresi dogru ise
							MainMenu mainMenu = new MainMenu();
							mainMenu.setUserID(rs.getInt(2));
							mainMenu.setAuthID(rs.getInt(3));
							mainMenu.setUserName(rs.getString(4));
							mainMenu.setVisible(true);

							// Login Ekranini kapat
							dispose();
						}
						else
						{
							// Kullanici adi veya sifresi yanlis ise
							new DisplayError(contentPane).showMessageDialog("Kullanıcı adı veya şifresi hatalı.\nLütfen tekrar deneyiniz.", "Hatali Giriş",
									JOptionPane.WARNING_MESSAGE);
						}
					}
				}
				catch (SQLException e1)
				{
					e1.printStackTrace();
				}
			}
		});
		contentPane.add(btnGiris);
	}
}