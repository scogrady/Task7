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
import org.genericdao.Transaction;
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

		try {
			if (request.getSession(false).getAttribute("customer") == null) {
				errors.add("Wrong User");
				return "login.do";
			}
			// Transaction.begin();

			customer = customerDAO.readFromID(customer.getCustomer_id());
			request.getSession().setAttribute("customer", customer);

			fundList = fundDAO.getFunds();
			// System.out.println("==========" + fundList.length);
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
			
			if (errors.size() != 0) {
				return "customer/buy-fund.jsp";
			}

			long num = -1;

			try {

				num = (long) (Double.parseDouble(form.getNum()) * 100);
				if (num > customer.getAvailable_cash()) {
					errors.add("Not enough money in Available Cash");
				}
			} catch (NumberFormatException e) {
				errors.add("Please double check your input.");
			}

			errors.addAll(form.getValidationErrors());

			customer = customerDAO.readFromID(customer.getCustomer_id());
			request.getSession().setAttribute("customer", customer);

			if (errors.size() != 0) {
				return "customer/buy-fund.jsp";
			}

			Transaction.begin();

			customer = customerDAO.readFromID(customer.getCustomer_id());
			TransactionBean buyFund = new TransactionBean();

			buyFund.setCustomer_id(customer.getCustomer_id());
			buyFund.setFund_id(Integer.parseInt(form.getFund_id()));
			buyFund.setGenerate_date(date);
			buyFund.setTransaction_type("Buy Fund");
			buyFund.setStatus("Pending");

			buyFund.setAmount(num);
			transactionDAO.create(buyFund);

			String message = "Successfully recieved your request.";
			request.setAttribute("message", message);
			request.setAttribute("form", null);
			
			customer.setAvailable_cash(customer.getAvailable_cash() - num);
			customerDAO.update(customer);
			Transaction.commit();

			customer = customerDAO.readFromID(customer.getCustomer_id());
			request.getSession().setAttribute("customer", customer);

			return "customer/buy-fund.jsp";
		} catch (RollbackException e) {
			errors.add(e.getMessage());
			return "customer/error.jsp";
		} catch (FormBeanException e) {
			return "customer/error.jsp";
		} finally {
			if (Transaction.isActive())
				Transaction.rollback();
		}

	}
}
