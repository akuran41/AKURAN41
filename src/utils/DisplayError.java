package utils;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class DisplayError
{
	private JPanel	contentPane;

	public DisplayError(JPanel contentPane)
	{
		this.contentPane = contentPane;
	}

	public void showMessageDialog(String msg, String title, int messageType)
	{
		JOptionPane.showMessageDialog(contentPane, msg, title, messageType, null);
	}

	public int showConfirmDialog(String msg, String title, int messageType)
	{
		return JOptionPane.showConfirmDialog(contentPane, msg, title, JOptionPane.YES_NO_OPTION);
	}
}