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
					ToolBarController toolBarController = new ToolBarController();
					SelectedNode selectedNode = new SelectedNode();
					PopupMenuController popupMenuController = new PopupMenuController(model, selectedNode);
					ViewController viewController = new ViewController(model);
					MyJPanel myJPanel = new MyJPanel(model, popupMenuController, viewController, toolBarController, selectedNode);
					IView iView = myJPanel;

					SizeController sizeController = new SizeController(model, iView);
					MyJFrame myJFrame = new MyJFrame(sizeController, myJPanel, toolBarController);
					
					
										
					
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
						| UnsupportedLookAndFeelException ex) {
//				} catch (Exception ex) {

					ex.printStackTrace();
				}

			}
		});

	}
}
