package ui;

import java.awt.Color;
import java.awt.Font;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.border.CompoundBorder;

import utils.CreateTime;
import utils.ErrorLog;

public class HallListData
{
	private CompoundBorder			compoundBorder;

	ArrayList<ArrayList<Object>>	bolumData	= new ArrayList<ArrayList<Object>>();

	private String					today;

	public HallListData(ArrayList<ArrayList<Object>> bolumData, CompoundBorder compoundBorder)
	{
		this.bolumData = bolumData;
		this.compoundBorder = compoundBorder;

		today = CreateTime.getCurrentDateOnly();
	}

	public JLabel generateEkilenCell(int x, int y, int index)
	{
		ArrayList<Object> cellData = bolumData.get(index);

		String ekilen = "";

		if (cellData.get(0) != null)
		{
			ekilen = cellData.get(0).toString();
		}

		JLabel label = new JLabel(ekilen);
		label.setFont(new Font("Tahoma", Font.BOLD, 13));
		label.setBorder(compoundBorder);
		label.setBounds(x, y, 182, 25);

		return label;
	}

	public JLabel generateEkimCell(int x, int y, int index)
	{
		ArrayList<Object> cellData = bolumData.get(index);

		String ekim = "";

		if (cellData.get(0) != null)
		{
			ekim = cellData.get(1).toString();
		}

		JLabel label = new JLabel(ekim);
		label.setFont(new Font("Tahoma", Font.BOLD, 13));
		label.setBorder(compoundBorder);
		label.setBounds(x, y, 182, 25);

		return label;
	}

	public JLabel generateHasatCell(int x, int y, int index)
	{
		ErrorLog errorLog = new ErrorLog();
		
		ArrayList<Object> cellData = bolumData.get(index);

		String hasat = "";

		if (cellData.get(0) != null)
		{
			hasat = cellData.get(2).toString();
		}

		JLabel label = new JLabel(hasat);
		label.setFont(new Font("Tahoma", Font.BOLD, 13));
		label.setBorder(compoundBorder);
		label.setBounds(x, y, 182, 25);

		if (cellData.get(0) != null)
		{
			try
			{
				if (!compareDate(hasat))
				{
					label.setForeground(Color.WHITE);
					label.setBackground(Color.RED);
					label.setOpaque(true);
				}
			}
			catch (ParseException e)
			{
				errorLog.generateLog(e);
			}
		}

		return label;
	}

	private boolean compareDate(String hasatZamani) throws ParseException
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Date dateToday = sdf.parse(today);
		Date dateHasat = sdf.parse(hasatZamani);

		if (dateToday.before(dateHasat))
			return true;

		return false;
	}
}