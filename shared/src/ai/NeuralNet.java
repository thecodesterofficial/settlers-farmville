import java.util.ArrayList;
import java.util.Random;

import core.GameBoard;


public class NeuralNet {
	
	double update = .5;
	ArrayList<Perceptron> settlement = new ArrayList<Perceptron>();
	ArrayList<Perceptron> city = new ArrayList<Perceptron>();
	ArrayList<Perceptron> path = new ArrayList<Perceptron>();
	boolean isLearning = false;
	NeuralNet winNeuralNet;
	NeuralNet loseNeuralNet;
	
	
	public NeuralNet(GameBoard game, boolean isLearning){
		
		this.isLearning = isLearning;
		
		for(int i = 0; i < game.allJoints.size(); i++){//Initializing each settlement perceptron
			settlement.add(new Perceptron(game));
		}
		
	
		
		
		for(int i = 0; i < game.allJoints.size(); i++){ //cities
			city.add(new Perceptron(game));
		}
		
		
		
		for(int i = 0; i < game.allPaths.size(); i++){ //paths
			path.add(new Perceptron(game));
		}
		
		
		winNeuralNet = this;
		loseNeuralNet = this;
		
		
	}
	
	
	public void executeNextMove(GameBoard game){
		
		ArrayList<String> allActions = new ArrayList<String>();
		allActions = game.allPossibleActions();
		
		if(allActions.isEmpty()){
			game.trade4Cards();
		}
		else{
		//String input = allActions.get(random);
		String input = getBestMove(game);
		
	if(isLearning)	
		updateNeuralNet(input);
		
		String[] split = input.split(" ");
		if(split[0].equals("Settlement")){
			game.placeSettlement(Integer.parseInt(split[1]));
		}
		else if(split[0].equals("Path")){
			game.placePath(Integer.parseInt(split[1]));
		}
		else if(split[0].equals("City")){
			game.placeCity(Integer.parseInt(split[1]));
		}
		
		
		}
		
		
	}
	
	
	private void updateNeuralNet(String input) {
		// TODO Auto-generated method stub
		
		String[] split = input.split(" ");
		if(split[0].equals("Settlement")){
			Perceptron winPer = new Perceptron();
			winPer = settlement.get(Integer.parseInt(split[1]));
			Perceptron losePer = new Perceptron();
			losePer = settlement.get(Integer.parseInt(split[1]));
			
			for(int i = 0; i < winPer.settlementBias.size(); i++){
				winPer.settlementBias.set(i, winPer.settlementBias.get(i) + update);
			}
			
			for(int i = 0; i < winPer.settlementBias.size(); i++){
				losePer.settlementBias.set(i, losePer.settlementBias.get(i) - update);
			}
			
			this.loseNeuralNet.settlement.set(Integer.parseInt(split[1]), losePer);
			this.winNeuralNet.settlement.set(Integer.parseInt(split[1]), winPer);
			
			}
		else if(split[0].equals("Path")){
			Perceptron winPer = new Perceptron();
			winPer = path.get(Integer.parseInt(split[1]));
			Perceptron losePer = new Perceptron();
			losePer = path.get(Integer.parseInt(split[1]));
			
			for(int i = 0; i < winPer.pathBias.size(); i++){
				winPer.pathBias.set(i, winPer.pathBias.get(i) + update);
			}
			
			for(int i = 0; i < winPer.pathBias.size(); i++){
				losePer.pathBias.set(i, losePer.pathBias.get(i) - update);
			}
			
			this.loseNeuralNet.path.set(Integer.parseInt(split[1]), losePer);
			this.winNeuralNet.path.set(Integer.parseInt(split[1]), winPer);
		}
		else if(split[0].equals("City")){
			Perceptron winPer = new Perceptron();
			winPer = city.get(Integer.parseInt(split[1]));
			Perceptron losePer = new Perceptron();
			losePer = city.get(Integer.parseInt(split[1]));
			
			for(int i = 0; i < winPer.cityBias.size(); i++){
				winPer.cityBias.set(i, winPer.cityBias.get(i) + update);
			}
			
			for(int i = 0; i < winPer.cityBias.size(); i++){
				losePer.cityBias.set(i, losePer.cityBias.get(i) - update);
			}
			
			this.loseNeuralNet.city.set(Integer.parseInt(split[1]), losePer);
			this.winNeuralNet.city.set(Integer.parseInt(split[1]), winPer);
		}
		
		
	}


	public String getBestMove(GameBoard game){
		
		String response = "nothing";
		double currentHigh = -999999999;
		ArrayList<String> allActions = new ArrayList<String>();
		allActions = game.allPossibleActions();
		
		for(int i = 0; i < allActions.size(); i ++){	
			String input = allActions.get(i);
			String[] split = input.split(" ");
			if(split[0].equals("Settlement")){
				
				double result = settlement.get(Integer.parseInt(split[1])).getValue(game);
				if(result > currentHigh){
					currentHigh = result;
					response = "Settlement "+Integer.parseInt(split[1]);
				}

			}
			else if(split[0].equals("Path")){
				double result = path.get(Integer.parseInt(split[1])).getValue(game);
				if(result > currentHigh){
					currentHigh = result;
					response = "Path "+Integer.parseInt(split[1]);
				}

			}
			else if(split[0].equals("City")){
				double result = city.get(Integer.parseInt(split[1])).getValue(game);
				if(result > currentHigh){
					currentHigh = result;
					response = "City "+Integer.parseInt(split[1]);
				}

			}
		}
		
		//update perceptrons with response

		return response;
	}
	
	
	

}
