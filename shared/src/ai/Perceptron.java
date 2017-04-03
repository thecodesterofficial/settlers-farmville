import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;


public class Perceptron {
	
	ArrayList<Double> settlementBias = new ArrayList<Double>();
	ArrayList<Double> cityBias = new ArrayList<Double>();
	ArrayList<Double> pathBias = new ArrayList<Double>();
	ArrayList<Double> hexImportance = new ArrayList<Double>();
	double numberBias;
	ArrayList<Double> hexTypeBias = new ArrayList<Double>();

	
	public Perceptron(){}

	public Perceptron(GameBoard game) {
		
		Random ran = new Random();
		double x = (ran.nextDouble()*2) - 1; //initial values between -1 and 1
		
		for(int i = 0; i < game.allJoints.size(); i++){ //settlements
			settlementBias.add((ran.nextDouble()*2) - 1);
		}
		
	
		
		for(int i = 0; i < game.allJoints.size(); i++){ //cities
			cityBias.add((ran.nextDouble()*2) - 1);
		}
		
		
		
		for(int i = 0; i < game.allPaths.size(); i++){ //paths
			pathBias.add((ran.nextDouble()*2) - 1);
		}
		
		for(int i = 0; i < game.allHexes.size(); i++){ //hex number
			this.hexImportance.add((ran.nextDouble()*2) - 1);
		}
		
		
		
			numberBias = ((ran.nextDouble()*2) - 1);
		
		
		
		
		for(int i = 0; i < 6; i++){ //hex type
			hexTypeBias.add((ran.nextDouble()*2) - 1);
		}
		
		
		
		
		
		
		
	}

	public double getValue(GameBoard game) {
		// TODO Auto-generated method stub
		double total = 0;
		
		for(int i = 0; i < game.allJoints.size(); i++){ //settlements
			if(game.allJoints.get(i).color.equals(new Color(237,201,175))){
				
			}
			else if(game.allJoints.get(i).color.equals(game.allPlayers.get(game.player).color)){ //if it belongs to the player
				total += settlementBias.get(i);
			}
			else{
				total -= settlementBias.get(i);
			}
		}
		
		for(int i = 0; i < game.allJoints.size(); i++){ //cities
			if(game.allJoints.get(i).color.equals(new Color(237,201,175))){
				
			}
			else if(game.allJoints.get(i).color.equals(game.allPlayers.get(game.player).color)){ //if it belongs to the player
				total += cityBias.get(i);
			}
			else{
				total -= cityBias.get(i);
			}
		}
		
		for(int i = 0; i < game.allPaths.size(); i++){ //paths
			if(game.allPaths.get(i).color.equals(new Color(129,98,78))){
				
			}
			else if(game.allPaths.get(i).color.equals(game.allPlayers.get(game.player).color)){ //if it belongs to the player
				total += pathBias.get(i);
			}
			else{
				total -= pathBias.get(i);
			}
		}
		
		
		for(int i = 0; i < game.allHexes.size(); i++){ //hex numbers
			
			if(game.allHexes.get(i).hexType == HexType.BRICK){
				total += this.hexImportance.get(i) * getPercentage(i, game) * hexTypeBias.get(0);
			}
			else if(game.allHexes.get(i).hexType == HexType.LUMBER){
				 total += this.hexImportance.get(i) * getPercentage(i, game) * hexTypeBias.get(0);
				}
			else if(game.allHexes.get(i).hexType == HexType.SAND){
				 total += this.hexImportance.get(i) * getPercentage(i, game) * hexTypeBias.get(0);
				}
			else if(game.allHexes.get(i).hexType == HexType.SHEEP){
				 total += this.hexImportance.get(i) * getPercentage(i, game) * hexTypeBias.get(0);
				}
			else if(game.allHexes.get(i).hexType == HexType.STONE){
				 total += this.hexImportance.get(i) * getPercentage(i, game) * hexTypeBias.get(0);
				}
			else if(game.allHexes.get(i).hexType == HexType.WHEAT){
				 total += this.hexImportance.get(i) * getPercentage(i, game) * hexTypeBias.get(0);
				}
			
		}
		
		
		/*
		for(int i = 0; i < game.allHexes.size(); i++){ //hex numbers
				total += hexNumberBias.get(i) * getPercentage(game.allHexes.get(i).number); 
		}
		
		
		for(int i = 0; i < game.allHexes.size() * 6; i++){// hex Types
			
			if(i%6 == 0){
				if(game.allHexes.get(i/6).hexType == HexType.BRICK)
					total += hexTypeBias.get(i);
				else
					total -= hexTypeBias.get(i);
			}
			else if(i%6 == 1){
				if(game.allHexes.get(i/6).hexType == HexType.WHEAT)
					total += hexTypeBias.get(i);
				else
					total -= hexTypeBias.get(i);
			}
			else if(i%6 == 2){
				if(game.allHexes.get(i/6).hexType == HexType.LUMBER)
					total += hexTypeBias.get(i);
				else
					total -= hexTypeBias.get(i);

			}
			else if(i%6 == 3){
				if(game.allHexes.get(i/6).hexType == HexType.SAND)
					total += hexTypeBias.get(i);
				else
					total -= hexTypeBias.get(i);

			}
			else if(i%6 == 4){
				if(game.allHexes.get(i/6).hexType == HexType.SHEEP)
					total += hexTypeBias.get(i);
				else
					total -= hexTypeBias.get(i);

			}
			else if(i%6 == 5){
				if(game.allHexes.get(i/6).hexType == HexType.STONE)
					total += hexTypeBias.get(i);
				else
					total -= hexTypeBias.get(i);

			}
			
		}*/
		
	
		return total;
	}

	private Double getPercentage(int number, GameBoard game) {
		// TODO Auto-generated method stub
		
		double rollNum = 1.0 * game.allHexes.get(number).number;
		double distance = Math.abs(7-rollNum);
		
		
		return distance * this.numberBias ;
		
		
	}

}
