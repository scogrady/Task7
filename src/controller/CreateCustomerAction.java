package controller;

import javax.servlet.http.HttpServletRequest;

import model.Model;
public class CreateCustomerAction extends Action {


	public CreateCustomerAction(Model model) {
	}

	public String getName() { return "CreateCustomer.do"; }

	public String perform(HttpServletRequest request) {	        
		return "employee/create-customer.jsp";
    }
}
