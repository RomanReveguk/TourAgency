package travelagency.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import travelagency.dao.TourDao;
import travelagency.dbutils.DbHelper;
import travelagency.modelsdata.TourData;

public class DefaultTourDao implements TourDao{

	private static final String GET_TOURS_FOR_USER = "SELECT t.tour_id, t.tour_name,t.description"
			+ " FROM tours t \r\n JOIN orders o ON t.tour_id = o.tour_id2\r\n" + 
			"WHERE o.user_id2 = ?";
	public static final String SELECT_ALL_TOURS = "SELECT tour_id, tour_name, description, price, date_start, date_end, country_id2, meals, tour_operator"
			+ "FROM tours";
	private static final String INSERT_TOUR = "INSERT INTO tours (tour_name, description,"
			+ "price, date_start, date_end, country_id2, meals, tour_operator)"
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_TOURS_FROM_CITY = "SELECT t.tour_id, t.tour_name, t.description, t.price, t.date_start, "
			+ "t.date_end,t.country_id2 FROM tours t \r\n" + 
			"JOIN countries con ON con.country_id = t.country_id2\r\n" + 
			"JOIN cities c ON c.country_id = con.country_id\r\n" + 
			"WHERE con.city_id = ?";
	private static final String GET_TOURS_FROM_COUNTRY = "SELECT  t.tour_id, t.tour_name,"
			+ " t.description, t.date_start, t.date_end, t.price, t.country_id2"
			+ " FROM tours t JOIN countries c ON c.country_id = t.country_id2\r\n" + 
			"WHERE c.country_id = ?";
	private static final String SELECT_TOUR_BETWEEN_DATE = "t.tour_id, t.tour_name, t.description, t.price, t.date_start, t.date_end,t.country_id2 \r\n" + 
			"FROM tours t \r\n" + 
			"WHERE t.date_start BETWEEN '?' AND '?'";
	private static final String SELECT_TOUR_BETWEEN_PRICE = "SELECT * FROM tours t \r\n" + 
			"WHERE t.price BETWEEN ? AND ?";
	
	

	private static DefaultTourDao instance;
	private DbHelper dbHelper;

	private DefaultTourDao() {
		dbHelper = DbHelper.getInstance();
	}

	public synchronized static TourDao getInstance() {
		if (instance == null) {
			instance = new DefaultTourDao();
		}
		return instance;
	}
	
