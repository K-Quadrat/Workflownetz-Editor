package de.hartschen.wne;

import java.awt.Color;

public class Warshall {
	private ArcsModel arcsModel;
	private ID id;
	private SwitchTransition switchTransition;
	private StatusBar statusBar;
	int lastPlaceId;
	int lastTransitionId;
	private int matrixSize;

	private int[][] matrix;

	private int V;
	private boolean[][] tc;

	public Warshall(ArcsModel arcsModel, ID id, SwitchTransition switchTransition, StatusBar statusBar) {
		super();
		this.arcsModel = arcsModel;
		this.id = id;
		this.switchTransition = switchTransition;
		this.statusBar = statusBar;
	}

	public boolean check() {
		createMatrix();
		getTC(matrix);
		// displayTC();
		if (!connectedWithStartAndEndPlace()) {
			return false;
		} else {
			return true;
		}
	}

	public void createMatrix() {

		lastPlaceId = id.getPlaceIdCounter();
		lastTransitionId = id.getTransitionIdCounter();
		matrixSize = lastPlaceId + lastTransitionId;

		// System.out.println("lastPlaceId: " + lastPlaceId);
		// System.out.println("lastTransitionId: " + lastTransitionId);
		// System.out.println("matrixSize: " + matrixSize);

		matrix = new int[matrixSize][matrixSize];

		// create matrix
		for (Arc a : arcsModel.getArcs()) {
			int sourceMatrixIndex = 0;
			int targetMatrixIndex = 0;
			if (a.getSource().contains("S")) {
				sourceMatrixIndex = (Integer.parseInt(a.getSource().replaceAll("[A-Z,a-z]", "")));

				sourceMatrixIndex--;

				targetMatrixIndex = (Integer.parseInt(a.getTarget().replaceAll("[A-Z,a-z]", "")));
				targetMatrixIndex = targetMatrixIndex + lastPlaceId;
				targetMatrixIndex--;

			} else if (a.getSource().contains("T")) {
				sourceMatrixIndex = (Integer.parseInt(a.getSource().replaceAll("[A-Z,a-z]", "")));
				sourceMatrixIndex = sourceMatrixIndex + lastPlaceId;
				sourceMatrixIndex--;

				targetMatrixIndex = (Integer.parseInt(a.getTarget().replaceAll("[A-Z,a-z]", "")));
				targetMatrixIndex--;

			}

			matrix[sourceMatrixIndex][targetMatrixIndex] = 1;
		}
		// system out print matrix
		// System.out.println("\nMatrix :\n");
		// for (int v = 0; v < matrixSize; v++) {
		// for (int w = 0; w < matrixSize; w++) {
		// System.out.print(matrix[v][w]);
		// }
		// System.out.println();
		// }

		getTC(matrix);
	}

	/** Function to make the transitive closure **/
	public void getTC(int[][] graph) {
		this.V = graph.length;
		tc = new boolean[V][V];
		for (int i = 0; i < V; i++) {
			for (int j = 0; j < V; j++)
				if (graph[i][j] != 0)
					tc[i][j] = true;
			tc[i][i] = true;
		}
		for (int i = 0; i < V; i++) {
			for (int j = 0; j < V; j++) {
				if (tc[j][i])
					for (int k = 0; k < V; k++)
						if (tc[j][i] && tc[i][k])
							tc[j][k] = true;
			}
		}
	}

	/** Funtion to display the trasitive closure **/
	public void displayTC() {
		System.out.println("\nTransitive closure :\n");
		System.out.print(" ");
		for (int v = 0; v < V; v++)
			System.out.print("   " + v);
		System.out.println();
		for (int v = 0; v < V; v++) {
			System.out.print(v + " ");
			for (int w = 0; w < V; w++) {
				if (tc[v][w])
					System.out.print("  * ");
				else
					System.out.print("    ");
			}
			System.out.println();
		}
	}

	public boolean connectedWithStartAndEndPlace() {
		// set startPlaceMatrixIndex and endPlaceMatrixIndex
		if (switchTransition.getStartNodeClass() != null && !switchTransition.getStartNodeClass().isEmpty()
				&& switchTransition.getEndNodeClass() != null && !switchTransition.getEndNodeClass().isEmpty()) {
			int startPlaceMatrixIndex = (Integer
					.parseInt(switchTransition.getStartNodeClass().replaceAll("[A-Z,a-z]", "")));
			startPlaceMatrixIndex--;

			int endPlaceMatrixIndex = (Integer
					.parseInt(switchTransition.getEndNodeClass().replaceAll("[A-Z,a-z]", "")));
			endPlaceMatrixIndex--;

			for (int i = 0; i < matrixSize; i++) {
				if (!tc[startPlaceMatrixIndex][i]) {
					// if false
					if (!nodeStillExist(i)) {
						return false;
					}
					;
				}
			}

			for (int i = 0; i < matrixSize; i++) {
				if (!tc[i][endPlaceMatrixIndex]) {
					// if false
					if (!nodeStillExist(i)) {
						return false;
					}
					;
				}
			}
		}
		return true;

	}

	public boolean nodeStillExist(int matrixIndex) {
		String nodeId = null;

		if (matrixIndex <= lastPlaceId) {
			matrixIndex++;
			nodeId = ("S" + matrixIndex);
		} else if (matrixIndex > lastPlaceId) {
			matrixIndex = matrixIndex - lastPlaceId;
			matrixIndex++;
			nodeId = ("T" + matrixIndex);

		}

		for (Arc as : arcsModel.getArcs()) {
			if (as.getSource().equals(nodeId)) {
				statusBar.setMessage("Not all network elements on a path from start place to end place 2", Color.RED);
				return false;

			}

			for (Arc at : arcsModel.getArcs()) {
				if (at.getTarget().equals(nodeId)) {
					statusBar.setMessage("Not all network elements on a path from start place to end place 2",
							Color.RED);
					return false;

				}
			}

		}
		return true;

	}

}
