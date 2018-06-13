package bp;

import java.util.Comparator;

public class IdeaComparator implements Comparator<Idea>{
	@Override
	public int compare(Idea a, Idea b) {
		return a.countVotes() < b.countVotes() ? -1 : a.countVotes() == b.countVotes() ? 0 : 1;
	}
}
