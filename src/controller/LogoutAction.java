
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

public class LogoutAction extends Action {	
	private FormBeanFactory<LoginForm> formBeanFactory = FormBeanFactory.getInstance(LoginForm.class);
	
	private CustomerDAO customerDAO;
	private EmployeeDAO employeeDAO;
	
	public LogoutAction(Model model) {
		customerDAO = model.getCustomerDAO();
		employeeDAO = model.getEmployeeDAO();
	}

	public String getName() { return "logout.do"; }
    
    public String perform(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        session.setAttribute("user",null);
	    return "login.jsp"; 
    }
}
