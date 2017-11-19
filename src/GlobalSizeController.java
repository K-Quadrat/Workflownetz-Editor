
public class GlobalSizeController {

	private IModel model;
	private IView view;
	private GlobalSizeModel globalSizeModel;

	public GlobalSizeController(IModel model, IView view, GlobalSizeModel globalSizeModel) {
		this.model = model;
		this.view = view;
		this.globalSizeModel = globalSizeModel;
	}

	public void nodesBigger() {
		
		globalSizeModel.setNodesSize(globalSizeModel.getNodesSize() + 10);
		globalSizeModel.setArcsSize(globalSizeModel.getArcsSize() + 2);

		for (Node n : model.getAllNodes()) {
			n.setRadius(globalSizeModel.getNodesSize());
			view.refresh();
		}

		for (Arc a : model.getArcs()) {
//			a.setRadius(globalSizeModel.getArcsSize());
			view.refresh();
		}
	}

	public void nodesSmaller() {
		globalSizeModel.setNodesSize(globalSizeModel.getNodesSize() - 10);
		globalSizeModel.setArcsSize(globalSizeModel.getArcsSize() - 2);

		for (Node n : model.getAllNodes()) {
			n.setRadius(globalSizeModel.getNodesSize());
			view.refresh();
		}

		for (Arc a : model.getArcs()) {
//			a.setRadius(globalSizeModel.getArcsSize());
			view.refresh();
		}
	}
}
