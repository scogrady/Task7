package controller;

import javax.servlet.http.HttpServletRequest;

import model.Model;

public class CreateFundAction extends Action {
	
	public CreateFundAction(Model model) {
	}

	public String getName() { return "CreateFund.do"; }

	public String perform(HttpServletRequest request) {
		return "employee/create-fund.jsp";
    }
}
