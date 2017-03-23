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

}
