package chess.logic;

public class XY {
	private int x;
	private int y;
	
	public XY(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() { return x; }
	public int getY() { return y; }
	
	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof XY)) return false;
		
		XY xy = (XY) obj;
		return x == xy.getX() && y == xy.getY();
	}
	
	// Not a great hashing algorithm, but it does its job
	@Override
	public int hashCode() {
		return Integer.parseInt(Integer.toString(x) + Integer.toString(y));
	}
}
