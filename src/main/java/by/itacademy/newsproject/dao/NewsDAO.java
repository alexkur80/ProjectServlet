package by.itacademy.newsproject.dao;

import java.util.List;

import by.itacademy.newsproject.entity.News;

public interface NewsDAO {
	void create(News news) throws DAOException;

	List<News> selectAll() throws DAOException;

	News select(int id) throws DAOException;

	void update(News news, int id) throws DAOException;

	void delete(int id) throws DAOException;

	void deleteSelected(int[] id) throws DAOException;
}