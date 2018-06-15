package bp;

import java.util.ArrayList;

/**
 * Idea Armazena um idea de alguma sessão, com sua descrição, autor, e uma lista de User que votaram nessa idea.
 * @author Matheus Rangel.
 */
public class Idea {
	Session session;
	String description;
	User author;
	ArrayList<User> voters;
	
	/**
	 * Gera uma ideia recebendo a sessão da qual ela faz parte, um autor, e a descrição dela.
	 * @param session Sessão da qual ela faz parte.
	 * @param author Autor da idea.
	 * @param description Descrição da ideia.
	 */
	public Idea(Session session, User author, String description) {
		super();
		this.description = description;
		this.author = author;
		voters = new ArrayList<User>();
		this.session = session; 
	}
	/**
	 * Registra o voto de usuario nessa idea.
	 * @param u Usuario que está votando nessa idea.
	 */
	public void registerVote(User u) {
		if(this.session.votingLimit < voters.size()) {
			System.err.println("registerVote: Limite de votos alcançado");
			return;
		}
		if(this.author.equals(u)) {
			System.err.println("registerVote: O autor da ideia não pode votar nela mesma");
			return;
		}
		if(!session.getParticipants().contains(u)) {
			System.err.println("registerVote: Usuario fora da sessão");
			return;
		}
		if(this.session.getPhase().equals(SessionPhase.VOTING)) {
			if(!voters.contains(u))
				voters.add(u);
			else
				System.err.println("registerVote: Usuario" + u.getUsername() + " ja votou nessa ideia.");
		}else {
			System.err.println("registerVote: Sessão não está na fase se votação");
		}
	}
	/**
	 * Remove o voto de um usuario da ideia.
	 * @param u Usuario que deseja remover o voto.
	 */
	public void reclaimVote(User u) {
		if(!session.getPhase().equals(SessionPhase.VOTING)) {
			System.err.println("reclaimVote: Sessão não está na fase se votação");
			return;
		}
		if(voters.contains(u)) {
			voters.remove(u);
		}else
			System.err.println("reclaimVote: Usuario" + u.getUsername() + " não votou nessa ideia.");
	}
	/**
	 * 
	 * @return Quantidade de votos que a ideia recebeu.
	 */
	public int countVotes() {
		return voters.size();
	}
	/**
	 * 
	 * @return Descrição da ideia.
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * 
	 * @return Autor da ideia.
	 */
	public User getAuthor() {
		return author;
	}

	/**
	 * @return ArrayList de usuarios.
	 */
	public ArrayList<User> getVoters() {
		return voters;
	}
	/**
	 * Verificar se essa ideia é igual com Idea passada como paramentro.
	 * @param idea Ideia para comparar.
	 * @return True se for igual.
	 */
	public boolean equals(Idea idea) {
		if(description.equals(idea.getDescription())) {
			return true;
		}else 
			return false;
	}
	/**
	 * 
	 * @return Uma String contendo o username do autor, a descrição da ideia, e quantidade de votos que ele tem.
	 */
	public String print() {
		return this.author.getUsername() + ": "+  this.description + System.lineSeparator() + "Votes: " + this.countVotes();
	}
}