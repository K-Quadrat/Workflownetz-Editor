import java.awt.*;
import javax.swing.*;

public class Arc {
	private String id;
	private String source;
	private String target;
	private Math m;
	private Point von, nach; // die zwei Punkte, die die Pfeil-Linie darstellen
	private double phi; // Rotationswinkel
	private Polygon pfeil;
	private double s; // Seitenlaenge des Pfeils
	private double xAlt, yAlt; // x-,y-Werte vor der Rotation,relativ zur Pfeilspitze
	private double xNeu, yNeu; // x-,y-Werte nach der Rotation,absolut
	private double xSpitze, ySpitze; // x-,y-Werte der Pfeilspitze
	private int radius;


	public Arc(String id, String source, String target, Point k1, Point k2, double length) {

		this.id = id;
		this.source = source;
		this.target = target;
		
		this.von = k1;
		this.nach = k2;
		this.s = length;
		phi = m.atan(((double) nach.y - (double) von.y) / ((double) nach.x - (double) von.x));
		if (nach.x < von.x)
			phi = phi + m.PI;
		// den Pfeil zeichnen
		pfeil = new Polygon();
		pfeil.addPoint(nach.x, nach.y); // Pfeilspitze
		// Die beiden anderen Punkte des Pfeils muessen um den Winkel phi relative zur
		// Pfeilspitze rotiert werden.
		// x_rot = x*cos(phi) - y*sin(phi)
		// y_rot = x*sin(phi) + y*cos(phi)
		xSpitze = (double) nach.x;
		ySpitze = (double) nach.y;
		// erster Punkt
		xAlt = (double) nach.x - s - xSpitze;
		yAlt = (double) nach.y + s / 2.5 - ySpitze;
		xNeu = xAlt * m.cos(phi) - yAlt * m.sin(phi) + xSpitze;
		yNeu = xAlt * m.sin(phi) + yAlt * m.cos(phi) + ySpitze;
		pfeil.addPoint((int) xNeu, (int) yNeu);
		// zweiter Punkt
		yAlt = (double) nach.y - s / 2.5 - ySpitze;
		xNeu = xAlt * m.cos(phi) - yAlt * m.sin(phi) + xSpitze;
		yNeu = xAlt * m.sin(phi) + yAlt * m.cos(phi) + ySpitze;
		pfeil.addPoint((int) xNeu, (int) yNeu);

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
		return von;
	}

	/**
	 * @param von
	 *            the von to set
	 */
	public void setVon(Point von) {
		this.von = von;
	}

	/**
	 * @return the nach
	 */
	public Point getNach() {
		return nach;
	}

	/**
	 * @param nach
	 *            the nach to set
	 */
	public void setNach(Point nach) {
		this.nach = nach;
	}

	/**
	 * @return the pfeil
	 */
	public Polygon getPfeil() {
		return pfeil;
	}

	/**
	 * @param pfeil
	 *            the pfeil to set
	 */
	public void setPfeil(Polygon pfeil) {
		this.pfeil = pfeil;
	}
	
	public void modifyArc(Point k1, Point k2, double length) {
		
		this.von = k1;
		this.nach = k2;
		this.s = length;
		phi = m.atan(((double) nach.y - (double) von.y) / ((double) nach.x - (double) von.x));
		if (nach.x < von.x)
			phi = phi + m.PI;
		// den Pfeil zeichnen
//		pfeil = new Polygon();
		pfeil.reset();
		pfeil.addPoint(nach.x, nach.y); // Pfeilspitze
		// Die beiden anderen Punkte des Pfeils muessen um den Winkel phi relative zur
		// Pfeilspitze rotiert werden.
		// x_rot = x*cos(phi) - y*sin(phi)
		// y_rot = x*sin(phi) + y*cos(phi)
		xSpitze = (double) nach.x;
		ySpitze = (double) nach.y;
		// erster Punkt
		xAlt = (double) nach.x - s - xSpitze;
		yAlt = (double) nach.y + s / 2.5 - ySpitze;
		xNeu = xAlt * m.cos(phi) - yAlt * m.sin(phi) + xSpitze;
		yNeu = xAlt * m.sin(phi) + yAlt * m.cos(phi) + ySpitze;
		pfeil.addPoint((int) xNeu, (int) yNeu);
		// zweiter Punkt
		yAlt = (double) nach.y - s / 2.5 - ySpitze;
		xNeu = xAlt * m.cos(phi) - yAlt * m.sin(phi) + xSpitze;
		yNeu = xAlt * m.sin(phi) + yAlt * m.cos(phi) + ySpitze;
		pfeil.addPoint((int) xNeu, (int) yNeu);
		
	}
	
	// pX, pY mouse click coordinates
	public boolean containsPoint(int pX, int pY) {
//		System.out.println(nach.x);
		return Math.abs(this.nach.x - pX) <= radius && Math.abs(this.nach.y - pY) <= radius;
	}

}