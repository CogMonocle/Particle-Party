
public class ParticleParameters {
	private double xC;
	private double yC;
	private double ang;
	private float r;
	private float g;
	private float b;
	private float a;
	private double s;
	private int cID;
	private int sID;
	private int pID;
	private ExtraProperties p;

	public ParticleParameters(double xC, double yC, double ang, float r,
			float g, float b, float a, double s, int cID, int sID, int pID,
			ExtraProperties p) {
		this.xC = xC;
		this.yC = yC;
		this.ang = ang;
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;
		this.s = s;
		this.cID = cID;
		this.sID = sID;
		this.pID = pID;
		this.p = p;
	}

	public double getxC() {
		return xC;
	}

	public void setxC(double xC) {
		this.xC = xC;
	}

	public double getyC() {
		return yC;
	}

	public void setyC(double yC) {
		this.yC = yC;
	}

	public double getAng() {
		return ang;
	}

	public void setAng(double ang) {
		this.ang = ang;
	}

	public float getR() {
		return r;
	}

	public void setR(float r) {
		this.r = r;
	}

	public float getG() {
		return g;
	}

	public void setG(float g) {
		this.g = g;
	}

	public float getB() {
		return b;
	}

	public void setB(float b) {
		this.b = b;
	}

	public float getA() {
		return a;
	}

	public void setA(float a) {
		this.a = a;
	}

	public double getS() {
		return s;
	}

	public void setS(double s) {
		this.s = s;
	}

	public int getcID() {
		return cID;
	}

	public void setcID(int cID) {
		this.cID = cID;
	}

	public int getsID() {
		return sID;
	}

	public void setsID(int sID) {
		this.sID = sID;
	}

	public int getpID() {
		return pID;
	}

	public void setpID(int pID) {
		this.pID = pID;
	}

	public ExtraProperties getP() {
		return p;
	}

	public void setP(ExtraProperties p) {
		this.p = p;
	}
}