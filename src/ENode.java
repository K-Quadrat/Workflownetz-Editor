
public enum ENode {
	PLACE('p'), TRANSITION('t');
	
	final char charRepresentation;
	
	private ENode(char charRepresentation) {
		this.charRepresentation = charRepresentation;
		
	}
	
	public char getCharRepresentation() {
		return this.charRepresentation;
		
	}

}
