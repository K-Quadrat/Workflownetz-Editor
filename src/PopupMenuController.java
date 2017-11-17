import java.awt.Color;

public class PopupMenuController {

	private IModel model;
	private SelectedNode selectedNode;

	public PopupMenuController(IModel model, SelectedNode selectedNode) {
		this.model = model;
		this.selectedNode = selectedNode;
	}

	public void connect() {
		System.out.println("Method connect in PopupMenuController");

	}

	public void setName() {

	}

	
	

	// public void delete(int clickX, int clickY) {
	// model.deleteNode(clickX, clickY);
	//
	// }

}
