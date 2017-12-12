package de.hartschen.wne;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Die Klasse beinhaltet alle Methoden, die nötig sind um die transitions zu
 * schalten.
 * 
 * @author Jens Hartschen
 *
 */
public class SwitchTransition {
	private IModel model;
	private ArcsModel arcsModel;
	private String startNodeClass;
	private String endNodeClass;
	private boolean transitionActive;
	private StatusBar statusBar;
	private AnimationMode animationMode;

	/**
	 * Konstruktor der SwitchTransition Klasse.
	 * 
	 * @param model
	 * @param arcsModel
	 * @param statusBar
	 * @param animationMode
	 */
	public SwitchTransition(IModel model, ArcsModel arcsModel, StatusBar statusBar, AnimationMode animationMode) {
		super();
		this.model = model;
		this.arcsModel = arcsModel;
		this.statusBar = statusBar;
		this.animationMode = animationMode;
	}

	/**
	 * Die Methode prüft ob eine transition aktiv ist.
	 * 
	 * @return true oder false
	 */
	public boolean isTransitionActive() {
		return transitionActive;
	}

	/**
	 * Die Methode setzt eine transition auf aktiv oder inaktiv
	 * 
	 * @param transitionActive
	 */
	public void setTransitionActive(boolean transitionActive) {
		this.transitionActive = transitionActive;
	}

	/**
	 * Die Methode liefert die Id der start node zurück.
	 * 
	 * @return start node Id
	 */
	public String getStartNodeClass() {
		return startNodeClass;
	}

	/**
	 * Die Methode setzt die start node.
	 * 
	 * @param startNodeClass
	 */
	public void setStartNodeClass(String startNodeClass) {
		this.startNodeClass = startNodeClass;
	}

	/**
	 * Die Methode liefert die Id der end node zurück.
	 * 
	 * @return end node Id
	 */
	public String getEndNodeClass() {
		return endNodeClass;
	}

	/**
	 * Die Methode setzt die end node.
	 * 
	 * @param endNodeClass
	 */
	public void setEndNodeClass(String endNodeClass) {
		this.endNodeClass = endNodeClass;
	}

