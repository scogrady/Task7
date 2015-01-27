
package controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.RollbackException;

import databeans.CustomerBean;
import databeans.EmployeeBean;
import model.CustomerDAO;
import model.EmployeeDAO;
import model.Model;

public class LogoutAction extends Action {	
	
	private CustomerDAO customerDAO;
	private EmployeeDAO employeeDAO;
	
	public LogoutAction(Model model) {
		customerDAO = model.getCustomerDAO();
		employeeDAO = model.getEmployeeDAO();
	}

	public String getName() { return "logout.do"; }
    
    public String perform(HttpServletRequest request) {
    		HttpSession session = request.getSession(false);
    		if (session.getAttribute("customer") != null) {
    			session.setAttribute("customer", null);
    		}
    		if (session.getAttribute("employee") != null) {
    			session.setAttribute("employee", null);
    		}
            return "login.jsp";
    }
}
