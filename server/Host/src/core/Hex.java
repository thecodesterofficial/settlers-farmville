package core;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Random;


public class Hex implements Serializable{
	
/**
	 * 
	 */
private static final long serialVersionUID = 1L;
public double centerX;
public double centerY;
public boolean wasCreated = true;
public int number;
public Color color = Color.red;
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
			
			if(dist < .1){
				joints.add(i);//Adds the arraylist position of the joint(So we don't need to deal with serialization of every object)
			}
			
		
		}
		
	if(hexType != HexType.SAND){
		int min = 2;
		int max = 12;//pretty sure this is non inclusive.. could be wrong
		Random rand = new Random();
		
		number = rand.nextInt(max - min)+min ;
		
		if(number == 7)
			number = 8;//Add to the 8 party woop woop
	
	}
	else{
		number = -1;
	}
		
		
	System.out.println("joints size: "+joints.size());
	
	}
	public HexType GetHexType()
	{
		return this.hexType;
	}
	

}
