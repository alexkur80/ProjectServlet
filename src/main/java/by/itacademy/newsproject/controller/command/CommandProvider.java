package by.itacademy.newsproject.controller.command;

import java.util.HashMap;
import java.util.Map;

import by.itacademy.newsproject.controller.ParameterCommand;
import by.itacademy.newsproject.controller.command.impl.CreateNewsCommandImpl;
import by.itacademy.newsproject.controller.command.impl.DeleteNewsCommandImpl;
import by.itacademy.newsproject.controller.command.impl.DeleteNewsSelectedCommandImpl;
import by.itacademy.newsproject.controller.command.impl.FillNewsCommandImpl;
import by.itacademy.newsproject.controller.command.impl.FindAllNewsCommandImpl;
import by.itacademy.newsproject.controller.command.impl.FindNewsCommandImpl;
import by.itacademy.newsproject.controller.command.impl.LocalizationCommandImpl;
import by.itacademy.newsproject.controller.command.impl.MainPageCommandImpl;
import by.itacademy.newsproject.controller.command.impl.UpdateNewsReadCommandImpl;
import by.itacademy.newsproject.controller.command.impl.UpdateNewsSaveCommandImpl;
import by.itacademy.newsproject.controller.command.impl.WelcomeCommandImpl;

public class CommandProvider {

	private Map<String, Command> command = new HashMap<>();

	public CommandProvider() {
		command.put(ParameterCommand.CREATE_NEWS, new CreateNewsCommandImpl());

		command.put(ParameterCommand.FIND_NEWS, new FindNewsCommandImpl());
		command.put(ParameterCommand.FIND_ALL_NEWS, new FindAllNewsCommandImpl());

		command.put(ParameterCommand.UPDATE_NEWS_READ, new UpdateNewsReadCommandImpl());
		command.put(ParameterCommand.UPDATE_NEWS_SAVE, new UpdateNewsSaveCommandImpl());

		command.put(ParameterCommand.DELETE_NEWS, new DeleteNewsCommandImpl());
		command.put(ParameterCommand.DELETE_NEWS_SELECTED, new DeleteNewsSelectedCommandImpl());

		command.put(ParameterCommand.WELCOME_PAGE, new WelcomeCommandImpl());
		command.put(ParameterCommand.MAIN_PAGE, new MainPageCommandImpl());
		command.put(ParameterCommand.FILL_NEWS, new FillNewsCommandImpl());

		command.put(ParameterCommand.LOCALIZATION, new LocalizationCommandImpl());
	}

	public Command getCommand(String commandName) {
		return command.get(commandName);
	}
}