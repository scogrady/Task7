package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;
import databeans.CustomerBean;
import databeans.PositionBean;
import databeans.TransactionBean;
import model.CustomerDAO;
import model.FundPriceHistoryDAO;
import model.Model;
import model.PositionDAO;
import model.TransactionDAO;

public class TransactionDayAction extends Action {
	//private FormBeanFactory<TransactionForm> formBeanFactory = FormBeanFactory.getInstance(TransactionForm.class);
	TransactionDAO transactionDAO;
	CustomerDAO customerDAO;
	FundPriceHistoryDAO fundPriceHistoryDAO;
	PositionDAO positionDAO;
	public TransactionDayAction(Model model) {
		transactionDAO = model.getTransactionDAO();
		customerDAO = model.getCustomerDAO();
		fundPriceHistoryDAO = model.getFundPriceHistoryDAO();
		positionDAO = model.getPositionDAO();
	}

	public String getName() { return "TransactionDay.do"; }

	public String perform(HttpServletRequest request) {
		try {
			//TransactionForm transactionForm = formBeanFactory.create(request);
			//not present
			//if (!transactionForm.isPresent()) {
				//return "transaction-day.jsp";
			//}
			
			Date date = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("date"));
			//transactionForm.getDate();
			TransactionBean[] transactions = transactionDAO.readByDate(null);
			for (TransactionBean transaction : transactions) {
				int customerId = transaction.getCustomer_id();
				CustomerBean customer = customerDAO.read(customerId);
				String transactionType = transaction.getTransaction_type();
				
				//sell fund
				if (transactionType.equals("Sell Fund")) {
					int fundId = transaction.getFund_id();
					long price = fundPriceHistoryDAO.readByDateAndFundID((java.sql.Date)date, fundId)[0].getPrice();
					double share = transaction.getShares(); 
					//positionDAO.readByCustomerIDAndFundId(customerId, fundId)[0].getShares();
					long moneyGot = (long)share / 1000 * price;
					
					customer.setCurrent_cash(customer.getCurrent_cash() + moneyGot);
					customer.setAvailable_cash(customer.getAvailable_cash());
					customerDAO.update(customer);
					
					transaction.setExecute_date(date);
					transaction.setStatus("completed");
					transactionDAO.update(transaction);
					
					continue;
				} 
				//buy fund
				if (transactionType.equals("Buy Fund")) {
					int fundId = transaction.getFund_id();
					long price = fundPriceHistoryDAO.readByDateAndFundID((java.sql.Date)date, fundId)[0].getPrice();
					long amount = transaction.getAmount();
					long shares = Math.round((amount / price) * 1000);
					
					customer.setCurrent_cash(customer.getCurrent_cash() - amount);
					customer.setAvailable_cash(customer.getCurrent_cash());
					customerDAO.update(customer);
					
					PositionBean position = positionDAO.readByCustomerIDAndFundId(customer.getCustomer_id(), fundId)[0];
					position.setShares(position.getShares() - shares);
					positionDAO.update(position);
					
					transaction.setExecute_date(date);
					transaction.setStatus("completed");
					transactionDAO.update(transaction);
					
					continue;
				}
								
				//request check
				if (transactionType.equals("Request Check")) {
					long amount = transaction.getAmount();
					
					customer.setCurrent_cash(customer.getCurrent_cash() + amount);
					customer.setAvailable_cash(customer.getAvailable_cash() + amount);
					continue;
				}				
				
				//deposit check
				if (transactionType.equals("Deposit Check")) {
					long amount = transaction.getAmount();
					
					customer.setCurrent_cash(customer.getCurrent_cash() - amount);
					customer.setAvailable_cash(customer.getAvailable_cash() - amount);
					continue;
				}		
			}
			
		} 
		catch (RollbackException e) {
			e.printStackTrace();
		}	
		//catch (FormBeanException e) {
		//	e.printStackTrace();
		//} 
		catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "employee/transaction-day.jsp";
    }
}
