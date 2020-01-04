package myServlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import travelagency.modelsdata.UserData;


@WebServlet(name= "AccountSetting", urlPatterns= {"/AccountSetting"})
public class AccountSetting extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		UserData loggedUser = (UserData) request.getSession().getAttribute("loggerInUser");
		request.getRequestDispatcher("WEB-INF/views/accountSetting.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
