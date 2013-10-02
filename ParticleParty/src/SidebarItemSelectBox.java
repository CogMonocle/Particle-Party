import org.lwjgl.opengl.GL11;


public class SidebarItemSelectBox implements SidebarItem
{
	protected String[] opts_;
	protected boolean[] selections_;
	protected int numOpts_;
	protected int position_;
	protected int selection_;
	protected boolean clickedThisTick;
	protected boolean clickedLastTick;
	protected boolean multiSelect_;
	
	public SidebarItemSelectBox(String[] opts, boolean multi)
	{
		opts_ = opts;
		for(numOpts_ = 0; numOpts_ < opts.length && opts[numOpts_] != null; numOpts_++);
		position_ = 0;
		selection_ = -1;
		clickedThisTick = false;
		clickedLastTick = false;
		multiSelect_ = multi;
		selections_ = new boolean[numOpts_];
	}
	
	public SidebarItemSelectBox(String[] opts, int def)
	{
		this(opts, false);
		selection_ = def;
	}
	
	public SidebarItemSelectBox(String[] opts, boolean[] def)
	{
		this(opts, true);
		selections_ = def;
	}

	@Override
	public void update(long delta)
	{
		clickedLastTick = clickedThisTick;
		clickedThisTick = false;
	}

	@Override
	public void render(double xPos, double yPos)
	{
		// Background
		GL11.glColor3f(0.2f, 0.2f, 0.2f);
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex2d(xPos, yPos);
		GL11.glVertex2d(xPos + getWidth(), yPos);
		GL11.glVertex2d(xPos + getWidth(), yPos + getHeight());
		GL11.glVertex2d(xPos, yPos + getHeight());
		GL11.glEnd();
		
		if(numOpts_ > 3)
		{
			for(int i = 0; i < 3; i ++)
			{
				// Option background
				if((!multiSelect_ && selection_ == i + position_) || (multiSelect_ && selections_[i + position_]))
				{
					GL11.glColor3f(0.6f, 0.6f, 0.6f);
				}
				else
				{
					GL11.glColor3f(0.8f, 0.8f, 0.8f);
				}
				GL11.glBegin(GL11.GL_QUADS);
				GL11.glVertex2d(xPos + Sidebar.HORIZONTAL_SPACING,
						yPos + getHeight() - (i + 1)  * (2 * Sidebar.VERTICAL_SPACING + SimpleText.getHeight()));
				GL11.glVertex2d(xPos + getWidth() - 3 * Sidebar.HORIZONTAL_SPACING,
						yPos + getHeight() - (i + 1)  * (2 * Sidebar.VERTICAL_SPACING + SimpleText.getHeight()));
				GL11.glVertex2d(xPos + getWidth() - 3 * Sidebar.HORIZONTAL_SPACING,
						yPos + getHeight() - i  * (2 * Sidebar.VERTICAL_SPACING + SimpleText.getHeight()) - Sidebar.VERTICAL_SPACING);
				GL11.glVertex2d(xPos + Sidebar.HORIZONTAL_SPACING,
						yPos + getHeight() - i  * (2 * Sidebar.VERTICAL_SPACING + SimpleText.getHeight()) - Sidebar.VERTICAL_SPACING);
				GL11.glEnd();
				GL11.glColor3f(0.0f, 0.0f, 0.0f);
				//Option Name
				SimpleText.drawString(opts_[i + position_], (int)(xPos + 2 * Sidebar.HORIZONTAL_SPACING),
						(int)(yPos + getHeight() - (i + 1)  * (2 * Sidebar.VERTICAL_SPACING + SimpleText.getHeight()) + Sidebar.VERTICAL_SPACING / 2));
			}
			
			GL11.glColor3f(0.8f, 0.8f, 0.8f);
			GL11.glBegin(GL11.GL_QUADS);
			
			// Up button
			GL11.glVertex2d(xPos + getWidth() - 2 * Sidebar.HORIZONTAL_SPACING,
					yPos + (getHeight() + Sidebar.VERTICAL_SPACING) / 2);
			GL11.glVertex2d(xPos + getWidth() - Sidebar.HORIZONTAL_SPACING,
					yPos + (getHeight() + Sidebar.VERTICAL_SPACING) / 2);
			GL11.glVertex2d(xPos + getWidth() - Sidebar.HORIZONTAL_SPACING,
					yPos + getHeight() - Sidebar.VERTICAL_SPACING);
			GL11.glVertex2d(xPos + getWidth() - 2 * Sidebar.HORIZONTAL_SPACING,
					yPos + getHeight() - Sidebar.VERTICAL_SPACING);
			
			// Down button
			GL11.glVertex2d(xPos + getWidth() - 2 * Sidebar.HORIZONTAL_SPACING,
					yPos + Sidebar.VERTICAL_SPACING);
			GL11.glVertex2d(xPos + getWidth() - Sidebar.HORIZONTAL_SPACING,
					yPos + Sidebar.VERTICAL_SPACING);
			GL11.glVertex2d(xPos + getWidth() - Sidebar.HORIZONTAL_SPACING,
					yPos + (getHeight() - Sidebar.VERTICAL_SPACING) / 2);
			GL11.glVertex2d(xPos + getWidth() - 2 * Sidebar.HORIZONTAL_SPACING,
					yPos + (getHeight() - Sidebar.VERTICAL_SPACING) / 2);
			
			GL11.glEnd();
			
			GL11.glBegin(GL11.GL_TRIANGLES);
			
			// Up arrow
			if(position_ == 0)
			{
				GL11.glColor3f(0.6f, 0.6f, 0.6f);
			}
			else
			{
				GL11.glColor3f(0.0f, 0.0f, 0.0f);
			}
			GL11.glVertex2d(xPos + getWidth() - 3.5 * Sidebar.HORIZONTAL_SPACING / 3,
					yPos + (getHeight() + 3 * Sidebar.VERTICAL_SPACING) / 2);
			GL11.glVertex2d(xPos + getWidth() - 5.5 * Sidebar.HORIZONTAL_SPACING / 3,
					yPos + (getHeight() + 3 * Sidebar.VERTICAL_SPACING) / 2);
			GL11.glVertex2d(xPos + getWidth() - 3 * Sidebar.HORIZONTAL_SPACING / 2,
					yPos + getHeight() - 2 * Sidebar.VERTICAL_SPACING);
			
			// Down arrow
			if(position_ == numOpts_ - 3)
			{
				GL11.glColor3f(0.6f, 0.6f, 0.6f);
			}
			else
			{
				GL11.glColor3f(0.0f, 0.0f, 0.0f);
			}
			GL11.glVertex2d(xPos + getWidth() - 3 * Sidebar.HORIZONTAL_SPACING / 2,
					yPos + 2 * Sidebar.VERTICAL_SPACING);
			GL11.glVertex2d(xPos + getWidth() - 5.5 * Sidebar.HORIZONTAL_SPACING / 3,
					yPos + (getHeight() - 3 * Sidebar.VERTICAL_SPACING) / 2);
			GL11.glVertex2d(xPos + getWidth() - 3.5 * Sidebar.HORIZONTAL_SPACING / 3,
					yPos + (getHeight() - 3 * Sidebar.VERTICAL_SPACING) / 2);
			
			GL11.glEnd();
		}
		else
		{
			for(int i = 0; i < numOpts_; i ++)
			{
				// Option background
				if((!multiSelect_ && selection_ == i) || (multiSelect_ && selections_[i]))
				{
					GL11.glColor3f(0.6f, 0.6f, 0.6f);
				}
				else
				{
					GL11.glColor3f(0.8f, 0.8f, 0.8f);
				}
				GL11.glBegin(GL11.GL_QUADS);
				GL11.glVertex2d(xPos + Sidebar.HORIZONTAL_SPACING,
						yPos + getHeight() - (i + 1)  * (2 * Sidebar.VERTICAL_SPACING + SimpleText.getHeight()));
				GL11.glVertex2d(xPos + getWidth() - Sidebar.HORIZONTAL_SPACING,
						yPos + getHeight() - (i + 1)  * (2 * Sidebar.VERTICAL_SPACING + SimpleText.getHeight()));
				GL11.glVertex2d(xPos + getWidth() - Sidebar.HORIZONTAL_SPACING,
						yPos + getHeight() - i  * (2 * Sidebar.VERTICAL_SPACING + SimpleText.getHeight()) - Sidebar.VERTICAL_SPACING);
				GL11.glVertex2d(xPos + Sidebar.HORIZONTAL_SPACING,
						yPos + getHeight() - i  * (2 * Sidebar.VERTICAL_SPACING + SimpleText.getHeight()) - Sidebar.VERTICAL_SPACING);
				GL11.glEnd();
				GL11.glColor3f(0.0f, 0.0f, 0.0f);
				//Option Name
				SimpleText.drawString(opts_[i], (int)(xPos + 2 * Sidebar.HORIZONTAL_SPACING),
						(int)(yPos + getHeight() - (i + 1)  * (2 * Sidebar.VERTICAL_SPACING + SimpleText.getHeight()) + Sidebar.VERTICAL_SPACING / 2));
			}
		}
	}

