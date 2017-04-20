import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import java.awt.Color;

import core.GameBoard;
import core.HexType;
import core.Player;
import core.Robber;

public class ServerComm extends Thread {
	Socket socket;
	BufferedReader in;
	PrintWriter out;
    GameBoard game;
    private String username = "";
	public ServerComm(String ip, int port, String username, GameBoard board) {
		this.game = board;
		try {
			socket = new Socket(ip, port);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(), true);
			out.println("connect " + username);
			this.username = username;
			String response = in.readLine();
			if (response.equals("connect good")) {
				start();
			} else {
				// TODO Should handle this more gracefully eventually.
				System.out.println("Connection was not good...");
				System.exit(1);
			}
		} catch (IOException e) {

			System.out.println("ERROR: unable to connect to the server.");
		}

	}
	public String getUsername()
	{
		return username;
	}
	public void processGame(String parts[])
	{
		if(parts[1].equals("init"))
		{
			if(parts[2].equals("hex"))
			{
				
				int index = Integer.parseInt(parts[3]);
				System.out.println("Setting Hex " + index);
				HexType type = HexType.valueOf(parts[4]);
				int number = Integer.parseInt(parts[5]);
				game.SetHexTypeAndNumber(index, type, number);
			}
			else if(parts[2].equals("robber"))
			{
				
				int number = Integer.parseInt(parts[3]);
				System.out.println(number);
				game.rob = new Robber(number);
			}
		}
		else if(parts[2].equals("start"))
		{
			
		}
			
	}
	
	public void process(String parts[])
	{
		
		if(parts[0].equals("game"))
		{
			processGame(parts);
		} 
		else if(parts[0].equals("player"))
		{
			if(parts[1].equals("new"))
			{
				String username = parts[2];
				Color color = Color.red;
				switch(game.allPlayers.size())
				{
				case 0:
					color = Color.red;
					break;
				case 1:
					color = Color.blue;
				case 2:
					color = Color.green;
				case 3:
					color = Color.yellow;
				}
				game.allPlayers.add(new Player(color, username));
				
			}
		}
		else if(parts[0].equals("move"))
		{
			if(parts[1].equals("settlement"))
			{
				int index = Integer.parseInt(parts[3]);
				game.placeSettlement(index);
				
			}
			else if(parts[1].equals("path"))
			{
				int index = Integer.parseInt(parts[3]);
				game.placePath(index);
				
			}
			else if(parts[1].equals("next"))
			{
				game.nextTurn();
			}
		}
	}
	
	public void sendMessage(String message)
	{
		out.println(message);
	}
	
	
	public void placeSettlement(int index)
	{
		this.sendMessage("move settlement " + index);
	}
	public void placePath(int index)
	{
		this.sendMessage("move path " + index);
	}
	public void nextTurn()
	{
		this.sendMessage("move next");
	}
	
	@Override
	public void run() {
		while (!this.interrupted()) {
			try {
				if (in.ready()) {
					String data = in.readLine();
					System.out.println(data);
					String[] split = data.split(" ");
					if (split.length > 0) {
						process(split);
					}
				
			}

			} catch (Exception e) {
				System.out.println(e.getStackTrace().toString());
			
			}

		}
	}
}
