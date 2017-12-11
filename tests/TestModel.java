import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import com.sun.xml.internal.bind.v2.runtime.reflect.opt.TransducedAccessor_field_Float;

import de.hartschen.wne.Arc;
import de.hartschen.wne.ArcsModel;
import de.hartschen.wne.ENode;
import de.hartschen.wne.Model;
import de.hartschen.wne.Node;

public class TestModel {
	private static Point sourcePoint;
	private Point targetPoint;
	static boolean hitSource;
	static boolean hitTarget;
	static Point source;
	static Point target;
	static boolean delete;
	static boolean hitS;
	static boolean hitT;

	public static void main(String[] args) {
		Model model = new Model();
		ArcsModel arcsModel = new ArcsModel();

		// TestModel2 testModel2 = new TestModel2(model);

		model.setNode("S1", 50, 200, 50, ENode.PLACE, "p1", false);
		model.setNode("S2", 200, 125, 50, ENode.PLACE, "p2", false);

		// Generate few transitions
		model.setNode("T1", 125, 200, 50, ENode.TRANSITION, "t1");
//		model.setNode("T2", 275, 125, 50, ENode.TRANSITION, "t2");

		// Generate few arcs
		arcsModel.setArc("K1", "S1", "T1", new Point(0, 0), new Point(0, 0), 10d);
		arcsModel.setArc("K2", "T1", "S2", new Point(0, 0), new Point(0, 0), 10d);
		arcsModel.setArc("K3", "S2", "T2", new Point(0, 0), new Point(0, 0), 10d);

		System.out.println("Initial Arcs Size: "+ arcsModel.getArcs().size());

		for (int i = 0; i < arcsModel.getArcs().size(); i++) {
			Arc a = arcsModel.getArcs().get(i);
			
			for (int j = 0; j < model.getAllNodes().size(); j++) {
				Node n = model.getAllNodes().get(j);
				if (n.getId().equals(a.getSource())) {
					System.out.println("Source set hit to true");
					hitS = true;
				}
				if(n.getId().equals(a.getTarget())){
					System.out.println("Target set hit to true");
					hitT = true;
				}
				
			}
			
			if(!hitS||!hitT) {
				System.out.println("ID: "+a.getId());
				System.out.println("i: "+i);
				
				System.out.println("Arcs Size: "+ arcsModel.getArcs().size());
				System.out.println();
				arcsModel.deleteArcById(a.getId());
				i--;
				}
			hitS = false;
			hitT = false;
			

		}
		
		// System.out.println(model.getAllNodes());
		// System.out.println(arcsModel.getArcs());
//		for (Arc a : arcsModel.getArcs()) {
//			arcsModel.deleteArcById();
//			
//		}
		
//		for (Arc a : arcsModel.getArcs()) {
//			
//			for (Node n : model.getAllNodes()) {
//				if (n.getId().equals(a.getTarget())) {
//					hit = true;
//				}
//				
//			}
//			if(!hit) {
//				arcsModel.deleteArcById(a.getId());
//			}
//			
//		}
		
		
		System.out.println("SECOND METHOD STARTS HERE");
		
		

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
					hitSource = true;
				}
				if (hitSource) {
					for (Node ntarget : model.getAllNodes()) {
						if (ntarget.getId().equals(a.getTarget())) {
							System.out.println("Treffer target");
							target = new Point(ntarget.getX(), ntarget.getY());
							System.out.println(ntarget.getId());
							System.out.println(target);
							hitSource = false;
							
							hitTarget = true;
							
						}
						
					}

				}

				System.out.println(n.getId());
				System.out.println(n.getX());
				System.out.println(n.getY());
			}

//			if (!hitSource || !hitTarget) {
//				System.out.println("ID TO DELETE = " + a.getId());
//				hitSource = false;
//				hitSource = false;
//			}

			// arcsModel.deleteArcById();
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
