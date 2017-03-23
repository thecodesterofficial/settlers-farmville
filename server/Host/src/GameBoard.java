import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;





public class GameBoard {
	
	private ArrayList<Hex> hexes = new ArrayList<Hex>();
	private ArrayList<Joint> joints = new ArrayList<Joint>();
	private static int NUM_TILES_ROW_ONE = 3;
	private static int NUM_TILES_ROW_TWO = 4;
	private static int NUM_TILES_ROW_THREE = 5;
	private static int NUM_TILES_ROW_FOUR = 4;
	private static int NUM_TILES_ROW_FIVE = 3;
	
	private void InitHexes()
	{
		InitHexLocations();
		InitHexType();
	}
	
	private void InitJoints()
	{
		// ROW 1
		
		joints.add(new Joint(2, 0));
		joints.add(new Joint(3, 0));
		joints.add(new Joint(4, 0));
		joints.add(new Joint(5, 0));
		joints.add(new Joint(6, 0));
		joints.add(new Joint(7, 0));
		joints.add(new Joint(8, 0));
	
		// ROW 2
		joints.add(new Joint(1, 1));
		joints.add(new Joint(2, 1));
		joints.add(new Joint(3, 1));
		joints.add(new Joint(4, 1));
		joints.add(new Joint(5, 1));
		joints.add(new Joint(6, 1));
		joints.add(new Joint(7, 1));
		joints.add(new Joint(8, 1));
		joints.add(new Joint(9, 1));

		// ROW 3
		joints.add(new Joint(0, 2));
		joints.add(new Joint(1, 2));
		joints.add(new Joint(2, 2));
		joints.add(new Joint(3, 2));
		joints.add(new Joint(4, 2));
		joints.add(new Joint(5, 2));
		joints.add(new Joint(6, 2));
		joints.add(new Joint(7, 2));
		joints.add(new Joint(8, 2));
		joints.add(new Joint(9, 2));
		joints.add(new Joint(10, 2));
		
		// ROW 4
		joints.add(new Joint(0, 3));
		joints.add(new Joint(1, 3));
		joints.add(new Joint(2, 3));
		joints.add(new Joint(3, 3));
		joints.add(new Joint(4, 3));
		joints.add(new Joint(5, 3));
		joints.add(new Joint(6, 3));
		joints.add(new Joint(7, 3));
		joints.add(new Joint(8, 3));
		joints.add(new Joint(9, 3));
		joints.add(new Joint(10, 3));
	
		// ROW 5
		joints.add(new Joint(1, 4));
		joints.add(new Joint(2, 4));
		joints.add(new Joint(3, 4));
		joints.add(new Joint(4, 4));
		joints.add(new Joint(5, 4));
		joints.add(new Joint(6, 4));
		joints.add(new Joint(7, 4));
		joints.add(new Joint(8, 4));
		joints.add(new Joint(9, 4));
		// ROW 6
		joints.add(new Joint(2, 5));
		joints.add(new Joint(3, 5));
		joints.add(new Joint(4, 5));
		joints.add(new Joint(5, 5));
		joints.add(new Joint(6, 5));
		joints.add(new Joint(7, 5));
		joints.add(new Joint(8, 5));
		
	}
	
	private void InitHexLocations()
	{
		hexes.add(new Hex(2, 0));
		hexes.add(new Hex(3, 0));
		hexes.add(new Hex(4, 0));
		hexes.add(new Hex(1, 1));
		hexes.add(new Hex(2, 1));
		hexes.add(new Hex(3, 1));
		hexes.add(new Hex(4, 1));
		hexes.add(new Hex(0, 2));
		hexes.add(new Hex(1, 2));
		hexes.add(new Hex(2, 2));
		hexes.add(new Hex(3, 2));
		hexes.add(new Hex(4, 2));
		hexes.add(new Hex(1, 3));
		hexes.add(new Hex(2, 3));
		hexes.add(new Hex(4, 3));
		hexes.add(new Hex(5, 3));
		hexes.add(new Hex(2, 4));
		hexes.add(new Hex(3, 4));
		hexes.add(new Hex(4, 4));
	}
	private void InitHexType()
	{
		Collections.shuffle(hexes); // Since our hexes have location attached.
		hexes.get(0).SetType(Hex.HexType.SHEEP);
		hexes.get(1).SetType(Hex.HexType.SHEEP);
		hexes.get(2).SetType(Hex.HexType.SHEEP);
		hexes.get(3).SetType(Hex.HexType.SHEEP);
		hexes.get(4).SetType(Hex.HexType.WHEAT);
		hexes.get(5).SetType(Hex.HexType.WHEAT);
		hexes.get(6).SetType(Hex.HexType.WHEAT);
		hexes.get(7).SetType(Hex.HexType.WHEAT);
		hexes.get(8).SetType(Hex.HexType.LUMBER);
		hexes.get(9).SetType(Hex.HexType.LUMBER);
		hexes.get(10).SetType(Hex.HexType.LUMBER);
		hexes.get(11).SetType(Hex.HexType.LUMBER);
		hexes.get(12).SetType(Hex.HexType.BRICK);
		hexes.get(13).SetType(Hex.HexType.BRICK);
		hexes.get(14).SetType(Hex.HexType.BRICK);
		hexes.get(15).SetType(Hex.HexType.STONE);
		hexes.get(16).SetType(Hex.HexType.STONE);
		hexes.get(17).SetType(Hex.HexType.STONE);
		hexes.get(18).SetType(Hex.HexType.SAND);
	}
	
