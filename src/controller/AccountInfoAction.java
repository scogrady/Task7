//Name: Hanze Xu
//Andrew ID: hanzex
//Title: Homework #9 ManageAction.java
//Course Number: 08-600
//Date: 2014-12-3


//oh my god
package controller;

//try pull
//try push
//try push helen
//try push from vic
//try push from vic 1
//try push avanti
//push from dell
//push try from dell
//try push vic 2
//try push vic 3
//push by Iris
// push helen2

//push helen3

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


/*
 * Sets up the request attributes for manage.jsp.

 * Sets the "userList" request attribute in order to display
 * the list of users on the navbar.
 * 
 * Sets the "favoriteList" request attribute in order to display
 * the list of user's favorites for management.
 * 
 * Forwards to manage.jsp.
 */
public class AccountInfoAction extends Action {

	
	
	private CustomerDAO customerDAO;
	private PositionDAO positionDAO;
	private FundDAO fundDAO;
	private FundPriceHistoryDAO fundPriceHistoryDAO;

	public AccountInfoAction(Model model) {
    	
		System.out.println("error0");
    	customerDAO =model.getCustomerDAO();
    	positionDAO =model.getPositionDAO();
    	fundDAO =model.getFundDAO();
    	fundPriceHistoryDAO =model.getFundPriceHistoryDAO();

	}

	public String getName() {System.out.println("error getname");
	return "AccountInfo.do"; }

	public String perform(HttpServletRequest request) {
        // Set up the errors list
		System.out.println("error http request");
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
		try {System.out.println("error11");
            // Set up customer list for nav bar
			
			request.setAttribute("customerList",customerDAO.getCustomers());
			
			System.out.println("error1");
			
			//-------------------------setting up session for test purpose-----------------
			
			 CustomerBean customer = customerDAO.read(1);
			 System.out.println("error2");
			 HttpSession session = request.getSession();
			 System.out.println("error3");
		     session.setAttribute("customer",customer);
		     System.out.println("error4");
		    //----------------------------------------------------------------------------- 
		     
		    //set up portfolio list for customer 
			request.setAttribute("position",positionDAO.readByCustomerID(customer.getCustomer_id()));
			//to get fund symbol list
			request.setAttribute("fundTicker", fundDAO.getFunds());
			//to get fund price 
			request.setAttribute("priceList", fundPriceHistoryDAO.getFundPriceHistorys());
			System.out.println("error5");
			
			
			//request.setAttribute("userList",userDAO.getUsers());
			//UserBean user = (UserBean) request.getSession(false).getAttribute("user");
        	//FavoriteBean[] favoriteList = favoriteDAO.getUserFavorites(user.getUserID());
	        //request.setAttribute("favoriteList",favoriteList);

	        return "customer/account-info.jsp";
        } catch (RollbackException e) {
        	System.out.print("error");
        	errors.add(e.getMessage());
        	return "error.jsp";
        }
    }
}
