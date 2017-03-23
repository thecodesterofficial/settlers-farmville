import static org.junit.Assert.*;

import java.awt.Adjustable;
import java.util.List;

import org.junit.*;


import junit.framework.TestCase;

public class TestGameBoard extends TestCase {
	GameBoard board;
	@Before
	protected void setUp()
	{
		board = new GameBoard();
	}
	public void testFindHexWhenThere()
	{
		Hex hex = board.FindHex(2, 0);
		assertTrue(hex.GetX() == 2);
		assertTrue(hex.GetY() == 0);
	}
	public void testFindHexWhenNotThere()
	{
		Hex hex = board.FindHex(0,  0);
		assertNull(hex);
	}
	public void testFindHexLeft()
	{
		Hex hex = board.FindLeftHex(2, 1);
		assertTrue(hex.GetX() == 1);
		assertTrue(hex.GetY() == 1);
	}
	public void testFindHexRight()
	{
		Hex hex = board.FindRightHex(2, 1);
		assertTrue(hex.GetX() == 3);
		assertTrue(hex.GetY() == 1);
	}
	public void testFindHexBottomLeft()
	{
		Hex hex = board.FindBottomLeftHex(2, 1);
		assertTrue(hex.GetX() == 1);
		assertTrue(hex.GetY() == 2);
	}
	public void testFindHexBottomRight()
	{
		Hex hex = board.FindBottomRightHex(2, 1);
		assertTrue(hex.GetX() == 2);
		assertTrue(hex.GetY() == 2);
	}
	public void testFindHexTopLeft()
	{
		Hex hex = board.FindTopLeftHex(2, 1);
		assertTrue(hex.GetX() == 2);
		assertTrue(hex.GetY() == 0);
	}
	public void testFindHexTopRight()
	{
		Hex hex = board.FindTopRightHex(2, 1);
		assertTrue(hex.GetX() == 3);
		assertTrue(hex.GetY() == 0);
	}
	public void testAdjacentHexesAllSides()
	{
		List<Hex> adj = board.FindAdjacentHexes(2,  2);
		assertTrue(adj.size() == 6);
	}
	
	public void testAdjacentHexesNotAllSides()
	{
		List<Hex> adj = board.FindAdjacentHexes(2,  0);
		assertTrue(adj.size() == 3);
	}
	public void testFindJointThere()
	{
		Joint joint = board.FindJoint(3,  0);
		assertTrue(joint.GetX() == 3);
		assertTrue(joint.GetY() == 0);
	}
	public void testFindJointNotThere()
	{
		Joint joint = board.FindJoint(0, 0);
		assertNull(joint);
	}
	public void testFindJointAboveThere()
	{
		Joint joint = board.FindJointAbove(5, 1);
		assertTrue(joint.GetX() == 5);
		assertTrue(joint.GetY() == 0);
	}
	public void testFindJointAboveNotThere()
	{
		Joint joint = board.FindJointAbove(3, 0);
		assertNull(joint);
	}
	public void testFindJointLeftThere()
	{
		Joint joint = board.FindJointLeft(4, 1);
		assertTrue(joint.GetX() == 3);
		assertTrue(joint.GetY() == 1);
	}
	public void testFindJointLeftNotThere()
	{
		Joint joint = board.FindJointLeft(1, 1);
		assertNull(joint);
	}
	public void testFindJointBelowThere()
	{
		Joint joint = board.FindJointBelow(3, 0);
		assertTrue(joint.GetX() == 3);
		assertTrue(joint.GetY() == 1);
	}
	public void testFindJointBelowNotThere()
	{
		Joint joint = board.FindJointBelow(4, 5);
		assertNull(joint);
	}
	public void testFindJointRightThere()
	{
		Joint joint = board.FindJointRight(3, 1);
		assertTrue(joint.GetX() == 4);
		assertTrue(joint.GetY() == 1);
	}
	public void testFindJointRightNotThere()
	{
		Joint joint = board.FindJointRight(9, 1);
		assertNull(joint);
	}
	public void testFindAdjacentJointsEdge1()
	{
		try
		{
			List<Joint> adj = board.FindAdjacentJoints(3,  0);
			assertTrue(adj.size() == 2);
			
		}
		catch(Exception e)
		{
			System.out.println("Problem finding adjacent joints on edge:");
			System.out.println(e.getMessage());
		}
	}
	public void testFindAdjacentJointsEdge2()
	{
		try 
		{
			List<Joint> adj = board.FindAdjacentJoints(7, 1);
		
			assertTrue(adj.size() == 3);
		}
		catch(Exception e)
		{
			System.out.println("Problem finding adjacent joints on edge:");
			System.out.println(e.getMessage());
		}
	}
	
