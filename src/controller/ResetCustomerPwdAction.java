package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import model.Model;
import databeans.CustomerBean;
import formbeans.ChangePwdForm;
import formbeans.ResetForm;
import model.CustomerDAO;

public class ResetCustomerPwdAction extends Action {
	
	private FormBeanFactory<ResetForm> formBeanFactory = FormBeanFactory.getInstance(ResetForm.class);
	
	private CustomerDAO customerDAO;
	
	public ResetCustomerPwdAction(Model model) {
		customerDAO = model.getCustomerDAO();
	}

	public String getName() { return "ResetCustomerPwd.do"; }

	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		try{
			request.setAttribute("customerList", customerDAO.getCustomers());
			ResetForm form = formBeanFactory.create(request);
			int id = Integer.parseInt((String) request.getParameter("id"));
			System.out.println("id:"+id);
			request.setAttribute("id", id);
			customerDAO.resetPassword(id,form.getNewPassword());
			request.setAttribute("message","Password Reset sucessfull");
			return "employee/success.jsp";
	    
	} catch (RollbackException e) {
		errors.add(e.getMessage());
		return "employee/error.jsp";
	} catch (FormBeanException e) {
		errors.add(e.getMessage());
		return "employee/error.jsp";
	}
    }
}
