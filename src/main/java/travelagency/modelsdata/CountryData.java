package travelagency.modelsdata;

public class CountryData {
	
	private String country_name;
	private int country_id;
	
	public String getCountry_name() {
		return country_name;
	}
	public void setCountry_name(String country_name) {
		this.country_name = country_name;
	}
	public int getCountry_id() {
		return country_id;
	}
	public void setCountry_id(int country_id) {
		this.country_id = country_id;
	}
	
	@Override
	public String toString() {
		return "CountryData [country_name=" + country_name + ", country_id=" + country_id + "]";
	}
	
	
	
	
}
