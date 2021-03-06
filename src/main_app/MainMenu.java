package main_app;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logs.LogPlant;
import logs.LogSystem;
import logs.LogUser;
import project_const.WindowArgs;
import ui.CreateLabel;
import ui.CreateSeparator;
import utils.CreateTime;
import utils.DisplayError;
import utils.ErrorLog;
import utils.LoginDataDisplay;
import db_process.ConnectDatabase;
import db_process.WriteDatabase;

public class MainMenu extends JFrame implements LoginDataDisplay
{
	private static final long	serialVersionUID	= -4644347830137344798L;

	private ErrorLog			errorLog			= null;

	private JPanel				contentPane;

	private JLabel				lblUsername;

	private CreateLabel			createLabel;
	private CreateSeparator		createSeparator;
	private DisplayError		displayError;

	private int					_id;
	private int					auth_id;
	private String				user_name;

	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					MainMenu frame = new MainMenu();
					frame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	public MainMenu()
	{
		errorLog = new ErrorLog();
		
		setResizable(WindowArgs._ISRESIZE);
		setTitle("Ana Menü");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(WindowArgs._WINDOWX, WindowArgs._WINDOWY, 526, 489);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Error message
		displayError = new DisplayError(contentPane);

		// Etiket nesnesi olustur
		createLabel = new CreateLabel();
		// Separator nesnesi olustur
		createSeparator = new CreateSeparator();

		JButton btnBitkiEkle = new JButton("<html>BİTKİ<br>EKLE</html>");
		btnBitkiEkle.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (auth_id == 2 || auth_id == 4 || auth_id == 5)
				{
					PlantRegistration registerNewPlant = new PlantRegistration();
					registerNewPlant.setUserID(_id);
					registerNewPlant.setUserName(user_name);
					registerNewPlant.setVisible(true);
				}
				else
				{
					displayNoAuth();
				}
			}
		});
		btnBitkiEkle.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnBitkiEkle.setBounds(10, 69, 120, 120);
		contentPane.add(btnBitkiEkle);

		JButton btnBitkiDuzenle = new JButton("<html>BİTKİ<br>DÜZENLE</html>");
		btnBitkiDuzenle.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				if (auth_id == 2 || auth_id == 4 || auth_id == 5)
				{
					PlantManagment plantManagment = new PlantManagment();
					plantManagment.setUserID(_id);
					plantManagment.setUserName(user_name);
					plantManagment.setVisible(true);
				}
				else
				{
					displayNoAuth();
				}
			}
		});
		btnBitkiDuzenle.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnBitkiDuzenle.setBounds(140, 69, 120, 120);
		contentPane.add(btnBitkiDuzenle);

		JButton btnPanelEkle = new JButton("<html>BÖLÜM<br>YÖNETİMİ</html>");
		btnPanelEkle.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				if (auth_id == 2 || auth_id == 4 || auth_id == 5)
				{
					// Yeni PIC karti ekleme
					HallManagment addNew = new HallManagment();
					addNew.setUserID(_id);
					addNew.setUserName(user_name);
					addNew.setVisible(true);
				}
				else
				{
					displayNoAuth();
				}
			}
		});
		btnPanelEkle.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnPanelEkle.setBounds(10, 200, 120, 120);
		contentPane.add(btnPanelEkle);

		JButton btnPanelDuzenle = new JButton("<html>PANEL<br>YÖNETİMİ</html>");
		btnPanelDuzenle.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (auth_id == 2 || auth_id == 4 || auth_id == 5)
				{
					PanelManagment cardManagment = new PanelManagment();
					cardManagment.setUserID(_id);
					cardManagment.setUserName(user_name);
					cardManagment.setVisible(true);
				}
				else
				{
					displayNoAuth();
				}
			}
		});
		btnPanelDuzenle.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnPanelDuzenle.setBounds(140, 200, 120, 120);
		contentPane.add(btnPanelDuzenle);

		JButton btnKullaniciEkle = new JButton("<html>KULLANICI<br>EKLE</html>");
		btnKullaniciEkle.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (auth_id == 2 || auth_id == 4 || auth_id == 5)
				{
					UserRegistration registerNewUser = new UserRegistration();
					registerNewUser.setUserID(_id);
					registerNewUser.setUserName(user_name);
					registerNewUser.setVisible(true);
				}
				else
				{
					displayNoAuth();
				}
			}
		});
		btnKullaniciEkle.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnKullaniciEkle.setBounds(10, 328, 120, 120);
		contentPane.add(btnKullaniciEkle);

		JButton btnKullaniciDuzenle = new JButton("<html>KULLANICI<br>DÜZENLE</html>");
		btnKullaniciDuzenle.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				if (auth_id == 2 || auth_id == 4 || auth_id == 5)
				{
					UserManagment userManagment = new UserManagment();
					userManagment.setUserID(_id);
					userManagment.setUserName(user_name);
					userManagment.setVisible(true);
				}
				else
				{
					displayNoAuth();
				}
			}
		});
		btnKullaniciDuzenle.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnKullaniciDuzenle.setBounds(140, 328, 120, 120);
		contentPane.add(btnKullaniciDuzenle);

		JButton btnBitkiLog = new JButton("<html>BİTKİ<br>LOG</html>");
		btnBitkiLog.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				if (auth_id == 2 || auth_id == 4 || auth_id == 5)
				{
					LogPlant logPlant = new LogPlant();
					logPlant.setUserID(_id);
					logPlant.setUserName(user_name);
					logPlant.setVisible(true);
				}
				else
				{
					displayNoAuth();
				}
			}
		});
		btnBitkiLog.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnBitkiLog.setBounds(270, 69, 120, 120);
		contentPane.add(btnBitkiLog);

		JButton btnSistemLog = new JButton("<html>SİSTEM<br>LOG</html>");
		btnSistemLog.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (auth_id == 2 || auth_id == 4 || auth_id == 5)
				{
					LogSystem logSystem = new LogSystem();
					logSystem.setUserID(_id);
					logSystem.setUserName(user_name);
					logSystem.setVisible(true);
				}
				else
				{
					displayNoAuth();
				}
			}
		});
		btnSistemLog.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnSistemLog.setBounds(270, 200, 120, 120);
		contentPane.add(btnSistemLog);

		JButton btnKullaniciLog = new JButton("<html>KULLANICI<br>LOG</html>");
		btnKullaniciLog.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (auth_id == 2 || auth_id == 4 || auth_id == 5)
				{
					LogUser logUser = new LogUser();
					logUser.setUserID(_id);
					logUser.setUserName(user_name);
					logUser.setVisible(true);
				}
				else
				{
					displayNoAuth();
				}
			}
		});
		btnKullaniciLog.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnKullaniciLog.setBounds(270, 328, 120, 120);
		contentPane.add(btnKullaniciLog);

		lblUsername = createLabel.generateLabel("", true, 1, 3, 15, 190, 11, 323, 20);
		contentPane.add(lblUsername);
		contentPane.add(createLabel.generateLabel(CreateTime.getCurrentTime(), true, 1, 3, 13, 190, 31, 323, 20));
		contentPane.add(createSeparator.generateSeparator(10, 56, 503));

		JButton btnCikis = new JButton("ÇIKIŞ");
		btnCikis.setForeground(Color.RED);
		btnCikis.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnCikis.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				// Sistemden cikis saatini veri tabanina yaz
				int cevap = displayError.showConfirmDialog("Sistemden çıkmak istediğinize eminmisiniz?", "Güvenli Çıkış", JOptionPane.OK_CANCEL_OPTION);

				if (cevap == JOptionPane.YES_OPTION)
				{
					ConnectDatabase connection = null;

					try
					{
						connection = new ConnectDatabase(true);
					}
					catch (SQLException e)
					{
						errorLog.generateLog(e);
					}

					String queryForLog = "INSERT INTO user_log(user_id, login_time, user_process) VALUES('" + _id + "', '" + CreateTime.getCurrentTime()
							+ "', 'Sistemden çıkış yaptı.')";

					WriteDatabase updateLogut = new WriteDatabase(connection.getMysqlConnection());
					updateLogut.executeQuery(queryForLog);

					System.exit(1);
					System.gc();
				}
			}
		});
		btnCikis.setBounds(411, 358, 90, 90);
		contentPane.add(btnCikis);

		contentPane.revalidate();
		contentPane.repaint();
	}

	private void displayNoAuth()
	{
		// If user has no privilege then display error message
		displayError.showMessageDialog("Bu bölüme erişim yetkiniz yok.", "HATA", JOptionPane.ERROR_MESSAGE);
	}

	@Override
	public void setUserID(int _id)
	{
		this._id = _id;
	}

	@Override
	public void setAuthID(int auth_id)
	{
		this.auth_id = auth_id;
	}

	@Override
	public void setUserName(String user_name)
	{
		this.user_name = user_name;
		this.lblUsername.setText(user_name);
	}
}