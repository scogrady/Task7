package controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.CustomerBean;
import databeans.FundBean;
import databeans.FundPriceHistoryBean;
import databeans.PositionBean;
import databeans.SellFundBean;
import databeans.TransactionBean;
import formbeans.BuyForm;
import formbeans.SellForm;
import model.CustomerDAO;
import model.FundDAO;
import model.FundPriceHistoryDAO;
import model.Model;
import model.PositionDAO;
import model.TransactionDAO;

public class SellFundAction extends Action {

	private TransactionDAO transactionDAO;
	private CustomerDAO customerDAO;
	private FundDAO fundDAO;
	private FundPriceHistoryDAO fundPriceHistoryDAO;
	private PositionDAO positionDAO;
	private FormBeanFactory<SellForm> formBeanFactory;
	
	

	public SellFundAction(Model model) {

	}

	public String getName() {
		return "SellFund.do";
	}

	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		CustomerBean customer = (CustomerBean) request.getSession(false)
				.getAttribute("customer");
		Date date = new Date();
		PositionBean[] fundList;
		SellFundBean[] sellFundList = null;
		FundBean fund;
		FundPriceHistoryBean price;

		try {

			customer = customerDAO.readFromID(customer.getCustomer_id());
			request.getSession().setAttribute("customer", customer);
			
			fundList = positionDAO.readByCustomerID(customer.getCustomer_id());
			
			for (int i = 0; i < fundList.length; i++) {
				sellFundList[i].setFund_id(fundList[i].getFund_id());
				sellFundList[i].setShares(fundList[i].getShares());
				
				fund = fundDAO.read(fundList[i].getFund_id());
				sellFundList[i].setName(fund.getName());
				
				price = fundPriceHistoryDAO.readLastPrice(fundList[i].getFund_id());
				sellFundList[i].setPrice(price.getPrice());
			}
			request.setAttribute("sellFundList",sellFundList);
		// display

			SellForm form = formBeanFactory.create(request);
			request.setAttribute("form", form);

			if (!form.isPresent()) {
				return "sell-fund.jsp";
			}

			errors.addAll(form.getValidationErrors());
			

			if (errors.size() != 0) {
				return "sell-fund.jsp";
			}

			TransactionBean sellFund = new TransactionBean();

			sellFund.setCustomer_id(customer.getCustomer_id());
			sellFund.setFund_id(form.getFund_id());
			sellFund.setExecute_date(date);
			long share = form.getNum_1() * 1000 + form.getNum_2();
			sellFund.setShares(share);
			sellFund.setTransaction_type("Sell Fund");
			sellFund.setStatus("Pending");
			//sellFund.setAmount(amount);
			transactionDAO.create(sellFund);

			price = fundPriceHistoryDAO.readLastPrice(sellFund.getFund_id());
			

			customer.setAvailable_cash(customer.getAvailable_cash() + (price.getPrice() * share));
			customerDAO.update(customer);

			customer = customerDAO.readFromID(customer.getCustomer_id());
			request.getSession().setAttribute("customer", customer);

			return "sell-fund.jsp";
		} catch (RollbackException e) {
			errors.add(e.getMessage());
			return "error.jsp";
		} catch (FormBeanException e) {
			return "error.jsp";
		}


	}
}
