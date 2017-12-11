import java.util.ArrayList;
import java.util.List;

import de.hartschen.wne.ENode;
import de.hartschen.wne.Node;

public class testArrayList {

	public static void main(String[] args) {
		List<Node> nodes = new ArrayList<Node>();
		int index = 0;

		// Generate few places
		nodes.add(new Node("S1", 200, 300, 50, ENode.PLACE, "Place number 1", false));
		nodes.add(new Node("S2", 300, 300, 50, ENode.PLACE, "Place number 1", false));
		nodes.add(new Node("S3", 400, 300, 50, ENode.PLACE, "Place number 1", false));
		nodes.add(new Node("S4", 500, 300, 50, ENode.PLACE, "Place number 1", false));

		// Generate few transitions
		nodes.add(new Node("T1", 200, 400, 50, ENode.TRANSITION, "Transition number 2", false));
		nodes.add(new Node("T2", 300, 400, 50, ENode.TRANSITION, "Transition number 2", false));
		nodes.add(new Node("T3", 400, 400, 50, ENode.TRANSITION, "Transition number 2", false));
		nodes.add(new Node("T4", 500, 400, 50, ENode.TRANSITION, "Transition number 2", false));

		for (Node n : nodes) {
			boolean bool = true;
			if (bool) {
				// String id = n.getId();
				// System.out.println(id);

				index = nodes.indexOf(n);
				System.out.println(index);
			}

		}
		System.out.println(index);

		// Here
		nodes.remove(index);

	}

}

// nodes.remove(0);

// index = nodes.indexOf(n);
// System.out.println(index);
// nodes.removeIf(p -> p.getId().equals("112"));