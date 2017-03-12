import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class CatanServer extends Thread {
	private String username;
	private int id;
	private Socket connection;
	PrintWriter out;
	BufferedReader in;
	CatanServerManager manager;
	public CatanServer( Socket connection , int id, CatanServerManager manager ) throws IOException {
        this.id = id;
        this.connection = connection;
        this.manager = manager;
        out = new PrintWriter(this.connection.getOutputStream(), true);
        in =  new BufferedReader(new InputStreamReader(this.connection.getInputStream()));
        start();
    }

    @Override
    public void run() {
         while( !this.interrupted() ) {
        	 try
        	 {
        		 if(in.ready())
        		 {
        			 String data = in.readLine();
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
        		 return;        	
        	 }
             
         }
    }

    private void rollDice()
    {
    	//if it is this players turn roll the dice and dispatch that to every one
    	manager.Dispatch("roll dice 2");
    }
    public void Send(String message)
    {
    	System.out.println("Made it to send");
    	out.println(message);
    }
    
    
    private void handleConnect(String[] message) 
    {
    	if(message.length == 2)
    	{
    		this.username = message[1];
    		System.out.println("Identified as " + this.username);
    		out.println("connect good");
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
    			manager.StartGame();
    		}
    		
    	}
    }
    
    public void process( String[] message ) {
    	
    	if(message[0].equals("connect"))
    	{
    		handleConnect(message);
    	}
    	else if(message[0].equals("game"))
    	{
    		handleGameMessage(message);
    	}
    	
    }

   
    public void close() {
        try {
             this.connection.close();
             
        } catch ( IOException e ) {
             
        }
    }
}