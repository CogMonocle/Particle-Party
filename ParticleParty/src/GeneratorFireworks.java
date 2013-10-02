import java.util.Random;

import org.lwjgl.opengl.GL11;


public class GeneratorFireworks extends GeneratorParticle
{
	public static final double MILLISECONDS_PER_PARTICLE = 20;
	public static final double LAUNCHER_WIDTH = 50;
	public static final double LAUNCHER_HEIGHT = 30;
	public static final double GRAVITY = 0.0005;
	public static final double FIREWORK_SIZE = 10;
	public static final double TRAIL_P_SIZE = 2;
	public static final double BURST_P_SIZE = 4;
	public static final int MAX_FIREWORKS = 16;
	public static final int BURST_AMT = 32;
	
	protected Firework[] fireworks;
	
	protected GeneratorParameters info;
	protected RendererParticle burstRenderer;
	protected Random r;
	
	private boolean[] cPickers;
	private boolean[] cRules;
	private boolean[] sRules;
	private boolean[] pRules;
	
	protected double x_;
	protected double y_;
	protected boolean clickedThisTick;
	protected boolean clickedLastTick;
	
	
	public GeneratorFireworks(RendererParticle p, RendererParticle b)
	{
		super(p);
		fireworks = new Firework[MAX_FIREWORKS];
		r = new Random();
		x_ = 0;
		y_ = 0;
		clickedThisTick = false;
		clickedLastTick = false;
		info = new GeneratorParameters("Fireworks");
		burstRenderer = b;
	}
	
	@Override
	public void clear()
	{
		super.clear();
		burstRenderer.clear();
	}
	
	protected boolean addFirework(Firework f)
	{
		for(int i = 0; i < MAX_FIREWORKS; i++)
		{
			if((fireworks[i] == null) || !fireworks[i].isAlive())
			{
				fireworks[i] = f;
				return true;
			}
		}
		return false;
	}
	
	@Override
	public void render() 
	{
		GL11.glBegin(GL11.GL_QUADS);
		for(int i = 0; i < MAX_FIREWORKS; i++)
		{
			if((fireworks[i] != null) && fireworks[i].isAlive())
			{
				fireworks[i].render();
			}
		}
		GL11.glEnd();
		GL11.glColor3f(0.4f, 0.0f, 0.0f);
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex2d(x_, y_);
		GL11.glVertex2d(x_ + LAUNCHER_WIDTH, y_);
		GL11.glVertex2d(x_ + LAUNCHER_WIDTH, y_ + LAUNCHER_HEIGHT);
		GL11.glVertex2d(x_, y_ + LAUNCHER_HEIGHT);
		GL11.glEnd();
	}

	@Override
	public void update(long delta)
	{
		clickedLastTick = clickedThisTick;
		clickedThisTick = false;
		for(int i = 0; i < MAX_FIREWORKS; i++)
		{
			if((fireworks[i] != null) && fireworks[i].isAlive())
			{
				fireworks[i].update(delta);
			}
		}
	}

	@Override
	public void mouseInput(int x, int y, int dX, int dY, boolean lmbDown,
			boolean rmbDown)
	{
		int cPicker;
		int cRule;
		int sRule;
		int pRule;
		x_ = x - (LAUNCHER_WIDTH / 2);
		clickedThisTick = lmbDown;
		if(!clickedLastTick && clickedThisTick)
		{
			while(!cPickers[cPicker = r.nextInt(cPickers.length)]);
			while(!cRules[cRule = r.nextInt(cRules.length)]);
			while(!sRules[sRule = r.nextInt(sRules.length)]);
			while(!pRules[pRule = r.nextInt(pRules.length)]);
			float[] colors = RendererParticle.getCPicker(cPicker).pickColor();
			addFirework(new Firework(x_ + ((LAUNCHER_WIDTH - FIREWORK_SIZE) / 2), Math.sqrt(2 * GRAVITY * y), colors[0],
					colors[1], colors[2], cRule, sRule, pRule));
		}
	}
	
	protected class Firework
	{
		private final double x_;
		private final double initY_;
		private double y_;
		private final double yVel_;
		private final float r_;
		private final float g_;
		private final float b_;
		private long timeSince_;
		private long age_;
		private final int cRule;
		private final int sRule;
		private final int pRule;
		private boolean alive_;
		
		public Firework(double x, double yVel, float r, float g, float b, int color, int size, int pos)
		{
			x_ = x;
			initY_ = 0;
			y_ = initY_;
			yVel_ = yVel;
			r_ = r;
			g_ = g;
			b_ = b;
			timeSince_ = 0;
			age_ = 0;
			cRule = color;
			sRule = size;
			pRule = pos;
			alive_ = true;
		}
		
		public void render()
		{
			GL11.glColor3f(r_, g_, b_);
			GL11.glVertex2d(x_, y_);
			GL11.glVertex2d(x_ + FIREWORK_SIZE, y_);
			GL11.glVertex2d(x_ + FIREWORK_SIZE, y_ + FIREWORK_SIZE);
			GL11.glVertex2d(x_, y_ + FIREWORK_SIZE);
		}
		
		public boolean isAlive()
		{
			return alive_;
		}
		
		public void update(long delta)
		{
			age_ += delta;
			y_ = (initY_ + (yVel_ * age_)) - ((GRAVITY * age_* age_) / 2) ;
			if((yVel_ - (GRAVITY * age_)) < 0)
			{
				alive_ = false;
				for(int i = 0; i < BURST_AMT; i++)
				{
					burstRenderer.addParticle(new ParticleParameters(x_ + (FIREWORK_SIZE / 2),
							y_ + (FIREWORK_SIZE / 2), r.nextDouble() * 2 * Math.PI, r_, g_, b_,
							1.0f, ((r.nextDouble() + 1) * BURST_P_SIZE) / 2, cRule,
							sRule, pRule, null));
				}
			}
			timeSince_ += delta;
			while((timeSince_ > MILLISECONDS_PER_PARTICLE) && alive_)
			{
				particles_.addParticle(new ParticleParameters(x_ + (r.nextDouble() * FIREWORK_SIZE), y_, 0, r_, g_, b_, 1.0f,
						TRAIL_P_SIZE, RendererParticle.getCRuleID("Fade Out"), 0, 0, null));
				timeSince_ -= MILLISECONDS_PER_PARTICLE;			
			}			
		}
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
