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
					Multiselect multiselect = new Multiselect(model, globalSizeModel);
					ViewController viewController = new ViewController(model, id, globalSizeModel, arcsModel);
					

					ArcsController arcsController = new ArcsController(model, arcsModel, globalSizeModel);
					SetStartMarkWithOutIView setStartMarkWithOutIView = new SetStartMarkWithOutIView(switchTransition, model, animationMode);
					MyJPanel myJPanel = new MyJPanel(model, popupMenuController, viewController, toolBarController, selectedNode, arcsModel, arcsController, globalSizeModel, statusBar, switchTransition, multiselect, animationMode, setStartMarkWithOutIView, warshall);
					IView iView = myJPanel;
					myJPanel.setIViewReference(iView);
					SetStartMark setStartMark = new SetStartMark(switchTransition, iView, model, animationMode, warshall);
					
					GlobalSizeController globalSizeController = new GlobalSizeController(model, iView, globalSizeModel, arcsModel);
					new MyJFrame(globalSizeController, myJPanel, toolBarController, model, iView, arcsModel, globalSizeModel, id, statusBar, setStartMark, animationMode, warshall);
					
										
					
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
						| UnsupportedLookAndFeelException ex) {
//				} catch (Exception ex) {

					ex.printStackTrace();
				}

			}
		});

	}
}
