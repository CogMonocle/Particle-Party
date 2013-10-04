
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

public class ParticleParty
{
	
	public static final int CIRCLE_RADIUS = 40;
	public static final int MAX_PARTICLE_GENS = 16;
	public static final int DEFAULT_GEN = 0;
	
	private GeneratorParticle[] gens;
	private String[] genNames;
	private RendererParticle.ColorPicker[] colPickers;
	private String[] colPickerNames;
	private RendererParticle.ColorRule[] cols;
	private String[] colNames;
	private RendererParticle.SizeRule[] sizs;
	private String[] sizNames;
	private RendererParticle.PosRule[] poss;
	private String[] posNames;
	
	
	private RendererParticle particles;
	private RendererConnected burstParticles;
	
	private int frameNumber;
	private SidebarItemSelectBox selGen;
	private SidebarItemSelectBox selColPicker;
	private SidebarItemSelectBox selCol;
	private SidebarItemSelectBox selSiz;
	private SidebarItemSelectBox selPos;
	
	private long delta;
	private long lastFrame;
	
	private final int width = 900;
	private final int height = 800;
	private int activeGen = -1;
	
	private boolean closeRequested;
	
	public void start()
	{
		try
		{
			Display.setDisplayMode(new DisplayMode(width,height));
			Display.create();
		} catch (LWJGLException e)
		{
			e.printStackTrace();
			System.exit(0);
		}
		closeRequested = false;
		//init lastFrame
		getDelta();
		// init OpenGL
		initOpenGL();
		// init managers
		particles = new RendererOctogonParticle();
		burstParticles = new RendererConnected();
		Sidebar.initialize(width, height);
		initGens();
		initColPicks();
		initCols();
		initSizs();
		initPoss();
		buildSidebar();
		
		while (!closeRequested)
		{
			delta = getDelta();
			pollInput();
			updateLogic();
			render();
		}
		Display.destroy();
	}

	private void buildSidebar()
	{
		frameNumber = Sidebar.addFrame("Main");
		Sidebar.addSidebarItem(new SidebarItemTitle("Generators"), frameNumber);
		selGen = new SidebarItemSelectBox(genNames, DEFAULT_GEN);
		Sidebar.addSidebarItem(selGen, frameNumber);
		Sidebar.addSidebarItem(new SidebarItemTitle("Color Pickers"), frameNumber);
		selColPicker = new SidebarItemSelectBox(colPickerNames, true);
		Sidebar.addSidebarItem(selColPicker, frameNumber);
		Sidebar.addSidebarItem(new SidebarItemTitle("Color Rules"), frameNumber);
		selCol = new SidebarItemSelectBox(colNames, true);
		Sidebar.addSidebarItem(selCol, frameNumber);
		Sidebar.addSidebarItem(new SidebarItemTitle("Size Rules"), frameNumber);
		selSiz = new SidebarItemSelectBox(sizNames, true);
		Sidebar.addSidebarItem(selSiz, frameNumber);
		Sidebar.addSidebarItem(new SidebarItemTitle("Position Rules"), frameNumber);
		selPos = new SidebarItemSelectBox(posNames, true);
		Sidebar.addSidebarItem(selPos, frameNumber);
		Sidebar.setFrame(frameNumber);
	}

	private void initPoss()
	{
		poss = new RendererParticle.PosRule[RendererParticle.MAX_RULES];
		RendererParticle.addPRule(new PosRuleBurst());
		RendererParticle.addPRule(new PosRuleFountain());
		RendererParticle.addPRule(new PosRuleSpinRise());
		RendererParticle.addPRule(new PosRuleSpinWiggleRise());
		RendererParticle.addPRule(new PosRuleSpiral());
		RendererParticle.addPRule(new PosRuleImpact());
		RendererParticle.addPRule(new PosRuleSpinWiggleFall());
		posNames = new String[RendererParticle.MAX_RULES];
		for(int i = 0; i < RendererParticle.MAX_RULES; i++)
		{
			poss[i] = RendererParticle.getPRule(i);
			if(poss[i] != null)
			{
				posNames[i] = poss[i].name();
			}
		}
	}

	private void initSizs()
	{
		sizs = new RendererParticle.SizeRule[RendererParticle.MAX_RULES];
		RendererParticle.addSRule(new SizeRulePulse());
		RendererParticle.addSRule(new SizeRuleShrink());
		RendererParticle.addSRule(new SizeRuleGrow());
		sizNames = new String[RendererParticle.MAX_RULES];
		for(int i = 0; i < RendererParticle.MAX_RULES; i++)
		{
			sizs[i] = RendererParticle.getSRule(i);
			if(sizs[i] != null)
			{
				sizNames[i] = sizs[i].name();
			}
		}
	}

	private void initCols() 
	{
		cols = new RendererParticle.ColorRule[RendererParticle.MAX_RULES];
		RendererParticle.addCRule(new ColorRuleFadeOut());
		RendererParticle.addCRule(new ColorRuleBrightenFade());
		RendererParticle.addCRule(new ColorRuleDarkenFade());
		RendererParticle.addCRule(new ColorRuleFadeInOut());
		colNames = new String[RendererParticle.MAX_RULES];
		for(int i = 0; i < RendererParticle.MAX_RULES; i++)
		{
			cols[i] = RendererParticle.getCRule(i);
			if(cols[i] != null)
			{
				colNames[i] = cols[i].name();
			}
		}
	}
	
