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
import static travelagency.filters.MyAccountFilter.*;


@WebServlet(name= "LoginServlet", urlPatterns= {"/login"})
public class LoginServlet extends HttpServlet {
	
	private UserDao userDao;
	{
		userDao = DefaultUserDao.getInstance();
	}
	// перенаправляет к жсп
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//получаем из объекта запроса объект диспетчера запросов, куда передаем адрес jsp странички, которой мы хотим передать управление;
		request.getRequestDispatcher("WEB-INF/views/login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
		String password = request.getParameter("password");

		if(login == null || login.isEmpty() || password == null || password.isEmpty()) {
			response.sendRedirect(getServletContext().getContextPath()+"/login");// переходим на страницу Логина если логин и пароль пустые 
		} else {
			UserData user = userDao.getUserPassword(login); 
			if(user.getPassword().equals(password)) {
				
				HttpSession session = request.getSession();
				session.setAttribute(LOGGER_IN_USER_KEY,user);

				//если пароль совпадает то переходим на homePage
				response.sendRedirect(getServletContext().getContextPath()+"/myaccount");
			}
			else {
				// если пароль не совпадает то переходим на стр. регистрации registerUserPage
				response.sendRedirect(getServletContext().getContextPath()+"/registerUserPage");
			}
		}
	
	}
}
