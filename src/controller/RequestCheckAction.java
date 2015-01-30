package controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;
import org.genericdao.Transaction;
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
			if (request.getSession(false).getAttribute("customer") == null) {
				errors.add("Wrong User");
				return "login.do";
			}
			Transaction.begin();
			customer = customerDAO.readFromID(customer.getCustomer_id());
			request.getSession().setAttribute("customer", customer);

			RequestCheckForm form = formBeanFactory.create(request);
			request.setAttribute("form", form);
			long amount = -1;

			if (!form.isPresent()) {
				return "customer/request-check.jsp";
			}

			errors.addAll(form.getValidationErrors());

				amount = (long) (Double.parseDouble(form.getNum()) * 100);
				if (amount > customer.getAvailable_cash()) {
					errors.add("Not enough money in Available Cash");
				}

			customer = customerDAO.readFromID(customer.getCustomer_id());
			request.getSession().setAttribute("customer", customer);

			if (errors.size() != 0) {
				return "customer/request-check.jsp";
			}

			TransactionBean requestCheck = new TransactionBean();
			
			//System.out.println("commit");
			requestCheck.setCustomer_id(customer.getCustomer_id());
			requestCheck.setGenerate_date(date);
			requestCheck.setTransaction_type("Request Check");
			requestCheck.setStatus("Pending");
			requestCheck.setAmount(amount);
			transactionDAO.create(requestCheck);

			String message = "Successfully recieve your request.";
			request.setAttribute("message", message);
			request.setAttribute("form", null);

			customer.setAvailable_cash(customer.getAvailable_cash() - amount);
			customerDAO.update(customer);

			customer = customerDAO.readFromID(customer.getCustomer_id());
			request.getSession().setAttribute("customer", customer);
			
			Transaction.commit();
			return "customer/request-check.jsp";
		} catch (RollbackException e) {
			errors.add(e.getMessage());
			return "customer/error.jsp";
		} catch (FormBeanException e) {

			return "customer/error.jsp";
		}
		 catch (NumberFormatException e) {
			errors.add("Please double check your input.");
		}
		finally {
			if (Transaction.isActive())
				Transaction.rollback();
		}
		return "customer/request-check.jsp";
	}
}
