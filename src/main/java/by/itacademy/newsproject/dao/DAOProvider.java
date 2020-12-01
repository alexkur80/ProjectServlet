package by.itacademy.newsproject.dao;

import by.itacademy.newsproject.dao.impl.SQLNewsDAOImpl;

public class DAOProvider {
	private static final DAOProvider instance = new DAOProvider();

	private final NewsDAO sqlNewsDAOImpl = new SQLNewsDAOImpl();

	private DAOProvider() {
	}

	public static DAOProvider getInstance() {
		return instance;
	}
	
	public NewsDAO getNewsDAO() {
		return sqlNewsDAOImpl;
	}

}