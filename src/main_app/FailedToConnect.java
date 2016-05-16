package main_app;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import mysql.SetupMySQL;
import project_const.WindowArgs;
import ui.CreateButton;
import ui.CreateLabel;
import ui.CreateSeparator;

public class FailedToConnect extends JFrame
{
	private static final long	serialVersionUID	= 2051907402208490919L;

	private JPanel				contentPane;
	private JButton				btnCancel;
	private JButton				btnRun;
	private JButton				btnSetup;

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
					DatabaseErrorScreen frame = new DatabaseErrorScreen();
					frame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	public FailedToConnect()
	{
		setTitle("Bağlantı Hatası");
		setResizable(WindowArgs._ISRESIZE);
		setAlwaysOnTop(WindowArgs._ONTOP);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(WindowArgs._WINDOWX, WindowArgs._WINDOWY, 542, 323);
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

		contentPane.add(createLabel.generateLabel("<html>MySQL veritabanına bağlanmaya çalışılırken hata oluştu.<br>Bu durumun olası sebepleri :</html>", false, 1, 1, 15, 10, 11, 424, 70));
		contentPane.add(createLabel.generateLabel("1 - Sisteminizde MySQL veritabanı kurulu olmayabilir.", false, 1, 1, 15, 10, 70, 424, 30));
		contentPane.add(createLabel.generateLabel("2 - MySQL veritabanı şuan için çalışmıyor olabilir.", false, 1, 1, 15, 10, 93, 424, 30));
		contentPane.add(createSeparator.generateSeparator(10, 242, 512));
		contentPane.add(createLabel.generateLabel("<html>Eğer sisteminizde MySQL kurulu ise <b>ÇALIŞTIR</b>, değil ise <b>KUR</b> düğmesine basınız.", false, 1, 1, 15, 10, 125, 519, 40));
		contentPane.add(createLabel.generateLabel("<html>Kurulum tamamlandıktan sonra, <b><i><u>Sadece</u></i></b> MySQL <b>Kullanıcı adı</b> ve <b>Şifre</b> sini<br><font color='red'><u><i><b>\"C:\\Windows\\sera\\mysera.ini\"</b></i></u></font> dosyasının içine yazınız.</html>", false, 1, 1, 13, 10, 192, 512, 35));

		btnCancel = createButton.generateButton("İPTAL", 402, 255);
		btnCancel.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				// Execute Garbage Collection
				System.gc();
				// Exit System
				System.exit(1);
			}
		});
		contentPane.add(btnCancel);

		btnRun = createButton.generateButton("ÇALIŞTIR", 205, 255);
		btnRun.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				// MySQL servisi calistir
				startMySQL();

				btnRun.setVisible(false);
				btnSetup.setVisible(false);
				btnCancel.setText("KAPAT");
			}
		});
		contentPane.add(btnRun);

		btnSetup = createButton.generateButton("KUR", 10, 255);
		btnSetup.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				try
				{
					new SetupMySQL();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}

				btnRun.setVisible(false);
				btnSetup.setVisible(false);
				btnCancel.setText("KAPAT");
			}
		});
		contentPane.add(btnSetup);
	}

	private void startMySQL()
	{
		Runtime r = Runtime.getRuntime();

		String[] mysql = {"cmd.exe", "/c", "sc", "start", "mysql"};// to start
																	// service
		try
		{
			r.exec(mysql);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
