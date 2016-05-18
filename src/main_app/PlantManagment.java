package main_app;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import ui.CreateButton;
import ui.CreateLabel;
import ui.CreateSeparator;
import utils.DisplayError;
import utils.LoginDataDisplay;
import db_process.ConnectDatabase;

public class PlantManagment extends JFrame implements LoginDataDisplay
{
	private static final long	serialVersionUID	= -3598167150109127981L;
	private JPanel				contentPane;
	private JTable				table;

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
		setResizable(false);
		setTitle("Bitki Yönetimi");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(300, 300, 874, 429);
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

		JLabel label = new JLabel("user_name");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setForeground(Color.RED);
		label.setFont(new Font("Tahoma", Font.BOLD, 15));
		label.setBounds(655, 11, 200, 20);
		contentPane.add(label);

		JLabel label_1 = new JLabel("2016/05/15 22:06");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setForeground(Color.RED);
		label_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_1.setBounds(655, 33, 200, 20);
		contentPane.add(label_1);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 56, 845, 2);
		contentPane.add(separator);

		JButton btnDzenle = new JButton("Düzenle");
		btnDzenle.setBounds(766, 64, 89, 23);
		contentPane.add(btnDzenle);

		JButton btnSil = new JButton("Sil");
		btnSil.setBounds(766, 98, 89, 23);
		contentPane.add(btnSil);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][]{{"1", "a", "b"}, {"2", "2a", "2b"},}, new String[]{"No", "A", "B"}));
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.setBounds(10, 69, 746, 240);
		contentPane.add(table);
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