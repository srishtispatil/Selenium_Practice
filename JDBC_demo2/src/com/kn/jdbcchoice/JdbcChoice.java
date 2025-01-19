package com.kn.jdbcchoice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class JdbcChoice {
	public static final String URL="jdbc:mysql://localhost:3306/JDBC";
	public static final String USER_NAME="root";
	public static final String PASSWORD="root";
	public static final String INSERT_EMP="INSERT INTO EMPLOYEE VALUES(?,?,?);";
	public static Connection con=null;
	
	public static final String UPDATE_EMP_NAME="UPDATE EMPLOYEE SET NAME=? WHERE ID=? ;";
	public static final String UPDATE_EMP_SALARY="UPDATE EMPLOYEE SET SALARY=? WHERE ID=? ;";
	public static final String DELETE_EMP="DELETE FROM  EMPLOYEE WHERE ID=? ;";
	public static final String SELECT_EMP="SELECT *FROM EMPLOYEE where id=?;";
	public static void main(String[] args) {
		
		//1.
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			//2.
			
			con=DriverManager.getConnection(URL, USER_NAME, PASSWORD);
			
			Scanner sc=new Scanner(System.in);
			System.out.println("Chose any 1 option");
			System.out.println("1.Insert Data");
			System.out.println("2.Update data");
			System.out.println("3.Delete data");
			System.out.println("4.select data");
			int num=sc.nextInt();
			
			switch (num){
			case 1:insert();
				break;
			
			case 2:update();
			break;
			case 3: delete();
			break;
			case 4: select();
			break;
			
			default:
				System.out.println("chose the right option");
			}
			
			
			
			
		
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	
	private static void select() {
		Scanner sc=new Scanner(System.in);
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement(SELECT_EMP);
			System.out.println("enter emp id");
			int empId =sc.nextInt();
			pstmt.setInt(1, empId);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()) {
				System.out.println("ID="+rs.getInt(1)+",NAME="+rs.getString(2)+",SALARY="+rs.getInt(3));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private static void delete() {
		Scanner sc=new Scanner(System.in);
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement(DELETE_EMP);
			System.out.println("enter emp id");
			int empId =sc.nextInt();
			pstmt.setInt(1, empId);
			int count=pstmt.executeUpdate();
			System.out.println(count+"records effected");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private static void update() {
		Scanner sc=new Scanner(System.in);
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement(UPDATE_EMP_NAME);
			System.out.println("enter emp id");
			int empId =sc.nextInt();
			System.out.println("enter emp name");
			String empName=sc.next();
			pstmt.setInt(2, empId);
			pstmt.setString(1, empName);
			int count=pstmt.executeUpdate();
			System.out.println(count+"records effected");
			
			 pstmt =con.prepareStatement(UPDATE_EMP_SALARY);
			System.out.println("enter emp id");
			int empId1 =sc.nextInt();
		System.out.println("enter emp salary");
			int empSalary=sc.nextInt();
			
			pstmt.setInt(2, empId1);
			pstmt.setInt(1, empSalary);
			int count1=pstmt.executeUpdate();
			System.out.println(count+"records effected");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
	private static void insert() {
		Scanner sc=new Scanner(System.in);
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement(INSERT_EMP);
			
			System.out.println("enter emp id");
			int empId =sc.nextInt();
			System.out.println("enter emp name");
			String empName=sc.next();
			System.out.println("enter emp salary");
			int empSalary=sc.nextInt();
			
			pstmt.setInt(1, empId);
			pstmt.setString(2, empName);
			pstmt.setInt(3, empSalary);
			
			int count=pstmt.executeUpdate();
			System.out.println(count+"records effected");
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
		


