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
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import ui.CreateButton;
import ui.CreateLabel;
import ui.CreateSeparator;
import utils.AuthConverter;
import utils.CreateTime;
import utils.DisplayError;
import utils.LoginDataDisplay;
import db_process.ConnectDatabase;
import db_process.ReadDatabase;
import db_process.WriteDatabase;

public class UserManagment extends JFrame implements LoginDataDisplay
{
	private static final long	serialVersionUID	= -3892496455332751876L;
	private JPanel				contentPane;
	private JTable				table;
	private DefaultTableModel	dtm;

	private JLabel				lblUsername;

	private ConnectDatabase		connection			= null;
	private DisplayError		displayError;
	private CreateLabel			createLabel;
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
					UserManagment frame = new UserManagment();
					frame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	public UserManagment()
	{
		try
		{
			connection = new ConnectDatabase(true);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		setTitle("Kullanıcı Düzenle");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(300, 300, 881, 440);
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
		// Hata mesajı nesnesini olustur.
		displayError = new DisplayError(contentPane);

		String[] tableHeader = new String[]{"ID", "Ad Soyad", "Yetki", "Login ID", "Mail Adresi", "Telefon", "Kay\u0131t Tarihi"};

		table = new JTable();
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		dtm = new DefaultTableModel(0, 0);
		dtm.setColumnIdentifiers(tableHeader);

		// Table nesnesine model ekle
		table.setModel(dtm);
		// Bilgileri al
		getUserList(dtm);

		table.setSurrendersFocusOnKeystroke(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setBounds(10, 69, 746, 268);
		contentPane.add(table);

		contentPane.add(createLabel.generateLabel(CreateTime.getCurrentTime(), true, 1, 3, 13, 655, 33, 200, 20));
		lblUsername = createLabel.generateLabel("", true, 1, 3, 15, 655, 11, 200, 20);
		contentPane.add(lblUsername);
		contentPane.add(createSeparator.generateSeparator(10, 56, 845));
		contentPane.add(createSeparator.generateSeparator(10, 348, 845));

		JButton btnDuzenle = new JButton("Düzenle");
		btnDuzenle.addActionListener(new ActionListener()
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
					Object str = table.getValueAt(x, 0);

					UserEdit editUser = new UserEdit();
					editUser.setUserID(_id);
					editUser.setUserName(user_name);
					editUser.setUserIdForEdit(str);
					editUser.setVisible(true);
				}
			}
		});
		btnDuzenle.setBounds(766, 69, 89, 23);
		contentPane.add(btnDuzenle);

		JButton btnSl = new JButton("Sil");
		btnSl.addActionListener(new ActionListener()
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
					int deleteUser = displayError.showConfirmDialog("Bu kullaniciyi silmek istediginizden eminmisiniz?", "Uyari", JOptionPane.YES_NO_OPTION);

					if (deleteUser == JOptionPane.YES_OPTION)
					{
						Object str = table.getValueAt(x, 0);
						Object userNameFormTable = table.getValueAt(x, 1);

						// Once kullaniciyi sil
						String queryForDelete = "DELETE FROM user WHERE _id = '" + str + "' LIMIT 1";
						WriteDatabase deleteUserFromDatabase = new WriteDatabase(connection.getMysqlConnection());
						deleteUserFromDatabase.executeQuery(queryForDelete);

						// Yapilan islemi Log kaydi yap
						String queryForLog = "INSERT INTO user_log(user_id, login_time, user_process) VALUES('" + _id + "', '" + CreateTime.getCurrentTime()
								+ "', '" + userNameFormTable + " isimli kullaniciyi sildi.')";
						deleteUserFromDatabase.executeQuery(queryForLog);
						// Pencereyi kapat
						dispose();
					}
				}
			}
		});
		btnSl.setBounds(766, 103, 89, 23);
		contentPane.add(btnSl);

		JButton button = createButton.generateButton("İPTAL", 735, 361);
		button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				dispose();
			}
		});
		contentPane.add(button);
	}

	private void getUserList(DefaultTableModel dtm)
	{
		String query = "SELECT _id, user_name, auth_id, user_id, user_mail, user_phone, user_register FROM user ORDER BY user_name ASC";

		try
		{
			ReadDatabase getUserList = new ReadDatabase(connection.getMysqlConnection());

			ResultSet rs = getUserList.getData(query);

			while (rs.next())
			{
				dtm.addRow(new Object[]{rs.getInt(1), rs.getString(2), AuthConverter.convertIdToReadableAuth(rs.getInt(3)), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getDate(7)});
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