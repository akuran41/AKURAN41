package main_app;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
import utils.DisplayError;
import utils.LoginDataDisplay;
import db_process.ConnectDatabase;
import db_process.ReadDatabase;
import db_process.WriteDatabase;
import files.FileUtility;

public class PlantManagment extends JFrame implements LoginDataDisplay
{
	private static final long	serialVersionUID	= -3598167150109127981L;
	private JPanel				contentPane;

	private JLabel				lblUsername;

	private ConnectDatabase		connection			= null;
	private DisplayError		displayError;
	private CreateLabel			createLabel;
	private CreateButton		createButton;
	private CreateSeparator		createSeparator;

	private int					_id;
	private String				user_name;
	private JTable				table;

	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					PlantManagment frame = new PlantManagment();
					frame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	public PlantManagment()
	{
		try
		{
			// Create DB connection
			connection = new ConnectDatabase(true);
		}
		catch (SQLException e1)
		{
			e1.printStackTrace();
		}

		setResizable(false);
		setTitle("Bitki Yönetimi");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(300, 300, 874, 429);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		// Etiket nesnesi olustur
		createLabel = new CreateLabel();
		// Dugme nesnesini olustur
		createButton = new CreateButton();
		// Separator nesnesi olustur
		createSeparator = new CreateSeparator();
		// Hata mesajı nesnesini olustur.
		displayError = new DisplayError(contentPane);
		contentPane.setLayout(null);

		lblUsername = createLabel.generateLabel("", true, 1, 3, 15, 655, 11, 200, 20);
		contentPane.add(lblUsername);
		contentPane.add(createLabel.generateLabel(CreateTime.getCurrentTime(), true, 1, 3, 13, 655, 33, 200, 20));
		contentPane.add(createSeparator.generateSeparator(10, 56, 845));
		contentPane.add(createSeparator.generateSeparator(10, 350, 845));

		JButton btnDzenle = new JButton("Düzenle");
		btnDzenle.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				int x = table.getSelectedRow();

				if (x == -1)
				{
					displayError.showMessageDialog("En az bir kayıt seçmelisiniz.", "Hata", JOptionPane.WARNING_MESSAGE);
				}
				else
				{
					Object str = table.getValueAt(x, 0);

					PlantEdit editUser = new PlantEdit();
					editUser.setUserID(_id);
					editUser.setUserName(user_name);
					editUser.setPlantIdForEdit(str);
					editUser.setVisible(true);
					// Pencereyi kapat
					dispose();
				}
			}
		});
		btnDzenle.setBounds(766, 64, 89, 23);
		contentPane.add(btnDzenle);

		JButton btnSil = new JButton("Sil");
		btnSil.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				int x = table.getSelectedRow();

				if (x == -1)
				{
					displayError.showMessageDialog("En az bir kayıt seçmelisiniz.", "Hata", JOptionPane.WARNING_MESSAGE);
				}
				else
				{
					int deleteUser = displayError.showConfirmDialog("Bu bitkiyi silmek istediginizden eminmisiniz?", "Uyari", JOptionPane.YES_NO_OPTION);

					if (deleteUser == JOptionPane.YES_OPTION)
					{
						Object str = table.getValueAt(x, 0);
						Object userNameFormTable = table.getValueAt(x, 1);
						
						String fileName = "";
						
						try
						{
							ReadDatabase readDatabase = new ReadDatabase(connection.getMysqlConnection());
							ResultSet rs = readDatabase.getData("SELECT bitki_resim FROM bitki WHERE _id = '" + str + "' LIMIT 1");
							while(rs.next())
							{
								fileName = rs.getString(1);
							}
						}
						catch (SQLException e1)
						{
							e1.printStackTrace();
						}

						// Once bitkiyi sil
						WriteDatabase deleteUserFromDatabase = new WriteDatabase(connection.getMysqlConnection());
						deleteUserFromDatabase.executeQuery("DELETE FROM bitki WHERE _id = '" + str + "' LIMIT 1");
						
						//	Sonra varsa dosyayi sil
						if(fileName.length() > 4)
						{
							File fileToDelete = new File(FileUtility.getImageFolder() + "\\" + fileName);
							fileToDelete.delete();
						}

						// Yapilan islemi Log kaydi yap
						String queryForLog = "INSERT INTO user_log(user_id, login_time, user_process) VALUES('" + _id + "', '" + CreateTime.getCurrentTime()
								+ "', '" + userNameFormTable + " isimli bitkiyi sildi.')";
						deleteUserFromDatabase.executeQuery(queryForLog);
						// Pencereyi kapat
						dispose();
					}
				}
			}
		});
		btnSil.setBounds(766, 98, 89, 23);
		contentPane.add(btnSil);

		try
		{
			getRecords();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		JButton btnNewButton = createButton.generateButton("İptal", 736, 363);
		btnNewButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				dispose();
			}
		});
		contentPane.add(btnNewButton);
	}

	private void getRecords() throws SQLException
	{
		int counter = 0;

		Object[] tableHeader = new String[]{"ID", "Bitki Adı", "Cinsi", "Türü", "Ülkesi", "Ekme Zamanı", "Hasat Zamanı", "Süre"};

		// Find out how many records we have
		ReadDatabase readDatabase = new ReadDatabase(connection.getMysqlConnection());
		ResultSet rsCounter = readDatabase.getData("SELECT COUNT(_id) FROM bitki");
		while (rsCounter.next())
		{
			counter = rsCounter.getInt(1);
		}

		Object[][] data = new Object[counter][8];
		// make counter 0
		counter = 0;

		ResultSet rs = readDatabase
				.getData("SELECT _id, bitki_adi, bitki_cinsi, bitki_turu, bitki_ulke, bitki_ekme_zamani, bitki_hasat_zamani, bitki_yetisme_suresi FROM bitki ORDER BY _id ASC");
		while (rs.next())
		{
			data[counter][0] = rs.getInt(1);
			data[counter][1] = rs.getString(2);
			data[counter][2] = rs.getString(3);
			data[counter][3] = rs.getString(4);
			data[counter][4] = rs.getString(5);
			data[counter][5] = rs.getDate(6);
			data[counter][6] = rs.getDate(7);
			data[counter][7] = rs.getInt(8) + " gün";

			counter++;
		}

		table = new JTable(data, tableHeader);
		// Tablodaki verileri secilebilir yap
		table.setEnabled(true);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		// Sadece her seferinde tek bir kayit secilmesine izin ver
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// Sort
		table.setAutoCreateRowSorter(true);
		table.setBounds(10, 64, 746, 275);

		JScrollPane scrollPane = new JScrollPane(table);
		// ScrollPane olculeri table ile ayni olmak zorunda
		scrollPane.setBounds(10, 64, 746, 275);
		contentPane.add(scrollPane);
		// Ekrani guncelle
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
		this.user_name = user_name;
		this.lblUsername.setText(user_name);
	}

	@Override
	public void setAuthID(int auth_id)
	{
		// Auto-generated method stub
	}
}