	/**
	 * Die Methode bekommt eine transition Id übergeben und prüft ob diese aktive
	 * ist. Wenn die transition aktiv ist, wird true zurückgeliefert und wenn sie
	 * nicht aktiv ist false.
	 * 
	 * @param id
	 * @return true oder false
	 */
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
		if (marking.contains(false) || marking.isEmpty()) {
			setTransitionActive(false);
			return false;

		} else {
			setTransitionActive(true);
			return true;
		}

	}

	/**
	 * Die Methode liefert true zurück, wenn es zu einer Kontaktsituation gekommen
	 * ist, ansonsten false. Im Fehlerfall wird eine entsprechende Nachricht an die
	 * StatusBar übergeben.
	 * 
	 * @param id
	 * @return true oder false
	 */
	public boolean contact(String id) {

		List<Boolean> marking = new ArrayList<Boolean>();
		List<Boolean> contact = new ArrayList<Boolean>();

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
		}

		else {
			contact.add(true);
		}

		if (contact.contains(true)) {
			statusBar.setMessage("Contact", Color.RED);
			return true;
		} else {
			return false;
		}

	}

	/**
	 * Die Methode liefert true zurück, wenn es zu einem deadlock gekommen ist,
	 * ansonsten false. Im Fehlerfall wird eine entsprechende Nachricht an die
	 * StatusBar übergeben.
	 * 
	 * @return true oder false
	 */
	public boolean deadlock() {
		List<Boolean> active = new ArrayList<Boolean>();

		for (Node n : model.getAllTransitions()) {
			active.add(transitionActive(n.getId()));
		}
		if (active.contains(true) || reachedTheEndMarking()) {
			return false;
		} else if (animationMode.isAnimationMode()) {
			statusBar.setMessage("Deadlock", Color.RED);
			return true;
		}
		return false;

	}

	/**
	 * Die Methode liefert true zurück, wenn die Endmarkierung erreicht wurde,
	 * ansonsten false. Bei erreichen der Endmarkierung, wird eine entsprechende
	 * Nachricht an die StatusBar übergeben.
	 * 
	 * @return true oder false
	 */
	public boolean reachedTheEndMarking() {
		for (Node n : model.getAllPlaces()) {
			if (n.getId().equals(endNodeClass)) {
				if (n.getMarking()) {
					statusBar.setMessage("Reached end marking", Color.BLACK);
					return true;
				}
			}

		}

		return false;

	}

	/**
	 * Die Methode schlatet die transition und entfernt, sowie setzt, die
	 * Markierungen der places entsprechen neu.
	 * 
	 * @param transition
	 */
	public void switchTransition(Node transition) {
		if (transitionActive(transition.getId()) && !contact(transition.getId())) {

			for (Arc a : arcsModel.getArcs()) {
				if (a.getTarget().equals(transition.getId())) {

					for (Node n : model.getAllNodes()) {
						if (n.getId().equals(a.getSource())) {
							n.setMarking(false);

						}
					}
				}

			}

			for (Arc a : arcsModel.getArcs()) {
				if (a.getSource().equals(transition.getId())) {

					for (Node n : model.getAllNodes()) {
						if (n.getId().equals(a.getTarget())) {
							n.setMarking(true);

						}
					}
				}

			}

		}
	}

	/**
	 * Die Methode liefert true zurück, wenn alle nodes mit dem Graphen verbunden
	 * sind. Ansonsten false.
	 * 
	 * @return true oder false
	 */
	public boolean areAllNetworkElementsOnThePath() {
		List<String> arcsSource = new ArrayList<String>();
		List<String> arcsTarget = new ArrayList<String>();
		List<String> nodes = new ArrayList<String>();

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

	/**
	 * Die Methode prüft wie viele start und end places es gibt und übergibt eine
	 * entsprechende Nachricht an die StatusBar.
	 * 
	 */
	public void hasStartingEndingPlaces() {

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

				}
			}

		}

		for (int i = 0; i < nodes.size(); i++) {

			if (arcsTarget.contains(nodes.get(i))) {

				if (!arcsSource.contains(nodes.get(i))) {
					nodesEnd.add(nodes.get(i));

				}
			}

		}

		if (nodesStart.isEmpty() && nodesEnd.isEmpty() && model.getAllNodes().isEmpty()) {
			// keine anfangsstelle und keine endstelle
			setStartNodeClass(null);
			setEndNodeClass(null);
			statusBar.setMessage("Here we go...", Color.BLACK);
		}

		else if (nodesEnd.size() >= 2 && nodesStart.size() >= 2) {
			// zu viele anfangsstellen und endsstelle
			setStartNodeClass(null);
			setEndNodeClass(null);
			statusBar.setMessage("Too many starting places and too many ending place", Color.BLACK);
		}

		else if (nodesStart.size() >= 2 && nodesEnd.isEmpty()) {
			// zu viele anfangsstellen und keine endstelle
			setStartNodeClass(null);
			setEndNodeClass(null);
			statusBar.setMessage("Too many starting places and no ending place", Color.BLACK);
		}

		else if (nodesEnd.size() >= 2 && nodesStart.isEmpty()) {
			// keine anfangsstelle und zu viele endstellen
			setStartNodeClass(null);
			setEndNodeClass(null);
			statusBar.setMessage("No starting place and too many ending places", Color.BLACK);
		}

		else if (nodesStart.isEmpty() && nodesEnd.isEmpty()) {
			// keine anfangsstelle und keine endstelle
			setStartNodeClass(null);
			setEndNodeClass(null);
			statusBar.setMessage("No starting place and no ending place", Color.BLACK);
		}

		else if (nodesStart.isEmpty()) {
			// keine anfangsstelle
			setStartNodeClass(null);
			setEndNodeClass(null);
			statusBar.setMessage("No starting place", Color.BLACK);
		}

		else if (nodesEnd.isEmpty()) {
			// keine endstelle
			setStartNodeClass(null);
			setEndNodeClass(null);
			statusBar.setMessage("No ending place", Color.BLACK);
		}

		else if (nodesStart.size() >= 2) {
			// zu viele anfangsstellen
			setStartNodeClass(null);
			setEndNodeClass(null);
			statusBar.setMessage("Too many starting places", Color.BLACK);
		}

		else if (nodesEnd.size() >= 2) {
			// zu viele anfangsstellen
			setStartNodeClass(null);
			setEndNodeClass(null);
			statusBar.setMessage("Too many ending places", Color.BLACK);
		}

		else if (nodesStart.size() == 1 && nodesEnd.size() == 1) {
			// genau eine anfangsstelle und genau eine endstelle
			setStartNodeClass(nodesStart.get(0));
			setEndNodeClass(nodesEnd.get(0));
			statusBar.setMessage("It's a Workflow Net!", Color.GREEN);

		} else {
			setStartNodeClass(null);
			setEndNodeClass(null);
			statusBar.setMessage("Not all network elements on a path from start place to end place", Color.RED);
		}

	}

	/**
	 * Die Methode führt die beiden Methoden, areAllNetworkElementsOnThePath() und
	 * hasStartingEndingPlaces() aus. Und Prüft somit auf ein Workflownetz. Wenn es
	 * sich um ein Workflownetz handelt, wird true zurückgeliefert, ansonsten false.
	 * Es wird eine entsprechende Nachricht an die StatusBar übergeben.
	 * 
	 * @return true oder false
	 */
	public boolean isWorkflowNet() {
		if (areAllNetworkElementsOnThePath()) {

			hasStartingEndingPlaces();
			return true;

		}
		statusBar.setMessage("Not all network elements on a path from start place to end place", Color.RED);
		return false;
	}

}
