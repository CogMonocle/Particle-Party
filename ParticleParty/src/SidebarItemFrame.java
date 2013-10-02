import org.lwjgl.opengl.GL11;


public class SidebarItemFrame
{
	public static final int MAX_ELEMS = 16;
	
	private SidebarItem[] elements;
	public final String name;
	private double heightFilled;
	private double[] elementPos;
	
	public SidebarItemFrame(String n)
	{
		name = n;
		elementPos = new double[MAX_ELEMS];
		heightFilled = Sidebar.VERTICAL_SPACING;
		elements = new SidebarItem[MAX_ELEMS];
	}
	
	public void update(long delta)
	{
		if(Sidebar.expanded_)
		{
			Sidebar.xPos_ -= (((Sidebar.xPos_ - (Sidebar.windowWidth_ - Sidebar.WIDTH)) * delta) / 100);
		}
		else
		{
			Sidebar.xPos_ += (((Sidebar.windowWidth_ - Sidebar.xPos_) * delta) / 100);
		}
		for(int i = 0; i < Sidebar.MAX_ELEMS; i++)
		{
			if(elements[i] != null)
			{
				elements[i].update(delta);
			}
		}
	}
	
	public void render()
	{
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glColor3f(0.5f, 0.5f, 0.5f);
		GL11.glVertex2d(Sidebar.xPos_, 0);
		GL11.glVertex2d(Sidebar.windowWidth_, 0);
		GL11.glVertex2d(Sidebar.windowWidth_, Sidebar.windowHeight_);
		GL11.glVertex2d(Sidebar.xPos_, Sidebar.windowHeight_);
		GL11.glEnd();
		
		for(int i = 0; i < Sidebar.MAX_ELEMS; i ++)
		{
			if(elements[i] != null)
			{
				elements[i].render(Sidebar.xPos_ + Sidebar.HORIZONTAL_SPACING, elementPos[i]);
			}
		}
	}
	
	public void handleClick(int x, int y)
	{
		for(int i = 0; i < Sidebar.MAX_ELEMS; i++)
		{
			if((elements[i] != null) && (y >= (elementPos[i])))
			{
				elements[i].handleClick((int)(x - Sidebar.xPos_), (int)(y - elementPos[i]));
				return;
			}
		}
	}
	
	public int addSidebarItem(SidebarItem e)
	{
		if(((heightFilled + e.getHeight() + Sidebar.VERTICAL_SPACING) > Sidebar.windowHeight_) ||
				(((2 * Sidebar.HORIZONTAL_SPACING) + e.getWidth()) > Sidebar.WIDTH))
		{
			return -1;
		}
		for(int i = 0; i < Sidebar.MAX_ELEMS; i++)
		{
			if(elements[i] == null)
			{
				elements[i] = e;
				elementPos[i] = (Sidebar.windowHeight_ - heightFilled) - e.getHeight();
				heightFilled += (e.getHeight() + Sidebar.VERTICAL_SPACING);
				return i;
			}
		}
		return -1;
	}
	
	public SidebarItem getSidebarItem(int i)
	{
		if(i < Sidebar.MAX_ELEMS)
		{
			return elements[i];
		}
		return null;
	}
	
	public void clearSidebar()
	{
		elements = new SidebarItem[Sidebar.MAX_ELEMS];
		elementPos = new double[Sidebar.MAX_ELEMS];
		heightFilled = Sidebar.VERTICAL_SPACING;
	}
}