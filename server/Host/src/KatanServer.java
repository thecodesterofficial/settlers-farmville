import java.io.IOException;
import java.net.Socket;

public class KatanServer extends Thread {
	private String username;
	private int id;
	private Socket connection;
	public KatanServer( Socket connection , int id ) {
        this.id = id;
        this.connection = connection;
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
