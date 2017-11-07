import java.util.ArrayList;
import java.util.List;

public class Model implements IModel {

	private List<Node> nodes = new ArrayList<Node>();

	@Override
	public void setNode(int x, int y, int radius, ENode nodeType, String name, boolean markedOrActivated) {
		nodes.add(new Node(x, y, radius, nodeType, name, markedOrActivated));

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

}
