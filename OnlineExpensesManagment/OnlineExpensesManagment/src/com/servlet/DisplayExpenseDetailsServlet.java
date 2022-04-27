package com.servlet;

import java.io.IOException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.operation.ExpenseOperations;
import com.operation.UserOperation;
import com.dbconnection.DbConnection;


import com.utility.Expense;

/**
 * Servlet implementation class DisplayProductDetailsServlet
 */
@WebServlet("/DisplayExpenseDetailsServlet")
public class DisplayExpenseDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DisplayExpenseDetailsServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	Connection conn = null;

	@Override
	public void init() throws ServletException {
		conn = DbConnection.getConnection();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		 
		
		ExpenseOperations expense1 = new ExpenseOperations();

		ArrayList<Expense> list = expense1.getProductDetails();
		HttpSession session = req.getSession();
		String s=(String) session.getAttribute("uname");
		session.setAttribute("productlistList", list);
		session.setAttribute("uname", s); //sending name to /DisplayProductDetails.jsp
		
		RequestDispatcher rd = req.getRequestDispatcher("/DisplayExpenseDetails.jsp");
		
		rd.forward(req, resp);
		
	}

	@Override
	public void destroy() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
