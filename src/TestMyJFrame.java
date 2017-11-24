import java.awt.EventQueue;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class TestMyJFrame {

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {

					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					IModel model = new Model();
					NodeId nodeId = new NodeId(model);
					ArcsModel arcsModel = new ArcsModel();
					ToolBarController toolBarController = new ToolBarController();
					SelectedNode selectedNode = new SelectedNode();
					PopupMenuController popupMenuController = new PopupMenuController(model, selectedNode);
					GlobalSizeModel globalSizeModel = new GlobalSizeModel();
					ViewController viewController = new ViewController(model, nodeId, globalSizeModel);
					

					ArcsController arcsController = new ArcsController(model, arcsModel, globalSizeModel);
					MyJPanel myJPanel = new MyJPanel(model, popupMenuController, viewController, toolBarController, selectedNode, arcsModel, arcsController, globalSizeModel);
					IView iView = myJPanel;
					
					GlobalSizeController globalSizeController = new GlobalSizeController(model, iView, globalSizeModel, arcsModel);
					new MyJFrame(globalSizeController, myJPanel, toolBarController, model, iView, arcsModel, globalSizeModel, nodeId);
					
										
					
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
						| UnsupportedLookAndFeelException ex) {
//				} catch (Exception ex) {

					ex.printStackTrace();
				}

			}
		});

	}
}
