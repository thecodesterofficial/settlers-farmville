import java.io.IOException;

public class Main {
	public static void main(String[] args) {
		int port = 9534;
		if(args.length == 1)
		{
			port = Integer.parseInt(args[0]);
		}
		CatanServerManager.instance().Start(9534);
		
	}
}
