package guydance.evaluation.quantitatively.product_engineers_perspective;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import guydance.evaluation.quantitatively.product_engineers_perspective.evolution_operations.EvolutionOperation;
import guydance.evaluation.quantitatively.product_engineers_perspective.evolution_operations.ExtractFeature;
import guydance.evaluation.quantitatively.product_engineers_perspective.evolution_operations.MergeFeatures;
import guydance.evaluation.quantitatively.product_engineers_perspective.evolution_operations.RemoveFeatureWithArtifacts;

public class QuantitativeEvaluation {
	
	public static List<Configuration> readConfigurations(String basePath) {
		List<Configuration> configurations = new ArrayList<Configuration>(1000);
		
		for(int i=1;i<=1000;i++) {
			String configurationFileString = ""+i;
			
			while(configurationFileString.length() < 5) {
				configurationFileString = "0"+configurationFileString;
			}
			
			configurationFileString = configurationFileString+".config";
			
			File configurationFile = new File(basePath+configurationFileString);
			configurations.add(readConfiguration(configurationFile, i));
		}
		
		return configurations;
	}
	
	public static void performEvaluation(String basePath, List<EvolutionOperation> evolutionOperations) {
		File featureListFile = new File(basePath+"before/out.features");
		List<String> featureList = readFeatureList(featureListFile);
		List<Configuration> configurations = readConfigurations(basePath+"before/products/");

		List<Guidance> guidance = GuidanceChecker.checkGuidance(configurations, evolutionOperations);
		
		File resultsFile = new File(basePath+"results.csv");
		File resultsFileShort = new File(basePath+"results_short.csv");
		
		GuidanceFileWriter guidanceFileWriter = new GuidanceFileWriter();
		guidanceFileWriter.writeGuidanceToCSV(featureList, configurations, evolutionOperations, guidance, resultsFile);
		guidanceFileWriter.writeShortGuidanceResultsToCSV(featureList, configurations, evolutionOperations, guidance, resultsFileShort);
	}
	
	public static void main(String[] args) {
//		String basePathDeleteFeature7da62cb185 = "commits/remove/commit7da62cb185/";
//		RemoveFeatureWithArtifacts deleteFeature7da62cb185Operation = new RemoveFeatureWithArtifacts("I2C_NUC900");
//		List<EvolutionOperation> deleteFeature7da62cb185Operations = new ArrayList<EvolutionOperation>(1);
//		deleteFeature7da62cb185Operations.add(deleteFeature7da62cb185Operation);
//		
//		performEvaluation(basePathDeleteFeature7da62cb185, deleteFeature7da62cb185Operations);
		
		
//		String basePathDeleteFeaturefb5a515704 = "commits/remove/commitfb5a515704/";
//		List<EvolutionOperation> deleteFeaturefb5a515704Operations = new ArrayList<EvolutionOperation>(1);
//		deleteFeaturefb5a515704Operations.add(new RemoveFeatureWithArtifacts("PPC_EARLY_DEBUG_WSP"));
//		deleteFeaturefb5a515704Operations.add(new RemoveFeatureWithArtifacts("PPC_WSP"));
//		deleteFeaturefb5a515704Operations.add(new RemoveFeatureWithArtifacts("PPC_PSR2"));
//		deleteFeaturefb5a515704Operations.add(new RemoveFeatureWithArtifacts("PPC_CHROMA"));
//		deleteFeaturefb5a515704Operations.add(new RemoveFeatureWithArtifacts("PPC_A2"));
//		
//		performEvaluation(basePathDeleteFeaturefb5a515704, deleteFeaturefb5a515704Operations);
		
//		String basePathExtractFeature995187bed3 = "commits/extract/commit995187bed3/";
//		List<EvolutionOperation> extractFeature995187bed3Operations = new ArrayList<EvolutionOperation>(1);
//		extractFeature995187bed3Operations.add(new ExtractFeature("IR_NEC_DECODER"));
//		
//		performEvaluation(basePathExtractFeature995187bed3, extractFeature995187bed3Operations);
		
//		String basePathExtractFeaturef1d7dbbe = "commits/extract/commitf1d7dbbe/";
//		List<EvolutionOperation> extractFeaturef1d7dbbeOperations = new ArrayList<EvolutionOperation>(3);
//		extractFeaturef1d7dbbeOperations.add(new ExtractFeature("COMEDI_PCMCIA_DRIVERS"));
//		extractFeaturef1d7dbbeOperations.add(new ExtractFeature("COMEDI_PCI_DRIVERS"));
//		extractFeaturef1d7dbbeOperations.add(new ExtractFeature("COMEDI"));
//		
//		performEvaluation(basePathExtractFeaturef1d7dbbe, extractFeaturef1d7dbbeOperations);
		
		String basePathMergeFeatures8254baccdd = "commits/merge/commit8254baccdd/";
		List<EvolutionOperation> mergeFeatures8254baccddOperations = new ArrayList<EvolutionOperation>(1);
		mergeFeatures8254baccddOperations.add(new MergeFeatures("USB_U_MS", "USB_F_MASS_STORAGE"));
		
		performEvaluation(basePathMergeFeatures8254baccdd, mergeFeatures8254baccddOperations);
		
		String basePathMergeFeatures9c2b85f4f9 = "commits/merge/commit9c2b85f4f9/";
		List<EvolutionOperation> mergeFeatures9c2b85f4f9Operations = new ArrayList<EvolutionOperation>(1);
		mergeFeatures9c2b85f4f9Operations.add(new MergeFeatures("USB_U_RNDIS", "USB_F_RNDIS"));
		
		performEvaluation(basePathMergeFeatures9c2b85f4f9, mergeFeatures9c2b85f4f9Operations);
	}
	
	
	private static List<String> readFeatureList(File file) {
		List<String> featureList = new ArrayList<String>();
		
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
		    String line = br.readLine();

		    while (line != null) {
		        featureList.add(line.substring(7));
		        line = br.readLine();
		    }
		    
//		    String everything = sb.toString();
		    
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		    try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return featureList;
	}
	
	
	private static Configuration readConfiguration(File file, int configurationId) {	
		Configuration configuration = new Configuration();
    	configuration.setConfigurationId(configurationId);
    	configuration.setSelectedFeatures(new ArrayList<String>());

		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
		    String line = br.readLine();

		    while (line != null) {
		        configuration.getSelectedFeatures().add(line);

		        line = br.readLine();
		    }
		    
//		    String everything = sb.toString();
		    
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		    try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return configuration;
	}
}
