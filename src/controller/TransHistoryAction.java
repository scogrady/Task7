package controller;

import java.util.ArrayList;
import java.util.Collections;
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
			if (request.getSession(false).getAttribute("customer") == null) {
				errors.add("Wrong User");
				return "login.do";
			}
			transactionHistory = transactionDAO.readByCustomerID(customer
					.getCustomer_id());
			ArrayList<TransactionBean> transactions = new ArrayList<TransactionBean>();

			for (int i = 0; i < transactionHistory.length; i++) {
				transactions.add(transactionHistory[i]);
			}
			
			//System.out.println("--------------"+ transactions.get(0).getGenerate_date());
			//System.out.println("--------------"+ transactions.get(1).getGenerate_date());

			Collections.sort(transactions);

			request.setAttribute("transactionHistory", transactions);

			return "customer/trans-history.jsp";

		} catch (RollbackException e) {
			errors.add(e.getMessage());
			return "customer/error.jsp";

		}
	}

}
