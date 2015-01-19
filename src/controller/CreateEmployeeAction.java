package controller;

import javax.servlet.http.HttpServletRequest;

import model.Model;

public class CreateEmployeeAction extends Action {
	public CreateEmployeeAction(Model model) {
	}

	public String getName() { return "CreateEmployee.do"; }

	public String perform(HttpServletRequest request) {
		return "employee/create-employee.jsp";
    }
}
