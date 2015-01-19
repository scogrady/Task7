package controller;

import javax.servlet.http.HttpServletRequest;

import model.Model;

public class RequestCheckAction extends Action {

	public RequestCheckAction(Model model) {
	}

	public String getName() { return "RequestCheck.do"; }

	public String perform(HttpServletRequest request) {
		return "customer/request-check.jsp";
    }
}
