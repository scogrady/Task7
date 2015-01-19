package controller;

import javax.servlet.http.HttpServletRequest;

import model.Model;

public class ResetPasswordAction extends Action {

	public ResetPasswordAction(Model model) {
	}

	public String getName() { return "ResetPassword.do"; }

	public String perform(HttpServletRequest request) {
	        return "employee/reset-password.jsp";
    }
}
