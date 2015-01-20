package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.FundDAO;
import model.FundPriceHistoryDAO;
import model.Model;

import org.genericdao.RollbackException;

import databeans.FundBean;
import databeans.FundPriceHistoryBean;

public class ResearchFundAction extends Action {

	private FundDAO fundDAO;
	private FundPriceHistoryDAO fundPriceHistoryDAO;

	public ResearchFundAction(Model model) {
		fundDAO = model.getFundDAO();
		fundPriceHistoryDAO = model.getFundPriceHistoryDAO();
		try {
			FundBean[] list = fundDAO.getFunds();
			for(int i=0;i<list.length;i++){
				System.out.println(list[i].getName());
			}
			
			FundPriceHistoryBean[] list2 = fundPriceHistoryDAO.getFundPriceHistorys();
			for(int i=0;i<list2.length;i++){
				System.out.println(list2[i].getFund_id()+" "+list2[i].getPrice_date()+" "+list2[i].getPrice());
			}
			
		} catch (RollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

	public String getName() {
		return "ResearchFund.do";
	}

	public String perform(HttpServletRequest request) {
		// Set up the errors list
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		try {
			// Set up fund list for nav bar
			request.setAttribute("fundList", fundDAO.getFunds());
			return "customer/research-fund.jsp";
		} catch (RollbackException e) {
			errors.add(e.getMessage());
			return "error.jsp";
		}
    }
}
