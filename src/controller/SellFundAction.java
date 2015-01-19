package controller;

import javax.servlet.http.HttpServletRequest;

import model.Model;

public class SellFundAction extends Action {

	public SellFundAction(Model model) {
	}

	public String getName() { return "SellFund.do"; }

	public String perform(HttpServletRequest request) {
		return "customer/sell-fund.jsp";
    }
}
