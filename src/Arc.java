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

	public Arc(String id, String source, String target) {
		this.id = id;
		this.source = source;
		this.target = target;
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

	

}