package com.lti.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lti.dao.UserDao;
import com.lti.model.User;

@WebServlet("/register")
public class UserController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private UserDao userDao;
	public void init() {
		userDao=new UserDao();
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		register(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	 
		resp.sendRedirect("register/register.jsp");
	}
	 private void register(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
	        

	        User employee = new User();
	        employee.setFirstName(request.getParameter("firstName"));
	        employee.setLastName(request.getParameter("lastName"));
	        employee.setUsername(request.getParameter("username"));
	        employee.setPassword(request.getParameter("password"));

	        try {
	            int result = userDao.registerEmployee(employee);
	            if (result == 1) {
	                request.setAttribute("NOTIFICATION", "User Registered Successfully!");
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        RequestDispatcher dispatcher = request.getRequestDispatcher("register/register.jsp");
	        dispatcher.forward(request, response);
	    }

}
