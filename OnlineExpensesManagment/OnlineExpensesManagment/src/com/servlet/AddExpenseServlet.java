package com.servlet;

import java.io.IOException;
import java.sql.Connection;

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
 * Servlet implementation class AddProductServlet
 */
@WebServlet("/AddExpenseServlet")
public class AddExpenseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddExpenseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	Connection conn=DbConnection.getConnection();
	ExpenseOperations productOperations=new ExpenseOperations();
	int pid=productOperations.getMaxPid();
	
	HttpSession session=request.getSession();
	
	Expense expense=new Expense(pid,request.getParameter("pname"), Float.parseFloat(request.getParameter("pprice")), request.getParameter("pcateory"),(String) session.getAttribute("uname"));
    boolean status=productOperations.productAdd(expense);
	if(status)
	{
		//response.sendRedirect("DisplayExpenseDetailsServlet");

		RequestDispatcher rd = request
				.getRequestDispatcher("/DisplayExpenseDetailsServlet");
		request.setAttribute("type", "admin");
		rd.forward(request, response);
	}
//	else {
//		RequestDispatcher rd = request
//				.getRequestDispatcher("/DisplayExpenseDetailsServlet");
//		request.setAttribute("type", "admin");
//		rd.forward(request, response);
//	}
	}

	
}
