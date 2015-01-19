//Name: Hanze Xu
//Andrew ID: hanzex
//Title: Homework #9 Model.java
//Course Number: 08-600
//Date: 2014-12-3
package model;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;

public class Model {
	private FavoriteDAO favoriteDAO;
	private UserDAO  userDAO;

	public Model(ServletConfig config) throws ServletException {
		try {
			String jdbcDriver = config.getInitParameter("jdbcDriverName");
			String jdbcURL    = config.getInitParameter("jdbcURL");
			ConnectionPool pool = new ConnectionPool(jdbcDriver, jdbcURL, "root", "abcd");
			
			userDAO  = new UserDAO("hanzex_user", pool);
			favoriteDAO = new FavoriteDAO("hanzex_favorite", pool);
		} catch (DAOException e) {
			throw new ServletException(e);
		}
	}
	
	public FavoriteDAO getFavoriteDAO() { return favoriteDAO; }
	public UserDAO  getUserDAO()  { return userDAO;  }
}
