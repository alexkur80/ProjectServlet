package by.itacademy.newsproject.controller.command.impl;

import java.io.IOException;
import java.time.LocalDate;
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
 * COMMAND: UPDATE_NEWS_SAVE
 * 
 * This COMMAND writes(updates) news data from DB
 *  
 * This Command executes only from other Command: FIND_ALL_NEWS
 *  
 * CURRENT_COMMAND Session parameter uses only if user change Locale, this Session
 * parameter has Command which execute after locale changes.
 * 
 * Uses Post/Redirect/Get
 *   
 * @author akurlovich
 * 
 */
public class UpdateNewsSaveCommandImpl implements Command {
	private static final Logger logger = Logger.getLogger(UpdateNewsSaveCommandImpl.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ServiceException {
		response.setContentType("text/html; charset=UTF-8");

		LocalDate date;
		String section;
		String author;
		String brief;
		String content;

		date = ParameterForm.DATE;
		section = request.getParameter(ParameterForm.SECTION);
		author = request.getParameter(ParameterForm.AUTHOR);
		brief = request.getParameter(ParameterForm.BRIEF);
		content = request.getParameter(ParameterForm.CONTENT);

		ServiceProvider serviceProvider = ServiceProvider.getInstance();
		NewsService newsService = serviceProvider.getNewsService();

		News news = new News(date, section, author, brief, content);

		try {
			Integer id = 0;
			id = Integer.parseInt(request.getParameter(ParameterForm.ID));
			newsService.update(news, id);
		} catch (NumberFormatException e) {
			logger.error("ID is not digit " + e);
		} catch (ServiceException e) {
			logger.error("Error performing command " + request.getParameter(ParameterForm.COMMAND) + " / " + e);
		}

		HttpSession session = request.getSession();
		session.setAttribute(ParameterSession.RESULT_OPERATION, ParameterSession.RESULT_OPERATION_MSG_UPDATE_SUCESS);
		session.setAttribute(ParameterSession.CURRENT_COMMAND, ParameterCommand.WELCOME_PAGE);

		response.sendRedirect(ParameterUrlController.PREFIX_COMMAND + ParameterCommand.WELCOME_PAGE);

	}
}