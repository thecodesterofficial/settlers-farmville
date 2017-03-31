import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import core.GameBoard;
import core.HexType;
import core.Player;
import core.ResourceCardType;
import core.Robber;


public class LocalStoryBoard {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		GameBoard game; //Randomly generated game board
		Player p1 = new Player(Color.red, "player 1");
		Player p2 = new Player(Color.blue, "player 2");
		Player p3 = new Player(Color.green, "player 3");
		Player p4 = new Player(Color.yellow, "player 4");
		
		
		
		
		/*
		game.allPlayers.get(0).cards.add(ResourceCardType.BRICKRC);
		game.allPlayers.get(0).cards.add(ResourceCardType.FORESTRC);
		game.allPlayers.get(0).cards.add(ResourceCardType.SHEEPRC);
		game.allPlayers.get(0).cards.add(ResourceCardType.STONERC);
		game.allPlayers.get(0).cards.add(ResourceCardType.WHEATRC);
		game.allPlayers.get(0).cards.add(ResourceCardType.BRICKRC);
		game.allPlayers.get(0).cards.add(ResourceCardType.FORESTRC);
		game.allPlayers.get(0).cards.add(ResourceCardType.SHEEPRC);
		game.allPlayers.get(0).cards.add(ResourceCardType.STONERC);
		game.allPlayers.get(0).cards.add(ResourceCardType.WHEATRC);
		game.allPlayers.get(0).cards.add(ResourceCardType.WHEATRC);
		
		game.allPlayers.get(1).cards.add(ResourceCardType.WHEATRC);
		game.allPlayers.get(1).cards.add(ResourceCardType.BRICKRC);
		game.allPlayers.get(1).cards.add(ResourceCardType.FORESTRC);
		game.allPlayers.get(1).cards.add(ResourceCardType.STONERC);
		game.allPlayers.get(1).cards.add(ResourceCardType.SHEEPRC);
		
		
		
		game.allPlayers.get(2).cards.add(ResourceCardType.SHEEPRC);
		game.allPlayers.get(2).cards.add(ResourceCardType.BRICKRC);
		game.allPlayers.get(2).cards.add(ResourceCardType.STONERC);
		game.allPlayers.get(2).cards.add(ResourceCardType.WHEATRC);
		game.allPlayers.get(2).cards.add(ResourceCardType.FORESTRC);
		game.allPlayers.get(2).cards.add(ResourceCardType.SHEEPRC);
		game.allPlayers.get(2).cards.add(ResourceCardType.BRICKRC);
		game.allPlayers.get(2).cards.add(ResourceCardType.STONERC);
		game.allPlayers.get(2).cards.add(ResourceCardType.WHEATRC);
		game.allPlayers.get(2).cards.add(ResourceCardType.FORESTRC);
		game.allPlayers.get(2).cards.add(ResourceCardType.WHEATRC);
		game.allPlayers.get(2).cards.add(ResourceCardType.FORESTRC);
		game.allPlayers.get(2).cards.add(ResourceCardType.WHEATRC);
		game.allPlayers.get(2).cards.add(ResourceCardType.FORESTRC);

		
		game.allPlayers.get(3).cards.add(ResourceCardType.FORESTRC);
		game.allPlayers.get(3).cards.add(ResourceCardType.BRICKRC);
		game.allPlayers.get(3).cards.add(ResourceCardType.SHEEPRC);
		game.allPlayers.get(3).cards.add(ResourceCardType.WHEATRC);
		game.allPlayers.get(3).cards.add(ResourceCardType.STONERC);
		*/
		game = new GameBoard();
		
		boolean server = true;
		if (server) { // Will make this prettier later. 
			String ip = JOptionPane.showInputDialog("What is the ip of the server?");
			String username = JOptionPane.showInputDialog("What is your username?");
			ServerComm comm = new ServerComm(ip, 9534, username, game);
		}
		else
		{
			
		}
		
		
		
		//game.allPlayers.add(p1);
		//game.allPlayers.add(p2);
		//game.allPlayers.add(p3);
		//game.allPlayers.add(p4);
		
		//init everything and do what you do
		
		//game.allPlayers.get(0).cards.add(ResourceCardType.BRICKRC);
		//game.allPlayers.get(0).cards.add(ResourceCardType.BRICKRC);
		//game.allPlayers.get(0).cards.add(ResourceCardType.BRICKRC);
		//game.allPlayers.get(0).cards.add(ResourceCardType.BRICKRC);
		UserInterface local = new UserInterface(game);
		local.runInterface();
	}

}
