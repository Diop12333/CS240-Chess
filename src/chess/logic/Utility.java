package chess.logic;

import java.util.Collection;
import java.util.Random;

public final class Utility {
	private Utility() {}
	
	private static final Random rand = new Random();
	public static <T> T randSelect(Collection<T> coll) {
		if (coll.isEmpty()) return null;
		
		int randIdx = rand.nextInt(coll.size());
		
		int idx = 0;
		for (T elem : coll) {
			if (idx == randIdx) return elem;
			
			idx++;
		}
		
		return null;
	}
}
