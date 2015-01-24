package controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;
import databeans.FundBean;
import formbeans.FundForm;
import model.FundDAO;
import model.Model;

public class CreateFundAction extends Action{

	private FundDAO fundDAO;
	private FormBeanFactory<FundForm> formBeanFactory = FormBeanFactory
			.getInstance(FundForm.class);

	public String getName() {
		return "CreateFund.do";
	}

	public CreateFundAction(Model model) {
		fundDAO = model.getFundDAO();
	}
	
	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		FundForm form;
		try {
			request.setAttribute("fundList", fundDAO.getFunds());
			form = formBeanFactory.create(request);
			request.setAttribute("form", form);
			if (!form.isPresent()) {

				return "employee/create-fund.jsp";
			}

			errors.addAll(form.getValidationErrors());

			FundBean fund = fundDAO.readBySymbol(form.getSymbol());
			if (fund != null) {
				errors.add("Fund Symbol is already exist.");
			}

			if (errors.size() != 0) {
				return "employee/create-fund.jsp";
			}

			// Create new UserBean
			System.out.println("create fund now!");
			fund = new FundBean();
			fund.setDescription(form.getDescription());
			fund.setName(form.getName());
			fund.setSymbol(form.getSymbol());
			
			fundDAO.createAutoIncrement(fund);

			request.setAttribute("fundList", fundDAO.getFunds());
			
			return "employee/create-fund.jsp";
		} catch (RollbackException e) {
			errors.add(e.getMessage());
			return "employee/error.jsp";
		} catch (FormBeanException e) {
			errors.add(e.getMessage());
			return "employee/error.jsp";
		}
	}


}
