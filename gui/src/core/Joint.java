package core;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;

public class Joint implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public double xLoc;
	public double yLoc;
	public Color color = new Color(237, 201, 175);
	public double size = .03;
	ArrayList<Joint> adjacent = new ArrayList<Joint>();
	public boolean city = false;

	public void setAdjacent() {

		int search = -1;

		for (int i = 0; i < GameBoard.allJoints.size(); i++) {
			if (GameBoard.allJoints.get(i).equals(this))
				search = i;
		}

		for (int i = 0; i < GameBoard.allPaths.size(); i++) {
			if (GameBoard.allPaths.get(i).joints.contains(search)) {
				for (Integer num : GameBoard.allPaths.get(i).joints) {
					if (!GameBoard.allJoints.get(num).equals(this))
						adjacent.add(GameBoard.allJoints.get(num));
				}

			}
		}

		/*
		 * //How to debug the adjacent stuff
		 * 
		 * System.out.println("adjacent "+adjacent.size());
		 * 
		 * if(search == 0){ size = .05; for(int i = 0; i < adjacent.size();
		 * i++){ adjacent.get(i).size = .05; } }
		 */
	}

	public Joint(double xLoc, double yLoc) {
		this.xLoc = xLoc;
		this.yLoc = yLoc;
	}

	/*
	 * public int getSize(){ return (int)(size*UserInterface.screenWidth); }
	 */

}
