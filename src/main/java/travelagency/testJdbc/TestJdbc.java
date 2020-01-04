package travelagency.testJdbc;

	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.util.List;

import travelagency.dao.HotelDao;
import travelagency.dao.OrderDao;
import travelagency.dao.TourDao;
import travelagency.dao.UserDao;
import travelagency.dao.impl.DefaultHotelDao;
import travelagency.dao.impl.DefaultOrderDao;
import travelagency.dao.impl.DefaultTourDao;
import travelagency.dao.impl.DefaultUserDao;
import travelagency.modelsdata.CityData;
import travelagency.modelsdata.CountryData;
import travelagency.modelsdata.HotelData;
import travelagency.modelsdata.OrderData;
import travelagency.modelsdata.TourData;
import travelagency.modelsdata.UserData;

	public class TestJdbc {
		
// ============ Queries
		private static final String INSERT_USERS = "insert into users(user_id, user_name, user_surname, user_date_of_birth, mail,password,role) values(?,?,?,?,?,?,?)";
		private static final String SELECT_USER = "SELECT * FROM users WHERE user_id = ?";
		
		public static final String URL = "jdbc:mysql://127.0.0.1:3306/touragency";
		private static final String PARAMS = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2B8";
		public static final String USER = "root";
		public static final String PASSWORD = "root";
		
		
		public static void main(String[] args) throws SQLException, ClassNotFoundException {
			
//============================Получаем  имя и фамилию через ЗАПРОС sql ==================================================================
			Class.forName("com.mysql.cj.jdbc.Driver");
			try (Connection conn = DriverManager.getConnection(URL + PARAMS, USER, PASSWORD); 
				PreparedStatement statement = conn.prepareStatement(SELECT_USER);) {
				
				statement.setInt(1, 2);
				try (ResultSet rs = statement.executeQuery();) {
					if (rs.next()) {
						System.out.println(rs.getString("user_name"));
						System.out.println(rs.getString("user_surname"));
						
					}
				}
			}
			System.out.println();
			
//============================Выполняем запрос на INSERT в табл Users ==================================================================			
//			Class.forName("com.mysql.cj.jdbc.Driver");
//			try (Connection conn = DriverManager.getConnection(URL + PARAMS, USER, PASSWORD); 
//
//			PreparedStatement statement = conn.prepareStatement(INSERT_USERS);) {
//				
//			
//				statement.setInt(1, 12);
//				statement.setString(2, "Monika");
//				statement.setString(3, "Fedotova");
//				statement.setString(4, "2001-10.10");
//				statement.setString(5, "fedotova@gmail.com");
//				statement.setString(6, "123");
//				statement.setString(7, "client");
//				statement.executeUpdate();
//			
//			}
			
//=====================Получаем все о пользователе через getUserById(*) =========================================================================
			UserDao userDao = new DefaultUserDao();
			UserData user = userDao.getUserById(2);
			System.out.println(user);
			
			List<TourData> toursForUser = userDao.getToursForUser(2);
			System.out.println(toursForUser);
			
			CountryData countryData = userDao.getCountryById(2);
			System.out.println(countryData);
			
			List<CityData> cityForCountry = userDao.getCityForCountry(14);
			System.out.println(cityForCountry);
			
/////////////////////////////////////////////////////////////////////////////////////////////////
			
			//Вывод определённого пользователя 2й способ
			UserDao userDao2 = DefaultUserDao.getInstance();
			UserData user2 = userDao2.getUserById(1);
			System.out.println(user2);
			
			//Вывод всех пользователей
			List<UserData> allUser = userDao.getAllUsers();
			System.out.println(allUser);
			
			//Изменение данных опр пользователя
//			System.out.println(userDao.updateUser(13,"Anastasia","Gaeva", "1998-03-20", "qwerty@gmail.com","123", 1));
			
			//Вывод всех туров определёного пользователя 2й способ
			TourDao tourDao = DefaultTourDao.getInstance();
			List<TourData> toursForUser2 = tourDao.getToursForUser(6);
			System.out.println(toursForUser2);

			//Вывод всех туров цены которых в пределах от 1000 до 3000
			List<TourData> toursPrice = tourDao.getToursBetweenPrice(1000, 3000);
			System.out.println(toursPrice);
			
//////////////////////////////////////////////////////////////////////////////////////	
			
			HotelDao hotelDao = DefaultHotelDao.getInstance();
			HotelData hotel = hotelDao.getHotelById(2);
			System.out.println(hotel);
			
			List<HotelData> hotelsByClass = hotelDao.getHotelsByClass("5*");
			System.out.println(hotelsByClass);

			List<HotelData> hotelsFromCity = hotelDao.getHotelsFromCity(1);
			System.out.println(hotelsFromCity);
			
			List<HotelData> hotelsByRoom= hotelDao.getHotelsByRoom("lux");
			System.out.println(hotelsByRoom);

			List<HotelData> hotelsByPlaces= hotelDao.getHotelsByPlaces(3);
			System.out.println(hotelsByPlaces);
			
			System.out.println(hotelDao.updateHotel(4, 3,"Gatwik", "Delux Room", 2,"4*"));
			
/////////////////////////////////////////////////////////////////////////////////////////////////////////
			
			OrderDao orderDao = DefaultOrderDao.getInstance();
//			System.out.println(orderDao.setOrder(1, 1, 2));
			
			OrderData order = orderDao.getOrderById(8);
			System.out.println(order);
			
			List<OrderData> getAllOrders = orderDao.getAllOrders();
			System.out.println(getAllOrders);
		}

	}
		
	

