package bp;

import java.util.List;

/**
 * Session
 */
public class Session {

	User owner;
	String description;
	SessionPhase phase;
	int votingLimit = 3;
	List<Idea> ideas;
	List<User> participants;

	public Session(User owner, String description) {
		this.owner = owner;
		this.description = description;
		this.phase = SessionPhase.WELCOME;
	}
	
	public SessionPhase nextPhase() {
		this.phase = this.phase.next();
		return this.phase;
	}

	public SessionPhase getPhase() {
		return this.phase;
	}

	public void addIdea(Idea idea) {
		int index = -1;
		if(!this.ideas.contains(idea)) {
			this.ideas.add(idea);
		}else {
			index = this.ideas.indexOf(idea);
			System.out.println("addIdea: " + this.ideas.get(index).getAuthor() +" ja teve essa ideia");
		}
	}
	
	public List<Idea> getIdeas() {
		return this.ideas;
	}

	public List<Idea> rankIdeas() {
		return ideas.sort(Comparator<Idea> c);
	}

	public boolean addParticipant(User participant) {
		return false;
	}

	public boolean removeParticipant(User participant) {
		return false;
	}

	public List<User> getParticipants() {
		return null;
	}

}