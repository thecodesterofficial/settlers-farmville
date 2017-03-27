import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class ConnectionManager {
	private static ConnectionManager instance;
	List<CatanServer> connections = new ArrayList<CatanServer>();
    final public static int MAX_CLIENTS = 4;
	private ConnectionManager()
	{
		
	}
	public static ConnectionManager instance()
	{
		if(instance == null)
		{
			instance = new ConnectionManager();
		}
		return instance;
	}
	public void assignConnection( Socket connection ) {
    	if(this.connections.size() < MAX_CLIENTS)
    	{
    		try
    		{
    			this.connections.add(new CatanServer(connection, connections.size()));
    		}
    		catch(IOException e)
      	  	{
      		  	System.out.println(e.getMessage());
      	  	}
    	}
    }
	public void Dispatch(String message)
    {
    	for(CatanServer conn : connections)
    	{
    		conn.Send(message);
    	}
    }
}
