import java.util.Arrays;

public class AllNodesOnGraph {
	private ArcsModel arcsModel;
	private IModel model;

	public AllNodesOnGraph(ArcsModel arcsModel, IModel model) {
		super();
		this.arcsModel = arcsModel;
		this.model = model;
	}

	public AllNodesOnGraph(ArcsModel arcsModel) {
		super();
		this.arcsModel = arcsModel;
	}

	public void check() {
		
		int numberOfPlaces = model.getAllPlaces().size();
		int numberOfTransitions = model.getAllTransitions().size();
		int numberOfNodes = model.getAllNodes().size();
		
		
		
		Boolean[] matrix = new Boolean[9];
		Arrays.fill(matrix, Boolean.FALSE);
		
		
		
	}

}
