package de.hartschen.wne;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class SwitchTransition {
	private IModel model;
	private ArcsModel arcsModel;
	private String startNodeClass;
	private String endNodeClass;
	private boolean transitionActive;
	private StatusBar statusBar;
	private AnimationMode animationMode;

	public SwitchTransition(IModel model, ArcsModel arcsModel, StatusBar statusBar, AnimationMode animationMode) {
		super();
		this.model = model;
		this.arcsModel = arcsModel;
		this.statusBar = statusBar;
		this.animationMode = animationMode;
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

	public boolean isWorkflowNet() {
		if (areAllNetworkElementsOnThePath()) {

			hasStartingEndingPlaces();
			return true;

		}
		statusBar.setMessage("Not all network elements on a path from start place to end place", Color.RED);
		return false;
	}

}
