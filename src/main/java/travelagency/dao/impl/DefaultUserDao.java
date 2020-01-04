package travelagency.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import travelagency.dao.UserDao;
import travelagency.dbutils.DbHelper;
import travelagency.modelsdata.CityData;
import travelagency.modelsdata.CountryData;
import travelagency.modelsdata.HotelData;
import travelagency.modelsdata.TourData;
import travelagency.modelsdata.UserData;

public class DefaultUserDao implements UserDao {
	// Запрос выводит все поля о пользователе по опред.айди
	private static final String SELECT_USER = "SELECT * FROM users WHERE user_id = ?";
	// Запрос выводит пароль пользователя по емейлу 
	private static final String SELECT_PASSWORD = "SELECT password FROM users WHERE mail = ?";
	// Запрос выводи все туры пользователя по опред.айди пользователя
	private static final String GET_TOURS_FOR_USER = "SELECT t.tour_id, t.tour_name, t.price, t.description, t.date_start, t.date_end, t.country_id2, t.meals, t.tour_operator FROM tours t \r\n"
			+ "JOIN orders o ON t.tour_id = o.tour_id2\r\n" + "WHERE o.user_id2= ?;";
	// Запрос выводи все поля о hotels по опред.айди city_id
	private static final String SELECT_HOTEL = "SELECT * FROM hotels WHERE city_id = ?";
	// Запрос выводи все поля о hotels по опред.айди city_id
	private static final String SELECT_COUNTRY = "SELECT * FROM countries WHERE country_id = ?";
	// Запрос выводи все туры пользователя по опред.айди пользователя
		private static final String GET_CITY_FOR_COUNTRY = "SELECT cities.city_id, cities.city_name, cities.country_id FROM cities \r\n"
				+ "JOIN countries ON cities.country_id= countries.country_id\r\n" + "WHERE countries.country_id= ?;";
		
		public static final String SELECT_ALL_USER = "SELECT * FROM users";
		
		public static final String UPDATE_USER = "UPDATE users SET user_name = ?, user_surname =?,"
				+ "user_date_of_birth=?, mail=?, password =?,roleId=? WHERE user_id = ?";
		private static final String INSERT_USER = "INSERT INTO users (user_name, user_surname,"
				+ "user_date_of_birth, mail, password, roleId)"
				+ "VALUES (?, ?, ?, ?, ?, ?);";
		public static final String SELECT_CLIENT = "SELECT u.user_id, u.user_name, u.user_surname "
				+ "FROM users u \r\n" + 
				"JOIN roles r ON r.role_id=u.roleId WHERE r.role_id = 1;";
		
		public static final String DELETE_USER = "DELETE FROM users WHERE user_id =?";

	public static final String URL = "jdbc:mysql://127.0.0.1:3306/touragency";
	private static final String PARAMS = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2B8";
	public static final String USER = "root";
	public static final String PASSWORD = "root";

	private static DefaultUserDao instance;
	private DbHelper dbHelper;

	public DefaultUserDao() {
		dbHelper = DbHelper.getInstance();
	}

	public synchronized static UserDao getInstance() {
		if (instance == null) {
			instance = new DefaultUserDao();
		}
		return instance;
	}

