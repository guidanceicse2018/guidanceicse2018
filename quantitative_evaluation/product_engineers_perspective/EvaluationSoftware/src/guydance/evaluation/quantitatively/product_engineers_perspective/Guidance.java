package guydance.evaluation.quantitatively.product_engineers_perspective;

import guydance.evaluation.quantitatively.product_engineers_perspective.evolution_operations.EvolutionOperation;

public class Guidance {

	private Configuration configuration;
	private EvolutionOperation evolutionOperation;
	private String guidanceIdFromPaper;
	private boolean behaviorPreserving;
	
	public Guidance(Configuration configuration, EvolutionOperation evolutionOperation, String guidanceIdFromPaper, boolean behaviorPreserving) {
		this.configuration = configuration;
		this.evolutionOperation = evolutionOperation;
		this.guidanceIdFromPaper = guidanceIdFromPaper;
		this.behaviorPreserving = behaviorPreserving;
	}

	public Configuration getConfiguration() {
		return configuration;
	}

	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}

	public EvolutionOperation getEvolutionOperation() {
		return evolutionOperation;
	}

	public void setEvolutionOperation(EvolutionOperation evolutionOperation) {
		this.evolutionOperation = evolutionOperation;
	}

	public String getGuidanceIdFromPaper() {
		return guidanceIdFromPaper;
	}

	public void setGuidanceIdFromPaper(String guidanceIdFromPaper) {
		this.guidanceIdFromPaper = guidanceIdFromPaper;
	}

	public boolean isBehaviorPreserving() {
		return behaviorPreserving;
	}

	public void setBehaviorPreserving(boolean behaviorPreserving) {
		this.behaviorPreserving = behaviorPreserving;
	}
	
	
	
}
