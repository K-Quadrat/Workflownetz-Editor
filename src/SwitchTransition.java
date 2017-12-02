import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class SwitchTransition {
	private IModel model;
	private ArcsModel arcsModel;
	private String source;
	private String target;
	private String startNodeClass;
	private String endNodeClass;
	private boolean contact;
	private boolean transitionActive;

	public SwitchTransition(IModel model, ArcsModel arcsModel) {
		super();
		this.model = model;
		this.arcsModel = arcsModel;
	}

	/**
	 * @return the transitionActive
	 */
	public boolean isTransitionActive() {
		return transitionActive;
	}

	/**
	 * @param transitionActive
	 *            the transitionActive to set
	 */
	public void setTransitionActive(boolean transitionActive) {
		this.transitionActive = transitionActive;
	}

	/**
	 * @return the contact
	 */
	public boolean isContact() {
		return contact;
	}

	/**
	 * @param contact
	 *            the contact to set
	 */
	public void setContact(boolean contact) {
		this.contact = contact;
	}

	/**
	 * @return the startNodeClass
	 */
	public String getStartNodeClass() {
		return startNodeClass;
	}

	/**
	 * @param startNodeClass
	 *            the startNodeClass to set
	 */
	public void setStartNodeClass(String startNodeClass) {
		this.startNodeClass = startNodeClass;
	}

	/**
	 * @return the endNodeClass
	 */
	public String getEndNodeClass() {
		return endNodeClass;
	}

	/**
	 * @param endNodeClass
	 *            the endNodeClass to set
	 */
	public void setEndNodeClass(String endNodeClass) {
		this.endNodeClass = endNodeClass;
	}

	public boolean transitionActive(String id) {
		List<Boolean> marking = new ArrayList<Boolean>();

		for (Arc a : arcsModel.getArcs()) {
			if (a.getTarget().equals(id)) {

				for (Node n : model.getAllPlaces()) {
					if (n.getId().equals(a.getSource())) {
						marking.add(n.getMarking());

					}

				}
			}
		}
		// System.out.println(marking);
		// if contains false return true
		if (marking.contains(false) || marking.isEmpty()) {
			setTransitionActive(false);
			return false;

		} else {
			setTransitionActive(true);
			return true;
		}

	}

	public boolean contact(String id) {

		List<Boolean> marking = new ArrayList<Boolean>();

		for (Arc a : arcsModel.getArcs()) {
			if (a.getSource().equals(id)) {

				for (Node n : model.getAllPlaces()) {
					if (n.getId().equals(a.getTarget())) {
						marking.add(n.getMarking());

					}

				}
			}
		}
		if (marking.contains(false) || marking.isEmpty()) {
			return false;
		} else {
			setContact(true);
			return true;
		}

	}

	public boolean deadlock() {
		if (!reachedTheEndMarking() && !transitionActive) {
			return true;
		}

		return false;
	}

	public boolean reachedTheEndMarking() {
		for (Node n : model.getAllPlaces()) {
			if (n.getId().equals(endNodeClass)) {
				if (n.getMarking()) {
					return true;
				}
			}

		}

		return false;

	}

	public void switchTransition(Node transition) {
		// System.out.println("Method switchTransition");
		// System.out.println(transition.getId());
		// System.out.println(transitionActive(transition.getId()));
		if (transitionActive(transition.getId()) && !contact(transition.getId())) {

			for (Arc a : arcsModel.getArcs()) {
				if (a.getTarget().equals(transition.getId())) {

					for (Node n : model.getAllNodes()) {
						if (n.getId().equals(a.getSource())) {
							n.setMarking(false);
							// System.out.println("n.setMarking(false): " + n.getId());

						}
					}
				}

			}

			for (Arc a : arcsModel.getArcs()) {
				if (a.getSource().equals(transition.getId())) {

					for (Node n : model.getAllNodes()) {
						if (n.getId().equals(a.getTarget())) {
							n.setMarking(true);
							// System.out.println("n.setMarking(true): " + n.getId());

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

		// hasStartingEndingPlaces
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
			setStartNodeClass(null);
			setEndNodeClass(null);
			return "Here we go...";
		}

		else if (nodesEnd.size() >= 2 && nodesStart.size() >= 2) {
			// zu viele anfangsstellen und endsstelle
			setStartNodeClass(null);
			setEndNodeClass(null);
			return "Too many starting places and too many ending place";
		}

		else if (nodesStart.size() >= 2 && nodesEnd.isEmpty()) {
			// zu viele anfangsstellen und keine endstelle
			setStartNodeClass(null);
			setEndNodeClass(null);
			return "Too many starting places and no ending place";
		}

		else if (nodesEnd.size() >= 2 && nodesStart.isEmpty()) {
			// keine anfangsstelle und zu viele endstellen
			setStartNodeClass(null);
			setEndNodeClass(null);
			return "No starting place and too many ending places";
		}

		else if (nodesStart.isEmpty()) {
			// keine anfangsstelle
			setStartNodeClass(null);
			setEndNodeClass(null);
			return "No starting place";
		}

		else if (nodesEnd.isEmpty()) {
			// keine endstelle
			setStartNodeClass(null);
			setEndNodeClass(null);
			return "No ending place";
		}

		else if (nodesStart.size() >= 2) {
			// zu viele anfangsstellen
			setStartNodeClass(null);
			setEndNodeClass(null);
			return "Too many starting places";
		}

		else if (nodesEnd.size() >= 2) {
			// zu viele anfangsstellen
			setStartNodeClass(null);
			setEndNodeClass(null);
			return "Too many ending places";
		}

		else if (nodesStart.size() == 1 && nodesEnd.size() == 1) {
			// genau eine anfangsstelle und genau eine endstelle
			setStartNodeClass(nodesStart.get(0));
			setEndNodeClass(nodesEnd.get(0));
			return "It's a Workflow Net!";

		}
		setStartNodeClass(null);
		setEndNodeClass(null);
		return "Not all network elements on a path from start place to end place";
	}

	public String isWorkflowNet() {
		if (areAllNetworkElementsOnThePath()) {

			return hasStartingEndingPlaces();

		}
		return "Not all network elements on a path from start place to end place";
	}

}
