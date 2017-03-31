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
	public ServerComm(String ip, int port, String username, GameBoard board) {
		this.game = board;
		try {
			socket = new Socket(ip, port);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(), true);
			out.println("connect " + username);
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
	public void processGame(String parts[])
	{
		if(parts[1].equals("init"))
		{
			if(parts[2].equals("hex"))
			{
				int index = Integer.parseInt(parts[3]);
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
				game.allPlayers.add(new Player(Color.red, username));
				
				for(Player p : game.allPlayers)
				{
					System.out.println("Player: " + p.username);
				}
				
			}
		}
	}
	
	@Override
	public void run() {
		while (!this.interrupted()) {
			try {
				if (in.ready()) {
					String data = in.readLine();
					
					String[] split = data.split(" ");
					if (split.length > 0) {
						process(split);
					}

				}

			} catch (Exception e) {
				System.out.println(e.getStackTrace().toString());
				return;
			}

		}
	}
}
