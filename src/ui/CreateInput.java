package ui;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class CreateInput
{
	public JTextField generateTextField(boolean isRight, int x, int y, int w)
	{
		JTextField myTextField = new JTextField();
		myTextField.setBounds(x, y, w, 25);

		if (isRight)
			myTextField.setHorizontalAlignment(SwingConstants.RIGHT);

		return myTextField;
	}
}