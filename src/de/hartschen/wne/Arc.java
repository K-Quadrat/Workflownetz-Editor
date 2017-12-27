package de.hartschen.wne;

import java.awt.*;

/**
 * Die Klasse implementiert die Kanten.
 * 
 * @author Jens Hartschen
 *
 */
/**
 * @author Jens Hartschen
 *
 */
public class Arc {
	private String id;
	private String source;
	private String target;
	private Point from, to; // die zwei Punkte, die die Kanten-Linie darstellen
	private double phi; // Rotationswinkel
	private Polygon arrow;
	private double s; // Seitenlaenge der Kante
	private double xOld, yOld; // x-,y-Werte vor der Rotation,relativ zur Kantenpitze
	private double xNew, yNew; // x-,y-Werte nach der Rotation,absolut
	private double xHead, yHead; // x-,y-Werte der Kantenspitze

	/**
	 * Konstruktor um ein Kanten Objekt zu erstellen.
	 * 
	 * @param id
	 * @param source
	 * @param target
	 * @param k1
	 * @param k2
	 * @param length
	 */
	public Arc(String id, String source, String target, Point k1, Point k2, double length) {

		this.id = id;
		this.source = source;
		this.target = target;

		this.from = k1;
		this.to = k2;
		this.s = length;
		phi = Math.atan(((double) to.y - (double) from.y) / ((double) to.x - (double) from.x));
		if (to.x < from.x)
			phi = phi + Math.PI;
		// die Kante zeichnen
		arrow = new Polygon();
		arrow.addPoint(to.x, to.y); // Kantenspitze
		// Die beiden anderen Punkte der Kante muessen um den Winkel phi relative zur
		// Kantenspitze rotiert werden.
		// x_rot = x*cos(phi) - y*sin(phi)
		// y_rot = x*sin(phi) + y*cos(phi)
		xHead = (double) to.x;
		yHead = (double) to.y;
		// erster Punkt
		xOld = (double) to.x - s - xHead;
		yOld = (double) to.y + s / 2.5 - yHead;
		xNew = xOld * Math.cos(phi) - yOld * Math.sin(phi) + xHead;
		yNew = xOld * Math.sin(phi) + yOld * Math.cos(phi) + yHead;
		arrow.addPoint((int) xNew, (int) yNew);
		// zweiter Punkt
		yOld = (double) to.y - s / 2.5 - yHead;
		xNew = xOld * Math.cos(phi) - yOld * Math.sin(phi) + xHead;
		yNew = xOld * Math.sin(phi) + yOld * Math.cos(phi) + yHead;
		arrow.addPoint((int) xNew, (int) yNew);

	}

	/**
	 * Die Methode liefert die id zurück.
	 * 
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Die Methode setzt die id.
	 * 
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Die Methode liefert die quell node id zurück.
	 * 
	 * @return the source
	 */
	public String getSource() {
		return source;
	}

	/**
	 * Die Methode setzt die quell node id.
	 * 
	 * @param source
	 *            the source to set
	 */
	public void setSource(String source) {
		this.source = source;
	}

	/**
	 * Die Methode liefert die ziel node id zurück.
	 * 
	 * @return the target
	 */
	public String getTarget() {
		return target;
	}

	/**
	 * Die Methode setzt die ziel node id.
	 * 
	 * @param target
	 *            the target to set
	 */
	public void setTarget(String target) {
		this.target = target;
	}

	/**
	 * Die Methode liefert die Quellkoordinaten zurück.
	 * 
	 * @return Point from
	 */
	public Point getFrom() {
		return from;
	}

	/**
	 * Die Methode setzt die Quellkooardinaten.
	 * 
	 * @param from
	 */
	public void setFrom(Point from) {
		this.from = from;
	}

	/**
	 * Die Methode liefert die Zielkoordinaten
	 * 
	 * @return Point to
	 */
	public Point getTo() {
		return to;
	}

	/**
	 * Die Methode setzt die Zielkooardinaten.
	 * 
	 * @param to
	 * 
	 */
	public void setTo(Point to) {
		this.to = to;
	}

	/**
	 * Die Methode liefert die Kantenspitze zurück.
	 * 
	 * @return Polygon arrow
	 */
	public Polygon getArrow() {
		return arrow;
	}

	/**
	 * Die Methode setzt die Kantenspitze.
	 * 
	 * @param arrow
	 */
	public void setArrow(Polygon arrow) {
		this.arrow = arrow;
	}

	/**
	 * Die Methode ändert die Koordinaten und Größe der Kante.
	 * 
	 * @param k1
	 * @param k2
	 * @param length
	 */
	public void modifyArc(Point k1, Point k2, double length) {

		this.from = k1;
		this.to = k2;
		this.s = length;
		phi = Math.atan(((double) to.y - (double) from.y) / ((double) to.x - (double) from.x));
		if (to.x < from.x)
			phi = phi + Math.PI;
		// die Kante zeichnen
		// Kante = new Polygon();
		arrow.reset();
		arrow.addPoint(to.x, to.y); // Kantenspitze
		// Die beiden anderen Punkte der Kante muessen um den Winkel phi relative zur
		// Kantenspitze rotiert werden.
		// x_rot = x*cos(phi) - y*sin(phi)
		// y_rot = x*sin(phi) + y*cos(phi)
		xHead = (double) to.x;
		yHead = (double) to.y;
		// erster Punkt
		xOld = (double) to.x - s - xHead;
		yOld = (double) to.y + s / 2.5 - yHead;
		xNew = xOld * Math.cos(phi) - yOld * Math.sin(phi) + xHead;
		yNew = xOld * Math.sin(phi) + yOld * Math.cos(phi) + yHead;
		arrow.addPoint((int) xNew, (int) yNew);
		// zweiter Punkt
		yOld = (double) to.y - s / 2.5 - yHead;
		xNew = xOld * Math.cos(phi) - yOld * Math.sin(phi) + xHead;
		yNew = xOld * Math.sin(phi) + yOld * Math.cos(phi) + yHead;
		arrow.addPoint((int) xNew, (int) yNew);

	}

	/**
	 * Die Mathode zeigt ob auf eine Kantenspitze geklickt wurde.
	 * 
	 * @param pX
	 *            Maus click Koordinate
	 * @param pY
	 *            Maus click Koordinate
	 * @return Ausgewählte Kantenspitze
	 */
	public boolean containsPoint(int pX, int pY) {
		return Math.abs(this.to.x - pX) <= 30 && Math.abs(this.to.y - pY) <= 30;
	}

}