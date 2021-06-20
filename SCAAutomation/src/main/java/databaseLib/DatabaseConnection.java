package databaseLib;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import commonUtilities.PropertyManager;

public class DatabaseConnection {

	private static String url;
	private  static String username;
	private static String password;
	public static Connection connection;

	public static Connection connectDB() {
		PropertyManager.loadConfig();
		url = PropertyManager.prop.getProperty("dbURL");
		username = PropertyManager.prop.getProperty("dbUsername");
		password = PropertyManager.prop.getProperty("dbPassword");
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Unable to load JDBC driver");
		}
		try {
			connection = DriverManager.getConnection(url, username, password);
			  if (connection != null) {
                  System.out.println("Connection established with the database");
              }
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Unable to establish connection with the database");
		}
		return connection;
	}

	public static void disconnectDB() {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Unable to close the DB connection");
			}
		}
	}
}
