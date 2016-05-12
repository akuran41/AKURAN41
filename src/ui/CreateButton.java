package ui;

import java.awt.Font;

import javax.swing.JButton;

import project_const.ButtonArgs;

public class CreateButton
{
	public JButton generateButton(String text, int x, int y)
	{
		JButton myButton = new JButton(text);
		myButton.setFont(new Font(ButtonArgs._FONT, Font.PLAIN, ButtonArgs._SIZE));
		myButton.setBounds(x, y, ButtonArgs._WIDTH, ButtonArgs._HEIGHT);
		
		return myButton;
	}
}