package controller;

import javax.servlet.http.HttpServletRequest;

import model.Model;

public class ChangePwdAction extends Action {

	public ChangePwdAction(Model model) {
	}

	public String getName() { return "ChangePwd.do"; }

	public String perform(HttpServletRequest request) {
		return "customer/change-pwd.jsp";
    }
}
