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
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import project_const.ButtonArgs;
import ui.CreateLabel;
import ui.CreateSeparator;
import utils.ErrorLog;
import utils.LoginDataDisplay;
import db_process.ConnectDatabase;
import db_process.ReadDatabase;

public class HallDetail extends JFrame implements LoginDataDisplay
{
	private static final long	serialVersionUID	= 5386948261042263411L;

	private ErrorLog			errorLog			= null;

	private JPanel				contentPane;
	private JPanel				standardDataPanel;
	private JComboBox<String>	comboBox;
	private JLabel				lblSystemHour;
	private JLabel				lblSystemMinute;

	private CompoundBorder		compoundBorder;
	private CompoundBorder		compoundBorderForInput;

	private ConnectDatabase		connection;
	private CreateLabel			createLabel;
	private CreateSeparator		createSeparator;
	private HallList			hallList;

	private int					_id;
	private int					bolumID;
	private boolean				isVisible			= false;

	private JLabel				ortamIsiSet;
	private JLabel				ortamIsiOkunan;
	private JLabel				ortamNemSet;
	private JLabel				ortamNemOkunan;
	private JLabel				canSuyuSet;
	private JLabel				canSuyuOkunan;
	private JLabel				o2Set;
	private JLabel				o2Okunan;
	private JLabel				co2Set;
	private JLabel				co2Okunan;
	private JLabel				isikSet;
	private JLabel				isikOkunan;
	private JLabel				canSuyuPhSet;
	private JLabel				canSuyuPhOkunan;
	private JLabel				aGidaSet;
	private JLabel				aGidaOkunan;
	private JLabel				bGidaSet;
	private JLabel				bGidaOkunan;
	private JLabel				cGidaSet;
	private JLabel				cGidaOkunan;
	private JLabel				isitmaVanaSet;
	private JLabel				isitmaVanaOkunan;
	private JLabel				sogutmaVanaSet;
	private JLabel				sogutmaVanaOkunan;
	private JLabel				isikKirmizi;
	private JLabel				isikMavi;
	private JLabel				dayLight;

	private JTextField			canSuyuBaslaSaat1;
	private JTextField			canSuyuBaslaDakika1;
	private JTextField			textField;
	private JTextField			textField_1;
	private JTextField			textField_2;
	private JTextField			textField_3;
	private JTextField			textField_4;
	private JTextField			textField_5;
	private JTextField			textField_6;
	private JTextField			textField_7;
	private JTextField			textField_8;
	private JTextField			textField_9;
	private JTextField			textField_10;
	private JTextField			textField_11;
	private JTextField			textField_12;
	private JTextField			textField_13;
	private JTextField			textField_14;
	private JTextField			textField_15;
	private JTextField			textField_16;
	private JTextField			textField_17;
	private JTextField			textField_18;
	private JTextField			textField_19;
	private JTextField			textField_20;
	private JTextField			textField_21;
	private JTextField			textField_22;
	private JTextField			textField_23;
	private JTextField			textField_24;
	private JTextField			textField_25;
	private JTextField			textField_26;
	private JTextField			textField_27;
	private JTextField			textField_28;
	private JTextField			textField_29;
	private JTextField			textField_30;
	private JTextField			textField_31;
	private JTextField			textField_32;
	private JTextField			textField_33;
	private JTextField			textField_34;
	private JTextField			textField_35;
	private JTextField			textField_36;
	private JTextField			textField_37;
	private JTextField			textField_38;
	private JTextField			textField_39;
	private JTextField			textField_40;
	private JTextField			textField_41;
	private JTextField			textField_42;
	private JTextField			textField_43;
	private JTextField			textField_44;
	private JTextField			textField_45;
	private JTextField			textField_46;
	private JTextField			textField_47;

	private JButton				btnDetayGoster;

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
		errorLog = new ErrorLog();

		setResizable(false);
		setTitle("Bölüm Yönetimi");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(10, 10, 845, 641);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Etiket nesnesi olustur
		createLabel = new CreateLabel();
		// Separator nesnesi olustur
		createSeparator = new CreateSeparator();

		// Border for Label
		compoundBorder = new CompoundBorder(new LineBorder(new Color(0, 0, 0)), new EmptyBorder(10, 5, 10, 0));
		// Border for Input
		compoundBorderForInput = new CompoundBorder(new LineBorder(new Color(0, 0, 0)), new EmptyBorder(5, 5, 5, 5));

