package db_connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionMysql {
	private Connection connection;
	
	//constants with connection params
	private final String HOST_DOT_ENV = "127.0.0.1";
	private final String PORT_DOT_ENV = "33060";
	private final String USER_DOT_ENV = "root";
	private final String PASSWORD_DOT_ENV = "password";
	
	/**
	 * Establish the connection to the MySQL server.
	 * 
	 * @throws ClassNotFoundException If class is not found
	 * @throws SQLException if it can't establish connection to MySQL.
	 */
	public void openConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			this.connection = DriverManager.getConnection("jdbc:mysql://" + HOST_DOT_ENV + ":" + PORT_DOT_ENV + " ", USER_DOT_ENV, PASSWORD_DOT_ENV);
			System.out.println("Connected to MySQL server at " +  HOST_DOT_ENV + ":" + PORT_DOT_ENV);
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println("ERROR: Cant connect to MySQL server.\n" + e);
		}
	}
	
	/**
	 * It close the connection to the MySQL server.
	 * @throws SQLException if it can't close connection to MySQL.
	 */
	public void closeConnection() {
		try {
			this.connection.close();
			System.out.println("\nMySQL server conection at " + HOST_DOT_ENV + ":" + PORT_DOT_ENV + " closed.");
		} catch (SQLException e) {
			Logger.getLogger(ConnectionMysql.class.getName()).log(Level.SEVERE, "ERROR: Cant close the connection to MySQL server.\n", e);
		}
	}
	
	/**
	 * This method creates a specified database if not exists.
	 * 
	 * @param databaseName The name of the database to create.
	 */
	public void createDatabase(String databaseName) {
		try {
			Statement stdb = this.connection.createStatement();
			String query = "CREATE DATABASE " + databaseName + ";";
			stdb.executeUpdate(query);
			System.out.println("Database " + databaseName + " created.");
		} catch (SQLException e) {
			Logger.getLogger(ConnectionMysql.class.getName()).log(Level.SEVERE, "ERROR: Cant create the database " + databaseName + "\n", e);
		}
	}
	
	/**
	 * This method deletes a specified database if exists.
	 * 
	 * @param databaseName The name of the database to delete.
	 */
	public void dropDatabase(String databaseName) {
		try {
			Statement stdb = this.connection.createStatement();
			String query = "DROP DATABASE IF EXISTS " + databaseName + ";";
			stdb.executeUpdate(query);
			System.out.println("Database " + databaseName + " deleted in case of exists.");
		} catch (SQLException e) {
			Logger.getLogger(ConnectionMysql.class.getName()).log(Level.SEVERE, "ERROR: Cant delete the database " + databaseName + "\n", e);
		}
	}
	
	
	/**
	 * It tries to use the database. 
	 * 
	 * @param databaseName Name of the database to connect
	 */
	private void useDatabase(String databaseName) {
		try {
			String query = "USE " + databaseName + ";";
			this.connection.createStatement().executeUpdate(query);
		} catch (SQLException e) {
			Logger.getLogger(ConnectionMysql.class.getName()).log(Level.SEVERE, "ERROR: Cant use the database " + databaseName + "\n", e);
		}
	}
	
	
	/**
	 * This method creates a specified table if not exists
	 * 
	 * @param databaseName Name of the database to connect
	 * @param tableName Name of the table to create
	 * @param tableConent Content of the table (columns)
	 */
	public void createTable(String databaseName, String tableName, String tableConent) {
		try {
			useDatabase(databaseName);
			String query = "CREATE TABLE IF NOT EXISTS " + tableName + "(" + tableConent + ");";
			this.connection.createStatement().executeUpdate(query);
			System.out.println("Table " + tableName + " created.");
		} catch (SQLException e) {
			System.out.println("ERROR: Cant create the table " + tableName + "\n" + e);
		}
	}
	
	/**
	 * This method deletes a specified table if exists
	 * 
	 * @param databaseName Name of the database to connect
	 * @param tableName Name of the table to delete
	 */
	public void dropTable(String databaseName, String tableName) {
		try {
			useDatabase(databaseName);
			String query = "DROP TABLE IF EXISTS" + tableName + ";";
			Statement sttb = this.connection.createStatement();
			sttb.executeUpdate(query);
			System.out.println("Table " + tableName + " deleted.");
		} catch (SQLException e) {
			System.out.println("ERROR: Cant delete the table " + tableName + "\n" + e);
		}
	}
	
	/**
	 * Inserts the data into a specified table
	 * 
	 * @param databaseName Name of the database to use
	 * @param tableName Table name in the database to insert values
	 * @param values The values that will be inserted.
	 */
	public void insertData(String databaseName, String tableName, String values) {
		try {
			useDatabase(databaseName);
			String query = "INSERT INTO " + tableName + " (" + getColumnsFromTable(databaseName, tableName) + ") VALUES " + values + ";";
			this.connection.createStatement().executeUpdate(query);
			System.out.println("Data succesfuly inserted into table " + tableName);
		} catch (SQLException e) {
			System.out.println("ERROR: Cant insert data into table " + tableName + "\n" + e);
		}
	}
	
	/**
	 * It will update the specified values into a specified table.
	 * 
	 * @param databaseName Name of the database to use
	 * @param tableName Table name in the database to update values
	 * @param valuesToSet The values that will be set. ex(columName = "value")
	 * @param condition The condition where the values will update (after WHERE).
	 */
	public void updateData(String databaseName, String tableName, String valuesToSet, String condition) {
		try {
			useDatabase(databaseName);
			String query = "UPDATE " + tableName + " SET " + valuesToSet + " WHERE " + condition + ";";
			this.connection.createStatement().executeUpdate(query);
			System.out.println("Data succesfuly updated to " + valuesToSet + " from table " + tableName + " where " + condition);
		} catch (SQLException e) {
			System.out.println("ERROR: Cant update data from table " + tableName + "\n" + e);
		}
	}
	
	/**
	 * Delete values from table where condition
	 * 
	 * @param databaseName Name of the database to connect
	 * @param tableName Table name in the database to delete values
	 * @param condition The condition to delete values (after WHERE).
	 */
	public void deleteData(String databaseName, String tableName, String condition) {
		try {
			useDatabase(databaseName);
			String query = "DELETE FROM " + tableName + " WHERE " + condition + ";";
			this.connection.createStatement().executeUpdate(query);
			System.out.println("Data succesfuly deleted from table " + tableName + " where " + condition);
		} catch (SQLException e) {
			System.out.println("ERROR: Cant delete data into table " + tableName + "\n" + e);
		}
	}

	/**
	 * This method gets the column names of a specified table without the auto-incrementals 
	 * 
	 * @param databaseName Name of the database to connect
	 * @param tableName Table name in the database to extract column names
	 * @return return a String of a table names separated with commas,
	 */
	public String getColumnsFromTable(String databaseName, String tableName) {
		String columns = "";
		try {
			String query = "SELECT * FROM " + tableName + " LIMIT 0"; // The LIMIT can be used when you want a specified number of rows from a result rather than the entire rows. 
			Statement st = this.connection.createStatement();
			ResultSet resultSet = st.executeQuery(query);
			ResultSetMetaData tableContent = resultSet.getMetaData(); // Provides information about the obtained ResultSet object like, the number of columns, names of the columns, datatypes...
			for (int i = 1; i <= tableContent.getColumnCount(); i++) {
				if (!tableContent.isAutoIncrement(i)) {
					if (i != tableContent.getColumnCount()) {
						columns += tableContent.getColumnName(i) + "," ;
					} else {
						columns += tableContent.getColumnName(i);
					}
				}
			}
		} catch (Exception e) {
			System.out.println("ERROR: Can not get meta data column name fom table " + tableName);
		}

		return columns;
	}
	
	
	/**
	 * This method prints the content of a table dinamically
	 * 
	 * @param databaseName  Name of the database to connect
	 * @param tableName Name of the table to print the content
	 */
	public void printTableContent(String databaseName, String tableName) {
		try {
			String query = "SELECT * FROM " + tableName;
			java.sql.ResultSet resultSet;
			resultSet = this.connection.createStatement().executeQuery(query);
			java.sql.ResultSetMetaData tableContent = resultSet.getMetaData();
			System.out.println("\nPrint table " + tableName + ":");
			int numColumnas = tableContent.getColumnCount();
			while (resultSet.next()) {
				for (int i = 1; i <= numColumnas; i++) {
					if (i != numColumnas) {
						System.out.print(tableContent.getColumnName(i) + ": " + resultSet.getString(tableContent.getColumnName(i)) + "  |  ");
					} else {
						System.out.print(tableContent.getColumnName(i) + ": " + resultSet.getString(tableContent.getColumnName(i)) + "\n");
					}
				}
			}
		} catch (SQLException e) {
			System.out.println("ERROR: Unable to print the content from table " + tableName + "\n" + e);
		}
	}
	
}
