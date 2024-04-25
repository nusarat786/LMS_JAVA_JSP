package com.connection;
import java.sql.*;
public class Connection_ {

	public static Connection getc() {
		
		Connection connection = null;
		
		String url = "jdbc:oracle:thin:@//localhost:1521/orcl";
		String username = "sys as sysdba"; // Assuming "sys" is the username based on your provided details
		String password = "123456789"; // Replace with the actual sys password
		
		
		
		try {
			//1. set driver file
			Class.forName("oracle.jdbc.OracleDriver");
			
			//2. get connection
			connection = DriverManager.getConnection(url, username, password);
			
			//3. pass message
			System.out.println("DB connected");
		}catch(Exception e){
			System.out.println(e);
		}
		
		
		
		return connection;
		
	}
	public static void check() {
		String jdbcUrl = "jdbc:oracle:thin:@//localhost:1521/orcl";
		String username = "sys as sysdba"; // Assuming "sys" is the username based on your provided details
		String password = "123456789"; // Replace with the actual sys password

		Connection c = getc();
		try {
			c.close();
			System.out.println("closed");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
		
	}
	
	
    
	public static void main(String[] args) {
		
		//check();
	}
}
