
public class ViewController {

	private IModel model;
	private IView view;
	private String placeId;
	private int placeIdCounter;
	private String transitionId;
	private int transitionIdCounter;
	private int radius;

	public ViewController(IModel model) {
		this.model = model;
	}

	public int getRadius() {
		for (Node n : model.getAllNodes()) {
			radius = n.getRadius();
		}
		return radius;
	}

	/**
	 * @return the placeId
	 */
	public String getPlaceId() {
		placeIdCounter++;
		placeId = "S" + Integer.toString(placeIdCounter);
		System.out.println(placeId);
		return placeId;
	}

	/**
	 * @param placeId
	 *            the placeId to set
	 */
	public void setPlaceIdCounter(int placeIdCounter) {
		this.placeIdCounter = placeIdCounter;
	}

	/**
	 * @return the transitionId
	 */
	public String getTransitionId() {
		transitionIdCounter++;
		transitionId = "T" + Integer.toString(transitionIdCounter);
		System.out.println(transitionId);
		return transitionId;
	}

	/**
	 * @param transitionId
	 *            the transitionId to set
	 */
	public void setTransitionIdCounter(int transitionIdCounter) {
		this.transitionIdCounter = transitionIdCounter;
	}

	public void addPlace(int x, int y) {
		model.setNode(getPlaceId(), x, y, getRadius(), ENode.PLACE, "no name yet", false);

	}
	
	public void addTransition(int x, int y) {
		model.setNode(getTransitionId(), x, y, getRadius(), ENode.TRANSITION, "no name yet", false);

	}


}
