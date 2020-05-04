package laptopClassFiles;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Laptop")
public class LaptopBag {
	
	private String BrandName;
	private String LaptopName;
	private Features Features;
	private String Id;
	
	@XmlElement(name="BrandName")
	public String getBrandName() {
		return BrandName;
	}
	public void setBrandName(String brandName) {
		BrandName = brandName;
	}
	
	@XmlElement(name="LaptopName")
	public String getLaptopName() {
		return LaptopName;
	}
	public void setLaptopName(String laptopName) {
		LaptopName = laptopName;
	}
	
	@XmlElement(name="Features")
	public Features getFeatures() {
		return Features;
	}
	public void setFeatures(Features features) {
		Features = features;
	}
	
	@XmlElement(name="Id")
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}


}
