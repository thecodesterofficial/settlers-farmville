import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class KatanServer extends Thread {
	private String username;
	private int id;
	private Socket connection;
	PrintWriter out;
	public KatanServer( Socket connection , int id ) throws IOException {
        this.id = id;
        this.connection = connection;
        
        out = new PrintWriter(connection.getOutputStream(), true);
        out.println("identity Mongoose");
        start();
    }

    @Override
    public void run() {
         while( !this.interrupted() ) {
             
         }
    }

    
    public void process( String message ) {

    }

   
    public void close() {
        try {
             this.connection.close();
             
        } catch ( IOException e ) {
             
        }
    }
}
