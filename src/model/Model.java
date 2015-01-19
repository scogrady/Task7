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

	private FundDAO fundDAO;

	public Model(ServletConfig config) throws ServletException {
		try {
			String jdbcDriver = config.getInitParameter("jdbcDriverName");
			String jdbcURL = config.getInitParameter("jdbcURL");
			ConnectionPool pool = new ConnectionPool(jdbcDriver, jdbcURL);

			fundDAO = new FundDAO("fund", pool);
		} catch (DAOException e) {
			throw new ServletException(e);
		}
	}


	public FundDAO getFundDAO() {
		return fundDAO;
	}
}
