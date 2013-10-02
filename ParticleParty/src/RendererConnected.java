import org.lwjgl.opengl.GL11;


public class RendererConnected extends RendererSquareParticle
{
	@Override
	public void render()
	{
		double[] pos;
		float[] color;
		double size;
		double xShift;
		double yShift;
		GL11.glBegin(GL11.GL_TRIANGLES);
		for(int i = 0; i < MAX_PARTICLES; i++)
		{
			color = getCRule(cID_[i]).getColor(r_[i], g_[i], b_[i], a_[i], age_[i], props_[i]);
			size = getSRule(sID_[i]).getSize(size_[i], age_[i], props_[i]);
			pos = getPRule(pID_[i]).getPos(xCoord_[i], yCoord_[i], angle_[i], age_[i], props_[i]);
			
			xShift = Math.sin(pos[2]) * size;
			yShift = Math.cos(pos[2]) * size;
			
			GL11.glColor4f(color[0], color[1], color[2], color[3]);
			GL11.glVertex2d(xCoord_[i], yCoord_[i]);
			GL11.glVertex2d(pos[0] - xShift, pos[1] + yShift);
			GL11.glVertex2d(pos[0] + xShift, pos[1] - yShift);
			
		}
		GL11.glEnd();
	}
}
