
public class TestModel {

	public static void main(String[] args) {
		Model model = new Model();

//		TestModel2 testModel2 = new TestModel2(model);
		
		model.setNode(200, 400, 50, ENode.PLACE, "Place number 1", false);
		model.setNode(200, 400, 50, ENode.PLACE, "Place number 2", false);
		model.setNode(200, 400, 50, ENode.PLACE, "Place number 3", false);
		model.setNode(200, 400, 50, ENode.TRANSITION, "Transition number 2", false);
		model.setNode(400, 800, 50, ENode.TRANSITION, "Transition number 3", false);

//		System.out.println(model.getAllNodes());
		
		for (Node n : model.getAllNodes()) {
			System.out.println(n.getName());
			
		}
//		model.getAllNodes();

		
		
	}

}
