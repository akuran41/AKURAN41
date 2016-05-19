package main_app;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class AddNewPicCard extends JFrame
{
	private static final long	serialVersionUID	= 2494461427189987440L;
	private JPanel				contentPane;

	private JLabel				lblUsername;

	private int					_id;
	private String				user_name;
	private JTextField textField;

	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					AddNewPicCard frame = new AddNewPicCard();
					frame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	public AddNewPicCard()
	{
		setTitle("Yeni Kart Ekle");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblUsername = new JLabel("user_name");
		lblUsername.setForeground(Color.RED);
		lblUsername.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblUsername.setBounds(224, 11, 200, 20);
		lblUsername.setText(user_name);
		contentPane.add(lblUsername);

		JLabel label = new JLabel("2016/05/02 18:18");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setForeground(Color.RED);
		label.setFont(new Font("Tahoma", Font.BOLD, 13));
		label.setBounds(224, 28, 200, 20);

		// Tarih ve Zaman olustur
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.getDefault());
		String dateToString = simpleDateFormat.format(date);
		label.setText(dateToString);

		contentPane.add(label);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 59, 414, 2);
		contentPane.add(separator);
		
		JLabel lblBitkiId = new JLabel("Bitki ID");
		lblBitkiId.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblBitkiId.setBounds(10, 72, 75, 25);
		contentPane.add(lblBitkiId);
		
		textField = new JTextField();
		textField.setBounds(75, 72, 60, 25);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblUyariBitkiId = new JLabel("<html><font color='red'>Uyari</font>: Bitki ID pic katri ile <u>ayni</u> olmak zorundadir.</html>");
		lblUyariBitkiId.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblUyariBitkiId.setBounds(145, 76, 279, 14);
		contentPane.add(lblUyariBitkiId);
		
		JLabel lblBitkiAdi = new JLabel("Bitki Adi");
		lblBitkiAdi.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblBitkiAdi.setBounds(10, 108, 75, 25);
		contentPane.add(lblBitkiAdi);
		
		JComboBox<String> comboBox = new JComboBox();
		comboBox.setBounds(75, 108, 175, 25);
		contentPane.add(comboBox);

		contentPane.revalidate();
		contentPane.repaint();
	}
}