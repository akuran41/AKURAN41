package main_app;

import java.awt.Color;
import java.awt.EventQueue;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import ui.CreateTableCells;
import ui.HallListData;
import utils.ErrorLog;
import utils.LoginDataDisplay;
import db_process.ConnectDatabase;
import db_process.ReadDatabase;

public class HallList extends JFrame implements LoginDataDisplay
{
	private static final long		serialVersionUID	= 6569607035183642991L;
	private ErrorLog				errorLog			= null;

	private JPanel					contentPane;
	private JPanel					standardDataPanel;

	private CompoundBorder			compoundBorder;

	private ConnectDatabase			connection;
	private CreateTableCells		createTableCell;
	private HallListData			hallList;

	ArrayList<ArrayList<Object>>	bolumData			= new ArrayList<ArrayList<Object>>();

	private int						divID;

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
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(900, 10, 930, 970);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

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

		hallList = new HallListData(bolumData, compoundBorder);

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
		standardDataPanel.add(createTableCell.generateEkilen(305, 40));
		standardDataPanel.add(createTableCell.generateEkilen(600, 40));

		standardDataPanel.add(createTableCell.generateEkilen(10, 266));
		standardDataPanel.add(createTableCell.generateEkilen(305, 266));
		standardDataPanel.add(createTableCell.generateEkilen(600, 266));

		standardDataPanel.add(createTableCell.generateEkilen(10, 153));
		standardDataPanel.add(createTableCell.generateEkilen(305, 153));
		standardDataPanel.add(createTableCell.generateEkilen(600, 153));

		standardDataPanel.add(createTableCell.generateEkilen(10, 492));
		standardDataPanel.add(createTableCell.generateEkilen(305, 492));
		standardDataPanel.add(createTableCell.generateEkilen(600, 492));

		standardDataPanel.add(createTableCell.generateEkilen(10, 379));
		standardDataPanel.add(createTableCell.generateEkilen(305, 379));
		standardDataPanel.add(createTableCell.generateEkilen(600, 379));

		standardDataPanel.add(createTableCell.generateEkilen(10, 605));
		standardDataPanel.add(createTableCell.generateEkilen(305, 605));
		standardDataPanel.add(createTableCell.generateEkilen(600, 605));

		standardDataPanel.add(createTableCell.generateEkilen(10, 718));
		standardDataPanel.add(createTableCell.generateEkilen(305, 718));
		standardDataPanel.add(createTableCell.generateEkilen(600, 718));

		standardDataPanel.add(createTableCell.generateEkilen(10, 831));
		standardDataPanel.add(createTableCell.generateEkilen(305, 831));
		standardDataPanel.add(createTableCell.generateEkilen(600, 831));

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
			createButtons(userID, Integer.parseInt(bolumID));
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

