package main_app;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
import ui.CreateLabel;
import ui.CreateSeparator;
import utils.ComboBoxItem;
import utils.CreateTime;
import utils.DisplayError;
import utils.ItemRenderer;
import utils.LoginDataDisplay;
import db_process.ConnectDatabase;
import db_process.ReadDatabase;
import db_process.WriteDatabase;

public class AddNewPicCard extends JFrame implements LoginDataDisplay
{
	private static final long		serialVersionUID	= 2494461427189987440L;
	private JPanel					contentPane;
	private JTextField				textField;
	private JComboBox<ComboBoxItem>	bitkiList;

	private JLabel					lblUsername;
	private JButton					btnNewButton;

	private ConnectDatabase			connection;
	private CreateLabel				createLabel;
	private CreateButton			createButton;
	private CreateSeparator			createSeparator;

	private int						_id;
	private boolean					isAdded				= false;

	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					AddNewPicCard frame = new AddNewPicCard();
					frame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	public AddNewPicCard()
	{
		try
		{
			// Create DB connection
			connection = new ConnectDatabase(true);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		setTitle("Yeni Kart Ekle");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(300, 300, 450, 252);
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

		lblUsername = createLabel.generateLabel("", true, 1, 3, 15, 224, 11, 200, 20);
		contentPane.add(lblUsername);
		contentPane.add(createLabel.generateLabel(CreateTime.getCurrentTime(), true, 1, 3, 13, 224, 31, 200, 20));
		contentPane.add(createSeparator.generateSeparator(10, 56, 414));
		contentPane.add(createLabel.generateLabel("Bitki ID", false, 1, 1, 13, 10, 72, 75, 25));
		contentPane.add(createLabel.generateLabel("<html><font color='red'>Uyarı</font>: Bitki ID pic kartı ile <u>aynı</u> olmak zorundadır.</html>", true, 1,
				1, 12, 145, 76, 279, 14));
		contentPane.add(createLabel.generateLabel("Bitki Adı", false, 1, 1, 13, 10, 108, 75, 25));
		contentPane.add(createSeparator.generateSeparator(10, 156, 414));

		textField = new JTextField();
		textField.setBounds(75, 72, 60, 25);
		textField.addKeyListener(new KeyAdapter()
		{
			public void keyReleased(KeyEvent e)
			{
				try
				{
					Integer.parseInt(textField.getText().toString());
				}
				catch(NumberFormatException e1)
				{
					textField.setText("");
				}
			}

			public void keyTyped(KeyEvent e)
			{
			}

			public void keyPressed(KeyEvent e)
			{
			}
		});
		contentPane.add(textField);

		try
		{
			getPlantList();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		btnNewButton = createButton.generateButton("Kayıt", 304, 169);
		btnNewButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				boolean isCardEmpty = true;
				String bitki_adi = "";

				ComboBoxItem x = (ComboBoxItem) bitkiList.getSelectedItem();

				Connection connect = connection.getMysqlConnection();
				ReadDatabase readDatabase = new ReadDatabase(connect);

				// If user not save data yet
				if (!isAdded)
				{
					try
					{
						if((textField.getText().length() == 0) || (Integer.parseInt(textField.getText()) > 0))
						{
							ResultSet rs = readDatabase.getData("SELECT b.bitki_adi FROM pic_cards c LEFT JOIN bitki b ON c.bitki_id = b._id WHERE c.pic_id = '"
									+ textField.getText().toLowerCase() + "' LIMIT 1");
							while (rs.next())
							{
								isCardEmpty = false;
								bitki_adi = rs.getString(1);
							}

							if (!isCardEmpty)
							{
								DisplayError displayError = new DisplayError(contentPane);
								displayError
										.showMessageDialog("<html>Seçtiğiniz kartta " + bitki_adi
												+ " isimli bitki zaten mevcut.<br>Bu kartı silmeden yeni bitki ekleyemezsiniz.</html>", "Hata",
												JOptionPane.WARNING_MESSAGE);
							}
							else
							{
								WriteDatabase writeDatabase = new WriteDatabase(connect);
								writeDatabase.executeQuery("INSERT INTO pic_cards(pic_id, bitki_id) VALUES('" + textField.getText().toLowerCase() + "', '"
										+ x.get_id() + "')");
								writeDatabase.executeQuery("INSERT INTO user_log(user_id, login_time, user_process) VALUES('" + _id + "', '"
										+ CreateTime.getCurrentTime() + "', '" + x.getBitki_adi() + " isimli bitkinin " + textField.getText().toLowerCase()
										+ " numaralı karta kayıt işlemini yaptı.')");

								// Change button value
								btnNewButton.setText("Gönder");
								isAdded = true;
							}
						}
					}
					catch (SQLException e)
					{
						e.printStackTrace();
					}
				}
				else
				{
					dispose();
				}

			}
		});
		contentPane.add(btnNewButton);

		JButton btnIptal = createButton.generateButton("İptal", 10, 169);
		btnIptal.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				dispose();
			}
		});
		contentPane.add(btnIptal);

		contentPane.revalidate();
		contentPane.repaint();
	}
	@SuppressWarnings("unchecked")
	private void getPlantList() throws SQLException
	{
		bitkiList = new JComboBox<ComboBoxItem>();
		bitkiList.setRenderer(new ItemRenderer());
		bitkiList.setBounds(75, 108, 175, 25);

		Connection connect = connection.getMysqlConnection();
		ReadDatabase readDatabase = new ReadDatabase(connect);
		ResultSet rs = readDatabase.getData("SELECT _id, bitki_adi FROM bitki ORDER BY bitki_adi ASC");

		while (rs.next())
		{
			bitkiList.addItem(new ComboBoxItem(rs.getInt(1), rs.getString(2)));
		}

		contentPane.add(bitkiList);
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