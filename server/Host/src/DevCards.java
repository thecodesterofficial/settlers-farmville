// Base structure modeled around a stack overflow answer
// http://stackoverflow.com/a/7996474

// This method of implementing development cards
// was chosen simply because it was fresh in my mind.
// If we choose for a more flexible setup to be
// extended later, we may prefer to have them designed
// as a full class instead of as an enum.

public enum DevCards {
	KNIGHT		(0),
	ROADS		(1),
	SURPLUS		(2),
	MONOPOLY	(3),
	LIBRARY		(4),
	MARKET		(5),
	GREATHALL	(6),
	CHAPEL		(7),
	UNIVERSITY	(8);
	
	private int _value;
	
	DevCards(int Value) {
		this._value = Value;
	}
	
	public int getValue() {
		return _value;
	}
	
	public static DevCards fromInt(int i) {
		for (DevCards b : DevCards .values()) {
			if (b.getValue() == i) {return b; }
		}
		return null;
	}
	
	public String getType() {
		switch(this._value) {
		case 0:
			return "Knight";
		case 1: case 2: case 3:
			return "Progress";
		case 4: case 5: case 6: case 7: case 8:
			return "Victory";
		default:
			return "Undefined";
		}
	}
}
