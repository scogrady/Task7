package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.EmployeeBean;
import formbeans.EmployeeForm;
import model.EmployeeDAO;
import model.Model;

public class CreateEmployeeAction extends Action {
	private EmployeeDAO employeeDAO;
	private FormBeanFactory<EmployeeForm> formBeanFactory = FormBeanFactory
			.getInstance(EmployeeForm.class);

	public CreateEmployeeAction(Model model) {
		employeeDAO = model.getEmployeeDAO();
	}

	public String getName() { return "CreateEmployee.do"; }

	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		try {
			EmployeeForm form = formBeanFactory.create(request);
			request.setAttribute("form", form);
			request.setAttribute("employeeList", employeeDAO.getEmployees());
			if (!form.isPresent()) {
				return "employee/create-employee.jsp";
			}

			errors.addAll(form.getValidationErrors());

			EmployeeBean employee = employeeDAO.read(form.getUsername());
			if (employee != null) {
				errors.add("Employee username is already exist.");
			}

			if (errors.size() != 0) {
				return "employee/create-employee.jsp";
			}

			// Create new UserBean
			employee = new EmployeeBean();
			employee.setUsername(form.getUsername());
			employee.setFirstname(form.getFirstname());
			employee.setLastname(form.getLastname());
			employee.setPassword(form.getPassword());
			employee.setStatus(0);
			employeeDAO.createAutoIncrement(employee);

			// Attach (this copy of) the user bean to the session
			//TODO ?
			HttpSession session = request.getSession(false);
			
			session.setAttribute("employee", employee);

			return "employee/create-employee.jsp";
			
		} catch (RollbackException e) {
			errors.add(e.getMessage());
			return "employee/create-employee.jsp";
		} catch (FormBeanException e) {
			errors.add(e.getMessage());
			return "employee/create-employee.jsp";
		}
	}

}


	
