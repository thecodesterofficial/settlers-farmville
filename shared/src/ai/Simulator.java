import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import core.GameBoard;
import core.Player;


public class Simulator {
	
	
	static GameBoard game = new GameBoard();//Randomly generated game board

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	

	//int learnerWins = 0;	
	int beforeWins = 0;
	int afterWins = 0;
	
	//n.getBestMove(game);
	
	GameBoard temp = new GameBoard();
	
	NeuralNet n = new NeuralNet(temp, false);
	//game = initGame;
	Player p1 = new Player(Color.red, "player 1");
	Player p2 = new Player(Color.blue, "player 2");
	Player p3 = new Player(Color.yellow, "player 3");
	Player p4 = new Player(Color.green, "player 4");

	Random rand = new Random();
	
	game.allPlayers.add(p1);
	game.allPlayers.add(p2);
	game.allPlayers.add(p3);
	game.allPlayers.add(p4);
	
	//UserInterface ui = new UserInterface(game);
	//ui.runInterface();
	
	
for(int gameNum= 1; gameNum < 101; gameNum ++){
		

		

		
		
	for(int i = 1; i < 1000; i++){	
		
		ArrayList<String> allActions = new ArrayList<String>();
		
		
		allActions = game.allPossibleActions();
		
	

		
		
		if(game.player == 0){
			
			n.executeNextMove(game);

			if(game.checkGameWinner()){
				
				if(game.player == 0){
					beforeWins ++;
				}
				
				n = n.winNeuralNet;
				n.winNeuralNet = n;
				n.loseNeuralNet = n;
				
				game = null;
				break;}			
		}
		else{
		if(allActions.isEmpty()){
			game.trade4Cards();
		}
		else{
			
	
		
		int max = allActions.size();
		int random = rand.nextInt(max); 
		
		String input = allActions.get(random);
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
		
		
		
		
		if(game.checkGameWinner()){
			
			n = n.loseNeuralNet;
			n.winNeuralNet = n;
			n.loseNeuralNet = n;
			
			
			if(game.player == 0){
				beforeWins ++;
			}
			
			game = null;
			
			break;}
		
		
		
		}}
		
		game.nextTurn();
		
	}
		
		//UserInterface ui = new UserInterface(game);
		//ui.runInterface();
		
		
		
		
	}
	
	
	
	n.isLearning = true;
	for(int gameNum= 1; gameNum < 1001; gameNum ++){//training game amount
		
		
		
	for(int i = 0; i < 1000; i++){	
		
		ArrayList<String> allActions = new ArrayList<String>();
		
		allActions = game.allPossibleActions();
		

		
		
		if(game.player == 0){
			
			n.executeNextMove(game);

			if(game.checkGameWinner()){
				
				if(game.player == 0){
					//learnerWins ++;
				}
				
				n = n.winNeuralNet;
				n.winNeuralNet = n;
				n.loseNeuralNet = n;
				
				
				break;}			
		}
		else{
		if(allActions.isEmpty()){
			game.trade4Cards();
		}
		else{
			
	
		
		int max = allActions.size();
		int random = rand.nextInt(max); 
		
		String input = allActions.get(random);
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
		
		
		
		
		if(game.checkGameWinner()){
			
			n = n.loseNeuralNet;
			n.winNeuralNet = n;
			n.loseNeuralNet = n;
			
			
			if(game.player == 0){
				//learnerWins ++;
			}
			
			
			
			break;}
		
		
		
		}}
		
		game.nextTurn();
		
	}
		
		
		
		
		//UserInterface ui = new UserInterface(game);
		//ui.runInterface();
		
	}
	
	
	n.isLearning = false;
for(int gameNum= 1; gameNum < 101; gameNum ++){
		
		
		
	for(int i = 0; i < 1000; i++){	
		
		ArrayList<String> allActions = new ArrayList<String>();
		
		allActions = game.allPossibleActions();
		

		
		
		if(game.player == 0){
			
			n.executeNextMove(game);

			if(game.checkGameWinner()){
				
				if(game.player == 0){
					afterWins ++;
				}
				
				n = n.winNeuralNet;
				n.winNeuralNet = n;
				n.loseNeuralNet = n;
				
				
				break;}			
		}
		else{
		if(allActions.isEmpty()){
			game.trade4Cards();
		}
		else{
			
	
		
		int max = allActions.size();
		int random = rand.nextInt(max); 
		
		String input = allActions.get(random);
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
		
		
		
		
		if(game.checkGameWinner()){
			
			n = n.loseNeuralNet;
			n.winNeuralNet = n;
			n.loseNeuralNet = n;
			
			
			if(game.player == 0){
				
			}
			
			
			
			break;}
		
		
		
		}}
		
		game.nextTurn();
		
	}
		
		//UserInterface ui = new UserInterface(game);
		//ui.runInterface();
		
		

		
	}
	
	
	
	
	System.out.println("amount of wins before learning "+beforeWins);
	System.out.println("amount of wins after learning "+afterWins);
	

	}

}
