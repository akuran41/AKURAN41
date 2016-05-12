package ui;

import javax.swing.JSeparator;

public class CreateSeparator
{
	public JSeparator generateSeparator(int x, int y, int w)
	{
		JSeparator separator = new JSeparator();
		separator.setBounds(x, y, w, 2);
		
		return separator;
	}
}