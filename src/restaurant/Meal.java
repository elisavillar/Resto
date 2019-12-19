package restaurant;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JComboBox;

import maintenance.MysqlConnect;

public class Meal {

	public String userType;

	MysqlConnect mysqlConnect = new MysqlConnect();

	private String name, category, menu, idMeal;
	private double price;
	Meal meal;

	/********************************************
	 * Constructors
	 *********************************************/

	public Meal () {
		
	}
	
	public Meal(String name, double price, String category, String season) {

		this.name = name;
		this.price = price;
		this.category = category;
		this.menu = season;
	}

	public Meal(String idMeal, String name, double price, String category, String season) {

		this.idMeal = idMeal;
		this.name = name;
		this.price = price;
		this.category = category;
		this.menu = season;
	}

	/********************************************
	 * Getters and Setters
	 *********************************************/

	public String getMealName() {
		return name;
	}

	public void setMealName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getMenu() {
		return menu;
	}

	public void setMenu(String season) {
		this.menu = season;
	}

	public String getIdMeal() {
		return idMeal;
	}

	public void setIdMeal(String idMeal) {
		this.idMeal = idMeal;
	}

	/********************************************
	 * Create a new Meal
	 *********************************************/

	public Boolean insertNewMeal(String name, double price, String category, String menu) {
		
		String sql = "INSERT INTO meal (name, price, category, menu) VALUES (?,?,?,?)";
		try {
			java.sql.PreparedStatement statement = mysqlConnect.connect().prepareStatement(sql);

			statement.setString(1, name);
			statement.setDouble(2, price);
			statement.setString(3, category);
			statement.setString(4, menu);

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

	/************************************************
	 * Get all the Meals from the table Meal
	 ************************************************/
	public ArrayList<Meal> getMealList() {

		ArrayList<Meal> meal = new ArrayList<Meal>();

		Meal meal1;
		try {
			String sql = "SELECT * FROM meal";

			java.sql.PreparedStatement statement = mysqlConnect.connect().prepareStatement(sql);

			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()) {

				meal1 = new Meal(rs.getString("idmeal"), rs.getString("name"), rs.getDouble("Price"), rs.getString("Category"), rs.getString("menu"));

				meal.add(meal1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			mysqlConnect.disconnect();
		}

		return meal;
	}
	
	/*************************************************
	 * Get specific Meal
	 ************************************************/
	public void getMeal(String name) {

		try {
			String sql = "SELECT * FROM meal WHERE name = " + name;

			java.sql.PreparedStatement statement = mysqlConnect.connect().prepareStatement(sql);

			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()) {
				statement.execute();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			mysqlConnect.disconnect();
		}

	}	
	
	public void getMeal(int idMeal) {

		try {
			String sql = "SELECT * FROM meal WHERE idMeal = " + idMeal;

			java.sql.PreparedStatement statement = mysqlConnect.connect().prepareStatement(sql);

			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()) {
				statement.execute();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			mysqlConnect.disconnect();
		}

	}
	

	/********************************************
	 * Modify for a specific Meal
	 *********************************************/

	public Boolean updateMeal(int idMeal, String name, double price, String category, String menu) {


		String sql = "UPDATE meal SET name = ?, price = ?, category = ?, menu = ? WHERE idMeal =" + idMeal;

		try {
			java.sql.PreparedStatement statement = mysqlConnect.connect().prepareStatement(sql);

			statement.setString(1, name);
			statement.setDouble(2, price);
			statement.setString(3, category);
			statement.setString(4, menu);

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

	/*************************************************
	 * Delete the specific Meal
	 *************************************************/

	public Boolean deleteMeal(int idMeal) {

		try {
			String sql = "DELETE FROM meal WHERE idMeal = " + idMeal;
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
	 * Get the Id for the specific Meal
	 *************************************************/

	public int getIdMeal(String name) {

		int idMealGotten = 0;

		try {
			String sql = "SELECT idmeal FROM meal WHERE name = " + "\"" + name + "\"";
			java.sql.PreparedStatement statement = mysqlConnect.connect().prepareStatement(sql);

			ResultSet rs = statement.executeQuery(sql);

			if (rs.next())

				idMealGotten = rs.getInt("idmeal");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			mysqlConnect.disconnect();
		}

		return idMealGotten;
	}
	
	/*************************************************
	 * Get the price for the specific Meal
	 *************************************************/

	public double getPriceMeal(String idOrder) {

		double priceMeal = 0;
		try {
			String sql = "SELECT price FROM meal, orderdetails WHERE meal_idmeal = idmeal and order_idorder = " + idOrder;
			java.sql.PreparedStatement statement = mysqlConnect.connect().prepareStatement(sql);

			ResultSet rs = statement.executeQuery(sql);

			if (rs.next())

				priceMeal = rs.getDouble("price");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			mysqlConnect.disconnect();
		}

		return priceMeal;
	}
	
	/************************************************
	 * Get Meal for ComboBox
	 ************************************************/
	public void getMealCb(JComboBox<Object> cbMeal) {

		try {
			String sql = "SELECT name FROM meal";

			java.sql.PreparedStatement statement = mysqlConnect.connect().prepareStatement(sql);

			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()) {

				cbMeal.addItem(rs.getString("name"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			mysqlConnect.disconnect();
		}

	}
	
	/*************************************************
	 * Get the Name for the specific Meal
	 *************************************************/

	public String getNameMeal(String name) {

		String nameMealGotten = null;

		try {
			String sql = "SELECT name FROM meal WHERE idMeal = " + name;
			java.sql.PreparedStatement statement = mysqlConnect.connect().prepareStatement(sql);

			ResultSet rs = statement.executeQuery(sql);

			if (rs.next())

				nameMealGotten = rs.getString("name");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			mysqlConnect.disconnect();
		}

		return nameMealGotten;
	}
	

}
