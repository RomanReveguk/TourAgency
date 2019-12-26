package travelagency.dao;

import java.util.List;

import travelagency.modelsdata.CityData;
import travelagency.modelsdata.CountryData;
import travelagency.modelsdata.HotelData;
import travelagency.modelsdata.TourData;
import travelagency.modelsdata.UserData;

public interface UserDao {
	
	UserData getUserById(int user_id);
	List<TourData> getToursForUser(int user_id);
	HotelData getHotelById(int city_id);
	CountryData getCountryById(int country_id);
	List<CityData> getCityForCountry(int country_id);
	
	
	List<UserData> getAllUsers();
	
	boolean updateUser(int user_id, String user_name, String user_surname, String user_date_of_birth, 
			String mail, String password, int roleId);
	boolean setUser( String user_name, String user_surname, String user_date_of_birth, 
			String mail, String password, int roleId);
	List<UserData> getClient();
}

