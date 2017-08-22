package guydance.evaluation.quantitatively.product_engineers_perspective;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import guydance.evaluation.quantitatively.product_engineers_perspective.evolution_operations.EvolutionOperation;
import guydance.evaluation.quantitatively.product_engineers_perspective.evolution_operations.ExtractFeature;
import guydance.evaluation.quantitatively.product_engineers_perspective.evolution_operations.MergeFeatures;
import guydance.evaluation.quantitatively.product_engineers_perspective.evolution_operations.RemoveFeatureWithArtifacts;

public class GuidanceFileWriter {

	public void writeGuidanceToCSV(List<String> featureModelList, List<Configuration> configurations, List<EvolutionOperation> evolutionOperations, List<Guidance> guidanceList, File file) {
		try{

		    PrintWriter writer = new PrintWriter(file, "UTF-8");
		    writer.println("ConfigurationId;EvolutionOperation(delete-merge-extract);GuidanceIdFromPaper;PreservesBehavior");
		    
			for(Guidance guidance: guidanceList) {
				StringBuilder lineBuilder = new StringBuilder();
				lineBuilder.append(guidance.getConfiguration().getConfigurationId());
				lineBuilder.append(";");
				
				EvolutionOperation evoOp = guidance.getEvolutionOperation();
				String evoOpString = "";
				if(evoOp instanceof MergeFeatures) {
					evoOpString = "merge";
				}
				else if(evoOp instanceof ExtractFeature) {
					evoOpString = "extract";
				}
				else if(evoOp instanceof RemoveFeatureWithArtifacts) {
					evoOpString = "delete";
				}
				lineBuilder.append(evoOpString);
				lineBuilder.append(";");
				
				lineBuilder.append(guidance.getGuidanceIdFromPaper());
				lineBuilder.append(";");
				
				lineBuilder.append(guidance.isBehaviorPreserving());
				
				writer.println(lineBuilder.toString());
			}
		    
		    writer.close();
		} catch (IOException e) {
		   // do something
		}
	}
	
