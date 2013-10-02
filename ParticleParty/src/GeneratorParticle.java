
public abstract class GeneratorParticle 
{
	protected RendererParticle particles_;
	
	public GeneratorParticle(RendererParticle p)
	{
		particles_ = p;
	}
	
	public void clear()
	{
		particles_.clear();
	}
	
	public abstract void render();
	
	public abstract void update(long delta);
	
	public abstract void mouseInput(int x, int y, int dX, int dY, boolean lmbDown, boolean rmbDown);
	
	public abstract void setColorPickers(boolean[] pickers);
	
	public abstract void setColorRules(boolean[] rules);
	
	public abstract void setSizeRules(boolean[] rules);
	
	public abstract void setPosRules(boolean[] rules);
	
	public abstract GeneratorParameters getInfo();
}
