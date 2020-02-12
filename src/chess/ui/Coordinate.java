package chess.ui;

// Refers to chess board square
// 0-indexed, y goes down
public class Coordinate {
	private int x;
	private int y;
	
	public Coordinate(int x, int y) {
		if (x < 0 || y < 0) throw new IllegalArgumentException("Negative coordinate");
		
		this.x = x;
		this.y = y;
	}
	
	public int getX() { return x; }
	public int getY() { return y; }
	
	public Coordinate shift(int dx, int dy) {
		return new Coordinate(x + dx, y + dy);
	}
}
