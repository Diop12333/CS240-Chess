package chess;

public class Movement
{
	Node head;
	
	public class Node
	{
		char piece;			// Name of Chess Piece 
		String location;	// Where The Chess Piece is Going
		Node next;
		
		Node(char p, String l)
		{
			piece = p;
			location = l;
		}
	}
}
