import java.util.ArrayList;
import java.util.List;

public class SwitchTransition {
	private IModel model;
	private ArcsModel arcsModel;
	private String source;
	private String target;

	public SwitchTransition(IModel model, ArcsModel arcsModel) {
		super();
		this.model = model;
		this.arcsModel = arcsModel;
	}

	public boolean transitionActive(String id) {
		List<Boolean> marking = new ArrayList<Boolean>();

		for (Arc a : arcsModel.getArcs()) {
			if (a.getTarget().equals(id)) {

				for (Node n : model.getAllPlaces()) {
					if (n.getId().equals(a.getSource())) {
						marking.add(n.getMarkOrActive());

					}

				}
			}
		}
		// System.out.println(marking);
		// if contains false return true
		if (marking.contains(false) || marking.isEmpty()) {
			return false;
		} else {
			return true;
		}

	}

	public void switchTransition(Node transition) {
		System.out.println("Method switchTransition");
		System.out.println(transition.getId());
		System.out.println(transitionActive(transition.getId()));
		if (transitionActive(transition.getId())) {
			System.out.println("I'm here");

			for (Arc a : arcsModel.getArcs()) {
				if (a.getTarget().equals(transition.getId())) {

					for (Node n : model.getAllNodes()) {
						if (n.getId().equals(a.getSource())) {
							n.setMarkOrActive(false);
							System.out.println("n.setMarkOrActive(false): " + n.getId());

						}
					}
				}

			}

			for (Arc a : arcsModel.getArcs()) {
				if (a.getSource().equals(transition.getId())) {

					for (Node n : model.getAllNodes()) {
						if (n.getId().equals(a.getTarget())) {
							n.setMarkOrActive(true);
							System.out.println("n.setMarkOrActive(true): " + n.getId());

						}
					}
				}

			}

		}
	}

	public boolean areAllNetworkElementsOnThePath() {
		List<String> arcsSource = new ArrayList<String>();
		List<String> arcsTarget = new ArrayList<String>();
		List<String> nodes = new ArrayList<String>();

		// if (!model.getAllNodes().isEmpty() && arcsModel.getArcs().isEmpty()) {
		// System.out.println("Nodes aber keine arcs / Nicht alle netzelemente auf dem
		// Pfad");
		// }

		for (Arc a : arcsModel.getArcs()) {
			arcsSource.add(a.getSource());
			arcsTarget.add(a.getTarget());
		}
		for (Node n : model.getAllNodes()) {
			nodes.add(n.getId());
		}

		for (int i = 0; i < nodes.size(); i++) {

			if (!arcsSource.contains(nodes.get(i))) {

				if (!arcsTarget.contains(nodes.get(i))) {
					return false;

				}
			}

		}

		return true;
	}

	public String hasStartingEndingPlaces() {
		if (areAllNetworkElementsOnThePath()) {

			List<String> arcsSource = new ArrayList<String>();
			List<String> arcsTarget = new ArrayList<String>();
			List<String> nodes = new ArrayList<String>();
			List<String> nodesStart = new ArrayList<String>();
			List<String> nodesEnd = new ArrayList<String>();

			for (Arc a : arcsModel.getArcs()) {
				arcsSource.add(a.getSource());
				arcsTarget.add(a.getTarget());
			}
			for (Node n : model.getAllPlaces()) {
				nodes.add(n.getId());
			}

			for (int i = 0; i < nodes.size(); i++) {

				if (arcsSource.contains(nodes.get(i))) {

					if (!arcsTarget.contains(nodes.get(i))) {
						nodesStart.add(nodes.get(i));
						// System.out.println("Start nodes: " + nodes.get(i));

					}
				}

			}

			for (int i = 0; i < nodes.size(); i++) {

				if (arcsTarget.contains(nodes.get(i))) {

					if (!arcsSource.contains(nodes.get(i))) {
						nodesEnd.add(nodes.get(i));
						// System.out.println("End nodes: " + nodes.get(i));

					}
				}

			}

			if (nodesStart.isEmpty() && nodesEnd.isEmpty()) {
				// keine anfangsstelle und keine endstelle
				return "No starting and no ending places";
			}
			
			else if (nodesEnd.size() >= 2 && nodesStart.size() >= 2) {
				// zu viele anfangsstellen und endsstelle
				return "Too many starting places and too many ending place";
			}

			else if (nodesStart.size() >= 2 && nodesEnd.isEmpty()) {
				// zu viele anfangsstellen und keine endstelle
				return "Too many starting places and no ending place";
			}

			else if (nodesEnd.size() >= 2 && nodesStart.isEmpty()) {
				// keine anfangsstelle und zu viele endstellen 
				return "No starting place and too many ending places";
			}
			
			else if (nodesStart.isEmpty()) {
				// keine anfangsstelle
				return "No starting place";
			}

			else if (nodesEnd.isEmpty()) {
				// keine endstelle
				return "No ending place";
			}

			else if (nodesStart.size() >= 2) {
				// zu viele anfangsstellen
				return "Too many starting places";
			}

			

			else if (nodesEnd.size() >= 2) {
				// zu viele anfangsstellen
				return "Too many ending places";
			}

			else if (nodesStart.size() == 1 && nodesEnd.size() == 1) {
				// genau eine anfangsstelle und genau eine endstelle
				return "It's a Workflow Net!";

			}

			nodesStart.clear();
			nodesEnd.clear();

		}
		return null;
	}
}
