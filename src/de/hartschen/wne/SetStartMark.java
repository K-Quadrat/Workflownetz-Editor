package de.hartschen.wne;

public class SetStartMark {
	private SwitchTransition switchTransition;
	private IView iView;
	private IModel model;
	private AnimationMode animationMode;
	private Warshall warshall;

	public SetStartMark(SwitchTransition switchTransition, IModel model, AnimationMode animationMode,
			Warshall warshall) {
		super();
		this.switchTransition = switchTransition;
		this.model = model;
		this.animationMode = animationMode;
		this.warshall = warshall;
	}

	public SetStartMark(SwitchTransition switchTransition, IView iView, Warshall warshall) {
		super();
		this.switchTransition = switchTransition;
		this.iView = iView;
	}

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

	public void setIViewReference(IView iView) {
		this.iView = iView;

	}

}
