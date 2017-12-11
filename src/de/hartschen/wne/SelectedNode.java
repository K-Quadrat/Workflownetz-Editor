package de.hartschen.wne;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class SelectedNode {

	private Node selectedNode = null;
	private Node selectedNodeRightClick = null;
	private IModel model;
	private GlobalSizeModel globalSizeModel;

	public SelectedNode(IModel model, GlobalSizeModel globalSizeModel) {
		super();
		this.model = model;
		this.globalSizeModel = globalSizeModel;
	}

	public SelectedNode(IModel model) {
		super();
		this.model = model;
	}

	/**
	 * @return the selectedNodeRightClick
	 */
	public Node getSelectedNodeRightClick() {
		return selectedNodeRightClick;
	}

	/**
	 * @param selectedNodeRightClick
	 *            the selectedNodeRightClick to set
	 */
	public void setSelectedNodeRightClick(Node selectedNodeRightClick) {
		this.selectedNodeRightClick = selectedNodeRightClick;
	}

	/**
	 * @return the selectedNode
	 */
	public Node getSelectedNode() {
		return selectedNode;
	}

	/**
	 * @param selectedNode
	 *            the selectedNode to set
	 */
	public void setSelectedNode(Node selectedNode) {
		this.selectedNode = selectedNode;
	}

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
