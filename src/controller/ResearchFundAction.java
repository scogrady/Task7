package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.FundDAO;
import model.Model;

import org.genericdao.RollbackException;

import databeans.FavoriteBean;
import databeans.FundBean;
import databeans.UserBean;

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
public class ResearchFundAction extends Action {

	private FundDAO fundDAO;

	public ResearchFundAction(Model model) {
		fundDAO = model.getFundDAO();
		try {
			FundBean[] list = fundDAO.getFunds();
			for(int i=0;i<list.length;i++){
				System.out.println(list[i].getName());
			}
		} catch (RollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
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
			return "customer/research-fund.jsp";
		} catch (RollbackException e) {
			errors.add(e.getMessage());
			return "error.jsp";
		}
    }
}
