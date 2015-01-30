package formbeans;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

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
			errors.add("Email address is required");
		}
		if (!Pattern.matches("[\\w]+", username)) {
			errors.add("Username should be all characters.");
		}
		if(username.length()>30||username.length()<3){
			errors.add("Username shoule more than 3 digit and less than 30 digit.");
		}
		if (firstname == null || firstname.length() == 0) {
			errors.add("First Name is required");
		}
		if (!Pattern.matches("[A-Za-z ]+", firstname)) {
			errors.add("First name should be all characters.");
		}
		
		if(firstname.length()>30||firstname.length()<2){
			errors.add("First name shoule more than 2 digit and less than 30 digit");
		}
		if (lastname == null || lastname.length() == 0) {
			errors.add("Last Name is required");
		}
		if (!Pattern.matches("[A-Za-z ]+", lastname)) {
			errors.add("Last name should be all characters.");
		}
		
		if(lastname.length()>30||lastname.length()<2){
			errors.add("Last name shoule more than 2 digit and less than 30 digit.");
		}
		if (errors.size() > 0) {
			return errors;
		}
		if (password == null || password.length() == 0) {
			errors.add("Password is required");
		}
		if(password.length()>20||password.length()<3){
			errors.add("Password should be more than 3 digit and less than 20 digit.");
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
