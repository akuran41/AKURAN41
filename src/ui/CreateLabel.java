package ui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class CreateLabel
{
	public JLabel generateLabel(String text, boolean isRed, int isBold, int isCenter, int lblSize, int xCoordinate, int yCoordinate, int lblWidth,
			int lblHeight)
	{
		JLabel myLabel = new JLabel();

		if (isRed)
			myLabel.setForeground(Color.RED);

		if (isBold == 1)
			myLabel.setFont(new Font("Tahoma", Font.PLAIN, lblSize));
		else if (isBold == 2)
			myLabel.setFont(new Font("Tahoma", Font.BOLD, lblSize));
		else
			myLabel.setFont(new Font("Tahoma", Font.ITALIC, lblSize));

		if (isCenter == 1)
			myLabel.setHorizontalAlignment(SwingConstants.LEFT);
		else if (isCenter == 2)
			myLabel.setHorizontalAlignment(SwingConstants.CENTER);
		else if (isCenter == 3)
			myLabel.setHorizontalAlignment(SwingConstants.RIGHT);

		myLabel.setBounds(xCoordinate, yCoordinate, lblWidth, lblHeight);
		myLabel.setText(text);

		return myLabel;
	}
}