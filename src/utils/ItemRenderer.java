package utils;

import java.awt.Component;

import javax.swing.JList;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

public class ItemRenderer extends BasicComboBoxRenderer
{
	private static final long	serialVersionUID	= 1213466115581191004L;

	@Override
	public Component getListCellRendererComponent(@SuppressWarnings("rawtypes") JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
	{
		super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		
		if (value != null)
		{
			ComboBoxItem item = (ComboBoxItem) value;
			setText(item.getBitki_adi().toUpperCase());
		}
		
		if (index == -1)
		{
			ComboBoxItem item = (ComboBoxItem) value;
			setText("" + item.getBitki_adi().toUpperCase());
		}
		
		return this;
	}
}