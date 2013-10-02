
public class ColorRuleFadeOut implements RendererParticle.ColorRule
{
	public static final float FADE_RATE = 0.001f;
	
	@Override
	public float[] getColor(float initR, float initG, float initB, float initA,
			long age, ExtraProperties p)
	{
		return new float[]{initR, initG, initB, initA - (age * FADE_RATE)};
	}
	
	@Override
	public String name()
	{
		return "Fade Out";
	}
}
