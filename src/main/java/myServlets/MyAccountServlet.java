package myServlets;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import travelagency.dao.impl.DefaultUserDao;
import travelagency.modelsdata.TourData;
import travelagency.modelsdata.UserData;


@WebServlet(name= "MyAccountServlet", urlPatterns= {"/myaccount"})
public class MyAccountServlet extends HttpServlet {


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UserData loggedUser = (UserData) request.getSession().getAttribute("loggerInUser");
		String loggedUserName = loggedUser.getUser_name();
		String loggedUserEmail = loggedUser.getMail();
		request.setAttribute("loggedUserName", loggedUserName);
		request.setAttribute("loggedUserEmail", loggedUserEmail);
		request.getRequestDispatcher("WEB-INF/views/myaccount.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
