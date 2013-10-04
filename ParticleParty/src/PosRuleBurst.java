
public class PosRuleBurst implements RendererParticle.PosRule
{
	public static final double SPEED_MULTIPLIER = 150;
	public static final double TAN_MULTIPLIER = 0.002;
	
	@Override
	public double[] getPos(double initX, double initY, double initA, long age,
			ExtraProperties p)
	{
		double resultX = initX + (Math.cos(initA) * Math.atan(age * TAN_MULTIPLIER) * SPEED_MULTIPLIER);
		double resultY = initY + (Math.sin(initA) * Math.atan(age * TAN_MULTIPLIER) * SPEED_MULTIPLIER);
		return new double[]{resultX, resultY, initA};
	}

	@Override
	public String name()
	{
		return "Burst";
	}
}
