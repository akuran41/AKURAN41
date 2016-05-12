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
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;

import project_const.WindowArgs;
import db_process.CreateDatabase;

public class DatabaseErrorScreen extends JFrame
{
	private static final long	serialVersionUID	= -2785046605912548643L;
	private JPanel				contentPane;
	private static DatabaseErrorScreen	frame;

	private JButton				btnCancel;
	private JButton				btnSetup;
	private JLabel				lblNewLabel;

	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					frame = new DatabaseErrorScreen();
					frame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	public DatabaseErrorScreen()
	{
		setTitle("Veritabani Hatasi");
		setResizable(WindowArgs._ISRESIZE);
		setAlwaysOnTop(WindowArgs._ONTOP);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(WindowArgs._WINDOWX, WindowArgs._WINDOWY, 556, 265);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblMysqlVeriTabanina = new JLabel(
				"<html>Veritabani bulunamadi.<br>Veritabani olusturmak icin <b>OLUSTUR</b> dugmesine, programi sonlandirmak icin <b>IPTAL</b> dugmesine basiniz.</html>");
		lblMysqlVeriTabanina.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMysqlVeriTabanina.setBounds(10, 11, 530, 70);
		getContentPane().add(lblMysqlVeriTabanina);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 174, 512, 2);
		getContentPane().add(separator);

		btnCancel = new JButton("IPTAL");
		btnCancel.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				// Execute Garbage Collection
				System.gc();
				// Exit System
				System.exit(1);
			}
		});
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCancel.setBounds(402, 187, 120, 30);
		getContentPane().add(btnCancel);

		btnSetup = new JButton("OLUSTUR");
		btnSetup.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				// Eger veritabani yoksa o zaman yenisini olustur.

				try
				{
					// Veritabani olustur daha sonra sistemden
					new CreateDatabase();
				}
				catch (SQLException e1)
				{
					e1.printStackTrace();
				}

				// Display Message
				lblNewLabel.setVisible(true);
				// Olustur dugmesini iptal et
				btnSetup.setVisible(false);
				// Iptal dugmesini KAPAT olarak degistir.
				btnCancel.setText("KAPAT");
			}
		});
		btnSetup.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSetup.setBounds(10, 187, 120, 30);
		getContentPane().add(btnSetup);

		lblNewLabel = new JLabel("<html>Veri tabani olusturuldu. <b>KAPAT</b> dugmesine basarak sistemi kapatip, yeniden baslatin.</html>");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(10, 110, 512, 40);
		contentPane.add(lblNewLabel);
		lblNewLabel.setVisible(false);
	}
}