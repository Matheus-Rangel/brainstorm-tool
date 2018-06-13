package bp;

/**
 * User
 */
public class User {
	String username;
	int votes;

	public User(String username) {
		this.username = username;
		this.votes = 0;
	}

	public void addVote() {
		votes++;
	}

	public void removeVote() {
		votes--;
	}

	public int getVotes() {
		return this.votes;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public boolean equals(User u) {
		if(u.getUsername().equals(this.username)) {
			return true;
		}else return false;
	}
}