package by.itacademy.newsproject.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.itacademy.newsproject.controller.ParameterSession;
import by.itacademy.newsproject.controller.ParameterUrlController;
import by.itacademy.newsproject.controller.command.Command;
import by.itacademy.newsproject.controller.ParameterCommand;

/**
 * Command uses to redirect to different pages, depends on CURRENT_COMMAND 
 * {@link by.itacademy.newsproject.controller.ParameterSession} 
 * 
 * <p> 
 * 
 * 
 * COMMAND: WELCOME_PAGE
 * 
 * @author akurlovich
 * 
 */
public class WelcomeCommandImpl implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");

		HttpSession session = request.getSession();

		String currentCommand = (String) session.getAttribute(ParameterSession.CURRENT_COMMAND);

		if (ParameterCommand.FIND_ALL_NEWS.equals(currentCommand)) {

			response.sendRedirect(ParameterUrlController.PREFIX_COMMAND + ParameterCommand.FIND_ALL_NEWS);

		}

		else {

			response.sendRedirect(ParameterUrlController.PREFIX_COMMAND + ParameterCommand.MAIN_PAGE);
		}
	}
}