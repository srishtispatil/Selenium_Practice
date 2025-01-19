package com.kn.dynamicQuary;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;




public class DynamicQuary {
	public static final String URL="jdbc:mysql://localhost:3306/JDBC";
	public static final String USER_NAME="root";
	public static final String PASSWORD="root";
	public static final String INSERT_EMP="INSERT INTO EMPLOYEE VALUES(?,?,?);";

	public static void main(String[] args) {
	Scanner sc=new Scanner(System.in);
		Connection con=null;
		
		
		try {
			//1.load and register the driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			//2.establish connection
			try {
				con=DriverManager.getConnection(URL, USER_NAME, PASSWORD);
				
				//3.create preparedStatement using connection
				PreparedStatement pstmt =con.prepareStatement(INSERT_EMP);
				
				//4.send and execute query using prepared statement
				
				
				System.out.println("Enter the Employee Id =");
				int empID= sc.nextInt();
				System.out.println("Enter the Employee Name");
				String empName =sc.next();
				System.out.println("Enter the Employee Salry");
				int empSalary=sc.nextInt();
				
				pstmt.setInt(1, empID);
				pstmt.setString(2, empName);
				pstmt.setInt(3, empSalary);
				
				
				int count=pstmt.executeUpdate();
				System.out.println(count+"record inserted");
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(con !=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
		
		
		
		
		
		

	}

}
