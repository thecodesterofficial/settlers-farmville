import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class CatanServerManager {
	
	final private ServerSocket socket;
    final public static int MAX_CLIENTS = 4;
    final private CatanServer[] connections = new CatanServer[ MAX_CLIENTS ];
    private int turn = 0;
    public CatanServerManager( int port ) throws IOException {
    	this.socket = new ServerSocket( port );
        Start();
    }

  
    public void Start() throws IOException {
        while ( true ) {
             System.out.println("Waiting for a connection.");
             Socket connection = socket.accept();
             assignConnection( connection );
        }
    }
    public void Dispatch(String message)
    {
    	for ( int i = 0 ; i < MAX_CLIENTS && this.connections[ i ] != null; i++ ) {    
    		System.out.println("made it to a connection");
            connections[i].Send(message);
        }
    }
    
    public void StartGame()
    {
    	Dispatch("game start");
    
    	Dispatch("player turn " + this.turn);
    	// Other stuff to happen at start of game
    }
    
    public void assignConnection( Socket connection ) {
         
    	 System.out.println("Assigning a connection...");
         for ( int i = 0 ; i < MAX_CLIENTS ; i++ ) {            
             if ( this.connections[ i ] == null ) {
            	  try
            	  {
            		  this.connections[ i ] = new CatanServer( connection , i, this);
            	  } catch(IOException e)
            	  {
            		  System.out.println(e.getMessage());
            	  }
                  break;
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
