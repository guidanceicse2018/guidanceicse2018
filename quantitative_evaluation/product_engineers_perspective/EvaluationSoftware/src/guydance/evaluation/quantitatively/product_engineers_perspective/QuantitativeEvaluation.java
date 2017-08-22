package guydance.evaluation.quantitatively.product_engineers_perspective;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import guydance.evaluation.quantitatively.product_engineers_perspective.evolution_operations.EvolutionOperation;

public class QuantitativeEvaluation {
	
	private static final long ARGI_SEED = 2345667;
	private static final long ERP_SEED = 4568947;

	public static void main(String[] args) {
		// Agrib Product Line
		File agribFeatureListFile = new File("Agri/AgribFeatureList.txt");
		List<String> agribFeatureList = readFeatureList(agribFeatureListFile);
		File agribConfigurationFile = new File("Agri/Config.csv");
		List<Configuration> agribConfigurations = readConfigurations(agribConfigurationFile);
		
		RandomEvolutionGenerator randomEvolutionGenerator = new RandomEvolutionGenerator(ARGI_SEED);
		List<EvolutionOperation> evolutionOperations = randomEvolutionGenerator.generateRandomEvolution(agribFeatureList, 300, true);
		List<Guidance> agribGuidance = GuidanceChecker.checkGuidance(agribConfigurations, evolutionOperations);
		
		File agribResultsFile = new File("Agri/agri_results.csv");
		File agribResultsFileShort = new File("Agri/agri_results_short.csv");
		GuidanceFileWriter guidanceFileWriter = new GuidanceFileWriter();
		guidanceFileWriter.writeGuidanceToCSV(agribFeatureList, agribConfigurations, evolutionOperations, agribGuidance, agribResultsFile);
		guidanceFileWriter.writeShortGuidanceResultsToCSV(agribFeatureList, agribConfigurations, evolutionOperations, agribGuidance, agribResultsFileShort);
		
		
		// ERP Product Line
		File erpFeatureListFile = new File("ERP/ERPFeatureList.txt");
		List<String> erpFeatureList = readFeatureList(erpFeatureListFile);
		File erpConfigurationFile = new File("ERP/Config.csv");
		List<Configuration> erpConfigurations = readConfigurations(erpConfigurationFile);

		RandomEvolutionGenerator randomEvolutionGeneratorERP = new RandomEvolutionGenerator(ERP_SEED);
		List<EvolutionOperation> evolutionOperationsERP = randomEvolutionGeneratorERP.generateRandomEvolution(erpFeatureList, 300, true);
		List<Guidance> erpGuidance = GuidanceChecker.checkGuidance(erpConfigurations, evolutionOperationsERP);
		
		File erpResultsFile = new File("ERP/erp_results.csv");
		File erpResultsFileShort = new File("ERP/erp_results_short.csv");
		GuidanceFileWriter guidanceFileWriterERP = new GuidanceFileWriter();
		guidanceFileWriterERP.writeGuidanceToCSV(erpFeatureList, erpConfigurations, evolutionOperationsERP, erpGuidance, erpResultsFile);
		guidanceFileWriterERP.writeShortGuidanceResultsToCSV(erpFeatureList, erpConfigurations, evolutionOperationsERP, erpGuidance, erpResultsFileShort);
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
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();

		    while (line != null) {
		        sb.append(line);
		        sb.append(System.lineSeparator());
		        line = br.readLine();
		        featureList.add(line);
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
	
	
	private static List<Configuration> readConfigurations(File file) {	
		Map<Integer, Configuration> configurationMap = new HashMap<Integer, Configuration>();
		

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
		        
		        String[] entry = line.split(";");
				
		        Integer configurationId = Integer.parseInt(entry[0]);
		        
		        Configuration configuration = configurationMap.get(configurationId);
		        
		        if(configuration == null) {
		        	configuration = new Configuration();
		        	configuration.setConfigurationId(configurationId);
		        	configuration.setSelectedFeatures(new ArrayList<String>());
		        	configurationMap.put(configurationId, configuration);
		        }
		        
		        configuration.getSelectedFeatures().add(entry[1]);

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
		
		return new ArrayList<Configuration>(configurationMap.values());
	}
}
