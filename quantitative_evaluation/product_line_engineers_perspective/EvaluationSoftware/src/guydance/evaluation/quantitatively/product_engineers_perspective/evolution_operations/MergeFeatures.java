package guydance.evaluation.quantitatively.product_engineers_perspective.evolution_operations;

public class MergeFeatures implements EvolutionOperation {

	private String sourceFeature;
	private String targetFeature;
	
	public MergeFeatures(String sourceFeature, String targetFeature) {
		this.sourceFeature = sourceFeature;
		this.targetFeature = targetFeature;
	}

	public String getSourceFeature() {
		return sourceFeature;
	}

	public void setSourceFeature(String sourceFeature) {
		this.sourceFeature = sourceFeature;
	}

	public String getTargetFeature() {
		return targetFeature;
	}

	public void setTargetFeature(String targetFeature) {
		this.targetFeature = targetFeature;
	}
	
	
	
}
