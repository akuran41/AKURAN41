package logs;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import ui.CreateButton;
import ui.CreateLabel;
import ui.CreateSeparator;
import utils.CreateTime;
import utils.LoginDataDisplay;
import db_process.ConnectDatabase;
import db_process.ReadDatabase;

public class LogUser extends JFrame implements LoginDataDisplay
{
	private static final long	serialVersionUID	= -4549921957623411447L;
	private JPanel				contentPane;

	private JLabel				lblUsername;

	private ConnectDatabase		connection;
	private CreateLabel			createLabel;
	private CreateButton		createButton;
	private CreateSeparator		createSeparator;

	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					LogUser frame = new LogUser();
					frame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	public LogUser()
	{
		setTitle("Kullanıcı Log");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(300, 300, 788, 572);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		try
		{
			// Create DB connection
			connection = new ConnectDatabase(true);
			// Generate Log
			getUserLog();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		// Etiket nesnesi olustur
		createLabel = new CreateLabel();
		// Dugme nesnesini olustur
		createButton = new CreateButton();
		// Separator nesnesi olustur
		createSeparator = new CreateSeparator();

		lblUsername = createLabel.generateLabel("", true, 1, 3, 15, 190, 11, 580, 20);
		contentPane.add(lblUsername);
		contentPane.add(createLabel.generateLabel(CreateTime.getCurrentTime(), true, 1, 3, 13, 190, 31, 580, 20));
		contentPane.add(createSeparator.generateSeparator(10, 56, 762));
		contentPane.add(createSeparator.generateSeparator(10, 488, 762));

		JButton btnNewButton = createButton.generateButton("İptal", 653, 500);
		btnNewButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				dispose();
			}
		});
		contentPane.add(btnNewButton);
	}

	private void getUserLog() throws SQLException
	{
		int number = 1;
		int counter = 0;

		Object[] tableHeader = new String[]{"No", "Adı Soyadı", "Login ID", "İşlem Tarihi", "Yapılan İşlem"};

		ReadDatabase getUserList = new ReadDatabase(connection.getMysqlConnection());

		ResultSet rsCounter = getUserList
				.getData("SELECT COUNT(l._id), u.user_name, u.user_id, l.login_time, l.user_process FROM user_log l LEFT JOIN user u ON l.user_id = u._id ORDER BY l.login_time DESC");

		while (rsCounter.next())
		{
			counter = rsCounter.getInt(1);
		}

		Object[][] data = new Object[counter][5];

		counter = 0;
		
		ResultSet rs = getUserList
				.getData("SELECT u.user_name, u.user_id, l.login_time, l.user_process FROM user_log l LEFT JOIN user u ON l.user_id = u._id ORDER BY l.login_time DESC");
		while (rs.next())
		{
			data[counter][0] = number;
			data[counter][1] = rs.getString(1);
			data[counter][2] = rs.getString(2);
			data[counter][3] = rs.getString(3);
			data[counter][4] = rs.getString(4);

			number++;
			counter++;
		}

		JTable table = new JTable();
		table = new JTable(data, tableHeader);

		// Tablodaki basliklari duzenle
		table.getColumnModel().getColumn(0).setPreferredWidth(45);
		table.getColumnModel().getColumn(1).setPreferredWidth(162);
		table.getColumnModel().getColumn(2).setPreferredWidth(120);
		table.getColumnModel().getColumn(3).setPreferredWidth(160);
		table.getColumnModel().getColumn(4).setPreferredWidth(350);

		// Tablodaki verileri secilebilir yap
		table.setEnabled(true);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		// Sadece her seferinde tek bir kayit secilmesine izin ver
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// Sort
		table.setAutoCreateRowSorter(true);
		table.setBounds(10, 69, 746, 268);

		JScrollPane scrollPane = new JScrollPane(table);
		// ScrollPane olculeri table ile ayni olmak zorunda
		scrollPane.setBounds(10, 69, 762, 410);
		contentPane.add(scrollPane);
		// Ekrani guncelle
		validate();
	}

	@Override
	public void setUserID(int _id)
	{
		// Unused implements
	}

	@Override
	public void setUserName(String user_name)
	{
		this.lblUsername.setText(user_name);
	}

	@Override
	public void setAuthID(int auth_id)
	{
		// Unused implements
	}
}