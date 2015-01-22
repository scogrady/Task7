package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.Model;
import model.EmployeeDAO;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import formbeans.ChangePwdForm;
import databeans.EmployeeBean;

public class ChangeEmployeePwdAction extends Action {
	private FormBeanFactory<ChangePwdForm> formBeanFactory = FormBeanFactory.getInstance(ChangePwdForm.class);
	
	private EmployeeDAO employeeDAO;
	
	public ChangeEmployeePwdAction(Model model) {
		employeeDAO=model.getEmployeeDAO();
	}

	public String getName() { return "ChangeEmployeePwd.do"; }

	public String perform(HttpServletRequest request) {
    	// Set up error list
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);

        try {
        	EmployeeBean employee = (EmployeeBean)request.getSession().getAttribute("employee");
        	// Load the form parameters into a form bean
	        ChangePwdForm form = formBeanFactory.create(request);
	        
	        // If no params were passed, return with no errors so that the form will be
	        // presented (we assume for the first time).
	        if (!form.isPresent()) {
	        	
	            return "employee/change-pwd.jsp";
	        }
	
	        // Check for any validation errors
	        errors.addAll(form.getValidationErrors());
	        if (errors.size() != 0) {
	            return "employee/change-pwd.jsp";
	        }
	     // Old password not correct
	     	if(!employee.getPassword().equals(form.getOldPassword())){
	     		errors.add("Enter correct Old password to countinue");
	     		return"employee/change-pwd.jsp";
	   		}
	        // new password same as old password
			if(employee.getPassword().equals(form.getNewPassword())){
				errors.add("New Password is same as Old password, please change and retry ");
				return"employee/change-pwd.jsp";
			}
			// Change the password
        	employeeDAO.setPassword(employee.getUsername(),form.getNewPassword());
	
			request.setAttribute("message","Password changed for "+employee.getFirstname() +" "+employee.getLastname());
			
	        return "employee/success.jsp";
        } catch (RollbackException e) {
        	errors.add(e.toString());
        	return "error.jsp";
        } catch (FormBeanException e) {
        	errors.add(e.toString());
        	return "error.jsp";
        }
    }
}
