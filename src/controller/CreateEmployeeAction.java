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

	public String getName() {
		return "CreateEmployee.do";
	}

	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		EmployeeForm form;
		try {
			request.setAttribute("employeeList", employeeDAO.getEmployees());
			form = formBeanFactory.create(request);
			request.setAttribute("form", form);
			if (!form.isPresent()) {
				System.out.println("create employee form is not present!");
				return "employee/create-employee.jsp";
			}

			errors.addAll(form.getValidationErrors());

			EmployeeBean employee = employeeDAO.read(form.getUsername());
			if (employee != null) {
				System.out.println(" employee  is already exist");	
				errors.add("Employee username is already exist.");
			}

			if (errors.size() != 0) {
				return "employee/create-employee.jsp";
			}

			// Create new UserBean
			employee = new EmployeeBean();
			employee.setUsername(form.getUsername());
			System.out.println("username = "+form.getUsername());
			
			employee.setFirstname(form.getFirstname());
			System.out.println("first = "+form.getFirstname());
			employee.setLastname(form.getLastname());
			System.out.println("last = "+form.getLastname());
			employee.setPassword(form.getPassword());
			System.out.println("pass = "+form.getPassword());
			employee.setStatus(0);
			employeeDAO.create(employee);
			System.out.println("create employee now!");
			String message = "Successfully create a new employee account.";
			request.setAttribute("message", message);

			request.setAttribute("employeeList", employeeDAO.getEmployees());
			return "employee/create-employee.jsp";

		} catch (RollbackException e) {
			errors.add(e.getMessage());
			return "employee/error.jsp";
		} catch (FormBeanException e) {
			errors.add(e.getMessage());
			return "employee/error.jsp";
		}
	}

}
