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
					PopupMenuController popupMenuController = new PopupMenuController(model);
					MyJPanel myJPanel = new MyJPanel(model, popupMenuController);
					IView iView = myJPanel;
					ViewController viewController = new ViewController(model, iView);
					MyJFrame myJFrame = new MyJFrame(viewController, myJPanel);


					
										
					
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
						| UnsupportedLookAndFeelException ex) {
//				} catch (Exception ex) {

					ex.printStackTrace();
				}

			}
		});

	}
}
