package laptopClassFiles;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class Features {
	
	

	private List<String> features;
	//private String xss;
	
	/*
	 * public String getXss() { return xss; }
	 * 
	 * public void setXss(String xss) { this.xss = xss; }
	 */
	public Features(){
		features = new ArrayList<String>();
	}
	
	@XmlElement(name="Feature")
	public List<String> getFeatures() {
		return features;
	}

	public void setFeatures(List<String> features) {
		this.features = features;
	}

}
