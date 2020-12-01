package by.itacademy.newsproject.controller.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.itacademy.newsproject.service.ServiceException;

public interface Command {

	void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException;
}