package travelagency.dao.impl;

import java.util.List;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import travelagency.dao.OrderDao;
import travelagency.dbutils.DbHelper;
import travelagency.modelsdata.OrderData;

public class DefaultOrderDao implements OrderDao{

	public static final String SELECT_ORDER_BY_ID = "SELECT * FROM orders WHERE order_id=?";
	private static final String SELECT_ORDERS_FOR_USER = "SELECT o.order_id, o.tour_id2, o.user_id2 "
			+ "FROM orders o \r\n" + 
			"JOIN users u ON o.user_id2 = u.user_id\r\n" + 
			"WHERE u.user_id = ?";
	private static final String INSERT_ORDER = "INSERT INTO orders (user_id2, tour_id2, col_tickets)"
			+ "VALUES (?, ?, ?);";
	public static final String SELECT_ALL_ORDERS = "SELECT * FROM orders";
	
	private static DefaultOrderDao instance;
	private DbHelper dbHelper;

	private DefaultOrderDao() {
		dbHelper = DbHelper.getInstance();
	}

	public synchronized static OrderDao getInstance() {
		if (instance == null) {
			instance = new DefaultOrderDao();
		}
		return instance;
	}

	// Информация о заказе по id заказа
	@Override
	public OrderData getOrderById(int order_id) {
		try {
			OrderData orderData = null;
			try (Connection conn = dbHelper.getConnection();
					PreparedStatement statement = conn.prepareStatement(SELECT_ORDER_BY_ID)) {
				statement.setInt(1, order_id);
				try (ResultSet rs = statement.executeQuery()) {
					if (rs.next()) {
						orderData = new OrderData();
						orderData.setOrder_id(rs.getInt("order_id"));
						orderData.setUser_id(rs.getInt("user_id2"));
						orderData.setTour_id(rs.getInt("tour_id2"));
						orderData.setCol_tickets(rs.getInt("col_tickets"));
					}
				}
			}
			return orderData;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	
	//Список заказов определённого пользователя
	@Override
	public List<OrderData> getOrdersForUser(int user_id) {
		List<OrderData> orders = new ArrayList<OrderData>();
		try (Connection conn = dbHelper.getConnection();
				PreparedStatement ps = conn.prepareStatement(SELECT_ORDERS_FOR_USER)) {
			ps.setInt(1, user_id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				OrderData orderData = new OrderData();
				orderData.setOrder_id(rs.getInt("order_id"));
				orderData.setUser_id(rs.getInt("user_id2"));
				orderData.setTour_id(rs.getInt("tour_id2"));
				orderData.setCol_tickets(rs.getInt("col_tickets"));
				orders.add(orderData);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return orders;
	}
	
	//Добавление заказа
	@Override
	public boolean setOrder(int user_id, int tour_id, int col_tickets) {
		try (Connection conn = dbHelper.getConnection();
				PreparedStatement statement = conn.prepareStatement(INSERT_ORDER)) {
			statement.setInt(1, user_id);
			statement.setInt(2, tour_id);
			statement.setInt(3, col_tickets);
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	//Список всех заказов
	@Override
	public List<OrderData> getAllOrders(){
		List<OrderData> orders = new ArrayList<>();
		try (Connection conn = dbHelper.getConnection();
				PreparedStatement statement = conn.prepareStatement(SELECT_ALL_ORDERS)) {
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				OrderData orderData = new OrderData();
				orderData.setOrder_id(rs.getInt("order_id"));
				orderData.setUser_id(rs.getInt("user_id2"));
				orderData.setTour_id(rs.getInt("tour_id2"));
				orderData.setCol_tickets(rs.getInt("col_tickets"));
				orders.add(orderData);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return orders;
	}
}
