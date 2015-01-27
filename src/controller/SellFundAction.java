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
	private FormBeanFactory<SellForm> formBeanFactory = FormBeanFactory
			.getInstance(SellForm.class);

	public SellFundAction(Model model) {
		transactionDAO = model.getTransactionDAO();
		customerDAO = model.getCustomerDAO();
		fundDAO = model.getFundDAO();
		fundPriceHistoryDAO = model.getFundPriceHistoryDAO();
		positionDAO = model.getPositionDAO();

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
		SellFundBean[] sellFundList;
		FundBean fund;
		FundPriceHistoryBean price;

		try {

			request.getSession().setAttribute("customer", customer);

			fundList = positionDAO.readByCustomerID(customer.getCustomer_id());

			sellFundList = new SellFundBean[fundList.length];
			for (int i = 0; i < fundList.length; i++) {
				sellFundList[i] = new SellFundBean();
				sellFundList[i].setFund_id(fundList[i].getFund_id());
				sellFundList[i].setShares(fundList[i].getAvailable_shares());

				fund = fundDAO.read(fundList[i].getFund_id());
				sellFundList[i].setName(fund.getName());

				price = fundPriceHistoryDAO.readLastPrice(fundList[i]
						.getFund_id());
				if (price == null) {
					price = new FundPriceHistoryBean();
					price.setPrice(-1);
				}
				sellFundList[i].setPrice(price.getPrice());
			}
			request.setAttribute("sellFundList", sellFundList);
			// display

			SellForm form = formBeanFactory.create(request);
			request.setAttribute("form", form);
			System.out.println(form.getFund_id());
			if (!form.isPresent()) {
				System.out.println("no form");
				return "customer/sell-fund.jsp";
			}

			long num = -1;

			try {

				num = (long) (Double.parseDouble(form.getNum()) * 1000);
				PositionBean sellFund = positionDAO.readByIdFundId(
						Integer.parseInt(form.getFund_id()),
						customer.getCustomer_id());

				if (num > sellFund.getAvailable_shares()) {
					errors.add("You can't sell more than what you have.");
				}

				// 找到fund 找到available share
			} catch (NumberFormatException e) {
				errors.add("Please double check your input.");
			}

			errors.addAll(form.getValidationErrors());

			if (errors.size() != 0) {
				return "customer/sell-fund.jsp";
			}

			// handle amount from form


			// create transaction

			TransactionBean sellFund = new TransactionBean();

			sellFund.setCustomer_id(customer.getCustomer_id());
			sellFund.setFund_id(Integer.parseInt(form.getFund_id()));
			sellFund.setGenerate_date(date);

		

			sellFund.setShares(num);
			sellFund.setTransaction_type("Sell Fund");
			sellFund.setStatus("Pending");
			// sellFund.setAmount(amount);
			transactionDAO.create(sellFund);

			String message = "Successfully recieved your request.";
			request.setAttribute("message", message);

			PositionBean position = positionDAO.readByIdFundId(
					Integer.parseInt(form.getFund_id()),
					customer.getCustomer_id());
			position.setAvailable_shares(position.getAvailable_shares() - num);
			positionDAO.update(position);
			price = fundPriceHistoryDAO.readLastPrice(sellFund.getFund_id());

			sellFundList = new SellFundBean[fundList.length];
			for (int i = 0; i < fundList.length; i++) {
				sellFundList[i] = new SellFundBean();
				sellFundList[i].setFund_id(fundList[i].getFund_id());
				sellFundList[i].setShares(fundList[i].getAvailable_shares());

				fund = fundDAO.read(fundList[i].getFund_id());
				sellFundList[i].setName(fund.getName());

				price = fundPriceHistoryDAO.readLastPrice(fundList[i]
						.getFund_id());
				if (price == null) {
					price = new FundPriceHistoryBean();
					price.setPrice(-1);
				}
				sellFundList[i].setPrice(price.getPrice());
			}
			request.setAttribute("sellFundList", sellFundList);

			return "customer/sell-fund.jsp";
		} catch (RollbackException e) {
			errors.add(e.getMessage());
			return "customer/error.jsp";
		} catch (FormBeanException e) {
			return "customer/error.jsp";
		}

	}
}
