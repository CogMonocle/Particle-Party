
public class SizeRulePulse implements RendererParticle.SizeRule
{
	
	public static final double MULTIPLIER = 0.1;
	public static final double SHIFT = 0;

	@Override
	public double getSize(double initS, long age, ExtraProperties p)
	{
		return (Math.sin(age) * MULTIPLIER + SHIFT) * initS;
	}

	@Override
	public String name()
	{
		return "Pulse";
	}
}
