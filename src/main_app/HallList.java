package main_app;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import ui.CreateLabel;
import ui.CreateTableCells;
import utils.ErrorLog;
import utils.LoginDataDisplay;
import db_process.ConnectDatabase;
import db_process.ReadDatabase;

public class HallList extends JFrame implements LoginDataDisplay
{
	private static final long	serialVersionUID	= 6569607035183642991L;
	private ErrorLog			errorLog			= null;

	private JPanel				contentPane;
	private JPanel				standardDataPanel;

	private CompoundBorder		compoundBorder;

	private ConnectDatabase		connection;
	private CreateLabel			createLabel;
	private CreateTableCells	createTableCell;

	private int					divID;
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
		errorLog = new ErrorLog();
		
		setResizable(false);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(900, 10, 930, 970);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Etiket nesnesi olustur
		createLabel = new CreateLabel();
		// Create STATIC table cells
		createTableCell = new CreateTableCells(contentPane);

		// Border for Label
		compoundBorder = new CompoundBorder(new LineBorder(new Color(0, 0, 0)), new EmptyBorder(10, 5, 10, 0));

		standardDataPanel = new JPanel();
		standardDataPanel.setBackground(Color.WHITE);
		standardDataPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		standardDataPanel.setBounds(10, 11, 895, 917);
		standardDataPanel.setLayout(null);
		contentPane.add(standardDataPanel);

		// Create Bolum label
		createBolumCell();
		// Create Buttons
		//createButtons();

		JLabel label_5 = new JLabel("");
		label_5.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_5.setBorder(compoundBorder);
		label_5.setBounds(113, 40, 182, 25);
		standardDataPanel.add(label_5);

		JLabel label_7 = new JLabel("");
		label_7.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_7.setBorder(compoundBorder);
		label_7.setBounds(113, 64, 182, 25);
		standardDataPanel.add(label_7);

		JLabel label_9 = new JLabel("");
		label_9.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_9.setBorder(compoundBorder);
		label_9.setBounds(113, 88, 182, 25);
		standardDataPanel.add(label_9);

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

		JLabel label_15 = new JLabel("");
		label_15.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_15.setBorder(compoundBorder);
		label_15.setBounds(408, 40, 182, 25);
		standardDataPanel.add(label_15);

		JLabel label_24 = new JLabel("");
		label_24.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_24.setBorder(compoundBorder);
		label_24.setBounds(703, 40, 182, 25);
		standardDataPanel.add(label_24);

		JLabel label_27 = new JLabel("");
		label_27.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_27.setBorder(compoundBorder);
		label_27.setBounds(703, 64, 182, 25);
		standardDataPanel.add(label_27);

		JLabel label_29 = new JLabel("");
		label_29.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_29.setBorder(compoundBorder);
		label_29.setBounds(703, 88, 182, 25);
		standardDataPanel.add(label_29);

		JLabel label_35 = new JLabel("");
		label_35.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_35.setBorder(compoundBorder);
		label_35.setBounds(113, 153, 182, 25);
		standardDataPanel.add(label_35);

		JLabel label_37 = new JLabel("");
		label_37.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_37.setBorder(compoundBorder);
		label_37.setBounds(113, 177, 182, 25);
		standardDataPanel.add(label_37);

		JLabel label_39 = new JLabel("");
		label_39.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_39.setBorder(compoundBorder);
		label_39.setBounds(113, 201, 182, 25);
		standardDataPanel.add(label_39);

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

		JLabel label_45 = new JLabel("");
		label_45.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_45.setBorder(compoundBorder);
		label_45.setBounds(408, 153, 182, 25);
		standardDataPanel.add(label_45);

		JLabel label_57 = new JLabel("");
		label_57.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_57.setBorder(compoundBorder);
		label_57.setBounds(703, 177, 182, 25);
		standardDataPanel.add(label_57);

		JLabel label_59 = new JLabel("");
		label_59.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_59.setBorder(compoundBorder);
		label_59.setBounds(703, 201, 182, 25);
		standardDataPanel.add(label_59);

		JLabel label_65 = new JLabel("");
		label_65.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_65.setBorder(compoundBorder);
		label_65.setBounds(113, 266, 182, 25);
		standardDataPanel.add(label_65);

