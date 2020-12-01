package by.itacademy.newsproject.controller.command.impl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import by.itacademy.newsproject.controller.ParameterSession;
import by.itacademy.newsproject.controller.ParameterUrlController;
import by.itacademy.newsproject.controller.command.Command;
import by.itacademy.newsproject.entity.ParameterForm;
import by.itacademy.newsproject.controller.ParameterCommand;
import by.itacademy.newsproject.service.NewsService;
import by.itacademy.newsproject.service.ServiceException;
import by.itacademy.newsproject.service.ServiceProvider;

/**
 * COMMAND: DELETE_NEWS_SELECTED
 * 
 * This command performs only from other parent command: FIND_ALL_NEWS
 * so after this command executes, returns back to FIND_ALL_NEWS command.
 * Creates session with message if operation successfully performs.
 * 
 * Uses Post/Redirect/Get
 * 
 * @author akurlovich
 *
 */
public class DeleteNewsSelectedCommandImpl implements Command {
	private static final Logger logger = Logger.getLogger(DeleteNewsSelectedCommandImpl.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");

		String SELECTED_NEWS_ID = "selected_news_id";

		ServiceProvider serviceProvider = ServiceProvider.getInstance();
		NewsService newsService = serviceProvider.getNewsService();


		try {
			String[] selectedNews = request.getParameterValues(SELECTED_NEWS_ID);
			int[] selectedNewsIdInt = new int[selectedNews.length];

			for (int i = 0; i < selectedNews.length; i++) {
				selectedNewsIdInt[i] = Integer.parseInt(selectedNews[i]);
				logger.info("id=" + selectedNewsIdInt[i]);
			}
			newsService.deleteSelected(selectedNewsIdInt);

		} catch (NullPointerException e) {
			logger.error("Nothing to delete because no checkbox selected / " + e);
		} catch (NumberFormatException e) {
			logger.error("Error performing command " + request.getParameter(ParameterForm.COMMAND) + " / " + e);
		} catch (ServiceException e) {
			logger.error("Error performing command " + request.getParameter(ParameterForm.COMMAND) + " / " + e);
		}

		HttpSession session = request.getSession();
		session.setAttribute(ParameterSession.RESULT_OPERATION, "Group news deleted successfully");

		response.sendRedirect(ParameterUrlController.PREFIX_COMMAND + ParameterCommand.WELCOME_PAGE);
	}
}