package de.hartschen.wne;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Klasse zur Verwaltung der Ids für die Transitions, Places und Arcs
 * 
 * @author Jens Hartschen
 *
 */
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

	/**
	 * Konstruktor der ID Klasse.
	 * 
	 * @param model
	 * @param arcsModel
	 */
	public ID(IModel model, ArcsModel arcsModel) {
		this.model = model;
		this.arcsModel = arcsModel;
	}

	/**
	 * Die Methode liefert die höchste Place Id als Ganzzahl zurück.
	 * 
	 * @return Integer placeIdCounter
	 */
	public int getPlaceIdCounter() {
		return placeIdCounter;
	}

	/**
	 * Die Methode setzt die höchste Place Id als Ganzzahl.
	 * 
	 * @param placeIdCounter
	 */
	public void setPlaceIdCounter(int placeIdCounter) {
		this.placeIdCounter = placeIdCounter;
	}

	/**
	 * Die Methode liefert die höchste Transition Id als Ganzzahl zurück.
	 * 
	 * @return transitionIdCounter
	 */
	public int getTransitionIdCounter() {
		return transitionIdCounter;
	}

	/**
	 * Die Methode setzt die höchste Transition Id als Ganzzahl.
	 * 
	 * @param transitionIdCounter
	 */
	public void setTransitionIdCounter(int transitionIdCounter) {
		this.transitionIdCounter = transitionIdCounter;
	}

	/**
	 * Die Methode liefert die nächste Place Id als String zurück.
	 * 
	 * @return Sting placeIdString
	 */
	public String getNextPlaceIdString() {
		placeIdCounter++;
		setPlaceIdString();
		return placeIdString;
	}

	/**
	 * Die Methode liefert die aktuelle Place Id als String zurück.
	 * 
	 * @return String placeIdString
	 */
	public String getPlaceIdString() {
		return placeIdString;
	}

	/**
	 * Die Methode setzt die nächste Place Id als String.
	 */
	public void setPlaceIdString() {
		placeIdString = "S" + Integer.toString(placeIdCounter);
	}

	/**
	 * Die Methode liefert die nächste Transition Id als String zurück.
	 * 
	 * @return String transitionIdString
	 */
	public String getNextTransitionIdString() {
		transitionIdCounter++;
		setTransitionIdString();
		return transitionIdString;
	}

	/**
	 * Die Methode liefert die aktuelle Transition Id als String zurück.
	 * 
	 * @return String transitionIdString
	 */
	public String getTransitionIdString() {
		return transitionIdString;
	}

	/**
	 * Die Methode setzt die nächste Transition Id als String.
	 */
	public void setTransitionIdString() {
		transitionIdString = "T" + Integer.toString(transitionIdCounter);
	}

	/**
	 * Die Methode liefert die nächste Arc Id als String zurück.
	 * 
	 * @return String arcIdString
	 */
	public String getNextArcIdString() {
		arcIdCounter++;
		setArcIdString();
		return arcIdString;
	}

	/**
	 * Die Methode liefert die aktuelle Arc Id als String zurück.
	 * 
	 * @return String arcIdString
	 */
	public String getArcIdString() {
		return arcIdString;
	}

	public void setArcIdString() {
		arcIdString = "K" + Integer.toString(arcIdCounter);
	}

	/**
	 * Die Methode prüft die Datenhaltung auf Transitions und Places und setzt die
	 * Counter auf die neuen Werte.
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

	/**
	 * Die Methode prüft die Datenhaltung auf Arcs und setzt den Counter auf den
	 * neuen Wert.
	 */
	public void setArcIdForParser() {
		for (Arc a : arcsModel.getArcs()) {
			arcsIds.add(Integer.parseInt(a.getId().replaceAll("[A-Z,a-z]", "")));

		}
		arcIdCounter = Collections.max(arcsIds);
		setArcIdString();

	}

}
