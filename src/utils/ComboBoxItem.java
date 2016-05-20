package utils;

public class ComboBoxItem
{
	private int		_id;
	private String	bitki_adi;

	public ComboBoxItem(int _id, String bitki_adi)
	{
		this._id = _id;
		this.bitki_adi = bitki_adi;
	}

	public int get_id()
	{
		return _id;
	}

	public String getBitki_adi()
	{
		return bitki_adi;
	}
}