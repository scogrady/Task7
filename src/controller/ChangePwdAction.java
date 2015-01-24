package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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

        try {System.out.println("Coming in ChangePwd.do");
        	CustomerBean customer = (CustomerBean)request.getSession().getAttribute("customer");
	        // Load the form parameters into a form bean
	        ChangePwdForm form = formBeanFactory.create(request);
	        System.out.println(form.isPresent());
	        // If no params were passed, return with no errors so that the form will be
	        // presented (we assume for the first time).
	        if (!form.isPresent()) {
	        	System.out.println("put form");
	            return "customer/change-pwd.jsp";
	        }
	        System.out.println("Coming in after putting form");
	        // Check for any validation errors
	        errors.addAll(form.getValidationErrors());
	        System.out.println("after validation");
	        if (errors.size() != 0) {
	            return "customer/change-pwd.jsp";
	        }
	        // Old password not correct
	     	if(!customer.getPassword().equals(form.getOldPassword())){
	     		errors.add("Enter correct Old password to countinue");
	     		return"customer/change-pwd.jsp";
	   		}
	        // new password same as old password
			if(customer.getPassword().equals(form.getNewPassword())){
				errors.add("New Password is same as Old password, please change and retry ");
				return"customer/change-pwd.jsp";
			}
			
			
			// Change the password
        	customerDAO.setPassword(customer.getCustomer_id(),form.getNewPassword());
	
			request.setAttribute("message","Password changed for "+customer.getFirstname() +" "+customer.getLastname());
			
	        return "customer/success.jsp";
        } catch (RollbackException e) {
        	errors.add(e.toString());
        	return "employee/error.jsp";
        } catch (FormBeanException e) {
        	errors.add(e.toString());
        	return "employee/error.jsp";
        }
    }
}
