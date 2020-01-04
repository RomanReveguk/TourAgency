package travelagency.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import travelagency.modelsdata.UserData;


@WebFilter(servletNames = { "MyAccountServlet" })
public class MyAccountFilter implements Filter {

	public static final String LOGGER_IN_USER_KEY = "loggerInUser";

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpSession session = ((HttpServletRequest) request).getSession(false);
		
		
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		HttpServletResponse httpResponse = (HttpServletResponse)response;

		if (session != null && session.getAttribute(LOGGER_IN_USER_KEY) != null) {
		chain.doFilter(request, response);
	
		} else {
			
			httpResponse.sendRedirect(httpRequest.getServletContext().getContextPath() + "/login");
		}
	}
		

//		HttpServletRequest httpRequest = (HttpServletRequest)request;
//		HttpServletResponse httpResponse = (HttpServletResponse)response;
//		UserData loggedUser = (UserData) httpRequest.getSession().getAttribute("loggedUser");
//		
//		 if (loggedUser != null) {			 
//			 chain.doFilter(request, response);	            	 
//	     } else 
//		httpResponse.sendRedirect(httpRequest.getServletContext().getContextPath() + "/login");
//	   // 	 httpResponse.sendRedirect("login");
//	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
