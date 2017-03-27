import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import core.*;
public class CatanServerManager {
	
	final private ServerSocket socket;
    final public static int MAX_CLIENTS = 4;
    List<CatanServer> connections = new ArrayList<CatanServer>();
    private int turn = 0;
    public CatanServerManager( int port ) throws IOException {
    	this.socket = new ServerSocket( port );
    	Start();
    }
    public void assignConnection( Socket connection ) {
    	if(this.connections.size() < MAX_CLIENTS)
    	{
    		try
    		{
    			this.connections.add(new CatanServer(connection, connections.size(), this));
    		}
    		catch(IOException e)
      	  	{
      		  	System.out.println(e.getMessage());
      	  	}
    	}
    }
	 private GameBoard board;
	  
	    public void Start() throws IOException {
	        while ( true ) {
	             Socket connection = socket.accept();
	             assignConnection( connection );
	        }
	    }
	    public void Dispatch(String message)
	    {
	    	for(CatanServer conn : connections)
	    	{
	    		conn.Send(message);
	    	}
	    }
	    public void  NewPlayer(String name)
	    {
	    	this.Dispatch("player new " + name);
	    }
	    public void StartGame()
	    {
	    	Dispatch("game init");
	    	board = new GameBoard();
	    	List<Hex> hexes = board.GetHexes();
	    	for(int i = 0; i < hexes.size(); i++)
	    	{
	    		Hex hex = hexes.get(i);
	    		Dispatch("game init hex " + i + " " + hex.GetType());
	    	}
	    	Dispatch("game start");
	    	
	    	Dispatch("player turn " + this.turn);
	    	
	    }

}
