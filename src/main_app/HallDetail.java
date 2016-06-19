package main_app;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import ui.CreateButton;
import ui.CreateInput;
import ui.CreateLabel;
import ui.CreateSeparator;
import utils.LoginDataDisplay;
import db_process.ConnectDatabase;
import db_process.ReadDatabase;

public class HallDetail extends JFrame implements LoginDataDisplay
{
	private static final long	serialVersionUID	= 5386948261042263411L;
	private JPanel				contentPane;
	private JPanel				standardDataPanel;
	private JComboBox<String>	comboBox;
	private JLabel				lblSystemHour;
	private JLabel				lblSystemMinute;

	private CompoundBorder		compoundBorder;

	private ConnectDatabase		connection;
	private CreateLabel			createLabel;
	private CreateInput			createInput;
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
					HallDetail frame = new HallDetail();
					frame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	public HallDetail()
	{
		setResizable(false);
		setTitle("Bölüm Yönetimi");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(10, 10, 1600, 827);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Etiket nesnesi olustur
		createLabel = new CreateLabel();
		// Input nesnesi olustur
		createInput = new CreateInput();
		// Dugme nesnesini olustur
		createButton = new CreateButton();
		// Separator nesnesi olustur
		createSeparator = new CreateSeparator();

		// Border for Label
		compoundBorder = new CompoundBorder(new LineBorder(new Color(0, 0, 0)), new EmptyBorder(10, 5, 10, 0));

		standardDataPanel = new JPanel();
		standardDataPanel.setBackground(Color.WHITE);
		standardDataPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		standardDataPanel.setBounds(10, 11, 817, 685);
		standardDataPanel.setLayout(null);
		contentPane.add(standardDataPanel);

		tableHeaders();
		tableCells();
		
		try
		{
			connection = new ConnectDatabase(true);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		JLabel label = new JLabel("0");
		label.setFont(new Font("Tahoma", Font.BOLD, 13));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBackground(new Color(141, 180, 226));
		label.setBounds(109, 80, 100, 35);
		label.setBorder(compoundBorder);
		label.setOpaque(true);
		standardDataPanel.add(label);

		JLabel lblNewLabel_2 = new JLabel("0");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBackground(new Color(141, 180, 226));
		lblNewLabel_2.setBounds(109, 114, 100, 35);
		lblNewLabel_2.setBorder(compoundBorder);
		lblNewLabel_2.setOpaque(true);
		standardDataPanel.add(lblNewLabel_2);

		JLabel label_1 = new JLabel("0");
		label_1.setOpaque(true);
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_1.setBorder(compoundBorder);
		label_1.setBackground(new Color(141, 180, 226));
		label_1.setBounds(109, 148, 100, 35);
		standardDataPanel.add(label_1);

		JLabel label_2 = new JLabel("0");
		label_2.setOpaque(true);
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_2.setBorder(compoundBorder);
		label_2.setBackground(new Color(141, 180, 226));
		label_2.setBounds(109, 182, 100, 35);
		standardDataPanel.add(label_2);

		JLabel label_3 = new JLabel("0");
		label_3.setOpaque(true);
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_3.setBorder(compoundBorder);
		label_3.setBackground(new Color(141, 180, 226));
		label_3.setBounds(109, 216, 100, 35);
		standardDataPanel.add(label_3);

		JLabel label_4 = new JLabel("0");
		label_4.setOpaque(true);
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_4.setBorder(compoundBorder);
		label_4.setBackground(new Color(141, 180, 226));
		label_4.setBounds(109, 250, 100, 35);
		standardDataPanel.add(label_4);

		JLabel label_5 = new JLabel("0");
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_5.setBorder(compoundBorder);
		label_5.setBounds(208, 80, 100, 35);
		standardDataPanel.add(label_5);

		JLabel label_6 = new JLabel("0");
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		label_6.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_6.setBorder(compoundBorder);
		label_6.setBounds(208, 114, 100, 35);
		standardDataPanel.add(label_6);

		JLabel label_7 = new JLabel("0");
		label_7.setHorizontalAlignment(SwingConstants.CENTER);
		label_7.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_7.setBorder(compoundBorder);
		label_7.setBounds(208, 148, 100, 35);
		standardDataPanel.add(label_7);

		JLabel label_8 = new JLabel("0");
		label_8.setHorizontalAlignment(SwingConstants.CENTER);
		label_8.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_8.setBorder(compoundBorder);
		label_8.setBounds(208, 182, 100, 35);
		standardDataPanel.add(label_8);

		JLabel label_9 = new JLabel("0");
		label_9.setHorizontalAlignment(SwingConstants.CENTER);
		label_9.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_9.setBorder(compoundBorder);
		label_9.setBounds(208, 216, 100, 35);
		standardDataPanel.add(label_9);

		JLabel label_10 = new JLabel("0");
		label_10.setHorizontalAlignment(SwingConstants.CENTER);
		label_10.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_10.setBorder(compoundBorder);
		label_10.setBounds(208, 250, 100, 35);
		standardDataPanel.add(label_10);

		JLabel label_17 = new JLabel("0");
		label_17.setOpaque(true);
		label_17.setHorizontalAlignment(SwingConstants.CENTER);
		label_17.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_17.setBorder(compoundBorder);
		label_17.setBackground(new Color(141, 180, 226));
		label_17.setBounds(406, 80, 100, 35);
		standardDataPanel.add(label_17);

		JLabel label_18 = new JLabel("0");
		label_18.setOpaque(true);
		label_18.setHorizontalAlignment(SwingConstants.CENTER);
		label_18.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_18.setBorder(compoundBorder);
		label_18.setBackground(new Color(141, 180, 226));
		label_18.setBounds(406, 114, 100, 35);
		standardDataPanel.add(label_18);

		JLabel label_19 = new JLabel("0");
		label_19.setOpaque(true);
		label_19.setHorizontalAlignment(SwingConstants.CENTER);
		label_19.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_19.setBorder(compoundBorder);
		label_19.setBackground(new Color(141, 180, 226));
		label_19.setBounds(406, 148, 100, 35);
		standardDataPanel.add(label_19);

		JLabel label_20 = new JLabel("0");
		label_20.setOpaque(true);
		label_20.setHorizontalAlignment(SwingConstants.CENTER);
		label_20.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_20.setBorder(compoundBorder);
		label_20.setBackground(new Color(141, 180, 226));
		label_20.setBounds(406, 182, 100, 35);
		standardDataPanel.add(label_20);

		JLabel label_21 = new JLabel("0");
		label_21.setOpaque(true);
		label_21.setHorizontalAlignment(SwingConstants.CENTER);
		label_21.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_21.setBorder(compoundBorder);
		label_21.setBackground(new Color(141, 180, 226));
		label_21.setBounds(406, 216, 100, 35);
		standardDataPanel.add(label_21);

		JLabel label_22 = new JLabel("0");
		label_22.setOpaque(true);
		label_22.setHorizontalAlignment(SwingConstants.CENTER);
		label_22.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_22.setBorder(compoundBorder);
		label_22.setBackground(new Color(141, 180, 226));
		label_22.setBounds(406, 250, 100, 35);
		standardDataPanel.add(label_22);

		JLabel label_23 = new JLabel("0");
		label_23.setHorizontalAlignment(SwingConstants.CENTER);
		label_23.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_23.setBorder(compoundBorder);
		label_23.setBounds(505, 80, 100, 35);
		standardDataPanel.add(label_23);

		JLabel label_24 = new JLabel("0");
		label_24.setHorizontalAlignment(SwingConstants.CENTER);
		label_24.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_24.setBorder(compoundBorder);
		label_24.setBounds(505, 114, 100, 35);
		standardDataPanel.add(label_24);

		JLabel label_25 = new JLabel("0");
		label_25.setHorizontalAlignment(SwingConstants.CENTER);
		label_25.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_25.setBorder(compoundBorder);
		label_25.setBounds(505, 148, 100, 35);
		standardDataPanel.add(label_25);

		JLabel label_26 = new JLabel("0");
		label_26.setHorizontalAlignment(SwingConstants.CENTER);
		label_26.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_26.setBorder(compoundBorder);
		label_26.setBounds(505, 182, 100, 35);
		standardDataPanel.add(label_26);

		JLabel label_27 = new JLabel("0");
		label_27.setHorizontalAlignment(SwingConstants.CENTER);
		label_27.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_27.setBorder(compoundBorder);
		label_27.setBounds(505, 216, 100, 35);
		standardDataPanel.add(label_27);

		JLabel label_28 = new JLabel("0");
		label_28.setHorizontalAlignment(SwingConstants.CENTER);
		label_28.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_28.setBorder(compoundBorder);
		label_28.setBounds(505, 250, 100, 35);
		standardDataPanel.add(label_28);

		JLabel label_32 = new JLabel("0");
		label_32.setHorizontalAlignment(SwingConstants.CENTER);
		label_32.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_32.setBorder(compoundBorder);
		label_32.setBounds(703, 182, 100, 35);
		standardDataPanel.add(label_32);

		JLabel label_33 = new JLabel("0");
		label_33.setHorizontalAlignment(SwingConstants.CENTER);
		label_33.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_33.setBorder(compoundBorder);
		label_33.setBounds(703, 216, 100, 35);
		standardDataPanel.add(label_33);

		JLabel label_34 = new JLabel("0");
		label_34.setHorizontalAlignment(SwingConstants.CENTER);
		label_34.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_34.setBorder(compoundBorder);
		label_34.setBounds(703, 250, 100, 35);
		standardDataPanel.add(label_34);

		JButton btnEkle = new JButton("Ekle");
		btnEkle.setBounds(714, 497, 89, 23);
		standardDataPanel.add(btnEkle);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(837, 11, 737, 685);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
	}
	
	public void setHallID(Object hallID)
	{
		try
		{
			setStaticCells(hallID);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	private void tableHeaders()
	{
		standardDataPanel.add(createLabel.generateLabel("Set Değeri", false, 1, 2, 11, 109, 57, 100, 20));
		standardDataPanel.add(createLabel.generateLabel("Okunan Değer", false, 1, 2, 11, 208, 57, 100, 20));
		standardDataPanel.add(createLabel.generateLabel("Set Değeri", false, 1, 2, 11, 406, 57, 100, 20));
		standardDataPanel.add(createLabel.generateLabel("Okunan Değer", false, 1, 2, 11, 505, 57, 100, 20));
	}

	private void tableCells()
	{
		JLabel lblBlmId = new JLabel("Bölüm ID");
		lblBlmId.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblBlmId.setBounds(10, 10, 75, 35);
		lblBlmId.setBorder(compoundBorder);
		standardDataPanel.add(lblBlmId);

		JLabel lblNewLabel_1 = new JLabel("Sistem Saat");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(385, 10, 100, 35);
		lblNewLabel_1.setBorder(compoundBorder);
		standardDataPanel.add(lblNewLabel_1);

		JLabel lblNewLabel = new JLabel("Sistem Dakika");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(583, 10, 100, 35);
		lblNewLabel.setBorder(compoundBorder);
		standardDataPanel.add(lblNewLabel);

		JLabel lblOrtamIsisi = new JLabel("Ortam Isisi");
		lblOrtamIsisi.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblOrtamIsisi.setBounds(10, 80, 100, 35);
		lblOrtamIsisi.setBorder(compoundBorder);
		standardDataPanel.add(lblOrtamIsisi);

		JLabel lblOrtamNem = new JLabel("Ortam Nem");
		lblOrtamNem.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblOrtamNem.setBounds(10, 114, 100, 35);
		lblOrtamNem.setBorder(compoundBorder);
		standardDataPanel.add(lblOrtamNem);

		JLabel lblCansuyuIsisi = new JLabel("Cansuyu Isisi");
		lblCansuyuIsisi.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCansuyuIsisi.setBounds(10, 148, 100, 35);
		lblCansuyuIsisi.setBorder(compoundBorder);
		standardDataPanel.add(lblCansuyuIsisi);

		JLabel lblOOrani = new JLabel("O2 Orani");
		lblOOrani.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblOOrani.setBounds(10, 182, 100, 35);
		lblOOrani.setBorder(compoundBorder);
		standardDataPanel.add(lblOOrani);

		JLabel lblCoOrani = new JLabel("CO2 Orani");
		lblCoOrani.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCoOrani.setBounds(10, 216, 100, 35);
		lblCoOrani.setBorder(compoundBorder);
		standardDataPanel.add(lblCoOrani);

		JLabel lblIsikSiddeti = new JLabel("Isik Siddeti");
		lblIsikSiddeti.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblIsikSiddeti.setBounds(10, 250, 100, 35);
		lblIsikSiddeti.setBorder(compoundBorder);
		standardDataPanel.add(lblIsikSiddeti);

		JLabel lblCansuyuPh = new JLabel("Cansuyu PH");
		lblCansuyuPh.setHorizontalAlignment(SwingConstants.CENTER);
		lblCansuyuPh.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCansuyuPh.setBorder(compoundBorder);
		lblCansuyuPh.setBounds(307, 80, 100, 35);
		standardDataPanel.add(lblCansuyuPh);

		JLabel lblAGidaOlcum = new JLabel("A gida Olcum");
		lblAGidaOlcum.setHorizontalAlignment(SwingConstants.CENTER);
		lblAGidaOlcum.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblAGidaOlcum.setBorder(compoundBorder);
		lblAGidaOlcum.setBounds(307, 114, 100, 35);
		standardDataPanel.add(lblAGidaOlcum);

		JLabel lblBGidaOlcum = new JLabel("B gida Olcum");
		lblBGidaOlcum.setHorizontalAlignment(SwingConstants.CENTER);
		lblBGidaOlcum.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblBGidaOlcum.setBorder(compoundBorder);
		lblBGidaOlcum.setBounds(307, 148, 100, 35);
		standardDataPanel.add(lblBGidaOlcum);

		JLabel lblCGidaOlcum = new JLabel("C gida Olcum");
		lblCGidaOlcum.setHorizontalAlignment(SwingConstants.CENTER);
		lblCGidaOlcum.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCGidaOlcum.setBorder(compoundBorder);
		lblCGidaOlcum.setBounds(307, 182, 100, 35);
		standardDataPanel.add(lblCGidaOlcum);

		JLabel lblIsitmaVana = new JLabel("Isitma Vana");
		lblIsitmaVana.setHorizontalAlignment(SwingConstants.CENTER);
		lblIsitmaVana.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblIsitmaVana.setBorder(compoundBorder);
		lblIsitmaVana.setBounds(307, 216, 100, 35);
		standardDataPanel.add(lblIsitmaVana);

		JLabel lblSogVana = new JLabel("Sog. Vana");
		lblSogVana.setHorizontalAlignment(SwingConstants.CENTER);
		lblSogVana.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSogVana.setBorder(compoundBorder);
		lblSogVana.setBounds(307, 250, 100, 35);
		standardDataPanel.add(lblSogVana);

		JLabel lblKirmiziIsik = new JLabel("Kirmizi Isik");
		lblKirmiziIsik.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblKirmiziIsik.setBorder(compoundBorder);
		lblKirmiziIsik.setBounds(604, 182, 100, 35);
		standardDataPanel.add(lblKirmiziIsik);

		JLabel lblMaviIsik = new JLabel("Mavi Isik");
		lblMaviIsik.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblMaviIsik.setBorder(compoundBorder);
		lblMaviIsik.setBounds(604, 216, 100, 35);
		standardDataPanel.add(lblMaviIsik);

		JLabel lblGunisigi = new JLabel("Gunisigi");
		lblGunisigi.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblGunisigi.setBorder(compoundBorder);
		lblGunisigi.setBounds(604, 250, 100, 35);
		standardDataPanel.add(lblGunisigi);
	}

	private void setStaticCells(Object hallID) throws SQLException
	{
		JLabel lblHallid = createLabel.generateLabel("" + hallID, false, 2, 2, 13, 84, 10, 75, 35);
		lblHallid.setBorder(compoundBorder);
		standardDataPanel.add(lblHallid);

		lblSystemHour = createLabel.generateLabel("", false, 2, 2, 13, 484, 10, 100, 35);
		lblSystemHour.setBorder(compoundBorder);
		standardDataPanel.add(lblSystemHour);

		lblSystemMinute = createLabel.generateLabel("", false, 2, 2, 13, 682, 10, 121, 35);
		lblSystemMinute.setBorder(compoundBorder);
		standardDataPanel.add(lblSystemMinute);

		standardDataPanel.add(createSeparator.generateSeparator(10, 296, 793));

		final SimpleDateFormat formatHour = new SimpleDateFormat("HH");
		final SimpleDateFormat formatMins = new SimpleDateFormat("mm");

		Timer timer = new Timer(1000, new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				lblSystemHour.setText("" + formatHour.format(new Date()));
				lblSystemMinute.setText("" + formatMins.format(new Date()));

				lblSystemHour.repaint();
				lblSystemMinute.repaint();
			}

		});

		timer.setInitialDelay(0);
		timer.start();

		comboBox = new JComboBox<String>();
		comboBox.setBounds(169, 10, 206, 35);
		standardDataPanel.add(comboBox);
		
		ReadDatabase getPlants = new ReadDatabase(connection.getMysqlConnection());
		ResultSet rs = getPlants.getData("SELECT bitki_adi FROM bitki ORDER BY bitki_adi ASC");
		while(rs.next())
		{
			comboBox.addItem(rs.getString(1));
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