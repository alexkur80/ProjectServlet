package by.itacademy.newsproject.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import by.itacademy.newsproject.entity.News;
import by.itacademy.newsproject.dao.DAOException;
import by.itacademy.newsproject.dao.DAOProvider;
import by.itacademy.newsproject.dao.NewsDAO;
import by.itacademy.newsproject.service.NewsService;
import by.itacademy.newsproject.service.ServiceException;
import by.itacademy.newsproject.service.validation.NewsValidation;

/**
 * Encapsulates business logic 'Service' layer 
 * <p>Validates incoming data and send further in 'DAO' layer
 * 
 * @author akurlovich
 *
 */
public class NewsServiceImpl implements NewsService {
	private static final Logger logger = Logger.getLogger(NewsServiceImpl.class);

	@Override
	public void create(News news) throws ServiceException {

		if (NewsValidation.isFormEmptyData(news)) {
			logger.error(
					"Validation isEmptyData: FAILED / Some of the fields: 'section', 'author', 'brief', 'content' are empty");
			throw new ServiceException("Some of the fields: 'section', 'author', 'brief', 'content' are empty");
		}

		if (NewsValidation.isFormFieldOverLength(news)) {
			logger.error(
					"Validation isFormLengthNotCorrect: FAILED / Some of the fields: 'section', 'author', 'brief', 'content' are overlength");
			throw new ServiceException("Some of the fields: 'section', 'author', 'brief', 'content' are overlength");
		}

		DAOProvider daoProvider = DAOProvider.getInstance();
		NewsDAO sqlNewsDao = daoProvider.getNewsDAO();

		try {
			sqlNewsDao.create(news);

		} catch (DAOException e) {
			logger.error("Error creating news / ", e);
			throw new ServiceException(e);
		}
	}

	@Override
	public List<News> selectAll() throws ServiceException {

		List<News> news = new ArrayList<>();

		DAOProvider daoProvider = DAOProvider.getInstance();
		NewsDAO sqlNewsDao = daoProvider.getNewsDAO();

		try {
			news = sqlNewsDao.selectAll();
		} catch (DAOException e) {
			logger.error("Error performing method: sqlNewsDao.selectAll() ", e);
			throw new ServiceException("Error list all news / ", e);
		}

		return news;
	}

	@Override
	public void update(News news, int id) throws ServiceException {

		if (NewsValidation.isFormEmptyData(news)) {
			logger.error("Some of the fields: 'section', 'author', 'brief', 'content' are empty");
			throw new ServiceException("Some of the fields: 'section', 'author', 'brief', 'content' are empty");

		}

		if (NewsValidation.isFormFieldOverLength(news)) {
			logger.warn("Validation isFormLengthNotCorrect: FAILED");
			throw new ServiceException("Some of the fields: 'section', 'author', 'brief', 'content' are overlength");
		}

		if (!NewsValidation.isIdCorrect(id)) {
			logger.warn(id + " is not in correct number range,  updating data by ID - FAILED");

			throw new ServiceException(id + " is not in correct number range,  updating data by ID - FAILED ");
		}

		DAOProvider daoProvider = DAOProvider.getInstance();
		NewsDAO sqlNewsDao = daoProvider.getNewsDAO();

		try {
			sqlNewsDao.update(news, id);
		} catch (DAOException e) {
			logger.error("Error updating by ID in DAO /  ", e);
			throw new ServiceException("Error updating by ID in DAO /  ", e);
		}
	}

	@Override
	public void delete(int id) throws ServiceException {

		if (!NewsValidation.isIdCorrect(id)) {
			logger.error(id + " is not in correct number range, delete by ID  - FAILED");

			throw new ServiceException(id + " is not in correct number range, delete by ID  - FAILED");
		}

		DAOProvider daoProvider = DAOProvider.getInstance();
		NewsDAO sqlNewsDao = daoProvider.getNewsDAO();

		try {
			sqlNewsDao.delete(id);
		} catch (DAOException e) {
			logger.error("Error delete by ID in DAO / ", e);
			throw new ServiceException("Error delete by ID in DAO / ", e);
		}
	}

	@Override
	public News select(int id) throws ServiceException {

		if (!NewsValidation.isIdCorrect(id)) {
			logger.error(id + " is not in correct number range");
			throw new ServiceException(id + " is not in correct number range");
		}

		DAOProvider daoProvider = DAOProvider.getInstance();
		NewsDAO sqlNewsDao = daoProvider.getNewsDAO();

		News news = new News();

		try {
			news = sqlNewsDao.select(id);
		} catch (DAOException e) {
			logger.error("Error select news by ID in DAO / ", e);
			throw new ServiceException("Error select news by ID in DAO / ", e);
		}

		return news;
	}

	@Override
	public void deleteSelected(int[] id) throws ServiceException {

		DAOProvider daoProvider = DAOProvider.getInstance();
		NewsDAO sqlNewsDao = daoProvider.getNewsDAO();

		try {
			sqlNewsDao.deleteSelected(id);
		}

		catch (DAOException e) {
			logger.error("Error group News delete in DAO /", e);
			throw new ServiceException("Error group News delete in DAO /", e);
		}
	}
}