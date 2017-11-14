
public class ViewRefresh {

	private IModel model;
	private IView view;

	public ViewRefresh(IModel model, IView view) {
		this.model = model;
		this.view = view;
	}

	public void nodesBigger() {
		for (Node n : model.getAllNodes()) {
			n.setRadius(n.getRadius() + 10);
			view.refresh();
		}
	}

	public void nodesSmaller() {
		for (Node n : model.getAllNodes()) {
			n.setRadius(n.getRadius() - 10);
			view.refresh();
		}
	}
}
