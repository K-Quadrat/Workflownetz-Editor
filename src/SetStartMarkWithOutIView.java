
public class SetStartMarkWithOutIView {

	private SwitchTransition switchTransition;
	private IModel model;
	private AnimationMode animationMode;

	public SetStartMarkWithOutIView(SwitchTransition switchTransition, IModel model, AnimationMode animationMode) {
		super();
		this.switchTransition = switchTransition;
		this.model = model;
		this.animationMode = animationMode;
	}

	public SetStartMarkWithOutIView(SwitchTransition switchTransition, IView iView) {
		super();
		this.switchTransition = switchTransition;
	}

	public void setStartMarking() {
		if (switchTransition.isWorkflowNet()) {

			for (Node n : model.getAllPlaces()) {
				if (n.getId().equals(switchTransition.getStartNodeClass())) {
					n.setMarking(true);
					animationMode.setAnimationMode(true);

				} else {
					n.setMarking(false);
				}

			}
		}
		
		else if (!switchTransition.isWorkflowNet()) {

			for (Node n : model.getAllPlaces()) {
				if (n.getId().equals(switchTransition.getStartNodeClass())) {
					n.setMarking(false);
					animationMode.setAnimationMode(false);

				} else {
					n.setMarking(false);
				}

			}
		}

	}

}
