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
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ui.CreateButton;
import ui.CreateLabel;
import ui.CreateSeparator;
import utils.ErrorLog;
import utils.LoginDataDisplay;
import db_process.ConnectDatabase;
import db_process.ReadDatabase;

public class PanelManagment extends JFrame implements LoginDataDisplay
{
	private static final long	serialVersionUID	= 7163907576378064509L;

	private ErrorLog			errorLog			= null;

	private JPanel				contentPane;
	private JComboBox<Integer>	comboBox;

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
					PanelManagment frame = new PanelManagment();
					frame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	public PanelManagment()
	{
		errorLog = new ErrorLog();
		
		setResizable(false);
		setTitle("Panel Yönetimi");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(300, 300, 358, 116);
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

		contentPane.add(createLabel.generateLabel("Panel yönetimi için lütfen bir bölüm seçiniz.", true, 1, 1, 13, 10, 11, 263, 20));
		contentPane.add(createSeparator.generateSeparator(10, 44, 335));

		comboBox = new JComboBox<Integer>();
		comboBox.setBounds(274, 11, 70, 23);
		contentPane.add(comboBox);

		// JButton btnIptal = createButton.generateButton("İptal", 225, 50);
		JButton btnIptal = createButton.generateButton("İptal", 10, 54);
		btnIptal.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				dispose();
			}
		});
		contentPane.add(btnIptal);

		JButton btnOk = createButton.generateButton("Düzenle", 225, 50);
		btnOk.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				Object itemSelected = comboBox.getSelectedItem();

				HallDetail hallDetail = new HallDetail();
				hallDetail.setHallID(itemSelected);
				hallDetail.setUserID(_id);
				hallDetail.setUserName(user_name);
				hallDetail.setVisible(true);

				dispose();
			}
		});
		contentPane.add(btnOk);

		try
		{
			getHallId();
		}
		catch (SQLException e)
		{
			errorLog.generateLog(e);
		}
	}

	private void getHallId() throws SQLException
	{
		ConnectDatabase connectDatabase = new ConnectDatabase(true);
		Connection connection = connectDatabase.getMysqlConnection();

		ReadDatabase readDatabase = new ReadDatabase(connection);
		ResultSet rs = readDatabase.getData("SELECT _id FROM hall ORDER BY _id ASC");

		// Add null Value to ComboBox
		comboBox.addItem(null);

		while (rs.next())
		{
			comboBox.addItem(rs.getInt(1));
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
	}

	@Override
	public void setAuthID(int auth_id)
	{
		// Auto-generated method stub
	}
}