
public class GameStateManager {
	enum States {
		Lobby
		
	}
	
	private static GameStateManager manager;

	public static GameStateManager instance() 
	{
		if(manager == null)
		{
			manager = new GameStateManager();
		}
		return manager;
	}
	
	
	private States state;
	
	private GameStateManager()
	{
		this.state = States.Lobby;
	}
	
	
}