	public Hex FindHex(int x, int y)
	{
		for(int i = 0; i < hexes.size(); i++)
		{
			if(hexes.get(i).GetX() == x && hexes.get(i).GetY() == y)
			{
				return hexes.get(i);
			}
		}
		return null;
	}
	
	public Hex FindLeftHex(int x, int y)
	{
		return FindHex(x - 1, y);
	}
	public Hex FindRightHex(int x, int y)
	{
		return FindHex(x + 1, y);
	}
	public Hex FindBottomLeftHex(int x, int y)
	{
		return FindHex(x - 1, y + 1);
	}
	public Hex FindBottomRightHex(int x, int y)
	{
		return FindHex(x, y + 1);
	}
	public Hex FindTopRightHex(int x, int y)
	{
		return FindHex(x + 1, y - 1);
	}
	public Hex FindTopLeftHex(int x, int y)
	{
		return FindHex(x, y - 1);
	}
	public ArrayList<Hex> FindAdjacentHexes(int x, int y)
	{
		ArrayList<Hex> adj = new ArrayList<Hex>();
		
		if(FindLeftHex(x, y) != null)
		{
			adj.add(FindLeftHex(x, y));
		}
		if(FindRightHex(x, y) != null)
		{
			adj.add(FindRightHex(x, y));
		}
		if(FindBottomLeftHex(x, y) != null)
		{
			adj.add(FindBottomLeftHex(x, y));
		}
		if(FindBottomRightHex(x, y) != null)
		{
			adj.add(FindBottomRightHex(x, y));
		}
		if(FindTopRightHex(x, y) != null)
		{
			adj.add(FindTopRightHex(x, y));
		}
		if(FindTopLeftHex(x, y) != null)
		{
			adj.add(FindTopLeftHex(x, y));
		}
		return adj;
	}
	
	public Joint FindJoint(int x, int y)
	{
		for(int i = 0; i < joints.size(); i++)
		{
			if(joints.get(i).GetX() == x && joints.get(i).GetY() == y)
			{
				return joints.get(i);
			}
		}
		return null;
	}
	public Joint FindJointAbove(int x, int y)
	{
		return FindJoint(x, y -1);
	}
	public Joint FindJointBelow(int x, int y)
	{
		return FindJoint(x, y + 1);
	}
	public Joint FindJointLeft(int x, int y)
	{
		return FindJoint(x - 1, y);
	}
	public Joint FindJointRight(int x, int y)
	{
		return FindJoint(x + 1, y);
	}
	public List<Joint> FindAdjacentJoints(int x, int y) throws Exception
	{
		Joint target = FindJoint(x, y);
		if(target == null)
		{
			throw new Exception("Count not find joint to find adjacents.");
		}
		List<Joint> adj = new ArrayList<Joint>();
		Joint left = FindJointLeft(x, y);
		Joint right = FindJointRight(x, y);
		if(right != null)
		{
			adj.add(right);
		}
		if(left != null)
		{
			adj.add(left);
		}
		
		if((x % 2 != 0 && y % 2 == 0) || (x % 2 == 0 && y % 2 != 0))
		{
			if(y != 0 || x % 2 == 0)
			{
				adj.add(FindJointBelow(x, y));
			}
			
			
		}
		else if((x % 2 != 0 && y % 2 != 0) || (x % 2 == 0 && y % 2 == 0))
		{
			if(y !=  5 || x % 2 == 0)
			{
				adj.add(FindJointAbove(x, y));
			}
		}
		return adj;
	}
	public List<Joint> FindJointsFromHex(int x, int y)
	{
		List<Joint> adj =  new ArrayList<Joint>();
		if(y == 0 || y == 4)
		{
			adj.add(FindJoint(x, y));
			adj.add(FindJoint(x + 1, y));
			adj.add(FindJoint(x + 2, y));
			adj.add(FindJoint(x, y + 1));
			adj.add(FindJoint(x + 1, y + 1));
			adj.add(FindJoint(x + 2, y + 1));
		}
		else if(y == 1 || y == 3)
		{
			adj.add(FindJoint(x + 1, y));
			adj.add(FindJoint(x + 2, y));
			adj.add(FindJoint(x + 3, y));
			adj.add(FindJoint(x + 1, y + 1));
			adj.add(FindJoint(x + 2, y + 1));
			adj.add(FindJoint(x + 3, y + 1));
		}
		else if(y == 2)
		{
			adj.add(FindJoint(x * 2, y));
			adj.add(FindJoint(x * 2, y));
			adj.add(FindJoint(x * 2, y));
			adj.add(FindJoint(x * 2, y + 1));
			adj.add(FindJoint(x * 2, y + 1));
			adj.add(FindJoint(x * 2, y + 1));
		}
		return adj;
	}
	private void Init()
	{
		InitHexes();
		InitJoints();
	}
	public GameBoard()
	{
		Init();
	}
	
}
