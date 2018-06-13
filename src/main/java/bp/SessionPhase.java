package bp;

public enum SessionPhase {
	WELCOME, // welcoming new participants
	BRAINSTORM, // creating new ideas
	VOTING, // voting on the ideas
	RANK; // final report
	private static SessionPhase [] vals = values();
	public SessionPhase next() {
		return vals[(this.ordinal() + 1) % vals.length];
	}
};