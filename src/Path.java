import java.awt.Color;
import java.io.Serializable;


public class Path implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5731697279643678549L;
	Color color = new Color(129,98,78);
	double xLoc1;
	double xLoc2;
	double yLoc1;
	double yLoc2;
	
	public Path(double xLoc1, double yLoc1, double xLoc2, double yLoc2){
		this.xLoc1 = xLoc1;
		this.xLoc2 = xLoc2;
		this.yLoc1 = yLoc1;
		this.yLoc2 = yLoc2;
	}

}
