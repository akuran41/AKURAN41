package main_app;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
import utils.CheckHallAddingItem;
import utils.CreateTime;
import utils.DisplayError;
import utils.ErrorLog;
import utils.LoginDataDisplay;
import db_process.ConnectDatabase;
import db_process.CreateDivValues;
import db_process.ReadDatabase;
import db_process.WriteDatabase;

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
	private DisplayError		displayError;

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
	private JTextField			canSuyuBaslaSaat2;
	private JTextField			canSuyuBaslaSaat3;
	private JTextField			canSuyuBaslaSaat4;
	private JTextField			canSuyuBaslaSaat5;
	private JTextField			canSuyuBitisSaat1;
	private JTextField			canSuyuBitisSaat2;
	private JTextField			canSuyuBitisSaat3;
	private JTextField			canSuyuBitisSaat4;
	private JTextField			canSuyuBitisSaat5;
	private JTextField			canSuyuBaslaDakika1;
	private JTextField			canSuyuBaslaDakika2;
	private JTextField			canSuyuBaslaDakika3;
	private JTextField			canSuyuBaslaDakika4;
	private JTextField			canSuyuBaslaDakika5;
	private JTextField			canSuyuBitisDakika1;
	private JTextField			canSuyuBitisDakika2;
	private JTextField			canSuyuBitisDakika3;
	private JTextField			canSuyuBitisDakika4;
	private JTextField			canSuyuBitisDakika5;

	private JTextField			isikBaslaSaat1;
	private JTextField			isikBaslaSaat2;
	private JTextField			isikBaslaSaat3;
	private JTextField			isikBaslaSaat4;
	private JTextField			isikBaslaSaat5;
	private JTextField			isikBitisSaat1;
	private JTextField			isikBitisSaat2;
	private JTextField			isikBitisSaat3;
	private JTextField			isikBitisSaat4;
	private JTextField			isikBitisSaat5;
	private JTextField			isikBaslaDakika1;
	private JTextField			isikBaslaDakika2;
	private JTextField			isikBaslaDakika3;
	private JTextField			isikBaslaDakika4;
	private JTextField			isikBaslaDakika5;
	private JTextField			isikBitisDakika1;
	private JTextField			isikBitisDakika2;
	private JTextField			isikBitisDakika3;
	private JTextField			isikBitisDakika4;
	private JTextField			isikBitisDakika5;

	private JTextField			suBaslaSaat1;
	private JTextField			suBaslaSaat2;
	private JTextField			suBaslaSaat3;
	private JTextField			suBaslaSaat4;
	private JTextField			suBaslaSaat5;
	private JTextField			suBitisSaat1;
	private JTextField			suBitisSaat2;
	private JTextField			suBitisSaat3;
	private JTextField			suBitisSaat4;
	private JTextField			suBitisSaat5;
	private JTextField			suBaslaDakika1;
	private JTextField			suBaslaDakika2;
	private JTextField			suBaslaDakika3;
	private JTextField			suBaslaDakika4;
	private JTextField			suBaslaDakika5;
	private JTextField			suBitisDakika1;
	private JTextField			suBitisDakika2;
	private JTextField			suBitisDakika3;
	private JTextField			suBitisDakika4;
	private JTextField			suBitisDakika5;

	private JTextField			coBaslaSaat1;
	private JTextField			coBaslaSaat2;
	private JTextField			coBaslaSaat3;
	private JTextField			coBaslaSaat4;
	private JTextField			coBaslaSaat5;
	private JTextField			coBitisSaat1;
	private JTextField			coBitisSaat2;
	private JTextField			coBitisSaat3;
	private JTextField			coBitisSaat4;
	private JTextField			coBitisSaat5;
	private JTextField			coBaslaDakika1;
	private JTextField			coBaslaDakika2;
	private JTextField			coBaslaDakika3;
	private JTextField			coBaslaDakika4;
	private JTextField			coBaslaDakika5;
	private JTextField			oBaslaDakika5;
	private JTextField			coBitisDakika1;
	private JTextField			coBitisDakika2;
	private JTextField			coBitisDakika3;
	private JTextField			coBitisDakika4;
	private JTextField			coBitisDakika5;

	private JTextField			oBaslaSaat1;
	private JTextField			oBaslaSaat2;
	private JTextField			oBaslaSaat3;
	private JTextField			oBaslaSaat4;
	private JTextField			oBaslaSaat5;
	private JTextField			oBitisSaat1;
	private JTextField			oBitisSaat2;
	private JTextField			oBitisSaat3;
	private JTextField			oBitisSaat4;
	private JTextField			oBitisSaat5;
	private JTextField			oBaslaDakika1;
	private JTextField			oBaslaDakika2;
	private JTextField			oBaslaDakika3;
	private JTextField			oBaslaDakika4;
	private JTextField			oBitisDakika1;
	private JTextField			oBitisDakika2;
	private JTextField			oBitisDakika3;
	private JTextField			oBitisDakika4;
	private JTextField			oBitisDakika5;

	private JButton				btnDetayGoster;

	private ArrayList<Object>	firstHallValues		= new ArrayList<Object>();

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
		// Warning nesnesi olustur
		displayError = new DisplayError(contentPane);

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


		btnDetayGoster = new JButton("Detay Göster");
		btnDetayGoster.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				// Detail Pane
				if(hallList == null)
				{
					hallList = new HallList();
					hallList.setUserID(_id);
				}
				
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
					
					hallList = null;
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
				String selectedPlant = comboBox.getSelectedItem().toString();

				CheckHallAddingItem checkHallItem = new CheckHallAddingItem(firstHallValues, selectedPlant);

				if (!checkHallItem.isPlantableHall())
				{
					displayError.showMessageDialog("Eklemek istediğiniz bitki, seçtiğiniz bölüm ile uyuşmuyor. Lütfen başka bitki seçiniz.", "UYARI",
							JOptionPane.WARNING_MESSAGE);
				}
				else
				{
					int hallID = 0;

					// First we find hall ID
					ReadDatabase getHallID = new ReadDatabase(connection.getMysqlConnection());
					try
					{
						ResultSet rs = getHallID.getData("SELECT _id FROM hall WHERE bolum_id = '" + bolumID
								+ "' AND add_date = '0000-00-00' ORDER BY _id ASC LIMIT 1");
						while (rs.next())
						{
							hallID = rs.getInt(1);
						}

						// If there is no empty hall in selected DIV then display warning message
						if (hallID == 0)
						{
							displayError.showMessageDialog("Eklemek istediğiniz bölümde boş yer yok.. Lütfen başka bölüm seçiniz.", "UYARI",
									JOptionPane.WARNING_MESSAGE);
						}
						else
						{
							CreateDivValues createDiv = new CreateDivValues(connection.getMysqlConnection(), bolumID);
							
							WriteDatabase writeDatabase = new WriteDatabase(connection.getMysqlConnection());
							writeDatabase.executeQuery("UPDATE hall SET bitki_id = '" + checkHallItem.getBitkiID() + "', add_date = '" + CreateTime.getCurrentDateOnly() + "' WHERE _id = '" + hallID
									+ "'");

							writeDatabase.executeQuery("INSERT INTO user_log(user_id, login_time, user_process) VALUES('" + _id + "', '" + CreateTime.getCurrentTime() + "', '"
									+ bolumID + " numaralı bölüme " + selectedPlant + " ekti.')");
							
							String[] cansuyu = {canSuyuBaslaSaat1.getText(), canSuyuBitisSaat1.getText(), canSuyuBaslaDakika1.getText(), canSuyuBitisDakika1.getText(), 
									canSuyuBaslaSaat2.getText(), canSuyuBitisSaat2.getText(), canSuyuBaslaDakika2.getText(), canSuyuBitisDakika2.getText(), 
									canSuyuBaslaSaat3.getText(), canSuyuBitisSaat3.getText(), canSuyuBaslaDakika3.getText(), canSuyuBitisDakika3.getText(), 
									canSuyuBaslaSaat4.getText(), canSuyuBitisSaat4.getText(), canSuyuBaslaDakika4.getText(), canSuyuBitisDakika4.getText(), 
									canSuyuBaslaSaat5.getText(), canSuyuBitisSaat5.getText(), canSuyuBaslaDakika5.getText(), canSuyuBitisDakika5.getText()};
							
							String[] isik = {isikBaslaSaat1.getText(), isikBitisSaat1.getText(), isikBaslaDakika1.getText(), isikBitisDakika1.getText(), 
									isikBaslaSaat2.getText(), isikBitisSaat2.getText(), isikBaslaDakika2.getText(), isikBitisDakika2.getText(), 
									isikBaslaSaat3.getText(), isikBitisSaat3.getText(), isikBaslaDakika3.getText(), isikBitisDakika3.getText(), 
									isikBaslaSaat4.getText(), isikBitisSaat4.getText(), isikBaslaDakika4.getText(), isikBitisDakika4.getText(), 
									isikBaslaSaat5.getText(), isikBitisSaat5.getText(), isikBaslaDakika5.getText(), isikBitisDakika5.getText()};
							
							String[] su = {suBaslaSaat1.getText(), suBitisSaat1.getText(), suBaslaDakika1.getText(), suBitisDakika1.getText(), 
									suBaslaSaat2.getText(), suBitisSaat2.getText(), suBaslaDakika2.getText(), suBitisDakika2.getText(), 
									suBaslaSaat3.getText(), suBitisSaat3.getText(), suBaslaDakika3.getText(), suBitisDakika3.getText(), 
									suBaslaSaat4.getText(), suBitisSaat4.getText(), suBaslaDakika4.getText(), suBitisDakika4.getText(), 
									suBaslaSaat5.getText(), suBitisSaat5.getText(), suBaslaDakika5.getText(), suBitisDakika5.getText()};
							
							String[] co = {coBaslaSaat1.getText(), coBitisSaat1.getText(), coBaslaDakika1.getText(), coBitisDakika1.getText(), 
									coBaslaSaat2.getText(), coBitisSaat2.getText(), coBaslaDakika2.getText(), coBitisDakika2.getText(), 
									coBaslaSaat3.getText(), coBitisSaat3.getText(), coBaslaDakika3.getText(), coBitisDakika3.getText(), 
									coBaslaSaat4.getText(), coBitisSaat4.getText(), coBaslaDakika4.getText(), coBitisDakika4.getText(), 
									coBaslaSaat5.getText(), coBitisSaat5.getText(), coBaslaDakika5.getText(), coBitisDakika5.getText()};
							
							String[] o = {oBaslaSaat1.getText(), oBitisSaat1.getText(), oBaslaDakika1.getText(), oBitisDakika1.getText(), 
									oBaslaSaat2.getText(), oBitisSaat2.getText(), oBaslaDakika2.getText(), oBitisDakika2.getText(), 
									oBaslaSaat3.getText(), oBitisSaat3.getText(), oBaslaDakika3.getText(), oBitisDakika3.getText(), 
									oBaslaSaat4.getText(), oBitisSaat4.getText(), oBaslaDakika4.getText(), oBitisDakika4.getText(), 
									oBaslaSaat5.getText(), oBitisSaat5.getText(), oBaslaDakika5.getText(), oBitisDakika5.getText()};

							createDiv.createCansuyu(cansuyu);
							createDiv.createIsik(isik);
							createDiv.createSu(su);
							createDiv.createCO(co);
							createDiv.createO(o);
							
							displayError.showMessageDialog("<html>Seçtiğiniz bitki " + bolumID
									+ " numaralı bölüme eklendi.<br><br>Aşağıdaki <b>DETAY GÖSTER</b> düğmesine basarak bölüm detayını görebilirsiniz.</html>",
									"İşleminiz Tamamlandı.", JOptionPane.INFORMATION_MESSAGE);

						}
					}
					catch (SQLException e1)
					{
						errorLog.generateLog(e1);
					}
				}
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
		
		//	We initilaze default values
		firstHallValues.add(0);
		firstHallValues.add(0);
		firstHallValues.add(0);
		firstHallValues.add(0);
		firstHallValues.add(0);
		firstHallValues.add(0);
		firstHallValues.add(0);
		firstHallValues.add(0);
		firstHallValues.add(0);
		firstHallValues.add(0);
		firstHallValues.add(0);

		// Get Set values from DB
		rs = getPlants
				.getData("SELECT bitki_isik_siddet, bitki_gunduz_isik_sure, bitki_gunduz_ortam_isi, bitki_gunduz_nem, bitki_gunduz_o2, bitki_gunduz_c2o, "
						+ "bitki_gunduz_cansuyu_isi, bitki_cansuyu_ph, bitki_gunduz_gida_a, bitki_gunduz_gida_b, bitki_gunduz_gida_c " + "FROM bitki b "
						+ "LEFT JOIN hall h ON b._id = h.bitki_id " + "WHERE h.bolum_id = '" + hallID + "' " + "ORDER BY h.bolum_id ASC " + "LIMIT 1");
		while (rs.next())
		{
			//	If we have value then clear arraylist
			firstHallValues.clear();
			//	Re generate new values
			firstHallValues.add(rs.getString(3));
			firstHallValues.add(rs.getString(4));
			firstHallValues.add(rs.getString(7));
			firstHallValues.add(rs.getString(5));
			firstHallValues.add(rs.getString(6));
			firstHallValues.add(rs.getString(1));
			firstHallValues.add(rs.getString(8));
			firstHallValues.add(rs.getString(9));
			firstHallValues.add(rs.getString(10));
			firstHallValues.add(rs.getString(11));
			firstHallValues.add(rs.getString(2));

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

		// Get Values from Sensor
		rs = getPlants.getData("SELECT env_temp, env_humid, env_02, env_co2, env_light, " + "env_pressure, env_water_temp, env_water_ph, a_gida, "
				+ "b_gida, c_gida, env_heat_valve, env_cool_valve " + "FROM product_log " + "WHERE bolum_id = '" + hallID + "' " + "ORDER BY _id DESC "
				+ "LIMIT 1");
		while (rs.next())
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

		setDivCansuyu(bolumID);
		setDivIsik(bolumID);
		setDivSu(bolumID);
		setDivCO(bolumID);
		setDivO(bolumID);
	}

	private void setDivCansuyu(int divID) throws SQLException
	{
		int x = 0;
		String[] data = new String[20];

		ReadDatabase getDivVal = new ReadDatabase(connection.getMysqlConnection());
		ResultSet rs = getDivVal.getData("SELECT basla_saat, bitis_saat, basla_dakika, bitis_dakika FROM bolum_cansuyu WHERE bolum_id = '" + divID
				+ "' ORDER BY _id ASC");
		while (rs.next())
		{
			data[x] = rs.getString(1);
			x++;
			data[x] = rs.getString(2);
			x++;
			data[x] = rs.getString(3);
			x++;
			data[x] = rs.getString(4);
			x++;
		}

		// ROW 1
		canSuyuBaslaSaat1 = new JTextField(data[0] == null ? "" : data[0]);
		canSuyuBaslaSaat1.setHorizontalAlignment(SwingConstants.RIGHT);
		canSuyuBaslaSaat1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		canSuyuBaslaSaat1.setBounds(10, 359, 38, 30);
		canSuyuBaslaSaat1.setBorder(compoundBorderForInput);
		standardDataPanel.add(canSuyuBaslaSaat1);

		canSuyuBitisSaat1 = new JTextField(data[1] == null ? "" : data[1]);
		canSuyuBitisSaat1.setHorizontalAlignment(SwingConstants.RIGHT);
		canSuyuBitisSaat1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		canSuyuBitisSaat1.setBorder(compoundBorderForInput);
		canSuyuBitisSaat1.setBounds(47, 359, 38, 30);
		standardDataPanel.add(canSuyuBitisSaat1);

		canSuyuBaslaDakika1 = new JTextField(data[2] == null ? "" : data[2]);
		canSuyuBaslaDakika1.setHorizontalAlignment(SwingConstants.RIGHT);
		canSuyuBaslaDakika1.setBounds(84, 359, 38, 30);
		canSuyuBaslaDakika1.setBorder(compoundBorderForInput);
		standardDataPanel.add(canSuyuBaslaDakika1);

		canSuyuBitisDakika1 = new JTextField(data[3] == null ? "" : data[3]);
		canSuyuBitisDakika1.setHorizontalAlignment(SwingConstants.RIGHT);
		canSuyuBitisDakika1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		canSuyuBitisDakika1.setBorder(compoundBorderForInput);
		canSuyuBitisDakika1.setBounds(121, 359, 38, 30);
		standardDataPanel.add(canSuyuBitisDakika1);

		// ROW 2
		canSuyuBaslaSaat2 = new JTextField(data[4] == null ? "" : data[4]);
		canSuyuBaslaSaat2.setHorizontalAlignment(SwingConstants.RIGHT);
		canSuyuBaslaSaat2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		canSuyuBaslaSaat2.setBorder(compoundBorderForInput);
		canSuyuBaslaSaat2.setBounds(10, 388, 38, 30);
		standardDataPanel.add(canSuyuBaslaSaat2);

		canSuyuBitisSaat2 = new JTextField(data[5] == null ? "" : data[5]);
		canSuyuBitisSaat2.setHorizontalAlignment(SwingConstants.RIGHT);
		canSuyuBitisSaat2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		canSuyuBitisSaat2.setBorder(compoundBorderForInput);
		canSuyuBitisSaat2.setBounds(47, 388, 38, 30);
		standardDataPanel.add(canSuyuBitisSaat2);

		canSuyuBaslaDakika2 = new JTextField(data[6] == null ? "" : data[6]);
		canSuyuBaslaDakika2.setHorizontalAlignment(SwingConstants.RIGHT);
		canSuyuBaslaDakika2.setBorder(compoundBorderForInput);
		canSuyuBaslaDakika2.setBounds(84, 388, 38, 30);
		standardDataPanel.add(canSuyuBaslaDakika2);

		canSuyuBitisDakika2 = new JTextField(data[7] == null ? "" : data[7]);
		canSuyuBitisDakika2.setHorizontalAlignment(SwingConstants.RIGHT);
		canSuyuBitisDakika2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		canSuyuBitisDakika2.setBorder(compoundBorderForInput);
		canSuyuBitisDakika2.setBounds(121, 388, 38, 30);
		standardDataPanel.add(canSuyuBitisDakika2);

		// ROW 3
		canSuyuBaslaSaat3 = new JTextField(data[8] == null ? "" : data[8]);
		canSuyuBaslaSaat3.setHorizontalAlignment(SwingConstants.RIGHT);
		canSuyuBaslaSaat3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		canSuyuBaslaSaat3.setBorder(compoundBorderForInput);
		canSuyuBaslaSaat3.setBounds(10, 417, 38, 30);
		standardDataPanel.add(canSuyuBaslaSaat3);

		canSuyuBitisSaat3 = new JTextField(data[9] == null ? "" : data[9]);
		canSuyuBitisSaat3.setHorizontalAlignment(SwingConstants.RIGHT);
		canSuyuBitisSaat3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		canSuyuBitisSaat3.setBorder(compoundBorderForInput);
		canSuyuBitisSaat3.setBounds(47, 417, 38, 30);
		standardDataPanel.add(canSuyuBitisSaat3);

		canSuyuBaslaDakika3 = new JTextField(data[10] == null ? "" : data[10]);
		canSuyuBaslaDakika3.setHorizontalAlignment(SwingConstants.RIGHT);
		canSuyuBaslaDakika3.setBorder(compoundBorderForInput);
		canSuyuBaslaDakika3.setBounds(84, 417, 38, 30);
		standardDataPanel.add(canSuyuBaslaDakika3);

		canSuyuBitisDakika3 = new JTextField(data[11] == null ? "" : data[11]);
		canSuyuBitisDakika3.setHorizontalAlignment(SwingConstants.RIGHT);
		canSuyuBitisDakika3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		canSuyuBitisDakika3.setBorder(compoundBorderForInput);
		canSuyuBitisDakika3.setBounds(121, 417, 38, 30);
		standardDataPanel.add(canSuyuBitisDakika3);

		// ROW 4
		canSuyuBaslaSaat4 = new JTextField(data[12] == null ? "" : data[12]);
		canSuyuBaslaSaat4.setHorizontalAlignment(SwingConstants.RIGHT);
		canSuyuBaslaSaat4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		canSuyuBaslaSaat4.setBorder(compoundBorderForInput);
		canSuyuBaslaSaat4.setBounds(10, 446, 38, 30);
		standardDataPanel.add(canSuyuBaslaSaat4);

		canSuyuBitisSaat4 = new JTextField(data[13] == null ? "" : data[13]);
		canSuyuBitisSaat4.setHorizontalAlignment(SwingConstants.RIGHT);
		canSuyuBitisSaat4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		canSuyuBitisSaat4.setBorder(compoundBorderForInput);
		canSuyuBitisSaat4.setBounds(47, 446, 38, 30);
		standardDataPanel.add(canSuyuBitisSaat4);

		canSuyuBaslaDakika4 = new JTextField(data[14] == null ? "" : data[14]);
		canSuyuBaslaDakika4.setHorizontalAlignment(SwingConstants.RIGHT);
		canSuyuBaslaDakika4.setBorder(compoundBorderForInput);
		canSuyuBaslaDakika4.setBounds(84, 446, 38, 30);
		standardDataPanel.add(canSuyuBaslaDakika4);

		canSuyuBitisDakika4 = new JTextField(data[15] == null ? "" : data[15]);
		canSuyuBitisDakika4.setHorizontalAlignment(SwingConstants.RIGHT);
		canSuyuBitisDakika4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		canSuyuBitisDakika4.setBorder(compoundBorderForInput);
		canSuyuBitisDakika4.setBounds(121, 446, 38, 30);
		standardDataPanel.add(canSuyuBitisDakika4);

		// ROW 5
		canSuyuBaslaSaat5 = new JTextField(data[16] == null ? "" : data[16]);
		canSuyuBaslaSaat5.setHorizontalAlignment(SwingConstants.RIGHT);
		canSuyuBaslaSaat5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		canSuyuBaslaSaat5.setBorder(compoundBorderForInput);
		canSuyuBaslaSaat5.setBounds(10, 475, 38, 30);
		standardDataPanel.add(canSuyuBaslaSaat5);

		canSuyuBitisSaat5 = new JTextField(data[17] == null ? "" : data[17]);
		canSuyuBitisSaat5.setHorizontalAlignment(SwingConstants.RIGHT);
		canSuyuBitisSaat5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		canSuyuBitisSaat5.setBorder(compoundBorderForInput);
		canSuyuBitisSaat5.setBounds(47, 475, 38, 30);
		standardDataPanel.add(canSuyuBitisSaat5);

		canSuyuBaslaDakika5 = new JTextField(data[18] == null ? "" : data[18]);
		canSuyuBaslaDakika5.setHorizontalAlignment(SwingConstants.RIGHT);
		canSuyuBaslaDakika5.setBorder(compoundBorderForInput);
		canSuyuBaslaDakika5.setBounds(84, 475, 38, 30);
		standardDataPanel.add(canSuyuBaslaDakika5);

		canSuyuBitisDakika5 = new JTextField(data[19] == null ? "" : data[19]);
		canSuyuBitisDakika5.setHorizontalAlignment(SwingConstants.RIGHT);
		canSuyuBitisDakika5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		canSuyuBitisDakika5.setBorder(compoundBorderForInput);
		canSuyuBitisDakika5.setBounds(121, 475, 38, 30);
		standardDataPanel.add(canSuyuBitisDakika5);
	}

	private void setDivIsik(int divID) throws SQLException
	{
		int x = 0;
		String[] data = new String[20];

		ReadDatabase getDivVal = new ReadDatabase(connection.getMysqlConnection());
		ResultSet rs = getDivVal.getData("SELECT basla_saat, bitis_saat, basla_dakika, bitis_dakika FROM bolum_isik WHERE bolum_id = '" + divID
				+ "' ORDER BY _id ASC");
		while (rs.next())
		{
			data[x] = rs.getString(1);
			x++;
			data[x] = rs.getString(2);
			x++;
			data[x] = rs.getString(3);
			x++;
			data[x] = rs.getString(4);
			x++;
		}

		// ROW 1
		isikBaslaSaat1 = new JTextField(data[0] == null ? "" : data[0]);
		isikBaslaSaat1.setHorizontalAlignment(SwingConstants.RIGHT);
		isikBaslaSaat1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		isikBaslaSaat1.setBorder(compoundBorderForInput);
		isikBaslaSaat1.setBounds(170, 359, 38, 30);
		standardDataPanel.add(isikBaslaSaat1);

		isikBitisSaat1 = new JTextField(data[1] == null ? "" : data[1]);
		isikBitisSaat1.setHorizontalAlignment(SwingConstants.RIGHT);
		isikBitisSaat1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		isikBitisSaat1.setBorder(compoundBorderForInput);
		isikBitisSaat1.setBounds(207, 359, 38, 30);
		standardDataPanel.add(isikBitisSaat1);

		isikBaslaDakika1 = new JTextField(data[2] == null ? "" : data[2]);
		isikBaslaDakika1.setHorizontalAlignment(SwingConstants.RIGHT);
		isikBaslaDakika1.setBorder(compoundBorderForInput);
		isikBaslaDakika1.setBounds(244, 359, 38, 30);
		standardDataPanel.add(isikBaslaDakika1);

		isikBitisDakika1 = new JTextField(data[3] == null ? "" : data[3]);
		isikBitisDakika1.setHorizontalAlignment(SwingConstants.RIGHT);
		isikBitisDakika1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		isikBitisDakika1.setBorder(compoundBorderForInput);
		isikBitisDakika1.setBounds(281, 359, 38, 30);
		standardDataPanel.add(isikBitisDakika1);

		// ROW 2
		isikBaslaSaat2 = new JTextField(data[4] == null ? "" : data[4]);
		isikBaslaSaat2.setHorizontalAlignment(SwingConstants.RIGHT);
		isikBaslaSaat2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		isikBaslaSaat2.setBorder(compoundBorderForInput);
		isikBaslaSaat2.setBounds(170, 388, 38, 30);
		standardDataPanel.add(isikBaslaSaat2);

		isikBitisSaat2 = new JTextField(data[5] == null ? "" : data[5]);
		isikBitisSaat2.setHorizontalAlignment(SwingConstants.RIGHT);
		isikBitisSaat2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		isikBitisSaat2.setBorder(compoundBorderForInput);
		isikBitisSaat2.setBounds(207, 388, 38, 30);
		standardDataPanel.add(isikBitisSaat2);

		isikBaslaDakika2 = new JTextField(data[6] == null ? "" : data[6]);
		isikBaslaDakika2.setHorizontalAlignment(SwingConstants.RIGHT);
		isikBaslaDakika2.setBorder(compoundBorderForInput);
		isikBaslaDakika2.setBounds(244, 388, 38, 30);
		standardDataPanel.add(isikBaslaDakika2);

		isikBitisDakika2 = new JTextField(data[7] == null ? "" : data[7]);
		isikBitisDakika2.setHorizontalAlignment(SwingConstants.RIGHT);
		isikBitisDakika2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		isikBitisDakika2.setBorder(compoundBorderForInput);
		isikBitisDakika2.setBounds(281, 388, 38, 30);
		standardDataPanel.add(isikBitisDakika2);

		// ROW 3
		isikBaslaSaat3 = new JTextField(data[8] == null ? "" : data[8]);
		isikBaslaSaat3.setHorizontalAlignment(SwingConstants.RIGHT);
		isikBaslaSaat3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		isikBaslaSaat3.setBorder(compoundBorderForInput);
		isikBaslaSaat3.setBounds(170, 417, 38, 30);
		standardDataPanel.add(isikBaslaSaat3);

		isikBitisSaat3 = new JTextField(data[9] == null ? "" : data[9]);
		isikBitisSaat3.setHorizontalAlignment(SwingConstants.RIGHT);
		isikBitisSaat3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		isikBitisSaat3.setBorder(compoundBorderForInput);
		isikBitisSaat3.setBounds(207, 417, 38, 30);
		standardDataPanel.add(isikBitisSaat3);

		isikBaslaDakika3 = new JTextField(data[10] == null ? "" : data[10]);
		isikBaslaDakika3.setHorizontalAlignment(SwingConstants.RIGHT);
		isikBaslaDakika3.setBorder(compoundBorderForInput);
		isikBaslaDakika3.setBounds(244, 417, 38, 30);
		standardDataPanel.add(isikBaslaDakika3);

		isikBitisDakika3 = new JTextField(data[11] == null ? "" : data[11]);
		isikBitisDakika3.setHorizontalAlignment(SwingConstants.RIGHT);
		isikBitisDakika3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		isikBitisDakika3.setBorder(compoundBorderForInput);
		isikBitisDakika3.setBounds(281, 417, 38, 30);
		standardDataPanel.add(isikBitisDakika3);

		// ROW 4
		isikBaslaSaat4 = new JTextField(data[12] == null ? "" : data[12]);
		isikBaslaSaat4.setHorizontalAlignment(SwingConstants.RIGHT);
		isikBaslaSaat4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		isikBaslaSaat4.setBorder(compoundBorderForInput);
		isikBaslaSaat4.setBounds(170, 446, 38, 30);
		standardDataPanel.add(isikBaslaSaat4);

		isikBitisSaat4 = new JTextField(data[13] == null ? "" : data[13]);
		isikBitisSaat4.setHorizontalAlignment(SwingConstants.RIGHT);
		isikBitisSaat4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		isikBitisSaat4.setBorder(compoundBorderForInput);
		isikBitisSaat4.setBounds(207, 446, 38, 30);
		standardDataPanel.add(isikBitisSaat4);

		isikBaslaDakika4 = new JTextField(data[14] == null ? "" : data[14]);
		isikBaslaDakika4.setHorizontalAlignment(SwingConstants.RIGHT);
		isikBaslaDakika4.setBorder(compoundBorderForInput);
		isikBaslaDakika4.setBounds(244, 446, 38, 30);
		standardDataPanel.add(isikBaslaDakika4);

		isikBitisDakika4 = new JTextField(data[15] == null ? "" : data[15]);
		isikBitisDakika4.setHorizontalAlignment(SwingConstants.RIGHT);
		isikBitisDakika4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		isikBitisDakika4.setBorder(compoundBorderForInput);
		isikBitisDakika4.setBounds(281, 446, 38, 30);
		standardDataPanel.add(isikBitisDakika4);

		// ROW 5
		isikBaslaSaat5 = new JTextField(data[16] == null ? "" : data[16]);
		isikBaslaSaat5.setHorizontalAlignment(SwingConstants.RIGHT);
		isikBaslaSaat5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		isikBaslaSaat5.setBorder(compoundBorderForInput);
		isikBaslaSaat5.setBounds(170, 475, 38, 30);
		standardDataPanel.add(isikBaslaSaat5);

		isikBitisSaat5 = new JTextField(data[17] == null ? "" : data[17]);
		isikBitisSaat5.setHorizontalAlignment(SwingConstants.RIGHT);
		isikBitisSaat5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		isikBitisSaat5.setBorder(compoundBorderForInput);
		isikBitisSaat5.setBounds(207, 475, 38, 30);
		standardDataPanel.add(isikBitisSaat5);

		isikBaslaDakika5 = new JTextField(data[18] == null ? "" : data[18]);
		isikBaslaDakika5.setHorizontalAlignment(SwingConstants.RIGHT);
		isikBaslaDakika5.setBorder(compoundBorderForInput);
		isikBaslaDakika5.setBounds(244, 475, 38, 30);
		standardDataPanel.add(isikBaslaDakika5);

		isikBitisDakika5 = new JTextField(data[19] == null ? "" : data[19]);
		isikBitisDakika5.setHorizontalAlignment(SwingConstants.RIGHT);
		isikBitisDakika5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		isikBitisDakika5.setBorder(compoundBorderForInput);
		isikBitisDakika5.setBounds(281, 475, 38, 30);
		standardDataPanel.add(isikBitisDakika5);
	}

	private void setDivSu(int divID) throws SQLException
	{
		int x = 0;
		String[] data = new String[20];

		ReadDatabase getDivVal = new ReadDatabase(connection.getMysqlConnection());
		ResultSet rs = getDivVal.getData("SELECT basla_saat, bitis_saat, basla_dakika, bitis_dakika FROM bolum_su WHERE bolum_id = '" + divID
				+ "' ORDER BY _id ASC");
		while (rs.next())
		{
			data[x] = rs.getString(1);
			x++;
			data[x] = rs.getString(2);
			x++;
			data[x] = rs.getString(3);
			x++;
			data[x] = rs.getString(4);
			x++;
		}

		// ROW 1
		suBaslaSaat1 = new JTextField(data[0] == null ? "" : data[0]);
		suBaslaSaat1.setHorizontalAlignment(SwingConstants.RIGHT);
		suBaslaSaat1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		suBaslaSaat1.setBorder(compoundBorderForInput);
		suBaslaSaat1.setBounds(330, 359, 38, 30);
		standardDataPanel.add(suBaslaSaat1);

		suBitisSaat1 = new JTextField(data[1] == null ? "" : data[1]);
		suBitisSaat1.setHorizontalAlignment(SwingConstants.RIGHT);
		suBitisSaat1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		suBitisSaat1.setBorder(compoundBorderForInput);
		suBitisSaat1.setBounds(367, 359, 38, 30);
		standardDataPanel.add(suBitisSaat1);

		suBaslaDakika1 = new JTextField(data[2] == null ? "" : data[2]);
		suBaslaDakika1.setHorizontalAlignment(SwingConstants.RIGHT);
		suBaslaDakika1.setBorder(compoundBorderForInput);
		suBaslaDakika1.setBounds(404, 359, 38, 30);
		standardDataPanel.add(suBaslaDakika1);

		suBitisDakika1 = new JTextField(data[3] == null ? "" : data[3]);
		suBitisDakika1.setHorizontalAlignment(SwingConstants.RIGHT);
		suBitisDakika1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		suBitisDakika1.setBorder(compoundBorderForInput);
		suBitisDakika1.setBounds(441, 359, 38, 30);
		standardDataPanel.add(suBitisDakika1);

		// ROW 2
		suBaslaSaat2 = new JTextField(data[4] == null ? "" : data[4]);
		suBaslaSaat2.setHorizontalAlignment(SwingConstants.RIGHT);
		suBaslaSaat2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		suBaslaSaat2.setBorder(compoundBorderForInput);
		suBaslaSaat2.setBounds(330, 388, 38, 30);
		standardDataPanel.add(suBaslaSaat2);

		suBitisSaat2 = new JTextField(data[5] == null ? "" : data[5]);
		suBitisSaat2.setHorizontalAlignment(SwingConstants.RIGHT);
		suBitisSaat2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		suBitisSaat2.setBorder(compoundBorderForInput);
		suBitisSaat2.setBounds(367, 388, 38, 30);
		standardDataPanel.add(suBitisSaat2);

		suBaslaDakika2 = new JTextField(data[6] == null ? "" : data[6]);
		suBaslaDakika2.setHorizontalAlignment(SwingConstants.RIGHT);
		suBaslaDakika2.setBorder(compoundBorderForInput);
		suBaslaDakika2.setBounds(404, 388, 38, 30);
		standardDataPanel.add(suBaslaDakika2);

		suBitisDakika2 = new JTextField(data[7] == null ? "" : data[7]);
		suBitisDakika2.setHorizontalAlignment(SwingConstants.RIGHT);
		suBitisDakika2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		suBitisDakika2.setBorder(compoundBorderForInput);
		suBitisDakika2.setBounds(441, 388, 38, 30);
		standardDataPanel.add(suBitisDakika2);

		// ROW 3
		suBaslaSaat3 = new JTextField(data[8] == null ? "" : data[8]);
		suBaslaSaat3.setHorizontalAlignment(SwingConstants.RIGHT);
		suBaslaSaat3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		suBaslaSaat3.setBorder(compoundBorderForInput);
		suBaslaSaat3.setBounds(330, 417, 38, 30);
		standardDataPanel.add(suBaslaSaat3);

		suBitisSaat3 = new JTextField(data[9] == null ? "" : data[9]);
		suBitisSaat3.setHorizontalAlignment(SwingConstants.RIGHT);
		suBitisSaat3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		suBitisSaat3.setBorder(compoundBorderForInput);
		suBitisSaat3.setBounds(367, 417, 38, 30);
		standardDataPanel.add(suBitisSaat3);

		suBaslaDakika3 = new JTextField(data[10] == null ? "" : data[10]);
		suBaslaDakika3.setHorizontalAlignment(SwingConstants.RIGHT);
		suBaslaDakika3.setBorder(compoundBorderForInput);
		suBaslaDakika3.setBounds(404, 417, 38, 30);
		standardDataPanel.add(suBaslaDakika3);

		suBitisDakika3 = new JTextField(data[11] == null ? "" : data[11]);
		suBitisDakika3.setHorizontalAlignment(SwingConstants.RIGHT);
		suBitisDakika3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		suBitisDakika3.setBorder(compoundBorderForInput);
		suBitisDakika3.setBounds(441, 417, 38, 30);
		standardDataPanel.add(suBitisDakika3);

		// ROW 4
		suBaslaSaat4 = new JTextField(data[12] == null ? "" : data[12]);
		suBaslaSaat4.setHorizontalAlignment(SwingConstants.RIGHT);
		suBaslaSaat4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		suBaslaSaat4.setBorder(compoundBorderForInput);
		suBaslaSaat4.setBounds(330, 446, 38, 30);
		standardDataPanel.add(suBaslaSaat4);

		suBitisSaat4 = new JTextField(data[13] == null ? "" : data[13]);
		suBitisSaat4.setHorizontalAlignment(SwingConstants.RIGHT);
		suBitisSaat4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		suBitisSaat4.setBorder(compoundBorderForInput);
		suBitisSaat4.setBounds(367, 446, 38, 30);
		standardDataPanel.add(suBitisSaat4);

		suBaslaDakika4 = new JTextField(data[14] == null ? "" : data[14]);
		suBaslaDakika4.setHorizontalAlignment(SwingConstants.RIGHT);
		suBaslaDakika4.setBorder(compoundBorderForInput);
		suBaslaDakika4.setBounds(404, 446, 38, 30);
		standardDataPanel.add(suBaslaDakika4);

		suBitisDakika4 = new JTextField(data[15] == null ? "" : data[15]);
		suBitisDakika4.setHorizontalAlignment(SwingConstants.RIGHT);
		suBitisDakika4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		suBitisDakika4.setBorder(compoundBorderForInput);
		suBitisDakika4.setBounds(441, 446, 38, 30);
		standardDataPanel.add(suBitisDakika4);

		// ROW 5
		suBaslaSaat5 = new JTextField(data[16] == null ? "" : data[16]);
		suBaslaSaat5.setHorizontalAlignment(SwingConstants.RIGHT);
		suBaslaSaat5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		suBaslaSaat5.setBorder(compoundBorderForInput);
		suBaslaSaat5.setBounds(330, 475, 38, 30);
		standardDataPanel.add(suBaslaSaat5);

		suBitisSaat5 = new JTextField(data[17] == null ? "" : data[17]);
		suBitisSaat5.setHorizontalAlignment(SwingConstants.RIGHT);
		suBitisSaat5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		suBitisSaat5.setBorder(compoundBorderForInput);
		suBitisSaat5.setBounds(367, 475, 38, 30);
		standardDataPanel.add(suBitisSaat5);

		suBaslaDakika5 = new JTextField(data[18] == null ? "" : data[18]);
		suBaslaDakika5.setHorizontalAlignment(SwingConstants.RIGHT);
		suBaslaDakika5.setBorder(compoundBorderForInput);
		suBaslaDakika5.setBounds(404, 475, 38, 30);
		standardDataPanel.add(suBaslaDakika5);

		suBitisDakika5 = new JTextField(data[19] == null ? "" : data[19]);
		suBitisDakika5.setHorizontalAlignment(SwingConstants.RIGHT);
		suBitisDakika5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		suBitisDakika5.setBorder(compoundBorderForInput);
		suBitisDakika5.setBounds(441, 475, 38, 30);
		standardDataPanel.add(suBitisDakika5);
	}

	private void setDivCO(int divID) throws SQLException
	{
		int x = 0;
		String[] data = new String[20];

		ReadDatabase getDivVal = new ReadDatabase(connection.getMysqlConnection());
		ResultSet rs = getDivVal.getData("SELECT basla_saat, bitis_saat, basla_dakika, bitis_dakika FROM bolum_co WHERE bolum_id = '" + divID
				+ "' ORDER BY _id ASC");
		while (rs.next())
		{
			data[x] = rs.getString(1);
			x++;
			data[x] = rs.getString(2);
			x++;
			data[x] = rs.getString(3);
			x++;
			data[x] = rs.getString(4);
			x++;
		}

		// ROW 1
		coBaslaSaat1 = new JTextField(data[0] == null ? "" : data[0]);
		coBaslaSaat1.setHorizontalAlignment(SwingConstants.RIGHT);
		coBaslaSaat1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		coBaslaSaat1.setBorder(compoundBorderForInput);
		coBaslaSaat1.setBounds(490, 359, 38, 30);
		standardDataPanel.add(coBaslaSaat1);

		coBitisSaat1 = new JTextField(data[1] == null ? "" : data[1]);
		coBitisSaat1.setHorizontalAlignment(SwingConstants.RIGHT);
		coBitisSaat1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		coBitisSaat1.setBorder(compoundBorderForInput);
		coBitisSaat1.setBounds(527, 359, 38, 30);
		standardDataPanel.add(coBitisSaat1);

		coBaslaDakika1 = new JTextField(data[2] == null ? "" : data[2]);
		coBaslaDakika1.setHorizontalAlignment(SwingConstants.RIGHT);
		coBaslaDakika1.setBorder(compoundBorderForInput);
		coBaslaDakika1.setBounds(564, 359, 38, 30);
		standardDataPanel.add(coBaslaDakika1);

		coBitisDakika1 = new JTextField(data[3] == null ? "" : data[3]);
		coBitisDakika1.setHorizontalAlignment(SwingConstants.RIGHT);
		coBitisDakika1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		coBitisDakika1.setBorder(compoundBorderForInput);
		coBitisDakika1.setBounds(601, 359, 38, 30);
		standardDataPanel.add(coBitisDakika1);

		// ROW 2
		coBaslaSaat2 = new JTextField(data[4] == null ? "" : data[4]);
		coBaslaSaat2.setHorizontalAlignment(SwingConstants.RIGHT);
		coBaslaSaat2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		coBaslaSaat2.setBorder(compoundBorderForInput);
		coBaslaSaat2.setBounds(490, 388, 38, 30);
		standardDataPanel.add(coBaslaSaat2);

		coBitisSaat2 = new JTextField(data[5] == null ? "" : data[5]);
		coBitisSaat2.setHorizontalAlignment(SwingConstants.RIGHT);
		coBitisSaat2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		coBitisSaat2.setBorder(compoundBorderForInput);
		coBitisSaat2.setBounds(527, 388, 38, 30);
		standardDataPanel.add(coBitisSaat2);

		coBaslaDakika2 = new JTextField(data[6] == null ? "" : data[6]);
		coBaslaDakika2.setHorizontalAlignment(SwingConstants.RIGHT);
		coBaslaDakika2.setBorder(compoundBorderForInput);
		coBaslaDakika2.setBounds(564, 388, 38, 30);
		standardDataPanel.add(coBaslaDakika2);

		coBitisDakika2 = new JTextField(data[7] == null ? "" : data[7]);
		coBitisDakika2.setHorizontalAlignment(SwingConstants.RIGHT);
		coBitisDakika2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		coBitisDakika2.setBorder(compoundBorderForInput);
		coBitisDakika2.setBounds(601, 388, 38, 30);
		standardDataPanel.add(coBitisDakika2);

		// ROW 3
		coBaslaSaat3 = new JTextField(data[8] == null ? "" : data[8]);
		coBaslaSaat3.setHorizontalAlignment(SwingConstants.RIGHT);
		coBaslaSaat3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		coBaslaSaat3.setBorder(compoundBorderForInput);
		coBaslaSaat3.setBounds(490, 417, 38, 30);
		standardDataPanel.add(coBaslaSaat3);

		coBitisSaat3 = new JTextField(data[9] == null ? "" : data[9]);
		coBitisSaat3.setHorizontalAlignment(SwingConstants.RIGHT);
		coBitisSaat3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		coBitisSaat3.setBorder(compoundBorderForInput);
		coBitisSaat3.setBounds(527, 417, 38, 30);
		standardDataPanel.add(coBitisSaat3);

		coBaslaDakika3 = new JTextField(data[10] == null ? "" : data[10]);
		coBaslaDakika3.setHorizontalAlignment(SwingConstants.RIGHT);
		coBaslaDakika3.setBorder(compoundBorderForInput);
		coBaslaDakika3.setBounds(564, 417, 38, 30);
		standardDataPanel.add(coBaslaDakika3);

		coBitisDakika3 = new JTextField(data[11] == null ? "" : data[11]);
		coBitisDakika3.setHorizontalAlignment(SwingConstants.RIGHT);
		coBitisDakika3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		coBitisDakika3.setBorder(compoundBorderForInput);
		coBitisDakika3.setBounds(601, 417, 38, 30);
		standardDataPanel.add(coBitisDakika3);

		// ROW 4
		coBaslaSaat4 = new JTextField(data[12] == null ? "" : data[12]);
		coBaslaSaat4.setHorizontalAlignment(SwingConstants.RIGHT);
		coBaslaSaat4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		coBaslaSaat4.setBorder(compoundBorderForInput);
		coBaslaSaat4.setBounds(490, 446, 38, 30);
		standardDataPanel.add(coBaslaSaat4);

		coBitisSaat4 = new JTextField(data[13] == null ? "" : data[13]);
		coBitisSaat4.setHorizontalAlignment(SwingConstants.RIGHT);
		coBitisSaat4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		coBitisSaat4.setBorder(compoundBorderForInput);
		coBitisSaat4.setBounds(527, 446, 38, 30);
		standardDataPanel.add(coBitisSaat4);

		coBaslaDakika4 = new JTextField(data[14] == null ? "" : data[14]);
		coBaslaDakika4.setHorizontalAlignment(SwingConstants.RIGHT);
		coBaslaDakika4.setBorder(compoundBorderForInput);
		coBaslaDakika4.setBounds(564, 446, 38, 30);
		standardDataPanel.add(coBaslaDakika4);

		coBitisDakika4 = new JTextField(data[15] == null ? "" : data[15]);
		coBitisDakika4.setHorizontalAlignment(SwingConstants.RIGHT);
		coBitisDakika4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		coBitisDakika4.setBorder(compoundBorderForInput);
		coBitisDakika4.setBounds(601, 446, 38, 30);
		standardDataPanel.add(coBitisDakika4);

		// ROW 5
		coBaslaSaat5 = new JTextField(data[16] == null ? "" : data[16]);
		coBaslaSaat5.setHorizontalAlignment(SwingConstants.RIGHT);
		coBaslaSaat5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		coBaslaSaat5.setBorder(compoundBorderForInput);
		coBaslaSaat5.setBounds(490, 475, 38, 30);
		standardDataPanel.add(coBaslaSaat5);

		coBitisSaat5 = new JTextField(data[17] == null ? "" : data[17]);
		coBitisSaat5.setHorizontalAlignment(SwingConstants.RIGHT);
		coBitisSaat5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		coBitisSaat5.setBorder(compoundBorderForInput);
		coBitisSaat5.setBounds(527, 475, 38, 30);
		standardDataPanel.add(coBitisSaat5);

		coBaslaDakika5 = new JTextField(data[18] == null ? "" : data[18]);
		coBaslaDakika5.setHorizontalAlignment(SwingConstants.RIGHT);
		coBaslaDakika5.setBorder(compoundBorderForInput);
		coBaslaDakika5.setBounds(564, 475, 38, 30);
		standardDataPanel.add(coBaslaDakika5);

		coBitisDakika5 = new JTextField(data[19] == null ? "" : data[19]);
		coBitisDakika5.setHorizontalAlignment(SwingConstants.RIGHT);
		coBitisDakika5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		coBitisDakika5.setBorder(compoundBorderForInput);
		coBitisDakika5.setBounds(601, 475, 38, 30);
		standardDataPanel.add(coBitisDakika5);
	}

	private void setDivO(int divID) throws SQLException
	{
		int x = 0;
		String[] data = new String[20];

		ReadDatabase getDivVal = new ReadDatabase(connection.getMysqlConnection());
		ResultSet rs = getDivVal.getData("SELECT basla_saat, bitis_saat, basla_dakika, bitis_dakika FROM bolum_o WHERE bolum_id = '" + divID
				+ "' ORDER BY _id ASC");
		while (rs.next())
		{
			data[x] = rs.getString(1);
			x++;
			data[x] = rs.getString(2);
			x++;
			data[x] = rs.getString(3);
			x++;
			data[x] = rs.getString(4);
			x++;
		}

		// ROW 1
		oBaslaSaat1 = new JTextField(data[0] == null ? "" : data[0]);
		oBaslaSaat1.setHorizontalAlignment(SwingConstants.RIGHT);
		oBaslaSaat1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		oBaslaSaat1.setBorder(compoundBorderForInput);
		oBaslaSaat1.setBounds(653, 359, 38, 30);
		standardDataPanel.add(oBaslaSaat1);

		oBitisSaat1 = new JTextField(data[1] == null ? "" : data[1]);
		oBitisSaat1.setHorizontalAlignment(SwingConstants.RIGHT);
		oBitisSaat1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		oBitisSaat1.setBorder(compoundBorderForInput);
		oBitisSaat1.setBounds(690, 359, 38, 30);
		standardDataPanel.add(oBitisSaat1);

		oBaslaDakika1 = new JTextField(data[2] == null ? "" : data[2]);
		oBaslaDakika1.setHorizontalAlignment(SwingConstants.RIGHT);
		oBaslaDakika1.setBorder(compoundBorderForInput);
		oBaslaDakika1.setBounds(727, 359, 38, 30);
		standardDataPanel.add(oBaslaDakika1);

		oBitisDakika1 = new JTextField(data[3] == null ? "" : data[3]);
		oBitisDakika1.setHorizontalAlignment(SwingConstants.RIGHT);
		oBitisDakika1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		oBitisDakika1.setBorder(compoundBorderForInput);
		oBitisDakika1.setBounds(764, 359, 38, 30);
		standardDataPanel.add(oBitisDakika1);

		// ROW 2
		oBaslaSaat2 = new JTextField(data[4] == null ? "" : data[4]);
		oBaslaSaat2.setHorizontalAlignment(SwingConstants.RIGHT);
		oBaslaSaat2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		oBaslaSaat2.setBorder(compoundBorderForInput);
		oBaslaSaat2.setBounds(653, 388, 38, 30);
		standardDataPanel.add(oBaslaSaat2);

		oBitisSaat2 = new JTextField(data[5] == null ? "" : data[5]);
		oBitisSaat2.setHorizontalAlignment(SwingConstants.RIGHT);
		oBitisSaat2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		oBitisSaat2.setBorder(compoundBorderForInput);
		oBitisSaat2.setBounds(690, 388, 38, 30);
		standardDataPanel.add(oBitisSaat2);

		oBaslaDakika2 = new JTextField(data[6] == null ? "" : data[6]);
		oBaslaDakika2.setHorizontalAlignment(SwingConstants.RIGHT);
		oBaslaDakika2.setBorder(compoundBorderForInput);
		oBaslaDakika2.setBounds(727, 388, 38, 30);
		standardDataPanel.add(oBaslaDakika2);

		oBitisDakika2 = new JTextField(data[7] == null ? "" : data[7]);
		oBitisDakika2.setHorizontalAlignment(SwingConstants.RIGHT);
		oBitisDakika2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		oBitisDakika2.setBorder(compoundBorderForInput);
		oBitisDakika2.setBounds(764, 388, 38, 30);
		standardDataPanel.add(oBitisDakika2);

		// ROW 3
		oBaslaSaat3 = new JTextField(data[8] == null ? "" : data[8]);
		oBaslaSaat3.setHorizontalAlignment(SwingConstants.RIGHT);
		oBaslaSaat3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		oBaslaSaat3.setBorder(compoundBorderForInput);
		oBaslaSaat3.setBounds(653, 417, 38, 30);
		standardDataPanel.add(oBaslaSaat3);

		oBitisSaat3 = new JTextField(data[9] == null ? "" : data[9]);
		oBitisSaat3.setHorizontalAlignment(SwingConstants.RIGHT);
		oBitisSaat3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		oBitisSaat3.setBorder(compoundBorderForInput);
		oBitisSaat3.setBounds(690, 417, 38, 30);
		standardDataPanel.add(oBitisSaat3);

		oBaslaDakika3 = new JTextField(data[10] == null ? "" : data[10]);
		oBaslaDakika3.setHorizontalAlignment(SwingConstants.RIGHT);
		oBaslaDakika3.setBorder(compoundBorderForInput);
		oBaslaDakika3.setBounds(727, 417, 38, 30);
		standardDataPanel.add(oBaslaDakika3);

		oBitisDakika3 = new JTextField(data[11] == null ? "" : data[11]);
		oBitisDakika3.setHorizontalAlignment(SwingConstants.RIGHT);
		oBitisDakika3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		oBitisDakika3.setBorder(compoundBorderForInput);
		oBitisDakika3.setBounds(764, 417, 38, 30);
		standardDataPanel.add(oBitisDakika3);

		// ROW 4
		oBaslaSaat4 = new JTextField(data[12] == null ? "" : data[12]);
		oBaslaSaat4.setHorizontalAlignment(SwingConstants.RIGHT);
		oBaslaSaat4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		oBaslaSaat4.setBorder(compoundBorderForInput);
		oBaslaSaat4.setBounds(653, 446, 38, 30);
		standardDataPanel.add(oBaslaSaat4);

		oBitisSaat4 = new JTextField(data[13] == null ? "" : data[13]);
		oBitisSaat4.setHorizontalAlignment(SwingConstants.RIGHT);
		oBitisSaat4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		oBitisSaat4.setBorder(compoundBorderForInput);
		oBitisSaat4.setBounds(690, 446, 38, 30);
		standardDataPanel.add(oBitisSaat4);

		oBaslaDakika4 = new JTextField(data[14] == null ? "" : data[14]);
		oBaslaDakika4.setHorizontalAlignment(SwingConstants.RIGHT);
		oBaslaDakika4.setBorder(compoundBorderForInput);
		oBaslaDakika4.setBounds(727, 446, 38, 30);
		standardDataPanel.add(oBaslaDakika4);

		oBitisDakika4 = new JTextField(data[15] == null ? "" : data[15]);
		oBitisDakika4.setHorizontalAlignment(SwingConstants.RIGHT);
		oBitisDakika4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		oBitisDakika4.setBorder(compoundBorderForInput);
		oBitisDakika4.setBounds(764, 446, 38, 30);
		standardDataPanel.add(oBitisDakika4);

		// ROW 5
		oBaslaSaat5 = new JTextField(data[16] == null ? "" : data[16]);
		oBaslaSaat5.setHorizontalAlignment(SwingConstants.RIGHT);
		oBaslaSaat5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		oBaslaSaat5.setBorder(compoundBorderForInput);
		oBaslaSaat5.setBounds(653, 475, 38, 30);
		standardDataPanel.add(oBaslaSaat5);

		oBitisSaat5 = new JTextField(data[17] == null ? "" : data[17]);
		oBitisSaat5.setHorizontalAlignment(SwingConstants.RIGHT);
		oBitisSaat5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		oBitisSaat5.setBorder(compoundBorderForInput);
		oBitisSaat5.setBounds(690, 475, 38, 30);
		standardDataPanel.add(oBitisSaat5);

		oBaslaDakika5 = new JTextField(data[18] == null ? "" : data[18]);
		oBaslaDakika5.setHorizontalAlignment(SwingConstants.RIGHT);
		oBaslaDakika5.setBorder(compoundBorderForInput);
		oBaslaDakika5.setBounds(727, 475, 38, 30);
		standardDataPanel.add(oBaslaDakika5);

		oBitisDakika5 = new JTextField(data[19] == null ? "" : data[19]);
		oBitisDakika5.setHorizontalAlignment(SwingConstants.RIGHT);
		oBitisDakika5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		oBitisDakika5.setBorder(compoundBorderForInput);
		oBitisDakika5.setBounds(764, 475, 38, 30);
		standardDataPanel.add(oBitisDakika5);
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