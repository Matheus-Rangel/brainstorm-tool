package bp;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;

/**
 * SessionTest
 */
public class SessionTest {
	User[] users = { new User("Fulano"), new User("Cicrano"), new User("Beltrano") };
	final static String DESCRIPTION = "Teste";
	Session session = new Session(users[0], DESCRIPTION);

	// @Before
	// public void setUp() {
	// User u = new User("Fulano");
	// this.session = new Session(u);
	// }

	/**
	 * Na criação de um objeto
	 */
	@Test
	public void creation() {
		assert (session.getOwner().equals(users[0]));
		assert (session.getPhase().compareTo(SessionPhase.WELCOME) == 0);
		assert (session.getIdeas().size() == 0);
		assert (session.getParticipants().size() == 1);
	}

	@Test
	public void nextPhase() {
		assert (session.nextPhase().compareTo(SessionPhase.BRAINSTORM) == 0);
		assert (session.getPhase().compareTo(SessionPhase.BRAINSTORM) == 0);
		assert (session.nextPhase().compareTo(SessionPhase.VOTING) == 0);
		assert (session.getPhase().compareTo(SessionPhase.VOTING) == 0);
		assert (session.nextPhase().compareTo(SessionPhase.RANK) == 0);
		assert (session.getPhase().compareTo(SessionPhase.RANK) == 0);
		assert (session.nextPhase().compareTo(SessionPhase.RANK) == 0);
		assert (session.getPhase().compareTo(SessionPhase.RANK) == 0);
	}

	@Test
	public void addParticipant() {
		assert (session.getParticipants().size() == 1);
		session.addParticipant(users[1]);
		assert (session.getParticipants().size() == 2);
		session.addParticipant(users[1]);
		assert (session.getParticipants().size() == 2);
		session.nextPhase();
		session.addParticipant(users[1]);
		assert (session.getParticipants().size() == 2);
	}
	
	@Test
	public void removeParticipant() {
		assert (!session.removeParticipant(users[0])); ///Não pode remover o dono
		session.addParticipant(users[1]);
		session.addParticipant(users[2]);
		assert (session.removeParticipant(users[1]));//Removeu usuario 1
		session.removeParticipant(users[1]);
		assert (!session.removeParticipant(users[1]));//Tentou remover usuario 1 novamente
	}
}