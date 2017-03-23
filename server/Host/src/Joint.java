
public class Joint {
	private int x;
	private int y;
	public Joint(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	public int GetX()
	{
		return x;
	}
	public int GetY()
	{
		return y;
	}
	@Override
	public boolean equals(Object other)
	{
		if(other instanceof Joint)
		{
			Joint joint = (Joint)other;
			return this.x == joint.x && joint.y == joint.y;
		}
		return false;
		
	}
}
