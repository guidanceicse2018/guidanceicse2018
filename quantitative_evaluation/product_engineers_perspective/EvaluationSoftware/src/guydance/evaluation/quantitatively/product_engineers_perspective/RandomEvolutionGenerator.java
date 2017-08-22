package guydance.evaluation.quantitatively.product_engineers_perspective;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import guydance.evaluation.quantitatively.product_engineers_perspective.evolution_operations.EvolutionOperation;
import guydance.evaluation.quantitatively.product_engineers_perspective.evolution_operations.ExtractFeature;
import guydance.evaluation.quantitatively.product_engineers_perspective.evolution_operations.MergeFeatures;
import guydance.evaluation.quantitatively.product_engineers_perspective.evolution_operations.RemoveFeatureWithArtifacts;

public class RandomEvolutionGenerator {
	
	private long seed;
	
	public RandomEvolutionGenerator(long seed) {
		this.seed = seed;
	}
	
	/**
	 * 
	 * @param featureList
	 * @param amountOfOperations
	 * @param equallyDistributedEvolutionOperations if true: (if possible) equal times delete, merge, extract
	 * @return
	 */
	public List<EvolutionOperation> generateRandomEvolution(List<String> featureList, int amountOfOperations, boolean equallyDistributedEvolutionOperations) {
		List<EvolutionOperation> evolutionOperations = new ArrayList<EvolutionOperation>(amountOfOperations);
		
		Random random = new Random(seed);

		if(!equallyDistributedEvolutionOperations) {
			for(int i = 0;i<amountOfOperations;i++) {
				evolutionOperations.add(generateRandomEvolutoinOperation(random, featureList));
			}
		}
		else {
			int additionalOperations = amountOfOperations%3;
			
			for(int i = 0; i<amountOfOperations/3; i++) {
				evolutionOperations.add(generateRemoveFeature(random, featureList));
				evolutionOperations.add(generateMergeFeatures(random, featureList));
				evolutionOperations.add(generateExtractFeature(random, featureList));
			}
			
			for(int i = 0; i<additionalOperations;i++) {
				evolutionOperations.add(generateRandomEvolutoinOperation(random, featureList));
			}
		}
		
		return evolutionOperations;
	}
	
	private EvolutionOperation generateRandomEvolutoinOperation(Random random, List<String> featureSet) {
		int randomOpId = random.nextInt(3);
		
		EvolutionOperation evoOp = null;
		
		switch(randomOpId) {
		case 0:
			evoOp = generateRemoveFeature(random, featureSet);
			break;
		case 1:
			evoOp = generateMergeFeatures(random, featureSet);
			break;
		case 2:
			evoOp = generateExtractFeature(random, featureSet);
			break;
		}
		
		return evoOp;
	}
	
	private ExtractFeature generateExtractFeature(Random random, List<String> featureList) {
		String sourceFeature = getRandomFeature(random, featureList);
		ExtractFeature extractFeature = new ExtractFeature(sourceFeature);
		return extractFeature;
	}
	
	private RemoveFeatureWithArtifacts generateRemoveFeature(Random random, List<String> featureList) {
		String feature = getRandomFeature(random, featureList);
		RemoveFeatureWithArtifacts removeFeatureWithArtifacts = new RemoveFeatureWithArtifacts(feature);
		return removeFeatureWithArtifacts;
	}
	
	private MergeFeatures generateMergeFeatures(Random random, List<String> featureList) {
		String sourceFeature = getRandomFeature(random, featureList);
		String targetFeature = getRandomFeature(random, featureList);
		
		while(sourceFeature.equals(targetFeature)) {
			targetFeature = getRandomFeature(random, featureList);
		}
		
		MergeFeatures mergeFeatures = new MergeFeatures(sourceFeature, targetFeature);
		return mergeFeatures;
	}
	
	private String getRandomFeature(Random random, List<String> featureList) {
		int id = random.nextInt(featureList.size());
		return featureList.get(id);
	}
	
}
