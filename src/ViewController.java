
public class ViewController extends MyJPanel {

	private IModel model;
	private IView view;
	private String placeId;
	private int placeIdCounter;
	private String transitionId;
	private int transitionIdCounter;
	private NodeId nodeId;
	private GlobalSizeModel globalSizeModel;

	public ViewController(IModel model, NodeId nodeId, GlobalSizeModel globalSizeModel) {
		super();
		this.model = model;
		this.nodeId = nodeId;
		this.globalSizeModel = globalSizeModel;
	}



	public void addPlace(int x, int y) {
		model.setNode(nodeId.getNextPlaceIdString(), x, y, globalSizeModel.getNodesSize(), ENode.PLACE, nodeId.getPlaceIdString(),
				false);
		System.out.println(nodeId.getPlaceIdString());
		refresh();

	}

	public void addTransition(int x, int y) {
		model.setNode(nodeId.getNextTransitionIdString(), x, y, globalSizeModel.getNodesSize(), ENode.TRANSITION,
				nodeId.getTransitionIdString(), false);
		System.out.println(nodeId.getTransitionIdString());
		refresh();
	}

}
