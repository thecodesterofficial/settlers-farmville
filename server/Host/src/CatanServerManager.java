import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

public class CatanServerManager {
	
	final private ServerSocket socket;
    final public static int MAX_CLIENTS = 4;
    //final private CatanServer[] connections = new CatanServer[ MAX_CLIENTS ];
    List<CatanServer> connections = new ArrayList<CatanServer>();
    private int turn = 0;
    public CatanServerManager( int port ) throws IOException {
    	this.socket = new ServerSocket( port );
        
    	Start();
        
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
    	/*for ( int i = 0 ; i < MAX_CLIENTS && this.connections[ i ] != null; i++ ) {    
            connections[i].Send(message);
        }*/
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
    
    public void assignConnection( Socket connection ) {
         /*for ( int i = 0 ; i < MAX_CLIENTS ; i++ ) {            
             if ( this.connections[ i ] == null ) {
            	  try
            	  {
            		  this.connections[ i ] = new CatanServer( connection , i, this);
            	  } 
                  break;
             }
         }*/
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
	public static void main(String[] args) {
		int port = 9534;
		if(args.length == 1)
		{
			port = Integer.parseInt(args[0]);
		}
		try
		{
			CatanServerManager server = new CatanServerManager(port);
		}
		catch(IOException e)
		{
			System.out.println(e.getMessage());
		}
	}

}
