package de.hartschen.wne;

import java.awt.*;

public class Arc {
	private String id;
	private String source;
	private String target;
	private Math m;
	private Point from, to; // die zwei Punkte, die die Pfeil-Linie darstellen
	private double phi; // Rotationswinkel
	private Polygon arrow;
	private double s; // Seitenlaenge des Pfeils
	private double xOld, yOld; // x-,y-Werte vor der Rotation,relativ zur Pfeilspitze
	private double xNew, yNew; // x-,y-Werte nach der Rotation,absolut
	private double xHead, yHead; // x-,y-Werte der Pfeilspitze

	public Arc(String id, String source, String target, Point k1, Point k2, double length) {

		this.id = id;
		this.source = source;
		this.target = target;

		this.from = k1;
		this.to = k2;
		this.s = length;
		phi = m.atan(((double) to.y - (double) from.y) / ((double) to.x - (double) from.x));
		if (to.x < from.x)
			phi = phi + m.PI;
		// den Pfeil zeichnen
		arrow = new Polygon();
		arrow.addPoint(to.x, to.y); // Pfeilspitze
		// Die beiden anderen Punkte des Pfeils muessen um den Winkel phi relative zur
		// Pfeilspitze rotiert werden.
		// x_rot = x*cos(phi) - y*sin(phi)
		// y_rot = x*sin(phi) + y*cos(phi)
		xHead = (double) to.x;
		yHead = (double) to.y;
		// erster Punkt
		xOld = (double) to.x - s - xHead;
		yOld = (double) to.y + s / 2.5 - yHead;
		xNew = xOld * m.cos(phi) - yOld * m.sin(phi) + xHead;
		yNew = xOld * m.sin(phi) + yOld * m.cos(phi) + yHead;
		arrow.addPoint((int) xNew, (int) yNew);
		// zweiter Punkt
		yOld = (double) to.y - s / 2.5 - yHead;
		xNew = xOld * m.cos(phi) - yOld * m.sin(phi) + xHead;
		yNew = xOld * m.sin(phi) + yOld * m.cos(phi) + yHead;
		arrow.addPoint((int) xNew, (int) yNew);

	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the source
	 */
	public String getSource() {
		return source;
	}

	/**
	 * @param source
	 *            the source to set
	 */
	public void setSource(String source) {
		this.source = source;
	}

	/**
	 * @return the target
	 */
	public String getTarget() {
		return target;
	}

	/**
	 * @param target
	 *            the target to set
	 */
	public void setTarget(String target) {
		this.target = target;
	}

	/**
	 * @return the von
	 */
	public Point getVon() {
		return from;
	}

	/**
	 * @param von
	 *            the von to set
	 */
	public void setVon(Point von) {
		this.from = von;
	}

	/**
	 * @return the nach
	 */
	public Point getNach() {
		return to;
	}

	/**
	 * @param nach
	 *            the nach to set
	 */
	public void setNach(Point nach) {
		this.to = nach;
	}

	/**
	 * @return the pfeil
	 */
	public Polygon getPfeil() {
		return arrow;
	}

	/**
	 * @param pfeil
	 *            the pfeil to set
	 */
	public void setPfeil(Polygon pfeil) {
		this.arrow = pfeil;
	}

	public void modifyArc(Point k1, Point k2, double length) {

		this.from = k1;
		this.to = k2;
		this.s = length;
		phi = m.atan(((double) to.y - (double) from.y) / ((double) to.x - (double) from.x));
		if (to.x < from.x)
			phi = phi + m.PI;
		// den Pfeil zeichnen
		// pfeil = new Polygon();
		arrow.reset();
		arrow.addPoint(to.x, to.y); // Pfeilspitze
		// Die beiden anderen Punkte des Pfeils muessen um den Winkel phi relative zur
		// Pfeilspitze rotiert werden.
		// x_rot = x*cos(phi) - y*sin(phi)
		// y_rot = x*sin(phi) + y*cos(phi)
		xHead = (double) to.x;
		yHead = (double) to.y;
		// erster Punkt
		xOld = (double) to.x - s - xHead;
		yOld = (double) to.y + s / 2.5 - yHead;
		xNew = xOld * m.cos(phi) - yOld * m.sin(phi) + xHead;
		yNew = xOld * m.sin(phi) + yOld * m.cos(phi) + yHead;
		arrow.addPoint((int) xNew, (int) yNew);
		// zweiter Punkt
		yOld = (double) to.y - s / 2.5 - yHead;
		xNew = xOld * m.cos(phi) - yOld * m.sin(phi) + xHead;
		yNew = xOld * m.sin(phi) + yOld * m.cos(phi) + yHead;
		arrow.addPoint((int) xNew, (int) yNew);

	}

	// pX, pY mouse click coordinates
	public boolean containsPoint(int pX, int pY) {
		return Math.abs(this.to.x - pX) <= 30 && Math.abs(this.to.y - pY) <= 30;
	}

}