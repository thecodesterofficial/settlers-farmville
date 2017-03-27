package core;

public class Hex {
	public enum HexType{
		LUMBER, SHEEP, BRICK, STONE, WHEAT, SAND;
	}
	
	private int x;
	private int y;
	
	
	HexType type = null;
	public Hex(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	public void SetType(HexType type)
	{
		this.type = type;
	}
	public HexType GetType()
	{
		return type;
	}
	public void SetX(int x)
	{
		this.x = x;
	}
	public void SetY(int y)
	{
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
		if(other instanceof Hex)
		{
			Hex hex = (Hex)other;
			return this.x == hex.x && this.y == hex.y;
		}
		return false;
		
	}
}