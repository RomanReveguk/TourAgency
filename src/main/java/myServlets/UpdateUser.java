package myServlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import travelagency.dao.UserDao;
import travelagency.dao.impl.DefaultUserDao;
import travelagency.modelsdata.UserData;

public class UpdateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserDao userDao;

	{
		userDao = DefaultUserDao.getInstance();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("WEB-INF/views/showUser.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int roleId=1;
		UserData userData=new UserData();
		userData.setUser_id(Integer.parseInt(request.getParameter("user_id")));
		userData.setUser_name(request.getParameter("firstName"));
		userData.setUser_surname(request.getParameter("surName"));
		userData.setUser_date_of_birth(request.getParameter("dateBirth"));
		userData.setMail(request.getParameter("mail"));
		userData.setPassword(request.getParameter("password"));
		userData.setRoleId(roleId);
		userDao.updateUser2(userData);
		}
	}



