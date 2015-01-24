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
import formbeans.BuyForm;
import formbeans.RequestCheckForm;
import model.CustomerDAO;
import model.Model;
import model.TransactionDAO;

public class RequestCheckAction extends Action {
	private CustomerDAO customerDAO;
	private TransactionDAO transactionDAO;
	private FormBeanFactory<RequestCheckForm> formBeanFactory = FormBeanFactory
			.getInstance(RequestCheckForm.class);;

	public RequestCheckAction(Model model) {
		customerDAO = model.getCustomerDAO();
		transactionDAO = model.getTransactionDAO();
	}

	public String getName() {
		return "RequestCheck.do";
	}

	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		CustomerBean customer = (CustomerBean) request.getSession(false)
				.getAttribute("customer");
		Date date = new Date();

		try {
			customer = customerDAO.readFromID(customer.getCustomer_id());
			request.getSession().setAttribute("customer", customer);
			
			RequestCheckForm form = formBeanFactory.create(request);
			request.setAttribute("form", form);

			if (!form.isPresent()) {
				return "customer/request-check.jsp";
			}
			
			errors.addAll(form.getValidationErrors());
			

			if (errors.size() != 0) {
				return "customer/request-check.jsp";
			}
			TransactionBean requestCheck = new TransactionBean();

			requestCheck.setCustomer_id(customer.getCustomer_id());
			requestCheck.setFund_id(1);
			requestCheck.setGenerate_date(date);
		
			requestCheck.setTransaction_type("Request Check");
			requestCheck.setStatus("Pending");
			long amount = Long.parseLong(form.getNum()) * 100 ;
			requestCheck.setAmount(amount);
			transactionDAO.create(requestCheck);
			
			String message = "Successfully recieve your request.";
			request.setAttribute("message", message);


			customer.setAvailable_cash(customer.getAvailable_cash() - amount);
			customerDAO.update(customer);
			
			customer = customerDAO.readFromID(customer.getCustomer_id());
			request.getSession().setAttribute("customer", customer);
		
			
			return "customer/request-check.jsp";
		} catch (RollbackException e) {
			errors.add(e.getMessage());
			return "customer/error.jsp";
		} catch (FormBeanException e) {
			return "customer/error.jsp";
		}
	}
}
