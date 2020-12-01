package by.itacademy.newsproject.dao.impl;

import java.util.ArrayList;
import java.util.List;

import by.itacademy.newsproject.controller.command.impl.DeleteNewsCommandImpl;
import by.itacademy.newsproject.controller.command.impl.FindNewsCommandImpl;
import by.itacademy.newsproject.controller.command.impl.UpdateNewsSaveCommandImpl;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import by.itacademy.newsproject.entity.News;
import by.itacademy.newsproject.dao.DAOException;
import by.itacademy.newsproject.dao.NewsDAO;
import by.itacademy.newsproject.dao.pool.ConnectionPool;

/**
 * Performs CRUD SQL operations:
 * 
 * CREATE_NEWS
 * FIND_ALL_NEWS
 * FIND_NEWS
 * 
 * UPDATE_NEWS_SAVE
 * 
 * DELETE_NEWS_SELECTED
 * DELETE_NEWS
 * 
 * @author akurlovich
 *
 */
public class SQLNewsDAOImpl implements NewsDAO {
	private final ConnectionPool pool = ConnectionPool.getInstance();

	private static final Logger logger = Logger.getLogger(SQLNewsDAOImpl.class);

	private static final String SQL_CREATE = "INSERT INTO newspaper(date, section, author, brief, content) VALUES (?, ?, ?, ?, ?)";
	private static final String SQL_DELETE_BY_ID = "DELETE FROM newspaper WHERE id = ?";
	private static final String SQL_UPDATE_BY_ID = "UPDATE newspaper SET date = ? , section = ? , author = ? , "
			+ "brief = ? , content = ? " + "WHERE id = ?";
	private static final String SQL_SELECT_BY_ID = "SELECT * FROM newspaper WHERE id = ?";
	private static final String SQL_SELECT_ALL = "SELECT * FROM newspaper";

	/**
	 * Creates News
	 * 
	 * Uses COMMAND: {@link by.itacademy.newsproject.controller.command.impl.CreateNewsCommandImpl}

	 * @see by.itacademy.newsproject.controller.command.impl.CreateNewsCommandImpl
	 * @param News news
	 * @exception DAOException
	 * 
	 */
	@Override
	public void create(News news) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = pool.takeConnection();
			ps = con.prepareStatement(SQL_CREATE);

			ps.setDate(1, Date.valueOf(news.getDate()));
			ps.setString(2, news.getSection());
			ps.setString(3, news.getAuthor());
			ps.setString(4, news.getBrief());
			ps.setString(5, news.getContent());
			ps.executeUpdate();

		} catch (SQLException e) {
			logger.error("Error saving news into DB");
			throw new DAOException(e);
		} finally {
			pool.closeConnection(con, ps);

		}
	}

	/**
	 * Deletes News from DB
	 * 
	 * Uses COMMAND: {@link DeleteNewsCommandImpl}
	 * 
	 */
	@Override
	public void delete(int id) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = pool.takeConnection();
			ps = con.prepareStatement(SQL_DELETE_BY_ID);

			ps.setInt(1, id);
			ps.executeUpdate();

		} catch (SQLException e) {
			logger.error("Error deleting News with id = " + id + " / " + e);
			throw new DAOException(e);
		} finally {
			pool.closeConnection(con, ps);
		}
	}

	/**
	 * Updates News from DB
	 * 
	 * Uses COMMAND: {@link UpdateNewsSaveCommandImpl}
	 */
	@Override
	public void update(News news, int id) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = pool.takeConnection();
			ps = con.prepareStatement(SQL_UPDATE_BY_ID);

			ps.setDate(1, Date.valueOf(news.getDate()));
			ps.setString(2, news.getSection());
			ps.setString(3, news.getAuthor());
			ps.setString(4, news.getBrief());
			ps.setString(5, news.getContent());
			ps.setInt(6, id);
			ps.executeUpdate();

		} catch (SQLException e) {
			logger.error("Error updating News with id = " + id + " / " + e);
			throw new DAOException(e);
		} finally {
			pool.closeConnection(con, ps);
		}
	}

	/**
	 * Select all News data from DB
	 * 
	 * Uses COMMAND: {@link by.itacademy.newsproject.controller.command.impl.FindAllNewsCommandImpl}
	 * 
	 * @return List of all News from DB
	 */
	@Override
	public List<News> selectAll() throws DAOException {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		int id = 0;
		LocalDate date = null;
		String section = null;
		String author = null;
		String brief = null;
		String content = null;

		List<News> newsAll = new ArrayList<>();

		try {
			con = pool.takeConnection();
			st = con.createStatement();
			rs = st.executeQuery(SQL_SELECT_ALL);

			while (rs.next()) {
				News newspaper = new News();

				id = rs.getInt(1);
				date = rs.getDate("date").toLocalDate();
				section = rs.getString(3);
				author = rs.getString(4);
				brief = rs.getString(5);
				content = rs.getString(6);

				newspaper.setId(id);
				newspaper.setDate(date);
				newspaper.setSection(section);
				newspaper.setAuthor(author);
				newspaper.setBrief(brief);
				newspaper.setContent(content);

				newsAll.add(newspaper);
			}

		} catch (SQLException e) {
			logger.error("Error select News list data from DB " + e);
			throw new DAOException(e);
		} finally {
			pool.closeConnection(con, st, rs);

		}

		return newsAll;
	}

	/**
	 * Select News by ID
	 * 
	 * Uses COMMAND: {@link FindNewsCommandImpl}
	 * 
	 */
	@Override
	public News select(int id) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		LocalDate date = null;
		String section = null;
		String author = null;
		String brief = null;
		String content = null;

		News newspaper = new News();

		try {
			con = pool.takeConnection();
			ps = con.prepareStatement(SQL_SELECT_BY_ID);

			ps.setInt(1, id);
			rs = ps.executeQuery();

			if (rs.next()) {
				id = rs.getInt(1);
				date = rs.getDate("date").toLocalDate();
				section = rs.getString(3);
				author = rs.getString(4);
				brief = rs.getString(5);
				content = rs.getString(6);

				newspaper.setId(id);
				newspaper.setDate(date);
				newspaper.setSection(section);
				newspaper.setAuthor(author);
				newspaper.setBrief(brief);
				newspaper.setContent(content);
			}

		} catch (SQLException e) {
			logger.error("Error selecting News  by id = " + id + " / " + e);
			throw new DAOException(e);
		} finally {
			pool.closeConnection(con, ps, rs);
		}

		return newspaper;
	}

	/**
	 * Deletes list of News from DB by group of selected checkboxes in Form
	 * Then execute method deleting each separate news in cycle.
	 *
	 * Uses COMMAND: {@link by.itacademy.newsproject.controller.command.impl.DeleteNewsSelectedCommandImpl}
	 *
	 */
	@Override
	public void deleteSelected(int[] id) throws DAOException {

		for (int i = 0; i < id.length; i++) {
			delete(id[i]);
		}
	}
}