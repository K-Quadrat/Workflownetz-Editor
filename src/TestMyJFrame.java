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
					PopupMenuController popupMenuController = new PopupMenuController(model);
					ViewController viewController = new ViewController(model);
					MyJPanel myJPanel = new MyJPanel(model, popupMenuController, viewController, toolBarController);
					IView iView = myJPanel;
					ViewRefresh viewRefresh = new ViewRefresh(model, iView);
					MyJFrame myJFrame = new MyJFrame(viewRefresh, myJPanel, toolBarController);
					
					
										
					
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
						| UnsupportedLookAndFeelException ex) {
//				} catch (Exception ex) {

					ex.printStackTrace();
				}

			}
		});

	}
}
