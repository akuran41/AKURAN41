package main_app;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import ui.CreateButton;
import ui.CreateLabel;
import ui.CreateSeparator;
import utils.LoginDataDisplay;
import db_process.ConnectDatabase;
//import org.eclipse.wb.swing.FocusTraversalOnArray;

public class PicCardManagment extends JFrame implements LoginDataDisplay
{
	private static final long	serialVersionUID	= 7163907576378064509L;
	private JPanel				contentPane;

	private JLabel				lblUsername;

	private ConnectDatabase		connection;
	private CreateLabel			createLabel;
	private CreateButton		createButton;
	private CreateSeparator		createSeparator;

	private int					_id;

	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					PicCardManagment frame = new PicCardManagment();
					frame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	public PicCardManagment()
	{
		setResizable(false);
		setTitle("Panel Düzenle");
		try
		{
			// Create DB connection
			connection = new ConnectDatabase(true);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(300, 300, 793, 349);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(567, 11, 200, 20);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(567, 30, 200, 20);
		contentPane.add(lblNewLabel_1);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 50, 757, 2);
		contentPane.add(separator);

		JButton btnNewButton = new JButton("Düzenle");
		btnNewButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
			}
		});
		btnNewButton.setBounds(678, 61, 89, 23);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Sil");
		btnNewButton_1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
			}
		});
		btnNewButton_1.setBounds(678, 95, 89, 23);
		contentPane.add(btnNewButton_1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 61, 658, 189);
		contentPane.add(scrollPane);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 261, 757, 2);
		contentPane.add(separator_1);

		JButton btnNewButton_2 = new JButton("İptal");
		btnNewButton_2.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				dispose();
			}
		});
		btnNewButton_2.setBounds(678, 274, 89, 23);
		contentPane.add(btnNewButton_2);
		//setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{contentPane, lblNewLabel, lblNewLabel_1, btnNewButton, btnNewButton_1, scrollPane, separator, separator_1, btnNewButton_2}));

		// Etiket nesnesi olustur
		createLabel = new CreateLabel();
		// Dugme nesnesini olustur
		createButton = new CreateButton();
		// Separator nesnesi olustur
		createSeparator = new CreateSeparator();

		//
		// contentPane.add(lblUsername);
		// contentPane.add(createLabel.generateLabel(CreateTime.getCurrentTime(),
		// true, 1, 3, 13, 224, 31, 200, 20));
		// contentPane.add(createSeparator.generateSeparator(10, 56, 414));
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