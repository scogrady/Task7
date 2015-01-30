package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
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
import org.genericdao.Transaction;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.CustomerBean;
import databeans.PositionBean;
import databeans.TransactionBean;
import formbeans.ChangePwdForm;
import formbeans.DepositForm;
import formbeans.IdForm;

public class ViewAccountAction extends Action {

	private TransactionDAO transactionDAO;

	private CustomerDAO customerDAO;
	private PositionDAO positionDAO;
	private FundDAO fundDAO;
	private FundPriceHistoryDAO fundPriceHistoryDAO;
	private FormBeanFactory<IdForm> formBeanFactory = FormBeanFactory
			.getInstance(IdForm.class);

	private FormBeanFactory<DepositForm> formBeanFactory2 = FormBeanFactory
			.getInstance(DepositForm.class);

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

		String role = request.getParameter("action");

		try {
			if (request.getSession(false).getAttribute("employee") == null) {
				errors.add("Wrong User");
				return "login.do";
			}
			Transaction.begin();

			IdForm form = formBeanFactory.create(request);
			int id;
			if (session.getAttribute("customerClicked") == null) {
				session.setAttribute("customerClicked",
						customerDAO.readFromID(1));
				id = 1;
			}
			CustomerBean customerClicked = (CustomerBean) request.getSession(
					false).getAttribute("customerClicked");
			System.out.println("customer clicked"
					+ customerClicked.getCustomer_id());// id setting
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
			// throw error that id in not valid.
			if (customerDAO.readFromID(id) == null) {
				System.out.println("we cought you");
				errors.add("Illegal customer id passed");
				request.setAttribute("customer", customerDAO.readFromID(1));
				return "employee/view-account.jsp";
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
			if (role != null && role.equals("Deposit")) {
				DepositForm form2 = formBeanFactory2.create(request);
				request.setAttribute("form2", form2);
				if (!form2.isPresent()) {
					return "employee/view-account.jsp";
				}
				long amount = -1;
				errors.addAll(form2.getValidationErrors());
				amount = (long) (Double.parseDouble(form2.getAmount()) * 100);

				if (errors.size() > 0) {
					return "employee/view-account.jsp";
				}

				CustomerBean customer = customerDAO.readFromID(id);

				if (amount > (100000000000.0 - customer.getAvailable_cash())) {
					errors.add("The available balance will beyond $1,000,000,000 after this deposit.");
				}
				if (errors.size() > 0) {
					return "employee/view-account.jsp";
				}
				Date date = new Date();
				TransactionBean depositCheck = new TransactionBean();

				depositCheck.setCustomer_id(id);
				depositCheck.setTransaction_type("Deposit Check");
				depositCheck.setStatus("Pending");
				depositCheck.setAmount(amount);
				depositCheck.setGenerate_date(date);
				transactionDAO.create(depositCheck);

				String message = "Successfully recieved your request.";
				request.setAttribute("message", message);
				request.setAttribute("form", null);

				customer.setAvailable_cash(customer.getAvailable_cash()
						+ amount);
				customerDAO.update(customer);

				request.setAttribute("customer", customerDAO.readFromID(id));

				ArrayList<TransactionBean> transactions2 = new ArrayList<TransactionBean>();
				transactionHistory = transactionDAO.readByCustomerID(id);
				for (int i = 0; i < transactionHistory.length; i++) {
					transactions2.add(transactionHistory[i]);
				}
				Collections.sort(transactions2);
				request.setAttribute("transactionHistory", transactions2);

				return "employee/view-account.jsp";
			}
			Transaction.commit();

			return "employee/view-account.jsp";
		} catch (RollbackException e) {
			errors.add("Incorrect Inputs");
			return "error.jsp";
		} catch (FormBeanException e) {
			errors.add("Bean Exceptions");
			return "error.jsp";

		} finally {
			if (Transaction.isActive())
				Transaction.rollback();
		}
	}
}
