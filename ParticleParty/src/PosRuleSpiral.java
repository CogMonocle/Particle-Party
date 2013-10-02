
public class PosRuleSpiral implements RendererParticle.PosRule
{
	
	public static final double MULTIPLIER = 0.01;
	public static final double SPACING = 2;
	public static final double SQRT_SPAC = Math.sqrt(SPACING);
	public static final double THING = 2;
	public static final double SQRT_2 = Math.sqrt(2);
	
	@Override
	public double[] getPos(double initX, double initY, double initA, long age,
			ExtraProperties p) 
	{
		double resultA = initA + age * MULTIPLIER;
		double t = theta(age);
		double resultX = initX + (Math.cos(t + initA) * t * SPACING);
		double resultY = initY + (Math.sin(t + initA) * t * SPACING);
		return new double[]{resultX, resultY, resultA};
	}
	
	public double theta(long time)
	{
		return SQRT_2 * Math.sqrt(SPACING * THING + time) / SQRT_SPAC;
	}

	@Override
	public String name()
	{
		return "Spiral";
	}
}