	public void testFindAdjacentJointsEdge3()
	{
		try 
		{
			List<Joint> adj = board.FindAdjacentJoints(3, 5);
		
			assertTrue(adj.size() == 2);
		}
		catch(Exception e)
		{
			System.out.println("Problem finding adjacent joints on edge:");
			System.out.println(e.getMessage());
		}
	}
	public void testFindAjacentJointsFull()
	{
		try
		{
			List<Joint> adj = board.FindAdjacentJoints(4, 2);
			assertTrue(adj.size() == 3);
		}
		catch(Exception e)
		{
			System.out.println("Problem finding adjacent joints on edge:");
			System.out.println(e.getMessage());
		}
	}
	public void testFindHexFromJointsRow1()
	{
		List<Joint> adj = board.FindJointsFromHex(2, 0);
		assertTrue(adj.size() == 6);
		assertFalse(adj.contains(null));
		assertTrue(adj.contains(new Joint(2, 0)));
		assertTrue(adj.contains(new Joint(3, 0)));
		assertTrue(adj.contains(new Joint(4, 0)));
		assertTrue(adj.contains(new Joint(2, 1)));
		assertTrue(adj.contains(new Joint(3, 1)));
		assertTrue(adj.contains(new Joint(4, 1)));
		assertFalse(adj.contains(new Joint(0, 0)));
	}
	public void testFindHexFromJointsRow2()
	{
		List<Joint> adj = board.FindJointsFromHex(2, 1);
		assertTrue(adj.size() == 6);
		assertFalse(adj.contains(null));
		assertTrue(adj.contains(new Joint(3, 1)));
		assertTrue(adj.contains(new Joint(4, 1)));
		assertTrue(adj.contains(new Joint(5, 1)));
		assertTrue(adj.contains(new Joint(3, 2)));
		assertTrue(adj.contains(new Joint(4, 2)));
		assertTrue(adj.contains(new Joint(5, 2)));
	
	}
	public void testFindHexFromJointsRow3()
	{
		List<Joint> adj = board.FindJointsFromHex(2, 2);
		assertTrue(adj.size() == 6);
		assertFalse(adj.contains(null));
		assertTrue(adj.contains(new Joint(4, 2)));
		assertTrue(adj.contains(new Joint(5, 2)));
		assertTrue(adj.contains(new Joint(6, 2)));
		assertTrue(adj.contains(new Joint(4, 3)));
		assertTrue(adj.contains(new Joint(5, 3)));
		assertTrue(adj.contains(new Joint(6, 3)));
	
	}
	
	public void testFindHexFromJointsRow4()
	{
		List<Joint> adj = board.FindJointsFromHex(2, 3);
		assertTrue(adj.size() == 6);
		assertFalse(adj.contains(null));
		assertTrue(adj.contains(new Joint(3, 3)));
		assertTrue(adj.contains(new Joint(4, 3)));
		assertTrue(adj.contains(new Joint(5, 3)));
		assertTrue(adj.contains(new Joint(3, 4)));
		assertTrue(adj.contains(new Joint(4, 4)));
		assertTrue(adj.contains(new Joint(5, 4)));
	
	}
	public void testFindHexFromJointsRow5()
	{
		List<Joint> adj = board.FindJointsFromHex(2, 4);
		assertTrue(adj.size() == 6);
		assertFalse(adj.contains(null));
		assertTrue(adj.contains(new Joint(2, 4)));
		assertTrue(adj.contains(new Joint(3, 4)));
		assertTrue(adj.contains(new Joint(4, 4)));
		assertTrue(adj.contains(new Joint(2, 5)));
		assertTrue(adj.contains(new Joint(3, 5)));
		assertTrue(adj.contains(new Joint(4, 5)));
	}


}
