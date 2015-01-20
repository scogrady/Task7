package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.FundDAO;
import model.FundPriceHistoryDAO;
import model.Model;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
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
		/**try {
			FundBean[] fundList = fundDAO.getFunds();
			for(int i=0;i<fundList.length;i++){
				System.out.println(fundList[i].getName());
			}
			
			FundPriceHistoryBean[] fundPriceHistoryList = fundPriceHistoryDAO.readByFundID(1);
			for(int i=0;i<fundPriceHistoryList.length;i++){
				System.out.println(fundPriceHistoryList[i].getFund_id()+" "+fundPriceHistoryList[i].getPrice_date()+" "+fundPriceHistoryList[i].getPrice());
			}
			
		} catch (RollbackException e) {
			e.printStackTrace();
		}*/
	
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
			
			FundBean[] fundList = fundDAO.getFunds();
			FundBean[] recommanList = Arrays.copyOf(fundList, 3);
			request.setAttribute("recommandFundList", recommanList);
			
			/**FundIdForm form = formBeanFactory.create(request);
			request.setAttribute("form", form);
			
			if (!form.isPresent()) {
				System.out.println("form is not Present");
				
				return "customer/research-fund.jsp";
			}
			errors.addAll(form.getValidationErrors());
			if (errors.size() != 0) {
				return "customer/research-fund.jsp";
			}
			System.out.println(form.getIdAsInt());*/
			request.setAttribute("fundPriceHistoryList", fundPriceHistoryDAO.readByFundID(Integer.parseInt(request.getParameter("fund_id"))));

			request.setAttribute("fundPriceHistoryName", fundDAO.readById(Integer.parseInt(request.getParameter("fund_id"))));
			
			
			return "customer/research-fund.jsp";
		}
		catch (RollbackException e) {
			errors.add(e.getMessage());
			return "error.jsp";
		}
    }
}
