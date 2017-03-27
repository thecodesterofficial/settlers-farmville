import java.io.IOException;

public class Main {
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
