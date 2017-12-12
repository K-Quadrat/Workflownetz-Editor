package de.hartschen.wne;

/**
 * 
 * Die Klasse definiert die nodes.
 * 
 * @author Jens Hartschen
 *
 */
public class Node {
	private String id;
	private int x;
	private int y;
	private int radius;
	private ENode nodeType;
	private String name;
	private boolean marking;

	/**
	 * Die Methode erstellt ein place Objekt.
	 * 
	 * @param id
	 * @param x
	 * @param y
	 * @param radius
	 * @param nodeType
	 * @param name
	 * @param marking
	 */
	public Node(String id, int x, int y, int radius, ENode nodeType, String name, boolean marking) {

		this.id = id;
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.nodeType = nodeType;
		this.name = name;
		this.marking = marking;
	}

	/**
	 * Die Methode erstellt ein transition Objekt.
	 * 
	 * @param id
	 * @param x
	 * @param y
	 * @param radius
	 * @param nodeType
	 * @param name
	 */
	public Node(String id, int x, int y, int radius, ENode nodeType, String name) {

		this.id = id;
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.nodeType = nodeType;
		this.name = name;
	}

	/**
	 * Die Methode liefert die node Id zurück.
	 * 
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Die Methode setzt die node Id.
	 * 
	 * @param node
	 *            id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Die Methode liefert den node type zurück.
	 * 
	 * @return the nodeType
	 */
	public ENode getNodeType() {
		return nodeType;
	}

	/**
	 * Die Methode setzt den node type.
	 * 
	 * @param nodeType
	 */
	public void setNodeType(ENode nodeType) {
		this.nodeType = nodeType;
	}

	/**
	 * Die Methode liefert den node namen zurück.
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Die Methode setzt den node type.
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Die Methode liefert den status der Markierung zurück.
	 * 
	 * @return true wenn markiert, false wenn nicht markiert
	 */
	public boolean getMarking() {
		return marking;
	}

	/**
	 * Die Methode setzt den Status der Makierung.
	 * 
	 * @param true
	 *            oder false
	 */
	public void setMarking(boolean marking) {
		this.marking = marking;
	}

	/**
	 * Die Methode liefert den node Radius zurück.
	 * 
	 * @return radius
	 */
	public int getRadius() {
		return radius;
	}

	/**
	 * Die Methode setzt den node Radius.
	 * 
	 * @param radius
	 */
	public void setRadius(int radius) {
		this.radius = radius;
	}

	/**
	 * Die Methode liefert die x Koordinate zurück.
	 * 
	 * @return x Koordinate
	 */
	public int getX() {
		return x;
	}

	/**
	 * Die Methode setzt die x Koordinate.
	 * 
	 * @param x
	 *            Koordinate
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Die Methode liefert die y Koordinate zurück.
	 * 
	 * @return y Koordinate
	 */
	public int getY() {
		return y;
	}

	/**
	 * Die Methode setzt die y Koordinate.
	 * 
	 * @param y
	 *            Koordinate
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Die Mathode zeigt ob auf eine node geklickt wurde.
	 * 
	 * @param pX
	 *            Maus click Koordinate
	 * @param pY
	 *            Maus click Koordinate
	 * @return Ausgewählte node
	 */
	public boolean containsPoint(int pX, int pY) {
		return Math.abs(this.x - pX) <= radius && Math.abs(this.y - pY) <= radius;
	}

}
