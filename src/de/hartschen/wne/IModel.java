package de.hartschen.wne;

import java.awt.Point;
import java.util.List;

/**
 * Interface Klasse der Model Klasse.
 * 
 * @author Jens Hartschen
 *
 */
public interface IModel {

	/**
	 * Die Methode erstellt ein neues place Objekt in der Datenhaltung.
	 * 
	 * @param id
	 * @param x
	 * @param y
	 * @param radius
	 * @param nodeType
	 * @param name
	 * @param marking
	 */
	void setNode(String id, int x, int y, int radius, ENode nodeType, String name, boolean marking);

	/**
	 * Die Methode erstellt ein neues transition Objekt in der Datenhaltung.
	 * 
	 * @param id
	 * @param x
	 * @param y
	 * @param radius
	 * @param nodeType
	 * @param name
	 */
	void setNode(String id, int x, int y, int radius, ENode nodeType, String name);

	/**
	 * Die Methode löscht ein node Objekt aus der Datenhaltung.
	 * 
	 * @param x
	 *            Koordinate
	 * @param y
	 *            Koordinate
	 */
	void deleteNode(int x, int y);

	/**
	 * Die Methode löscht ein node Objekt aus der Datenhaltung.
	 * 
	 * @param id
	 */
	void deleteNodeById(String id);

	/**
	 * Die Methode liefert ein node Objekt aus der Datenhaltung zurück.
	 * 
	 * @param x
	 *            Koordinate
	 * @param y
	 *            Koordinate
	 * @return node
	 */
	Node getNode(int x, int y);

	/**
	 * Die Methode liefert eine Liste mit allen node Objekten aus der Datenhaltung
	 * zurück.
	 * 
	 * @return Liste mit nodes
	 */
	List<Node> getAllNodes();

	/**
	 * Die Methode liefert ein place Objekt aus der Datenhaltung zurück.
	 * 
	 * @param x
	 *            Koordinate
	 * @param y
	 *            Koordinate
	 * @return place Objekt
	 */
	Node getPlace(int x, int y);

	/**
	 * Die Methode liefert eine Liste mit allen place Objekten aus der Datenhaltung
	 * zurück.
	 * 
	 * @return Liste mit places
	 */
	List<Node> getAllPlaces();

	/**
	 * Die Methode liefert ein transition Objekt aus der Datenhaltung zurück.
	 * 
	 * @param x
	 *            Koordinate
	 * @param y
	 *            Koordinate
	 * @return transition Objekt
	 */
	Node getTransition(int x, int y);

	/**
	 * Die Methode liefert eine Liste mit allen transition Objekten aus der
	 * Datenhaltung.
	 * 
	 * @return Liste mit transitions
	 */
	List<Node> getAllTransitions();

	/**
	 * Die Methode setzt die Datenhaltung für transitions und places zurück.
	 */
	void clearNodesList();

	/**
	 * Die Methode liefert den größten x und y Koordinatenwert zurück.
	 * 
	 * @return Point mit Koordinaten
	 */
	Point getLargestPoint();

	/**
	 * Die Methode liefert den kleinsten x und y Koordinatenwert zurück.
	 * 
	 * @return Point mit Koordinaten
	 */
	Point getSmallestPoint();

}
