
public class Bank {

	// See enum Resources
	// Counts based off board game's "Game Components"
	// list:  95 total cards between 5 resources
	private int[] currency = {19,19,19,19,19};
	
	// See enum DevCards
	// Counts based off board game's "Game Components"
	// list and almanac: 14 Knight cards, 6 Progress
	// Cards (2 each of 3 types), and 5 Victory Point cards
	private int[] cards_in_deck = {14,2,2,2,1,1,1,1,1};
	
	public Bank() {
		
	}

	int getResourceCount(Resources type) {
		return currency[type.getValue()];
	}
	
	void changeResourceCount(Resources type, int count) {
		currency[type.getValue()]+=count;
	}
	
	void changeCardCount(DevCards type, int count) {
		cards_in_deck[type.getValue()]+=count;
	}
	
	// While I am adding these functions here as they
	// involve management of the bank's resources,
	// feel free to move them to elsewhere.
	@SuppressWarnings("unused")
	boolean buyRoad(PlayerStorage customer) {
		boolean purchased = false;
		//TODO
		if (purchased) {
			customer.changeResourceCount(Resources.BRICK, -1);
			customer.changeResourceCount(Resources.LUMBER, -1);
			this.changeResourceCount(Resources.BRICK, 1);
			this.changeResourceCount(Resources.LUMBER, 1);
		}
		return purchased;
	}
	
	@SuppressWarnings("unused")
	boolean buySettlement(PlayerStorage customer) {
		boolean purchased = false;
		//TODO
		if (purchased) {
			customer.changeResourceCount(Resources.BRICK, -1);
			customer.changeResourceCount(Resources.LUMBER, -1);
			customer.changeResourceCount(Resources.WOOL, -1);
			customer.changeResourceCount(Resources.GRAIN, -1);
			this.changeResourceCount(Resources.BRICK, 1);
			this.changeResourceCount(Resources.LUMBER, 1);
			this.changeResourceCount(Resources.WOOL, 1);
			this.changeResourceCount(Resources.GRAIN, 1);
		}
		return purchased;
	}
	
	@SuppressWarnings("unused")
	boolean buyCity(PlayerStorage customer) {
		boolean purchased = false;
		//TODO
		if (purchased) {
			customer.changeResourceCount(Resources.ORE, -3);
			customer.changeResourceCount(Resources.GRAIN, -2);
			this.changeResourceCount(Resources.ORE, 3);
			this.changeResourceCount(Resources.GRAIN, 2);
		}
		return purchased;
	}
	
	@SuppressWarnings("unused")
	boolean buyCard(PlayerStorage customer) {
		DevCards purchased = null;
		//TODO
		if (purchased != null) {
			customer.changeResourceCount(Resources.ORE, -1);
			customer.changeResourceCount(Resources.WOOL, -1);
			customer.changeResourceCount(Resources.GRAIN, -1);
			this.changeResourceCount(Resources.ORE, 1);
			this.changeResourceCount(Resources.WOOL, 1);
			this.changeResourceCount(Resources.GRAIN, 1);
			customer.buyCard(purchased);
			this.changeCardCount(purchased, -1);
			return true;
		}
		return false;
	}
}
