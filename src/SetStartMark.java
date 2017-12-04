
public class SetStartMark {
	private SwitchTransition switchTransition;
	private IView iView;
	private IModel model;
	private AnimationMode animationMode;

	
	
	public SetStartMark(SwitchTransition switchTransition, IView iView, IModel model, AnimationMode animationMode) {
		super();
		this.switchTransition = switchTransition;
		this.iView = iView;
		this.model = model;
		this.animationMode = animationMode;
	}



	public SetStartMark(SwitchTransition switchTransition, IView iView) {
		super();
		this.switchTransition = switchTransition;
		this.iView = iView;
	}



	public void setStartMarking() {
		if(switchTransition.isWorkflowNet()) {
			

			for (Node n : model.getAllPlaces()) {
				if(n.getId().equals(switchTransition.getStartNodeClass())) {
					n.setMarking(true);
					animationMode.setAnimationMode(true);

				}
				else {
					n.setMarking(false);
				}
				
			}
			iView.refresh();
		}
		
	}
	
	
}
