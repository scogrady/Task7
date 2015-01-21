package model;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;

public class Model {
	private CustomerDAO customerDAO;
	private EmployeeDAO employeeDAO;
	private FundDAO fundDAO;
	private FundPriceHistoryDAO fundPriceHistoryDAO;
	private PositionDAO positionDAO;
	private TransactionDAO transactionDAO;

	public Model(ServletConfig config) throws ServletException {
		try {
			String jdbcDriver = config.getInitParameter("jdbcDriverName");
			String jdbcURL = config.getInitParameter("jdbcURL");
			ConnectionPool pool = new ConnectionPool(jdbcDriver, jdbcURL,
					"root", "");

			customerDAO = new CustomerDAO("customer", pool);
			employeeDAO = new EmployeeDAO("employee", pool);
			fundDAO = new FundDAO("fund", pool);
			fundPriceHistoryDAO = new FundPriceHistoryDAO("fund_price_history",
					pool);
			positionDAO = new PositionDAO("position", pool);
			transactionDAO = new TransactionDAO("transaction", pool);

		} catch (DAOException e) {
			throw new ServletException(e);
		}
	}

	public CustomerDAO getCustomerDAO() {
		return customerDAO;
	}

	public EmployeeDAO getEmployeeDAO() {
		return employeeDAO;
	}

	public FundDAO getFundDAO() {
		return fundDAO;
	}

	public FundPriceHistoryDAO getFundPriceHistoryDAO() {
		return fundPriceHistoryDAO;
	}

	public PositionDAO getPositionDAO() {
		return positionDAO;
	}

	public TransactionDAO getTransactionDAO() {
		return transactionDAO;
	}
}
