
public class PosRuleFountain implements RendererParticle.PosRule
{
	public static String KEY = "center";
	public static double INITIAL_Y_VEL = 2;
	public static double GRAVITY = -0.004;
	public static double HORIZ_MULTIPLIER = 0.01;
	public static double SPIN_MULTIPLIER = 0.2;
	
	@Override
	public double[] getPos(double initX, double initY, double initA, long age, ExtraProperties p)
	{
		String centerString = p.getValue(KEY);
		double center;
		try
		{
			center = Double.parseDouble(centerString);
		}
		catch(NumberFormatException e)
		{
			return new double[]{initX, initY, initA};
		}
		double resultX = initX + ((initX - center) * age * HORIZ_MULTIPLIER);
		double resultY = initY + (INITIAL_Y_VEL * age) + (GRAVITY * age * age / 2);
		double resultA = initA + ((initX - center) * age * SPIN_MULTIPLIER);
		return new double[]{resultX, resultY, resultA};
	}
	
	@Override
	public String name()
	{
		return "Fountain";
	}
}
