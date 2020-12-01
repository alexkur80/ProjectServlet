package by.itacademy.newsproject.service;

import java.util.List;

import by.itacademy.newsproject.entity.News;

public interface NewsService {
    void create(News news) throws ServiceException;

    List<News> selectAll() throws ServiceException;

    News select(int id) throws ServiceException;

    void update(News news, int id) throws ServiceException;

    void delete(int id) throws ServiceException;

    void deleteSelected(int[] id) throws ServiceException;
}