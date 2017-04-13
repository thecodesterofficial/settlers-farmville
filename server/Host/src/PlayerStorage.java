

public class PlayerStorage {


	// Not using an enum here (yet) since it would
	// likely be used primarily for this class.
	private int[] stock = {15,5,4};
	
	// See enum Resources
	private int[] currency = {0,0,0,0,0};
	
	// See enum DevCards
	private int[] cards_in_hand = {0,0,0,0,0,0,0,0,0};
	private int card_count = 0;
	boolean longest_road = false;
	boolean largest_army = false;
	
	private String username = "DUMMY STRING";
	//------------------------------------------------
	// May need a block for communication info
	// For example, the socket the player is using?
	// This is assuming that we use more than just the
	// username for communication, though.
	//------------------------------------------------
	
	public PlayerStorage() {
		
	}
	public PlayerStorage(String givenname) {
		username = givenname;
	}

	String getName() {
		return username;
	}
	
	int getStockRoads() { return stock[0]; }
	int getStockSettlements() { return stock[1]; }
	int getStockCities() { return stock[2]; }
	
	int getResourceCount(Resources type) {
		return currency[type.getValue()];
	}
	
	void changeResourceCount(Resources type, int count) {
		currency[type.getValue()]+=count;
	}
	
	// Card count stored as separate variable so that
	// we don't have to traverse the array each time
	// we check card count, but if it doesn't get called
	// much, we can remove this variable.
	int getCardCount() {
		return card_count;
	}
	
	// These currently assume that other functions handle
	// the actions and checks involved with purchasing
	// and playing dev cards, but that can be changed.
	void buyCard(DevCards bought) {
		cards_in_hand[bought.getValue()]++;
		card_count++;
	}
	void playCard(DevCards played) {
		cards_in_hand[played.getValue()]--;
		card_count--;
	}
	
	int getCurrentVP() {
		int VP = 0;
		if (longest_road) VP+=2;
		if (largest_army) VP+=2;
		// 1 VP for each settlement
		VP += 5 - stock[1];
		// 2 VP for each city
		VP += 8 - (2 * stock[2]);
		
		for (DevCards b : DevCards .values()) {
			if (b.getType() == "Victory")
				VP += cards_in_hand[b.getValue()];
		}
		
		return VP;
	}
}
