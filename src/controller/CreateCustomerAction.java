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
import databeans.EmployeeBean;
import formbeans.CustomerForm;
import formbeans.EmployeeForm;
import model.CustomerDAO;
import model.EmployeeDAO;
import model.Model;

public class CreateCustomerAction extends Action {

	private CustomerDAO customerDAO;
	private FormBeanFactory<CustomerForm> formBeanFactory = FormBeanFactory
			.getInstance(CustomerForm.class);

	public CreateCustomerAction(Model model) {
		customerDAO = model.getCustomerDAO();
	}

	public String getName() {
		return "CreateCustomer.do";
	}

	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		CustomerForm form;
		try {
			request.setAttribute("customerList", customerDAO.getCustomers());
			form = formBeanFactory.create(request);
			request.setAttribute("form", form);
			if (!form.isPresent()) {
				System.out.println("create customer form is not present!");
				return "employee/create-customer.jsp";
			}

			errors.addAll(form.getValidationErrors());

			CustomerBean customer = customerDAO.read(form.getUsername());
			if (customer != null) {
				System.out.println(" customer is already exist");	
				errors.add("Customer username is already exist.");
			}

			if (errors.size() != 0) {
				System.out.println(" error :");	
				for(int i=0;i<errors.size();i++){
					System.out.println(errors.get(i));
				}
				return "employee/create-customer.jsp";
			}

			// Create new UserBean
			System.out.println("create customer now!");
			customer = new CustomerBean();
			customer.setUsername(form.getUsername());
			customer.setAddr_line1(form.getAddr_line1());
			customer.setAddr_line2(form.getAddr_line2());
			customer.setAvailable_cash(0);
			customer.setCity(form.getCity());
			customer.setCurrent_cash(0);
			customer.setFirstname(form.getFirstname());
			customer.setLastname(form.getLastname());
			customer.setPassword(form.getPassword());
			customer.setState(form.getState());
			customer.setStatus(0);
			customer.setZip(form.getZip());
			customer.setCurrent_cash(0);
			customer.setAvailable_cash(0);
			customerDAO.create(customer);
			
			String message = "Successfully created a new customer account.";
			request.setAttribute("message", message);
			request.setAttribute("form", null);

			request.setAttribute("customerList", customerDAO.getCustomers());
			return "employee/create-customer.jsp";

		} catch (RollbackException e) {
			errors.add(e.getMessage());
			return "employee/error.jsp";
		} catch (FormBeanException e) {
			errors.add(e.getMessage());
			return "employee/error.jsp";
		}
	}

}
