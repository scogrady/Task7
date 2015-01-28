package controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import databeans.TransactionBean;
import model.CustomerDAO;
import model.FundDAO;
import model.FundPriceHistoryDAO;
import model.Model;
import model.PositionDAO;
import model.TransactionDAO;
import formbeans.PriceForm;

public class TransitionDayAction extends Action {
	private FormBeanFactory<PriceForm> formBeanFactory = FormBeanFactory.getInstance(PriceForm.class);
	TransactionDAO transactionDAO;
	CustomerDAO customerDAO;
	FundPriceHistoryDAO fundPriceHistoryDAO;
	PositionDAO positionDAO;
	FundDAO fundDAO;
	public TransitionDayAction(Model model) {
		transactionDAO = model.getTransactionDAO();
		customerDAO = model.getCustomerDAO();
		fundPriceHistoryDAO = model.getFundPriceHistoryDAO();
		positionDAO = model.getPositionDAO();
		fundDAO = model.getFundDAO();
	}

	public String getName() { return "Transition.do"; }

	public String perform(HttpServletRequest request) {
		try {

			FundBean[] fundList = fundDAO.getFunds();
			request.setAttribute("fundList", fundList);
			
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			FundPriceHistoryBean fundPriceHistory =  fundPriceHistoryDAO.readByLastDate();
			Date lastDate = null;
			if (fundPriceHistory != null) {
				lastDate = fundPriceHistory.getPrice_date();
				request.setAttribute("lastDate", formatter.format(lastDate));
			} else {
				request.setAttribute("lastDate", null);
			}
			
			PriceForm form = formBeanFactory.create(request);
			if (!form.isPresent()) {
				return "employee/transition-day.jsp";
			}
			request.setAttribute("form", form);
			List<String> errors = new ArrayList<String>();
			request.setAttribute("errors", errors);
			
			errors.addAll(form.getValidationErrors());
			Date date = formatter.parse(form.getDate());
			if (lastDate != null && date.compareTo(lastDate) <= 0) {
				errors.add("Transition Day should be later than last transition day.");
			}			
			if (errors.size() != 0) {
				return "employee/transition-day.jsp";
			}

			
			
			//get form data
			if (request.getParameter("date") == null) {
				return "employee/transition-day.jsp";
			}
			
			for (int i = 0; i < form.getId().length; i++) {
				int fundId = form.getId()[i];
				long price = Math.round(form.getPrice()[i] * 100);
				FundPriceHistoryBean fundPrice = new FundPriceHistoryBean();
				fundPrice.setFund_id(fundId);
				fundPrice.setPrice(price);
				fundPrice.setPrice_date(new java.sql.Date(date.getTime()));
				fundPriceHistoryDAO.create(fundPrice);
			}
				
			TransactionBean[] transactions = transactionDAO.readByDate(null);
			synchronized(this) { 
			for (TransactionBean transaction : transactions) {
				int customerId = transaction.getCustomer_id();
				CustomerBean customer = customerDAO.read(customerId);
				String transactionType = transaction.getTransaction_type();
				
				//sell fund
				if (transactionType.equals("Sell Fund")) {
					int fundId = transaction.getFund_id();
					long price = fundPriceHistoryDAO.readByDateAndFundID(new java.sql.Date(date.getTime()), fundId)[0].getPrice();
					long shares = transaction.getShares(); 
					//positionDAO.readByCustomerIDAndFundId(customerId, fundId)[0].getShares();
					long moneyGot = Math.round(shares * 1.000000 / 1000 * price);
					
					customer.setCurrent_cash(customer.getCurrent_cash() + moneyGot);
					customer.setAvailable_cash(customer.getCurrent_cash());
					customerDAO.update(customer);
					
					
					PositionBean position = positionDAO.readByCustomerIDAndFundId(customer.getCustomer_id(), fundId)[0];
					if (position.getShares() - shares < 0.001) {
						positionDAO.delete(position);
					} else {
						position.setShares(position.getShares() - shares);
						position.setAvailable_shares(position.getAvailable_shares());
						positionDAO.update(position);
					}
					
					transaction.setExecute_date(date);
					transaction.setStatus("completed");
					transaction.setAmount(moneyGot);
					transactionDAO.update(transaction);
					
					continue;
				} 
				//buy fund
				if (transactionType.equals("Buy Fund")) {
					int fundId = transaction.getFund_id();
					long price = fundPriceHistoryDAO.readByDateAndFundID(new java.sql.Date(date.getTime()), fundId)[0].getPrice();
					long amount = transaction.getAmount();
					long shares = Math.round((amount * 1.000000 / price) * 1000);					
					long newAmount = Math.round(shares * price / 1000.000);
					
					
					customer.setCurrent_cash(customer.getCurrent_cash() - newAmount < 0 ? 0 : customer.getCurrent_cash() - newAmount);
					customer.setAvailable_cash(customer.getCurrent_cash());
					customerDAO.update(customer);
					
					PositionBean[] positions = positionDAO.readByCustomerIDAndFundId(customer.getCustomer_id(), fundId);
					if (positions.length == 0) {
						PositionBean position = new PositionBean();
						position.setCustomer_id(customer.getCustomer_id());
						position.setFund_id(fundId);
						position.setShares(shares);
						position.setAvailable_shares(shares);
						positionDAO.create(position);
					} else {
						PositionBean position = positionDAO.readByCustomerIDAndFundId(customer.getCustomer_id(), fundId)[0];
						position.setShares(position.getShares() + shares);
						position.setAvailable_shares(position.getShares());
						positionDAO.update(position);						
					}
					
					transaction.setExecute_date(date);
					transaction.setStatus("completed");
					transaction.setShares(shares);
					transactionDAO.update(transaction);
					
					continue;
				}
								
				//request check
				if (transactionType.equals("Request Check")) {
					long amount = transaction.getAmount();
					
					customer.setCurrent_cash(customer.getCurrent_cash() - amount);
					customer.setAvailable_cash(customer.getCurrent_cash());
					customerDAO.update(customer);
					
					transaction.setExecute_date(date);
					transaction.setStatus("completed");
					transactionDAO.update(transaction);					
					continue;
				}				
				
				//deposit check
				if (transactionType.equals("Deposit Check")) {
					long amount = transaction.getAmount();
					
					customer.setCurrent_cash(customer.getCurrent_cash() + amount);
					customer.setAvailable_cash(customer.getCurrent_cash());
					customerDAO.update(customer);
					
					
					transaction.setExecute_date(date);
					transaction.setStatus("completed");
					transactionDAO.update(transaction);
					continue;
				}
			}
			String message = "Successfully simulate transition day.";
			request.setAttribute("message", message);
			request.setAttribute("lastDate", form.getDate());
			request.setAttribute("form", null );
			return "employee/transition-day.jsp";	
			}
		} 
		catch (RollbackException e) {
			e.printStackTrace();
			return "error.jsp";
		}	
		catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error.jsp";
		}
		catch (FormBeanException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error.jsp";
		}		
    }
}
