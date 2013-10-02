import org.lwjgl.opengl.GL11;


public class PosRuleSpinWiggleRise implements RendererParticle.PosRule, SidebarItem
{
	public static final double ANTIGRAVITY = 0.0014;
	public static final double ANGULAR_VELOCITY = 0.01;
	public static final double MAX_AMPLITUDE = 500;
	public static final double MIN_AMPLITUDE = 0;
	public static final double AMP_RANGE = MAX_AMPLITUDE - MIN_AMPLITUDE;
	public static final double MAX_PERIOD = 100;
	public static final double MIN_PERIOD = 10;
	public static final double PER_RANGE = MAX_PERIOD - MIN_PERIOD;
	public static double SIDEBAR_SLIDER_WIDTH = 10;
	public static double amplitude = 5;
	public static double period = 50;
	private static int sideBarFrameId;
	private static int sideBarId;
	
	@Override
	public double[] getPos(double initX, double initY, double initA, long age, ExtraProperties p)
	{
		double resultX = initX + (amplitude * Math.sin(age / period));
		double resultY = initY + ((ANTIGRAVITY * age * age) / 2);
		double resultA = initA + (ANGULAR_VELOCITY * age);
		return new double[]{resultX, resultY, resultA};
	}
	
	static
	{
		PosRuleSpinWiggleRise p = new PosRuleSpinWiggleRise();
		sideBarFrameId = Sidebar.addFrame("SpinWiggleRiseMenu");
		Sidebar.addSidebarItem(new ElementText(), sideBarFrameId);
		sideBarId = Sidebar.addSidebarItem(p, sideBarFrameId);
	}
	
	@Override
	public String name()
	{
		return "Wave Rise";
	}

	@Override
	public void update(long delta)
	{
		
	}

	@Override
	public void render(double xPos, double yPos)
	{
		GL11.glBegin(GL11.GL_QUADS);
		
		// Background
		GL11.glColor3f(0.2f, 0.2f, 0.2f);
		GL11.glVertex2d(xPos, yPos);
		GL11.glVertex2d(xPos + getWidth(), yPos);
		GL11.glVertex2d(xPos + getWidth(), yPos + getHeight());
		GL11.glVertex2d(xPos, yPos + getHeight());
		
		// Amp slider bar
		double topY = (yPos + getHeight())  - Sidebar.VERTICAL_SPACING;
		double height = (getHeight() - (3 * Sidebar.VERTICAL_SPACING)) / 2;
		double width = getWidth() - (2 * Sidebar.HORIZONTAL_SPACING);
		GL11.glColor3f(0.8f, 0.8f, 0.8f);
		GL11.glVertex2d(xPos + Sidebar.HORIZONTAL_SPACING, topY - height);
		GL11.glVertex2d(xPos + Sidebar.HORIZONTAL_SPACING + width, topY - height);
		GL11.glVertex2d(xPos + Sidebar.HORIZONTAL_SPACING + width, topY);
		GL11.glVertex2d(xPos + Sidebar.HORIZONTAL_SPACING, topY);
		
		// Amp slider
		double pos = ((1 - ((amplitude - MIN_AMPLITUDE) / AMP_RANGE)) * (width - SIDEBAR_SLIDER_WIDTH));
		GL11.glColor3f(0.1f, 0.1f, 0.1f);
		GL11.glVertex2d(xPos + Sidebar.HORIZONTAL_SPACING + pos, topY - height);
		GL11.glVertex2d(xPos + Sidebar.HORIZONTAL_SPACING + pos + SIDEBAR_SLIDER_WIDTH, topY - height);
		GL11.glVertex2d(xPos + Sidebar.HORIZONTAL_SPACING + pos + SIDEBAR_SLIDER_WIDTH, topY);
		GL11.glVertex2d(xPos + Sidebar.HORIZONTAL_SPACING + pos, topY);
		
		
		// Per slider bar
		topY -= (height + Sidebar.VERTICAL_SPACING);
		GL11.glColor3f(0.8f, 0.8f, 0.8f);
		GL11.glVertex2d(xPos + Sidebar.HORIZONTAL_SPACING, topY - height);
		GL11.glVertex2d(xPos + Sidebar.HORIZONTAL_SPACING + width, topY - height);
		GL11.glVertex2d(xPos + Sidebar.HORIZONTAL_SPACING + width, topY);
		GL11.glVertex2d(xPos + Sidebar.HORIZONTAL_SPACING, topY);
		
		// Amp slider
		pos = ((1 - ((period - MIN_PERIOD) / PER_RANGE)) * (width - SIDEBAR_SLIDER_WIDTH));
		GL11.glColor3f(0.1f, 0.1f, 0.1f);
		GL11.glVertex2d(xPos + Sidebar.HORIZONTAL_SPACING + pos, topY - height);
		GL11.glVertex2d(xPos + Sidebar.HORIZONTAL_SPACING + pos + SIDEBAR_SLIDER_WIDTH, topY - height);
		GL11.glVertex2d(xPos + Sidebar.HORIZONTAL_SPACING + pos + SIDEBAR_SLIDER_WIDTH, topY);
		GL11.glVertex2d(xPos + Sidebar.HORIZONTAL_SPACING + pos, topY);
		
		
		GL11.glEnd();	
	}

	@Override
	public void handleClick(int x, int y)
	{
		if((x >= (2 * Sidebar.HORIZONTAL_SPACING)) && (x <= (getWidth())))
		{
			double topY = getHeight()  - Sidebar.VERTICAL_SPACING;
			double height = (getHeight() - (3 * Sidebar.VERTICAL_SPACING)) / 2;
			if((y < (topY)) && (y > (topY - height)))
			{
				double pos = 1 - ((x - (2 * Sidebar.HORIZONTAL_SPACING)) / (getWidth() - (2 * Sidebar.HORIZONTAL_SPACING)));
				amplitude = MIN_AMPLITUDE + (AMP_RANGE * pos);
			}
			if((y < (topY - (height + Sidebar.VERTICAL_SPACING))) && (y > (topY - ((2 * height) + Sidebar.VERTICAL_SPACING))))
			{
				double pos = 1 - ((x - (2 * Sidebar.HORIZONTAL_SPACING)) / (getWidth() - (2 * Sidebar.HORIZONTAL_SPACING)));
				period = MIN_PERIOD + (PER_RANGE * pos);
			}
		}
	}

	@Override
	public double getWidth()
	{
		return (Sidebar.WIDTH - (Sidebar.VERTICAL_SPACING * 2));
	}

	@Override
	public double getHeight()
	{
		return 120;
	}
}
