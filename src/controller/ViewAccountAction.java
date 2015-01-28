package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import model.Model;
import model.CustomerDAO;
import model.PositionDAO;
import model.FundDAO;
import model.FundPriceHistoryDAO;
import model.TransactionDAO;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.CustomerBean;
import databeans.PositionBean;
import databeans.TransactionBean;
import databeans.UserBean;
import formbeans.ChangePwdForm;
import formbeans.IdForm;

public class ViewAccountAction extends Action {

	private TransactionDAO transactionDAO;

	private CustomerDAO customerDAO;
	private PositionDAO positionDAO;
	private FundDAO fundDAO;
	private FundPriceHistoryDAO fundPriceHistoryDAO;
	private FormBeanFactory<IdForm> formBeanFactory = FormBeanFactory
			.getInstance(IdForm.class);

	public ViewAccountAction(Model model) {

		transactionDAO = model.getTransactionDAO();
		customerDAO = model.getCustomerDAO();
		positionDAO = model.getPositionDAO();
		fundDAO = model.getFundDAO();
		fundPriceHistoryDAO = model.getFundPriceHistoryDAO();

	}

	public String getName() {
		return "ViewAccount.do";
	}

	public String perform(HttpServletRequest request) {
		// Set up the errors list
		HttpSession session = request.getSession();
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		
		TransactionBean[] transactionHistory;
		try {
			IdForm form = formBeanFactory.create(request);
			int id;
			if (session.getAttribute("customerClicked") == null)// for first
																// time coming
																// to page
			{
				session.setAttribute("customerClicked",
						customerDAO.readFromID(1));
				id = 1;
			}
			CustomerBean customerClicked = (CustomerBean) request.getSession(
					false).getAttribute("customerClicked");
			System.out.println("customer clicked"
					+ customerClicked.getCustomer_id());
			// id setting
			
			
			if (request.getParameter("customer_id") == null) {
				id = customerClicked.getCustomer_id();
			} else {
				id = Integer.parseInt(request.getParameter("customer_id"));
				session.setAttribute("customerClicked", null);
				session.setAttribute("customerClicked",
						customerDAO.readFromID(id));
						System.out.println(id);
			}

			request.setAttribute("customerList", customerDAO.getCustomers());
			request.setAttribute("id", id);
			//throw error that id in not valid.
			if(customerDAO.readFromID(id)==null)
			{
				errors.add("Illegal customer id passed");
				return "employee/error.jsp";
			}
			request.setAttribute("customer", customerDAO.readFromID(id));
			// Setting up loacl session for clicked customer
			session.setAttribute("customerClicked", customerDAO.readFromID(id));
			// set up portfolio list for customer
			request.setAttribute("position", positionDAO.readByCustomerID(id));
			// to get fund symbol list
			request.setAttribute("fundTicker", fundDAO.getFunds());
			// to get fund price
			request.setAttribute("priceList",
					fundPriceHistoryDAO.getFundPriceHistorys());

			ArrayList<TransactionBean> transactions = new ArrayList<TransactionBean>();
			transactionHistory = transactionDAO.readByCustomerID(id);

			for (int i = 0; i < transactionHistory.length; i++) {
				transactions.add(transactionHistory[i]);
			}
			
			Collections.sort(transactions);
			request.setAttribute("transactionHistory", transactions);

			return "employee/view-account.jsp";
		} catch (RollbackException e) {
			errors.add("Incorrect Inputs");
			return "error.jsp";
		} catch (FormBeanException e) {
			errors.add("Bean Exceptions");
			return "error.jsp";

		}
	}
}
