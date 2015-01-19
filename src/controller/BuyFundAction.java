package controller;

import javax.servlet.http.HttpServletRequest;

import model.Model;

public class BuyFundAction extends Action {
	public BuyFundAction(Model model) {
	}

	public String getName() { return "BuyFund.do"; }

	public String perform(HttpServletRequest request) {
		
	    return "customer/buy-fund.jsp";
    }
}
