import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;





public class GameBoard {
	
	private ArrayList<Hex> hexes = new ArrayList<Hex>();
	
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
	
	private void Init()
	{
		InitHexes();
	}
	public GameBoard()
	{
		Init();
	}
	
}
