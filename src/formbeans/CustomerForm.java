package formbeans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.mybeans.form.FormBean;

public class CustomerForm extends FormBean {
	private String username;
	private String password;
	private String confirm;
	private String firstname;
	private String lastname;
	private String addr_line1;
	private String addr_line2;
	private String city;
	private String state;
	private String zip;
	private long current_cash;
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

	

	public String getAddr_line1() {
		return addr_line1;
	}

	public String getAddr_line2() {
		return addr_line2;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String getZip() {
		return zip;
	}

	public long getCurrent_cash() {
		return current_cash;
	}

	

	public void setAddr_line1(String addr_line1) {
		this.addr_line1 = trimAndConvert(addr_line1, "<>\"");
	}

	public void setAddr_line2(String addr_line2) {
		this.addr_line2 = trimAndConvert(addr_line2, "<>\"");
	}

	public void setCity(String city) {
		this.city = trimAndConvert(city, "<>\"");
	}

	public void setState(String state) {
		this.state = trimAndConvert(state, "<>\"");
	}

	public void setZip(String zip) {
		this.zip = trimAndConvert(zip, "<>\"");
	}

	public void setCurrent_cash(long current_cash) {
		this.current_cash = current_cash;
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
		
		if (addr_line1 == null || addr_line1.length() == 0) {
			errors.add("Addr_line1 address is required");
		}

		if (city == null || city.length() == 0) {
			errors.add("City is required");
		}

		if (state == null || state.length() == 0) {
			errors.add("State is required");
		}

		if (zip == null || zip.length() == 0) {
			errors.add("Zip Code is required");
		}

		
		if (errors.size() > 0) {
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
