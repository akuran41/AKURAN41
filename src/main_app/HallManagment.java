package main_app;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import ui.CreateButton;
import ui.CreateLabel;
import ui.CreateSeparator;
import utils.CreateTime;
import utils.DisplayError;
import utils.LoginDataDisplay;
import db_process.ConnectDatabase;
import db_process.ReadDatabase;
import db_process.WriteDatabase;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JSeparator;

public class HallManagment extends JFrame implements LoginDataDisplay
{
	private static final long		serialVersionUID	= 2494461427189987440L;
	private JPanel					contentPane;
	private JTable					table;

	private JLabel					lblUsername;

	private ConnectDatabase			connection;
	private CreateLabel				createLabel;
	private CreateButton			createButton;
	private DisplayError			displayError;
	private CreateSeparator			createSeparator;

	private int						_id;

	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					HallManagment frame = new HallManagment();
					frame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	public HallManagment()
	{
		setTitle("Bölüm Yönetimi");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(300, 300, 450, 312);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		try
		{
			// Create DB connection
			connection = new ConnectDatabase(true);
			// Get hall list
			createTable();
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
		// Hata mesajı nesnesini olustur.
		displayError = new DisplayError(contentPane);

		lblUsername = createLabel.generateLabel("", true, 1, 3, 15, 224, 11, 200, 20);
		contentPane.add(lblUsername);
		contentPane.add(createLabel.generateLabel(CreateTime.getCurrentTime(), true, 1, 3, 13, 224, 31, 200, 20));
		contentPane.add(createSeparator.generateSeparator(10, 56, 414));

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 231, 418, 2);
		contentPane.add(separator);

		JButton btnEkle = createButton.generateButton("Bölüm Ekle", 305, 240);
		btnEkle.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					addNewHall();
				}
				catch (SQLException e1)
				{
					e1.printStackTrace();
				}
			}

			private void addNewHall() throws SQLException
			{
				WriteDatabase write = new WriteDatabase(connection.getMysqlConnection());
				write.executeQuery("INSERT INTO hall(add_date) VALUES('" + CreateTime.getCurrentTime() + "')");

				// Create USERLOG
				write.executeQuery("INSERT INTO user_log(user_id, login_time, user_process) VALUES('" + _id + "', '" + CreateTime.getCurrentTime()
						+ "', 'Yeni bölüm olusturdu.')");
				
				//	populate Table
				//createTable();
				dispose();
			}
		});
		contentPane.add(btnEkle);

		JButton btnSil = createButton.generateButton("Bölüm Sil", 160, 240);
		btnSil.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				int selected = table.getSelectedRow();

				if (selected == -1)
				{
					displayError.showMessageDialog("En az bir kayıt seçmelisiniz.", "Hata", JOptionPane.WARNING_MESSAGE);
				}
				else
				{
					Object hallID = table.getValueAt(selected, 0);
					removeHall(hallID);
				}
			}

			private void removeHall(Object selected)
			{
				int hallID = (int) selected;
				boolean isCardEmpty = true;

				try
				{
					ReadDatabase checkBeforeDelete = new ReadDatabase(connection.getMysqlConnection());
					ResultSet rs = checkBeforeDelete.getData("SELECT _id FROM pic_cards WHERE hall_id = '" + hallID + "'");
					while (rs.next())
					{
						isCardEmpty = false;
						break;
					}

					if (!isCardEmpty)
					{
						displayError.showMessageDialog("<html>Seçtiğiniz bölümde bitki mevcut.<br>Ekili bitkileri silmeden bu bölümü silemezsiniz.</html>",
								"Hata", JOptionPane.WARNING_MESSAGE);
					}
					else
					{
						WriteDatabase deleteHall = new WriteDatabase(connection.getMysqlConnection());
						deleteHall.executeQuery("DELETE FROM hall WHERE _id = '" + hallID + "' LIMIT 1");

						// Create USERLOG
						deleteHall.executeQuery("INSERT INTO user_log(user_id, login_time, user_process) VALUES('" + _id + "', '" + CreateTime.getCurrentTime()
								+ "', '" + hallID + " numaralı bölümü sildi.')");
						
						//	populate Table
						//createTable();
						dispose();
					}
				}
				catch (SQLException e1)
				{
					e1.printStackTrace();
				}
			}
		});
		contentPane.add(btnSil);

		JButton btnIptal = createButton.generateButton("İptal", 10, 240);
		btnIptal.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				dispose();
			}
		});
		contentPane.add(btnIptal);
	}
	
	private void createTable() throws SQLException
	{
		int counter = 0;
		Object[] tableHeader = new String[]{"ID", "Kayıt Tarihi"};
		
		ReadDatabase getHallList = new ReadDatabase(connection.getMysqlConnection());
		ResultSet rsCounter = getHallList.getData("SELECT COUNT(_id) FROM hall");

		while (rsCounter.next())
		{
			counter = rsCounter.getInt(1);
		}

		Object[][] data = new Object[counter][2];

		counter = 0;
		
		ResultSet rs = getHallList.getData("SELECT _id, add_date FROM hall ORDER BY _id ASC");
		while (rs.next())
		{
			data[counter][0] = rs.getInt(1);
			data[counter][1] = rs.getDate(2);

			counter++;
		}

		table = new JTable(data, tableHeader);
		//	Tablodaki basliklari duzenle
		
		table.getColumnModel().getColumn(0).setPreferredWidth(35);
		table.getColumnModel().getColumn(1).setPreferredWidth(172);
		
		//	Tablodaki verileri secilebilir yap
		table.setEnabled(true);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		//	Sadece her seferinde tek bir kayit secilmesine izin ver
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//	Sort
		table.setAutoCreateRowSorter(true);
		table.setBounds(10, 69, 414, 151);

		JScrollPane scrollPane = new JScrollPane(table);
		//	ScrollPane olculeri table ile ayni olmak zorunda
		scrollPane.setBounds(10, 69, 414, 151);
		contentPane.add(scrollPane);
		//	Ekrani guncelle
		validate();
	}

	@Override
	public void setUserID(int _id)
	{
		this._id = _id;
	}

	@Override
	public void setUserName(String user_name)
	{
		lblUsername.setText(user_name);
	}

	@Override
	public void setAuthID(int auth_id)
	{
		// Auto-generated method stub
	}
}