import java.awt.Color;
import java.io.Serializable;


public class Hex implements Serializable{
	
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
double centerX;
double centerY;
boolean wasCreated = true;
Color color = Color.red;

public Hex(){wasCreated = false;}
	
	public Hex(	double centerX, double centerY){
		this.centerX = centerX;
		this.centerY = centerY;
	}
	

}
