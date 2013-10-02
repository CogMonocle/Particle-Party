import java.util.Random;


/**
 * @author Justin
 *
 * Color Picker for various purples
 */
public class ColorPickerPurples implements RendererParticle.ColorPicker
{

	Random r = new Random();
	
	@Override
	public float[] pickColor()
	{
		float[] colors = {r.nextFloat(), r.nextFloat() * 0.2f, r.nextFloat(), 1.0f};
		return colors;
	}

	@Override
	public String name()
	{
		return "Purples";
	}

}