import org.lwjgl.opengl.GL11;


public class Sidebar
{
	public static final double WIDTH = 200;
	public static final double VERTICAL_SPACING = 10;
	public static final double HORIZONTAL_SPACING = 10;
	public static final int MAX_ELEMS = 16;
	
	private static SidebarItem[] elements_;
	private static double[] elementPos_;
	
	private static double xPos_;
	private static double windowHeight_;
	private static double windowWidth_;
	private static double heightFilled_;
	
	private static boolean expanded_;
	
	public static void initialize(double windowWidth, double windowHeight)
	{
		elements_ = new SidebarItem[MAX_ELEMS];
		elementPos_ = new double[MAX_ELEMS];
		xPos_ = windowWidth;
		windowWidth_ = windowWidth;
		windowHeight_ = windowHeight;
		expanded_ = false;
		heightFilled_ = VERTICAL_SPACING;
	}
	
	public static void update(long delta)
	{
		if(expanded_)
		{
			xPos_ -= (((xPos_ - (windowWidth_ - WIDTH)) * delta) / 100);
		}
		else
		{
			xPos_ += (((windowWidth_ - xPos_) * delta) / 100);
		}
		for(int i = 0; i < MAX_ELEMS; i++)
		{
			if(elements_[i] != null)
			{
				elements_[i].update(delta);
			}
		}
	}
	
	public static void render()
	{
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glColor3f(0.5f, 0.5f, 0.5f);
		GL11.glVertex2d(xPos_, 0);
		GL11.glVertex2d(windowWidth_, 0);
		GL11.glVertex2d(windowWidth_, windowHeight_);
		GL11.glVertex2d(xPos_, windowHeight_);
		GL11.glEnd();
		
		for(int i = 0; i < MAX_ELEMS; i ++)
		{
			if(elements_[i] != null)
			{
				elements_[i].render(xPos_ + HORIZONTAL_SPACING, elementPos_[i]);
			}
		}
	}
	
	public static void handleClick(int x, int y)
	{
		for(int i = 0; i < MAX_ELEMS; i++)
		{
			if((elements_[i] != null) && (y >= (elementPos_[i])))
			{
				elements_[i].handleClick((int)(x - xPos_), (int)(y - elementPos_[i]));
				return;
			}
		}
	}
	
	public static void toggleExpanded()
	{
		expanded_ = !expanded_;
	}
	
	public static int addSidebarItem(SidebarItem e)
	{
		if(((heightFilled_ + e.getHeight() + VERTICAL_SPACING) > windowHeight_) ||
				(((2 * HORIZONTAL_SPACING) + e.getWidth()) > WIDTH))
		{
			return -1;
		}
		for(int i = 0; i < MAX_ELEMS; i++)
		{
			if(elements_[i] == null)
			{
				elements_[i] = e;
				elementPos_[i] = (windowHeight_ - heightFilled_) - e.getHeight();
				heightFilled_ += (e.getHeight() + VERTICAL_SPACING);
				return i;
			}
		}
		return -1;
	}
	
	public static double getXPos()
	{
		return xPos_;
	}
	
	public static SidebarItem getSidebarItem(int i)
	{
		if(i < MAX_ELEMS)
		{
			return elements_[i];
		}
		return null;
	}
	
	public static void clearSidebar()
	{
		elements_ = new SidebarItem[MAX_ELEMS];
		elementPos_ = new double[MAX_ELEMS];
		heightFilled_ = 0;
	}
	
	public interface SidebarItem
	{
		public void update(long delta);
		
		public void render(double xPos, double yPos);
		
		public void handleClick(int x, int y);
		
		public double getWidth();
		
		public double getHeight();
		
		@Override
		public String toString();
	}
}
