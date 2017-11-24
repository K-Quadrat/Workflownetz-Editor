import java.awt.Point;

public class ViewController extends MyJPanel {

	private IModel model;
	private IView view;
	private String placeId;
	private int placeIdCounter;
	private String transitionId;
	private int transitionIdCounter;
	private ID id;
	private GlobalSizeModel globalSizeModel;
	private ArcsModel arcsModel;

	public ViewController(IModel model, ID id, GlobalSizeModel globalSizeModel, ArcsModel arcsModel) {
		super();
		this.model = model;
		this.id = id;
		this.globalSizeModel = globalSizeModel;
		this.arcsModel = arcsModel;
	}



	public void addPlace(int x, int y) {
		model.setNode(id.getNextPlaceIdString(), x, y, globalSizeModel.getNodesSize(), ENode.PLACE, id.getPlaceIdString(),
				false);
		System.out.println(id.getPlaceIdString());
		refresh();

	}

	public void addTransition(int x, int y) {
		model.setNode(id.getNextTransitionIdString(), x, y, globalSizeModel.getNodesSize(), ENode.TRANSITION,
				id.getTransitionIdString(), false);
		System.out.println(id.getTransitionIdString());
		refresh();
	}
	
	public void addArc(String firstClickNodeId, String secondClickNodeId) {
		arcsModel.setArc(id.getNextArcIdString(), firstClickNodeId, secondClickNodeId, new Point(0, 0), new Point(0, 0), globalSizeModel.getArcsSize());
		System.out.println(id.getArcIdString());
		refresh();
	}

}
