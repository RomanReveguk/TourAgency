package travelagency.dao;

import java.sql.Date;
import java.util.List;

import travelagency.modelsdata.TourData;

public interface TourDao {

	List<TourData> getToursForUser(int user_id);
	List<TourData> getAllTours();
	boolean setTour(String tour_name, String description,int pric, String date_start,String date_end,
			int country_id, String meals, String tour_operator);
	List<TourData> getToursFromCity(int city_id);
	List<TourData> getToursFromCountry(int country_id2);
	List<TourData> getToursBetweenDate(String date_start, String date_end);
	List<TourData> getToursBetweenPrice(int start_price, int end_price);
	
}
