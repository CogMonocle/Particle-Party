import java.util.Random;

public class ColorPickerBlues implements RendererParticle.ColorPicker
{

	Random r = new Random();
	
	@Override
	public float[] pickColor()
	{
		float[] colors = {r.nextFloat() * 0.3f, r.nextFloat() * 0.3f, r.nextFloat(), 1.0f};
		return colors;
	}

	@Override
	public String name()
	{
		return "Blues";
	}

}
