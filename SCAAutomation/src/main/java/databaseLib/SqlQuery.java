package databaseLib;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlQuery extends DatabaseConnection{

	public static Statement statement;
	public static ResultSet resultset;
	
	public static void executeQuery(String query, String columnName) {
		try {
			statement = connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Statement object not created");
		}
		try {
			resultset = statement.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Unable to execute query or problem in storing the queries in result set");
		}	
		
		try {
			while(resultset.next()) {
				resultset.getString(columnName);
				System.out.println(resultset.getString(columnName));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
