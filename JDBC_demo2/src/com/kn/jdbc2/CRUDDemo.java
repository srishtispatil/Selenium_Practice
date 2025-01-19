package com.kn.jdbc2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CRUDDemo {
	private static String URL ="jdbc:mysql://localhost:3306/JDBC";
	private static String USER_NAME ="root";
	private static String PASSWORD="root";
	//private static String CREATE_EMPLOYEE="CREATE TABLE EMPLOYEE(ID INT,NAME VARCHAR(30),SALARY INT);";
	private static String INSERT_EMPLOYEE1="INSERT INTO EMPLOYEE VALUES(1,'RAM',5000);";
	private static String INSERT_EMPLOYEE2="INSERT INTO EMPLOYEE VALUES(2,'SHAM',2400);";
	private static String INSERT_EMPLOYEE3="INSERT INTO EMPLOYEE VALUES(3,'SEETA',2200);";
	private static String INSERT_EMPLOYEE4="INSERT INTO EMPLOYEE VALUES(4,'GEETA',5560);";
	private static String INSERT_EMPLOYEE5="INSERT INTO EMPLOYEE VALUES(5,'REETA',5230);";
    private static String UPDATE_EMPLOYEE="UPDATE EMPLOYEE SET SALARY=1000 WHERE ID=3;";
    private static String DELETE_EMPLOYEE="DELETE FROM EMPLOYEE WHERE ID=2;";
    private static String SELECT_EMPLOYEE="SELECT*FROM EMPLOYEE";
    
	public static void main(String[] args) {
	
		Connection con=null;
		//1.load and register the driver
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			//2. establish connection
			try {
				con=DriverManager.getConnection(URL ,USER_NAME,PASSWORD);
				System.out.println("connection establised "+con);
				
				//3.create statement object
				Statement stmt=con.createStatement();
				
				//4.send and execute the quary
				//boolean flag=stmt.execute(CREATE_EMPLOYEE);
			//	int count=0;
			//	count=stmt.executeUpdate(INSERT_EMPLOYEE1);
			//	System.out.println(count+" record affected");
				
			//	count=stmt.executeUpdate(INSERT_EMPLOYEE2);
			//	System.out.println(count+" record affected");
				
			//	count=stmt.executeUpdate(INSERT_EMPLOYEE3);
			//	System.out.println(count+" record affected");
				
			//	count=stmt.executeUpdate(INSERT_EMPLOYEE4);
			//	System.out.println(count+" record affected");
				
			//	count=stmt.executeUpdate(INSERT_EMPLOYEE5);
			//	System.out.println(count+" record affected");
				
//				int count=0;
//				count=stmt.executeUpdate(UPDATE_EMPLOYEE);
//				System.out.println(count+"updated employee");
//
//			int count=0;
//			count=stmt.executeUpdate(DELETE_EMPLOYEE);
//			System.out.println(count+"updated employee");
				
				
//5.process the result to get data				
ResultSet rs=stmt.executeQuery(SELECT_EMPLOYEE);
while(rs.next()) {
	System.out.println("ID="+rs.getInt(1)+",NAME="+rs.getString(2)+",SALARY="+rs.getInt(3));
	
}
				
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
