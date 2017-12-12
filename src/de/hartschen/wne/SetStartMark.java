package de.hartschen.wne;

/**
 * Klasse zum setzten der Markierung auf der start node.
 * 
 * @author Jens Hartschen
 *
 */
public class SetStartMark {
	private SwitchTransition switchTransition;
	private IView iView;
	private IModel model;
	private AnimationMode animationMode;
	private Warshall warshall;

	/**
	 * Konstruktor der SetStartMark Klasse.
	 * 
	 * @param switchTransition
	 * @param model
	 * @param animationMode
	 * @param warshall
	 */
	public SetStartMark(SwitchTransition switchTransition, IModel model, AnimationMode animationMode,
			Warshall warshall) {
		super();
		this.switchTransition = switchTransition;
		this.model = model;
		this.animationMode = animationMode;
		this.warshall = warshall;
	}

	/**
	 * Die Methode setzt die Markierung für die start node.
	 */
	public void setStartMarking() {
		warshall.check();
		if (switchTransition.isWorkflowNet() && warshall.check()) {

			for (Node n : model.getAllPlaces()) {
				if (n.getId().equals(switchTransition.getStartNodeClass())) {
					n.setMarking(true);
					animationMode.setAnimationMode(true);

				} else {
					n.setMarking(false);
				}

			}
			iView.refresh();
		}

		else if (!switchTransition.isWorkflowNet() || !warshall.check()) {

			for (Node n : model.getAllPlaces()) {
				if (n.getId().equals(switchTransition.getStartNodeClass())) {
					n.setMarking(false);
					animationMode.setAnimationMode(false);

				} else {
					n.setMarking(false);
				}

			}
			iView.refresh();
		}

	}

	/**
	 * Die Methode bekommt eine Referenz des iView Objekts übergeben, um die
	 * refresh() Methode der WNEPanel Klasse zugänglich zu machen.
	 * 
	 * @param iView
	 */
	public void setIViewReference(IView iView) {
		this.iView = iView;

	}

}
