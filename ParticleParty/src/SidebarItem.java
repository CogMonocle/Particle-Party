
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