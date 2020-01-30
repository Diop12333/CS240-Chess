package chess;

public class Movement
{
	Node head;
	
	public class Node
	{
		char piece;
		String location;
		Node next;
		
		Node(char p, String l)
		{
			piece = p;
			location = l;
		}
	}
}
