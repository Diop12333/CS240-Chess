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
	
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
	
	public boolean equals(XY xy) {
		return getX() == xy.getX() && getY() == xy.getY();
	}
}
