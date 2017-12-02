
public class SetStartMark {
	private SwitchTransition switchTransition;
	private IView iView;
	private IModel model;

	
	
	public SetStartMark(SwitchTransition switchTransition, IView iView, IModel model) {
		super();
		this.switchTransition = switchTransition;
		this.iView = iView;
		this.model = model;
	}



	public SetStartMark(SwitchTransition switchTransition, IView iView) {
		super();
		this.switchTransition = switchTransition;
		this.iView = iView;
	}



	public void setStartMarking() {
		if(switchTransition.isWorkflowNet().equals("It's a Workflow Net!")) {
			

			for (Node n : model.getAllPlaces()) {
				if(n.getId().equals(switchTransition.getStartNodeClass())) {
					n.setMarking(true);
				}
				else {
					n.setMarking(false);
				}
				
			}
			switchTransition.setContact(false);
			iView.refresh();
		}
		
	}
	
	
}
