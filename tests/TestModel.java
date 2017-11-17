import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class TestModel {
	private static Point sourcePoint;
	private Point targetPoint;
	
	
	public static void main(String[] args) {
		Model model = new Model();

		// TestModel2 testModel2 = new TestModel2(model);

		model.setNode("S1", 200, 300, 50, ENode.PLACE, "P1", false);
		model.setNode("S2", 300, 300, 50, ENode.PLACE, "P2", false);
		model.setNode("S3", 400, 300, 50, ENode.PLACE, "P3", false);
		model.setNode("S4", 500, 300, 50, ENode.PLACE, "P4", false);

		// Generate few transitions
		model.setNode("T1", 200, 400, 50, ENode.TRANSITION, "Wohnzimmer aufräumen", false);
		model.setNode("T2", 300, 400, 50, ENode.TRANSITION, "Wohnzimmer fegen", false);
		model.setNode("T3", 400, 400, 50, ENode.TRANSITION, "Spülmaschiene einräumen", false);
		model.setNode("T4", 500, 400, 50, ENode.TRANSITION, "Spülmaschiene starten", false);

		// Generate few arcs
		model.setArc("K1", "S1", "T1");
		model.setArc("K2", "T1", "S2");
		model.setArc("K3", "S2", "T3");

		// System.out.println(model.getAllNodes());

		// for (Node n : model.getAllNodes()) {
		// System.out.println(n.getName());
		//
		// }
		List<Arc> arcs = new ArrayList<Arc>();
		List<Node> nodes = new ArrayList<Node>();

		

		for (Node n : model.getAllNodes()) {
			
			for(Arc a : model.getArcs()) {
				
				if(n.getId().equals(a.getSource())) {
					System.out.println("Treffer source");
					int sourceX = n.getX();
					int sourceY = n.getY();
					sourcePoint.setLocation(sourceX, sourceY);
				}
				if(n.getId().equals(a.getTarget())) {
					System.out.println("Treffer target");
					int targetX = n.getX();
					int targetY = n.getY();
				}
			}
		}

		

	}

}
