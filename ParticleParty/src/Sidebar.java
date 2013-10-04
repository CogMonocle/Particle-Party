import org.lwjgl.opengl.GL11;



public class Sidebar
{
	public static final double WIDTH = 200;
	public static final double VERTICAL_SPACING = 10;
	public static final double HORIZONTAL_SPACING = 10;
	public static final int MAX_ELEMS = 16;
	
	private static SidebarItemFrame[] elements_;
	private static int curFrame_;
	
	static double xPos_;
	static double windowHeight_;
	static double windowWidth_;
	
	static boolean expanded_;
	
	public static void initialize(double windowWidth, double windowHeight)
	{
		elements_ = new SidebarItemFrame[MAX_ELEMS];
		xPos_ = windowWidth;
		windowWidth_ = windowWidth;
		windowHeight_ = windowHeight;
		expanded_ = false;
		curFrame_ = 0;
	}
	
	public static void update(long delta)
	{
		elements_[curFrame_].update(delta);
	}
	
	public static void handleClick(int x, int y)
	{
		elements_[curFrame_].handleClick(x, y);
	}
	
	public static void render()
	{
		elements_[curFrame_].render();
		
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glColor4f(0.2f, 0.2f, 0.2f, 0.5f);
		GL11.glVertex2d(Sidebar.xPos_, 0);
		GL11.glVertex2d(Sidebar.windowWidth_, 0);
		GL11.glVertex2d(Sidebar.windowWidth_, Sidebar.windowHeight_);
		GL11.glVertex2d(Sidebar.xPos_, Sidebar.windowHeight_);
		GL11.glEnd();
	}
	
	public static int addFrame(String name)
	{
		for(int i = 0; i < MAX_ELEMS; i++)
		{
			if(elements_[i] == null)
			{
				elements_[i] = new SidebarItemFrame(name);
				return i;
			}
		}
		return -1;
	}
	
	public static int addSidebarItem(SidebarItem s, int frame)
	{
		return elements_[frame].addSidebarItem(s);
	}
	
	public static void setFrame(int frame)
	{
		curFrame_ = frame;
	}
	
	public static void toggleExpanded()
	{
		expanded_ = !expanded_;
	}
	
	public static double getXPos()
	{
		return xPos_;
	}
}
