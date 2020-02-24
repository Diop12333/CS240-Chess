package chess.ui;

// Refers to chess board square
// 0-indexed, y goes down
public class Coordinate extends XY {
	public Coordinate(XY xy) {
		this(xy.getX(), xy.getY());
	}
	
	public Coordinate(int x, int y) {
		super(x, y);
	}
	
	public Coordinate shifted(XY dxdy) { return shifted(dxdy.getX(), dxdy.getY()); }
	public Coordinate shifted(int dx, int dy) {
		return new Coordinate(getX() + dx, getY() + dy);
	}
	
	public boolean equals(Coordinate otherCoord) {
		return getX() == otherCoord.getX() && getY() == otherCoord.getY();
	}
}
