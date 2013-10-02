
public class ExtraProperties
{
	public static final String UNDEFINED = "UNDEFINED";
	public static final int MAX_PROPS = 32;
	
	private String[] keys_;
	private String[] values_;
	
	public ExtraProperties()
	{
		keys_ = new String[MAX_PROPS];
		values_ = new String[MAX_PROPS];
		for(int i = 0; i < MAX_PROPS; i++)
		{
			keys_[i] = UNDEFINED;
		}
	}
	
	public boolean setValue(String key, String value)
	{
		for(int i = 0; i < MAX_PROPS; i++)
		{
			if(keys_[i].equals(UNDEFINED))
			{
				keys_[i] = key;
				values_[i] = value;
				return true;
			}
		}
		return false;
	}
	
	public String getValue(String key)
	{
		for(int i = 0; i < MAX_PROPS; i++)
		{
			if(keys_[i].equals(key))
			{
				
				return values_[i];
			}
		}
		return "";
	}
}
