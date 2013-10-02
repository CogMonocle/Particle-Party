
public class GeneratorParameters
{

	private String name_;
	
	private int[] cRules_;
	private int[] sRules_;
	private int[] pRules_;
	
	public GeneratorParameters(String name, int[] cRules, int[] sRules, int[] pRules)
	{
		name_ = name;
		cRules_ = cRules;
		sRules_ = sRules;
		pRules_ = pRules;
	}
	
	public GeneratorParameters(String name)
	{
		this(name, null, null, null);
	}

	public String getName()
	{
		return name_;
	}
	
	public int[] getCRules()
	{
		return cRules_;
	}
	
	public int[] getSRules()
	{
		return sRules_;
	}
	
	public int[] getPRules()
	{
		return pRules_;
	}
}
