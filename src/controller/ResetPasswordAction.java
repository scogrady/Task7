package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import model.Model;
import databeans.CustomerBean;
import formbeans.IdForm;
import model.CustomerDAO;

public class ResetPasswordAction extends Action {
	
	private FormBeanFactory<IdForm> formBeanFactory = FormBeanFactory.getInstance(IdForm.class);
	
	private CustomerDAO customerDAO;
	
	public ResetPasswordAction(Model model) {
		customerDAO = model.getCustomerDAO();
	}

	public String getName() { return "ResetPassword.do"; }

	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		try{
			request.setAttribute("customerList", customerDAO.getCustomers());
			IdForm form = formBeanFactory.create(request);
			
			if(!form.isPresent()){
				return "employee/reset-password.jsp";
			}
			
			int id = form.getIdAsInt();
			//customerDAO.resetPassword(id);
			request.setAttribute("id", id);
			
			return "employee/reset-password-form.jsp";
	    
	} catch (RollbackException e) {
		errors.add(e.getMessage());
		return "employee/error.jsp";
	} catch (FormBeanException e) {
		errors.add(e.getMessage());
		return "employee/error.jsp";
	}
    }
}
