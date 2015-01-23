package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.CustomerBean;
import databeans.TransactionBean;
import formbeans.IdForm;
import model.CustomerDAO;
import model.Model;
import model.TransactionDAO;

public class ViewTransactionAction extends Action {
	private CustomerDAO customerDAO;


	public ViewTransactionAction(Model model) {
		customerDAO = model.getCustomerDAO();
	}

	public String getName() {
		return "ViewTransaction.do";
	}

	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		
		
		//找列表
		try {
			request.setAttribute("customerList", customerDAO.getCustomers());

			
			return "employee/view-transaction.jsp";
		} catch (RollbackException e) {
			errors.add(e.getMessage());
			return "error.jsp";

		}
		
	}
}
