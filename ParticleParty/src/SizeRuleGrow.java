
public class SizeRuleGrow implements RendererParticle.SizeRule
{
	
	public static final double MULTIPLIER = 0.1;

	@Override
	public double getSize(double initS, long age, ExtraProperties p)
	{
		return initS + (age * MULTIPLIER);
	}

	@Override
	public String name()
	{
		return "Grow";
	}
}