		standardDataPanel = new JPanel();
		standardDataPanel.setBackground(Color.WHITE);
		standardDataPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		standardDataPanel.setBounds(10, 11, 817, 583);
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
			errorLog.generateLog(e);
		}

		// Detail Pane
		hallList = new HallList();
		hallList.setUserID(_id);

		canSuyuBaslaSaat1 = new JTextField();
		canSuyuBaslaSaat1.setHorizontalAlignment(SwingConstants.RIGHT);
		canSuyuBaslaSaat1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		canSuyuBaslaSaat1.setBounds(10, 359, 75, 30);
		canSuyuBaslaSaat1.setBorder(compoundBorderForInput);
		standardDataPanel.add(canSuyuBaslaSaat1);

		canSuyuBaslaDakika1 = new JTextField();
		canSuyuBaslaDakika1.setHorizontalAlignment(SwingConstants.RIGHT);
		canSuyuBaslaDakika1.setBounds(84, 359, 76, 30);
		canSuyuBaslaDakika1.setBorder(compoundBorderForInput);
		standardDataPanel.add(canSuyuBaslaDakika1);

		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.RIGHT);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField.setBorder(compoundBorderForInput);
		textField.setBounds(10, 388, 75, 30);
		standardDataPanel.add(textField);

		textField_1 = new JTextField();
		textField_1.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_1.setBorder(compoundBorderForInput);
		textField_1.setBounds(84, 388, 76, 30);
		standardDataPanel.add(textField_1);

		textField_2 = new JTextField();
		textField_2.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_2.setBorder(compoundBorderForInput);
		textField_2.setBounds(10, 417, 75, 30);
		standardDataPanel.add(textField_2);

		textField_3 = new JTextField();
		textField_3.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_3.setBorder(compoundBorderForInput);
		textField_3.setBounds(84, 417, 76, 30);
		standardDataPanel.add(textField_3);

		textField_4 = new JTextField();
		textField_4.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_4.setBorder(compoundBorderForInput);
		textField_4.setBounds(10, 446, 75, 30);
		standardDataPanel.add(textField_4);

		textField_5 = new JTextField();
		textField_5.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_5.setBorder(compoundBorderForInput);
		textField_5.setBounds(84, 446, 76, 30);
		standardDataPanel.add(textField_5);

		textField_6 = new JTextField();
		textField_6.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_6.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_6.setBorder(compoundBorderForInput);
		textField_6.setBounds(10, 475, 75, 30);
		standardDataPanel.add(textField_6);

		textField_7 = new JTextField();
		textField_7.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_7.setBorder(compoundBorderForInput);
		textField_7.setBounds(84, 475, 76, 30);
		standardDataPanel.add(textField_7);

		textField_8 = new JTextField();
		textField_8.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_8.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_8.setBorder(compoundBorderForInput);
		textField_8.setBounds(170, 359, 75, 30);
		standardDataPanel.add(textField_8);

		textField_9 = new JTextField();
		textField_9.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_9.setBorder(compoundBorderForInput);
		textField_9.setBounds(244, 359, 76, 30);
		standardDataPanel.add(textField_9);

		textField_10 = new JTextField();
		textField_10.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_10.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_10.setBorder(compoundBorderForInput);
		textField_10.setBounds(170, 388, 75, 30);
		standardDataPanel.add(textField_10);

		textField_11 = new JTextField();
		textField_11.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_11.setBorder(compoundBorderForInput);
		textField_11.setBounds(244, 388, 76, 30);
		standardDataPanel.add(textField_11);

		textField_12 = new JTextField();
		textField_12.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_12.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_12.setBorder(compoundBorderForInput);
		textField_12.setBounds(170, 417, 75, 30);
		standardDataPanel.add(textField_12);

		textField_13 = new JTextField();
		textField_13.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_13.setBorder(compoundBorderForInput);
		textField_13.setBounds(244, 417, 76, 30);
		standardDataPanel.add(textField_13);

		textField_14 = new JTextField();
		textField_14.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_14.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_14.setBorder(compoundBorderForInput);
		textField_14.setBounds(170, 446, 75, 30);
		standardDataPanel.add(textField_14);

		textField_15 = new JTextField();
		textField_15.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_15.setBorder(compoundBorderForInput);
		textField_15.setBounds(244, 446, 76, 30);
		standardDataPanel.add(textField_15);

		textField_16 = new JTextField();
		textField_16.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_16.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_16.setBorder(compoundBorderForInput);
		textField_16.setBounds(170, 475, 75, 30);
		standardDataPanel.add(textField_16);

		textField_17 = new JTextField();
		textField_17.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_17.setBorder(compoundBorderForInput);
		textField_17.setBounds(244, 475, 76, 30);
		standardDataPanel.add(textField_17);

		textField_18 = new JTextField();
		textField_18.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_18.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_18.setBorder(compoundBorderForInput);
		textField_18.setBounds(330, 359, 75, 30);
		standardDataPanel.add(textField_18);

		textField_19 = new JTextField();
		textField_19.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_19.setBorder(compoundBorderForInput);
		textField_19.setBounds(404, 359, 76, 30);
		standardDataPanel.add(textField_19);

		textField_20 = new JTextField();
		textField_20.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_20.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_20.setBorder(compoundBorderForInput);
		textField_20.setBounds(330, 388, 75, 30);
		standardDataPanel.add(textField_20);

		textField_21 = new JTextField();
		textField_21.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_21.setBorder(compoundBorderForInput);
		textField_21.setBounds(404, 388, 76, 30);
		standardDataPanel.add(textField_21);

		textField_22 = new JTextField();
		textField_22.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_22.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_22.setBorder(compoundBorderForInput);
		textField_22.setBounds(330, 417, 75, 30);
		standardDataPanel.add(textField_22);

		textField_23 = new JTextField();
		textField_23.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_23.setBorder(compoundBorderForInput);
		textField_23.setBounds(404, 417, 76, 30);
		standardDataPanel.add(textField_23);

		textField_24 = new JTextField();
		textField_24.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_24.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_24.setBorder(compoundBorderForInput);
		textField_24.setBounds(330, 446, 75, 30);
		standardDataPanel.add(textField_24);

		textField_25 = new JTextField();
		textField_25.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_25.setBorder(compoundBorderForInput);
		textField_25.setBounds(404, 446, 76, 30);
		standardDataPanel.add(textField_25);

		textField_26 = new JTextField();
		textField_26.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_26.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_26.setBorder(compoundBorderForInput);
		textField_26.setBounds(330, 475, 75, 30);
		standardDataPanel.add(textField_26);

		textField_27 = new JTextField();
		textField_27.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_27.setBorder(compoundBorderForInput);
		textField_27.setBounds(404, 475, 76, 30);
		standardDataPanel.add(textField_27);

		textField_28 = new JTextField();
		textField_28.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_28.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_28.setBorder(compoundBorderForInput);
		textField_28.setBounds(490, 359, 75, 30);
		standardDataPanel.add(textField_28);

		textField_29 = new JTextField();
		textField_29.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_29.setBorder(compoundBorderForInput);
		textField_29.setBounds(564, 359, 76, 30);
		standardDataPanel.add(textField_29);

		textField_30 = new JTextField();
		textField_30.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_30.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_30.setBorder(compoundBorderForInput);
		textField_30.setBounds(490, 388, 75, 30);
		standardDataPanel.add(textField_30);

		textField_31 = new JTextField();
		textField_31.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_31.setBorder(compoundBorderForInput);
		textField_31.setBounds(564, 388, 76, 30);
		standardDataPanel.add(textField_31);

		textField_32 = new JTextField();
		textField_32.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_32.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_32.setBorder(compoundBorderForInput);
		textField_32.setBounds(490, 417, 75, 30);
		standardDataPanel.add(textField_32);

		textField_33 = new JTextField();
		textField_33.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_33.setBorder(compoundBorderForInput);
		textField_33.setBounds(564, 417, 76, 30);
		standardDataPanel.add(textField_33);

		textField_34 = new JTextField();
		textField_34.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_34.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_34.setBorder(compoundBorderForInput);
		textField_34.setBounds(490, 446, 75, 30);
		standardDataPanel.add(textField_34);

		textField_35 = new JTextField();
		textField_35.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_35.setBorder(compoundBorderForInput);
		textField_35.setBounds(564, 446, 76, 30);
		standardDataPanel.add(textField_35);

		textField_36 = new JTextField();
		textField_36.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_36.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_36.setBorder(compoundBorderForInput);
		textField_36.setBounds(490, 475, 75, 30);
		standardDataPanel.add(textField_36);

		textField_37 = new JTextField();
		textField_37.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_37.setBorder(compoundBorderForInput);
		textField_37.setBounds(564, 475, 76, 30);
		standardDataPanel.add(textField_37);

		textField_38 = new JTextField();
		textField_38.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_38.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_38.setBorder(compoundBorderForInput);
		textField_38.setBounds(653, 359, 75, 30);
		standardDataPanel.add(textField_38);

		textField_39 = new JTextField();
		textField_39.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_39.setBorder(compoundBorderForInput);
		textField_39.setBounds(727, 359, 76, 30);
		standardDataPanel.add(textField_39);

		textField_40 = new JTextField();
		textField_40.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_40.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_40.setBorder(compoundBorderForInput);
		textField_40.setBounds(653, 388, 75, 30);
		standardDataPanel.add(textField_40);

		textField_41 = new JTextField();
		textField_41.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_41.setBorder(compoundBorderForInput);
		textField_41.setBounds(727, 388, 76, 30);
		standardDataPanel.add(textField_41);

		textField_42 = new JTextField();
		textField_42.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_42.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_42.setBorder(compoundBorderForInput);
		textField_42.setBounds(653, 417, 75, 30);
		standardDataPanel.add(textField_42);

		textField_43 = new JTextField();
		textField_43.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_43.setBorder(compoundBorderForInput);
		textField_43.setBounds(727, 417, 76, 30);
		standardDataPanel.add(textField_43);

		textField_44 = new JTextField();
		textField_44.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_44.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_44.setBorder(compoundBorderForInput);
		textField_44.setBounds(653, 446, 75, 30);
		standardDataPanel.add(textField_44);

		textField_45 = new JTextField();
		textField_45.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_45.setBorder(compoundBorderForInput);
		textField_45.setBounds(727, 446, 76, 30);
		standardDataPanel.add(textField_45);

		textField_46 = new JTextField();
		textField_46.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_46.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_46.setBorder(compoundBorderForInput);
		textField_46.setBounds(653, 475, 75, 30);
		standardDataPanel.add(textField_46);

		textField_47 = new JTextField();
		textField_47.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_47.setBorder(compoundBorderForInput);
		textField_47.setBounds(727, 475, 76, 30);
		standardDataPanel.add(textField_47);

		btnDetayGoster = new JButton("Detay Göster");
		btnDetayGoster.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (!isVisible)
				{
					btnDetayGoster.setText("Detay Gizle");
					hallList.setBolumID(bolumID, _id);
					hallList.setVisible(true);
				}
				else
				{
					btnDetayGoster.setText("Detay Göster");
					hallList.dispose();
				}

				isVisible = !isVisible;
			}
		});
		btnDetayGoster.setFont(new Font(ButtonArgs._FONT, Font.PLAIN, ButtonArgs._SIZE));
		btnDetayGoster.setBounds(170, 516, 150, 52);
		standardDataPanel.add(btnDetayGoster);

		JButton btnCancel = new JButton("İptal");
		btnCancel.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				hallList.dispose();
				dispose();
			}
		});
		btnCancel.setFont(new Font(ButtonArgs._FONT, Font.PLAIN, ButtonArgs._SIZE));
		btnCancel.setBounds(653, 516, 150, 52);
		standardDataPanel.add(btnCancel);

		JButton btnEkle = new JButton("Ekle");
		btnEkle.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				
			}
		});
		btnEkle.setFont(new Font(ButtonArgs._FONT, Font.PLAIN, ButtonArgs._SIZE));
		btnEkle.setBounds(10, 516, 150, 52);
		standardDataPanel.add(btnEkle);
	}

	public void setHallID(Object hallID)
	{
		try
		{
			setStaticCells(hallID);
		}
		catch (SQLException e)
		{
			errorLog.generateLog(e);
		}
	}

	private void tableHeaders()
	{
		standardDataPanel.add(createLabel.generateLabel("Set Değeri", false, 1, 2, 11, 109, 57, 100, 20));
		standardDataPanel.add(createLabel.generateLabel("Okunan Değer", false, 1, 2, 11, 208, 57, 100, 20));
		standardDataPanel.add(createLabel.generateLabel("Set Değeri", false, 1, 2, 11, 406, 57, 100, 20));
		standardDataPanel.add(createLabel.generateLabel("Okunan Değer", false, 1, 2, 11, 505, 57, 100, 20));

		ortamIsiSet = new JLabel("0");
		ortamIsiSet.setFont(new Font("Tahoma", Font.BOLD, 13));
		ortamIsiSet.setHorizontalAlignment(SwingConstants.CENTER);
		ortamIsiSet.setBackground(new Color(141, 180, 226));
		ortamIsiSet.setBounds(109, 80, 100, 35);
		ortamIsiSet.setBorder(compoundBorder);
		ortamIsiSet.setOpaque(true);
		standardDataPanel.add(ortamIsiSet);

		ortamIsiOkunan = new JLabel("0");
		ortamIsiOkunan.setHorizontalAlignment(SwingConstants.CENTER);
		ortamIsiOkunan.setFont(new Font("Tahoma", Font.BOLD, 13));
		ortamIsiOkunan.setBorder(compoundBorder);
		ortamIsiOkunan.setBounds(208, 80, 100, 35);
		standardDataPanel.add(ortamIsiOkunan);

		ortamNemSet = new JLabel("0");
		ortamNemSet.setFont(new Font("Tahoma", Font.BOLD, 13));
		ortamNemSet.setHorizontalAlignment(SwingConstants.CENTER);
		ortamNemSet.setBackground(new Color(141, 180, 226));
		ortamNemSet.setBounds(109, 114, 100, 35);
		ortamNemSet.setBorder(compoundBorder);
		ortamNemSet.setOpaque(true);
		standardDataPanel.add(ortamNemSet);

		ortamNemOkunan = new JLabel("0");
		ortamNemOkunan.setHorizontalAlignment(SwingConstants.CENTER);
		ortamNemOkunan.setFont(new Font("Tahoma", Font.BOLD, 13));
		ortamNemOkunan.setBorder(compoundBorder);
		ortamNemOkunan.setBounds(208, 114, 100, 35);
		standardDataPanel.add(ortamNemOkunan);

		canSuyuSet = new JLabel("0");
		canSuyuSet.setOpaque(true);
		canSuyuSet.setHorizontalAlignment(SwingConstants.CENTER);
		canSuyuSet.setFont(new Font("Tahoma", Font.BOLD, 13));
		canSuyuSet.setBorder(compoundBorder);
		canSuyuSet.setBackground(new Color(141, 180, 226));
		canSuyuSet.setBounds(109, 148, 100, 35);
		standardDataPanel.add(canSuyuSet);

		canSuyuOkunan = new JLabel("0");
		canSuyuOkunan.setHorizontalAlignment(SwingConstants.CENTER);
		canSuyuOkunan.setFont(new Font("Tahoma", Font.BOLD, 13));
		canSuyuOkunan.setBorder(compoundBorder);
		canSuyuOkunan.setBounds(208, 148, 100, 35);
		standardDataPanel.add(canSuyuOkunan);

		o2Set = new JLabel("0");
		o2Set.setOpaque(true);
		o2Set.setHorizontalAlignment(SwingConstants.CENTER);
		o2Set.setFont(new Font("Tahoma", Font.BOLD, 13));
		o2Set.setBorder(compoundBorder);
		o2Set.setBackground(new Color(141, 180, 226));
		o2Set.setBounds(109, 182, 100, 35);
		standardDataPanel.add(o2Set);

		o2Okunan = new JLabel("0");
		o2Okunan.setHorizontalAlignment(SwingConstants.CENTER);
		o2Okunan.setFont(new Font("Tahoma", Font.BOLD, 13));
		o2Okunan.setBorder(compoundBorder);
		o2Okunan.setBounds(208, 182, 100, 35);
		standardDataPanel.add(o2Okunan);

		co2Set = new JLabel("0");
		co2Set.setOpaque(true);
		co2Set.setHorizontalAlignment(SwingConstants.CENTER);
		co2Set.setFont(new Font("Tahoma", Font.BOLD, 13));
		co2Set.setBorder(compoundBorder);
		co2Set.setBackground(new Color(141, 180, 226));
		co2Set.setBounds(109, 216, 100, 35);
		standardDataPanel.add(co2Set);

		co2Okunan = new JLabel("0");
		co2Okunan.setHorizontalAlignment(SwingConstants.CENTER);
		co2Okunan.setFont(new Font("Tahoma", Font.BOLD, 13));
		co2Okunan.setBorder(compoundBorder);
		co2Okunan.setBounds(208, 216, 100, 35);
		standardDataPanel.add(co2Okunan);

		isikSet = new JLabel("0");
		isikSet.setOpaque(true);
		isikSet.setHorizontalAlignment(SwingConstants.CENTER);
		isikSet.setFont(new Font("Tahoma", Font.BOLD, 13));
		isikSet.setBorder(compoundBorder);
		isikSet.setBackground(new Color(141, 180, 226));
		isikSet.setBounds(109, 250, 100, 35);
		standardDataPanel.add(isikSet);

		isikOkunan = new JLabel("0");
		isikOkunan.setHorizontalAlignment(SwingConstants.CENTER);
		isikOkunan.setFont(new Font("Tahoma", Font.BOLD, 13));
		isikOkunan.setBorder(compoundBorder);
		isikOkunan.setBounds(208, 250, 100, 35);
		standardDataPanel.add(isikOkunan);

		canSuyuPhSet = new JLabel("0");
		canSuyuPhSet.setOpaque(true);
		canSuyuPhSet.setHorizontalAlignment(SwingConstants.CENTER);
		canSuyuPhSet.setFont(new Font("Tahoma", Font.BOLD, 13));
		canSuyuPhSet.setBorder(compoundBorder);
		canSuyuPhSet.setBackground(new Color(141, 180, 226));
		canSuyuPhSet.setBounds(406, 80, 100, 35);
		standardDataPanel.add(canSuyuPhSet);

		canSuyuPhOkunan = new JLabel("0");
		canSuyuPhOkunan.setHorizontalAlignment(SwingConstants.CENTER);
		canSuyuPhOkunan.setFont(new Font("Tahoma", Font.BOLD, 13));
		canSuyuPhOkunan.setBorder(compoundBorder);
		canSuyuPhOkunan.setBounds(505, 80, 100, 35);
		standardDataPanel.add(canSuyuPhOkunan);

		aGidaSet = new JLabel("0");
		aGidaSet.setOpaque(true);
		aGidaSet.setHorizontalAlignment(SwingConstants.CENTER);
		aGidaSet.setFont(new Font("Tahoma", Font.BOLD, 13));
		aGidaSet.setBorder(compoundBorder);
		aGidaSet.setBackground(new Color(141, 180, 226));
		aGidaSet.setBounds(406, 114, 100, 35);
		standardDataPanel.add(aGidaSet);

		aGidaOkunan = new JLabel("0");
		aGidaOkunan.setHorizontalAlignment(SwingConstants.CENTER);
		aGidaOkunan.setFont(new Font("Tahoma", Font.BOLD, 13));
		aGidaOkunan.setBorder(compoundBorder);
		aGidaOkunan.setBounds(505, 114, 100, 35);
		standardDataPanel.add(aGidaOkunan);

		bGidaSet = new JLabel("0");
		bGidaSet.setOpaque(true);
		bGidaSet.setHorizontalAlignment(SwingConstants.CENTER);
		bGidaSet.setFont(new Font("Tahoma", Font.BOLD, 13));
		bGidaSet.setBorder(compoundBorder);
		bGidaSet.setBackground(new Color(141, 180, 226));
		bGidaSet.setBounds(406, 148, 100, 35);
		standardDataPanel.add(bGidaSet);

		bGidaOkunan = new JLabel("0");
		bGidaOkunan.setHorizontalAlignment(SwingConstants.CENTER);
		bGidaOkunan.setFont(new Font("Tahoma", Font.BOLD, 13));
		bGidaOkunan.setBorder(compoundBorder);
		bGidaOkunan.setBounds(505, 148, 100, 35);
		standardDataPanel.add(bGidaOkunan);

		cGidaSet = new JLabel("0");
		cGidaSet.setOpaque(true);
		cGidaSet.setHorizontalAlignment(SwingConstants.CENTER);
		cGidaSet.setFont(new Font("Tahoma", Font.BOLD, 13));
		cGidaSet.setBorder(compoundBorder);
		cGidaSet.setBackground(new Color(141, 180, 226));
		cGidaSet.setBounds(406, 182, 100, 35);
		standardDataPanel.add(cGidaSet);

		cGidaOkunan = new JLabel("0");
		cGidaOkunan.setHorizontalAlignment(SwingConstants.CENTER);
		cGidaOkunan.setFont(new Font("Tahoma", Font.BOLD, 13));
		cGidaOkunan.setBorder(compoundBorder);
		cGidaOkunan.setBounds(505, 182, 100, 35);
		standardDataPanel.add(cGidaOkunan);

		isitmaVanaSet = new JLabel("0");
		isitmaVanaSet.setOpaque(true);
		isitmaVanaSet.setHorizontalAlignment(SwingConstants.CENTER);
		isitmaVanaSet.setFont(new Font("Tahoma", Font.BOLD, 13));
		isitmaVanaSet.setBorder(compoundBorder);
		isitmaVanaSet.setBackground(new Color(141, 180, 226));
		isitmaVanaSet.setBounds(406, 216, 100, 35);
		standardDataPanel.add(isitmaVanaSet);

		isitmaVanaOkunan = new JLabel("0");
		isitmaVanaOkunan.setHorizontalAlignment(SwingConstants.CENTER);
		isitmaVanaOkunan.setFont(new Font("Tahoma", Font.BOLD, 13));
		isitmaVanaOkunan.setBorder(compoundBorder);
		isitmaVanaOkunan.setBounds(505, 216, 100, 35);
		standardDataPanel.add(isitmaVanaOkunan);

		sogutmaVanaSet = new JLabel("0");
		sogutmaVanaSet.setOpaque(true);
		sogutmaVanaSet.setHorizontalAlignment(SwingConstants.CENTER);
		sogutmaVanaSet.setFont(new Font("Tahoma", Font.BOLD, 13));
		sogutmaVanaSet.setBorder(compoundBorder);
		sogutmaVanaSet.setBackground(new Color(141, 180, 226));
		sogutmaVanaSet.setBounds(406, 250, 100, 35);
		standardDataPanel.add(sogutmaVanaSet);

		sogutmaVanaOkunan = new JLabel("0");
		sogutmaVanaOkunan.setHorizontalAlignment(SwingConstants.CENTER);
		sogutmaVanaOkunan.setFont(new Font("Tahoma", Font.BOLD, 13));
		sogutmaVanaOkunan.setBorder(compoundBorder);
		sogutmaVanaOkunan.setBounds(505, 250, 100, 35);
		standardDataPanel.add(sogutmaVanaOkunan);

		isikKirmizi = new JLabel("0");
		isikKirmizi.setHorizontalAlignment(SwingConstants.CENTER);
		isikKirmizi.setFont(new Font("Tahoma", Font.BOLD, 13));
		isikKirmizi.setBorder(compoundBorder);
		isikKirmizi.setBounds(703, 182, 100, 35);
		standardDataPanel.add(isikKirmizi);

		isikMavi = new JLabel("0");
		isikMavi.setHorizontalAlignment(SwingConstants.CENTER);
		isikMavi.setFont(new Font("Tahoma", Font.BOLD, 13));
		isikMavi.setBorder(compoundBorder);
		isikMavi.setBounds(703, 216, 100, 35);
		standardDataPanel.add(isikMavi);

		dayLight = new JLabel("0");
		dayLight.setHorizontalAlignment(SwingConstants.CENTER);
		dayLight.setFont(new Font("Tahoma", Font.BOLD, 13));
		dayLight.setBorder(compoundBorder);
		dayLight.setBounds(703, 250, 100, 35);
		standardDataPanel.add(dayLight);
	}

	private void tableCells()
	{
		JLabel lblBlmId = createLabel.generateLabel("Bölüm ID", false, 2, 1, 13, 10, 10, 75, 35);
		lblBlmId.setBorder(compoundBorder);
		standardDataPanel.add(lblBlmId);

		JLabel lblNewLabel_1 = createLabel.generateLabel("Sistem Saat", false, 2, 1, 13, 385, 10, 100, 35);
		lblNewLabel_1.setBorder(compoundBorder);
		standardDataPanel.add(lblNewLabel_1);

		JLabel lblNewLabel = createLabel.generateLabel("Sistem Dakika", false, 2, 1, 13, 583, 10, 100, 35);
		lblNewLabel.setBorder(compoundBorder);
		standardDataPanel.add(lblNewLabel);

		JLabel lblOrtamIsisi = createLabel.generateLabel("Ortam Isısı", false, 2, 1, 13, 10, 80, 100, 35);
		lblOrtamIsisi.setBorder(compoundBorder);
		standardDataPanel.add(lblOrtamIsisi);

		JLabel lblOrtamNem = createLabel.generateLabel("Ortam Nem", false, 2, 1, 13, 10, 114, 100, 35);
		lblOrtamNem.setBorder(compoundBorder);
		standardDataPanel.add(lblOrtamNem);

		JLabel lblCansuyuIsisi = createLabel.generateLabel("Cansuyu Isısı", false, 2, 1, 13, 10, 148, 100, 35);
		lblCansuyuIsisi.setBorder(compoundBorder);
		standardDataPanel.add(lblCansuyuIsisi);

		JLabel lblOOrani = createLabel.generateLabel("O2 Oranı", false, 2, 1, 13, 10, 182, 100, 35);
		lblOOrani.setBorder(compoundBorder);
		standardDataPanel.add(lblOOrani);

		JLabel lblCoOrani = createLabel.generateLabel("CO2 Oranı", false, 2, 1, 13, 10, 216, 100, 35);
		lblCoOrani.setBorder(compoundBorder);
		standardDataPanel.add(lblCoOrani);

		JLabel lblIsikSiddeti = createLabel.generateLabel("Işık Şiddeti", false, 2, 1, 13, 10, 250, 100, 35);
		lblIsikSiddeti.setBorder(compoundBorder);
		standardDataPanel.add(lblIsikSiddeti);

		JLabel lblCansuyuPh = createLabel.generateLabel("Cansuyu PH", false, 2, 2, 13, 307, 80, 100, 35);
		lblCansuyuPh.setBorder(compoundBorder);
		standardDataPanel.add(lblCansuyuPh);

		JLabel lblAGidaOlcum = createLabel.generateLabel("A gıda Ölçüm", false, 2, 2, 13, 307, 114, 100, 35);
		lblAGidaOlcum.setBorder(compoundBorder);
		standardDataPanel.add(lblAGidaOlcum);

		JLabel lblBGidaOlcum = createLabel.generateLabel("B gıda Ölçüm", false, 2, 2, 13, 307, 148, 100, 35);
		lblBGidaOlcum.setBorder(compoundBorder);
		standardDataPanel.add(lblBGidaOlcum);

		JLabel lblCGidaOlcum = createLabel.generateLabel("C gıda Ölçüm", false, 2, 2, 13, 307, 182, 100, 35);
		lblCGidaOlcum.setBorder(compoundBorder);
		standardDataPanel.add(lblCGidaOlcum);

		JLabel lblIsitmaVana = createLabel.generateLabel("Isıtma Vana", false, 2, 2, 13, 307, 216, 100, 35);
		lblIsitmaVana.setBorder(compoundBorder);
		standardDataPanel.add(lblIsitmaVana);

		JLabel lblSogVana = createLabel.generateLabel("Soğ. Vana", false, 2, 2, 13, 307, 250, 100, 35);
		lblSogVana.setBorder(compoundBorder);
		standardDataPanel.add(lblSogVana);

		JLabel lblKirmiziIsik = createLabel.generateLabel("Kırmızı Işık", false, 2, 1, 13, 604, 182, 100, 35);
		lblKirmiziIsik.setBorder(compoundBorder);
		standardDataPanel.add(lblKirmiziIsik);

		JLabel lblMaviIsik = createLabel.generateLabel("Mavi Işık", false, 2, 1, 13, 604, 216, 100, 35);
		lblMaviIsik.setBorder(compoundBorder);
		standardDataPanel.add(lblMaviIsik);

		JLabel lblGunisigi = createLabel.generateLabel("Günışğı", false, 2, 1, 13, 604, 250, 100, 35);
		lblGunisigi.setBorder(compoundBorder);
		standardDataPanel.add(lblGunisigi);

		JLabel lblCoBesleme = createLabel.generateLabel("Co2 Besleme", false, 2, 2, 13, 490, 296, 150, 35);
		lblCoBesleme.setBorder(compoundBorder);
		standardDataPanel.add(lblCoBesleme);

		JLabel label_30 = createLabel.generateLabel("SAAT", false, 2, 2, 13, 490, 330, 75, 30);
		label_30.setBorder(compoundBorder);
		standardDataPanel.add(label_30);

		JLabel label_31 = createLabel.generateLabel("DAKİKA", false, 2, 2, 13, 564, 330, 76, 30);
		label_31.setBorder(compoundBorder);
		standardDataPanel.add(label_31);

		JLabel lblSuPskrtme = createLabel.generateLabel("Su Püskürtme", false, 2, 2, 13, 330, 296, 150, 35);
		lblSuPskrtme.setBorder(compoundBorder);
		standardDataPanel.add(lblSuPskrtme);

		JLabel label_15 = createLabel.generateLabel("SAAT", false, 2, 2, 13, 330, 330, 75, 30);
		label_15.setBorder(compoundBorder);
		standardDataPanel.add(label_15);

		JLabel label_16 = createLabel.generateLabel("DAKİKA", false, 2, 2, 13, 404, 330, 76, 30);
		label_16.setBorder(compoundBorder);
		standardDataPanel.add(label_16);

		JLabel label_11 = createLabel.generateLabel("Işık", false, 2, 2, 13, 170, 296, 150, 35);
		label_11.setBorder(compoundBorder);
		standardDataPanel.add(label_11);

		JLabel label_12 = createLabel.generateLabel("SAAT", false, 2, 2, 13, 170, 330, 75, 30);
		label_12.setBorder(compoundBorder);
		standardDataPanel.add(label_12);

		JLabel label_13 = createLabel.generateLabel("DAKİKA", false, 2, 2, 13, 244, 330, 76, 30);
		label_13.setBorder(compoundBorder);
		standardDataPanel.add(label_13);

		JLabel lblNewLabel_3 = createLabel.generateLabel("Cansuyu Pompası", false, 2, 2, 13, 10, 296, 150, 35);
		lblNewLabel_3.setBorder(compoundBorder);
		standardDataPanel.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = createLabel.generateLabel("SAAT", false, 2, 2, 13, 10, 330, 75, 30);
		lblNewLabel_4.setBorder(compoundBorder);
		standardDataPanel.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = createLabel.generateLabel("DAKİKA", false, 2, 2, 13, 84, 330, 76, 30);
		lblNewLabel_5.setBorder(compoundBorder);
		standardDataPanel.add(lblNewLabel_5);

		JLabel lblOBesleme = createLabel.generateLabel("O2 Besleme", false, 2, 2, 13, 653, 296, 150, 35);
		lblOBesleme.setBorder(compoundBorder);
		standardDataPanel.add(lblOBesleme);

		JLabel label_36 = createLabel.generateLabel("SAAT", false, 2, 2, 13, 653, 330, 75, 30);
		label_36.setBorder(compoundBorder);
		standardDataPanel.add(label_36);

		JLabel label_37 = createLabel.generateLabel("DAKİKA", false, 2, 2, 13, 727, 330, 76, 30);
		label_37.setBorder(compoundBorder);
		standardDataPanel.add(label_37);
	}

	private void setStaticCells(Object hallID) throws SQLException
	{
		bolumID = Integer.parseInt("" + hallID);
		
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
		while (rs.next())
		{
			comboBox.addItem(rs.getString(1));
		}
		
		//	Get Setted values from DB
		rs = getPlants.getData("SELECT bitki_isik_siddet, bitki_gunduz_isik_sure, bitki_gunduz_ortam_isi, bitki_gunduz_nem, bitki_gunduz_o2, bitki_gunduz_c2o, "
				+ "bitki_gunduz_cansuyu_isi, bitki_cansuyu_ph, bitki_gunduz_gida_a, bitki_gunduz_gida_b, bitki_gunduz_gida_c "
				+ "FROM bitki b "
				+ "LEFT JOIN hall h ON b._id = h.bitki_id "
				+ "WHERE h.bolum_id = '" + hallID + "' "
				+ "ORDER BY h.bolum_id ASC "
				+ "LIMIT 1");
		while(rs.next())
		{
			ortamIsiSet.setText(rs.getString(3) + " °C");
			ortamNemSet.setText(rs.getString(4) + " %");
			canSuyuSet.setText(rs.getString(7) + " °C");
			o2Set.setText(rs.getString(5) + " %");
			co2Set.setText(rs.getString(6) + " %");
			isikSet.setText(rs.getString(1) + " lux");
			canSuyuPhSet.setText(rs.getString(8));
			aGidaSet.setText(rs.getString(9));
			bGidaSet.setText(rs.getString(10));
			cGidaSet.setText(rs.getString(11));
			isitmaVanaSet.setText("0");
			sogutmaVanaSet.setText("0");
			isikKirmizi.setText("0");
			isikMavi.setText("0");
			dayLight.setText(rs.getString(2));
		}
		
		//	Get Values from Sensor
		rs = getPlants.getData("SELECT env_temp, env_humid, env_02, env_co2, env_light, "
				+ "env_pressure, env_water_temp, env_water_ph, a_gida, "
				+ "b_gida, c_gida, env_heat_valve, env_cool_valve "
				+ "FROM product_log "
				+ "WHERE bolum_id = '" + hallID + "' "
				+ "ORDER BY _id DESC "
				+ "LIMIT 1");
		while(rs.next())
		{
			ortamIsiOkunan.setText(rs.getString(1) + " °C");
			ortamNemOkunan.setText(rs.getString(2) + " %");
			canSuyuOkunan.setText(rs.getString(7) + " °C");
			o2Okunan.setText(rs.getString(3) + " %");
			co2Okunan.setText(rs.getString(4) + " %");
			isikOkunan.setText(rs.getString(5) + " lux");
			canSuyuPhOkunan.setText(rs.getString(8));
			aGidaOkunan.setText(rs.getString(9));
			bGidaOkunan.setText(rs.getString(10));
			cGidaOkunan.setText(rs.getString(11));
			isitmaVanaOkunan.setText(rs.getString(12));
			sogutmaVanaOkunan.setText(rs.getString(13));
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
		//	Unused implementation
	}

	@Override
	public void setAuthID(int auth_id)
	{
		// Unused implementation
	}
}