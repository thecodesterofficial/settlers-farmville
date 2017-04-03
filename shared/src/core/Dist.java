package core;

public class Dist {
	
	City city1;
	City city2;
	
	public Dist(City city1, City city2){
		this.city1 = city1;
		this.city2 = city2;
	}
	
	static double getRadius(City c1, City c2){
		
		int xDiff = c1.xLoc - c2.xLoc;
		int yDiff = c1.yLoc - c2.yLoc;
		int total = (xDiff*xDiff)+(yDiff*yDiff);
		
		return Math.sqrt((double)(total));
	}
	

}
