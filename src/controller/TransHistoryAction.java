package controller;

import javax.servlet.http.HttpServletRequest;

import model.Model;

public class TransHistoryAction extends Action {


	public TransHistoryAction(Model model) {
	}

	public String getName() { return "TransHistory.do"; }

	public String perform(HttpServletRequest request) {
        	return "error.jsp";
    }
}
