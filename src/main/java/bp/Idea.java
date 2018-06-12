package bp;

import java.util.List;

/**
 * Idea
 */
public class Idea {
	//Session session; atributo não necessario
	String description;
	User author;
	List<User> voters;
	

	public Idea(String description, User author) {
		super();
		this.description = description;
		this.author = author;
	}

	public void registerVote(User u) {
		voters.
	}

	public void reclaimVote(User u) {
	}

	public int countVotes() {
		return 0;
	}

}