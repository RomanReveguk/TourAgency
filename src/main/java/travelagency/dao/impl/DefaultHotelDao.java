package travelagency.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import travelagency.dao.HotelDao;
import travelagency.dbutils.DbHelper;
import travelagency.modelsdata.HotelData;

public class DefaultHotelDao implements HotelDao {
	
	// Запрос выводи все поля о hotels по опред.айди hotel_id
		private static final String SELECT_HOTELL = "SELECT hotel_id,hotel_name, room, places, hotel_class, city_id FROM hotels WHERE hotel_id = ?";

		private static final String GET_HOTELS_NAME_BY_ID = "SELECT * FROM hotels WHERE hotel_class = ?";
		private static final String GET_HOTELS_FROM_CITY = "SELECT * FROM hotels WHERE city_id = ?";
		private static final String GET_HOTELS_FROM_ROOM = "SELECT * FROM hotels WHERE room = ?";
		private static final String GET_HOTELS_BY_PLACES= "SELECT  * FROM hotels WHERE places = ?";
		private static final String UPDATE_HOTEL = "UPDATE hotels SET hotel_name = ?, room =?,"
				+ "places=?, hotel_class=?, city_id=? WHERE hotel_id = ?";
		private static final String INSERT_HOTEL = "INSERT INTO hotels (hotel_name, room,"
				+ "places, hotel_class, city_id)"
				+ "VALUES (?, ?, ?, ?, ?);";

		public static final String URL = "jdbc:mysql://127.0.0.1:3306/touragency";
		private static final String PARAMS = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2B8";
		public static final String USER = "root";
		public static final String PASSWORD = "root";

		private static DefaultHotelDao instance;
		private DbHelper dbHelper;

		public DefaultHotelDao() {
			dbHelper = DbHelper.getInstance();
		}

		public synchronized static HotelDao getInstance() {
			if (instance == null) {
				instance = new DefaultHotelDao();
			}
			return instance;
		}

		// ====================================Получаем Отели по айди =================================		
	@Override
	public HotelData getHotelById(int hotel_id) {
		try {
			HotelData hotelData = null;

			Class.forName("com.mysql.cj.jdbc.Driver");
			try (Connection conn = DriverManager.getConnection(URL + PARAMS, USER, PASSWORD);
					PreparedStatement statement = conn.prepareStatement(SELECT_HOTELL);) {

				statement.setInt(1, hotel_id);
				try (ResultSet rs = statement.executeQuery();) {
					if (rs.next()) {
						hotelData = new HotelData();
						hotelData.setHotel_id(rs.getInt("hotel_id"));
						hotelData.setHotel_name(rs.getString("hotel_name"));
						hotelData.setRoom(rs.getString("room"));
						hotelData.setPlaces(rs.getInt("places"));
						hotelData.setHotel_class(rs.getString("hotel_class"));
						hotelData.setCity_id(rs.getInt("city_id"));
					}
				}
			}
			return hotelData;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	// Список отелей по количеству звезд
	@Override
	public List<HotelData> getHotelsByClass (String hotel_class) {
			List<HotelData> hotels = new ArrayList<HotelData>();
			try (Connection conn = dbHelper.getConnection();
					PreparedStatement ps = conn.prepareStatement(GET_HOTELS_NAME_BY_ID)) {
				ps.setString(1, hotel_class);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					HotelData hotelData = new HotelData();
					hotelData.setHotel_id(rs.getInt("hotel_id"));
					hotelData.setHotel_name(rs.getString("hotel_name"));
					hotelData.setRoom(rs.getString("room"));
					hotelData.setPlaces(rs.getInt("places"));
					hotelData.setHotel_class(rs.getString("hotel_class"));
					hotelData.setCity_id(rs.getInt("city_id"));
					hotels.add(hotelData);
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
			return hotels;
		}	

	@Override
	public List<HotelData> getHotelsFromCity(int city_id) {
		List<HotelData> hotels = new ArrayList<HotelData>();
		try (Connection conn = dbHelper.getConnection();
				PreparedStatement ps = conn.prepareStatement(GET_HOTELS_FROM_CITY)) {
			ps.setInt(1, city_id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				HotelData hotelData = new HotelData();
				hotelData.setHotel_id(rs.getInt("hotel_id"));
				hotelData.setHotel_name(rs.getString("hotel_name"));
				hotelData.setRoom(rs.getString("room"));
				hotelData.setPlaces(rs.getInt("places"));
				hotelData.setHotel_class(rs.getString("hotel_class"));
				hotelData.setCity_id(rs.getInt("city_id"));
				hotels.add(hotelData);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return hotels;
	}
	
	@Override
	public List<HotelData> getHotelsByRoom(String room) {
		List<HotelData> hotels = new ArrayList<HotelData>();
		try (Connection conn = dbHelper.getConnection();
				PreparedStatement ps = conn.prepareStatement(GET_HOTELS_FROM_ROOM)) {
			ps.setString(1, room);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				HotelData hotelData = new HotelData();
				hotelData.setHotel_id(rs.getInt("hotel_id"));
				hotelData.setHotel_name(rs.getString("hotel_name"));
				hotelData.setPlaces(rs.getInt("places"));
				hotelData.setHotel_class(rs.getString("hotel_class"));
				hotelData.setCity_id(rs.getInt("city_id"));
				hotelData.setRoom(rs.getString("room"));
				hotels.add(hotelData);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return hotels;
	}

	@Override
	public List<HotelData> getHotelsByPlaces(int places) {
		List<HotelData> hotels = new ArrayList<HotelData>();
		try (Connection conn = dbHelper.getConnection();
				PreparedStatement ps = conn.prepareStatement(GET_HOTELS_BY_PLACES)) {
			ps.setInt(1, places);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				HotelData hotelData = new HotelData();
				hotelData.setHotel_name(rs.getString("hotel_name"));
				hotelData.setRoom(rs.getString("room"));
				hotelData.setHotel_class(rs.getString("hotel_class"));
				hotelData.setHotel_id(rs.getInt("hotel_id"));
				hotelData.setHotel_name(rs.getString("hotel_name"));
				hotelData.setCity_id(rs.getInt("city_id"));
				hotelData.setPlaces(rs.getInt("places"));
				hotels.add(hotelData);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return hotels;
	}

	@Override
	public boolean updateHotel(int hotel_id, int city_id, String hotel_name, String room, int places,
			String hotel_class) {
		try (Connection conn = dbHelper.getConnection();
				PreparedStatement statement = conn.prepareStatement(UPDATE_HOTEL)) {
			statement.setInt(6, hotel_id);
			statement.setString(1, hotel_name);
			statement.setString(2, room);
			statement.setInt(3, places);
			statement.setString(4, hotel_class);
			statement.setInt(5, city_id);
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean setHotel(int city_id, String hotel_name, String room, int places, String hotel_class) {
		try (Connection conn = dbHelper.getConnection();
				PreparedStatement statement = conn.prepareStatement(INSERT_HOTEL)) {
			statement.setString(1, hotel_name);
			statement.setString(2, room);
			statement.setInt(3, places);
			statement.setString(4, hotel_class);
			statement.setInt(5, city_id);
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
