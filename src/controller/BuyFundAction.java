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

import databeans.CustomerBean;
import databeans.TransactionBean;
import formbeans.BuyForm;
import formbeans.FavoriteForm;

public class BuyFundAction extends Action {

	private TransactionDAO transactionDAO;
	private CustomerDAO customerDAO;
	private FundDAO fundDAO;
	private FundPriceHistoryDAO fundPriceHistoryDAO;
	private FormBeanFactory<BuyForm> formBeanFactory;


	public BuyFundAction(Model model) {
		transactionDAO = model.getTransactionDAO();
		customerDAO = model.getCustomerDAO();
	}

	public String getName() {
		return "BuyFund.do";
	}

	public String perform(HttpServletRequest request) {
		
	    return "customer/buy-fund.jsp";
   
		// Set up the errors list
		
		
			
			

			// check if it's within available balance?

			
			
			
			
			

<<<<<<< HEAD
		
=======
			TransactionBean buyFund = new TransactionBean();

			buyFund.setCustomer_id(customer.getCustomer_id());
			buyFund.setFund_id(form.getFund_id());
			buyFund.setGenerate_date(date);
			// buyFund.setShares();
			buyFund.setTransaction_type("Buy Fund");
			buyFund.setStatus("Pending");
			long amount = form.getNum_1() * 100 + form.getNum_2();
			buyFund.setAmount(amount);
			transactionDAO.create(buyFund);

			// put it into queue
			// change available balance
			
			customer.setAvailable_cash(customer.getAvailable_cash() - amount);
			customerDAO.update(customer);
			
			customer = customerDAO.readFromID(customer.getCustomer_id());
			request.getSession().setAttribute("customer", customer);
>>>>>>> branch 'master' of https://github.com/scogrady/Task7.git
			
			
			
		} 
	}

