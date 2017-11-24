import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class NodeId {
	int placeIdCounter;
	int transitionIdCounter;
	String placeIdString;
	String transitionIdString;
	private List<Integer> placesIds = new ArrayList<Integer>();
	private List<Integer> transitionsIds = new ArrayList<Integer>();
	private IModel model;

	public NodeId(IModel model) {
		this.model = model;
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

}
