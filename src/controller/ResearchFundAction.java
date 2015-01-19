package controller;

import javax.servlet.http.HttpServletRequest;

import model.Model;

public class ResearchFundAction extends Action {

	public ResearchFundAction(Model model) {
	}

	public String getName() { return "ResearchFund.do"; }

	public String perform(HttpServletRequest request) {
	        return "customer/research-fund.jsp";
    }
}