	@Override
	public void handleClick(int x, int y)
	{
		clickedThisTick = true;
		if(clickedLastTick)
		{
			return;
		}
		if(numOpts_ > 3)
		{
			if(x > 2 * Sidebar.HORIZONTAL_SPACING && x < getWidth() - 2 * Sidebar.HORIZONTAL_SPACING)
			{
				for(int i = 0; i < 3; i++)
				{
					if(y > getHeight() - (i + 1)  * (2 * Sidebar.VERTICAL_SPACING + SimpleText.getHeight()) &&
							y < getHeight() - i  * (2 * Sidebar.VERTICAL_SPACING + SimpleText.getHeight()) - Sidebar.VERTICAL_SPACING)
					{
						selections_[i + position_] = !selections_[i + position_];
						selection_ = i + position_;
						return;
					}
				}
			}
				
			if(x > getWidth() - Sidebar.HORIZONTAL_SPACING && x < getWidth())
			{
				if(y > (getHeight() + Sidebar.VERTICAL_SPACING) / 2 && y < getHeight() - Sidebar.VERTICAL_SPACING)
				{
					if(position_ > 0)
					{
						position_--;
					}
					return;
				}
				
				if(y > Sidebar.VERTICAL_SPACING && y < (getHeight() - Sidebar.VERTICAL_SPACING) / 2)
				{
					if(position_ < (numOpts_ - 3))
					{
						position_++;
					}
					return;
				}
			}
		}
		else if(x > Sidebar.HORIZONTAL_SPACING && x < getWidth() - Sidebar.HORIZONTAL_SPACING)
		{
			for(int i = 0; i < numOpts_; i++)
			{
				if(y > getHeight() - (i + 1)  * (2 * Sidebar.VERTICAL_SPACING + SimpleText.getHeight()) &&
						y < getHeight() - i  * (2 * Sidebar.VERTICAL_SPACING + SimpleText.getHeight()) - Sidebar.VERTICAL_SPACING)
				{
					selections_[i] = !selections_[i];
					selection_ = i;
					return;
				}
			}
		}
	}
	

	@Override
	public double getWidth()
	{
		return Sidebar.WIDTH - (2 * Sidebar.HORIZONTAL_SPACING);
	}

	@Override
	public double getHeight()
	{
		return (Sidebar.VERTICAL_SPACING * 7) + (SimpleText.getHeight() * 3);
	}
	
	public int getSelection()
	{
		return selection_;
	}
	
	public boolean[] getMultiSelection()
	{
		return selections_;
	}
	
}
