import java.util.Random;


/**
 * @author Justin Niosi
 *
 * Color Picker for random colors
 */
public class ColorPickerRandom implements RendererParticle.ColorPicker
{
	Random r = new Random();
	
	@Override
	public float[] pickColor()
	{
		float[] colors = {r.nextFloat(), r.nextFloat(), r.nextFloat(), 1.0f};
		return colors;
	}

	@Override
	public String name()
	{
		return "Random";
	}

}
