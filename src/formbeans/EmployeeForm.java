package formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class EmployeeForm extends FormBean {
	private String username;
	private String password;
	private String confirm;
	private String firstname;
	private String lastname;
	private String action;
	
	public String getConfirm() {
		return confirm;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public String getFirstname() {
		return firstname;
	}
	public String getLastname() {
		return lastname;
	}

	public void setUsername(String username) {
		this.username = trimAndConvert(username, "<>\"");
	}
	
	public void setFirstname(String firstname) {
		this.firstname = trimAndConvert(firstname, "<>\"");
	}
	public void setLastname(String lastname) {
		this.lastname = trimAndConvert(lastname, "<>\"");
	}
	public void setPassword(String s) {
		password = s.trim();
	}

	public void setConfirm(String s) {
		confirm = s.trim();
	}

	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	


	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (username == null || username.length() == 0) {
			errors.add("Employee username is required");
		}

		if (firstname == null || firstname.length() == 0) {
			errors.add("First Name is required");
		}

		if (lastname == null || lastname.length() == 0) {
			errors.add("Last Name is required");
		}

		if (password == null || password.length() == 0) {
			errors.add("Password is required");
		}

		if (confirm == null || confirm.length() == 0) {
			errors.add("Confirm Password is required");
		}
		if (errors.size() > 0) {
			return errors;
		}
		if (!password.equals(confirm)) {
			errors.add("Passwords are not the same");
		}
		if (action == null)
			errors.add("Button is required");

		if (!action.equals("Create"))
			errors.add("Invalid button");

		return errors;
	}
}
