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


@WebServlet(name = "DeleteUserServlet", urlPatterns= {"/DeleteUserServlet"})
public class DeleteUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public DeleteUserServlet() {
        super();
    }
    private UserDao userDao;

	{
		userDao = DefaultUserDao.getInstance();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/views/deleteUser.jsp").forward(request, response);
	}

//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		int userId = Integer.parseInt(request.getParameter("user_id"));
//		UserDao userDao = DefaultUserDao.getInstance();
//		userDao.deleteUserById(userId);
//		HttpSession session = request.getSession();
//		session.invalidate();// удаляем сессию и переходим на страниу логина
//
//		
//	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		int userId = Integer.parseInt(request.getParameter("user_id"));
		userDao.deleteUserById(userId);	

	}
	
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
}
