package travelagency.modelsdata;

public class TourData {
	
	private int tour_id;
	private String tour_name;
	private String description;
	private double price;
	private String date_start;
	private String date_end;
	private int country_id2;
	private String meals;
	private String tour_operator;
	
	public int getTour_id() {
		return tour_id;
	}
	public void setTour_id(int tour_id) {
		this.tour_id = tour_id;
	}
	public String getTour_name() {
		return tour_name;
	}
	public void setTour_name(String tour_name) {
		this.tour_name = tour_name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getDate_start() {
		return date_start;
	}
	public void setDate_start(String date_start) {
		this.date_start = date_start;
	}
	public String getDate_end() {
		return date_end;
	}
	public void setDate_end(String date_end) {
		this.date_end = date_end;
	}
	public int getCountry_id2() {
		return country_id2;
	}
	public void setCountry_id2(int country_id2) {
		this.country_id2 = country_id2;
	}
	public String getMeals() {
		return meals;
	}
	public void setMeals(String meals) {
		this.meals = meals;
	}
	public String getTour_operator() {
		return tour_operator;
	}
	public void setTour_operator(String tour_operator) {
		this.tour_operator = tour_operator;
	}
	@Override
	public String toString() {
		return "TourData [tour_id=" + tour_id + ", tour_name=" + tour_name + ", description=" + description + ", price="
				+ price + ", date_start=" + date_start + ", date_end=" + date_end + ", country_id2=" + country_id2
				+ ", meals=" + meals + ", tour_operator=" + tour_operator + "]";
	}

	
}
	