package controller;

import javax.servlet.http.HttpServletRequest;

import model.Model;

public class TransactionDayAction extends Action {

	public TransactionDayAction(Model model) {
	}

	public String getName() { return "TransactionDay.do"; }

	public String perform(HttpServletRequest request) {
		return "employee/transaction-day.jsp";
    }
}
