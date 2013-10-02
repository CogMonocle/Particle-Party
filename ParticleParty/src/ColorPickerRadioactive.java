import java.util.Random;


/**
 * @author Justin Niosi
 * 
 * Light Green Color Picker
 */
public class ColorPickerRadioactive  implements RendererParticle.ColorPicker
{

	Random r = new Random();
	
	@Override
	public float[] pickColor()
	{
		float[] colors = {r.nextFloat() * 0.2f, (r.nextFloat() * 0.6f) + 0.4f, r.nextFloat() * 0.2f, 1.0f};
		return colors;
	}

	@Override
	public String name()
	{
		return "Radioactive";
	}

}