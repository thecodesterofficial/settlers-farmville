import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import core.GameBoard;
import core.Player;

public class CatanServer extends Thread {
	private String username;
	private int id;
	private Socket connection;
	PrintWriter out;
	BufferedReader in;
	public CatanServer( Socket connection , int id) throws IOException {
        this.id = id;
        this.connection = connection;
        out = new PrintWriter(this.connection.getOutputStream(), true);
        in =  new BufferedReader(new InputStreamReader(this.connection.getInputStream()));
        start();
	}

    @Override
    public void run() {
         while( !this.interrupted() ) {
        	 String data ="";
        	 try
        	 {
        		 if(in.ready())
        		 {
        			 data = in.readLine();
        			 System.out.println("Read in message: " + data);
        			 String[] split = data.split(" ");
        			 if(split.length > 0)
        			 {
        				 process(split);
        			 }
        			 
        		 }
        		 
        		
        	 }
        	 catch(Exception e)
        	 {
        		 System.out.println(e.getStackTrace().toString());
        		 System.out.println(e.getMessage());
        		 System.out.println("Problem parsing " + data);
        		       	
        	 }
             
         }
    }

   
    public void Send(String message)
    {
    	System.out.println("Made it to send");
    	out.println(message);
    }
    
    private Color GetNewPlayerColor()
    {
    	Color color = Color.red;
	switch(CatanServerManager.instance().GetGameBoard().allPlayers.size())
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
		return color;
    }
    
    private void handleConnect(String[] message) 
    {
    	if(message.length == 2)
    	{
    		this.username = message[1];
    		
    		
    		
    		out.println("connect good");
    		GameBoard board = CatanServerManager.instance().GetGameBoard();
    		for(Player player : board.allPlayers)
    		{
    			Send("player new " + player.username); // Tell New player about current players
    			
    		}
    		
    		
    		
    		board.allPlayers.add(new Player(GetNewPlayerColor(), this.username));
    		
    		CatanServerManager.instance().NewPlayer(this.username);
    	}
    	else
    	{
    		out.println("connect bad");
    	}
    
    }
    private void handleGameMessage(String[] message)
    {
    	if(message.length > 0)
    	{
    		if(message[1].equals("start"))
    		{
    			CatanServerManager.instance().StartGame();
    		}
    		
    	}
    }
    
    public void process( String[] message ) {
    	
    	System.out.println("Processing Message ON SERVER");
    	if(message[0].equals("connect"))
    	{
    		handleConnect(message);
    	}
    	else if(message[0].equals("game"))
    	{
    		handleGameMessage(message);
    	}
    	else if(message[0].equals("move"))
    	{
    		handleMove(message);
    	}
    	
    }

    private void handleMove(String[] message)
    {
    	if(message[1].equals("settlement"))
    	{
    		int index = Integer.parseInt(message[2]);
    		if(CatanServerManager.instance().GetGameBoard().placeSettlement(index))
    		{
    			ConnectionManager.instance().Dispatch("move settlement " + this.username + " " + index);
    			System.out.println("Sending Settlement from server");
    		}
    	}
    	else if(message[1].equals("path"))
    	{
    		int index = Integer.parseInt(message[2]);
    		if(CatanServerManager.instance().GetGameBoard().placePath(index))
    		{
    			ConnectionManager.instance().Dispatch("move path " + this.username + " " + index);
    		}
    	}
    	else if(message[1].equals("next"))
    	{
    		if(CatanServerManager.instance().GetGameBoard().nextTurn())
    		{
    			
    				
    			GameBoard board = CatanServerManager.instance().GetGameBoard();
    			String currentPlayer = board.getCurrentPlayer().username;
    			ConnectionManager.instance().Dispatch("move next " + currentPlayer);
    			ConnectionManager.instance().Dispatch("move round " + board.round);
    			if(board.round > 2)
    			{
    				int number = board.getDiceNumber();
    				//System.out.println("Rolled a " + number);
    				System.out.println("ROLLING THE DICE");
    				try
        			{
    				board.rollDice(number);
        			}
        			catch(Exception e)
        			{
        				System.out.println("actually the porblem is here.");
        			}
    				ConnectionManager.instance().Dispatch("move dice " + number);
    			}
    			
    		}
    	}
    	
    }
    public void close() {
        try {
             this.connection.close();
             
        } catch ( IOException e ) {
             
        }
    }
}
