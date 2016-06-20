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
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import ui.CreateButton;
import ui.CreateLabel;
import ui.CreateSeparator;
import utils.CreateTime;
import utils.ErrorLog;
import utils.LoginDataDisplay;
import db_process.ConnectDatabase;
import db_process.ReadDatabase;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class LogSystem extends JFrame implements LoginDataDisplay
{
	private static final long	serialVersionUID	= 5667658795911877900L;
	private JPanel				contentPane;

	private JLabel				lblUsername;

	private ErrorLog			errorLog			= null;
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
					LogSystem frame = new LogSystem();
					frame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	public LogSystem()
	{
		errorLog = new ErrorLog();

		setTitle("Sistem Log");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(300, 300, 1176, 572);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		try
		{
			// Create DB connection
			connection = new ConnectDatabase(true);
			// Generate Log
			getSysLog();
		}
		catch (SQLException e)
		{
			errorLog.generateLog(e);
		}

		// Etiket nesnesi olustur
		createLabel = new CreateLabel();
		// Dugme nesnesini olustur
		createButton = new CreateButton();
		// Separator nesnesi olustur
		createSeparator = new CreateSeparator();

		lblUsername = createLabel.generateLabel("", true, 1, 3, 15, 190, 11, 966, 20);
		contentPane.add(lblUsername);
		contentPane.add(createLabel.generateLabel(CreateTime.getCurrentTime(), true, 1, 3, 13, 190, 31, 966, 20));
		contentPane.add(createSeparator.generateSeparator(10, 56, 1150));
		contentPane.add(createSeparator.generateSeparator(10, 488, 1150));

		JButton btnNewButton = createButton.generateButton("İptal", 1042, 500);
		btnNewButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				dispose();
			}
		});
		contentPane.add(btnNewButton);
	}

	private void getSysLog() throws SQLException
	{
		int number = 1;
		int counter = 0;

		Object[] tableHeader = new String[]{"No", "Hall ID", "Panel ID", "Ortam Isı", "Ortam Nem", "Ortam O2", "Ortam CO2", "Ortam Işık", "Ortam Bar",
				"C.suyu PH", "C.suyu Isı", "A Gıda", "B Gıda", "C Gıda", "X Gıda", "Soğ. Vana", "Isı Vana", "Tarih"};

		ReadDatabase getSysLog = new ReadDatabase(connection.getMysqlConnection());

		ResultSet rsCounter = getSysLog.getData("SELECT COUNT(_id) FROM sys_log");
		while (rsCounter.next())
		{
			counter = rsCounter.getInt(1);
		}

		Object[][] data = new Object[counter][18];

		counter = 0;

		ResultSet rs = getSysLog.getData("SELECT hall_id, panel_id, env_temp, env_humid, env_oxigen, "
				+ "env_co2, env_light, env_bar, env_water_ph, env_water_temp, a_ingredient, b_ingredient, c_ingredient, x_ingredient, cool_pump_valve, "
				+ "heat_pump_valve, log_time " + "FROM sys_log " + "ORDER BY log_time DESC");
		while (rs.next())
		{
			data[counter][0] = number;
			data[counter][1] = rs.getString(1);
			data[counter][2] = rs.getString(2);
			data[counter][3] = rs.getString(3);
			data[counter][4] = rs.getString(4);
			data[counter][5] = rs.getString(5);
			data[counter][6] = rs.getString(6);
			data[counter][7] = rs.getString(7);
			data[counter][8] = rs.getString(8);
			data[counter][9] = rs.getString(9);
			data[counter][10] = rs.getString(10);
			data[counter][11] = rs.getString(11);
			data[counter][12] = rs.getString(12);
			data[counter][13] = rs.getString(13);
			data[counter][14] = rs.getString(14);
			data[counter][15] = rs.getString(15);
			data[counter][16] = rs.getString(16);
			data[counter][17] = rs.getString(17);

			number++;
			counter++;
		}

		JTable table = new JTable();
		table = new JTable(data, tableHeader);

		// Tablodaki basliklari duzenle
		table.getColumnModel().getColumn(0).setPreferredWidth(60);
		table.getColumnModel().getColumn(1).setPreferredWidth(110);
		table.getColumnModel().getColumn(2).setPreferredWidth(130);
		table.getColumnModel().getColumn(3).setPreferredWidth(180);
		table.getColumnModel().getColumn(4).setPreferredWidth(190);
		table.getColumnModel().getColumn(5).setPreferredWidth(180);
		table.getColumnModel().getColumn(6).setPreferredWidth(180);
		table.getColumnModel().getColumn(7).setPreferredWidth(190);
		table.getColumnModel().getColumn(8).setPreferredWidth(190);
		table.getColumnModel().getColumn(9).setPreferredWidth(180);
		table.getColumnModel().getColumn(10).setPreferredWidth(190);
		table.getColumnModel().getColumn(11).setPreferredWidth(140);
		table.getColumnModel().getColumn(12).setPreferredWidth(140);
		table.getColumnModel().getColumn(13).setPreferredWidth(140);
		table.getColumnModel().getColumn(14).setPreferredWidth(140);
		table.getColumnModel().getColumn(15).setPreferredWidth(150);
		table.getColumnModel().getColumn(16).setPreferredWidth(140);
		table.getColumnModel().getColumn(17).setPreferredWidth(380);

		// Tablodaki verileri secilebilir yap
		table.setEnabled(true);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		// Sadece her seferinde tek bir kayit secilmesine izin ver
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// Sort
		table.setAutoCreateRowSorter(true);
		table.setBounds(10, 69, 1150, 268);

		JScrollPane scrollPane = new JScrollPane(table);
		// ScrollPane olculeri table ile ayni olmak zorunda
		scrollPane.setBounds(10, 69, 1150, 408);
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