	private void createButtons(int userID, int bolumID) throws SQLException
	{
		ArrayList<Integer> hall_id = new ArrayList<Integer>();

		ReadDatabase getHallIds = new ReadDatabase(connection.getMysqlConnection());
		ResultSet rs = getHallIds.getData("SELECT _id FROM hall WHERE bolum_id = '" + divID + "' ORDER BY _id ASC");
		while (rs.next())
		{
			hall_id.add(rs.getInt(1));
		}

		// Get Entire Hall data from selected DIV
		ResultSet rsHall = getHallIds
				.getData("SELECT b.bitki_adi, b.bitki_ekme_zamani, b.bitki_hasat_zamani FROM `hall` h LEFT JOIN bitki b ON h.bitki_id = b._id WHERE h.bolum_id = '"
						+ bolumID + "' ORDER BY h._id ASC");
		while (rsHall.next())
		{
			ArrayList<Object> obj = new ArrayList<Object>();
			obj.add(rsHall.getString(1));
			obj.add(rsHall.getString(2));
			obj.add(rsHall.getString(3));

			bolumData.add(obj);
		}

		standardDataPanel.add(hallList.generateEkilenCell(113, 40, 0));
		standardDataPanel.add(hallList.generateEkimCell(113, 64, 0));
		standardDataPanel.add(hallList.generateHasatCell(113, 88, 0));

		standardDataPanel.add(hallList.generateEkilenCell(408, 40, 1));
		standardDataPanel.add(hallList.generateEkimCell(408, 64, 1));
		standardDataPanel.add(hallList.generateHasatCell(408, 88, 1));

		standardDataPanel.add(hallList.generateEkilenCell(703, 40, 2));
		standardDataPanel.add(hallList.generateEkimCell(703, 64, 2));
		standardDataPanel.add(hallList.generateHasatCell(703, 88, 2));

		standardDataPanel.add(hallList.generateEkilenCell(113, 153, 3));
		standardDataPanel.add(hallList.generateEkimCell(113, 177, 3));
		standardDataPanel.add(hallList.generateHasatCell(113, 201, 3));

		standardDataPanel.add(hallList.generateEkilenCell(408, 153, 4));
		standardDataPanel.add(hallList.generateEkimCell(408, 177, 4));
		standardDataPanel.add(hallList.generateHasatCell(408, 201, 4));

		standardDataPanel.add(hallList.generateEkilenCell(703, 153, 5));
		standardDataPanel.add(hallList.generateEkimCell(703, 177, 5));
		standardDataPanel.add(hallList.generateHasatCell(703, 201, 5));

		standardDataPanel.add(hallList.generateEkilenCell(113, 266, 6));
		standardDataPanel.add(hallList.generateEkimCell(113, 290, 6));
		standardDataPanel.add(hallList.generateHasatCell(113, 314, 6));

		standardDataPanel.add(hallList.generateEkilenCell(408, 266, 7));
		standardDataPanel.add(hallList.generateEkimCell(408, 290, 7));
		standardDataPanel.add(hallList.generateHasatCell(408, 314, 7));

		standardDataPanel.add(hallList.generateEkilenCell(703, 266, 8));
		standardDataPanel.add(hallList.generateEkimCell(703, 290, 8));
		standardDataPanel.add(hallList.generateHasatCell(703, 314, 8));

		standardDataPanel.add(hallList.generateEkilenCell(113, 379, 9));
		standardDataPanel.add(hallList.generateEkimCell(113, 403, 9));
		standardDataPanel.add(hallList.generateHasatCell(113, 427, 9));

		standardDataPanel.add(hallList.generateEkilenCell(408, 379, 10));
		standardDataPanel.add(hallList.generateEkimCell(408, 427, 10));
		standardDataPanel.add(hallList.generateHasatCell(408, 427, 10));

		standardDataPanel.add(hallList.generateEkilenCell(703, 379, 11));
		standardDataPanel.add(hallList.generateEkimCell(703, 403, 11));
		standardDataPanel.add(hallList.generateHasatCell(703, 427, 11));

		standardDataPanel.add(hallList.generateEkilenCell(113, 492, 12));
		standardDataPanel.add(hallList.generateEkimCell(113, 516, 12));
		standardDataPanel.add(hallList.generateHasatCell(113, 540, 12));

		standardDataPanel.add(hallList.generateEkilenCell(408, 492, 13));
		standardDataPanel.add(hallList.generateEkimCell(408, 516, 13));
		standardDataPanel.add(hallList.generateHasatCell(408, 540, 13));

		standardDataPanel.add(hallList.generateEkilenCell(703, 492, 14));
		standardDataPanel.add(hallList.generateEkimCell(703, 516, 14));
		standardDataPanel.add(hallList.generateHasatCell(703, 540, 14));

		standardDataPanel.add(hallList.generateEkilenCell(113, 605, 15));
		standardDataPanel.add(hallList.generateEkimCell(113, 629, 15));
		standardDataPanel.add(hallList.generateHasatCell(113, 653, 15));

		standardDataPanel.add(hallList.generateEkilenCell(408, 605, 16));
		standardDataPanel.add(hallList.generateEkimCell(408, 629, 16));
		standardDataPanel.add(hallList.generateHasatCell(408, 653, 16));

		standardDataPanel.add(hallList.generateEkilenCell(703, 605, 17));
		standardDataPanel.add(hallList.generateEkimCell(703, 629, 17));
		standardDataPanel.add(hallList.generateHasatCell(703, 653, 17));

		standardDataPanel.add(hallList.generateEkilenCell(113, 718, 18));
		standardDataPanel.add(hallList.generateEkimCell(113, 742, 18));
		standardDataPanel.add(hallList.generateHasatCell(113, 766, 18));

		standardDataPanel.add(hallList.generateEkilenCell(408, 718, 19));
		standardDataPanel.add(hallList.generateEkimCell(408, 742, 19));
		standardDataPanel.add(hallList.generateHasatCell(408, 766, 19));

		standardDataPanel.add(hallList.generateEkilenCell(703, 718, 20));
		standardDataPanel.add(hallList.generateEkimCell(703, 742, 20));
		standardDataPanel.add(hallList.generateHasatCell(703, 766, 20));

		standardDataPanel.add(hallList.generateEkilenCell(113, 831, 21));
		standardDataPanel.add(hallList.generateEkimCell(133, 855, 21));
		standardDataPanel.add(hallList.generateHasatCell(113, 879, 21));

		standardDataPanel.add(hallList.generateEkilenCell(408, 831, 22));
		standardDataPanel.add(hallList.generateEkimCell(408, 855, 22));
		standardDataPanel.add(hallList.generateHasatCell(408, 879, 22));

		standardDataPanel.add(hallList.generateEkilenCell(703, 931, 23));
		standardDataPanel.add(hallList.generateEkimCell(703, 855, 23));
		standardDataPanel.add(hallList.generateHasatCell(703, 879, 23));

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
		// Unused implementation
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