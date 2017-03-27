package core;

public class Joint {
	public enum StructureType{
		None,
		Settlement,
		City
	}
	private int x;
	private int y;
	private String owner;
	private StructureType structure = StructureType.None;
	public Joint(int x, int y)
	{
		this.x = x;
		this.y = y;
		this.owner = "";
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
	public String GetOwner()
	{
		return owner;
	}
	public void SetOwner(String owner)
	{
		this.owner = owner;
	}
	public StructureType GetStructureType()
	{
		return structure;
	}
	public void SetStructureType(StructureType type)
	{
		this.structure = type;
	}
}
