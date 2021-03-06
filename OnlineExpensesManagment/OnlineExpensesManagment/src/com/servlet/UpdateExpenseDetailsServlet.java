package com.servlet;

import java.io.IOException;
import java.sql.Connection;

import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dbconnection.DbConnection;

import com.operation.ExpenseOperations;

import com.utility.Expense;

/**
 * Servlet implementation class UpdateProductDetailsServlet
 */
@WebServlet("/UpdateExpenseDetailsServlet")
public class UpdateExpenseDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateExpenseDetailsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    private Connection conn=null;
@Override
public void init() throws ServletException {
 conn =DbConnection.getConnection();
}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			HttpSession session=request.getSession();
			Expense product=new Expense(Integer.parseInt(request.getParameter("pid")), request.getParameter("pname"), Float.parseFloat(request.getParameter("pprice")), request.getParameter("pcatagory"),(String) session.getAttribute("uname"));
			
			ExpenseOperations expenseOperations=new ExpenseOperations();
			boolean status=expenseOperations.productUpdate(product);
			if(status)
			{
				RequestDispatcher rd = request
						.getRequestDispatcher("/DisplayExpenseDetailsServlet");
				request.setAttribute("type", "admin");
				rd.forward(request, response);
			}
 
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	
@Override
public void destroy() {
	if(conn!=null){
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
}
