package controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
		HttpSession session = request.getSession();
		Date date = new Date();

		try {
			request.setAttribute("transactionList",
					transactionDAO.getTransactions());// TODO DELETE

			request.setAttribute("customerList", customerDAO.getCustomers());
			DepositForm form = formBeanFactory.create(request);
			request.setAttribute("form", form);
			CustomerBean customer = (CustomerBean) request.getSession(
					false).getAttribute("customerClicked");
			System.out.println("after setting session customer");
			request.setAttribute("customer",customer);

			if (!form.isPresent()) {
				return "ViewAccount.do";
			}
			long amount = -1;
			
			errors.addAll(form.getValidationErrors());

			try {

				amount = (long) (Double.parseDouble(form.getAmount()) * 100);
				// System.out.println("depositAmount:  " + amount);

				CustomerBean customer1 = customerDAO.read(form.getUsername());
				if (amount > (Long.MAX_VALUE - customer1.getAvailable_cash())) {
					errors.add("The available balance will beyond the maximum amount after this deposit.");
				}

			} catch (NumberFormatException e) {
				errors.add("Please double check your input.");
			}
			// set up ViewAccount.do
			
			
			if (errors.size() != 0) {
				System.out.println("we are in if" + session.getAttribute("customerClicked"));
				
				System.out.println("after setting session customer");
				request.setAttribute("customer",customer);
				System.out.println("cust id in deposit page"+customer.getCustomer_id());
				return "employee/view-account.jsp";
			}

			TransactionBean depositCheck = new TransactionBean();
			CustomerBean customer2 = customerDAO.read(form.getUsername());

			depositCheck.setCustomer_id(customer2.getCustomer_id());
			depositCheck.setTransaction_type("Deposit Check");
			depositCheck.setStatus("Pending");
			depositCheck.setAmount(amount);
			// TODO new java.sql.Date(date.getTime())
			depositCheck.setGenerate_date(date);
			transactionDAO.create(depositCheck);

			String message = "Successfully recieved your request.";
			request.setAttribute("message", message);
			request.setAttribute("form", null);

			customer2.setAvailable_cash(customer2.getAvailable_cash() + amount);
			customerDAO.update(customer2);

			request.setAttribute("transactionList",
					transactionDAO.getTransactions());
			return "employee/view-account.jsp";
		} catch (RollbackException e) {
			errors.add(e.getMessage());
			return "employee/error.jsp";
		} catch (FormBeanException e) {
			return "employee/error.jsp";
		}
	}

}
