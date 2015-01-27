package controller;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.CustomerBean;
import databeans.TransactionBean;
import formbeans.DepositForm;
import model.CustomerDAO;
import model.Model;
import model.TransactionDAO;

public class DepositCheckAction extends Action {
	
	private CustomerDAO customerDAO;
	private TransactionDAO transactionDAO;
	private FormBeanFactory<DepositForm> formBeanFactory = FormBeanFactory
			.getInstance(DepositForm.class);

	public DepositCheckAction(Model model) {
		customerDAO = model.getCustomerDAO();
		transactionDAO = model.getTransactionDAO();
	}

	public String getName() {
		return "DepositCheck.do";
	}

	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		Date date = new Date();

		try {
			request.setAttribute("transactionList", transactionDAO.getTransactions());//TODO DELETE
			
			request.setAttribute("customerList", customerDAO.getCustomers());
			DepositForm form = formBeanFactory.create(request);
			request.setAttribute("form", form);

			if (!form.isPresent()) {
				return "ViewAccount.do";
			}

			errors.addAll(form.getValidationErrors());

			if (errors.size() != 0) {
				return "ViewAccount.do";
			}

			TransactionBean depositCheck = new TransactionBean();

			CustomerBean customer = customerDAO.read(form.getUsername());
			depositCheck.setCustomer_id(customer.getCustomer_id());
			depositCheck.setTransaction_type("Deposit Check");
			depositCheck.setStatus("Pending");
			depositCheck.setAmount(Long.parseLong(form.getAmount()) * 100);
			depositCheck.setFund_id(1);
			depositCheck.setShares(-1);
			//TODO new java.sql.Date(date.getTime())
			depositCheck.setGenerate_date(date);
			transactionDAO.create(depositCheck);
			
			String message = "Successfully recieve your request.";
			request.setAttribute("message", message);


			customer.setAvailable_cash(customer.getAvailable_cash() + (long)Double.parseDouble(form.getAmount()) * 100);
			customerDAO.update(customer);
			
			request.setAttribute("transactionList", transactionDAO.getTransactions());
			return "ViewAccount.do";
		} catch (RollbackException e) {
			errors.add(e.getMessage());
			return "employee/error.jsp";
		} catch (FormBeanException e) {
			return "employee/error.jsp";
		}
	}

}
