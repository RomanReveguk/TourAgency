package travelagency.modelsdata;

public class CityData {
	private String city_name;
	private int city_id;
	private int country_id;
	public String getCity_name() {
		return city_name;
	}
	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}
	public int getCity_id() {
		return city_id;
	}
	public void setCity_id(int city_id) {
		this.city_id = city_id;
	}
	public int getCountry_id() {
		return country_id;
	}
	public void setCountry_id(int country_id) {
		this.country_id = country_id;
	}
	@Override
	public String toString() {
		return "CityData [city_name=" + city_name + ", city_id=" + city_id + ", country_id=" + country_id + "]";
	}
	
	
}
