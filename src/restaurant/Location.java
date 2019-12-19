package restaurant;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JComboBox;

import maintenance.MysqlConnect;

public class Location {

	private String idLocation, size, position, state;
	final String VACANT = "Vacant", DIRTY = "Dirty", OCCUPIED = "Occupied";

	static MysqlConnect mysqlConnect = new MysqlConnect();

	/********************************************
	 * Constructors
	 *********************************************/
	public Location() {

	}

	public Location(String idLocation, String size, String state, String position) {
		super();
		this.idLocation = idLocation;
		this.size = size;
		this.state = state;
		this.position = position;

	}

	/********************************************
	 * Getters and Setters
	 *********************************************/

	public String getIdLocation() {
		return idLocation;
	}

	public void setIdLocation(String idLocation) {
		this.idLocation = idLocation;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	/********************************************
	 * Create a new Location
	 *********************************************/

	public Boolean insertNewLocation(String number, String size, String position) {

		String sql = "INSERT INTO location (idlocation, size, position) VALUES (?,?,?)";
		try {
			java.sql.PreparedStatement statement = mysqlConnect.connect().prepareStatement(sql);

			statement.setString(1, number);
			statement.setString(2, size);
			statement.setString(3, position);

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
	 * Get all the Locations from the table Location
	 ************************************************/
	public ArrayList<Location> getLocations() {

		ArrayList<Location> location = new ArrayList<Location>();

		Location loc;
		try {
			String sql = "SELECT * FROM location";

			java.sql.PreparedStatement statement = mysqlConnect.connect().prepareStatement(sql);

			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()) {

				loc = new Location(rs.getString("idLocation"), rs.getString("size"), rs.getString("state"),
						rs.getString("position"));

				location.add(loc);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			mysqlConnect.disconnect();
		}

		return location;
	}

	/*************************************************
	 * Get specific Location from the table Location
	 ************************************************/
	public void getLocation(String idLocation) {

		try {
			String sql = "SELECT * FROM location WHERE idLocation = " + idLocation;

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
	 * Modify for a specific Location
	 *********************************************/

	public Boolean updateLocation(String number, String size, String position) {

		String sql = "UPDATE location SET size = ? , position = ? WHERE idLocation =" + number;

		try {
			java.sql.PreparedStatement statement = mysqlConnect.connect().prepareStatement(sql);

			statement.setString(1, size);
			statement.setString(2, position);

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
	 * Set state for the specific Location
	 *************************************************/

	public void setStateLocation(String idLocation, String state) {

		try {

			String sql = "UPDATE location SET state = ? WHERE idLocation =" + idLocation;

			java.sql.PreparedStatement statement = mysqlConnect.connect().prepareStatement(sql);

			statement.setString(1, state);

			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			mysqlConnect.disconnect();
		}
	}

	/*************************************************
	 * Check if the specific Location is available
	 *************************************************/

	public Boolean checkAvailable(String idLocation) {

		try {
			String sql = "SELECT * FROM location WHERE state = 'Vacant' AND idLocation =" + idLocation;

			java.sql.PreparedStatement statement = mysqlConnect.connect().prepareStatement(sql);

			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()) {
				statement.execute();
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			mysqlConnect.disconnect();
		}

		return false;

	}

	/*************************************************
	 * Get state of the Location
	 *************************************************/

	public String getState(String idLocation) {

		String state = null;

		try {
			String sql = "SELECT state FROM location WHERE idLocation =" + idLocation;

			java.sql.PreparedStatement statement = mysqlConnect.connect().prepareStatement(sql);

			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()) {

				state = rs.getString(1);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			mysqlConnect.disconnect();
		}

		return state;

	}

	/*************************************************
	 * Check if Location is dirty
	 *************************************************/

	public Boolean checkDirty(String idLocation) {

		try {
			String sql = "SELECT * FROM location WHERE state = 'Dirty' and idLocation =" + idLocation;

			java.sql.PreparedStatement statement = mysqlConnect.connect().prepareStatement(sql);

			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()) {
				statement.execute();
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			mysqlConnect.disconnect();
		}

		return false;

	}

	/***************************************************
	 * Check size of the specific Location is available
	 ***************************************************/

	public String checkSize(String idLocation) {

		String size = null;
		try {
			String sql = "SELECT size FROM location WHERE idLocation =" + idLocation;

			java.sql.PreparedStatement statement = mysqlConnect.connect().prepareStatement(sql);

			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()) {
				size = rs.getString(1);
			}
			statement.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			mysqlConnect.disconnect();
		}

		return size;

	}

	/*************************************************
	 * Delete the specific Location
	 *************************************************/

	public Boolean deleteLocation(String idLocation) {
		Order order = new Order();
		order.deleteOrderbyLocation(idLocation);
		try {
			String sql = "DELETE FROM location WHERE idLocation = " + idLocation;
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
	 * Get Location for ComboBox
	 ************************************************/
	public void getLocationsCb(JComboBox<Object> cbLocations) {

		try {
			String sql = "SELECT idLocation FROM location";

			java.sql.PreparedStatement statement = mysqlConnect.connect().prepareStatement(sql);

			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()) {

				cbLocations.addItem(rs.getString("idLocation"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			mysqlConnect.disconnect();
		}

	}

}