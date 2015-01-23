package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;

import databeans.CustomerBean;
import databeans.TransactionBean;
import model.Model;
import model.TransactionDAO;

public class TransHistoryAction extends Action {
	private TransactionDAO transactionDAO;
	private TransactionBean[] transactionHistory;

	public TransHistoryAction(Model model) {
		transactionDAO = model.getTransactionDAO();
	}

	public String getName() {
		return "TransHistory.do";
	}

	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		CustomerBean customer = (CustomerBean) request.getSession(false)
				.getAttribute("customer");
		try {
			transactionHistory = transactionDAO.readByCustomerID(customer
					.getCustomer_id());

			request.setAttribute("transactionHistory", transactionHistory);

			return "employee/view-transaction.jsp";
			

		} catch (RollbackException e) {
			errors.add(e.getMessage());
			return "error.jsp";

		}
	}

}
