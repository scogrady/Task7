package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import model.FundDAO;
import model.FundPriceHistoryDAO;
import model.Model;
import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanFactory;

import databeans.FundBean;
import formbeans.FundIdForm;

public class ResearchFundAction extends Action {

	private FundDAO fundDAO;
	private FundPriceHistoryDAO fundPriceHistoryDAO;

	private FormBeanFactory<FundIdForm> formBeanFactory = FormBeanFactory
			.getInstance(FundIdForm.class);

	public ResearchFundAction(Model model) {
		fundDAO = model.getFundDAO();
		fundPriceHistoryDAO = model.getFundPriceHistoryDAO();

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

			FundBean[] fundList = fundDAO.getFunds();
			FundBean[] recommanList = Arrays.copyOf(fundList, 3);

			request.setAttribute("fundList", fundList);
			request.setAttribute("recommandFundList", recommanList);
			int fundId = 1;
			if(request.getParameter("fund_id") != null){
				try{
					fundId = Integer.parseInt(request.getParameter("fund_id"));
					if(fundId > fundList.length){
						fundId = 1;
						errors.add("No Such Fund.");
					}
				}catch(Exception e){
					errors.add("Illegal Fund Id.");
				}
			}
			request.setAttribute("fundPriceHistoryList", fundPriceHistoryDAO.readByFundID(fundId));
			request.setAttribute("fundPriceHistoryName", fundDAO.readById(fundId));
		
			if(request.getParameter("compare_id") != null) {
				try{
					int compareId = Integer.parseInt(request.getParameter("compare_id"));
					if(compareId > fundList.length){
						errors.add("No Such Fund.");
					}
					else{
						request.setAttribute("comparePriceHistoryList",fundPriceHistoryDAO.readByFundID(Integer.parseInt(request.getParameter("compare_id"))));
						request.setAttribute("comparePriceHistoryName", fundDAO.readById(Integer.parseInt(request.getParameter("compare_id"))));
					}
				}catch(Exception e){
					errors.add("Illegal Fund Id.");
				}
			}		
			request.setAttribute("now_id", fundId);
			
			return "customer/research-fund.jsp";
		} catch (RollbackException e) {
			errors.add(e.getMessage());
			return "customer/error.jsp";
		}
	}
}
