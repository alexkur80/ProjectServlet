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
 * COMMAND: CREATE_NEWS
 * 
 * Accepts data from JSP file: section, author, brief, content. ID and DATE
 * generates automatically. Date - current server date.
 * 
 * <p>Puts data to DB and returns status result message (RESULT_OPERATION). 
 * 
 * <p>Receives data from JSP form page, creates bean
 * 
 * <p>Transfers bean to Service layer 
 * Creates session with message if operation successfully performed 
 * 
 * <p> Session parameter RESULT_OPERATION to save result message  if operation performed successfully
 * 
 * <p>Session parameter  CURRENT_COMMAND needs 
 * if locale changes on this page, the same page should be displayed. Our case - WELCOME_PAGE because
 * operation executed successfully.
 * 
 * <p>Uses Post/Redirect/Get 
 * 
 * @author akurlovich
 *
 */
public class CreateNewsCommandImpl implements Command {
	private static final Logger logger = Logger.getLogger(CreateNewsCommandImpl.class);

	public CreateNewsCommandImpl() {
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

		HttpSession session = request.getSession();

		try {
			newsService.create(news);

			session.setAttribute(ParameterSession.RESULT_OPERATION,
					ParameterSession.RESULT_OPERATION_MSG_CREATE_SUCCESS);
			session.setAttribute(ParameterSession.CURRENT_COMMAND, ParameterCommand.WELCOME_PAGE);
			
		} catch (ServiceException e) {
			session.setAttribute(ParameterSession.RESULT_OPERATION,
					ParameterSession.RESULT_OPERATION_MSG_CREATE_FAILED);
			logger.error("Error performing command " + request.getParameter(ParameterForm.COMMAND) + " / " + e);
		}
		response.sendRedirect(ParameterUrlController.PREFIX_COMMAND + ParameterCommand.WELCOME_PAGE);
	}
}