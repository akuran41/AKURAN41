package main_app;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import project_const.WindowArgs;
import ui.CreateButton;
import ui.CreateLabel;
import ui.CreateSeparator;
import db_process.CreateDatabase;

public class DatabaseErrorScreen extends JFrame
{
	private static final long	serialVersionUID	= -2785046605912548643L;
	private JPanel				contentPane;
	private static DatabaseErrorScreen	frame;

	private JButton				btnCancel;
	private JButton				btnSetup;
	private JLabel				lblNewLabel;

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
		setTitle("Veritabanı Hatası");
		setResizable(WindowArgs._ISRESIZE);
		setAlwaysOnTop(WindowArgs._ONTOP);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(WindowArgs._WINDOWX, WindowArgs._WINDOWY, 541, 254);
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

		contentPane.add(createLabel.generateLabel("<html>Veritabanı bulunamadı.<br>Veritabanı oluşturmak için <b>OLUŞTUR</b> düğmesine, programı sonlandırmak için <b>İPTAL</b> düğmesine basınız.</html>", false, 1, 1, 15, 10, 11, 530, 70));
		contentPane.add(createSeparator.generateSeparator(10, 174, 512));
		

		btnCancel = createButton.generateButton("İptal", 402, 187);
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
		contentPane.add(btnCancel);

		btnSetup = createButton.generateButton("Oluştur", 10, 187);
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
		contentPane.add(btnSetup);

		lblNewLabel = createLabel.generateLabel("<html>Veritabanı oluşturuldu. <b>KAPAT</b> düğmesine basarak sistemi kapatıp, yeniden başlatın.</html>", true, 2, 1, 15, 10, 110, 512, 40);
		lblNewLabel.setVisible(false);
		contentPane.add(lblNewLabel);
	}
}