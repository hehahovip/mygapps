package com.hehaho.googleapp.demo.servlet;
import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

@SuppressWarnings("serial")
public class DemoServlet extends HttpServlet {
	
	private static Logger log = Logger.getLogger(DemoServlet.class.getName());
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/plain");
		resp.getWriter().println("Hello, world,this is the second demo !");
		
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		
//		HttpClient client = new HttpClient();
//		GetMethod method = new GetMethod("http://www.ibm.com");
//		client.executeMethod(method);
//		log.info(method.getResponseBodyAsString());
		
		if (user != null) {
            resp.setContentType("text/plain");
            resp.getWriter().println("Hello, " + user.getNickname());
            resp.getWriter().println("AuthDomain, " + user.getAuthDomain());
            resp.getWriter().println("NickName, " + user.getNickname());
        } else {
            resp.sendRedirect(userService.createLoginURL(req.getRequestURI()));
        }
	}
}
