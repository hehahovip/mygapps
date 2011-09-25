/**
 * 
 */
package com.hehaho.googleapp.demo.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hehaho.googleapp.demo.jdo.Person;
import com.hehaho.googleapp.demo.jdo.JDOServcie;

/**
 * @author Kevin.Wang
 *
 */
public class JDOTestServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Logger log = Logger.getLogger(JDOTestServlet.class.getName());
	
	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1)
			throws ServletException, IOException {

		JDOServcie s = new JDOServcie();
		s.savePerson();
	}

}
