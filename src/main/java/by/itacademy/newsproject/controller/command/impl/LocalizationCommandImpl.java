package by.itacademy.newsproject.controller.command.impl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.itacademy.newsproject.controller.ParameterSession;
import by.itacademy.newsproject.controller.ParameterUrlController;
import by.itacademy.newsproject.controller.command.Command;
 
/**
 * COMMAND: LOCALIZATION
 * 
 * Returns locale and Command
 * 
 * <p>CURRENT_COMMAND - required Session attribute, uses to execute this command after locale changes.
 * Required parameter Locale to save and store in Session.
 * 
 * <p>Once CURRENT_COMMAND is not available (Session is empty):  
 * CURRENT_COMMAND = {@link by.itacademy.newsproject.controller.command.impl.MainPageCommandImpl} 
 * It is required because Command LOCALIZATION require this Session attribute.
 * 
 * @author akurlovich
 *
 */
public class LocalizationCommandImpl implements Command {
	public static final String LOCALE = "locale";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");

		String currentCommand;
		String locale;

		HttpSession session = request.getSession();

		currentCommand = (String) session.getAttribute(ParameterSession.CURRENT_COMMAND);

		if (currentCommand == null) {
			currentCommand = ParameterSession.CURRENT_COMMAND_DEFAULT;
		}

		locale = request.getParameter(LOCALE);

		session.setAttribute(LOCALE, locale);

		response.sendRedirect(ParameterUrlController.PREFIX_COMMAND + currentCommand);

	}
}