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
					StatusBar statusBar = new StatusBar();
					AnimationMode animationMode = new AnimationMode();
					IModel model = new Model();
					ArcsModel arcsModel = new ArcsModel();
					SwitchTransition switchTransition = new SwitchTransition(model, arcsModel, statusBar, animationMode);
					ID id = new ID(model, arcsModel);
					Warshall warshall = new Warshall(arcsModel, model, id, switchTransition, statusBar);

					ToolBarController toolBarController = new ToolBarController();
					
					GlobalSizeModel globalSizeModel = new GlobalSizeModel();
					SelectedNode selectedNode = new SelectedNode(model, globalSizeModel);
					PopupMenuController popupMenuController = new PopupMenuController(model, selectedNode, arcsModel);
					
					ViewController viewController = new ViewController(model, id, globalSizeModel, arcsModel);
					

					ArcsController arcsController = new ArcsController(model, arcsModel, globalSizeModel);
					Multiselect multiselect = new Multiselect(model, globalSizeModel, arcsController);
					SetStartMark setStartMark = new SetStartMark(switchTransition, model, animationMode, warshall);
					MyJPanel myJPanel = new MyJPanel(model, popupMenuController, viewController, toolBarController, selectedNode, arcsModel, arcsController, globalSizeModel, statusBar, switchTransition, multiselect, animationMode, setStartMark, warshall);
					IView iView = myJPanel;
					myJPanel.setIViewReference(iView);

					setStartMark.setIViewReference(iView);
					
					GlobalSizeController globalSizeController = new GlobalSizeController(model, iView, globalSizeModel, arcsModel);
					new MyJFrame(globalSizeController, myJPanel, toolBarController, model, iView, arcsModel, globalSizeModel, id, statusBar, setStartMark, animationMode, warshall, multiselect, arcsController);
					
										
					
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
						| UnsupportedLookAndFeelException ex) {
//				} catch (Exception ex) {

					ex.printStackTrace();
				}

			}
		});

	}
}
