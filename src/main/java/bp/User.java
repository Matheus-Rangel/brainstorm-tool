package bp;

/**
 * User Armazena o o nome do usuario e quantidade de votos feita por ele.
 * @author Matheus Rangel
 */
public class User {
	String username;
	int votes;

	/**
	 * Gera um usuario a partir do nome de usuario.
	 * @param username Nome do usuario.
	 */
	public User(String username) {
		this.username = username;
		this.votes = 0;
	}
	/**
	 * Adiciona um voto ao contador de votos.
	 */
	public void addVote() {
		votes++;
	}
	/**
	 * Remove um voto do contador de votos.
	 */
	public void removeVote() {
		votes--;
	}
	/**
	 * @return Quantidade de votos feita pelo usuario.
	 */
	public int getVotes() {
		return this.votes;
	}
	/**
	 * @return Nome de usuario. 
	 */
	public String getUsername() {
		return this.username;
	}
	/**
	 * Compara se esse User é igual com um outro User.
	 * @param u Usuario para compararar.
	 * @return True se for igual.
	 */
	public boolean equals(User u) {
		if(u.getUsername().equals(this.username)) {
			return true;
		}else return false;
	}
}