import java.util.Random;


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
