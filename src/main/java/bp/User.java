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
  void addVote() {
	  votes ++;
  }
  void removeVote() {
	  votes --;
  }
  int getVotes() {
	  return this.votes;
  }
}