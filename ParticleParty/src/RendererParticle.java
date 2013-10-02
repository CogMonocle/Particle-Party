
public abstract class RendererParticle
{
	public static final int MAX_PARTICLES = 4096;
	public static final int MAX_RULES = 32;
	
	private static ColorPicker[] cPickers;
	private static int numCPickers;
	private static ColorRule[] cRules;
	private static int numCRules;
	private static SizeRule[] sRules;
	private static int numSRules;
	private static PosRule[] pRules;
	private static int numPRules;
	
	protected ExtraProperties[] props_;
	protected double[] xCoord_;
	protected double[] yCoord_;
	protected double[] angle_;
	protected double[] size_;
	protected float[] r_;
	protected float[] g_;
	protected float[] b_;
	protected float[] a_;
	protected long[] age_;
	protected int[] cID_;
	protected int[] sID_;
	protected int[] pID_;

	public RendererParticle()
	{	
		props_ = new ExtraProperties[MAX_PARTICLES];
		xCoord_ = new double[MAX_PARTICLES];
		yCoord_ = new double[MAX_PARTICLES];
		angle_ = new double[MAX_PARTICLES];
		size_ = new double[MAX_PARTICLES];
		r_ = new float[MAX_PARTICLES];
		g_ = new float[MAX_PARTICLES];
		b_ = new float[MAX_PARTICLES];
		a_ = new float[MAX_PARTICLES];
		age_ = new long[MAX_PARTICLES];
		cID_ = new int[MAX_PARTICLES];
		sID_ = new int[MAX_PARTICLES];
		pID_ = new int[MAX_PARTICLES];
	}
	
	public void update(long delta)
	{
		for(int i = 0; i < MAX_PARTICLES; i++)
		{
			age_[i] += delta;
		}
	}
	
	public void clear()
	{
		props_ = new ExtraProperties[MAX_PARTICLES];
		xCoord_ = new double[MAX_PARTICLES];
		yCoord_ = new double[MAX_PARTICLES];
		angle_ = new double[MAX_PARTICLES];
		size_ = new double[MAX_PARTICLES];
		r_ = new float[MAX_PARTICLES];
		g_ = new float[MAX_PARTICLES];
		b_ = new float[MAX_PARTICLES];
		a_ = new float[MAX_PARTICLES];
		age_ = new long[MAX_PARTICLES];
		cID_ = new int[MAX_PARTICLES];
		sID_ = new int[MAX_PARTICLES];
		pID_ = new int[MAX_PARTICLES];
	}

	public abstract void render();
	
	public abstract int addParticle(ParticleParameters parameterObject);
	
	public interface ColorPicker
	{
		public float[] pickColor();
		
		public String name();
	}
		
	public interface ColorRule
	{
		public float[] getColor(float initR, float initG, float initB, float initA, long age, ExtraProperties p);
	
		public String name();
	}
	
	public interface SizeRule
	{
		public double getSize(double initS, long age, ExtraProperties p);
		
		public String name();
	}
	
	public interface PosRule
	{
		public double[] getPos(double initX, double initY, double initA, long age, ExtraProperties p);
	
		public String name();
	}
	
	public static int addCPicker(ColorPicker c)
	{
		if(numCPickers < MAX_RULES)
		{
			cPickers[numCPickers] = c;
			numCPickers++;
			return numCPickers - 1;
		}
		return - 1;
	}
	
	public static ColorPicker getCPicker(int id)
	{
		if(id < MAX_RULES)
		{
			return cPickers[id];
		}
		return null;
	}
	
	public static int getCPickerID(String name)
	{
		for(int i = 0; i < numCPickers; i++)
		{
			if(cPickers[i].name().equalsIgnoreCase(name))
			{
				return i;
			}
		}
		return -1;
	}
	
	public static int addCRule(ColorRule c)
	{
		if(numCRules < MAX_RULES)
		{
			cRules[numCRules] = c;
			numCRules++;
			return numCRules - 1;
		}
		return -1;
	}
	
	public static ColorRule getCRule(int id)
	{
		if(id < MAX_RULES)
		{
			return cRules[id];
		}
		return null;
	}
	
	public static int getCRuleID(String name)
	{
		for(int i = 0; i < numCRules; i++)
		{
			if(cRules[i].name().equalsIgnoreCase(name))
			{
				return i;
			}
		}
		return -1;
	}
	
	public static int addSRule(SizeRule s)
	{
		if(numSRules < MAX_RULES)
		{
			sRules[numSRules] = s;
			numSRules ++;
			return numSRules - 1;
		}
		return - 1;
	}
	
	public static SizeRule getSRule(int id)
	{
		if(id < MAX_RULES)
		{
			return sRules[id];
		}
		return null;
	}
	
	public static int getSRuleID(String name)
	{
		for(int i = 0; i < numSRules; i++)
		{
			if(sRules[i].name().equalsIgnoreCase(name))
			{
				return i;
			}
		}
		return -1;
	}
	
	public static int addPRule(PosRule p)
	{
		if(numPRules < MAX_RULES)
		{
			pRules[numPRules] = p;
			numPRules ++;
			return numPRules - 1;
		}
		return -1;
	}
	
	public static PosRule getPRule(int id)
	{
		if(id < MAX_RULES)
		{
			return pRules[id];
		}
		return null;
	}
	
	public static int getPRuleID(String name)
	{
		for(int i = 0; i < numPRules; i++)
		{
			if(pRules[i].name().equalsIgnoreCase(name))
			{
				return i;
			}
		}
		return -1;
	}
	
	static
	{
		cPickers = new ColorPicker[MAX_RULES];
		numCPickers = 0;
		addCPicker(new ColorPicker()
		{

			@Override
			public float[] pickColor()
			{
				float[] array = {1.0f, 1.0f, 1.0f, 1.0f};
				return array;
			}

			@Override
			public String name()
			{
				return "Default";
			}
			
		});
		cRules = new ColorRule[MAX_RULES];
		numCRules = 0;
		addCRule(new ColorRule()
		{

			@Override
			public float[] getColor(float initR, float initG, float initB,
					float initA, long age, ExtraProperties p)
			{
				return new float[]{initR, initG, initB, initA};
			}
			
			@Override
			public String name()
			{
				return "Default";
			}
			
		});
		sRules = new SizeRule[MAX_RULES];
		numSRules = 0;
		addSRule(new SizeRule()
		{

			@Override
			public double getSize(double initS, long age, ExtraProperties p)
			{
				return initS;
			}
			
			@Override
			public String name()
			{
				return "Default";
			}
			
		});
		pRules = new PosRule[MAX_RULES];
		numPRules = 0;
		addPRule(new PosRule()
		{

			@Override
			public double[] getPos(double initX, double initY, double initA,
					long age, ExtraProperties p)
			{
				return new double[]{initX, initY, initA};
			}
			
			@Override
			public String name()
			{
				return "Default";
			}
			
		});	
	}
}
