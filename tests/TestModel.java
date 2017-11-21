import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import com.sun.xml.internal.bind.v2.runtime.reflect.opt.TransducedAccessor_field_Float;

public class TestModel {
	private static Point sourcePoint;
	private Point targetPoint;
	static boolean treffer;
	static Point source;
	static Point target;

	public static void main(String[] args) {
		Model model = new Model();
		ArcsModel arcsModel = new ArcsModel();

		// TestModel2 testModel2 = new TestModel2(model);

		model.setNode("S1", 50, 200, 50, ENode.PLACE, "p1", false);
		model.setNode("S2", 200, 125, 50, ENode.PLACE, "p2", false);

		// Generate few transitions
		model.setNode("T1", 125, 200, 50, ENode.TRANSITION, "t1");
		model.setNode("T2", 275, 125, 50, ENode.TRANSITION, "t2");

		// Generate few arcs
		arcsModel.setArc("K1", "S1", "T1", new Point(0, 0), new Point(0, 0), 10d);
		arcsModel.setArc("K2", "T1", "S2", new Point(0, 0), new Point(0, 0), 10d);
		arcsModel.setArc("K3", "S2", "T2", new Point(0, 0), new Point(0, 0), 10d);

		// System.out.println(model.getAllNodes());
		// System.out.println(arcsModel.getArcs());

		for (Arc a : arcsModel.getArcs()) {
			System.out.println(a.getId());
			System.out.println(a.getSource());
			System.out.println(a.getTarget());

			for (Node n : model.getAllNodes()) {
				if (n.getId().equals(a.getSource())) {
					System.out.println("Treffer source");
					source = new Point(n.getX(), n.getY());
					System.out.println(n.getId());
					System.out.println(source);
					treffer = true;
				}
				if (treffer) {
					for (Node ntarget : model.getAllNodes()) {
						if (ntarget.getId().equals(a.getTarget())) {
							System.out.println("Treffer target");
							target = new Point(ntarget.getX(), ntarget.getY());
							System.out.println(ntarget.getId());
							System.out.println(target);
							treffer = false;
						}
					}

				}
				System.out.println(n.getId());
				System.out.println(n.getX());
				System.out.println(n.getY());
			}

		}

		// for (Node n : model.getAllNodes()) {
		//
		// for(Arc a : model.getArcs()) {
		//
		// if(n.getId().equals(a.getSource())) {
		// System.out.println("Treffer source");
		// int sourceX = n.getX();
		// int sourceY = n.getY();
		// sourcePoint.setLocation(sourceX, sourceY);
		// }
		// if(n.getId().equals(a.getTarget())) {
		// System.out.println("Treffer target");
		// int targetX = n.getX();
		// int targetY = n.getY();
		// }
		// }
		// }

	}

}
