import java.util.ArrayList;
import java.util.List;

public class Model implements IModel {

	private List<Node> nodes = new ArrayList<Node>();
	private int radius;
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


}
