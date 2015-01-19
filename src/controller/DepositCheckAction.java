package controller;
import javax.servlet.http.HttpServletRequest;

import model.Model;

public class DepositCheckAction extends Action {

	public DepositCheckAction(Model model) {
	}

	public String getName() { return "DepositCheck.do"; }

	public String perform(HttpServletRequest request) {
		return "employee/deposit-check.jsp";
    }
}
