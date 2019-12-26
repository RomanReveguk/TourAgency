package travelagency.modelsdata;

public class HotelData {
	private int city_id;
	private String hotel_name;
	private String room;
	private int places;
	private String hotel_class;
	
	public int getCity_id() {
		return city_id;
	}
	public void setCity_id(int city_id) {
		this.city_id = city_id;
	}
	public String getHotel_name() {
		return hotel_name;
	}
	public void setHotel_name(String hotel_name) {
		this.hotel_name = hotel_name;
	}
	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
	}
	public int getPlaces() {
		return places;
	}
	public void setPlaces(int places) {
		this.places = places;
	}
	public String getHotel_class() {
		return hotel_class;
	}
	public void setHotel_class(String hotel_class) {
		this.hotel_class = hotel_class;
	}
	@Override
	public String toString() {
		return "HotelData [city_id=" + city_id + ", hotel_name=" + hotel_name + ", room=" + room + ", places=" + places
				+ ", hotel_class=" + hotel_class + "]";
	}
}
