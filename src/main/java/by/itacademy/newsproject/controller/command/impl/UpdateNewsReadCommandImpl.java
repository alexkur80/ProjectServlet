package by.itacademy.newsproject.controller.command.impl;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import by.itacademy.newsproject.entity.News;
import by.itacademy.newsproject.entity.ParameterForm;
import by.itacademy.newsproject.controller.ParameterSession;
import by.itacademy.newsproject.controller.ParameterUrlController;
import by.itacademy.newsproject.controller.command.Command;
import by.itacademy.newsproject.controller.ParameterCommand;
import by.itacademy.newsproject.service.NewsService;
import by.itacademy.newsproject.service.ServiceException;
import by.itacademy.newsproject.service.ServiceProvider;

/**
 * COMMAND: UPDATE_NEWS
 * 
 * This COMMAND works together with UPDATE_NEWS_SAVE and
 * give form with News from DB to update. Once Submit button pressed,
 * Command UPDATE_NEWS_BY_ID_ executes to save changes in DB.
 * 
 * This Command executes only from other Command: FIND_ALL_NEWS
 * so after current command executes, returns back to FIND_ALL_NEWS.
 * 
 * CURRENT_COMMAND Session parameter uses only if user change Locale, this Session
 * parameter has Command which execute after locale changes.
 * 
 * @author akurlovich
 * 
 */
public class UpdateNewsReadCommandImpl implements Command {
	private static final Logger logger = Logger.getLogger(UpdateNewsReadCommandImpl.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ServiceException {
		response.setContentType("text/html; charset=UTF-8");

		Integer id = 0;

		News news = new News();

		ServiceProvider serviceProvider = ServiceProvider.getInstance();
		NewsService newsService = serviceProvider.getNewsService();

		try {
			id = Integer.parseInt(request.getParameter(ParameterForm.ID));
			news = newsService.select(id);
		} catch (NumberFormatException e) {
			logger.error("ID is not number / " + e);
		} catch (ServiceException e) {
			logger.error("Error performing command " + request.getParameter(ParameterForm.COMMAND) + " / " + e);
		}

		request.setAttribute("news", news);
		request.setAttribute("command", ParameterCommand.UPDATE_NEWS_SAVE);

		HttpSession session = request.getSession();
		session.setAttribute(ParameterSession.CURRENT_COMMAND, ParameterCommand.UPDATE_NEWS_READ + "&id=" + id);

		RequestDispatcher requestDispatcher = request
				.getRequestDispatcher(ParameterUrlController.UPDATE_NEWS_READ);
		requestDispatcher.forward(request, response);

	}
}