	private void initColPicks()
	{
		colPickers = new RendererParticle.ColorPicker[RendererParticle.MAX_RULES];
		RendererParticle.addCPicker(new ColorPickerRandom());
		RendererParticle.addCPicker(new ColorPickerBrights());
		RendererParticle.addCPicker(new ColorPickerReds());
		RendererParticle.addCPicker(new ColorPickerPurples());
		RendererParticle.addCPicker(new ColorPickerBlues());
		RendererParticle.addCPicker(new ColorPickerRadioactive());
		colPickerNames = new String[RendererParticle.MAX_RULES];
		for(int i = 0; i < RendererParticle.MAX_RULES; i++)
		{
			colPickers[i] = RendererParticle.getCPicker(i);
			if(colPickers[i] != null)
			{
				colPickerNames[i] = colPickers[i].name();
			}
		}
	}

	private void initGens()
	{
		gens = new GeneratorParticle[MAX_PARTICLE_GENS];
		addGen(new GeneratorMouse(particles));
		addGen(new GeneratorCircle(width / 2, 0, CIRCLE_RADIUS, particles));
		addGen(new GeneratorFireworks(particles, burstParticles));
		genNames = new String[MAX_PARTICLE_GENS];
		for(int i = 0; i < MAX_PARTICLE_GENS; i++)
		{
			if(gens[i] != null)
			{
				genNames[i] = gens[i].getInfo().getName();
			}
		}
		activeGen = DEFAULT_GEN;
	}
	
	private void pollInput()
	{
		if((Mouse.getX() > Sidebar.getXPos()) && Mouse.isButtonDown(0))
		{
			Sidebar.handleClick(Mouse.getX(), Mouse.getY());
		}
		else
		{
			gens[activeGen].mouseInput(Mouse.getX(), Mouse.getY(), Mouse.getDX(), Mouse.getDY(), Mouse.isButtonDown(0), Mouse.isButtonDown(1));
		}
		while (Keyboard.next())
		{
			if (Keyboard.getEventKeyState())
			{
				int key = Keyboard.getEventKey();
				if (key == Keyboard.KEY_ESCAPE)
				{
					closeRequested = true;
				}
				else if (key == Keyboard.KEY_TAB)
				{
					Sidebar.toggleExpanded();
				}
				else if (key == Keyboard.KEY_C)
				{
					clearActiveGen();
				}
			}
		}
		if(Display.isCloseRequested())
		{
			closeRequested = true;
		}
	}
	
	/**
	 * Updates the sidebar, checks for changes in the options in the sidebar and applies them.
	 * Then updates the update logic for the particle renderers.
	 */
	private void updateLogic()
	{
		Sidebar.update(delta);
		int newGen = selGen.getSelection();
		if(activeGen != newGen)
		{
			clearActiveGen();
			activeGen = newGen;
		}
		gens[activeGen].setColorPickers(makeValid(selColPicker.getMultiSelection()));
		gens[activeGen].setColorRules(makeValid(selCol.getMultiSelection()));
		gens[activeGen].setSizeRules(makeValid(selSiz.getMultiSelection()));
		gens[activeGen].setPosRules(makeValid(selPos.getMultiSelection()));
		gens[activeGen].update(delta);
		burstParticles.update(delta);
		particles.update(delta);
	}
	
	/**
	 * Clears the buffers, renders the particles, the generator, then the sidebar
	 */
	private void render()
	{
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		renderBackground();
		particles.render();
		burstParticles.render();
		gens[activeGen].render();
		Sidebar.render();
		Display.update();
	}
	
	private void renderBackground()
	{
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glColor3f(0.55f, 0.55f, 0.6f);
		GL11.glVertex2d(0, 0);
		GL11.glVertex2d(width, 0);
		GL11.glColor3f(0.1f, 0.1f, 0.15f);
		GL11.glVertex2d(width, height);
		GL11.glVertex2d(0, height);
		GL11.glEnd();
	}
	
	/**
	 * Initialize the OpenGL matrix + color handling
	 */
	private void initOpenGL()
	{
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, width, 0, height, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glDepthFunc(GL11.GL_NEVER);
	}
	
	/**
	 * @param p - a GeneratorParticle object to be added
	 * @return The id assigned to the generator passed
	 */
	public int addGen(GeneratorParticle p)
	{
		for(int i = 0; i < MAX_PARTICLE_GENS; i++)
		{
			if(gens[i] == null)
			{
				gens[i] = p;
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * @return The time in ticks since getDelta() was last run.
	 *  Undefined for the first time it's run.
	 */
	public int getDelta()
	{
	    long time = getTime();
	    int delta = (int) (time - lastFrame);
	    lastFrame = time;
	    	
	    return delta;
	}
	
	/**
	 * @return The system time in milliseconds
	 */
	public long getTime()
	{
	    return System.nanoTime() / 1000000;
	}
	
	/**
	 * Clears all particles currently on screen.
	 */
	public void clearActiveGen()
	{
		gens[activeGen].clear();
	}
	
	/**
	 * @param rules - A list of which rules are active
	 * @return The list, modified so that if no rules were previously
	 * active, at the very least, the default rule is now active.
	 */
	public boolean[] makeValid(boolean[] rules)
	{
		boolean someTrue = false;
		for(boolean b: rules)
		{
			if(b)
			{
				someTrue = true;
			}
		}
		if(!someTrue)
		{
			rules = new boolean[rules.length];
			rules[0] = true;
		}
		return rules;
	}
	
	public static void main(String[] argv)
	{		
		//Un-comment for exporting
//		System.setProperty("org.lwjgl.librarypath", System.getProperty("user.dir") + "/natives/");
		ParticleParty ParticleDisplay = new ParticleParty();
		ParticleDisplay.start();
	}
}
