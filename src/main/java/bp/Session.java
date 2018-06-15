package bp;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Session Cria e gerencia a sessão de brainstorm.
 * @author Matheus Rangel
 */
public class Session {

	User owner;
	String description;
	SessionPhase phase;
	int votingLimit;
	ArrayList<Idea> ideas;
	ArrayList<User> participants;
	
	/**
	 * Cria a sessão.
	 * @param owner Dono da sessão, usuario que deseja criar a sessão.
	 * @param description Descriçào do problema que será discutido na sessão.
	 * @param votingLimit Quantidade limite de votos que uma ideia pode ter na sessão.
	 */
	public Session(User owner, String description, int votingLimit) {
		this.votingLimit = votingLimit;
		this.owner = owner;
		this.description = description;
		this.phase = SessionPhase.WELCOME;
		this.ideas = new ArrayList<Idea>();
		this.participants = new ArrayList<User>();
		this.participants.add(owner);
	}
	/**
	 * Avança a fase da sessão.
	 * @return A fase atual da sessão.
	 */
	public SessionPhase nextPhase() {
		this.phase = this.phase.next();
		return this.phase;
	}

	/**
	 * 
	 * @return Fase atual da sessão.
	 */
	public SessionPhase getPhase() {
		return this.phase;
	}
	/**
	 * Adciona um nova idea para resolver o problema discutido na sessão.
	 * @param idea Ideia a ser adicionada.
	 */
	public void addIdea(Idea idea) {
		int index = -1;
		if(this.phase.equals(SessionPhase.BRAINSTORM)) {
			if(!this.ideas.contains(idea)) {
				this.ideas.add(idea);
			}else {
				index = this.ideas.indexOf(idea);
				System.err.println("addIdea: " + this.ideas.get(index).getAuthor().getUsername() +" ja teve essa ideia");
			}
		}else {
			System.err.println("addIdea: Sessão não está na fase de BrainStorm");
		}
	}
	/**
	 * 
	 * @return Uma ArrayList com as ideias da sessão.
	 * 
	 */
	public ArrayList<Idea> getIdeas() {
		return this.ideas;
	}
	/**
	 * Rankeia as ideia de acordo com a quantidade de votos.
	 * @return ArrayList com as ideias rankeadas por voto, e que tenham pelo menos um voto.
	 */
	public ArrayList<Idea> rankIdeas() {
		this.ideas.sort(new Comparator<Idea>() {
			@Override
			public int compare(Idea a, Idea b) {
				return b.countVotes() - a.countVotes();
			}
		});
		ArrayList<Idea> rank = new ArrayList<Idea>();
		for (Idea idea : this.ideas) {
			if(idea.countVotes() == 0){
				break;
			}
			rank.add(idea);
		}
		return rank;
	}
	/**
	 * Adiciona um participante a sessão apenas se a sessão estiver na fase WELCOME.
	 * @param participant Usuario a ser adicionado.
	 * @return True se conseguir adicionar.
	 */
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
	/**
	 * Remove Participante da sessão.
	 * @param participant Usuario a ser removido.
	 * @return true se conseguir remover o usuario.
	 */
	public boolean removeParticipant(User participant) {
		if(participant.equals(this.owner)) {
			System.err.println("removeParticipant: Não é possivel remover o dono da sessão");
			return false;
		}
		return this.participants.remove(participant);
	}
	/**
	 * @return Uma ArrayList com todos os participantes.
	 */
	public ArrayList<User> getParticipants() {
		return this.participants;
	}
	/**
	 * @return O dono da sessão.
	 */
	public User getOwner() {
		return owner;
	}


}