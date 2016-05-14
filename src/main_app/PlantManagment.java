package main_app;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import utils.LoginDataDisplay;

public class PlantManagment extends JFrame implements LoginDataDisplay
{
	private static final long	serialVersionUID	= -3598167150109127981L;
	private JPanel				contentPane;

	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					PlantManagment frame = new PlantManagment();
					frame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	public PlantManagment()
	{
		setTitle("Bitki Yönetimi");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

	@Override
	public void setUserID(int _id)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setUserName(String user_name)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setAuthID(int auth_id)
	{
		// TODO Auto-generated method stub
		
	}
}