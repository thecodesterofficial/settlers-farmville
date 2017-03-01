import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class KatanServer extends Thread {
	private String username;
	private int id;
	private Socket connection;
	PrintWriter out;
	BufferedReader in;
	public KatanServer( Socket connection , int id ) throws IOException {
        this.id = id;
        this.connection = connection;
        
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
        			 process(data);
        		 }
        		 
        		
        	 }
        	 catch(Exception e)
        	 {
        		 System.out.println(e.getStackTrace().toString());
        		 return;        	
        	 }
             
         }
    }

    
    public void process( String message ) {
    	// parse strings and respond appropriately
    	
    	if(message.equals("dice"))
    	{
    		out.println("dice 2");
    	}
    	
    }

   
    public void close() {
        try {
             this.connection.close();
             
        } catch ( IOException e ) {
             
        }
    }
}
