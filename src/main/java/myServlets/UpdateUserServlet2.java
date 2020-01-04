package myServlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import travelagency.dao.UserDao;
import travelagency.dao.impl.DefaultUserDao;
import travelagency.modelsdata.UserData;


@WebServlet(name = "UpdateUserServlet2", urlPatterns= {"/UpdateUserServlet2"})
public class UpdateUserServlet2 extends HttpServlet {
	
	private UserDao userDao;
	{
		userDao= DefaultUserDao.getInstance();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("user",userDao.getUserById(Integer.parseInt(request.getParameter("userId"))));
		request.getRequestDispatcher("WEB-INF/views/updateUser.jsp").forward(request, response);
	} 

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int roleId=1;
		UserData userData=new UserData();
		userData.setUser_id(Integer.parseInt(request.getParameter("userId")));
	
		userData.setUser_name(request.getParameter("firstName"));
		userData.setUser_surname(request.getParameter("surName"));
		userData.setUser_date_of_birth(request.getParameter("dateBirth"));
		userData.setMail(request.getParameter("mail"));
		userData.setPassword(request.getParameter("password"));
		userData.setRoleId(roleId);
		userDao.updateUser2(userData);
		request.getRequestDispatcher("WEB-INF/views/myaccount.jsp").forward(request, response);
	}

}
