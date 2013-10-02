
public class ColorRuleDarkenFade	implements RendererParticle.ColorRule
{
	public static final float BRIGHTEN_RATE = 0.001f;
	public static final float FADE_RATE = 0.001f;
	
	@Override
	public float[] getColor(float initR, float initG, float initB, float initA,
			long age, ExtraProperties p)
	{
		float resultR = initR - age * BRIGHTEN_RATE;
		float resultG = initG - age * BRIGHTEN_RATE;
		float resultB = initB - age * BRIGHTEN_RATE;
		float resultA = initA - age * FADE_RATE;
		return new float[]{resultR, resultG, resultB, resultA};
	}

	@Override
	public String name()
	{
		return "Darken";
	}

}
