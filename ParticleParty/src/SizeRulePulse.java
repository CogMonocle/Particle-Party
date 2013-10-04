
public class SizeRulePulse implements RendererParticle.SizeRule
{
	
	public static final double MULTIPLIER = 0.45;
	public static final double SHIFT = 1 - MULTIPLIER;
	public static final double SPEED = 0.2;

	@Override
	public double getSize(double initS, long age, ExtraProperties p)
	{
		return ((Math.sin(age * SPEED) * MULTIPLIER) + SHIFT) * initS;
	}

	@Override
	public String name()
	{
		return "Pulse";
	}
}
