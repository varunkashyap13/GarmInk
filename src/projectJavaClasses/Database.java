/**
 * @author: Varun Kashyap
 * FileName: Database.java
 * Date: 06/28/2021
 * Description: Class to access H2 Database. 
 * Contains methods to implement queries on Student, Courses, and Registrar tables.
 */

package projectJavaClasses;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

	public Connection con;
	public Statement stm;
	private String dbError = "";
	ResultSet res;

	public Database() {
		try {
			//  Get a Connection to the database
			Class.forName("com.mysql.cj.jdbc.Driver");
			String dbURL = "jdbc:mysql://sql5.freemysqlhosting.net:3306/sql5432509";
			String username = "sql5432509";
			String password = "PgZizn2fNV";
			
			con = DriverManager.getConnection(dbURL, username, password);
			stm = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, 
                    ResultSet.CONCUR_READ_ONLY);
			
		} catch (Exception e) {
			dbError = "Unable to connect to the database.";
		}
	}

	// query for ACCOUNTS table
	public void setSearchAccounts() {
		try {
			res = stm.executeQuery("SELECT * FROM ACCOUNTS");
		} catch (SQLException e) {
			dbError += "Unable to search for your account.";
		}
	}

	// query for PRODUCTS table
	public void setSearchProducts() {
		try {
			res = stm.executeQuery("SELECT * FROM PRODUCTS");
		} catch (SQLException e) {
			dbError += "Unable to grab our products.";
		}
	}
	
	// query for CARTS table
	public void setSearchCarts() {
		try {
			res = stm.executeQuery("SELECT * FROM CARTS");
		} catch (SQLException e) {
			dbError += "Unable to grab the items from your cart.";
		}
	}
	
	public String getDbError() {
		return dbError;
	}

	public ResultSet getResult() {
		return res;
	}
}