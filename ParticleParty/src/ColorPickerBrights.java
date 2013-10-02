import java.util.Random;


/**
 * @author Justin
 *
 * Color Picker for various bright colors
 */
public class ColorPickerBrights implements RendererParticle.ColorPicker
{

	Random r = new Random();
	
	@Override
	public float[] pickColor()
	{
		float[] colors = {(r.nextFloat() * 0.8f) + 0.2f, (r.nextFloat() * 0.8f) + 0.2f, (r.nextFloat() * 0.8f) + 0.2f, 1.0f};
		return colors;
	}

	@Override
	public String name()
	{
		return "Brights";
	}

}
