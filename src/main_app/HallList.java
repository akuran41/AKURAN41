package main_app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import ui.CreateButton;
import ui.CreateInput;
import ui.CreateLabel;
import ui.CreateSeparator;
import utils.ErrorLog;
import utils.LoginDataDisplay;
import db_process.ConnectDatabase;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class HallList extends JFrame implements LoginDataDisplay
{
	private static final long	serialVersionUID	= 6569607035183642991L;
	private ErrorLog			errorLog			= null;

	private JPanel				contentPane;
	private JPanel				standardDataPanel;

	private CompoundBorder		compoundBorder;

	private ConnectDatabase		connection;
	private CreateLabel			createLabel;
	private CreateInput			createInput;
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
					HallList frame = new HallList();
					frame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	public HallList()
	{
		setResizable(false);
		setAlwaysOnTop(true);
		errorLog = new ErrorLog();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(900, 10, 930, 970);
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
		standardDataPanel.setBounds(10, 11, 895, 917);
		standardDataPanel.setLayout(null);
		contentPane.add(standardDataPanel);
		
		JLabel label = new JLabel("Bolüm ID");
		label.setFont(new Font("Tahoma", Font.BOLD, 13));
		label.setBorder(compoundBorder);
		label.setBounds(10, 11, 104, 30);
		standardDataPanel.add(label);
		
		JLabel label_1 = new JLabel("1");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_1.setBorder(compoundBorder);
		label_1.setBounds(113, 11, 30, 30);
		standardDataPanel.add(label_1);
		
		JLabel label_2 = new JLabel("Hol ID");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_2.setBorder(compoundBorder);
		label_2.setBounds(142, 11, 75, 30);
		standardDataPanel.add(label_2);
		
		JLabel label_3 = new JLabel("1");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_3.setBorder(compoundBorder);
		label_3.setBounds(216, 11, 30, 30);
		standardDataPanel.add(label_3);
		
		JButton button = new JButton("SIL");
		button.setBorder(compoundBorder);
		button.setBounds(245, 11, 50, 30);
		standardDataPanel.add(button);
		
		JLabel label_4 = new JLabel("Ekilen Bitki");
		label_4.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_4.setBorder(compoundBorder);
		label_4.setBounds(10, 40, 104, 25);
		standardDataPanel.add(label_4);
		
		JLabel label_5 = new JLabel("");
		label_5.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_5.setBorder(compoundBorder);
		label_5.setBounds(113, 40, 182, 25);
		standardDataPanel.add(label_5);
		
		JLabel label_6 = new JLabel("Ekim Zamanı");
		label_6.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_6.setBorder(compoundBorder);
		label_6.setBounds(10, 64, 104, 25);
		standardDataPanel.add(label_6);
		
		JLabel label_7 = new JLabel("");
		label_7.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_7.setBorder(compoundBorder);
		label_7.setBounds(113, 64, 182, 25);
		standardDataPanel.add(label_7);
		
		JLabel label_8 = new JLabel("Hasat zamanı");
		label_8.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_8.setBorder(compoundBorder);
		label_8.setBounds(10, 88, 104, 25);
		standardDataPanel.add(label_8);
		
		JLabel label_9 = new JLabel("");
		label_9.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_9.setBorder(compoundBorder);
		label_9.setBounds(113, 88, 182, 25);
		standardDataPanel.add(label_9);
		
		JLabel label_10 = new JLabel("Hasat zamanı");
		label_10.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_10.setBorder(compoundBorder);
		label_10.setBounds(305, 88, 104, 25);
		standardDataPanel.add(label_10);
		
		JLabel label_11 = new JLabel("");
		label_11.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_11.setBorder(compoundBorder);
		label_11.setBounds(408, 88, 182, 25);
		standardDataPanel.add(label_11);
		
		JLabel label_12 = new JLabel("");
		label_12.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_12.setBorder(compoundBorder);
		label_12.setBounds(408, 64, 182, 25);
		standardDataPanel.add(label_12);
		
		JLabel label_13 = new JLabel("Ekim Zamanı");
		label_13.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_13.setBorder(compoundBorder);
		label_13.setBounds(305, 64, 104, 25);
		standardDataPanel.add(label_13);
		
		JLabel label_14 = new JLabel("Ekilen Bitki");
		label_14.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_14.setBorder(compoundBorder);
		label_14.setBounds(305, 40, 104, 25);
		standardDataPanel.add(label_14);
		
		JLabel label_15 = new JLabel("");
		label_15.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_15.setBorder(compoundBorder);
		label_15.setBounds(408, 40, 182, 25);
		standardDataPanel.add(label_15);
		
		JLabel label_16 = new JLabel("Bolüm ID");
		label_16.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_16.setBorder(compoundBorder);
		label_16.setBounds(305, 11, 104, 30);
		standardDataPanel.add(label_16);
		
		JLabel label_17 = new JLabel("1");
		label_17.setHorizontalAlignment(SwingConstants.CENTER);
		label_17.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_17.setBorder(compoundBorder);
		label_17.setBounds(408, 11, 30, 30);
		standardDataPanel.add(label_17);
		
		JLabel label_18 = new JLabel("Hol ID");
		label_18.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_18.setBorder(compoundBorder);
		label_18.setBounds(437, 11, 75, 30);
		standardDataPanel.add(label_18);
		
		JLabel label_19 = new JLabel("2");
		label_19.setHorizontalAlignment(SwingConstants.CENTER);
		label_19.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_19.setBorder(compoundBorder);
		label_19.setBounds(511, 11, 30, 30);
		standardDataPanel.add(label_19);
		
		JButton button_1 = new JButton("SIL");
		button_1.setBorder(compoundBorder);
		button_1.setBounds(540, 11, 50, 30);
		standardDataPanel.add(button_1);
		
		JLabel label_20 = new JLabel("Bolüm ID");
		label_20.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_20.setBorder(compoundBorder);
		label_20.setBounds(600, 11, 104, 30);
		standardDataPanel.add(label_20);
		
		JLabel label_21 = new JLabel("1");
		label_21.setHorizontalAlignment(SwingConstants.CENTER);
		label_21.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_21.setBorder(compoundBorder);
		label_21.setBounds(703, 11, 30, 30);
		standardDataPanel.add(label_21);
		
		JLabel label_22 = new JLabel("Hol ID");
		label_22.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_22.setBorder(compoundBorder);
		label_22.setBounds(732, 11, 75, 30);
		standardDataPanel.add(label_22);
		
		JLabel label_23 = new JLabel("3");
		label_23.setHorizontalAlignment(SwingConstants.CENTER);
		label_23.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_23.setBorder(compoundBorder);
		label_23.setBounds(806, 11, 30, 30);
		standardDataPanel.add(label_23);
		
		JButton button_2 = new JButton("SIL");
		button_2.setBorder(compoundBorder);
		button_2.setBounds(835, 11, 50, 30);
		standardDataPanel.add(button_2);
		
		JLabel label_24 = new JLabel("");
		label_24.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_24.setBorder(compoundBorder);
		label_24.setBounds(703, 40, 182, 25);
		standardDataPanel.add(label_24);
		
		JLabel label_25 = new JLabel("Ekilen Bitki");
		label_25.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_25.setBorder(compoundBorder);
		label_25.setBounds(600, 40, 104, 25);
		standardDataPanel.add(label_25);
		
		JLabel label_26 = new JLabel("Ekim Zamanı");
		label_26.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_26.setBorder(compoundBorder);
		label_26.setBounds(600, 64, 104, 25);
		standardDataPanel.add(label_26);
		
		JLabel label_27 = new JLabel("");
		label_27.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_27.setBorder(compoundBorder);
		label_27.setBounds(703, 64, 182, 25);
		standardDataPanel.add(label_27);
		
		JLabel label_28 = new JLabel("Hasat zamanı");
		label_28.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_28.setBorder(compoundBorder);
		label_28.setBounds(600, 88, 104, 25);
		standardDataPanel.add(label_28);
		
		JLabel label_29 = new JLabel("");
		label_29.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_29.setBorder(compoundBorder);
		label_29.setBounds(703, 88, 182, 25);
		standardDataPanel.add(label_29);
		
		JLabel label_30 = new JLabel("Bolüm ID");
		label_30.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_30.setBorder(compoundBorder);
		label_30.setBounds(10, 124, 104, 30);
		standardDataPanel.add(label_30);
		
		JLabel label_31 = new JLabel("1");
		label_31.setHorizontalAlignment(SwingConstants.CENTER);
		label_31.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_31.setBorder(compoundBorder);
		label_31.setBounds(113, 124, 30, 30);
		standardDataPanel.add(label_31);
		
		JLabel label_32 = new JLabel("Hol ID");
		label_32.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_32.setBorder(compoundBorder);
		label_32.setBounds(142, 124, 75, 30);
		standardDataPanel.add(label_32);
		
		JLabel label_33 = new JLabel("4");
		label_33.setHorizontalAlignment(SwingConstants.CENTER);
		label_33.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_33.setBorder(compoundBorder);
		label_33.setBounds(216, 124, 30, 30);
		standardDataPanel.add(label_33);
		
		JButton button_3 = new JButton("SIL");
		button_3.setBorder(compoundBorder);
		button_3.setBounds(245, 124, 50, 30);
		standardDataPanel.add(button_3);
		
		JLabel label_34 = new JLabel("Ekilen Bitki");
		label_34.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_34.setBorder(compoundBorder);
		label_34.setBounds(10, 153, 104, 25);
		standardDataPanel.add(label_34);
		
		JLabel label_35 = new JLabel("");
		label_35.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_35.setBorder(compoundBorder);
		label_35.setBounds(113, 153, 182, 25);
		standardDataPanel.add(label_35);
		
		JLabel label_36 = new JLabel("Ekim Zamanı");
		label_36.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_36.setBorder(compoundBorder);
		label_36.setBounds(10, 177, 104, 25);
		standardDataPanel.add(label_36);
		
		JLabel label_37 = new JLabel("");
		label_37.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_37.setBorder(compoundBorder);
		label_37.setBounds(113, 177, 182, 25);
		standardDataPanel.add(label_37);
		
		JLabel label_38 = new JLabel("Hasat zamanı");
		label_38.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_38.setBorder(compoundBorder);
		label_38.setBounds(10, 201, 104, 25);
		standardDataPanel.add(label_38);
		
		JLabel label_39 = new JLabel("");
		label_39.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_39.setBorder(compoundBorder);
		label_39.setBounds(113, 201, 182, 25);
		standardDataPanel.add(label_39);
		
		JLabel label_40 = new JLabel("Hasat zamanı");
		label_40.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_40.setBorder(compoundBorder);
		label_40.setBounds(305, 201, 104, 25);
		standardDataPanel.add(label_40);
		
		JLabel label_41 = new JLabel("");
		label_41.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_41.setBorder(compoundBorder);
		label_41.setBounds(408, 201, 182, 25);
		standardDataPanel.add(label_41);
		
		JLabel label_42 = new JLabel("");
		label_42.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_42.setBorder(compoundBorder);
		label_42.setBounds(408, 177, 182, 25);
		standardDataPanel.add(label_42);
		
		JLabel label_43 = new JLabel("Ekim Zamanı");
		label_43.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_43.setBorder(compoundBorder);
		label_43.setBounds(305, 177, 104, 25);
		standardDataPanel.add(label_43);
		
		JLabel label_44 = new JLabel("Ekilen Bitki");
		label_44.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_44.setBorder(compoundBorder);
		label_44.setBounds(305, 153, 104, 25);
		standardDataPanel.add(label_44);
		
		JLabel label_45 = new JLabel("");
		label_45.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_45.setBorder(compoundBorder);
		label_45.setBounds(408, 153, 182, 25);
		standardDataPanel.add(label_45);
		
		JLabel label_46 = new JLabel("Bolüm ID");
		label_46.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_46.setBorder(compoundBorder);
		label_46.setBounds(305, 124, 104, 30);
		standardDataPanel.add(label_46);
		
		JLabel label_47 = new JLabel("1");
		label_47.setHorizontalAlignment(SwingConstants.CENTER);
		label_47.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_47.setBorder(compoundBorder);
		label_47.setBounds(408, 124, 30, 30);
		standardDataPanel.add(label_47);
		
		JLabel label_48 = new JLabel("Hol ID");
		label_48.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_48.setBorder(compoundBorder);
		label_48.setBounds(437, 124, 75, 30);
		standardDataPanel.add(label_48);
		
		JLabel label_49 = new JLabel("5");
		label_49.setHorizontalAlignment(SwingConstants.CENTER);
		label_49.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_49.setBorder(compoundBorder);
		label_49.setBounds(511, 124, 30, 30);
		standardDataPanel.add(label_49);
		
		JButton button_4 = new JButton("SIL");
		button_4.setBorder(compoundBorder);
		button_4.setBounds(540, 124, 50, 30);
		standardDataPanel.add(button_4);
		
		JLabel label_50 = new JLabel("Bolüm ID");
		label_50.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_50.setBorder(compoundBorder);
		label_50.setBounds(600, 124, 104, 30);
		standardDataPanel.add(label_50);
		
		JLabel label_51 = new JLabel("1");
		label_51.setHorizontalAlignment(SwingConstants.CENTER);
		label_51.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_51.setBorder(compoundBorder);
		label_51.setBounds(703, 124, 30, 30);
		standardDataPanel.add(label_51);
		
		JLabel label_52 = new JLabel("Hol ID");
		label_52.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_52.setBorder(compoundBorder);
		label_52.setBounds(732, 124, 75, 30);
		standardDataPanel.add(label_52);
		
		JLabel label_53 = new JLabel("6");
		label_53.setHorizontalAlignment(SwingConstants.CENTER);
		label_53.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_53.setBorder(compoundBorder);
		label_53.setBounds(806, 124, 30, 30);
		standardDataPanel.add(label_53);
		
		JButton button_5 = new JButton("SIL");
		button_5.setBorder(compoundBorder);
		button_5.setBounds(835, 124, 50, 30);
		standardDataPanel.add(button_5);
		
		JLabel label_54 = new JLabel("");
		label_54.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_54.setBorder(compoundBorder);
		label_54.setBounds(703, 153, 182, 25);
		standardDataPanel.add(label_54);
		
		JLabel label_55 = new JLabel("Ekilen Bitki");
		label_55.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_55.setBorder(compoundBorder);
		label_55.setBounds(600, 153, 104, 25);
		standardDataPanel.add(label_55);
		
		JLabel label_56 = new JLabel("Ekim Zamanı");
		label_56.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_56.setBorder(compoundBorder);
		label_56.setBounds(600, 177, 104, 25);
		standardDataPanel.add(label_56);
		
		JLabel label_57 = new JLabel("");
		label_57.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_57.setBorder(compoundBorder);
		label_57.setBounds(703, 177, 182, 25);
		standardDataPanel.add(label_57);
		
		JLabel label_58 = new JLabel("Hasat zamanı");
		label_58.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_58.setBorder(compoundBorder);
		label_58.setBounds(600, 201, 104, 25);
		standardDataPanel.add(label_58);
		
		JLabel label_59 = new JLabel("");
		label_59.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_59.setBorder(compoundBorder);
		label_59.setBounds(703, 201, 182, 25);
		standardDataPanel.add(label_59);
		
		JLabel label_60 = new JLabel("Bolüm ID");
		label_60.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_60.setBorder(compoundBorder);
		label_60.setBounds(10, 237, 104, 30);
		standardDataPanel.add(label_60);
		
		JLabel label_61 = new JLabel("1");
		label_61.setHorizontalAlignment(SwingConstants.CENTER);
		label_61.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_61.setBorder(compoundBorder);
		label_61.setBounds(113, 237, 30, 30);
		standardDataPanel.add(label_61);
		
		JLabel label_62 = new JLabel("Hol ID");
		label_62.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_62.setBorder(compoundBorder);
		label_62.setBounds(142, 237, 75, 30);
		standardDataPanel.add(label_62);
		
		JLabel label_63 = new JLabel("7");
		label_63.setHorizontalAlignment(SwingConstants.CENTER);
		label_63.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_63.setBorder(compoundBorder);
		label_63.setBounds(216, 237, 30, 30);
		standardDataPanel.add(label_63);
		
		JButton button_6 = new JButton("SIL");
		button_6.setBorder(compoundBorder);
		button_6.setBounds(245, 237, 50, 30);
		standardDataPanel.add(button_6);
		
		JLabel label_64 = new JLabel("Ekilen Bitki");
		label_64.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_64.setBorder(compoundBorder);
		label_64.setBounds(10, 266, 104, 25);
		standardDataPanel.add(label_64);
		
		JLabel label_65 = new JLabel("");
		label_65.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_65.setBorder(compoundBorder);
		label_65.setBounds(113, 266, 182, 25);
		standardDataPanel.add(label_65);
		
		JLabel label_66 = new JLabel("Ekim Zamanı");
		label_66.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_66.setBorder(compoundBorder);
		label_66.setBounds(10, 290, 104, 25);
		standardDataPanel.add(label_66);
		
		JLabel label_67 = new JLabel("");
		label_67.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_67.setBorder(compoundBorder);
		label_67.setBounds(113, 290, 182, 25);
		standardDataPanel.add(label_67);
		
		JLabel label_68 = new JLabel("Hasat zamanı");
		label_68.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_68.setBorder(compoundBorder);
		label_68.setBounds(10, 314, 104, 25);
		standardDataPanel.add(label_68);
		
		JLabel label_69 = new JLabel("");
		label_69.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_69.setBorder(compoundBorder);
		label_69.setBounds(113, 314, 182, 25);
		standardDataPanel.add(label_69);
		
		JLabel label_70 = new JLabel("Hasat zamanı");
		label_70.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_70.setBorder(compoundBorder);
		label_70.setBounds(305, 314, 104, 25);
		standardDataPanel.add(label_70);
		
		JLabel label_71 = new JLabel("");
		label_71.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_71.setBorder(compoundBorder);
		label_71.setBounds(408, 314, 182, 25);
		standardDataPanel.add(label_71);
		
		JLabel label_72 = new JLabel("");
		label_72.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_72.setBorder(compoundBorder);
		label_72.setBounds(408, 290, 182, 25);
		standardDataPanel.add(label_72);
		
		JLabel label_73 = new JLabel("Ekim Zamanı");
		label_73.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_73.setBorder(compoundBorder);
		label_73.setBounds(305, 290, 104, 25);
		standardDataPanel.add(label_73);
		
		JLabel label_74 = new JLabel("Ekilen Bitki");
		label_74.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_74.setBorder(compoundBorder);
		label_74.setBounds(305, 266, 104, 25);
		standardDataPanel.add(label_74);
		
		JLabel label_75 = new JLabel("");
		label_75.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_75.setBorder(compoundBorder);
		label_75.setBounds(408, 266, 182, 25);
		standardDataPanel.add(label_75);
		
		JLabel label_76 = new JLabel("Bolüm ID");
		label_76.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_76.setBorder(compoundBorder);
		label_76.setBounds(305, 237, 104, 30);
		standardDataPanel.add(label_76);
		
		JLabel label_77 = new JLabel("1");
		label_77.setHorizontalAlignment(SwingConstants.CENTER);
		label_77.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_77.setBorder(compoundBorder);
		label_77.setBounds(408, 237, 30, 30);
		standardDataPanel.add(label_77);
		
		JLabel label_78 = new JLabel("Hol ID");
		label_78.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_78.setBorder(compoundBorder);
		label_78.setBounds(437, 237, 75, 30);
		standardDataPanel.add(label_78);
		
		JLabel label_79 = new JLabel("8");
		label_79.setHorizontalAlignment(SwingConstants.CENTER);
		label_79.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_79.setBorder(compoundBorder);
		label_79.setBounds(511, 237, 30, 30);
		standardDataPanel.add(label_79);
		
		JButton button_7 = new JButton("SIL");
		button_7.setBorder(compoundBorder);
		button_7.setBounds(540, 237, 50, 30);
		standardDataPanel.add(button_7);
		
		JLabel label_80 = new JLabel("Bolüm ID");
		label_80.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_80.setBorder(compoundBorder);
		label_80.setBounds(600, 237, 104, 30);
		standardDataPanel.add(label_80);
		
		JLabel label_81 = new JLabel("1");
		label_81.setHorizontalAlignment(SwingConstants.CENTER);
		label_81.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_81.setBorder(compoundBorder);
		label_81.setBounds(703, 237, 30, 30);
		standardDataPanel.add(label_81);
		
		JLabel label_82 = new JLabel("Hol ID");
		label_82.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_82.setBorder(compoundBorder);
		label_82.setBounds(732, 237, 75, 30);
		standardDataPanel.add(label_82);
		
		JLabel label_83 = new JLabel("9");
		label_83.setHorizontalAlignment(SwingConstants.CENTER);
		label_83.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_83.setBorder(compoundBorder);
		label_83.setBounds(806, 237, 30, 30);
		standardDataPanel.add(label_83);
		
		JButton button_8 = new JButton("SIL");
		button_8.setBorder(compoundBorder);
		button_8.setBounds(835, 237, 50, 30);
		standardDataPanel.add(button_8);
		
		JLabel label_84 = new JLabel("");
		label_84.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_84.setBorder(compoundBorder);
		label_84.setBounds(703, 266, 182, 25);
		standardDataPanel.add(label_84);
		
		JLabel label_85 = new JLabel("Ekilen Bitki");
		label_85.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_85.setBorder(compoundBorder);
		label_85.setBounds(600, 266, 104, 25);
		standardDataPanel.add(label_85);
		
		JLabel label_86 = new JLabel("Ekim Zamanı");
		label_86.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_86.setBorder(compoundBorder);
		label_86.setBounds(600, 290, 104, 25);
		standardDataPanel.add(label_86);
		
		JLabel label_87 = new JLabel("");
		label_87.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_87.setBorder(compoundBorder);
		label_87.setBounds(703, 290, 182, 25);
		standardDataPanel.add(label_87);
		
		JLabel label_88 = new JLabel("Hasat zamanı");
		label_88.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_88.setBorder(compoundBorder);
		label_88.setBounds(600, 314, 104, 25);
		standardDataPanel.add(label_88);
		
		JLabel label_89 = new JLabel("");
		label_89.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_89.setBorder(compoundBorder);
		label_89.setBounds(703, 314, 182, 25);
		standardDataPanel.add(label_89);
		
		JLabel label_90 = new JLabel("Bolüm ID");
		label_90.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_90.setBorder(compoundBorder);
		label_90.setBounds(10, 350, 104, 30);
		standardDataPanel.add(label_90);
		
		JLabel label_91 = new JLabel("1");
		label_91.setHorizontalAlignment(SwingConstants.CENTER);
		label_91.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_91.setBorder(compoundBorder);
		label_91.setBounds(113, 350, 30, 30);
		standardDataPanel.add(label_91);
		
		JLabel label_92 = new JLabel("Hol ID");
		label_92.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_92.setBorder(compoundBorder);
		label_92.setBounds(142, 350, 75, 30);
		standardDataPanel.add(label_92);
		
		JLabel label_93 = new JLabel("10");
		label_93.setHorizontalAlignment(SwingConstants.CENTER);
		label_93.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_93.setBorder(compoundBorder);
		label_93.setBounds(216, 350, 30, 30);
		standardDataPanel.add(label_93);
		
		JButton button_9 = new JButton("SIL");
		button_9.setBorder(compoundBorder);
		button_9.setBounds(245, 350, 50, 30);
		standardDataPanel.add(button_9);
		
		JLabel label_94 = new JLabel("Ekilen Bitki");
		label_94.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_94.setBorder(compoundBorder);
		label_94.setBounds(10, 379, 104, 25);
		standardDataPanel.add(label_94);
		
		JLabel label_95 = new JLabel("");
		label_95.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_95.setBorder(compoundBorder);
		label_95.setBounds(113, 379, 182, 25);
		standardDataPanel.add(label_95);
		
		JLabel label_96 = new JLabel("Ekim Zamanı");
		label_96.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_96.setBorder(compoundBorder);
		label_96.setBounds(10, 403, 104, 25);
		standardDataPanel.add(label_96);
		
		JLabel label_97 = new JLabel("");
		label_97.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_97.setBorder(compoundBorder);
		label_97.setBounds(113, 403, 182, 25);
		standardDataPanel.add(label_97);
		
		JLabel label_98 = new JLabel("Hasat zamanı");
		label_98.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_98.setBorder(compoundBorder);
		label_98.setBounds(10, 427, 104, 25);
		standardDataPanel.add(label_98);
		
		JLabel label_99 = new JLabel("");
		label_99.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_99.setBorder(compoundBorder);
		label_99.setBounds(113, 427, 182, 25);
		standardDataPanel.add(label_99);
		
		JLabel label_100 = new JLabel("Hasat zamanı");
		label_100.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_100.setBorder(compoundBorder);
		label_100.setBounds(305, 427, 104, 25);
		standardDataPanel.add(label_100);
		
		JLabel label_101 = new JLabel("");
		label_101.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_101.setBorder(compoundBorder);
		label_101.setBounds(408, 427, 182, 25);
		standardDataPanel.add(label_101);
		
		JLabel label_102 = new JLabel("");
		label_102.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_102.setBorder(compoundBorder);
		label_102.setBounds(408, 403, 182, 25);
		standardDataPanel.add(label_102);
		
		JLabel label_103 = new JLabel("Ekim Zamanı");
		label_103.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_103.setBorder(compoundBorder);
		label_103.setBounds(305, 403, 104, 25);
		standardDataPanel.add(label_103);
		
		JLabel label_104 = new JLabel("Ekilen Bitki");
		label_104.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_104.setBorder(compoundBorder);
		label_104.setBounds(305, 379, 104, 25);
		standardDataPanel.add(label_104);
		
		JLabel label_105 = new JLabel("");
		label_105.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_105.setBorder(compoundBorder);
		label_105.setBounds(408, 379, 182, 25);
		standardDataPanel.add(label_105);
		
		JLabel label_106 = new JLabel("Bolüm ID");
		label_106.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_106.setBorder(compoundBorder);
		label_106.setBounds(305, 350, 104, 30);
		standardDataPanel.add(label_106);
		
		JLabel label_107 = new JLabel("1");
		label_107.setHorizontalAlignment(SwingConstants.CENTER);
		label_107.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_107.setBorder(compoundBorder);
		label_107.setBounds(408, 350, 30, 30);
		standardDataPanel.add(label_107);
		
		JLabel label_108 = new JLabel("Hol ID");
		label_108.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_108.setBorder(compoundBorder);
		label_108.setBounds(437, 350, 75, 30);
		standardDataPanel.add(label_108);
		
		JLabel label_109 = new JLabel("11");
		label_109.setHorizontalAlignment(SwingConstants.CENTER);
		label_109.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_109.setBorder(compoundBorder);
		label_109.setBounds(511, 350, 30, 30);
		standardDataPanel.add(label_109);
		
		JButton button_10 = new JButton("SIL");
		button_10.setBorder(compoundBorder);
		button_10.setBounds(540, 350, 50, 30);
		standardDataPanel.add(button_10);
		
		JLabel label_110 = new JLabel("Bolüm ID");
		label_110.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_110.setBorder(compoundBorder);
		label_110.setBounds(600, 350, 104, 30);
		standardDataPanel.add(label_110);
		
		JLabel label_111 = new JLabel("1");
		label_111.setHorizontalAlignment(SwingConstants.CENTER);
		label_111.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_111.setBorder(compoundBorder);
		label_111.setBounds(703, 350, 30, 30);
		standardDataPanel.add(label_111);
		
		JLabel label_112 = new JLabel("Hol ID");
		label_112.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_112.setBorder(compoundBorder);
		label_112.setBounds(732, 350, 75, 30);
		standardDataPanel.add(label_112);
		
		JLabel label_113 = new JLabel("12");
		label_113.setHorizontalAlignment(SwingConstants.CENTER);
		label_113.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_113.setBorder(compoundBorder);
		label_113.setBounds(806, 350, 30, 30);
		standardDataPanel.add(label_113);
		
		JButton button_11 = new JButton("SIL");
		button_11.setBorder(compoundBorder);
		button_11.setBounds(835, 350, 50, 30);
		standardDataPanel.add(button_11);
		
		JLabel label_114 = new JLabel("");
		label_114.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_114.setBorder(compoundBorder);
		label_114.setBounds(703, 379, 182, 25);
		standardDataPanel.add(label_114);
		
		JLabel label_115 = new JLabel("Ekilen Bitki");
		label_115.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_115.setBorder(compoundBorder);
		label_115.setBounds(600, 379, 104, 25);
		standardDataPanel.add(label_115);
		
		JLabel label_116 = new JLabel("Ekim Zamanı");
		label_116.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_116.setBorder(compoundBorder);
		label_116.setBounds(600, 403, 104, 25);
		standardDataPanel.add(label_116);
		
		JLabel label_117 = new JLabel("");
		label_117.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_117.setBorder(compoundBorder);
		label_117.setBounds(703, 403, 182, 25);
		standardDataPanel.add(label_117);
		
		JLabel label_118 = new JLabel("Hasat zamanı");
		label_118.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_118.setBorder(compoundBorder);
		label_118.setBounds(600, 427, 104, 25);
		standardDataPanel.add(label_118);
		
		JLabel label_119 = new JLabel("");
		label_119.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_119.setBorder(compoundBorder);
		label_119.setBounds(703, 427, 182, 25);
		standardDataPanel.add(label_119);
		
		JLabel label_120 = new JLabel("Bolüm ID");
		label_120.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_120.setBorder(compoundBorder);
		label_120.setBounds(10, 463, 104, 30);
		standardDataPanel.add(label_120);
		
		JLabel label_121 = new JLabel("1");
		label_121.setHorizontalAlignment(SwingConstants.CENTER);
		label_121.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_121.setBorder(compoundBorder);
		label_121.setBounds(113, 463, 30, 30);
		standardDataPanel.add(label_121);
		
		JLabel label_122 = new JLabel("Hol ID");
		label_122.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_122.setBorder(compoundBorder);
		label_122.setBounds(142, 463, 75, 30);
		standardDataPanel.add(label_122);
		
		JLabel label_123 = new JLabel("13");
		label_123.setHorizontalAlignment(SwingConstants.CENTER);
		label_123.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_123.setBorder(compoundBorder);
		label_123.setBounds(216, 463, 30, 30);
		standardDataPanel.add(label_123);
		
		JButton button_12 = new JButton("SIL");
		button_12.setBorder(compoundBorder);
		button_12.setBounds(245, 463, 50, 30);
		standardDataPanel.add(button_12);
		
		JLabel label_124 = new JLabel("Ekilen Bitki");
		label_124.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_124.setBorder(compoundBorder);
		label_124.setBounds(10, 492, 104, 25);
		standardDataPanel.add(label_124);
		
		JLabel label_125 = new JLabel("");
		label_125.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_125.setBorder(compoundBorder);
		label_125.setBounds(113, 492, 182, 25);
		standardDataPanel.add(label_125);
		
		JLabel label_126 = new JLabel("Ekim Zamanı");
		label_126.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_126.setBorder(compoundBorder);
		label_126.setBounds(10, 516, 104, 25);
		standardDataPanel.add(label_126);
		
		JLabel label_127 = new JLabel("");
		label_127.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_127.setBorder(compoundBorder);
		label_127.setBounds(113, 516, 182, 25);
		standardDataPanel.add(label_127);
		
		JLabel label_128 = new JLabel("Hasat zamanı");
		label_128.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_128.setBorder(compoundBorder);
		label_128.setBounds(10, 540, 104, 25);
		standardDataPanel.add(label_128);
		
		JLabel label_129 = new JLabel("");
		label_129.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_129.setBorder(compoundBorder);
		label_129.setBounds(113, 540, 182, 25);
		standardDataPanel.add(label_129);
		
		JLabel label_130 = new JLabel("Hasat zamanı");
		label_130.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_130.setBorder(compoundBorder);
		label_130.setBounds(305, 540, 104, 25);
		standardDataPanel.add(label_130);
		
		JLabel label_131 = new JLabel("");
		label_131.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_131.setBorder(compoundBorder);
		label_131.setBounds(408, 540, 182, 25);
		standardDataPanel.add(label_131);
		
		JLabel label_132 = new JLabel("");
		label_132.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_132.setBorder(compoundBorder);
		label_132.setBounds(408, 516, 182, 25);
		standardDataPanel.add(label_132);
		
		JLabel label_133 = new JLabel("Ekim Zamanı");
		label_133.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_133.setBorder(compoundBorder);
		label_133.setBounds(305, 516, 104, 25);
		standardDataPanel.add(label_133);
		
		JLabel label_134 = new JLabel("Ekilen Bitki");
		label_134.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_134.setBorder(compoundBorder);
		label_134.setBounds(305, 492, 104, 25);
		standardDataPanel.add(label_134);
		
		JLabel label_135 = new JLabel("");
		label_135.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_135.setBorder(compoundBorder);
		label_135.setBounds(408, 492, 182, 25);
		standardDataPanel.add(label_135);
		
		JLabel label_136 = new JLabel("Bolüm ID");
		label_136.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_136.setBorder(compoundBorder);
		label_136.setBounds(305, 463, 104, 30);
		standardDataPanel.add(label_136);
		
		JLabel label_137 = new JLabel("1");
		label_137.setHorizontalAlignment(SwingConstants.CENTER);
		label_137.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_137.setBorder(compoundBorder);
		label_137.setBounds(408, 463, 30, 30);
		standardDataPanel.add(label_137);
		
		JLabel label_138 = new JLabel("Hol ID");
		label_138.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_138.setBorder(compoundBorder);
		label_138.setBounds(437, 463, 75, 30);
		standardDataPanel.add(label_138);
		
		JLabel label_139 = new JLabel("14");
		label_139.setHorizontalAlignment(SwingConstants.CENTER);
		label_139.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_139.setBorder(compoundBorder);
		label_139.setBounds(511, 463, 30, 30);
		standardDataPanel.add(label_139);
		
		JButton button_13 = new JButton("SIL");
		button_13.setBorder(compoundBorder);
		button_13.setBounds(540, 463, 50, 30);
		standardDataPanel.add(button_13);
		
		JLabel label_140 = new JLabel("Bolüm ID");
		label_140.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_140.setBorder(compoundBorder);
		label_140.setBounds(600, 463, 104, 30);
		standardDataPanel.add(label_140);
		
		JLabel label_141 = new JLabel("1");
		label_141.setHorizontalAlignment(SwingConstants.CENTER);
		label_141.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_141.setBorder(compoundBorder);
		label_141.setBounds(703, 463, 30, 30);
		standardDataPanel.add(label_141);
		
		JLabel label_142 = new JLabel("Hol ID");
		label_142.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_142.setBorder(compoundBorder);
		label_142.setBounds(732, 463, 75, 30);
		standardDataPanel.add(label_142);
		
		JLabel label_143 = new JLabel("15");
		label_143.setHorizontalAlignment(SwingConstants.CENTER);
		label_143.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_143.setBorder(compoundBorder);
		label_143.setBounds(806, 463, 30, 30);
		standardDataPanel.add(label_143);
		
		JButton button_14 = new JButton("SIL");
		button_14.setBorder(compoundBorder);
		button_14.setBounds(835, 463, 50, 30);
		standardDataPanel.add(button_14);
		
		JLabel label_144 = new JLabel("");
		label_144.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_144.setBorder(compoundBorder);
		label_144.setBounds(703, 492, 182, 25);
		standardDataPanel.add(label_144);
		
		JLabel label_145 = new JLabel("Ekilen Bitki");
		label_145.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_145.setBorder(compoundBorder);
		label_145.setBounds(600, 492, 104, 25);
		standardDataPanel.add(label_145);
		
		JLabel label_146 = new JLabel("Ekim Zamanı");
		label_146.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_146.setBorder(compoundBorder);
		label_146.setBounds(600, 516, 104, 25);
		standardDataPanel.add(label_146);
		
		JLabel label_147 = new JLabel("");
		label_147.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_147.setBorder(compoundBorder);
		label_147.setBounds(703, 516, 182, 25);
		standardDataPanel.add(label_147);
		
		JLabel label_148 = new JLabel("Hasat zamanı");
		label_148.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_148.setBorder(compoundBorder);
		label_148.setBounds(600, 540, 104, 25);
		standardDataPanel.add(label_148);
		
		JLabel label_149 = new JLabel("");
		label_149.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_149.setBorder(compoundBorder);
		label_149.setBounds(703, 540, 182, 25);
		standardDataPanel.add(label_149);
		
		JLabel label_150 = new JLabel("Bolüm ID");
		label_150.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_150.setBorder(compoundBorder);
		label_150.setBounds(10, 576, 104, 30);
		standardDataPanel.add(label_150);
		
		JLabel label_151 = new JLabel("1");
		label_151.setHorizontalAlignment(SwingConstants.CENTER);
		label_151.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_151.setBorder(compoundBorder);
		label_151.setBounds(113, 576, 30, 30);
		standardDataPanel.add(label_151);
		
		JLabel label_152 = new JLabel("Hol ID");
		label_152.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_152.setBorder(compoundBorder);
		label_152.setBounds(142, 576, 75, 30);
		standardDataPanel.add(label_152);
		
		JLabel label_153 = new JLabel("16");
		label_153.setHorizontalAlignment(SwingConstants.CENTER);
		label_153.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_153.setBorder(compoundBorder);
		label_153.setBounds(216, 576, 30, 30);
		standardDataPanel.add(label_153);
		
		JButton button_15 = new JButton("SIL");
		button_15.setBorder(compoundBorder);
		button_15.setBounds(245, 576, 50, 30);
		standardDataPanel.add(button_15);
		
		JLabel label_154 = new JLabel("Ekilen Bitki");
		label_154.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_154.setBorder(compoundBorder);
		label_154.setBounds(10, 605, 104, 25);
		standardDataPanel.add(label_154);
		
		JLabel label_155 = new JLabel("");
		label_155.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_155.setBorder(compoundBorder);
		label_155.setBounds(113, 605, 182, 25);
		standardDataPanel.add(label_155);
		
		JLabel label_156 = new JLabel("Ekim Zamanı");
		label_156.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_156.setBorder(compoundBorder);
		label_156.setBounds(10, 629, 104, 25);
		standardDataPanel.add(label_156);
		
		JLabel label_157 = new JLabel("");
		label_157.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_157.setBorder(compoundBorder);
		label_157.setBounds(113, 629, 182, 25);
		standardDataPanel.add(label_157);
		
		JLabel label_158 = new JLabel("Hasat zamanı");
		label_158.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_158.setBorder(compoundBorder);
		label_158.setBounds(10, 653, 104, 25);
		standardDataPanel.add(label_158);
		
		JLabel label_159 = new JLabel("");
		label_159.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_159.setBorder(compoundBorder);
		label_159.setBounds(113, 653, 182, 25);
		standardDataPanel.add(label_159);
		
		JLabel label_160 = new JLabel("Hasat zamanı");
		label_160.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_160.setBorder(compoundBorder);
		label_160.setBounds(305, 653, 104, 25);
		standardDataPanel.add(label_160);
		
		JLabel label_161 = new JLabel("");
		label_161.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_161.setBorder(compoundBorder);
		label_161.setBounds(408, 653, 182, 25);
		standardDataPanel.add(label_161);
		
		JLabel label_162 = new JLabel("");
		label_162.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_162.setBorder(compoundBorder);
		label_162.setBounds(408, 629, 182, 25);
		standardDataPanel.add(label_162);
		
		JLabel label_163 = new JLabel("Ekim Zamanı");
		label_163.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_163.setBorder(compoundBorder);
		label_163.setBounds(305, 629, 104, 25);
		standardDataPanel.add(label_163);
		
		JLabel label_164 = new JLabel("Ekilen Bitki");
		label_164.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_164.setBorder(compoundBorder);
		label_164.setBounds(305, 605, 104, 25);
		standardDataPanel.add(label_164);
		
		JLabel label_165 = new JLabel("");
		label_165.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_165.setBorder(compoundBorder);
		label_165.setBounds(408, 605, 182, 25);
		standardDataPanel.add(label_165);
		
		JLabel label_166 = new JLabel("Bolüm ID");
		label_166.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_166.setBorder(compoundBorder);
		label_166.setBounds(305, 576, 104, 30);
		standardDataPanel.add(label_166);
		
		JLabel label_167 = new JLabel("1");
		label_167.setHorizontalAlignment(SwingConstants.CENTER);
		label_167.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_167.setBorder(compoundBorder);
		label_167.setBounds(408, 576, 30, 30);
		standardDataPanel.add(label_167);
		
		JLabel label_168 = new JLabel("Hol ID");
		label_168.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_168.setBorder(compoundBorder);
		label_168.setBounds(437, 576, 75, 30);
		standardDataPanel.add(label_168);
		
		JLabel label_169 = new JLabel("17");
		label_169.setHorizontalAlignment(SwingConstants.CENTER);
		label_169.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_169.setBorder(compoundBorder);
		label_169.setBounds(511, 576, 30, 30);
		standardDataPanel.add(label_169);
		
		JButton button_16 = new JButton("SIL");
		button_16.setBorder(compoundBorder);
		button_16.setBounds(540, 576, 50, 30);
		standardDataPanel.add(button_16);
		
		JLabel label_170 = new JLabel("Bolüm ID");
		label_170.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_170.setBorder(compoundBorder);
		label_170.setBounds(600, 576, 104, 30);
		standardDataPanel.add(label_170);
		
		JLabel label_171 = new JLabel("1");
		label_171.setHorizontalAlignment(SwingConstants.CENTER);
		label_171.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_171.setBorder(compoundBorder);
		label_171.setBounds(703, 576, 30, 30);
		standardDataPanel.add(label_171);
		
		JLabel label_172 = new JLabel("Hol ID");
		label_172.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_172.setBorder(compoundBorder);
		label_172.setBounds(732, 576, 75, 30);
		standardDataPanel.add(label_172);
		
		JLabel label_173 = new JLabel("18");
		label_173.setHorizontalAlignment(SwingConstants.CENTER);
		label_173.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_173.setBorder(compoundBorder);
		label_173.setBounds(806, 576, 30, 30);
		standardDataPanel.add(label_173);
		
		JButton button_17 = new JButton("SIL");
		button_17.setBorder(compoundBorder);
		button_17.setBounds(835, 576, 50, 30);
		standardDataPanel.add(button_17);
		
		JLabel label_174 = new JLabel("");
		label_174.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_174.setBorder(compoundBorder);
		label_174.setBounds(703, 605, 182, 25);
		standardDataPanel.add(label_174);
		
		JLabel label_175 = new JLabel("Ekilen Bitki");
		label_175.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_175.setBorder(compoundBorder);
		label_175.setBounds(600, 605, 104, 25);
		standardDataPanel.add(label_175);
		
		JLabel label_176 = new JLabel("Ekim Zamanı");
		label_176.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_176.setBorder(compoundBorder);
		label_176.setBounds(600, 629, 104, 25);
		standardDataPanel.add(label_176);
		
		JLabel label_177 = new JLabel("");
		label_177.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_177.setBorder(compoundBorder);
		label_177.setBounds(703, 629, 182, 25);
		standardDataPanel.add(label_177);
		
		JLabel label_178 = new JLabel("Hasat zamanı");
		label_178.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_178.setBorder(compoundBorder);
		label_178.setBounds(600, 653, 104, 25);
		standardDataPanel.add(label_178);
		
		JLabel label_179 = new JLabel("");
		label_179.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_179.setBorder(compoundBorder);
		label_179.setBounds(703, 653, 182, 25);
		standardDataPanel.add(label_179);
		
		JLabel label_180 = new JLabel("Bolüm ID");
		label_180.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_180.setBorder(compoundBorder);
		label_180.setBounds(10, 689, 104, 30);
		standardDataPanel.add(label_180);
		
		JLabel label_181 = new JLabel("1");
		label_181.setHorizontalAlignment(SwingConstants.CENTER);
		label_181.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_181.setBorder(compoundBorder);
		label_181.setBounds(113, 689, 30, 30);
		standardDataPanel.add(label_181);
		
		JLabel label_182 = new JLabel("Hol ID");
		label_182.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_182.setBorder(compoundBorder);
		label_182.setBounds(142, 689, 75, 30);
		standardDataPanel.add(label_182);
		
		JLabel label_183 = new JLabel("19");
		label_183.setHorizontalAlignment(SwingConstants.CENTER);
		label_183.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_183.setBorder(compoundBorder);
		label_183.setBounds(216, 689, 30, 30);
		standardDataPanel.add(label_183);
		
		JButton button_18 = new JButton("SIL");
		button_18.setBorder(compoundBorder);
		button_18.setBounds(245, 689, 50, 30);
		standardDataPanel.add(button_18);
		
		JLabel label_184 = new JLabel("Ekilen Bitki");
		label_184.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_184.setBorder(compoundBorder);
		label_184.setBounds(10, 718, 104, 25);
		standardDataPanel.add(label_184);
		
		JLabel label_185 = new JLabel("");
		label_185.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_185.setBorder(compoundBorder);
		label_185.setBounds(113, 718, 182, 25);
		standardDataPanel.add(label_185);
		
		JLabel label_186 = new JLabel("Ekim Zamanı");
		label_186.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_186.setBorder(compoundBorder);
		label_186.setBounds(10, 742, 104, 25);
		standardDataPanel.add(label_186);
		
		JLabel label_187 = new JLabel("");
		label_187.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_187.setBorder(compoundBorder);
		label_187.setBounds(113, 742, 182, 25);
		standardDataPanel.add(label_187);
		
		JLabel label_188 = new JLabel("Hasat zamanı");
		label_188.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_188.setBorder(compoundBorder);
		label_188.setBounds(10, 766, 104, 25);
		standardDataPanel.add(label_188);
		
		JLabel label_189 = new JLabel("");
		label_189.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_189.setBorder(compoundBorder);
		label_189.setBounds(113, 766, 182, 25);
		standardDataPanel.add(label_189);
		
		JLabel label_190 = new JLabel("Hasat zamanı");
		label_190.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_190.setBorder(compoundBorder);
		label_190.setBounds(305, 766, 104, 25);
		standardDataPanel.add(label_190);
		
		JLabel label_191 = new JLabel("");
		label_191.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_191.setBorder(compoundBorder);
		label_191.setBounds(408, 766, 182, 25);
		standardDataPanel.add(label_191);
		
		JLabel label_192 = new JLabel("");
		label_192.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_192.setBorder(compoundBorder);
		label_192.setBounds(408, 742, 182, 25);
		standardDataPanel.add(label_192);
		
		JLabel label_193 = new JLabel("Ekim Zamanı");
		label_193.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_193.setBorder(compoundBorder);
		label_193.setBounds(305, 742, 104, 25);
		standardDataPanel.add(label_193);
		
		JLabel label_194 = new JLabel("Ekilen Bitki");
		label_194.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_194.setBorder(compoundBorder);
		label_194.setBounds(305, 718, 104, 25);
		standardDataPanel.add(label_194);
		
		JLabel label_195 = new JLabel("");
		label_195.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_195.setBorder(compoundBorder);
		label_195.setBounds(408, 718, 182, 25);
		standardDataPanel.add(label_195);
		
		JLabel label_196 = new JLabel("Bolüm ID");
		label_196.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_196.setBorder(compoundBorder);
		label_196.setBounds(305, 689, 104, 30);
		standardDataPanel.add(label_196);
		
		JLabel label_197 = new JLabel("1");
		label_197.setHorizontalAlignment(SwingConstants.CENTER);
		label_197.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_197.setBorder(compoundBorder);
		label_197.setBounds(408, 689, 30, 30);
		standardDataPanel.add(label_197);
		
		JLabel label_198 = new JLabel("Hol ID");
		label_198.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_198.setBorder(compoundBorder);
		label_198.setBounds(437, 689, 75, 30);
		standardDataPanel.add(label_198);
		
		JLabel label_199 = new JLabel("20");
		label_199.setHorizontalAlignment(SwingConstants.CENTER);
		label_199.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_199.setBorder(compoundBorder);
		label_199.setBounds(511, 689, 30, 30);
		standardDataPanel.add(label_199);
		
		JButton button_19 = new JButton("SIL");
		button_19.setBorder(compoundBorder);
		button_19.setBounds(540, 689, 50, 30);
		standardDataPanel.add(button_19);
		
		JLabel label_200 = new JLabel("Bolüm ID");
		label_200.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_200.setBorder(compoundBorder);
		label_200.setBounds(600, 689, 104, 30);
		standardDataPanel.add(label_200);
		
		JLabel label_201 = new JLabel("1");
		label_201.setHorizontalAlignment(SwingConstants.CENTER);
		label_201.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_201.setBorder(compoundBorder);
		label_201.setBounds(703, 689, 30, 30);
		standardDataPanel.add(label_201);
		
		JLabel label_202 = new JLabel("Hol ID");
		label_202.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_202.setBorder(compoundBorder);
		label_202.setBounds(732, 689, 75, 30);
		standardDataPanel.add(label_202);
		
		JLabel label_203 = new JLabel("21");
		label_203.setHorizontalAlignment(SwingConstants.CENTER);
		label_203.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_203.setBorder(compoundBorder);
		label_203.setBounds(806, 689, 30, 30);
		standardDataPanel.add(label_203);
		
		JButton button_20 = new JButton("SIL");
		button_20.setBorder(compoundBorder);
		button_20.setBounds(835, 689, 50, 30);
		standardDataPanel.add(button_20);
		
		JLabel label_204 = new JLabel("");
		label_204.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_204.setBorder(compoundBorder);
		label_204.setBounds(703, 718, 182, 25);
		standardDataPanel.add(label_204);
		
		JLabel label_205 = new JLabel("Ekilen Bitki");
		label_205.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_205.setBorder(compoundBorder);
		label_205.setBounds(600, 718, 104, 25);
		standardDataPanel.add(label_205);
		
		JLabel label_206 = new JLabel("Ekim Zamanı");
		label_206.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_206.setBorder(compoundBorder);
		label_206.setBounds(600, 742, 104, 25);
		standardDataPanel.add(label_206);
		
		JLabel label_207 = new JLabel("");
		label_207.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_207.setBorder(compoundBorder);
		label_207.setBounds(703, 742, 182, 25);
		standardDataPanel.add(label_207);
		
		JLabel label_208 = new JLabel("Hasat zamanı");
		label_208.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_208.setBorder(compoundBorder);
		label_208.setBounds(600, 766, 104, 25);
		standardDataPanel.add(label_208);
		
		JLabel label_209 = new JLabel("");
		label_209.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_209.setBorder(compoundBorder);
		label_209.setBounds(703, 766, 182, 25);
		standardDataPanel.add(label_209);
		
		JLabel label_210 = new JLabel("Bolüm ID");
		label_210.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_210.setBorder(compoundBorder);
		label_210.setBounds(10, 802, 104, 30);
		standardDataPanel.add(label_210);
		
		JLabel label_211 = new JLabel("1");
		label_211.setHorizontalAlignment(SwingConstants.CENTER);
		label_211.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_211.setBorder(compoundBorder);
		label_211.setBounds(113, 802, 30, 30);
		standardDataPanel.add(label_211);
		
		JLabel label_212 = new JLabel("Hol ID");
		label_212.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_212.setBorder(compoundBorder);
		label_212.setBounds(142, 802, 75, 30);
		standardDataPanel.add(label_212);
		
		JLabel label_213 = new JLabel("22");
		label_213.setHorizontalAlignment(SwingConstants.CENTER);
		label_213.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_213.setBorder(compoundBorder);
		label_213.setBounds(216, 802, 30, 30);
		standardDataPanel.add(label_213);
		
		JButton button_21 = new JButton("SIL");
		button_21.setBorder(compoundBorder);
		button_21.setBounds(245, 802, 50, 30);
		standardDataPanel.add(button_21);
		
		JLabel label_214 = new JLabel("Ekilen Bitki");
		label_214.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_214.setBorder(compoundBorder);
		label_214.setBounds(10, 831, 104, 25);
		standardDataPanel.add(label_214);
		
		JLabel label_215 = new JLabel("");
		label_215.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_215.setBorder(compoundBorder);
		label_215.setBounds(113, 831, 182, 25);
		standardDataPanel.add(label_215);
		
		JLabel label_216 = new JLabel("Ekim Zamanı");
		label_216.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_216.setBorder(compoundBorder);
		label_216.setBounds(10, 855, 104, 25);
		standardDataPanel.add(label_216);
		
		JLabel label_217 = new JLabel("");
		label_217.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_217.setBorder(compoundBorder);
		label_217.setBounds(113, 855, 182, 25);
		standardDataPanel.add(label_217);
		
		JLabel label_218 = new JLabel("Hasat zamanı");
		label_218.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_218.setBorder(compoundBorder);
		label_218.setBounds(10, 879, 104, 25);
		standardDataPanel.add(label_218);
		
		JLabel label_219 = new JLabel("");
		label_219.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_219.setBorder(compoundBorder);
		label_219.setBounds(113, 879, 182, 25);
		standardDataPanel.add(label_219);
		
		JLabel label_220 = new JLabel("Hasat zamanı");
		label_220.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_220.setBorder(compoundBorder);
		label_220.setBounds(305, 879, 104, 25);
		standardDataPanel.add(label_220);
		
		JLabel label_221 = new JLabel("");
		label_221.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_221.setBorder(compoundBorder);
		label_221.setBounds(408, 879, 182, 25);
		standardDataPanel.add(label_221);
		
		JLabel label_222 = new JLabel("");
		label_222.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_222.setBorder(compoundBorder);
		label_222.setBounds(408, 855, 182, 25);
		standardDataPanel.add(label_222);
		
		JLabel label_223 = new JLabel("Ekim Zamanı");
		label_223.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_223.setBorder(compoundBorder);
		label_223.setBounds(305, 855, 104, 25);
		standardDataPanel.add(label_223);
		
		JLabel label_224 = new JLabel("Ekilen Bitki");
		label_224.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_224.setBorder(compoundBorder);
		label_224.setBounds(305, 831, 104, 25);
		standardDataPanel.add(label_224);
		
		JLabel label_225 = new JLabel("");
		label_225.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_225.setBorder(compoundBorder);
		label_225.setBounds(408, 831, 182, 25);
		standardDataPanel.add(label_225);
		
		JLabel label_226 = new JLabel("Bolüm ID");
		label_226.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_226.setBorder(compoundBorder);
		label_226.setBounds(305, 802, 104, 30);
		standardDataPanel.add(label_226);
		
		JLabel label_227 = new JLabel("1");
		label_227.setHorizontalAlignment(SwingConstants.CENTER);
		label_227.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_227.setBorder(compoundBorder);
		label_227.setBounds(408, 802, 30, 30);
		standardDataPanel.add(label_227);
		
		JLabel label_228 = new JLabel("Hol ID");
		label_228.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_228.setBorder(compoundBorder);
		label_228.setBounds(437, 802, 75, 30);
		standardDataPanel.add(label_228);
		
		JLabel label_229 = new JLabel("23");
		label_229.setHorizontalAlignment(SwingConstants.CENTER);
		label_229.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_229.setBorder(compoundBorder);
		label_229.setBounds(511, 802, 30, 30);
		standardDataPanel.add(label_229);
		
		JButton button_22 = new JButton("SIL");
		button_22.setBorder(compoundBorder);
		button_22.setBounds(540, 802, 50, 30);
		standardDataPanel.add(button_22);
		
		JLabel label_230 = new JLabel("Bolüm ID");
		label_230.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_230.setBorder(compoundBorder);
		label_230.setBounds(600, 802, 104, 30);
		standardDataPanel.add(label_230);
		
		JLabel label_231 = new JLabel("1");
		label_231.setHorizontalAlignment(SwingConstants.CENTER);
		label_231.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_231.setBorder(compoundBorder);
		label_231.setBounds(703, 802, 30, 30);
		standardDataPanel.add(label_231);
		
		JLabel label_232 = new JLabel("Hol ID");
		label_232.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_232.setBorder(compoundBorder);
		label_232.setBounds(732, 802, 75, 30);
		standardDataPanel.add(label_232);
		
		JLabel label_233 = new JLabel("24");
		label_233.setHorizontalAlignment(SwingConstants.CENTER);
		label_233.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_233.setBorder(compoundBorder);
		label_233.setBounds(806, 802, 30, 30);
		standardDataPanel.add(label_233);
		
		JButton button_23 = new JButton("SIL");
		button_23.setBorder(compoundBorder);
		button_23.setBounds(835, 802, 50, 30);
		standardDataPanel.add(button_23);
		
		JLabel label_234 = new JLabel("");
		label_234.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_234.setBorder(compoundBorder);
		label_234.setBounds(703, 831, 182, 25);
		standardDataPanel.add(label_234);
		
		JLabel label_235 = new JLabel("Ekilen Bitki");
		label_235.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_235.setBorder(compoundBorder);
		label_235.setBounds(600, 831, 104, 25);
		standardDataPanel.add(label_235);
		
		JLabel label_236 = new JLabel("Ekim Zamanı");
		label_236.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_236.setBorder(compoundBorder);
		label_236.setBounds(600, 855, 104, 25);
		standardDataPanel.add(label_236);
		
		JLabel label_237 = new JLabel("");
		label_237.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_237.setBorder(compoundBorder);
		label_237.setBounds(703, 855, 182, 25);
		standardDataPanel.add(label_237);
		
		JLabel label_238 = new JLabel("Hasat zamanı");
		label_238.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_238.setBorder(compoundBorder);
		label_238.setBounds(600, 879, 104, 25);
		standardDataPanel.add(label_238);
		
		JLabel label_239 = new JLabel("");
		label_239.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_239.setBorder(compoundBorder);
		label_239.setBounds(703, 879, 182, 25);
		standardDataPanel.add(label_239);

		try
		{
			connection = new ConnectDatabase(true);
		}
		catch (SQLException e)
		{
			errorLog.generateLog(e);
		}
	}

	public void setHallID(Object hallID)
	{
		//try
		//{
			//setStaticCells(hallID);
		//}
		//catch (SQLException e)
		//{
		//	errorLog.generateLog(e);
		//}
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