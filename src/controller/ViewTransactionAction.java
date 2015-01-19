package controller;

import javax.servlet.http.HttpServletRequest;

import model.Model;

public class ViewTransactionAction extends Action {

	public ViewTransactionAction(Model model) {
	}

	public String getName() { return "ViewTransaction.do"; }

	public String perform(HttpServletRequest request) {
		return "employee/view-transaction.jsp";
    }
}
