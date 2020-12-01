package by.itacademy.newsproject.service;

import by.itacademy.newsproject.service.ServiceProvider;
import by.itacademy.newsproject.service.impl.NewsServiceImpl;

public class ServiceProvider {
	private static final ServiceProvider instance = new ServiceProvider();
	
	private final NewsService newsServiceImpl = new NewsServiceImpl();

	private ServiceProvider() {
	}

	public static ServiceProvider getInstance() {
		return instance;
	}

	public NewsService getNewsService() {
		return newsServiceImpl;
	}
}