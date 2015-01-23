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
import model.Model;
import model.TransactionDAO;

public class EmployeeViewTransHistoryAction extends Action {
	private TransactionDAO transactionDAO;
	private TransactionBean[] transactionHistory;
	private FormBeanFactory<IdForm> formBeanFactory = FormBeanFactory.getInstance(IdForm.class);


	public EmployeeViewTransHistoryAction(Model model) {
		transactionDAO = model.getTransactionDAO();
	}

	public String getName() {
		return "EmployeeViewTransHistory.do";
	}

	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
				try {
			IdForm form = formBeanFactory.create(request);

			int id = form.getIdAsInt();
			request.setAttribute("id", id);
			
			transactionHistory = transactionDAO.readByCustomerID(id);

			request.setAttribute("transactionHistory", transactionHistory);

			return "employee/trans-history.jsp";
			
			

		} catch (RollbackException e) {
			errors.add(e.getMessage());
			return "employee/error.jsp";

		} catch (FormBeanException e) {
			errors.add(e.getMessage());
			return "employee/error.jsp";

		}
	}

}
