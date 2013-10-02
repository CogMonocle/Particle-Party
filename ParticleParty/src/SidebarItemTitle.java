import org.lwjgl.opengl.GL11;


public class SidebarItemTitle implements Sidebar.SidebarItem
{
	private String title;
	
	public SidebarItemTitle(String text)
	{
		title = text;
	}
	
	@Override
	public void update(long delta)
	{
		
	}

	@Override
	public void render(double xPos, double yPos)
	{
		GL11.glColor3f(0.2f, 0.2f, 0.2f);
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex2d(xPos, yPos);
		GL11.glVertex2d(xPos + getWidth(), yPos);
		GL11.glVertex2d(xPos + getWidth(), yPos + getHeight());
		GL11.glVertex2d(xPos, yPos + getHeight());
		GL11.glEnd();
		GL11.glColor3f(0.8f, 0.8f, 0.8f);
		SimpleText.drawString(title, (int)(xPos + Sidebar.HORIZONTAL_SPACING), (int)(yPos + Sidebar.VERTICAL_SPACING / 2));
	}

	@Override
	public void handleClick(int x, int y)
	{
		
	}

	@Override
	public double getWidth()
	{
		return Sidebar.WIDTH - 2 * Sidebar.HORIZONTAL_SPACING;
	}

	@Override
	public double getHeight()
	{
		return Sidebar.VERTICAL_SPACING + SimpleText.getHeight();
	}
	
	@Override
	public String toString()
	{
		return "Width: " + getWidth() + ", Height: " + getHeight();
	}
}
