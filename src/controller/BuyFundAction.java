package controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.CustomerDAO;
import model.FundDAO;
import model.FundPriceHistoryDAO;
import model.Model;
import model.TransactionDAO;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.BuyFundBean;
//import databeans.BuyFundBean;
import databeans.CustomerBean;
import databeans.FundBean;
import databeans.FundPriceHistoryBean;
import databeans.TransactionBean;
import formbeans.BuyForm;

public class BuyFundAction extends Action {

	private TransactionDAO transactionDAO;
	private CustomerDAO customerDAO;
	private FundDAO fundDAO;
	private FundPriceHistoryDAO fundPriceHistoryDAO;
	private FormBeanFactory<BuyForm> formBeanFactory = FormBeanFactory
			.getInstance(BuyForm.class);

	public BuyFundAction(Model model) {

		transactionDAO = model.getTransactionDAO();
		customerDAO = model.getCustomerDAO();
		fundDAO = model.getFundDAO();
		fundPriceHistoryDAO = model.getFundPriceHistoryDAO();
	}

	public String getName() {
		return "BuyFund.do";
	}

	public String perform(HttpServletRequest request) {
		// Set up the errors list
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		CustomerBean customer = (CustomerBean) request.getSession(false)
				.getAttribute("customer");
		Date date = new Date();
		FundBean[] fundList;
		BuyFundBean[] buyFundList;
		FundPriceHistoryBean price;
		long change;

		try {

			customer = customerDAO.readFromID(customer.getCustomer_id());
			request.getSession().setAttribute("customer", customer);

			fundList = fundDAO.getFunds();
			//System.out.println("==========" + fundList.length);
			buyFundList = new BuyFundBean[fundList.length];
			for (int i = 0; i < fundList.length; i++) {
				buyFundList[i] = new BuyFundBean();
				buyFundList[i].setFund_id(fundList[i].getFund_id());
				buyFundList[i].setName(fundList[i].getName());
				buyFundList[i].setSymbol(fundList[i].getSymbol());

				price = fundPriceHistoryDAO.readLastPrice(buyFundList[i]
						.getFund_id());
				if (price == null) {
					price = new FundPriceHistoryBean();
					price.setPrice(-1);
				}
				buyFundList[i].setPrice(price.getPrice());


			}

			request.setAttribute("buyFundList", buyFundList);

			BuyForm form = formBeanFactory.create(request);
			request.setAttribute("form", form);

			if (!form.isPresent()) {
				return "customer/buy-fund.jsp";
			}

			// check if it's within available balance?

			errors.addAll(form.getValidationErrors());
			
			if (errors.size() != 0) {
				return "customer/buy-fund.jsp";
			}
			
			//handle amount from form

			double num;
			if (form.getNum() == "") {
				num = 0;

			} else {
				num = Double.parseDouble(form.getNum());
			}



			TransactionBean buyFund = new TransactionBean();

			buyFund.setCustomer_id(customer.getCustomer_id());
			buyFund.setFund_id(Integer.parseInt(form.getFund_id()));
			buyFund.setGenerate_date(date);
			// buyFund.setShares();
			buyFund.setTransaction_type("Buy Fund");
			buyFund.setStatus("Pending");
			
			long amount = (long) (num * 100);
			
			buyFund.setAmount(amount);
			transactionDAO.create(buyFund);
			
			String message = "Successfully recieve your request.";
			request.setAttribute("message", message);

			// put it into queue
			// change available balance

			customer.setAvailable_cash(customer.getAvailable_cash() - amount);
			customerDAO.update(customer);

			customer = customerDAO.readFromID(customer.getCustomer_id());
			request.getSession().setAttribute("customer", customer);

			return "customer/buy-fund.jsp";
		} catch (RollbackException e) {
			errors.add(e.getMessage());
			return "customer/error.jsp";
		} catch (FormBeanException e) {
			return "customer/error.jsp";
		}

	}
}
