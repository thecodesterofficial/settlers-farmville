import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static int port = 9534;
	public static void main(String[] args) {
	
		try {
			Socket socket = new Socket("127.0.0.1", Client.port);
	        BufferedReader in = new BufferedReader(
	                new InputStreamReader(socket.getInputStream()));
	        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
	        out.println("connect techboysquared");
	        String response = in.readLine();
	        if(response.equals("connect good"))
	        {
	        	System.out.println("Connection Established!");
	        	out.println("game start");
	        	while(true)
	        	{
	        		while(in.ready())
	        		{
	        			System.out.println(in.readLine());
	        		}
	        	}
	        }
	        else
	        {
	        	
	        }
	        
		} catch(IOException e)
		{
			System.out.println(e.getMessage());
		}
	}

}
