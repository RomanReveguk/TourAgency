package myServlets;

import static travelagency.filters.MyAccountFilter.LOGGER_IN_USER_KEY;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import travelagency.dao.UserDao;
import travelagency.dao.impl.DefaultUserDao;
import travelagency.modelsdata.UserData;

@WebServlet(name = "RegisterUserPageServlet", urlPatterns= {"/registeruserpage"})
public class RegisterUserPageServlet extends HttpServlet {
	
	private UserDao userDao;

	{
		userDao = DefaultUserDao.getInstance();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/views/registerUserPage.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		UserData userData = new UserData();
		int roleId=1;
		userData.setUser_name(request.getParameter("firstName"));
		userData.setUser_surname(request.getParameter("surName"));
		userData.setUser_date_of_birth((request.getParameter("dateBirth")));
		userData.setMail(request.getParameter("mail"));
		userData.setRoleId(roleId);
		
		String password = request.getParameter("password");
		String passwordCheck = request.getParameter("passwordCheck");

		if (password.equals(passwordCheck)) {
			userData.setPassword(passwordCheck);
			userDao.setUser2(userData);
			response.sendRedirect(getServletContext().getContextPath() + "/login");
		} else {
			response.sendRedirect(getServletContext().getContextPath() + "/error");
		}	
	}
}
