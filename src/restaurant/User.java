package restaurant;

import java.util.concurrent.atomic.AtomicInteger;

import maintenance.MysqlConnect;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User {

	private String username;
	private String password;
	private String email;
	private String type;
	MysqlConnect mysqlConnect = new MysqlConnect();


	/************************
	 * Empty constructor
	 *************************/
	public User() {

	}

	/************************
	 * Full constructor
	 *************************/
	public User(String username, String password, String email, String type) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.type = type;
	}

	/***********************************************
	 * Generic constructor with type as a parameter
	 **********************************************/
	public User(String type) {
		final int numUser;
		final AtomicInteger count = new AtomicInteger(0);
		numUser = count.incrementAndGet();
		this.username = "username" + numUser;
		this.password = "1234";
		this.email = "someone@site.com";
		this.type = type;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String checkPassword(int length, String password) {

		if (length == 4) {
			return password;
		} else {
			return "1234";
		}

	}
	
	/********************************************
	 * Get user to compare from the Db
	 *********************************************/	
		public User getUser(User use) {

			User user = null;

			try {
				String sql = "SELECT * FROM USER WHERE USERNAME = ? AND PASSWORD = ? AND TYPE = ?";

				java.sql.PreparedStatement statement = mysqlConnect.connect().prepareStatement(sql);

				statement.setString(1, use.getUsername());
				statement.setString(2, use.getPassword());
				statement.setString(3, use.getType());

				ResultSet rs = statement.executeQuery();

				while (rs.next()) {
					user = new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				mysqlConnect.disconnect();
			}

			return user;

		}
		
		
		/********************************************
		 * Create a new User type client
		 *********************************************/

		public Boolean insertUserClient(String username) {

			String sql = "INSERT INTO user (username,password, type) VALUES (?,?,?)";
			try {
				java.sql.PreparedStatement statement = mysqlConnect.connect().prepareStatement(sql);

				statement.setString(1, username);
				statement.setString(2, "1234");
				statement.setString(3, "Client");

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
		 * Check if the client exist
		 ************************************************/
		public Boolean checkClient(String clientName) {

			try {
				String sql = "SELECT * FROM user WHERE username ='" + clientName + "'";

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
		
		
		
	}


