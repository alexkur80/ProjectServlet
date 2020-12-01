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
 * COMMAND: find_news
 * 
 * This command executed only from other Command: FIND_ALL_NEWS
 * so after current command executed, returns back to FIND_ALL_NEWS.
 * 
 * CURRENT_COMMAND Session parameter uses only if user change Locale, the same page should be displayed.
 * 
 * @author akurlovich
 *
 */
public class FindNewsCommandImpl implements Command {
	private static final Logger logger = Logger.getLogger(FindNewsCommandImpl.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");

		News news = new News();

		ServiceProvider serviceProvider = ServiceProvider.getInstance();
		NewsService newsService = serviceProvider.getNewsService();
		
		Integer id = 0;
		
		try {
			id = Integer.parseInt(request.getParameter(ParameterForm.ID));
			news = newsService.select(id);
		} catch (NumberFormatException e) {
			logger.error("Error, ID is not number, coudn't find News from DB by ID: ", e);
			response.sendRedirect("error.jsp");
		} catch (ServiceException e) {

		}

		request.setAttribute("news", news);

		HttpSession session = request.getSession();
		
		session.setAttribute(ParameterSession.CURRENT_COMMAND, ParameterCommand.FIND_NEWS + "&id=" + id);

		RequestDispatcher requestDispatcher = request.getRequestDispatcher(ParameterUrlController.SELECT_NEWS);
		requestDispatcher.forward(request, response);
	}
}