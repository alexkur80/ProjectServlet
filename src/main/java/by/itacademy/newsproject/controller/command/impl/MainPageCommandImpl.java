package by.itacademy.newsproject.controller.command.impl;


import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.itacademy.newsproject.controller.ParameterUrlController;
import by.itacademy.newsproject.controller.command.Command;

/**
 * COMMAND: MAIN_PAGE
 * 
 * Start application point
 * 
 * @author akurlovich
 *
 */
public class MainPageCommandImpl implements Command {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(ParameterUrlController.MAIN_PAGE);
		requestDispatcher.forward(request, response);
		
	}
}