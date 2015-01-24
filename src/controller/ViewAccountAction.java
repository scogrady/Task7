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
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.CustomerBean;
import databeans.PositionBean;
import databeans.UserBean;
import formbeans.ChangePwdForm;
import formbeans.IdForm;



public class ViewAccountAction extends Action {

	
	
	private CustomerDAO customerDAO;
	private PositionDAO positionDAO;
	private FundDAO fundDAO;
	private FundPriceHistoryDAO fundPriceHistoryDAO;
	private FormBeanFactory<IdForm> formBeanFactory = FormBeanFactory.getInstance(IdForm.class);
	public ViewAccountAction(Model model) {
    	
		
    	customerDAO =model.getCustomerDAO();
    	positionDAO =model.getPositionDAO();
    	fundDAO =model.getFundDAO();
    	fundPriceHistoryDAO =model.getFundPriceHistoryDAO();

	}

	public String getName() {
	return "ViewAccount.do"; }

	public String perform(HttpServletRequest request) {
        // Set up the errors list
		
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
		try {
			 IdForm form = formBeanFactory.create(request);
			 int id;
			if(request.getParameter("customer_id")==null)
				 id=1;
			 else
			 id = Integer.parseInt(request.getParameter("customer_id"));
			request.setAttribute("customerList", customerDAO.getCustomers());
			request.setAttribute("id", id);
			request.setAttribute("customer", customerDAO.readFromID(id)); 
		    //set up portfolio list for customer 
			request.setAttribute("position",positionDAO.readByCustomerID(id));
			//to get fund symbol list
			request.setAttribute("fundTicker", fundDAO.getFunds());
			//to get fund price 
			request.setAttribute("priceList", fundPriceHistoryDAO.getFundPriceHistorys());
		
			return "employee/view-account.jsp";
        } catch (RollbackException e) {
        	errors.add(e.getMessage());
        	return "error.jsp";
        }
		 catch (FormBeanException e) {
				errors.add(e.getMessage());
				return "error.jsp";

			}
    }
}
