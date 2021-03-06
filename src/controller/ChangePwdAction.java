package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.Model;
import model.CustomerDAO;
import model.EmployeeDAO;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import formbeans.ChangePwdForm;
import databeans.CustomerBean;
import databeans.EmployeeBean;
import databeans.UserBean;

public class ChangePwdAction extends Action {
	private FormBeanFactory<ChangePwdForm> formBeanFactory = FormBeanFactory.getInstance(ChangePwdForm.class);
	
	private CustomerDAO customerDAO;
	
	public ChangePwdAction(Model model) {
		customerDAO=model.getCustomerDAO();
	}

	public String getName() { return "ChangePwd.do"; }

	public String perform(HttpServletRequest request) {
		
		
    	// Set up error list
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        HttpSession session = request.getSession();
        try {
    		if (request.getSession(false).getAttribute("customer") == null) {
				errors.add("Wrong User");
    			return "login.do";
    		}
        	CustomerBean customer = (CustomerBean)request.getSession().getAttribute("customer");
	        // Load the form parameters into a form bean
	        ChangePwdForm form = formBeanFactory.create(request);
	        // If no params were passed, return with no errors so that the form will be
	        // presented (we assume for the first time).
	        if (!form.isPresent()) {
	            return "customer/change-pwd.jsp";
	        }
	        //System.out.println("Coming in after putting form");
	        // Check for any validation errors
	        errors.addAll(form.getValidationErrors());
	        if (errors.size() != 0) {
	            return "customer/change-pwd.jsp";
	        }
	        // Old password not correct
	     	if(!customer.getPassword().equals(form.getOldPassword())){
	     		errors.add("Enter correct old password to continue");
	     		return"customer/change-pwd.jsp";
	   		}
	        // new password same as old password
			if(customer.getPassword().equals(form.getNewPassword())){
				errors.add("New Password is same as Old password, please change and retry ");
				return"customer/change-pwd.jsp";
			}
			
			
			// Change the password"
        	customerDAO.setPassword(customer.getCustomer_id(),form.getNewPassword());
        	int newId=customer.getCustomer_id();
        	session.setAttribute("customer", null);
        	session.setAttribute("customer", customerDAO.readFromID(newId));
			request.setAttribute("message","Password changed for "+customer.getFirstname() +" "+customer.getLastname());
			
	        return "customer/change-pwd.jsp";
        } catch (RollbackException e) {
        	errors.add(e.toString());
        	return "error.jsp";
        } catch (FormBeanException e) {
        	errors.add(e.toString());
        	return "error.jsp";
        }
    }
}
