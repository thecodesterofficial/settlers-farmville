
public class GameState {
	CatanServerManager manager;
	private String gamestate;
	private String username;
	
	public enum Allowed_States {
		"lobby", "play", "win", "lose";
	}
	
	public String GetState() {
		return gamestate;
	}
	
	public boolean ChangeState(Allowed_States message) {
		// Message will come in the form of GameState: condition.
		// Possible gamestates: lobby, play, win, lose.
		String[] blocks = message.split("\\s+");
		String tmpstate = blocks[1];
		
		// You should be able to return to the lobby at any state of the game.
		if (tmpstate == "lobby") {
			gamestate = "lobby";
			return true;
		}
		
		// To avoid complications of restarting the game, you can only enter the play state if in lobby.
		else if (tmpstate == "play") {
			if (gamestate == "lobby") {
				gamestate = "play";
				return true;
			}
			else {
				return false;
			}
		}
		
		// Checks whether the player won or lost and updates their state accordingly.
		// Win or lose should only happen from the play state.
		else if (tmpstate == "win" || tmpstate == "lose") {
			if (gamestate == "play") {
				gamestate = tmpstate;
				return true;
			}
			else {
				return false;
			}
		}
		
		else {
			return false;
		}
	}

}
