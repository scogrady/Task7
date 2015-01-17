//Name: Hanze Xu
//Andrew ID: hanzex
//Title: Homework #9 LoginAction.java
//Course Number: 08-600
//Date: 2014-12-3
package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.Model;
import model.UserDAO;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.UserBean;
import formbeans.LoginForm;

/*
 * Processes the parameters from the form in login.jsp.
 * If successful, set the "user" session attribute to the
 * user's User bean and then redirects to manage page.
 */
public class LoginAction extends Action {
	private FormBeanFactory<LoginForm> formBeanFactory = FormBeanFactory.getInstance(LoginForm.class);
	
	private UserDAO userDAO;

	public LoginAction(Model model) {
		userDAO = model.getUserDAO();
	}

	public String getName() { return "login.do"; }
    
    public String perform(HttpServletRequest request) {
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        try {
	    	LoginForm form = formBeanFactory.create(request);
        	request.setAttribute("userList", userDAO.getUsers());
	        request.setAttribute("form",form);
	        String role = request.getParameter("action");
	        // If no params were passed, return with no errors so that the form will be
	        // presented (we assume for the first time).
	        /*if (!form.isPresent()) {
	            return "login.jsp";
	        }

	        // Any validation errors?
	        errors.addAll(form.getValidationErrors());
	        if (errors.size() != 0) {
	            return "login.jsp";
	        }

	        // Look up the user
	        UserBean user = userDAO.read(form.getEmail());
	        
	        if (user == null) {
	            errors.add("User Name not found");
	            return "login.jsp";
	        }

	        // Check the password
	       	if (!form.getPassword().equals(user.getPassword())) {
	            errors.add("Incorrect password");
	            return "login.jsp";
	       	}
	
	        // Attach (this copy of) the user bean to the session
	        HttpSession session = request.getSession();
	        session.setAttribute("user",user);*/
	        if (role != null) {
	        	if (role.equals("Customer")) return "AccountInfo.do";
	        	if (role.equals("Employee")) return "EmployeeIndex.do";
	        } 
	        return "login.jsp";
        } catch (RollbackException e) {
        	errors.add(e.getMessage());
        	return "error.jsp";
        } catch (FormBeanException e) {
        	errors.add(e.getMessage());
        	return "error.jsp";
        }
    }
}
