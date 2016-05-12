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

import project_const.WindowArgs;
import ui.CreateButton;
import ui.CreateLabel;
import ui.CreateSeparator;
import utils.CreateTime;
import utils.LoginDataDisplay;
import db_process.ConnectDatabase;
import db_process.WriteDatabase;

public class MainMenu extends JFrame implements LoginDataDisplay
{
	private static final long	serialVersionUID	= -4644347830137344798L;
	private JPanel				contentPane;

	private JLabel				lblUsername;

	private CreateLabel				createLabel;
	private CreateButton			createButton;
	private CreateSeparator			createSeparator;

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
		setResizable(WindowArgs._ISRESIZE);
		setTitle("Ana Menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(WindowArgs._WINDOWX, WindowArgs._WINDOWY, 408, 497);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Etiket nesnesi olustur
		createLabel = new CreateLabel();
		//	Dugme nesnesini olustur
		createButton = new CreateButton();
		// Separator nesnesi olustur
		createSeparator = new CreateSeparator();

		JButton btnYeniKartEkle = new JButton("<html>KART<br>EKLE</html>");
		btnYeniKartEkle.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				// Yeni PIC karti ekleme
				AddNewPicCard addNew = new AddNewPicCard();
				addNew.setVisible(true);
			}
		});
		btnYeniKartEkle.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnYeniKartEkle.setBounds(10, 200, 120, 120);
		contentPane.add(btnYeniKartEkle);

		JButton btnNewButton_1 = new JButton("<html>KULLANICI<br>EKLE</html>");
		btnNewButton_1.addActionListener(new ActionListener()
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
					JOptionPane.showMessageDialog(contentPane, "Bu bolume erisim yetkiniz yok.", "HATA", JOptionPane.ERROR_MESSAGE, null);
				}
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnNewButton_1.setBounds(10, 328, 120, 120);
		contentPane.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("<html>LOG<br>YONETIMI</html>");
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnNewButton_2.setBounds(270, 200, 120, 120);
		contentPane.add(btnNewButton_2);

		lblUsername = createLabel.generateLabel("", true, 1, 3, 15, 190, 11, 200, 20);
		contentPane.add(lblUsername);
		contentPane.add(createLabel.generateLabel(CreateTime.getCurrentTime(), true, 1, 3, 13, 190, 31, 200, 20));
		contentPane.add(createSeparator.generateSeparator(10, 56, 390));
		

		JButton btnCikis = new JButton("CIKIS");
		btnCikis.setForeground(Color.RED);
		btnCikis.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnCikis.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				// Sistemden cikis saatini veri tabanina yaz
				int cevap = JOptionPane.showConfirmDialog(null, "Sistemden Cikmak istediginize eminmisiniz?", "Guvenli Cikis", JOptionPane.OK_CANCEL_OPTION);

				if (cevap == JOptionPane.YES_OPTION)
				{
					ConnectDatabase connection = null;

					try
					{
						connection = new ConnectDatabase(true);
					}
					catch (SQLException e)
					{
						e.printStackTrace();
					}

					String queryForLog = "INSERT INTO user_log(user_id, login_time, user_process) VALUES('" + _id + "', '"
							+ CreateTime.getCurrentTime() + "', 'Sistemden cikis yapti.')";

					WriteDatabase updateLogut = new WriteDatabase(connection.getMysqlConnection());
					updateLogut.executeQuery(queryForLog);

					System.exit(1);
					System.gc();
				}
			}
		});
		btnCikis.setBounds(300, 358, 90, 90);
		contentPane.add(btnCikis);

		JButton btnBitkiEkle = new JButton("<html>BITKI<br>EKLE</html>");
		btnBitkiEkle.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				PlantRegistration registerNewPlant = new PlantRegistration();
				registerNewPlant.setUserID(_id);
				registerNewPlant.setUserName(user_name);
				registerNewPlant.setVisible(true);
			}
		});
		btnBitkiEkle.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnBitkiEkle.setBounds(10, 69, 120, 120);
		contentPane.add(btnBitkiEkle);

		JButton btnNewButton_4 = new JButton("<html>BITKI<br>DUZENLE</html>");
		btnNewButton_4.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnNewButton_4.setBounds(140, 69, 120, 120);
		contentPane.add(btnNewButton_4);

		JButton btnNewButton_5 = new JButton("<html>KART<br>DUZENLE</html>");
		btnNewButton_5.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnNewButton_5.setBounds(140, 200, 120, 120);
		contentPane.add(btnNewButton_5);

		JButton btnNewButton_6 = new JButton("<html>KULLANICI<br>DUZENLE</html>");
		btnNewButton_6.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				UserManagment userManagment = new UserManagment();
				userManagment.setUserID(_id);
				userManagment.setUserName(user_name);
				userManagment.setVisible(true);
			}
		});
		btnNewButton_6.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnNewButton_6.setBounds(140, 328, 120, 120);
		contentPane.add(btnNewButton_6);

		contentPane.revalidate();
		contentPane.repaint();
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