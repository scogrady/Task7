package controller;

import javax.servlet.http.HttpServletRequest;

import model.Model;

public class ViewAccountAction extends Action {

	public ViewAccountAction(Model model) {
	}

	public String getName() { return "ViewAccount.do"; }

	public String perform(HttpServletRequest request) {
		return "employee/view-account.jsp";
    }
}
