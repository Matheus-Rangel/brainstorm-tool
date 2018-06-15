package bp;

import java.util.Iterator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * SessionTest
 */
public class SessionTest {
	User[] users= { new User("Fulano"), new User("Cicrano"), new User("Beltrano") };;
	static final String DESCRIPTION = "Teste";
	Session session;

	@Before
	public void setUp() {
		this.session = new Session(users[0], DESCRIPTION, 3);
	 }

	/**
	 * Na criação de um objeto
	 */
	@Test
	public void creation() {
		assert (this.session.getOwner().equals(users[0]));
		assert (this.session.getPhase().compareTo(SessionPhase.WELCOME) == 0);
		assert (this.session.getIdeas().size() == 0);
		assert (this.session.getParticipants().size() == 1);
	}

	@Test
	public void nextPhase() {
		assert (this.session.nextPhase().compareTo(SessionPhase.BRAINSTORM) == 0);
		assert (this.session.getPhase().compareTo(SessionPhase.BRAINSTORM) == 0);
		assert (this.session.nextPhase().compareTo(SessionPhase.VOTING) == 0);
		assert (this.session.getPhase().compareTo(SessionPhase.VOTING) == 0);
		assert (this.session.nextPhase().compareTo(SessionPhase.RANK) == 0);
		assert (this.session.getPhase().compareTo(SessionPhase.RANK) == 0);
		assert (this.session.nextPhase().compareTo(SessionPhase.RANK) == 0);
		assert (this.session.getPhase().compareTo(SessionPhase.RANK) == 0);
	}

	@Test
	public void addParticipant() {
		assert (this.session.getParticipants().size() == 1);
		this.session.addParticipant(this.users[1]);
		assert (this.session.getParticipants().size() == 2);
		this.session.addParticipant(this.users[1]);
		assert (this.session.getParticipants().size() == 2);
		this.session.nextPhase();
		this.session.addParticipant(this.users[1]);
		assert (this.session.getParticipants().size() == 2);
	}
	
	@Test
	public void removeParticipant() {
		assert (!this.session.removeParticipant(this.users[0])); ///Não pode remover o dono
		this.session.addParticipant(this.users[1]);
		this.session.addParticipant(this.users[2]);
		assert (this.session.removeParticipant(this.users[1]));//Removeu usuario 1
		this.session.removeParticipant(this.users[1]);
		assert (!this.session.removeParticipant(this.users[1]));//Tentou remover usuario 1 novamente
	}
	@Test
	public void registerIdea() {
		Idea idea = new Idea(this.session, this.users[1], "Fazer um novo teste");
		this.session.addIdea(idea);
		this.session.addParticipant(this.users[1]);
		assert(this.session.getIdeas().size() == 0);//session.phase == Welcome
		this.session.nextPhase();
		this.session.addIdea(idea);
		assert(this.session.getIdeas().size() == 1);//session.phase == BrainStorm
		this.session.addIdea(idea);
		assert(this.session.getIdeas().size() == 1);//mesma ideia.
		for (int i = 0; i < 10; i++) {
			idea = new Idea(this.session, this.users[1], "Fazer um novo teste" + i);
			this.session.addIdea(idea);
		}
		assert(this.session.getIdeas().size() == 11);
	}
	@Test
	public void rankIdea() {
		registerIdea();
		this.session.nextPhase();
		List<Idea> rank;
		rank = this.session.rankIdeas();
		assert(rank.size() == 0);
		
		rank = this.session.getIdeas();
		for (Iterator<Idea> iterator = rank.iterator(); iterator.hasNext();) {
			Idea idea = (Idea) iterator.next();
			idea.registerVote(this.users[1]);
			idea.registerVote(this.users[2]);
			if(iterator.hasNext()) {
				idea = (Idea) iterator.next();
				idea.registerVote(this.users[0]);
			}
			if(iterator.hasNext()) {
				iterator.next();
			}
		}
		rank = this.session.rankIdeas();
		int votes = rank.get(0).countVotes();
		for(Idea idea : rank) {
			assert(idea.countVotes() <= votes && idea.countVotes() > 0);
			votes = idea.countVotes();
			System.out.println(idea.print());
		}
	}
}