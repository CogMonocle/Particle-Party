import java.util.Random;


public class GeneratorMouse extends GeneratorParticle
{
	public static final double MILLISECONDS_PER_PARTICLE = 2;
	public static final double SIZE = 8;
	
	private final GeneratorParameters info;
	
	private boolean[] cPickers;
	private boolean[] cRules;
	private boolean[] sRules;
	private boolean[] pRules;
	
	private int x_;
	private int y_;
	private long timeSince_;
	
	public GeneratorMouse(RendererParticle p)
	{
		super(p);
		x_ = 0;
		y_ = 0;
		timeSince_ = 0;
		info = new GeneratorParameters("Mouse");
	}
	
	@Override
	public void render()
	{

	}

	@Override
	public void update(long delta)
	{
		int cPicker;
		int cRule;
		int sRule;
		int pRule;
		timeSince_ += delta;
		while(timeSince_ > MILLISECONDS_PER_PARTICLE)
		{
			Random r = new Random();
			while(!cPickers[cPicker = r.nextInt(cPickers.length)])
			{
				;
			}
			while(!cRules[cRule = r.nextInt(cRules.length)])
			{
				;
			}
			while(!sRules[sRule = r.nextInt(sRules.length)])
			{
				;
			}
			while(!pRules[pRule = r.nextInt(pRules.length)])
			{
				;
			}
			float[] colors = RendererParticle.getCPicker(cPicker).pickColor();
			particles_.addParticle(new ParticleParameters(x_, y_, r.nextDouble() * 2 * Math.PI, colors[0],
					colors[1], colors[2], colors[3], SIZE, cRule, sRule,
					pRule, new ExtraProperties()));
			timeSince_ -= MILLISECONDS_PER_PARTICLE;			
		}
	}

	@Override
	public void mouseInput(int x, int y, int dX, int dY, boolean lmbDown, boolean rmbDown)
	{
		x_ = x;
		y_ = y;
	}
	
	@Override
	public void setColorPickers(boolean[] pickers)
	{
		cPickers = pickers;
	}
	
	@Override
	public void setColorRules(boolean[] rules)
	{
		cRules = rules;
	}

	@Override
	public void setSizeRules(boolean[] rules)
	{
		sRules = rules;
	}

	@Override
	public void setPosRules(boolean[] rules) 
	{
		pRules = rules;
	}
	
	@Override
	public GeneratorParameters getInfo()
	{
		return info;
	}
}
