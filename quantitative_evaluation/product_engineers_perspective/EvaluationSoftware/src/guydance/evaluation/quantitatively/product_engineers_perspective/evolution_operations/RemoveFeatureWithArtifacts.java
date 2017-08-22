package guydance.evaluation.quantitatively.product_engineers_perspective.evolution_operations;

public class RemoveFeatureWithArtifacts implements EvolutionOperation {

	private String featureToRemove;
	
	public RemoveFeatureWithArtifacts(String featureToRemove) {
		this.featureToRemove = featureToRemove;
	}

	public String getFeatureToRemove() {
		return featureToRemove;
	}

	public void setFeatureToRemove(String featureToRemove) {
		this.featureToRemove = featureToRemove;
	}
	
	
	
}
