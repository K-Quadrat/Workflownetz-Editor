package de.hartschen.wne;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ID {
	int placeIdCounter;
	int transitionIdCounter;
	int arcIdCounter;
	String placeIdString;
	String transitionIdString;
	String arcIdString;
	private List<Integer> placesIds = new ArrayList<Integer>();
	private List<Integer> transitionsIds = new ArrayList<Integer>();
	private List<Integer> arcsIds = new ArrayList<Integer>();
	private IModel model;
	private ArcsModel arcsModel;

	public ID(IModel model, ArcsModel arcsModel) {
		this.model = model;
		this.arcsModel = arcsModel;
	}

	
	
	/**
	 * @return the placeIdCounter
	 */
	public int getPlaceIdCounter() {
		return placeIdCounter;
	}



	/**
	 * @param placeIdCounter the placeIdCounter to set
	 */
	public void setPlaceIdCounter(int placeIdCounter) {
		this.placeIdCounter = placeIdCounter;
	}



	/**
	 * @return the transitionIdCounter
	 */
	public int getTransitionIdCounter() {
		return transitionIdCounter;
	}



	/**
	 * @param transitionIdCounter the transitionIdCounter to set
	 */
	public void setTransitionIdCounter(int transitionIdCounter) {
		this.transitionIdCounter = transitionIdCounter;
	}



	/**
	 * @return the placeIdString
	 */
	public String getNextPlaceIdString() {
		placeIdCounter++;
		setPlaceIdString();
		return placeIdString;
	}

	public String getPlaceIdString() {
		return placeIdString;
	}

	/**
	 * @param placeIdString
	 *            the placeIdString to set
	 */
	public void setPlaceIdString() {
		placeIdString = "S" + Integer.toString(placeIdCounter);
	}

	/**
	 * @return the transitionIdString
	 */
	public String getNextTransitionIdString() {
		transitionIdCounter++;
		setTransitionIdString();
		return transitionIdString;
	}

	public String getTransitionIdString() {
		return transitionIdString;
	}

	/**
	 * @param transitionIdString
	 *            the transitionIdString to set
	 */
	public void setTransitionIdString() {
		transitionIdString = "T" + Integer.toString(transitionIdCounter);
	}

	public String getNextArcIdString() {
		arcIdCounter++;
		setArcIdString();
		return arcIdString;
	}

	public String getArcIdString() {
		return arcIdString;
	}

	public void setArcIdString() {
		arcIdString = "K" + Integer.toString(arcIdCounter);
	}

	/**
	 * @param placesIds
	 *            the placesIds to set
	 */
	public void setBothIdForParser() {
		for (Node n : model.getAllNodes()) {
			if (n.getNodeType() == ENode.PLACE) {
				placesIds.add(Integer.parseInt(n.getId().replaceAll("[A-Z,a-z]", "")));
			}

			if (n.getNodeType() == ENode.TRANSITION) {
				transitionsIds.add(Integer.parseInt(n.getId().replaceAll("[A-Z,a-z]", "")));
			}

		}
		placeIdCounter = Collections.max(placesIds);

		transitionIdCounter = Collections.max(transitionsIds);

		setPlaceIdString();
		setTransitionIdString();

	}

	public void setArcIdForParser() {
		for (Arc a : arcsModel.getArcs()) {
			arcsIds.add(Integer.parseInt(a.getId().replaceAll("[A-Z,a-z]", "")));

		}
		arcIdCounter = Collections.max(arcsIds);
		setArcIdString();

	}

}
