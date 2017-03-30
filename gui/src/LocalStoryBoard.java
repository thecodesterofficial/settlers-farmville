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
			
			try {
				Socket socket = new Socket(ip, 9534);
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				out.println("connect techboysquared");
				String response = in.readLine();
				boolean gameStart = false;
				if (response.equals("connect good")) {
					System.out.println("Connection Established!");
					out.println("game start");
					while (!gameStart) {
						String msg ="";
						while (in.ready() && !gameStart) {
							msg = in.readLine();
							String [] parts = msg.split(" ");
							if(parts.length > 3)
							{
							if(parts[0].equals("game") && parts[1].equals("init") && parts[2].equals("hex"))
							{
								int index = Integer.parseInt(parts[3]);
								HexType type = HexType.valueOf(parts[4]);
								int number = Integer.parseInt(parts[5]);
								game.SetHexTypeAndNumber(index, type, number);
							}
							}
							System.out.println(msg);
							if(msg.equals("game start"))
							{
								gameStart = true;
								System.out.println("Game Should Start");
							}
						}
						
					}
				} else {

				}
			} catch (IOException e) {
				System.out.println("Unable to connect...");
			}
		}
		else
		{
			
		}
		
		
		
		game.allPlayers.add(p1);
		game.allPlayers.add(p2);
		//game.allPlayers.add(p3);
		//game.allPlayers.add(p4);
		
		//init everything and do what you do
		
		game.allPlayers.get(0).cards.add(ResourceCardType.BRICKRC);
		game.allPlayers.get(0).cards.add(ResourceCardType.BRICKRC);
		game.allPlayers.get(0).cards.add(ResourceCardType.BRICKRC);
		game.allPlayers.get(0).cards.add(ResourceCardType.BRICKRC);
		UserInterface local = new UserInterface(game);
		local.runInterface();
	}

}
