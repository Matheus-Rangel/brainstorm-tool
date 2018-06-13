package bp;

import java.util.ArrayList;
import java.util.List;

/**
 * Idea
 */
public class Idea {
	String description;
	User author;
	List<User> voters;
	

	public Idea(String description, User author) {
		super();
		this.description = description;
		this.author = author;
		voters = new ArrayList<User>();
	}

	public void registerVote(User u) {
		if(!voters.contains(u))
			voters.add(u);
		else
			System.out.println("registerVote: Usuario" + u.getUsername() + " ja votou nessa ideia.");
	}

	public void reclaimVote(User u) {
		if(voters.contains(u)) {
			voters.remove(u);
		}else
			System.out.println("reclaimVote: Usuario" + u.getUsername() + " não votou nessa ideia.");
	}

	public int countVotes() {
		return voters.size();
	}
	
	public String getDescription() {
		return description;
	}

	public User getAuthor() {
		return author;
	}

	public List<User> getVoters() {
		return voters;
	}

	public boolean equals(Idea idea) {
		if(description.equals(idea.getDescription())) {
			return true;
		}else 
			return false;
	}
}