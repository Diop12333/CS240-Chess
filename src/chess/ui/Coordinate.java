package chess.ui;

// Refers to chess board square
// 0-indexed, y goes down
public class Coordinate extends XY {
	public Coordinate(XY xy) {
		this(xy, null);
	}
	
	public Coordinate(XY xy, XY dimensions) {
		this(xy.getX(), xy.getY(), dimensions);
	}
	
	public Coordinate(int x, int y) {
		this(x, y, null);
	}
	
	public Coordinate(int x, int y, XY dimensions) {
		super(x, y);
		
		if (
			x < 0 && y < 0 ||
			dimensions != null && (x >= dimensions.getX() || y >= dimensions.getY())
		) throw new IllegalArgumentException("Invalid coordinate");
	}
	
	public Coordinate shift(XY dxdy) { return shift(dxdy.getX(), dxdy.getY()); }
	public Coordinate shift(int dx, int dy) {
		return new Coordinate(getX() + dx, getY() + dy);
	}
}
