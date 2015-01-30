package formbeans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import org.mybeans.form.FormBean;

import sun.util.calendar.ZoneInfo;

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
		} else if (!Pattern.matches("[\\w]+", username)) {
			errors.add("Username should be all characters.");
		} else if (username.length() > 30 || username.length() < 3) {
			errors.add("Username shoule more than 3 digit and less than 30 digit.");
		}

		if (firstname == null || firstname.length() == 0) {
			errors.add("First Name is required");
		} else if (!Pattern.matches("[A-Za-z ]+", firstname)) {
			errors.add("First name should be all characters.");
		} else if (firstname.length() > 30 || firstname.length() < 2) {
			errors.add("First name shoule more than 2 digit and less than 30 digit.");
		}
		if (lastname == null || lastname.length() == 0) {
			errors.add("Last Name is required");
		} else if (!Pattern.matches("[A-Za-z ]+", lastname)) {
			errors.add("Last name should be all characters.");
		} else if (lastname.length() > 30 || lastname.length() < 2) {
			errors.add("Last name shoule more than 2 digit and less than 30 digit.");
		}
		if (errors.size() > 0) {
			return errors;
		}
		if (password == null || password.length() == 0) {
			errors.add("Password is required");
		} else if (password.length() > 20 || password.length() < 3) {
			errors.add("Password should be more than 3 digit and less than 20 digit.");
		}

		else if (confirm == null || confirm.length() == 0) {
			errors.add("Confirm Password is required");
		}

		if (addr_line1 == null || addr_line1.length() == 0) {
			errors.add("Address line 1 is required");
		} else if (!Pattern.matches("[\\w ,-]+", addr_line1)) {
			errors.add("Address line 1 shouldn't have special characters.");
		} else if (addr_line1.length() > 50 || addr_line1.length() < 3) {
			errors.add("Address line 1 should be more than 3 digit and less than 50 digit.");
		}

		if (addr_line2 != null || addr_line2.length() != 0) {
			if (!Pattern.matches("[\\w ,-]+", addr_line2)) {
				errors.add("Address line 2 shouldn't have special characters.");
			} else if (addr_line2.length() > 50) {
				errors.add("Address line 2 should be less than 50 digit.");
			}
		}

		if (city == null || city.length() == 0) {
			errors.add("City is required");
		} else if (!Pattern.matches("[A-Za-z ]+", city)) {
			errors.add("City should be all characters.");
		} else if (city.length() > 30 || city.length() < 3) {
			errors.add("City shoule more than 3 digit and less than 30 digit");
		}

		if (state == null || state.length() == 0) {
			errors.add("State is required");
		}

		if (zip == null || zip.length() == 0) {
			errors.add("Zip Code is required");
		}
		try {
			int zipcode = Integer.parseInt(zip);
			if (zipcode < 10000 || zipcode > 100000) {
				errors.add("Zip Code should be 5 digit number");
			}
		} catch (Exception e) {
			errors.add("Zip Code should be 5 digit number");
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
