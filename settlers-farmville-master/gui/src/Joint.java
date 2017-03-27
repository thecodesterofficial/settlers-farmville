import java.awt.Color;
import java.io.Serializable;


public class Joint implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public double xLoc;
	public double yLoc;
	Color color = new Color(237,201,175);
	public double size = .025;
	
	
	
	public Joint(double xLoc, double yLoc ){
		this.xLoc = xLoc;
		this.yLoc = yLoc;
	}
	
	public int getSize(){
		return (int)(size*UserInterface.screenWidth);
	}

}
