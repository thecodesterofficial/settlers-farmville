// Base structure modeled around a stack overflow answer
// http://stackoverflow.com/a/7996474

public enum Resources {
	GRAIN	(0), 
	BRICK	(1), 
	ORE		(2), 
	LUMBER	(3), 
	WOOL	(4);
	
	private int _value;
	
	Resources(int Value) {
		this._value = Value;
	}
	
	public int getValue() {
		return _value;
	}
	
	public static Resources fromInt(int i) {
		for (Resources b : Resources .values()) {
			if (b.getValue() == i) { return b; }
		}
		return null;
	}
}
