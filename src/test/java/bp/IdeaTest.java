package bp;

import org.junit.Before;
import org.junit.Test;

/**
 * IdeaTest
 */
public class IdeaTest {
	Session session;
	Idea idea;
	User user1, user2, user3;

	@Before
	public void setUp() {
		this.user1 = new User("fulano");//dono da sessao e ideia
		this.user2 = new User("cicrano");//votante
		this.user3 = new User("beltrano");//usuario fora da sessao
		this.session = new Session(user1, "Testar as ideias", 3);
		this.idea = new Idea(this.session, this.user1, "An idea");
		session.addParticipant(user2);
	}
	@Test
	public void creatingIdea() {
		assert(idea.getAuthor().equals(user1));
		assert(idea.getVoters().size() == 0);
	}

	@Test
	public void addVotes() {
		idea.registerVote(user1);
		idea.registerVote(user2);
		assert(idea.getVoters().size() == 0);
		this.session.nextPhase();
		session.addIdea(idea);
		assert(session.getIdeas().size() == 1);
		assert(session.getIdeas().get(0).equals(idea));
		idea.registerVote(user2);
		assert(session.getIdeas().get(0).countVotes() == 0);
		this.session.nextPhase();
		idea.registerVote(user1);
		idea.registerVote(user2);
		idea.registerVote(user3);
		assert(idea.getVoters().size() == 1);
		assert(idea.getVoters().get(0).equals(user2));
		return;
	}

	@Test
	public void removeVotes() {
		idea.reclaimVote(user2);
		assert(idea.getVoters().size() == 0);
		addVotes();
		idea.registerVote(user2);
		assert(idea.getVoters().size() == 1);
		idea.reclaimVote(user2);
		assert(idea.getVoters().size() == 0);
		idea.registerVote(user2);
		assert(idea.getVoters().size() == 1);
		session.nextPhase();
		idea.reclaimVote(user2);
		assert(idea.getVoters().size() == 1);	
	}
}