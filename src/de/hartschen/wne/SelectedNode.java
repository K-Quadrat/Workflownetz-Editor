package de.hartschen.wne;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * Klasse um einzelne nodes auszuwählen.
 * 
 * @author Jens Hartschen
 *
 */
public class SelectedNode {

	private Node selectedNode = null;
	private Node selectedNodeRightClick = null;
	private IModel model;
	private GlobalSizeModel globalSizeModel;

	/**
	 * Konstuktor der Klasse SelectedNode.
	 * 
	 * @param model
	 * @param globalSizeModel
	 */
	public SelectedNode(IModel model, GlobalSizeModel globalSizeModel) {
		super();
		this.model = model;
		this.globalSizeModel = globalSizeModel;
	}

	/**
	 * Diese Methode liefert beim Rechtsklick eine ausgewählte node zurück.
	 * 
	 * @return the selectedNodeRightClick
	 */
	public Node getSelectedNodeRightClick() {
		return selectedNodeRightClick;
	}

	/**
	 * Diese Methode setzt beim Rechtsklick eine node als ausgewählt.
	 * 
	 * @param selectedNodeRightClick
	 */
	public void setSelectedNodeRightClick(Node selectedNodeRightClick) {
		this.selectedNodeRightClick = selectedNodeRightClick;
	}

	/**
	 * Diese Methode liefert eine ausgewählte node zurück.
	 * 
	 * @return the selectedNode
	 */
	public Node getSelectedNode() {
		return selectedNode;
	}

	/**
	 * Diese Methode setzt eine node als ausgewählte.
	 * 
	 * @param selectedNode
	 */
	public void setSelectedNode(Node selectedNode) {
		this.selectedNode = selectedNode;
	}

	/**
	 * Die Methode setzt beim verschiebden der node, die neuen Koordinaten in der
	 * Datenhaltung.
	 * 
	 * @param coordinates
	 */
	public void setNewCoordinatesForSelectedNode(Point coordinates) {

		if (model.getSmallestPoint().x <= globalSizeModel.getNodesSize() / 2) {
			if (getSelectedNode().getX() < coordinates.x) {
				getSelectedNode().setX(coordinates.x);
			}

		} else {
			getSelectedNode().setX(coordinates.x);
		}

		if (model.getSmallestPoint().y <= globalSizeModel.getNodesSize() / 2) {
			if (getSelectedNode().getY() < coordinates.y) {
				getSelectedNode().setY(coordinates.y);
			}

		} else {
			getSelectedNode().setY(coordinates.y);
		}

	}

}
