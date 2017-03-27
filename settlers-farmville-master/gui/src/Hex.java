import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.HashSet;


public class Hex implements Serializable{
	
/**
	 * 
	 */
private static final long serialVersionUID = 1L;
double centerX;
double centerY;
boolean wasCreated = true;
Color color = Color.red;
HexType hexType;
HashSet<Integer> joints = new HashSet<Integer>();



public Hex(){wasCreated = false;}
	
	public Hex(	double centerX, double centerY){
		hexType = GameBoard.hexInitArray.remove(0);
		this.centerX = centerX;
		this.centerY = centerY;
		
		for(int i = 0; i < GameBoard.allJoints.size(); i++){//Add all the joints corresponding to this hex
			double dist = Math.sqrt((centerX - GameBoard.allJoints.get(i).xLoc)*(centerX - GameBoard.allJoints.get(i).xLoc)
					+(centerY - GameBoard.allJoints.get(i).yLoc)*(centerY - GameBoard.allJoints.get(i).yLoc));
			
			if(dist < .13){
				joints.add(i);//Adds the arraylist position of the joint(So we don't need to deal with serialization of every object)
			}
			
		
		}
	}

	public BufferedImage getImage() {
		// TODO Auto-generated method stub
		if(hexType == HexType.BRICK)
			return UserInterface.brickImage;
		else if(hexType == HexType.LUMBER)
			return UserInterface.forestImage;
		else if(hexType == HexType.SAND)
			return UserInterface.sandImage;
		else if(hexType == HexType.SHEEP)
			return UserInterface.sheepImage;
		else if(hexType == HexType.STONE)
			return UserInterface.stoneImage;
		else if(hexType == HexType.WHEAT)
			return UserInterface.wheatImage;
		else 
			return null;
		
	}
	

}
