import org.lwjgl.opengl.GL11;


public class RendererSquareParticle extends RendererParticle
{	
	public RendererSquareParticle()
	{
		super();
	}
	
	@Override
	public int addParticle(ParticleParameters parameterObject)
	{
		for(int i = 0; i < MAX_PARTICLES; i++)
		{
			float[] color = getCRule(cID_[i]).getColor(r_[i], g_[i], b_[i], a_[i], age_[i], null);
			double size = getSRule(sID_[i]).getSize(size_[i], age_[i], props_[i]);
			if(color[3] <= 0.0f || size <= 0)
			{
				xCoord_[i] = parameterObject.getxC();
				yCoord_[i] = parameterObject.getyC();
				angle_[i] = parameterObject.getAng();
				r_[i] = parameterObject.getR();
				b_[i] = parameterObject.getB();
				g_[i] = parameterObject.getG();
				a_[i] = parameterObject.getA();
				size_[i] = parameterObject.getS();
				age_[i] = 0;
				cID_[i] = parameterObject.getcID();
				sID_[i] = parameterObject.getsID();
				pID_[i] = parameterObject.getpID();
				props_[i] = parameterObject.getP();
				return i;
			}
		}
		return -1;
	}
	
	@Override
	public void render()
	{
		double[] pos;
		float[] color;
		double size;
		GL11.glBegin(GL11.GL_QUADS);
		for(int i = 0; i < MAX_PARTICLES; i++)
		{
			color = getCRule(cID_[i]).getColor(r_[i], g_[i], b_[i], a_[i], age_[i], props_[i]);
			size = getSRule(sID_[i]).getSize(size_[i], age_[i], props_[i]);
			pos = getPRule(pID_[i]).getPos(xCoord_[i], yCoord_[i], angle_[i], age_[i], props_[i]);
			
			if(size > 0 && color[3] > 0)
			{	
				GL11.glColor4f(color[0], color[1], color[2], color[3]);
				GL11.glVertex2d(pos[0] + (Math.cos(pos[2] + 1 * Math.PI / 4) * size), pos[1] + (Math.sin(pos[2] + 1 * Math.PI / 4) * size));
				GL11.glVertex2d(pos[0] + (Math.cos(pos[2] + 3 * Math.PI / 4) * size), pos[1] + (Math.sin(pos[2] + 3 * Math.PI / 4) * size));
				GL11.glVertex2d(pos[0] + (Math.cos(pos[2] + 5 * Math.PI / 4) * size), pos[1] + (Math.sin(pos[2] + 5 * Math.PI / 4) * size));
				GL11.glVertex2d(pos[0] + (Math.cos(pos[2] + 7 * Math.PI / 4) * size), pos[1] + (Math.sin(pos[2] + 7 * Math.PI / 4) * size));
			}
		}
		GL11.glEnd();
	}
}
