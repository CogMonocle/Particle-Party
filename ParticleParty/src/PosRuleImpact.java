
public class PosRuleImpact implements RendererParticle.PosRule
{
	public static final double SPEED_MULTIPLIER = 200;
	public static final double TAN_MULTIPLIER = 0.01;
	public static final double IMPLODE_TIME = 1000;
	
	@Override
	public double[] getPos(double initX, double initY, double initA, long age,
			ExtraProperties p)
	{
		double resultX = initX + Math.cos(initA) * Math.atan((IMPLODE_TIME - age) * TAN_MULTIPLIER) * SPEED_MULTIPLIER;
		double resultY = initY + Math.sin(initA) * Math.atan((IMPLODE_TIME - age) * TAN_MULTIPLIER) * SPEED_MULTIPLIER;
		return new double[]{resultX, resultY, initA};
	}

	@Override
	public String name()
	{
		return "Impact";
	}
}