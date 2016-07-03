package ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import utils.CreateTime;
import utils.DisplayError;
import utils.ErrorLog;
import db_process.ConnectDatabase;
import db_process.WriteDatabase;

public class CreateTableCells
{
	private CompoundBorder	compoundBorder;
	private ErrorLog		errorLog	= null;
	private DisplayError	displayError;

	private ConnectDatabase	connection;
	private CreateLabel		createLabel;

	public CreateTableCells(JPanel contentPane)
	{
		errorLog = new ErrorLog();

		try
		{
			connection = new ConnectDatabase(false);
		}
		catch (SQLException e)
		{
			errorLog.generateLog(e);
		}

		// Etiket nesnesi olustur
		createLabel = new CreateLabel();
		// Border for Label
		compoundBorder = new CompoundBorder(new LineBorder(new Color(0, 0, 0)), new EmptyBorder(10, 5, 10, 0));
		// Create Dialog Object
		displayError = new DisplayError(contentPane);
	}

	public JLabel generateBolum(int x, int y)
	{
		JLabel label = createLabel.generateLabel("Bolüm ID", false, 2, 1, 13, x, y, 104, 30);
		label.setBorder(compoundBorder);

		return label;
	}

	public JLabel generateHall(int x, int y)
	{
		JLabel label = createLabel.generateLabel("Hol ID", false, 2, 1, 13, x, y, 75, 30);
		label.setBorder(compoundBorder);

		return label;
	}

	public JLabel generateEkilen(int x, int y)
	{
		JLabel label = createLabel.generateLabel("Ekilen Bitki", false, 2, 1, 13, x, y, 104, 25);
		label.setBorder(compoundBorder);

		return label;
	}

	public JLabel generateEkim(int x, int y)
	{
		JLabel label = createLabel.generateLabel("Ekim Zamanı", false, 2, 1, 13, x, y, 104, 25);
		label.setBorder(compoundBorder);

		return label;
	}

	public JLabel generateHasat(int x, int y)
	{
		JLabel label = createLabel.generateLabel("Hasat zamanı", false, 2, 1, 13, x, y, 104, 25);
		label.setBorder(compoundBorder);

		return label;
	}

	public JLabel generateHallNum(String num, int x, int y)
	{
		JLabel label = createLabel.generateLabel(num, false, 2, 1, 13, x, y, 30, 30);
		label.setBorder(compoundBorder);

		return label;
	}

	public JLabel assignBolumId(String num, int x, int y)
	{
		JLabel label = createLabel.generateLabel(num, false, 2, 1, 13, x, y, 30, 30);
		label.setBorder(compoundBorder);

		return label;
	}

	public JButton generateButton(final int bolumID, final int hallRowID, final int userID, final int hallID, int x, int y)
	{
		JButton button = new JButton("SIL");
		button.setBorder(compoundBorder);
		button.setBounds(x, y, 50, 30);
		button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				int result = displayDialog();

				if (result == 0)
				{
					System.out.println("EVET");
					WriteDatabase deleteRecord = new WriteDatabase(connection.getMysqlConnection());
					deleteRecord.executeQuery("UPDATE hall SET bitki_id = '0', add_date = '0000-00-00' WHERE _id = '" + hallRowID + "' LIMIT 1 ");

					deleteRecord.executeQuery("INSERT INTO user_log(user_id, login_time, user_process) VALUES('" + userID + "', '"
							+ CreateTime.getCurrentTime() + "', '" + bolumID + " bolümde yer alan " + hallID + " numaralı holü sildi.')");
				}
			}
		});

		return button;
	}

	private int displayDialog()
	{
		return displayError.showConfirmDialog("Seçtiğiniz bitkiyi silmek istediğinize eminmisiniz?", "HATA", JOptionPane.YES_NO_OPTION);
	}
}