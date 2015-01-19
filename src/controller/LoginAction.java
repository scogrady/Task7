
package controller;
import javax.servlet.http.HttpServletRequest;
import model.Model;

public class LoginAction extends Action {	
	public LoginAction(Model model) {
	}

	public String getName() { return "login.do"; }
    
    public String perform(HttpServletRequest request) {
    	String role = request.getParameter("action");
	    if (role != null) {
	    	if (role.equals("Customer")) return "AccountInfo.do";
	        if (role.equals("Employee")) return "CreateEmployee.do";
	    } 
	    return "login.jsp"; 
    }
}
