import org.lwjgl.opengl.GL11;


public class ElementText implements Sidebar.SidebarItem
{
	@Override
	public void update(long delta)
	{

	}

	@Override
	public void render(double xPos, double yPos)
	{
		GL11.glColor3f(0, 0, 0);
		SimpleText.drawString("Amplitude and Frequency", (int)xPos, (int)yPos);
	}

	@Override
	public void handleClick(int x, int y)
	{
		
	}

	@Override
	public double getWidth()
	{
		return Sidebar.WIDTH;
	}

	@Override
	public double getHeight()
	{
		return 20;
	}
}
