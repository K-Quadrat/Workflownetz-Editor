
public class Node {
	private String id;
	private int x;
	private int y;
	private int radius;
	private ENode nodeType;
	private String name;
	private boolean markedOrActivated;
	
	public Node(String id, int x, int y, int radius, ENode nodeType, String name, boolean markedOrActivated) {
		
		this.id = id;
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.nodeType = nodeType;
		this.name = name;
		this.markedOrActivated = markedOrActivated;
	}
	
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}


	/**
	 * @return the nodeType
	 */
	public ENode getNodeType() {
		return nodeType;
	}

	/**
	 * @param nodeType the nodeType to set
	 */
	public void setNodeType(ENode nodeType) {
		this.nodeType = nodeType;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the markedOrActivated
	 */
	public boolean isMarkedOrActivated() {
		return markedOrActivated;
	}

	/**
	 * @param markedOrActivated the markedOrActivated to set
	 */
	public void setMarkedOrActivated(boolean markedOrActivated) {
		this.markedOrActivated = markedOrActivated;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	// pX, pY mouse click coordinates
	public boolean containsPoint(int pX, int pY) {
		return Math.abs(this.x - pX) <= radius && Math.abs(this.y - pY) <= radius;
	}

}
