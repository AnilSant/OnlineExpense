package com.operation;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import com.dbconnection.DbConnection;
import com.utility.Expense;

public class ExpenseOperations {
	public ArrayList<Expense> getProductDetails() {
		ArrayList<Expense> list = new ArrayList<Expense>();
		try {
			java.sql.Connection conn = DbConnection.getConnection();
			String query = "select * from productdetails";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				Expense expense = new Expense(rs.getInt(1), rs.getString(2),
						rs.getFloat(3), rs.getString(4),rs.getString(5));
				list.add(expense);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;

	}

	public Expense getProductById(int pid) {
		Expense product = new Expense();
		try {
			Connection conn = DbConnection.getConnection();
			String query = "select * from productdetails where pid=" + pid;
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {

				product.setPid(rs.getInt(1));
				product.setPname(rs.getString(2));
				product.setPprice(rs.getFloat(3));
				product.setPcatagory(rs.getString(4));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return product;

	}
	public boolean productAdd(Expense expense)
	{
		boolean status=false;
		try {
			Connection conn=DbConnection.getConnection();
			String query="insert into productdetails(`pid`, `pname`, `price`, `pcatagory`,`username`) values(?,?,?,?,?)";
			PreparedStatement ps=conn.prepareStatement(query);
			ps.setInt(1, expense.getPid());
			ps.setString(2, expense.getPname());
			ps.setFloat(3, expense.getPprice());
			ps.setString(4, expense.getPcatagory());
			ps.setString(5, expense.getUsername());
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
	public boolean productUpdate(Expense expense){
		boolean status=false;
		try {
			
			Connection conn=DbConnection.getConnection();
			String query="update productdetails set pname=?,price=?,pcatagory=? where pid=?";
			PreparedStatement ps=conn.prepareStatement(query);
			ps.setString(1, expense.getPname());
			ps.setFloat(2, expense.getPprice());
			ps.setString(3, expense.getPcatagory());
			ps.setInt(4, expense.getPid());
			int rows =ps.executeUpdate();
			if(rows>0){
				status=true;
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}return status;	
	}
public boolean productDetele(int pid){
	boolean status=false;
	try {
		Connection conn=DbConnection.getConnection();
		String query="delete from productdetails where pid=?";
		PreparedStatement ps=conn.prepareStatement(query);
		ps.setInt(1, pid);
		int rows =ps.executeUpdate();
		if(rows>0){
			
			status=true;
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return status;
	
}
public int getMaxPid(){
int pid = 0;

try {
	Connection conn=DbConnection.getConnection();
		String getPID="select max(pid) from productdetails";
						
		PreparedStatement ps =conn.prepareStatement(getPID);
		ResultSet rs=ps.executeQuery();
	if(rs.next())
		{
		
		pid=rs.getInt(1);
		pid=pid+1;
		}
		else
		{
		 pid=1;
		}
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
return pid;
}
}
