package controller;


import javax.servlet.http.HttpServletRequest;

import model.Model;



public class AccountInfoAction extends Action {

	public AccountInfoAction(Model model) {
	}

	public String getName() { return "AccountInfo.do"; }

	public String perform(HttpServletRequest request) {
        return "customer/account-info.jsp";
    }
}
