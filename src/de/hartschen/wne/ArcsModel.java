package de.hartschen.wne;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * Datenhaltungsklasse für die Pfeile
 * 
 * @author Jens Hartschen
 *
 */
public class ArcsModel {
	private boolean containsPoint = false;

	private List<Arc> arcs = new ArrayList<Arc>();
	private int index;

	/**
	 * Konstruktor der Datenhaltungsklasse für die Pfeile
	 * 
	 * @param id
	 * @param source
	 * @param target
	 * @param k1
	 * @param k2
	 * @param length
	 */
	public void setArc(String id, String source, String target, Point k1, Point k2, double length) {
		arcs.add(new Arc(id, source, target, k1, k2, length));
	}

	/**
	 * Die Methode liefert eine Liste mit allen Pfeilen zurück.
	 * 
	 * @return list arcs
	 */
	public List<Arc> getArcs() {
		return arcs;

	}

	/**
	 * Die Methode löscht einen Pfeil anhand der id.
	 * 
	 * @param id
	 */
	public void deleteArcById(String id) {
		for (Arc a : getArcs()) {
			if (a.getId().equals(id)) {
				index = arcs.indexOf(a);
			}
		}
		arcs.remove(index);
	}

	/**
	 * Die Methode löscht einen Pfeil anhand der Koordinaten.
	 * 
	 * @param x
	 * @param y
	 */
	public void deleteArc(int x, int y) {
		for (Arc a : arcs) {
			if (a.containsPoint(x, y)) {
				index = arcs.indexOf(a);
				containsPoint = true;
			}

		}

		if (containsPoint) {
			arcs.remove(index);
			containsPoint = false;
		}

	}

	/**
	 * Die Methode setzt die Datenhaltung zurück.
	 */
	public void clearArcsList() {
		arcs.clear();
	}

}
