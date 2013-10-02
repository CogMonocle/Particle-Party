
public class PosRuleSpinRise implements RendererParticle.PosRule
{
	public static double GRAVITY = 0.0004;
	public static double ANGULAR_VELOCITY = 0.01;
	
	@Override
	public double[] getPos(double initX, double initY, double initA, long age, ExtraProperties p)
	{
		double resultY = initY + (GRAVITY * age * age / 2);
		double resultA = initA + (ANGULAR_VELOCITY * age);
		return new double[]{initX, resultY, resultA};
	}
	
	@Override
	public String name()
	{
		return "Spin Rise";
	}
}
