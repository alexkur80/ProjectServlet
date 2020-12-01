package by.itacademy.newsproject.controller.command.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
 * COMMAND: FIND_ALL_NEWS
 * 
 * Outputs list all news from DB
 * 
 * CURRENT_COMMAND Session parameter uses only if user change Locale, the same page should be displayed. 
 * This Session parameter uses if COMMAND = DELETE_NEWS or COMMAND = DELETE_NEWS_SELECTED
 * And if operation executed successfully, returns to FIND_ALL_NEWS page.
 * 
 * CURRENT_COMMAND Session is FIND_ALL_NEWS page because if we execute Delete and Group delete operations,
 * the same page should be displayed.
 * 
 * @author akurlovich
 *
 */
public class FindAllNewsCommandImpl implements Command {
	private static final Logger logger = Logger.getLogger(FindAllNewsCommandImpl.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		;
		final String HIDE_BUTTON = "hideButton";
		final String DISPLAY_BUTTON = "displayButton";

		List<News> news = new ArrayList<>();
		
		ServiceProvider serviceProvider = ServiceProvider.getInstance();
		NewsService newsService = serviceProvider.getNewsService();

		HttpSession session = request.getSession();

		try {
			news = newsService.selectAll();
			if (news.size() == 0) {
				session.setAttribute(ParameterSession.KEY_EMPTY_NEWS_TABLE, HIDE_BUTTON);
				logger.info("hide button");
			} else {
				session.setAttribute(ParameterSession.KEY_EMPTY_NEWS_TABLE, DISPLAY_BUTTON);
				logger.info("open button");
			}
		} catch (ServiceException e) {
			logger.error("Error performing command " + request.getParameter(ParameterForm.COMMAND) + " / " + e);
		}

		request.setAttribute("news", news);

		session.setAttribute(ParameterSession.CURRENT_COMMAND, ParameterCommand.FIND_ALL_NEWS);

		RequestDispatcher requestDispatcher = request.getRequestDispatcher(ParameterUrlController.SELECT_ALL_NEWS);
		requestDispatcher.forward(request, response);
	}
}