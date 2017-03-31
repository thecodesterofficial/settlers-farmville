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
	private static CatanServerManager instance;
	private ServerSocket socket;
    private CatanServerManager() {
    	board = new GameBoard(true);
    }
    public static CatanServerManager instance()
    {
    	if(instance == null)
    	{
    		instance = new CatanServerManager();
    	}
    	return instance;
    }
    
    
    public void Start(int port) {
    	try
    	{
    	    this.socket = new ServerSocket(port);
	        while ( true ) {
	             Socket connection = socket.accept();
	             ConnectionManager.instance().assignConnection( connection );
	        }
    	}
    	catch(IOException e)
    	{
    		System.out.println(e.getMessage());
    	}
    }
    private GameBoard board;
    public void  NewPlayer(String name)
    {
    	ConnectionManager.instance().Dispatch("player new " + name);
    }
	    
    
    public void StartGame()
    {
    	ConnectionManager.instance().Dispatch("game init");
    	
    	List<Hex> hexes = board.allHexes;
    	for(int i = 0; i < hexes.size(); i++)
    	{
    		Hex hex = hexes.get(i);
    		ConnectionManager.instance().Dispatch("game init hex " + i + " " + hex.GetHexType() + " " + hex.number);
    	}
    	ConnectionManager.instance().Dispatch("game init robber " + board.rob.location);
    	ConnectionManager.instance().Dispatch("game start");
    }
    public GameBoard GetGameBoard()
    {
    	return this.board;
    }

}
