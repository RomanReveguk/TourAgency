package travelagency.dao;

import java.util.List;

import travelagency.modelsdata.HotelData;

public interface HotelDao {

	HotelData getHotelById(int hotel_id);

	List<HotelData> getHotelsByClass (String hotel_class);

	List<HotelData> getHotelsFromCity(int city_id);

	List<HotelData> getHotelsByRoom(String room);

	List<HotelData> getHotelsByPlaces(int places);

	boolean updateHotel(int hotel_id, int city_id, String hotel_name, String room, int places, String hotel_class);

	boolean setHotel(int city_id, String hotel_name, String room, int places, String hotel_class);

}
