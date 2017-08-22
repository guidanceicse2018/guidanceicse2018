package guydance.evaluation.quantitatively.product_engineers_perspective;

import java.util.ArrayList;
import java.util.List;

import guydance.evaluation.quantitatively.product_engineers_perspective.evolution_operations.EvolutionOperation;
import guydance.evaluation.quantitatively.product_engineers_perspective.evolution_operations.ExtractFeature;
import guydance.evaluation.quantitatively.product_engineers_perspective.evolution_operations.MergeFeatures;
import guydance.evaluation.quantitatively.product_engineers_perspective.evolution_operations.RemoveFeatureWithArtifacts;

public class GuidanceChecker {

	public static List<Guidance> checkGuidance(List<Configuration> configurations, List<EvolutionOperation> evolutionOperations) {
		List<Guidance> guidances = new ArrayList<Guidance>(configurations.size()+evolutionOperations.size());
		
		for(EvolutionOperation evolutionOperation: evolutionOperations) {
			guidances.addAll(checkGuidance(configurations, evolutionOperation));
		}
		
		return guidances;
		
	}
	
	public static List<Guidance> checkGuidance(List<Configuration> configurations, EvolutionOperation evolutionOperation) {
		List<Guidance> guidances = new ArrayList<Guidance>(configurations.size());
		
		for(Configuration configuration: configurations) {
			guidances.add(checkGuidance(configuration, evolutionOperation));
		}
		
		return guidances;
	}
	
	public static Guidance checkGuidance(Configuration configuration, EvolutionOperation evolutionOperation) {
		if(evolutionOperation instanceof MergeFeatures) {
			return checkMergeFeaturesGuidance(configuration, (MergeFeatures) evolutionOperation);
		}
		else if(evolutionOperation instanceof ExtractFeature) {
			return checkExtractFeatureGuidance(configuration, (ExtractFeature) evolutionOperation);
		}
		else if(evolutionOperation instanceof RemoveFeatureWithArtifacts) {
			return checkRemoveFeatureGuidance(configuration, (RemoveFeatureWithArtifacts) evolutionOperation);
		}
		
		return null;
	}
	
	public static Guidance checkRemoveFeatureGuidance(Configuration configuration, RemoveFeatureWithArtifacts evoOp) {		
		String guidanceId;
		boolean behaviorPreserving;
		
		if(!configuration.getSelectedFeatures().contains(evoOp.getFeatureToRemove())) {
			guidanceId = "D0";
			behaviorPreserving = true;
		}
		else {
			guidanceId = "D1";
			behaviorPreserving = false;
		}
		
		Guidance guidance = new Guidance(configuration, evoOp, guidanceId, behaviorPreserving);
		
		return guidance;
	}
	
	// Reference to the paper: target == f_0, source == f_1 
	public static Guidance checkMergeFeaturesGuidance(Configuration configuration, MergeFeatures evoOp) {
		String guidanceId = "";
		boolean behaviorPreserving = false;
		
		if (configuration.getSelectedFeatures().contains(evoOp.getTargetFeature()) && configuration.getSelectedFeatures().contains(evoOp.getSourceFeature())) {
			guidanceId = "M0";
			behaviorPreserving = true;
		}
		else if(!configuration.getSelectedFeatures().contains(evoOp.getTargetFeature()) && !configuration.getSelectedFeatures().contains(evoOp.getSourceFeature())) {
			guidanceId = "M1";
			behaviorPreserving = true;
		}
		else if(!configuration.getSelectedFeatures().contains(evoOp.getTargetFeature()) && configuration.getSelectedFeatures().contains(evoOp.getSourceFeature())) {
			guidanceId = "M2";
			behaviorPreserving = false;
		}
		else if(configuration.getSelectedFeatures().contains(evoOp.getTargetFeature()) && !configuration.getSelectedFeatures().contains(evoOp.getSourceFeature())) {
			guidanceId = "M3";
			behaviorPreserving = false;
		}
		
		Guidance guidance = new Guidance(configuration, evoOp, guidanceId, behaviorPreserving);
		
		return guidance;
	}
	
	public static Guidance checkExtractFeatureGuidance(Configuration configuration, ExtractFeature evoOp) {
		String guidanceId = "";
		boolean behaviorPreserving = false;
		
		if(!configuration.getSelectedFeatures().contains(evoOp.getSourceFeature())) {
			guidanceId = "E0";
			behaviorPreserving = true;
		}
		else {
			guidanceId = "E1";
			behaviorPreserving = true;
		}
		
		Guidance guidance = new Guidance(configuration, evoOp, guidanceId, behaviorPreserving);
		
		return guidance;
	}
	
}
