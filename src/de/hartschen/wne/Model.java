package de.hartschen.wne;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Model implements IModel {

	private List<Node> nodes = new ArrayList<Node>();
	private int index;
	private boolean containsPoint = false;

	@Override
	public void setNode(String id, int x, int y, int radius, ENode nodeType, String name, boolean marking) {
		nodes.add(new Node(id, x, y, radius, nodeType, name, marking));
	}

	@Override
	public void setNode(String id, int x, int y, int radius, ENode nodeType, String name) {
		nodes.add(new Node(id, x, y, radius, nodeType, name));
	}

	@Override
	public Node getNode(int x, int y) {
		for (Node n : nodes) {
			if (n.containsPoint(x, y)) {
				return n;
			}
		}
		return null;
	}

	@Override
	public List<Node> getAllNodes() {
		return nodes;
	}

	@Override
	public Node getPlace(int x, int y) {
		for (Node n : nodes) {
			if (n.containsPoint(x, y) && n.getNodeType() == ENode.PLACE) {
				return n;
			}
		}
		return null;
	}

	@Override
	public List<Node> getAllPlaces() {
		List<Node> allPlacesList = new ArrayList<Node>();

		for (Node n : nodes) {
			if (n.getNodeType() == ENode.PLACE) {
				allPlacesList.add(n);
			}
		}
		return allPlacesList;
	}

	@Override
	public Node getTransition(int x, int y) {
		for (Node n : nodes) {
			if (n.containsPoint(x, y) && n.getNodeType() == ENode.TRANSITION) {
				return n;
			}
		}
		return null;
	}

	@Override
	public List<Node> getAllTransitions() {
		List<Node> allTransitionsList = new ArrayList<Node>();
		for (Node n : nodes) {
			if (n.getNodeType() == ENode.TRANSITION) {
				allTransitionsList.add(n);
			}
		}
		return allTransitionsList;
	}

	@Override
	public void deleteNode(int x, int y) {
		for (Node n : nodes) {
			if (n.containsPoint(x, y)) {
				index = nodes.indexOf(n);
				containsPoint = true;
			}

		}

		if (containsPoint) {
			nodes.remove(index);
			containsPoint = false;
		}

	}

	@Override
	public void clearNodesList() {
		nodes.clear();
	}

	@Override
	public Point getLargestPoint() {

		if (!getAllNodes().isEmpty()) {
			List<Integer> xCoordinates = new ArrayList<Integer>();
			List<Integer> yCoordinates = new ArrayList<Integer>();
			for (Node n : getAllNodes()) {
				xCoordinates.add(n.getX());
				yCoordinates.add(n.getY());
			}
			if (Collections.max(xCoordinates) < 1024 || Collections.max(yCoordinates) < 768) {
				return new Point(1024, 768);
			} else {
				return new Point(Collections.max(xCoordinates), Collections.max(yCoordinates));
			}

		} else {
			return new Point(1024, 768);
		}

	}

	@Override
	public Point getSmallestPoint() {

		if (!getAllNodes().isEmpty()) {
			List<Integer> xCoordinates = new ArrayList<Integer>();
			List<Integer> yCoordinates = new ArrayList<Integer>();
			for (Node n : getAllNodes()) {
				xCoordinates.add(n.getX());
				yCoordinates.add(n.getY());
			}
			return new Point(Collections.min(xCoordinates), Collections.min(yCoordinates));

		} else {
			return null;
		}
	}

	@Override
	public void deleteNodeById(String id) {
		boolean hit = false;
		for (Node n : getAllNodes()) {
			if (n.getId().equals(id)) {
				index = nodes.indexOf(n);
				hit = true;
			}
		}
		if (hit) {
			nodes.remove(index);	
		}
		
	}
	
	

}
