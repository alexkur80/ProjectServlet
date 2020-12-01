package by.itacademy.newsproject.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.itacademy.newsproject.entity.ParameterForm;
import by.itacademy.newsproject.controller.command.Command;
import by.itacademy.newsproject.controller.command.CommandProvider;
import by.itacademy.newsproject.service.ServiceException;
	
/**
 * Controller  - start point for ANY COMMAND REQUEST 
 * (Analogy as DispatcherServlet in Spring MVC)
 * 
 * @author akurlovich
 *
 */
public class Controller extends HttpServlet {
	private static final Logger logger = Logger.getLogger(Controller.class);

	private final CommandProvider provider = new CommandProvider();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	protected void process(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String commandName;
		Command command;

		commandName = request.getParameter(ParameterForm.COMMAND);

		logger.info("Command: " + commandName);

		command = provider.getCommand(commandName);
		try {
			request.setAttribute("command", command);
			command.execute(request, response);
		} catch (ServletException | IOException | ServiceException e) {
			logger.error("Problem executing command " + commandName + " / " + e);
			response.sendRedirect(ParameterUrlController.ERROR_PAGE);
		}
	}
}