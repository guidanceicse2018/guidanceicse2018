package guydance.evaluation.quantitatively.product_engineers_perspective;

import java.util.List;

public class Configuration {

	private List<String> selectedFeatures;
	private int configurationId;
	
	
	

	public int getConfigurationId() {
		return configurationId;
	}

	public void setConfigurationId(int configurationId) {
		this.configurationId = configurationId;
	}

	public List<String> getSelectedFeatures() {
		return selectedFeatures;
	}

	public void setSelectedFeatures(List<String> selectedFeatures) {
		this.selectedFeatures = selectedFeatures;
	}
	
	
}
