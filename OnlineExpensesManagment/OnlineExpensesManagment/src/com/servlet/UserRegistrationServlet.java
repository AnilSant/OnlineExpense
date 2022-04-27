package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dbconnection.DbConnection;
import com.operation.ExpenseOperations;
import com.operation.UserOperation;
import com.operation.UserRegistrations;
import com.utility.Expense;
import com.utility.UserRegistration;

/**
 * Servlet implementation class UserRegistrationServlet
 */
@WebServlet("/UserRegistration")
public class UserRegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserRegistrationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
/*	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}/*

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    String uname = request.getParameter("uname");
    UserRegistrations useReg=new UserRegistrations();
    boolean status=useReg.validateUser(uname);
    
    if(status!=true) {
	Connection conn=DbConnection.getConnection();
	UserRegistrations userOperations=new UserRegistrations();
	//int pid=productOperations.getMaxPid();
	UserRegistration userInfo=new UserRegistration(request.getParameter("uname"), request.getParameter("password"), request.getParameter("type"),request.getParameter("name"));
	boolean status1=userOperations.userAdd(userInfo);
	if(status1)
	{
		//response.sendRedirect("DisplayProductDetailsServlet");

		RequestDispatcher rd = request
				.getRequestDispatcher("/DisplayProductDetailsServlet");
		request.setAttribute("type", "admin");
		rd.forward(request, response);
	}
	}
    else {
    	response.sendRedirect("AlreadyExists.jsp");
    	
    }
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		doGet(request, response);
	}

}