		JLabel label_67 = new JLabel("");
		label_67.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_67.setBorder(compoundBorder);
		label_67.setBounds(113, 290, 182, 25);
		standardDataPanel.add(label_67);

		JLabel label_69 = new JLabel("");
		label_69.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_69.setBorder(compoundBorder);
		label_69.setBounds(113, 314, 182, 25);
		standardDataPanel.add(label_69);

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

		JLabel label_75 = new JLabel("");
		label_75.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_75.setBorder(compoundBorder);
		label_75.setBounds(408, 266, 182, 25);
		standardDataPanel.add(label_75);

		JLabel label_84 = new JLabel("");
		label_84.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_84.setBorder(compoundBorder);
		label_84.setBounds(703, 266, 182, 25);
		standardDataPanel.add(label_84);

		JLabel label_87 = new JLabel("");
		label_87.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_87.setBorder(compoundBorder);
		label_87.setBounds(703, 290, 182, 25);
		standardDataPanel.add(label_87);

		JLabel label_89 = new JLabel("");
		label_89.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_89.setBorder(compoundBorder);
		label_89.setBounds(703, 314, 182, 25);
		standardDataPanel.add(label_89);

		JLabel label_95 = new JLabel("");
		label_95.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_95.setBorder(compoundBorder);
		label_95.setBounds(113, 379, 182, 25);
		standardDataPanel.add(label_95);

		JLabel label_97 = new JLabel("");
		label_97.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_97.setBorder(compoundBorder);
		label_97.setBounds(113, 403, 182, 25);
		standardDataPanel.add(label_97);

		JLabel label_99 = new JLabel("");
		label_99.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_99.setBorder(compoundBorder);
		label_99.setBounds(113, 427, 182, 25);
		standardDataPanel.add(label_99);

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

		JLabel label_105 = new JLabel("");
		label_105.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_105.setBorder(compoundBorder);
		label_105.setBounds(408, 379, 182, 25);
		standardDataPanel.add(label_105);

		JLabel label_114 = new JLabel("");
		label_114.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_114.setBorder(compoundBorder);
		label_114.setBounds(703, 379, 182, 25);
		standardDataPanel.add(label_114);

		JLabel label_117 = new JLabel("");
		label_117.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_117.setBorder(compoundBorder);
		label_117.setBounds(703, 403, 182, 25);
		standardDataPanel.add(label_117);

		JLabel label_119 = new JLabel("");
		label_119.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_119.setBorder(compoundBorder);
		label_119.setBounds(703, 427, 182, 25);
		standardDataPanel.add(label_119);

		JLabel label_125 = new JLabel("");
		label_125.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_125.setBorder(compoundBorder);
		label_125.setBounds(113, 492, 182, 25);
		standardDataPanel.add(label_125);

		JLabel label_127 = new JLabel("");
		label_127.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_127.setBorder(compoundBorder);
		label_127.setBounds(113, 516, 182, 25);
		standardDataPanel.add(label_127);

		JLabel label_129 = new JLabel("");
		label_129.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_129.setBorder(compoundBorder);
		label_129.setBounds(113, 540, 182, 25);
		standardDataPanel.add(label_129);

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

		JLabel label_135 = new JLabel("");
		label_135.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_135.setBorder(compoundBorder);
		label_135.setBounds(408, 492, 182, 25);
		standardDataPanel.add(label_135);

		JLabel label_144 = new JLabel("");
		label_144.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_144.setBorder(compoundBorder);
		label_144.setBounds(703, 492, 182, 25);
		standardDataPanel.add(label_144);

		JLabel label_147 = new JLabel("");
		label_147.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_147.setBorder(compoundBorder);
		label_147.setBounds(703, 516, 182, 25);
		standardDataPanel.add(label_147);

		JLabel label_149 = new JLabel("");
		label_149.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_149.setBorder(compoundBorder);
		label_149.setBounds(703, 540, 182, 25);
		standardDataPanel.add(label_149);

		JLabel label_155 = new JLabel("");
		label_155.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_155.setBorder(compoundBorder);
		label_155.setBounds(113, 605, 182, 25);
		standardDataPanel.add(label_155);

