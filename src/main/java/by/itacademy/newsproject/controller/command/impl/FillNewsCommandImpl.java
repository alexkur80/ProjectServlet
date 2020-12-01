package by.itacademy.newsproject.controller.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.itacademy.newsproject.controller.ParameterSession;
import by.itacademy.newsproject.controller.ParameterUrlController;
import by.itacademy.newsproject.controller.command.Command;
import by.itacademy.newsproject.controller.ParameterCommand;

/**
 * COMMAND: FILL_NEWS
 * 
 * Forwards to JSP page with form to fill data which we need to save into DB.
 * This COMMAND works together with CREATE_NEWS
 * This COMMAND FillNewsCommandImpl is independent because it is situated inside /WEB-INF/jsp
 * and direct access from url not allowed.  
 * 
 * CURRENT_COMMAND Session parameter uses only if user change Locale, the same page should be displayed
 * (Form to fill News data).
 * 
 * @author akurlovich
 *
 */
public class FillNewsCommandImpl implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		
		HttpSession session = request.getSession();
		session.setAttribute(ParameterSession.CURRENT_COMMAND, ParameterCommand.FILL_NEWS);
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(ParameterUrlController.FILL_NEWS);
		requestDispatcher.forward(request, response);
	}
}