	//Список туров определённого пользователя
	@Override
	public List<TourData> getToursForUser(int user_id2) {
		List<TourData> tours = new ArrayList<TourData>();
		try (Connection conn = dbHelper.getConnection();
				PreparedStatement ps = conn.prepareStatement(GET_TOURS_FOR_USER)) {
			ps.setInt(1, user_id2);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				TourData tourData = new TourData();
				tourData.setTour_id(rs.getInt("tour_id"));
				tourData.setTour_name(rs.getString("tour_name"));
				tourData.setDescription(rs.getString("description"));
				tours.add(tourData);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return tours;
	}
	
	//Список всех туров
	@Override
	public List<TourData> getAllTours(){
		List<TourData> tours = new ArrayList<>();
		try (Connection conn = dbHelper.getConnection();
				PreparedStatement statement = conn.prepareStatement(SELECT_ALL_TOURS)) {
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				TourData tourData = new TourData();
				tourData.setTour_id(rs.getInt("tour_id"));
				tourData.setTour_name(rs.getString("tour_name"));
				tourData.setDescription(rs.getString("description"));
				tourData.setPrice(rs.getDouble("price"));
				tourData.setDate_start(rs.getString("date_start"));
				tourData.setDate_end(rs.getString("date_end"));
				tourData.setCountry_id2(rs.getInt("country_id2"));
				tourData.setMeals(rs.getString("meals"));
				tourData.setTour_operator(rs.getString("tour_operator"));
				tours.add(tourData);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return tours;
	}
	
	//Добавление тура
		@Override
		public boolean setTour(String tour_name, String description,int price, String date_start, String date_end,
				int country_id2, String meals, String tour_operator) {
			try (Connection conn = dbHelper.getConnection();
					PreparedStatement statement = conn.prepareStatement(INSERT_TOUR)) {
				statement.setString(1, tour_name);
				statement.setString(2, description);
				statement.setDouble(3, price);
				statement.setString(4, date_start);
				statement.setString(5, date_end);
				statement.setInt(6, country_id2);
				statement.setString(7, meals);
				statement.setString(8, tour_operator);
				statement.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			return true;
		}

    //Список туров из определёного города
	@Override
	public List<TourData> getToursFromCity(int city_id) {
		List<TourData> tours = new ArrayList<>();
		try (Connection conn = dbHelper.getConnection();
				PreparedStatement statement = conn.prepareStatement(GET_TOURS_FROM_CITY)) {
			statement.setInt(1, city_id);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				TourData tourData = new TourData();
				tourData.setTour_id(rs.getInt("tour_id"));
				tourData.setTour_name(rs.getString("tour_name"));
				tourData.setDescription(rs.getString("description"));
				tourData.setPrice(rs.getDouble("price"));
				tourData.setDate_start(rs.getString("date_start"));
				tourData.setDate_end(rs.getString("date_end"));
				tourData.setCountry_id2(rs.getInt("country_id2"));
				tours.add(tourData);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return tours;
	}
	
	//Список туров из определёной страны
	@Override
	public List<TourData> getToursFromCountry(int country_id2) {
		List<TourData> tours = new ArrayList<>();
		try (Connection conn = dbHelper.getConnection();
				PreparedStatement statement = conn.prepareStatement(GET_TOURS_FROM_COUNTRY)) {
			statement.setInt(1, country_id2);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				TourData tourData = new TourData();
				tourData.setTour_id(rs.getInt("tour_id"));
				tourData.setTour_name(rs.getString("tour_name"));
				tourData.setDescription(rs.getString("description"));
				tourData.setPrice(rs.getDouble("price"));
				tourData.setDate_start(rs.getString("date_start"));
				tourData.setDate_end(rs.getString("date_end"));
				tourData.setCountry_id2(rs.getInt("country_id2"));
				tours.add(tourData);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return tours;
	}

	@Override
	public List<TourData> getToursBetweenDate(String date_start, String date_end) {
		List<TourData> tours = new ArrayList<>();
		try (Connection conn = dbHelper.getConnection();
				PreparedStatement statement = conn.prepareStatement(SELECT_TOUR_BETWEEN_DATE)) {
			statement.setString(1, date_start);
			statement.setString(2, date_end);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				TourData tourData = new TourData();
				tourData.setTour_id(rs.getInt("tour_id"));
				tourData.setTour_name(rs.getString("tour_name"));
				tourData.setDescription(rs.getString("description"));
				tourData.setPrice(rs.getDouble("price"));
				tourData.setDate_start(rs.getString("date_start"));
				tourData.setDate_end(rs.getString("date_end"));
				tourData.setCountry_id2(rs.getInt("country_id2"));
				tours.add(tourData);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return tours;
	}

	//Список туров по диапазону прайса
	@Override
	public List<TourData> getToursBetweenPrice(int start_price, int end_price) {
		List<TourData> tours = new ArrayList<>();
		try (Connection conn = dbHelper.getConnection();
				PreparedStatement statement = conn.prepareStatement(SELECT_TOUR_BETWEEN_PRICE)) {
			statement.setInt(1, start_price);
			statement.setInt(2, end_price);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				TourData tourData = new TourData();
				tourData.setTour_id(rs.getInt("tour_id"));
				tourData.setTour_name(rs.getString("tour_name"));
				tourData.setDescription(rs.getString("description"));
				tourData.setPrice(rs.getDouble("price"));
				tourData.setDate_start(rs.getString("date_start"));
				tourData.setDate_end(rs.getString("date_end"));
				tourData.setCountry_id2(rs.getInt("country_id2"));
				tours.add(tourData);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return tours;
	}
}
