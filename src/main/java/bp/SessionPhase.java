package bp;

public enum SessionPhase {
	WELCOME, // welcoming new participants
	BRAINSTORM, // creating new ideas
	VOTING, // voting on the ideas
	RANK; // final report
	private static SessionPhase [] vals = values();
	public SessionPhase next() {
		if(this.ordinal() < vals.length - 1)
			return vals[this.ordinal() + 1]; 
		else
			return vals[this.ordinal()];
	}
};