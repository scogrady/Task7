
package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.Model;
import model.CustomerDAO;
import model.PositionDAO;
import model.FundDAO;
import model.FundPriceHistoryDAO;

import org.genericdao.RollbackException;

import databeans.CustomerBean;
import databeans.PositionBean;
import databeans.UserBean;

public class AccountInfoAction extends Action {

	private CustomerDAO customerDAO;
	private PositionDAO positionDAO;
	private FundDAO fundDAO;
	private FundPriceHistoryDAO fundPriceHistoryDAO;

	public AccountInfoAction(Model model) {
    	
		
    	customerDAO =model.getCustomerDAO();
    	positionDAO =model.getPositionDAO();
    	fundDAO =model.getFundDAO();
    	fundPriceHistoryDAO =model.getFundPriceHistoryDAO();

	}

	public String getName() {
	return "AccountInfo.do"; }

	public String perform(HttpServletRequest request) {
        // Set up the errors list
		
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
		try {
			 CustomerBean customer = (CustomerBean) request.getSession(false).getAttribute("customer");
		    //set up portfolio list for customer 
			request.setAttribute("position",positionDAO.readByCustomerID(customer.getCustomer_id()));
			//to get fund symbol list
			request.setAttribute("fundTicker", fundDAO.getFunds());
			//to get fund price 
			request.setAttribute("priceList", fundPriceHistoryDAO.getFundPriceHistorys());
			
			
			//request.setAttribute("userList",userDAO.getUsers());
			//UserBean user = (UserBean) request.getSession(false).getAttribute("user");
        	//FavoriteBean[] favoriteList = favoriteDAO.getUserFavorites(user.getUserID());
	        //request.setAttribute("favoriteList",favoriteList);

	        return "customer/account-info.jsp";
        } catch (RollbackException e) {
        	errors.add(e.getMessage());
        	return "customer/error.jsp";
        }
    }
}
