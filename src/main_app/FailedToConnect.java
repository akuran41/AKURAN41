package main_app;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;

import project_const.ButtonArgs;
import project_const.WindowArgs;
import mysql.SetupMySQL;
import java.awt.Color;

public class FailedToConnect extends JFrame
{
	private static final long	serialVersionUID	= 2051907402208490919L;

	private JPanel				contentPane;
	private JButton				btnCancel;
	private JButton				btnRun;
	private JButton				btnSetup;

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
		setTitle("Baglanti Hatasi");
		setResizable(WindowArgs._ISRESIZE);
		setAlwaysOnTop(WindowArgs._ONTOP);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(WindowArgs._WINDOWX, WindowArgs._WINDOWY, 556, 335);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblMysqlVeriTabanina = new JLabel("<html>MySQL veri tabanina baglanmaya calisilirken hata olustu.<br>Bu durumun olasi sebepleri :</html>");
		lblMysqlVeriTabanina.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMysqlVeriTabanina.setBounds(10, 11, 424, 70);
		getContentPane().add(lblMysqlVeriTabanina);

		JLabel lblSisteminizde = new JLabel("1 - Sisteminizde MySQL veri tabani kurulu olmayabilir.");
		lblSisteminizde.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSisteminizde.setBounds(10, 70, 424, 30);
		getContentPane().add(lblSisteminizde);

		JLabel lblMysql = new JLabel("2 - MySQL veritabani suan icin calismiyor olabilir.");
		lblMysql.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMysql.setBounds(10, 93, 424, 30);
		getContentPane().add(lblMysql);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 242, 512, 2);
		getContentPane().add(separator);

		JLabel lblEgerSisteminizdeMysql = new JLabel("<html>Eger sisteminizde MySQL kurulu ise <b>CALISTIR</b>, degil ise <b>KUR</b> dugmesine basiniz.");
		lblEgerSisteminizdeMysql.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEgerSisteminizdeMysql.setBounds(10, 125, 519, 40);
		getContentPane().add(lblEgerSisteminizdeMysql);

		btnCancel = new JButton("IPTAL");
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
		btnCancel.setFont(new Font(ButtonArgs._FONT, Font.PLAIN, ButtonArgs._SIZE));
		btnCancel.setBounds(402, 255, ButtonArgs._WIDTH, ButtonArgs._HEIGHT);
		getContentPane().add(btnCancel);

		btnRun = new JButton("CALISTIR");
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
		btnRun.setFont(new Font(ButtonArgs._FONT, Font.PLAIN, ButtonArgs._SIZE));
		btnRun.setBounds(205, 255, ButtonArgs._WIDTH, ButtonArgs._HEIGHT);
		getContentPane().add(btnRun);

		btnSetup = new JButton("KUR");
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
		btnSetup.setFont(new Font(ButtonArgs._FONT, Font.PLAIN, ButtonArgs._SIZE));
		btnSetup.setBounds(10, 255, ButtonArgs._WIDTH, ButtonArgs._HEIGHT);
		getContentPane().add(btnSetup);

		JLabel lblKurulumTamamlandiktanSonra = new JLabel(
				"<html>Kurulum tamamlandiktan sonra, <b><i><u>Sadece</u></i></b> MySQL <b>Kullanici adi</b> ve <b>Sifre</b> sini<br><font color='red'><u><i><b>\"C:\\Windows\\sera\\mysera.ini\"</b></i></u></font> dosyasinin icine yaziniz.</html>");
		lblKurulumTamamlandiktanSonra.setForeground(Color.BLACK);
		lblKurulumTamamlandiktanSonra.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblKurulumTamamlandiktanSonra.setBounds(10, 192, 512, 35);
		contentPane.add(lblKurulumTamamlandiktanSonra);
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