	public void writeShortGuidanceResultsToCSV(List<String> featureList, List<Configuration> configurations, List<EvolutionOperation> evolutionOperations, List<Guidance> guidanceList, File file) {
		
		long amountOfBehaviorPreservationOverall = 0;
		long amountOfBehaviorPreservationForMerge = 0;
		long amountOfBehaviorPreservationForDelete = 0;
		long amountOfBehaviorPreservationForExtract = 0;
		
		long amountOfD0 = 0;
		long amountOfD1 = 0;
		
		long amountOfM0 = 0;
		long amountOfM1 = 0;
		long amountOfM2 = 0;
		long amountOfM3 = 0;
		
		long amountOfE0 = 0;
		long amountOfE1 = 0;
		
		for(Guidance guidance: guidanceList) {
			if(guidance.isBehaviorPreserving()) {
				amountOfBehaviorPreservationOverall++;
			}
			
			EvolutionOperation evoOp = guidance.getEvolutionOperation();
			
			if(evoOp instanceof RemoveFeatureWithArtifacts) {
				if(guidance.isBehaviorPreserving()) {
					amountOfBehaviorPreservationForDelete++;
				}
				
				if(guidance.getGuidanceIdFromPaper().equals("D0")) {
					amountOfD0++;
				}
				else if(guidance.getGuidanceIdFromPaper().equals("D1")) {
					amountOfD1++;
				}
			}
			else if(evoOp instanceof MergeFeatures) {
				if(guidance.isBehaviorPreserving()) {
					amountOfBehaviorPreservationForMerge++;
				}
				
				if(guidance.getGuidanceIdFromPaper().equals("M0")) {
					amountOfM0++;
				}
				else if(guidance.getGuidanceIdFromPaper().equals("M1")) {
					amountOfM1++;
				}
				else if(guidance.getGuidanceIdFromPaper().equals("M2")) {
					amountOfM2++;
				}
				else if(guidance.getGuidanceIdFromPaper().equals("M3")) {
					amountOfM3++;
				}
				
			}
			else if(evoOp instanceof ExtractFeature) {
				if(guidance.isBehaviorPreserving()) {
					amountOfBehaviorPreservationForExtract++;
				}
				
				if(guidance.getGuidanceIdFromPaper().equals("E0")) {
					amountOfE0++;
				}
				else if(guidance.getGuidanceIdFromPaper().equals("E1")) {
					amountOfE1++;
				}
			}
		}
		
		try{

		    PrintWriter writer = new PrintWriter(file, "UTF-8");
		    
			StringBuilder lineBuilder = new StringBuilder();
			lineBuilder.append("AmountOfFeatures;");
			lineBuilder.append(featureList.size());
			writer.println(lineBuilder.toString());
		    
		    
			lineBuilder = new StringBuilder();
			lineBuilder.append("AmountOfConfigurations;");
			lineBuilder.append(configurations.size());
			writer.println(lineBuilder.toString());
		    
		    
			lineBuilder = new StringBuilder();
			lineBuilder.append("AmountOfEvolutionOperations;");
			lineBuilder.append(evolutionOperations.size());
			writer.println(lineBuilder.toString());
		    
		    
			lineBuilder = new StringBuilder();
			lineBuilder.append("AmountOfGuidances;");
			lineBuilder.append(guidanceList.size());
			writer.println(lineBuilder.toString());
		    
		    
			lineBuilder = new StringBuilder();
			lineBuilder.append("AmountOfBehaviorPreservationOverall;");
			lineBuilder.append(amountOfBehaviorPreservationOverall);
			writer.println(lineBuilder.toString());
		    
		    
			lineBuilder = new StringBuilder();
			lineBuilder.append("AmountOfBehaviorPreservationDelete;");
			lineBuilder.append(amountOfBehaviorPreservationForDelete);
			writer.println(lineBuilder.toString());
		    
		    
			lineBuilder = new StringBuilder();
			lineBuilder.append("AmountOfBehaviorPreservationMerge;");
			lineBuilder.append(amountOfBehaviorPreservationForMerge);
			writer.println(lineBuilder.toString());
		    
		    
			lineBuilder = new StringBuilder();
			lineBuilder.append("AmountOfBehaviorPreservationExtract;");
			lineBuilder.append(amountOfBehaviorPreservationForExtract);
			writer.println(lineBuilder.toString());
		    
		    
			lineBuilder = new StringBuilder();
			lineBuilder.append("AmountOfConfigurationsIn D0;");
			lineBuilder.append(amountOfD0);
			writer.println(lineBuilder.toString());
		    
		    
			lineBuilder = new StringBuilder();
			lineBuilder.append("AmountOfConfigurationsIn D1;");
			lineBuilder.append(amountOfD1);
			writer.println(lineBuilder.toString());
		    
		    
			lineBuilder = new StringBuilder();
			lineBuilder.append("AmountOfConfigurationsIn M0;");
			lineBuilder.append(amountOfM0);
			writer.println(lineBuilder.toString());
		    
		    
			lineBuilder = new StringBuilder();
			lineBuilder.append("AmountOfConfigurationsIn M1;");
			lineBuilder.append(amountOfM1);
			writer.println(lineBuilder.toString());
		    
		    
			lineBuilder = new StringBuilder();
			lineBuilder.append("AmountOfConfigurationsIn M2;");
			lineBuilder.append(amountOfM2);
			writer.println(lineBuilder.toString());
		    
		    
			lineBuilder = new StringBuilder();
			lineBuilder.append("AmountOfConfigurationsIn M3;");
			lineBuilder.append(amountOfM3);
			writer.println(lineBuilder.toString());
		    
		    
			lineBuilder = new StringBuilder();
			lineBuilder.append("AmountOfConfigurationsIn E0;");
			lineBuilder.append(amountOfE0);
			writer.println(lineBuilder.toString());
		    
		    
			lineBuilder = new StringBuilder();
			lineBuilder.append("AmountOfConfigurationsIn E1;");
			lineBuilder.append(amountOfE1);
			writer.println(lineBuilder.toString());
				
		    
		    writer.close();
		} catch (IOException e) {
		   // do something
		}
	}
	
}
