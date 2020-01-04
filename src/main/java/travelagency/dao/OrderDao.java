package travelagency.dao;

import java.util.List;

import travelagency.modelsdata.OrderData;

public interface OrderDao {
	OrderData getOrderById (int order_id);
	List<OrderData> getOrdersForUser(int user_id);
	boolean setOrder(int user_id, int tour_id, int col_tickets);
	List<OrderData> getAllOrders();
}
