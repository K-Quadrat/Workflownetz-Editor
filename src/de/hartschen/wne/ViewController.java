package de.hartschen.wne;

import java.awt.Point;

/**
 * Steuerklasse für WNEPanel
 * 
 * @author Jens Hartschen
 *
 */
public class ViewController extends WNEPanel {

	private IModel model;
	private ID id;
	private GlobalSizeModel globalSizeModel;
	private ArcsModel arcsModel;

	public ViewController(IModel model, ID id, GlobalSizeModel globalSizeModel, ArcsModel arcsModel) {
		super();
		this.model = model;
		this.id = id;
		this.globalSizeModel = globalSizeModel;
		this.arcsModel = arcsModel;
	}

	/**
	 * Die Methode bekommt Koordinaten auf dem WNEPanel übergeben und erstellt ein
	 * place in der Datenhaltung.
	 * 
	 * @param x
	 *            Koordinaten
	 * @param y
	 *            Koordinaten
	 */
	public void addPlace(int x, int y) {
		model.setNode(id.getNextPlaceIdString(), x, y, globalSizeModel.getNodesSize(), ENode.PLACE,
				id.getPlaceIdString(), false);
		refresh();

	}

	/**
	 * Die Methode bekommt Koordinaten auf dem WNEPanel übergeben und erstellt eine
	 * transition in der Datenhaltung.
	 * 
	 * @param x
	 *            Koordinaten
	 * @param y
	 *            Koordinaten
	 */
	public void addTransition(int x, int y) {
		model.setNode(id.getNextTransitionIdString(), x, y, globalSizeModel.getNodesSize(), ENode.TRANSITION,
				id.getTransitionIdString(), false);
		refresh();
	}

	/**
	 * Die Methode bekommt zwei node ids übergeben und erstellt ein Pfeil zwischen
	 * den beiden nodes.
	 * 
	 * @param firstClickNodeId
	 * @param secondClickNodeId
	 */
	public void addArc(String firstClickNodeId, String secondClickNodeId) {
		arcsModel.setArc(id.getNextArcIdString(), firstClickNodeId, secondClickNodeId, new Point(0, 0), new Point(0, 0),
				globalSizeModel.getArcsSize());
		refresh();
	}

}
