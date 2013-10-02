import java.util.Random;

import org.lwjgl.opengl.GL11;


public class GeneratorCircle extends GeneratorParticle
{
	public static final double MILLISECONDS_PER_PARTICLE = 20;
	
	private final RendererParticle particles_;
	private final GeneratorParameters info;
	
	private boolean[] cPickers;
	private boolean[] cRules;
	private boolean[] sRules;
	private boolean[] pRules;
	
	public long timeSince_;
	private double x_;
	private double y_;
	private double r_;
	private int numVerts_;
	public int particleID_;
	
	public GeneratorCircle(double x, double y, double r, RendererParticle p)
	{
		super(p);
		timeSince_ = 0;
		setX(x);
		setY(y);
		r_ = r;
		numVerts_ = (int) (r_ + 10);
		particles_ = p;
		info = new GeneratorParameters("Circle");
		//info = new GeneratorParameters("Circle", null, null, new int[]{PosRuleFountain.ruleId});
	}

	public void shiftX(double x)
	{
		setX(getX_() + x);
	}
	
	public void shiftY(double y)
	{
		setY(getY_() + y);
	}
	
	public void setX(double x)
	{
		x_ = x;
	}
	
	public void setY(double y)
	{
		y_ = y;
	}
	
	public void setR(double r)
	{
		r_ = r;
		numVerts_ = (int) (r_ + 10);
	}
	
	@Override
	public void render()
	{
		GL11.glBegin(GL11.GL_TRIANGLE_FAN);
		double angle;
		GL11.glColor4f((float)Math.random(), (float)Math.random(), (float)Math.random(), 1.0f);
		GL11.glVertex2d(getX_(), getY_());
		for(int i = 0; i <= numVerts_; i++)
		{
			GL11.glColor4f((float)Math.random(), (float)Math.random(), (float)Math.random(), 0.6f);
			angle = (i * 2 * Math.PI) / numVerts_;
			GL11.glVertex2d(getX_() + (Math.cos(angle) * r_), getY_() + (Math.sin(angle) * r_));
		}
		GL11.glEnd();
	}
	
	@Override
	public void update(long delta)
	{
		int cPicker;
		int cRule;
		int sRule;
		int pRule;
		timeSince_ += delta;
		if(timeSince_ > MILLISECONDS_PER_PARTICLE)
		{
			Random r = new Random();
			double dist = r.nextDouble() * r_;
			double posAng = r.nextDouble() * 2 * Math.PI;
			double rotAng = r.nextDouble() * 2 * Math.PI;
			while(!cPickers[cPicker = r.nextInt(cPickers.length)]);
			while(!cRules[cRule = r.nextInt(cRules.length)]);
			while(!sRules[sRule = r.nextInt(sRules.length)]);
			while(!pRules[pRule = r.nextInt(pRules.length)]);
			ExtraProperties p = new ExtraProperties();
			p.setValue(PosRuleFountain.KEY, String.valueOf(x_));
			float[] colors = RendererParticle.getCPicker(cPicker).pickColor();
			particles_.addParticle(new ParticleParameters(x_ + ((dist * Math.cos(posAng)) /2), y_ + ((dist * Math.sin(posAng)) / 2),
					rotAng, colors[0], colors[1], colors[2], colors[3], 5,
					cRule, sRule, pRule, p));
			timeSince_ -= MILLISECONDS_PER_PARTICLE;			
		}
	}

	public boolean isInside(double x, double y)
	{
		double dx = getX_() - x;
		double dy = getY_() - y;
		return ((dx * dx) + (dy * dy)) <= (r_ * r_);
	}

	public double getX_()
	{
		return x_;
	}

	public double getY_()
	{
		return y_;
	}
	
	@Override
	public void mouseInput(int x, int y, int dX, int dY, boolean lmbDown, boolean rmbDown)
	{
		// TODO Auto-generated method stub
		
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
