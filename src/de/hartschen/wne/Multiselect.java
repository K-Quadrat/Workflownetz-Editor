package de.hartschen.wne;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * Klasse zur Verwaltung der Multiselect Funktionen.
 * 
 * @author Jens Hartschen
 *
 */
public class Multiselect {
	private List<String> multiselectedNodesIds = new ArrayList<String>();
	private Point multiselectFrom = new Point(0, 0);
	private Point multiselectTo = new Point(0, 0);
	private IModel model;
	private Point coordinatesFrom;
	private Point coordinatesTo;
	private GlobalSizeModel globalSizeModel;

	/**
	 * konstruktor der Multiselect Klasse.
	 * 
	 * @param model
	 * @param globalSizeModel
	 */
	public Multiselect(IModel model, GlobalSizeModel globalSizeModel) {
		super();
		this.model = model;
		this.globalSizeModel = globalSizeModel;
	}

	/**
	 * Die Methode fügt die Ids, der ausgewählten nodes, in die Liste
	 * multiselectedNodesIds hinzu.
	 */
	public void addNodeMultiselectedNodesIds() {

		for (Node n : model.getAllNodes()) {
			if (multiselectFrom.x <= n.getX() && n.getX() <= multiselectTo.getX() && multiselectFrom.getY() <= n.getY()
					&& n.getY() <= multiselectTo.getY()) {
				multiselectedNodesIds.add(n.getId());
			}

		}
		System.out.println(multiselectedNodesIds);
	}

	/**
	 * Die Methode lösche alle ausgewählten nodes aus der Datenhaltung.
	 */
	public void deleteMultiselectedNodes() {
		for (int i = 0; i < getMultiselectedNodesIds().size(); i++) {
			for (int j = 0; j < model.getAllNodes().size(); j++) {
				Node n = model.getAllNodes().get(j);

				if (n.getId().equals(getMultiselectedNodesIds().get(i))) {
					model.deleteNodeById(n.getId());
					j--;
				}
			}
		}
	}

	/**
	 * Die Methode setzt die Liste multiselectedNodesIds zurück.
	 */
	public void clearMultiselect() {
		multiselectedNodesIds.clear();
	}

	/**
	 * Die Methode liefert eine Liste mit allen ausgewählten nodes zurück.
	 * 
	 * @return Liste multiselectedNodesIds
	 */
	public List<String> getMultiselectedNodesIds() {
		return multiselectedNodesIds;

	}

	/**
	 * Die Methode liefert die Koordinaten für multiselectFrom zurück.
	 * 
	 * @return Point multiselectFrom
	 */
	public Point getc() {
		return multiselectFrom;
	}

	/**
	 * Die Methode setzt die Koordinaten für multiselectFrom.
	 * 
	 * @param Point
	 *            multiselectFrom
	 */
	public void setMultiselectFrom(Point multiselectFrom) {
		this.multiselectFrom = multiselectFrom;
	}

	/**
	 * Die Methode liefert die Koordinaten für multiselectTo zurück.
	 * 
	 * @return Point multiselectTo
	 */
	public Point getMultiselectTo() {
		return multiselectTo;
	}

	/**
	 * Die Methode liefert die Koordinaten für multiselectTo zurück.
	 * 
	 * @param Point
	 *            multiselectTo
	 */
	public void setMultiselectTo(Point multiselectTo) {
		this.multiselectTo = multiselectTo;
	}

	/**
	 * Die Methode liefert die Koordinaten für coordinatesFrom zurück.
	 * 
	 * @return Point coordinatesFrom
	 */
	public Point getCoordinatesFrom() {
		return coordinatesFrom;
	}

	/**
	 * Die Methode setzt die Koordinaten für coordinatesFrom.
	 * 
	 * @param Point
	 *            coordinatesFrom
	 */
	public void setCoordinatesFrom(Point coordinatesFrom) {
		this.coordinatesFrom = coordinatesFrom;
	}

	/**
	 * Die Methode liefert die Koordinaten für getCoordinatesTo zurück.
	 * 
	 * @return Point coordinatesTo
	 */
	public Point getCoordinatesTo() {
		return coordinatesTo;
	}

	/**
	 * Die Methode setzt die Koordinaten für getCoordinatesTo.
	 * 
	 * @param Point
	 *            coordinatesTo
	 */
	public void setCoordinatesTo(Point coordinatesTo) {
		this.coordinatesTo = coordinatesTo;
	}

	/**
	 * Die Methode setzt beim verschiebden der nodes, die neuen Koordinaten in der
	 * Datenhaltung.
	 */
	public void setNewCoordinates() {
		int distanceX = coordinatesTo.x - coordinatesFrom.x;
		int distanceY = coordinatesTo.y - coordinatesFrom.y;

		for (Node n : model.getAllNodes()) {
			for (int i = 0; i < getMultiselectedNodesIds().size(); i++) {
				if (n.getId().equals(getMultiselectedNodesIds().get(i))) {
					if (model.getSmallestPoint().x <= globalSizeModel.getNodesSize() / 2) {
						if (n.getX() < (n.getX() + distanceX / 20)) {
							n.setX(n.getX() + distanceX / 20);
						}

					} else {
						n.setX(n.getX() + distanceX / 20);
					}

					if (model.getSmallestPoint().y <= globalSizeModel.getNodesSize() / 2) {
						if (n.getY() < (n.getY() + distanceY / 20)) {
							n.setY(n.getY() + distanceY / 20);
						}

					} else {
						n.setY(n.getY() + distanceY / 20);
					}

				}
			}
		}
	}

}
