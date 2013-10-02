
public class SizeRuleShrink implements RendererParticle.SizeRule
{
	
	public static final double MULTIPLIER = 0.0025;

	@Override
	public double getSize(double initS, long age, ExtraProperties p)
	{
		double resultS = initS - (age * MULTIPLIER);
		return resultS > 0 ? resultS : 0;
	}

	@Override
	public String name()
	{
		return "Shrink";
	}
}