	// ====================================Получаем все поля о пользователе по его айди=================================
	@Override
	public UserData getUserById(int user_id) {
		try {
			UserData userData = null;

			Class.forName("com.mysql.cj.jdbc.Driver");
			try (Connection conn = DriverManager.getConnection(URL + PARAMS, USER, PASSWORD);
					PreparedStatement statement = conn.prepareStatement(SELECT_USER);) {

				statement.setInt(1, user_id);
				try (ResultSet rs = statement.executeQuery();) {
					if (rs.next()) {
						userData = new UserData();
						userData.setUser_id(rs.getInt("user_id"));
						userData.setUser_name(rs.getString("user_name"));
						userData.setUser_surname(rs.getString("user_surname"));
						userData.setUser_date_of_birth(rs.getString("user_date_of_birth"));
						userData.setMail(rs.getString("mail"));
						userData.setRoleId(rs.getInt("roleId"));
					}
				}
			}
			return userData;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public UserData getUserPassword(String mail) {
		try {
			UserData userData = null;

			Class.forName("com.mysql.cj.jdbc.Driver");
			try (Connection conn = DriverManager.getConnection(URL + PARAMS, USER, PASSWORD);
					PreparedStatement statement = conn.prepareStatement(SELECT_PASSWORD);) {

				statement.setString(1, mail);
				try (ResultSet rs = statement.executeQuery();) {
					if (rs.next()) {
						userData = new UserData();
						userData.setPassword(rs.getString("password"));
					}
				}
			}
			return userData;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}
	

	// ====================================Получаем Туры по айди пользователя=================================
	@Override
	public List<TourData> getToursForUser(int user_id) {
		List<TourData> tours = new ArrayList<TourData>();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			try (Connection conn = dbHelper.getConnection();
					PreparedStatement ps = conn.prepareStatement(GET_TOURS_FOR_USER)) {

				ps.setInt(1, user_id);

				try (ResultSet rs = ps.executeQuery()) {
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

				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return tours;
	}
	
	// ====================================Получаем айди и назв.страны по айди country_id=================================
	@Override
	public CountryData getCountryById(int country_id) {
		try {
			CountryData countryData = null;
			Class.forName("com.mysql.cj.jdbc.Driver");
			try (Connection conn = dbHelper.getConnection();
					PreparedStatement ps = conn.prepareStatement(SELECT_COUNTRY)) {

				ps.setInt(1, country_id);

				try (ResultSet rs = ps.executeQuery()) {
					if (rs.next()) {
						countryData = new CountryData();
						countryData.setCountry_id(rs.getInt("country_id"));
						countryData.setCountry_name(rs.getString("country_name"));
					}
				}
			}
			return countryData;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	// ====================================Получаем города по country_id=================================
	@Override
	public List<CityData> getCityForCountry(int country_id) {
		List<CityData> cities = new ArrayList<CityData>();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			try (Connection conn = dbHelper.getConnection();
					PreparedStatement ps = conn.prepareStatement(GET_CITY_FOR_COUNTRY)) {

				ps.setInt(1, country_id);

				try (ResultSet rs = ps.executeQuery()) {
					while (rs.next()) {
						CityData cityData = new CityData();
						cityData.setCountry_id(rs.getInt("country_id"));
						cityData.setCity_id(rs.getInt("city_id"));
						cityData.setCity_name(rs.getString("city_name"));
						
						cities.add(cityData);
					}

				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return cities;
		
	}
	
	// Список всех пользователей (имя и фамилия)
		@Override
		public List<UserData> getAllUsers() {
			List<UserData> users = new ArrayList<>();
			try (Connection conn = dbHelper.getConnection();
					PreparedStatement statement = conn.prepareStatement(SELECT_ALL_USER)) {

				ResultSet rs = statement.executeQuery();
				while (rs.next()) {
					UserData userData = new UserData();
					userData.setUser_id(rs.getInt("user_id"));
					userData.setUser_name(rs.getString("user_name"));
					userData.setUser_surname(rs.getString("user_surname"));
					userData.setUser_date_of_birth(rs.getString("user_date_of_birth"));
					userData.setMail(rs.getString("mail"));
					userData.setRoleId(rs.getInt("roleId"));
					users.add(userData);
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			return users;
		}

		// Обновление данных пользователя
		@Override
		public boolean updateUser(int user_id, String user_name, String user_surname, String user_date_of_birth, 
				String mail, String password, int roleId) {
			try (Connection conn = dbHelper.getConnection();
					PreparedStatement statement = conn.prepareStatement(UPDATE_USER)) {
				statement.setInt(7, user_id);
				statement.setString(1, user_name);
				statement.setString(2, user_surname);
				statement.setString(3, user_date_of_birth);
				statement.setString(4, mail);
				statement.setString(5, password);
				statement.setInt(6, roleId);
				statement.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			return true;
		}
		
		// Обновление данных пользователя
		@Override
		public boolean updateUser2(UserData userData) {
			try (Connection conn = dbHelper.getConnection();
					PreparedStatement statement = conn.prepareStatement(UPDATE_USER)) {
				statement.setInt(7, userData.getUser_id());
				statement.setString(1, userData.getUser_name());
				statement.setString(2, userData.getUser_surname());
				statement.setString(3, userData.getUser_date_of_birth());
				statement.setString(4, userData.getMail());
				statement.setString(5, userData.getPassword());
				statement.setInt(6, userData.getRoleId());
				statement.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			return true;
		}
		
		//Добавление пользователя
		@Override
		public boolean setUser(String user_name, String user_surname, String user_date_of_birth, 
				String mail, String password, int roleId) {
			try (Connection conn = dbHelper.getConnection();
					PreparedStatement statement = conn.prepareStatement(INSERT_USER)) {
				statement.setString(1, user_name);
				statement.setString(2, user_surname);
				statement.setString(3, user_date_of_birth);
				statement.setString(4, mail);
				statement.setString(5, password);
				statement.setInt(6, roleId);
				statement.executeUpdate();
				
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			return true;
		}
		
		//Добавление пользователя
				@Override
				public boolean setUser2(UserData userData) {
					try (Connection conn = dbHelper.getConnection();
							PreparedStatement statement = conn.prepareStatement(INSERT_USER)) {
						statement.setString(1, userData.getUser_name());
						statement.setString(2, userData.getUser_surname());
						statement.setString(3, userData.getUser_date_of_birth());
						statement.setString(4, userData.getMail());
						statement.setString(5, userData.getPassword());
						statement.setInt(6, userData.getRoleId());
						statement.executeUpdate();
					} catch (Exception e) {
						e.printStackTrace();
						return false;
					}
					return true;
				}

		//Список клиентов
		@Override
		public List<UserData> getClient() {
			List<UserData> users = new ArrayList<>();
			try (Connection conn = dbHelper.getConnection();
					PreparedStatement statement = conn.prepareStatement(SELECT_CLIENT)) {
				ResultSet rs = statement.executeQuery();
				while (rs.next()) {
					UserData userData = new UserData();
					userData.setUser_id(rs.getInt("user_id"));
					userData.setUser_name(rs.getString("user_name"));
					userData.setUser_surname(rs.getString("user_surname"));
					users.add(userData);
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			return users;
		}

		@Override
		public boolean deleteUserById(int user_id) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				try (Connection conn = DriverManager.getConnection(URL + PARAMS, USER, PASSWORD);
						PreparedStatement statement = conn.prepareStatement(DELETE_USER);) {
					statement.setInt(1, user_id);
				}
				return true;
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
}
