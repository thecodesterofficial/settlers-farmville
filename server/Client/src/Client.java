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
	        out.println("dice");
	        String response = in.readLine();
	        System.out.println(response);
	        System.out.println("Exit");
		} catch(IOException e)
		{
			System.out.println(e.getMessage());
		}
	}

}