import java.awt.Color;

public class PopupMenuController {

	private IModel model;
	private SelectedNode selectedNode;
	private ArcsModel arcsModel;


	public PopupMenuController(IModel model, SelectedNode selectedNode,  ArcsModel arcsModel) {
		this.model = model;
		this.selectedNode = selectedNode;
		this.arcsModel = arcsModel;
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