		JLabel label_157 = new JLabel("");
		label_157.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_157.setBorder(compoundBorder);
		label_157.setBounds(113, 629, 182, 25);
		standardDataPanel.add(label_157);

		JLabel label_159 = new JLabel("");
		label_159.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_159.setBorder(compoundBorder);
		label_159.setBounds(113, 653, 182, 25);
		standardDataPanel.add(label_159);

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

		JLabel label_165 = new JLabel("");
		label_165.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_165.setBorder(compoundBorder);
		label_165.setBounds(408, 605, 182, 25);
		standardDataPanel.add(label_165);

		JLabel label_174 = new JLabel("");
		label_174.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_174.setBorder(compoundBorder);
		label_174.setBounds(703, 605, 182, 25);
		standardDataPanel.add(label_174);

		JLabel label_177 = new JLabel("");
		label_177.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_177.setBorder(compoundBorder);
		label_177.setBounds(703, 629, 182, 25);
		standardDataPanel.add(label_177);

		JLabel label_179 = new JLabel("");
		label_179.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_179.setBorder(compoundBorder);
		label_179.setBounds(703, 653, 182, 25);
		standardDataPanel.add(label_179);

		JLabel label_185 = new JLabel("");
		label_185.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_185.setBorder(compoundBorder);
		label_185.setBounds(113, 718, 182, 25);
		standardDataPanel.add(label_185);

		JLabel label_187 = new JLabel("");
		label_187.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_187.setBorder(compoundBorder);
		label_187.setBounds(113, 742, 182, 25);
		standardDataPanel.add(label_187);

		JLabel label_189 = new JLabel("");
		label_189.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_189.setBorder(compoundBorder);
		label_189.setBounds(113, 766, 182, 25);
		standardDataPanel.add(label_189);

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

		JLabel label_195 = new JLabel("");
		label_195.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_195.setBorder(compoundBorder);
		label_195.setBounds(408, 718, 182, 25);
		standardDataPanel.add(label_195);

		JLabel label_204 = new JLabel("");
		label_204.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_204.setBorder(compoundBorder);
		label_204.setBounds(703, 718, 182, 25);
		standardDataPanel.add(label_204);

		JLabel label_207 = new JLabel("");
		label_207.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_207.setBorder(compoundBorder);
		label_207.setBounds(703, 742, 182, 25);
		standardDataPanel.add(label_207);

		JLabel label_209 = new JLabel("");
		label_209.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_209.setBorder(compoundBorder);
		label_209.setBounds(703, 766, 182, 25);
		standardDataPanel.add(label_209);

		JLabel label_215 = new JLabel("");
		label_215.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_215.setBorder(compoundBorder);
		label_215.setBounds(113, 831, 182, 25);
		standardDataPanel.add(label_215);

		JLabel label_217 = new JLabel("");
		label_217.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_217.setBorder(compoundBorder);
		label_217.setBounds(113, 855, 182, 25);
		standardDataPanel.add(label_217);

		JLabel label_219 = new JLabel("");
		label_219.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_219.setBorder(compoundBorder);
		label_219.setBounds(113, 879, 182, 25);
		standardDataPanel.add(label_219);

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

		JLabel label_225 = new JLabel("");
		label_225.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_225.setBorder(compoundBorder);
		label_225.setBounds(408, 831, 182, 25);
		standardDataPanel.add(label_225);

		JLabel label_234 = new JLabel("");
		label_234.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_234.setBorder(compoundBorder);
		label_234.setBounds(703, 831, 182, 25);
		standardDataPanel.add(label_234);

		JLabel label_237 = new JLabel("");
		label_237.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_237.setBorder(compoundBorder);
		label_237.setBounds(703, 855, 182, 25);
		standardDataPanel.add(label_237);

		JLabel label_239 = new JLabel("");
		label_239.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_239.setBorder(compoundBorder);
		label_239.setBounds(703, 879, 182, 25);
		standardDataPanel.add(label_239);

		JLabel label_54 = new JLabel("");
		label_54.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_54.setBorder(compoundBorder);
		label_54.setBounds(703, 153, 182, 25);
		standardDataPanel.add(label_54);

