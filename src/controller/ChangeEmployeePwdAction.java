package controller;

import javax.servlet.http.HttpServletRequest;

import model.Model;

public class ChangeEmployeePwdAction extends Action {

	public ChangeEmployeePwdAction(Model model) {
	}

	public String getName() { return "ChangeEmployeePwd.do"; }

	public String perform(HttpServletRequest request) {
	        return "employee/change-pwd.jsp";
    }
}
