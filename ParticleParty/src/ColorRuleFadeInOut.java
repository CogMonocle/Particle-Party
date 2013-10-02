
public class ColorRuleFadeInOut implements RendererParticle.ColorRule
{
	public static final float FADE_RATE = 0.001f;
	
	@Override
	public float[] getColor(float initR, float initG, float initB, float initA,
			long age, ExtraProperties p)
	{
		long lifespan = (long) (initA / FADE_RATE);
		return new float[]{initR, initG, initB, initA - (Math.abs(lifespan - age) * FADE_RATE)};
	}
	
	@Override
	public String name()
	{
		return "Fade In Out";
	}
}