		try
		{
			connection = new ConnectDatabase(true);
		}
		catch (SQLException e)
		{
			errorLog.generateLog(e);
		}
	}

	private void createBolumCell()
	{
		// Generate BOLUM
		standardDataPanel.add(createTableCell.generateBolum(10, 11));
		standardDataPanel.add(createTableCell.generateBolum(305, 11));
		standardDataPanel.add(createTableCell.generateBolum(10, 237));
		standardDataPanel.add(createTableCell.generateBolum(600, 124));
		standardDataPanel.add(createTableCell.generateBolum(305, 124));
		standardDataPanel.add(createTableCell.generateBolum(10, 124));
		standardDataPanel.add(createTableCell.generateBolum(600, 11));
		standardDataPanel.add(createTableCell.generateBolum(10, 463));
		standardDataPanel.add(createTableCell.generateBolum(600, 350));
		standardDataPanel.add(createTableCell.generateBolum(305, 350));
		standardDataPanel.add(createTableCell.generateBolum(10, 350));
		standardDataPanel.add(createTableCell.generateBolum(600, 237));
		standardDataPanel.add(createTableCell.generateBolum(305, 237));
		standardDataPanel.add(createTableCell.generateBolum(10, 689));
		standardDataPanel.add(createTableCell.generateBolum(600, 576));
		standardDataPanel.add(createTableCell.generateBolum(305, 576));
		standardDataPanel.add(createTableCell.generateBolum(10, 576));
		standardDataPanel.add(createTableCell.generateBolum(600, 463));
		standardDataPanel.add(createTableCell.generateBolum(305, 463));
		standardDataPanel.add(createTableCell.generateBolum(600, 802));
		standardDataPanel.add(createTableCell.generateBolum(305, 802));
		standardDataPanel.add(createTableCell.generateBolum(10, 802));
		standardDataPanel.add(createTableCell.generateBolum(600, 689));
		standardDataPanel.add(createTableCell.generateBolum(305, 689));

		// Generate HALL
		standardDataPanel.add(createTableCell.generateHall(142, 11));
		standardDataPanel.add(createTableCell.generateHall(732, 124));
		standardDataPanel.add(createTableCell.generateHall(437, 124));
		standardDataPanel.add(createTableCell.generateHall(142, 124));
		standardDataPanel.add(createTableCell.generateHall(732, 11));
		standardDataPanel.add(createTableCell.generateHall(437, 11));
		standardDataPanel.add(createTableCell.generateHall(732, 350));
		standardDataPanel.add(createTableCell.generateHall(437, 350));
		standardDataPanel.add(createTableCell.generateHall(142, 350));
		standardDataPanel.add(createTableCell.generateHall(732, 237));
		standardDataPanel.add(createTableCell.generateHall(437, 237));
		standardDataPanel.add(createTableCell.generateHall(142, 237));
		standardDataPanel.add(createTableCell.generateHall(437, 802));
		standardDataPanel.add(createTableCell.generateHall(142, 802));
		standardDataPanel.add(createTableCell.generateHall(732, 689));
		standardDataPanel.add(createTableCell.generateHall(437, 689));
		standardDataPanel.add(createTableCell.generateHall(142, 689));
		standardDataPanel.add(createTableCell.generateHall(437, 576));
		standardDataPanel.add(createTableCell.generateHall(142, 576));
		standardDataPanel.add(createTableCell.generateHall(732, 802));
		standardDataPanel.add(createTableCell.generateHall(732, 576));
		standardDataPanel.add(createTableCell.generateHall(732, 463));
		standardDataPanel.add(createTableCell.generateHall(437, 463));
		standardDataPanel.add(createTableCell.generateHall(142, 463));

		// Generate Ekilen Bitki
		standardDataPanel.add(createTableCell.generateEkilen(10, 40));
		standardDataPanel.add(createTableCell.generateEkilen(305, 266));
		standardDataPanel.add(createTableCell.generateEkilen(10, 266));
		standardDataPanel.add(createTableCell.generateEkilen(600, 153));
		standardDataPanel.add(createTableCell.generateEkilen(10, 153));
		standardDataPanel.add(createTableCell.generateEkilen(600, 40));
		standardDataPanel.add(createTableCell.generateEkilen(305, 40));
		standardDataPanel.add(createTableCell.generateEkilen(10, 605));
		standardDataPanel.add(createTableCell.generateEkilen(600, 492));
		standardDataPanel.add(createTableCell.generateEkilen(305, 492));
		standardDataPanel.add(createTableCell.generateEkilen(10, 492));
		standardDataPanel.add(createTableCell.generateEkilen(600, 379));
		standardDataPanel.add(createTableCell.generateEkilen(305, 379));
		standardDataPanel.add(createTableCell.generateEkilen(10, 379));
		standardDataPanel.add(createTableCell.generateEkilen(600, 266));
		standardDataPanel.add(createTableCell.generateEkilen(600, 831));
		standardDataPanel.add(createTableCell.generateEkilen(305, 831));
		standardDataPanel.add(createTableCell.generateEkilen(10, 831));
		standardDataPanel.add(createTableCell.generateEkilen(600, 718));
		standardDataPanel.add(createTableCell.generateEkilen(305, 718));
		standardDataPanel.add(createTableCell.generateEkilen(10, 718));
		standardDataPanel.add(createTableCell.generateEkilen(600, 605));
		standardDataPanel.add(createTableCell.generateEkilen(305, 605));

		// Generate Ekim Zamani
		standardDataPanel.add(createTableCell.generateEkim(305, 290));
		standardDataPanel.add(createTableCell.generateEkim(10, 290));
		standardDataPanel.add(createTableCell.generateEkim(600, 177));
		standardDataPanel.add(createTableCell.generateEkim(305, 177));
		standardDataPanel.add(createTableCell.generateEkim(10, 177));
		standardDataPanel.add(createTableCell.generateEkim(600, 64));
		standardDataPanel.add(createTableCell.generateEkim(305, 64));
		standardDataPanel.add(createTableCell.generateEkim(10, 64));
		standardDataPanel.add(createTableCell.generateEkim(305, 629));
		standardDataPanel.add(createTableCell.generateEkim(600, 516));
		standardDataPanel.add(createTableCell.generateEkim(305, 516));
		standardDataPanel.add(createTableCell.generateEkim(10, 516));
		standardDataPanel.add(createTableCell.generateEkim(600, 403));
		standardDataPanel.add(createTableCell.generateEkim(305, 403));
		standardDataPanel.add(createTableCell.generateEkim(10, 403));
		standardDataPanel.add(createTableCell.generateEkim(600, 290));
		standardDataPanel.add(createTableCell.generateEkim(600, 855));
		standardDataPanel.add(createTableCell.generateEkim(305, 855));
		standardDataPanel.add(createTableCell.generateEkim(10, 855));
		standardDataPanel.add(createTableCell.generateEkim(600, 742));
		standardDataPanel.add(createTableCell.generateEkim(305, 742));
		standardDataPanel.add(createTableCell.generateEkim(10, 742));
		standardDataPanel.add(createTableCell.generateEkim(600, 629));
		standardDataPanel.add(createTableCell.generateEkim(10, 629));

		// Generate Hasat Zamani
		standardDataPanel.add(createTableCell.generateHasat(600, 314));
		standardDataPanel.add(createTableCell.generateHasat(10, 314));
		standardDataPanel.add(createTableCell.generateHasat(600, 201));
		standardDataPanel.add(createTableCell.generateHasat(305, 201));
		standardDataPanel.add(createTableCell.generateHasat(10, 201));
		standardDataPanel.add(createTableCell.generateHasat(600, 88));
		standardDataPanel.add(createTableCell.generateHasat(305, 88));
		standardDataPanel.add(createTableCell.generateHasat(10, 88));
		standardDataPanel.add(createTableCell.generateHasat(305, 653));
		standardDataPanel.add(createTableCell.generateHasat(10, 653));
		standardDataPanel.add(createTableCell.generateHasat(600, 540));
		standardDataPanel.add(createTableCell.generateHasat(305, 540));
		standardDataPanel.add(createTableCell.generateHasat(10, 540));
		standardDataPanel.add(createTableCell.generateHasat(600, 427));
		standardDataPanel.add(createTableCell.generateHasat(305, 427));
		standardDataPanel.add(createTableCell.generateHasat(10, 427));
		standardDataPanel.add(createTableCell.generateHasat(600, 879));
		standardDataPanel.add(createTableCell.generateHasat(305, 879));
		standardDataPanel.add(createTableCell.generateHasat(10, 879));
		standardDataPanel.add(createTableCell.generateHasat(600, 766));
		standardDataPanel.add(createTableCell.generateHasat(305, 766));
		standardDataPanel.add(createTableCell.generateHasat(10, 766));
		standardDataPanel.add(createTableCell.generateHasat(600, 653));
		standardDataPanel.add(createTableCell.generateHasat(305, 314));

		// Generate Hall Numbers
		standardDataPanel.add(createTableCell.generateHallNum("1", 216, 11));
		standardDataPanel.add(createTableCell.generateHallNum("2", 511, 11));
		standardDataPanel.add(createTableCell.generateHallNum("3", 806, 11));
		standardDataPanel.add(createTableCell.generateHallNum("4", 216, 124));
		standardDataPanel.add(createTableCell.generateHallNum("5", 511, 124));
		standardDataPanel.add(createTableCell.generateHallNum("6", 806, 124));
		standardDataPanel.add(createTableCell.generateHallNum("7", 216, 237));
		standardDataPanel.add(createTableCell.generateHallNum("8", 511, 237));
		standardDataPanel.add(createTableCell.generateHallNum("9", 806, 237));
		standardDataPanel.add(createTableCell.generateHallNum("10", 216, 350));
		standardDataPanel.add(createTableCell.generateHallNum("11", 511, 350));
		standardDataPanel.add(createTableCell.generateHallNum("12", 806, 350));
		standardDataPanel.add(createTableCell.generateHallNum("13", 216, 463));
		standardDataPanel.add(createTableCell.generateHallNum("14", 511, 463));
		standardDataPanel.add(createTableCell.generateHallNum("15", 806, 463));
		standardDataPanel.add(createTableCell.generateHallNum("16", 216, 576));
		standardDataPanel.add(createTableCell.generateHallNum("17", 511, 576));
		standardDataPanel.add(createTableCell.generateHallNum("18", 806, 576));
		standardDataPanel.add(createTableCell.generateHallNum("19", 216, 689));
		standardDataPanel.add(createTableCell.generateHallNum("20", 511, 689));
		standardDataPanel.add(createTableCell.generateHallNum("21", 806, 689));
		standardDataPanel.add(createTableCell.generateHallNum("22", 216, 802));
		standardDataPanel.add(createTableCell.generateHallNum("23", 511, 802));
		standardDataPanel.add(createTableCell.generateHallNum("24", 806, 802));
	}

	public void setBolumID(int bID, int userID)
	{
		String bolumID = "" + bID;
		
		divID = Integer.parseInt(bolumID);
		
		try
		{
			createButtons(userID);
		}
		catch (SQLException e)
		{
			errorLog.generateLog(e);
		}

		standardDataPanel.add(createTableCell.assignBolumId(bolumID, 703, 124));
		standardDataPanel.add(createTableCell.assignBolumId(bolumID, 408, 124));
		standardDataPanel.add(createTableCell.assignBolumId(bolumID, 113, 124));
		standardDataPanel.add(createTableCell.assignBolumId(bolumID, 703, 11));
		standardDataPanel.add(createTableCell.assignBolumId(bolumID, 408, 11));
		standardDataPanel.add(createTableCell.assignBolumId(bolumID, 113, 11));
		standardDataPanel.add(createTableCell.assignBolumId(bolumID, 703, 350));
		standardDataPanel.add(createTableCell.assignBolumId(bolumID, 408, 350));
		standardDataPanel.add(createTableCell.assignBolumId(bolumID, 113, 350));
		standardDataPanel.add(createTableCell.assignBolumId(bolumID, 703, 237));
		standardDataPanel.add(createTableCell.assignBolumId(bolumID, 408, 237));
		standardDataPanel.add(createTableCell.assignBolumId(bolumID, 113, 237));
		standardDataPanel.add(createTableCell.assignBolumId(bolumID, 703, 576));
		standardDataPanel.add(createTableCell.assignBolumId(bolumID, 408, 576));
		standardDataPanel.add(createTableCell.assignBolumId(bolumID, 113, 576));
		standardDataPanel.add(createTableCell.assignBolumId(bolumID, 703, 463));
		standardDataPanel.add(createTableCell.assignBolumId(bolumID, 408, 463));
		standardDataPanel.add(createTableCell.assignBolumId(bolumID, 113, 463));
		standardDataPanel.add(createTableCell.assignBolumId(bolumID, 408, 802));
		standardDataPanel.add(createTableCell.assignBolumId(bolumID, 113, 802));
		standardDataPanel.add(createTableCell.assignBolumId(bolumID, 703, 689));
		standardDataPanel.add(createTableCell.assignBolumId(bolumID, 408, 689));
		standardDataPanel.add(createTableCell.assignBolumId(bolumID, 113, 689));
		standardDataPanel.add(createTableCell.assignBolumId(bolumID, 703, 802));
	}

	private void createButtons(int userID) throws SQLException
	{
		ArrayList<Integer> hall_id = new ArrayList<Integer>();
		
		ReadDatabase getHallIds = new ReadDatabase(connection.getMysqlConnection());
		ResultSet rs = getHallIds.getData("SELECT _id FROM hall WHERE bolum_id = '" + divID + "' ORDER BY _id ASC");
		while(rs.next())
		{
			hall_id.add(rs.getInt(1));
		}
		
		standardDataPanel.add(createTableCell.generateButton(divID, hall_id.get(0), userID, 1, 245, 11));
		standardDataPanel.add(createTableCell.generateButton(divID, hall_id.get(1), userID, 2, 540, 11));
		standardDataPanel.add(createTableCell.generateButton(divID, hall_id.get(2), userID, 3, 835, 11));
		standardDataPanel.add(createTableCell.generateButton(divID, hall_id.get(3), userID, 4, 245, 124));
		standardDataPanel.add(createTableCell.generateButton(divID, hall_id.get(4), userID, 5, 540, 124));
		standardDataPanel.add(createTableCell.generateButton(divID, hall_id.get(5), userID, 6, 835, 124));
		standardDataPanel.add(createTableCell.generateButton(divID, hall_id.get(6), userID, 7, 245, 237));
		standardDataPanel.add(createTableCell.generateButton(divID, hall_id.get(7), userID, 8, 540, 237));
		standardDataPanel.add(createTableCell.generateButton(divID, hall_id.get(8), userID, 9, 835, 237));
		standardDataPanel.add(createTableCell.generateButton(divID, hall_id.get(9), userID, 10, 245, 350));
		standardDataPanel.add(createTableCell.generateButton(divID, hall_id.get(10), userID, 11, 540, 350));
		standardDataPanel.add(createTableCell.generateButton(divID, hall_id.get(11), userID, 12, 835, 350));
		standardDataPanel.add(createTableCell.generateButton(divID, hall_id.get(12), userID, 13, 245, 463));
		standardDataPanel.add(createTableCell.generateButton(divID, hall_id.get(13), userID, 14, 540, 463));
		standardDataPanel.add(createTableCell.generateButton(divID, hall_id.get(14), userID, 15, 835, 463));
		standardDataPanel.add(createTableCell.generateButton(divID, hall_id.get(15), userID, 16, 245, 576));
		standardDataPanel.add(createTableCell.generateButton(divID, hall_id.get(16), userID, 17, 540, 576));
		standardDataPanel.add(createTableCell.generateButton(divID, hall_id.get(17), userID, 18, 835, 576));
		standardDataPanel.add(createTableCell.generateButton(divID, hall_id.get(18), userID, 19, 245, 689));
		standardDataPanel.add(createTableCell.generateButton(divID, hall_id.get(19), userID, 20, 540, 689));
		standardDataPanel.add(createTableCell.generateButton(divID, hall_id.get(20), userID, 21, 835, 689));
		standardDataPanel.add(createTableCell.generateButton(divID, hall_id.get(21), userID, 22, 245, 802));
		standardDataPanel.add(createTableCell.generateButton(divID, hall_id.get(22), userID, 23, 540, 802));
		standardDataPanel.add(createTableCell.generateButton(divID, hall_id.get(23), userID, 24, 835, 802));
	}

	@Override
	public void setUserID(int _id)
	{
		this._id = _id;
	}

	@Override
	public void setUserName(String user_name)
	{
		// Unused implementation
	}

	@Override
	public void setAuthID(int auth_id)
	{
		// Unused implementation
	}
}