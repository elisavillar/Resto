package restaurant;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import maintenance.MysqlConnect;

public class Order {

	private String idOrder, date, clientName, status, idLocation;
	private double total;
	User clientUser = new User();
	final String NEW = "New", IN_PROGRESS = "In progress", READY = "Ready", SERVED = "Served", PAID = "Paid";
	private ArrayList<OrderDetails> orderDetailList = new ArrayList<OrderDetails>();
	OrderDetails orderDetails = new OrderDetails();
	final double HST = 1.13f;

	static MysqlConnect mysqlConnect = new MysqlConnect();

	/********************************************
	 * Constructors
	 *********************************************/
	public Order() {

	}

	public Order(String date, String clientName, String idLocation) {

		this.date = date;
		this.clientName = clientName;
		this.idLocation = idLocation;

	}

	public Order(String idOrder, String date, String clientName, String status, String idLocation) {

		this.idOrder = idOrder;
		this.date = date;
		this.clientName = clientName;
		this.status = status;
		this.idLocation = idLocation;

	}

	/********************************************
	 * Getters and Setters
	 *********************************************/

	public String getIdOrder() {
		return idOrder;
	}

	public void setIdOrder(String idOrder) {
		this.idOrder = idOrder;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIdLocation() {
		return idLocation;
	}

	public void setIdLocation(String idLocation) {
		this.idLocation = idLocation;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public String getDate() {
		date = this.date.substring(0, 10);
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public ArrayList<OrderDetails> getOrderDetailList() {
		return orderDetailList;
	}

	public void setOrderDetailList(ArrayList<OrderDetails> orderDetailList) {
		this.orderDetailList = orderDetailList;
	}

	/********************************************
	 * Create a new Order
	 *********************************************/

	public Boolean insertNewOrder(String clientName, String idLocation) {

		String sql = "INSERT INTO  restodb.order (clientName, location_idlocation) VALUES (?,?)";
		try {
			java.sql.PreparedStatement statement = mysqlConnect.connect().prepareStatement(sql);

			statement.setString(1, clientName);
			statement.setString(2, idLocation);

			if (!clientUser.checkClient(clientName)) {
				clientUser.insertUserClient(clientName);
			}

			boolean x = statement.execute();

			if (x)
				return false;
			else
				return true;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			mysqlConnect.disconnect();
		}

		return false;
	}

	/********************************************
	 * Modify status for a specific Order
	 *********************************************/

	public Boolean updateOrderStatus(String idOrder, String option) {

		String sql = "UPDATE restodb.order SET status = ? WHERE idorder =" + idOrder;

		try {
			java.sql.PreparedStatement statement = mysqlConnect.connect().prepareStatement(sql);

			statement.setString(1, option);

			int x = statement.executeUpdate();

			if (x > 0)
				return true;
			else
				return false;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			mysqlConnect.disconnect();
		}

		return false;
	}

	/********************************************
	 * Add item to the Order
	 *********************************************/

	public void addItem(String nameMeal, String quantity, String idOrder) {

		try {

			orderDetails.insertNewOrderDetail(idOrder, quantity, nameMeal);

		} finally {
			mysqlConnect.disconnect();
		}
	}

	/*************************************************
	 * Delete the specific Order
	 *************************************************/

	public Boolean deleteOrder(String idOrder) {

		OrderDetails od = new OrderDetails();
		od.deleteAllOrderDetails(idOrder);

		try {
			String sql = "DELETE FROM restodb.order WHERE idorder = " + idOrder;
			java.sql.PreparedStatement statement = mysqlConnect.connect().prepareStatement(sql);

			int x = statement.executeUpdate(sql);

			if (x > 0)
				return true;
			else
				return false;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			mysqlConnect.disconnect();
		}

		return false;
	}

	
	/*************************************************
	 * Delete the specific Order for the Location
	 *************************************************/

	public Boolean deleteOrderbyLocation(String idLocation) {

		try {
			String sql = "DELETE restodb.order FROM restodb.order INNER JOIN orderdetails ON idorder = order_idorder" + 
					"WHERE location_idlocation =1;" + idLocation;
			java.sql.PreparedStatement statement = mysqlConnect.connect().prepareStatement(sql);

			int x = statement.executeUpdate(sql);

			if (x > 0)
				return true;
			else
				return false;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			mysqlConnect.disconnect();
		}

		return false;
	}
	
	
	/********************************************
	 * Calculate total
	 *********************************************/

	public double calculateTotal(String idOrder) {

		total = 0;

		try {
			String sql = "SELECT price, quantity FROM orderdetails, meal WHERE meal_idmeal = idmeal AND order_idorder = "
					+ idOrder;
			java.sql.PreparedStatement statement = mysqlConnect.connect().prepareStatement(sql);

			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()) {

				total += rs.getDouble(1) * rs.getDouble(2) * HST;

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			mysqlConnect.disconnect();
		}

		return total;
	}

	/************************************************
	 * Get all the Order from the table Order
	 ************************************************/
	public ArrayList<Order> getOrders() {

		ArrayList<Order> order = new ArrayList<Order>();

		Order or = null;
		try {
			String sql = "SELECT * FROM restodb.order";

			java.sql.PreparedStatement statement = mysqlConnect.connect().prepareStatement(sql);

			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()) {

				or = new Order(rs.getString("idorder"), rs.getString("date"), rs.getString("clientName"),
						rs.getString("status"), rs.getString("location_idlocation"));

				order.add(or);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			mysqlConnect.disconnect();
		}

		return order;
	}

	/************************************************
	 * Get all today's Orders from the table Order
	 ************************************************/
	public ArrayList<Order> getTodaysOrders() {

		ArrayList<Order> order = new ArrayList<Order>();

		Order or = null;

		Date date = new Date();

		Timestamp ts = new Timestamp(date.getTime());
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		try {
			String sql = "SELECT * FROM restodb.order WHERE date >= '" + formatter.format(ts).substring(0, 10) + "'";

			java.sql.PreparedStatement statement = mysqlConnect.connect().prepareStatement(sql);

			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()) {

				or = new Order(rs.getString("idorder"), rs.getString("date"), rs.getString("clientName"),
						rs.getString("status"), rs.getString("location_idlocation"));

				order.add(or);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			mysqlConnect.disconnect();
		}

		return order;
	}

	
	/*************************************************
	 * Get the IdOrder for a specific Client
	 *************************************************/

	public String getIdOrderClient(String username) {

		String idOrderGotten = null;

		try {
			String sql = "SELECT idorder FROM restodb.order WHERE status = 'New' and clientname = '" + username + "'";
			java.sql.PreparedStatement statement = mysqlConnect.connect().prepareStatement(sql);

			ResultSet rs = statement.executeQuery(sql);

			if (rs.next())

				idOrderGotten = rs.getString("idorder");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			mysqlConnect.disconnect();
		}

		return idOrderGotten;
	}

	/*************************************************
	 * Get the Location for a specific Client
	 *************************************************/

	public String getLocationClient(String username) {

		String idLocationGotten = null;

		try {
			String sql = "SELECT location_idLocation FROM restodb.order WHERE status = 'New' and clientname = '"
					+ username + "'";
			java.sql.PreparedStatement statement = mysqlConnect.connect().prepareStatement(sql);

			ResultSet rs = statement.executeQuery(sql);

			if (rs.next())

				idLocationGotten = rs.getString("location_idLocation");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			mysqlConnect.disconnect();
		}

		return idLocationGotten;
	}
	
	/****************************************************
	 * Get the id of order for the Location
	 ****************************************************/

	public String getIdOrderLocation(String loc) {

		String idOrderGotten = null;

		try {
			String sql = "SELECT idorder FROM restodb.order WHERE (status = 'New' or status= 'In Progress') and location_idlocation = " + loc;
			java.sql.PreparedStatement statement = mysqlConnect.connect().prepareStatement(sql);

			ResultSet rs = statement.executeQuery(sql);

			if (rs.next())

				idOrderGotten = rs.getString(1);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			mysqlConnect.disconnect();
		}

		return idOrderGotten;
	}

	/********************************************
	 * Set all past orders to paid and vacant
	 *********************************************/

	public void setOldOrderToPaid() {

		Date date = new Date();

		Timestamp ts = new Timestamp(date.getTime());
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String sql = "UPDATE restodb.order, location set status = 'Paid', state = 'Vacant' where idorder > 0 and date < '"
				+ formatter.format(ts).substring(0, 10) + " 00:00:00" + "'";

		try {
			java.sql.PreparedStatement statement = mysqlConnect.connect().prepareStatement(sql);

			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			mysqlConnect.disconnect();
		}

	}
	
	/*************************************************
	 * Get the Status for a specific Order
	 *************************************************/

	public String getStatusOrder(String idLocation) {

		String statusGotten = null;

		try {
			String sql = "SELECT status FROM restodb.order WHERE status != 'Paid' AND location_idLocation =" + idLocation;
			
			java.sql.PreparedStatement statement = mysqlConnect.connect().prepareStatement(sql);

			ResultSet rs = statement.executeQuery(sql);

			if (rs.next())

				statusGotten = rs.getString(1);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			mysqlConnect.disconnect();
		}

		return statusGotten;
	}

}
