
package controller;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.CustomerBean;
import databeans.EmployeeBean;
import formbeans.LoginForm;
import model.CustomerDAO;
import model.EmployeeDAO;
import model.Model;

public class LoginAction extends Action {	
	private FormBeanFactory<LoginForm> formBeanFactory = FormBeanFactory.getInstance(LoginForm.class);
	
	private CustomerDAO customerDAO;
	private EmployeeDAO employeeDAO;
	
	public LoginAction(Model model) {
		customerDAO = model.getCustomerDAO();
		employeeDAO = model.getEmployeeDAO();
	}

	public String getName() { return "getTokenAction.do"; }
    
    public String perform(HttpServletRequest request) {
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
    	String role = request.getParameter("action");
    	
    	LoginForm form;
        HttpSession session = request.getSession(false);
        if (session.getAttribute("customer") != null) {
        	return "AccountInfo.do";
        }
        if (session.getAttribute("employee") != null) {
        	return "CreateEmployee.do";
        }
		try {
			form = formBeanFactory.create(request);
			request.setAttribute("form",form);
			
			if (!form.isPresent()) {
				return "login.jsp";
			}

			// Any validation errors?
			errors.addAll(form.getValidationErrors());
			if (errors.size() != 0) {
				return "login.jsp";
			}
			
			if (role.equals("Customer")) {
				CustomerBean customer = customerDAO.readByUsername(form.getUsername());
				if (customer == null) {
					errors.add("Customer Not Found");
					return "login.jsp";
				}
			
				if (!customer.checkPassword(form.getPassword())) {
					errors.add("Invalid Password");
					return "login.jsp";
				}
		        session.setAttribute("customer", customer);
				return "AccountInfo.do";
			}
			
			if (role.equals("Employee")) {
				EmployeeBean employee = employeeDAO.readByUsername(form.getUsername());
				if (employee == null) {
					errors.add("Employee Not Found");
					return "login.jsp";
				}
			
				if (!employee.checkPassword(form.getPassword())) {
					errors.add("Invalid Password");
					return "login.jsp";
				}	
				
				session.setAttribute("employee", employee);
				return "CreateEmployee.do";
			}
			if (role != null) {
				if (role.equals("Customer")) return "AccountInfo.do";
				if (role.equals("Employee")) return "CreateEmployee.do";
			} 
		}
		catch (FormBeanException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (RollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return "login.jsp"; 
    }
}
