package de.hartschen.wne;

/**
 * Controller Klasse für die globale Größe.
 * 
 * @author Jens Hartschen
 *
 */
public class GlobalSizeController {

	private IModel model;
	private IView view;
	private GlobalSizeModel globalSizeModel;

	/**
	 * Konstruktor der GlobalSizeController Klasse.
	 * 
	 * @param model
	 * @param view
	 * @param globalSizeModel
	 */
	public GlobalSizeController(IModel model, IView view, GlobalSizeModel globalSizeModel) {
		this.model = model;
		this.view = view;
		this.globalSizeModel = globalSizeModel;
	}

	/**
	 * Die Methode vergrößert die nodes und die Pfeilspitzen.
	 */
	public void nodesBigger() {

		globalSizeModel.setNodesSize(globalSizeModel.getNodesSize() + 10);
		globalSizeModel.setArcsSize(globalSizeModel.getArcsSize() + 2);

		for (Node n : model.getAllNodes()) {
			n.setRadius(globalSizeModel.getNodesSize());
			view.refresh();
		}

	}

	/**
	 * Die Methode verkleinert die nodes und die Pfeilspitzen.
	 */
	public void nodesSmaller() {
		globalSizeModel.setNodesSize(globalSizeModel.getNodesSize() - 10);
		globalSizeModel.setArcsSize(globalSizeModel.getArcsSize() - 2);

		for (Node n : model.getAllNodes()) {
			n.setRadius(globalSizeModel.getNodesSize());
			view.refresh();
		}

	}
}
