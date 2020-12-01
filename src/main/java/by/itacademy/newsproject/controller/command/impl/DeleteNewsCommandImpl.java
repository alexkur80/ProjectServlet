package by.itacademy.newsproject.controller.command.impl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import by.itacademy.newsproject.entity.ParameterForm;
import by.itacademy.newsproject.controller.ParameterSession;
import by.itacademy.newsproject.controller.ParameterUrlController;
import by.itacademy.newsproject.controller.command.Command;
import by.itacademy.newsproject.controller.ParameterCommand;
import by.itacademy.newsproject.service.NewsService;
import by.itacademy.newsproject.service.ServiceException;
import by.itacademy.newsproject.service.ServiceProvider;

/**
 * COMMAND: DELETE_NEWS
 * 
 * This command performs only from other Command page: FIND_ALL_NEWS
 * so after DELETE_NEWS command executes, returns back to FIND_ALL_NEWS.
 * Creates session with message if operation successfully performs.
 * 
 * <p>WELCOME_PAGE page choose what page redirect, depends on CURRENT_COMMAND Session parameter.
 * 
 * Uses Post/Redirect/Get
 * 
 * @author akurlovich
 *
 */
public class DeleteNewsCommandImpl implements Command {
	private static final Logger logger = Logger.getLogger(DeleteNewsCommandImpl.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");

		HttpSession session = request.getSession();

		ServiceProvider serviceProvider = ServiceProvider.getInstance();
		NewsService newsService = serviceProvider.getNewsService();

		try {
			Integer id = Integer.parseInt(request.getParameter(ParameterForm.ID));
			newsService.delete(id);

			session.setAttribute(ParameterSession.RESULT_OPERATION,
					ParameterSession.RESULT_OPERATION_MSG_DELETE_SUCESS);
		} catch (NumberFormatException e) {
			logger.error("Error, ID is not digit / " + e);
		} catch (ServiceException e) {
			logger.error("Error performing command " + request.getParameter(ParameterForm.COMMAND) + " / " + e);
		}
		response.sendRedirect(ParameterUrlController.PREFIX_COMMAND + ParameterCommand.WELCOME_PAGE);
	}
}