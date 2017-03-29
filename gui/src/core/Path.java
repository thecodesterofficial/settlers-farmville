package core;
import java.awt.Color;
import java.io.Serializable;
import java.util.HashSet;


public class Path implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Color color = new Color(129,98,78);
	public double xLoc1;
	public double xLoc2;
	public double yLoc1;
	public double yLoc2;
	public HashSet<Integer> joints = new HashSet<Integer>();
	
	public Path(double xLoc1, double yLoc1, double xLoc2, double yLoc2){
		this.xLoc1 = xLoc1;
		this.xLoc2 = xLoc2;
		this.yLoc1 = yLoc1;
		this.yLoc2 = yLoc2;
		
		
		for(int i = 0; i < GameBoard.allJoints.size(); i++){//Add all the joints corresponding to this hex
			double dist = Math.sqrt((xLoc1 - GameBoard.allJoints.get(i).xLoc)*(xLoc1 - GameBoard.allJoints.get(i).xLoc)
					+(yLoc1 - GameBoard.allJoints.get(i).yLoc)*(yLoc1 - GameBoard.allJoints.get(i).yLoc));
			
			if(dist < .03){
				joints.add(i);//Adds the arraylist position of the joint(So we don't need to deal with serialization of every object)
			}
			
		
		}
		
		for(int i = 0; i < GameBoard.allJoints.size(); i++){//Add all the joints corresponding to this hex
			double dist = Math.sqrt((xLoc2 - GameBoard.allJoints.get(i).xLoc)*(xLoc2 - GameBoard.allJoints.get(i).xLoc)
					+(yLoc2 - GameBoard.allJoints.get(i).yLoc)*(yLoc2 - GameBoard.allJoints.get(i).yLoc));
			
			if(dist < .03){
				joints.add(i);//Adds the arraylist position of the joint(So we don't need to deal with serialization of every object)
			}
			
		
		}		
	}
	
	
	

}
