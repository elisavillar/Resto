package restaurant;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import maintenance.MysqlConnect;

public class OrderDetails {

	private String mealName, quantity, idOrderDetails, order_idOrder, meal_idOrderMeal;

	MysqlConnect mysqlConnect = new MysqlConnect();
	DefaultTableModel model = new DefaultTableModel();

	private double price;

	/********************************************
	 * Constructors
	 *********************************************/

	public OrderDetails() {

	}

	OrderDetails(String idOrderDetails, String name, String quantity, Double price) {

		this.idOrderDetails = idOrderDetails;
		this.mealName = name;
		this.quantity = quantity;
		this.price = price;
	}

	public OrderDetails(String idOrderDetails, String order_idOrder, String quantity, String meal_idOrderMeal) {

		this.idOrderDetails = idOrderDetails;
		this.order_idOrder = order_idOrder;
		this.quantity = quantity;
		this.meal_idOrderMeal = meal_idOrderMeal;

	}

	public OrderDetails(String string, String string2) {
		// TODO Auto-generated constructor stub
	}

	/********************************************
	 * Getters and Setters
	 *********************************************/

	public String getMealName() {
		return mealName;
	}

	public void setMealName(String mealName) {
		this.mealName = mealName;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getIdOrderDetails() {
		return idOrderDetails;
	}

	public void setIdOrderDetails(String idOrderDetails) {
		this.idOrderDetails = idOrderDetails;
	}

	public String getOrder_idOrder() {
		return order_idOrder;
	}

	public void setOrder_idOrder(String order_idOrder) {
		this.order_idOrder = order_idOrder;
	}

	public String getMeal_idOrderMeal() {
		return meal_idOrderMeal;
	}

	public void setMeal_idOrderMeal(String meal_idOrderMeal) {
		this.meal_idOrderMeal = meal_idOrderMeal;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	/********************************************
	 * Create a new Order detail
	 *********************************************/

	public Boolean insertNewOrderDetail(String idOrder, String quantity, String nameMeal) {

		Meal meal = new Meal();

		int idMeal = meal.getIdMeal(nameMeal);

		String sql = "INSERT INTO orderdetails (meal_idmeal, order_idorder, quantity) values (?,?,?)";
		try {
			java.sql.PreparedStatement statement = mysqlConnect.connect().prepareStatement(sql);

			statement.setInt(1, idMeal);
			statement.setString(2, idOrder);
			statement.setString(3, quantity);

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

	/*************************************************
	 * Delete the specific Order Details
	 *************************************************/

	public Boolean deleteOrderDetails(String idOrderDetails) {

		try {
			String sql = "DELETE FROM orderdetails WHERE idOrderDetails = " + idOrderDetails;
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

	/*****************************************************
	 * Delete all the order Details for the specific Order
	 *****************************************************/

	public Boolean deleteAllOrderDetails(String idOrder) {

		try {
			String sql = "DELETE FROM orderdetails WHERE order_idOrder = " + idOrder;
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

	/************************************************
	 * Get all the Order Details
	 ************************************************/
	public ArrayList<OrderDetails> getOrdersDetails() {

		ArrayList<OrderDetails> orderD = new ArrayList<OrderDetails>();

		OrderDetails ord;
		try {
			String sql = "SELECT * FROM orderdetails";

			java.sql.PreparedStatement statement = mysqlConnect.connect().prepareStatement(sql);

			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()) {

				ord = new OrderDetails(rs.getString("idorderdetails"), rs.getString("order_idorder"),
						rs.getString("quantity"), rs.getString("meal_idmeal"));
				orderD.add(ord);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			mysqlConnect.disconnect();
		}

		return orderD;
	}

	/************************************************
	 * Get all the Order Details for a specific Order
	 ************************************************/
	public ArrayList<OrderDetails> getOrdersDetails(String idOrder) {

		ArrayList<OrderDetails> orderD = new ArrayList<OrderDetails>();

		OrderDetails ord;
		try {
			String sql = "SELECT idorderdetails, name, quantity, price FROM meal, orderdetails WHERE meal_idmeal = idmeal and order_idorder ="
					+ idOrder;

			java.sql.PreparedStatement statement = mysqlConnect.connect().prepareStatement(sql);

			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()) {

				ord = new OrderDetails(rs.getString("idorderdetails"), rs.getString("name"), rs.getString("quantity"),
						rs.getDouble("price"));
				orderD.add(ord);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			mysqlConnect.disconnect();
		}

		return orderD;
	}

	/*****************************************************
	 * Get all the Order Details for a specific Location
	 *****************************************************/
	public ArrayList<OrderDetails> getOrdersDetailsKitchen(int location) {

		ArrayList<OrderDetails> orderD = new ArrayList<OrderDetails>();

		OrderDetails ord;
		try {
			String sql = "SELECT name, location_idlocation, quantity FROM meal, orderdetails, restodb.order WHERE meal_idmeal = idmeal \r\n"
					+ "and order_idorder = idorder and (status = 'New' or status= 'In progress') and location_idlocation = "
					+ location;

			java.sql.PreparedStatement statement = mysqlConnect.connect().prepareStatement(sql);

			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()) {

				ord = new OrderDetails(rs.getString("name"), rs.getString("quantity"));
				orderD.add(ord);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			mysqlConnect.disconnect();
		}

		return orderD;
	}

	/****************************************************
	 * Get the id of order Details for the specific Order
	 ****************************************************/

	public int getIdOrderDetails(String idOrder) {

		int idOrderDetailsgotten = 0;

		try {
			String sql = "SELECT idOrderDetails FROM orderdetails WHERE order_idorder = " + idOrder;
			java.sql.PreparedStatement statement = mysqlConnect.connect().prepareStatement(sql);

			ResultSet rs = statement.executeQuery(sql);

			if (rs.next())

				idOrderDetailsgotten = rs.getInt("idOrderDetails");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			mysqlConnect.disconnect();
		}

		return idOrderDetailsgotten;
	}
	
	

	/********************************************
	 * Modify for a specific Order Detail
	 *********************************************/

	public Boolean updateOrderDetail(String idOrderDetail, String quantity, String nameMeal) {

		Meal ml = new Meal();

		int idMeal = ml.getIdMeal(nameMeal);

		String sql = "UPDATE orderdetails SET quantity = ?, meal_idmeal = ? WHERE idorderdetails =" + idOrderDetail;

		try {
			java.sql.PreparedStatement statement = mysqlConnect.connect().prepareStatement(sql);

			statement.setString(1, quantity);
			statement.setInt(2, idMeal);

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
	 * Set Receipt Label  Order Detail
	 *********************************************/

	public String setLabelReceipt(String idOrder) {

		String receiptDet = "<html> <table  width=100% > <tr>" + 
							"<td> Name </td> <td> Quantity </td> <td> Price </td> </tr> <tr> <td>" ;

		String sql = "SELECT name, quantity, price FROM orderdetails, meal WHERE idmeal = meal_idmeal AND order_idorder ="
				+ idOrder;

		try {
			java.sql.PreparedStatement statement = mysqlConnect.connect().prepareStatement(sql);

			ResultSet rs = statement.executeQuery(sql);

			while (rs.next())
				receiptDet += rs.getString(1) + "</td><td>" + rs.getString(2) + "</td><td>$" + rs.getString(3) + "</td></tr>";

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			mysqlConnect.disconnect();
		}
		receiptDet += "</html>";

		return receiptDet;

	}

	/********************************************
	 * Set Kitchen Label Order Detail
	 *********************************************/

	public String setLabelKitchen(int location) {

		String kitchenOrders = "<html> <table> <tr> <td>";

		String sql = "SELECT name, quantity FROM meal, orderdetails, restodb.order WHERE meal_idmeal = idmeal and order_idorder = idorder "
				+ "and (status = 'New' or status= 'In progress') and location_idlocation = "
				+ location;

		try {
			java.sql.PreparedStatement statement = mysqlConnect.connect().prepareStatement(sql);

			ResultSet rs = statement.executeQuery(sql);

			while (rs.next())
				kitchenOrders += rs.getString(1) + "</td><td style= \"text-align: right;\">" + rs.getString(2) + "</td></tr>";

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			mysqlConnect.disconnect();
		}
		kitchenOrders += "</html>";

		return kitchenOrders;		
		
	}
}
