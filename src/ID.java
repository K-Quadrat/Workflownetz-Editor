import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
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
				// Remove all "S", parse String to int and add all numbers to ArrayList
				// placesIds
				placesIds.add(Integer.parseInt(n.getId().replaceAll("S", "")));
			}

			if (n.getNodeType() == ENode.TRANSITION) {
				// Remove all "T", parse String to int and add all numbers to ArrayList
				// transitionsIds
				transitionsIds.add(Integer.parseInt(n.getId().replaceAll("T", "")));
			}

		}
		placeIdCounter = Collections.max(placesIds);

		transitionIdCounter = Collections.max(transitionsIds);

		setPlaceIdString();
		setTransitionIdString();

	}

	public void setArcIdForParser() {
		for (Arc a : arcsModel.getArcs()) {
			// Remove all "K", parse String to int and add all numbers to ArrayList
			// placesIds
			arcsIds.add(Integer.parseInt(a.getId().replaceAll("K", "")));

		}
		arcIdCounter = Collections.max(arcsIds);
		setArcIdString();

	}

}
