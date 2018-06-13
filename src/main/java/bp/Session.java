package bp;

import java.util.ArrayList;
import java.util.Comparator;
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
		this.ideas = new ArrayList<Idea>();
		this.participants = new ArrayList<User>();
		this.participants.add(owner);
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
			System.err.println("addIdea: " + this.ideas.get(index).getAuthor() +" ja teve essa ideia");
		}
	}
	
	public List<Idea> getIdeas() {
		return this.ideas;
	}

	public List<Idea> rankIdeas() {
		this.ideas.sort(new Comparator<Idea>() {
			@Override
			public int compare(Idea a, Idea b) {
				return a.countVotes() - b.countVotes();
			}
		});
		return this.ideas;
	}

	public boolean addParticipant(User participant) {
		if(this.phase.compareTo(SessionPhase.WELCOME) == 0) {
			if(this.participants.contains(participant)) {
				System.err.println("addParticipant: Usuario " + participant.getUsername() + " ja está na sessão");
				return false;
			}else {
				this.participants.add(participant);
				return true;
			}
		}else {
			System.err.println("addParticipant: Sessão não esta na fase de acohimento.");
			return false;
		}
	}

	public boolean removeParticipant(User participant) {
		if(participant.equals(this.owner)) {
			System.err.println("removeParticipant: Não é possivel remover o dono da sessão");
			return false;
		}
		return this.participants.remove(participant);
	}

	public List<User> getParticipants() {
		return this.participants;
	}
	
	public User getOwner() {
		return owner;
	}


}