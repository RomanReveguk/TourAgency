package myServlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import travelagency.dao.UserDao;
import travelagency.dao.impl.DefaultUserDao;
import travelagency.modelsdata.UserData;


@WebServlet(name = "ShowUserByIdServlet", urlPatterns= {"/ShowUserByIdServlet"})
public class ShowUserByIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	
	private UserDao userDao;
	{
		userDao= DefaultUserDao.getInstance();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserData user = userDao.getUserById(Integer.parseInt(request.getParameter("user_id")));
		request.setAttribute("user", user);
		request.getRequestDispatcher("WEB-INF/views/showUser.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
