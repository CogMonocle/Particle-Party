import org.lwjgl.opengl.GL11;


public class RendererOctogonParticle extends RendererSquareParticle
{
	@Override
	public void render()
	{
		double[] pos;
		float[] color;
		double size;
		double angle;
		int numVerts_ = 8;
		GL11.glBegin(GL11.GL_TRIANGLES);
		for(int i = 0; i < MAX_PARTICLES; i++)
		{
			color = getCRule(cID_[i]).getColor(r_[i], g_[i], b_[i], a_[i], age_[i], props_[i]);
			size = getSRule(sID_[i]).getSize(size_[i], age_[i], props_[i]);
			pos = getPRule(pID_[i]).getPos(xCoord_[i], yCoord_[i], angle_[i], age_[i], props_[i]);
			if(size != 0 && color[3] > 0)
			{
				for(int j = 0; j < numVerts_; j++)
				{
					GL11.glColor4f(color[0], color[1], color[2], color[3]);
					angle = j * 2 * Math.PI / numVerts_;
					GL11.glVertex2d(pos[0] + (Math.cos(angle) * size), pos[1] + (Math.sin(angle) * size));
					angle = (j + 1) * 2 * Math.PI / numVerts_;
					GL11.glVertex2d(pos[0] + (Math.cos(angle) * size), pos[1] + (Math.sin(angle) * size));
					GL11.glVertex2d(pos[0], pos[1]);
				}
			}
		}
		GL11.glEnd();
	}
}
