package com.operation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.dbconnection.DbConnection;
import com.utility.Expense;
import com.utility.User;
import com.utility.UserRegistration;

public class UserRegistrations {
	public boolean userAdd(UserRegistration userInfo)
	{
		boolean status=false;
		try {
			Connection conn=DbConnection.getConnection();
			String query="insert into logindata values(?,?,?,?)";
			PreparedStatement ps=conn.prepareStatement(query);
			ps.setString(1, userInfo.getUserName());
			ps.setString(2, userInfo.getPassword());
			ps.setString(3,userInfo.getRole());
			ps.setString(4, userInfo.getName());
			int rows=ps.executeUpdate();
			if(rows>0)
			{
				status=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return status;
		
	}
	public boolean validateUser(String uname)
	{
	boolean status=false;
	try {
		Connection conn=DbConnection.getConnection();
		String query = "select username from logindata where username=?";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setString(1, uname);
		//ps.setString(2, user.getPassword());
		ResultSet rs = ps.executeQuery();
		if(rs.next())
		{
			status=true;
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return status;
	}

}
