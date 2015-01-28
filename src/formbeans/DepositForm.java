package formbeans;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.mybeans.form.FormBean;

public class DepositForm extends FormBean {
	private String amount;
	private String username;
	String action;

	public String getUsername() {
		return username;
	}

	public String getAction() {
		return action;
	}

	public String getAmount() {
		return amount;
	}

	public void setUsername(String username) {
		this.username = trimAndConvert(username, "<>\"");
	}

	public void setAmount(String amount) {
		this.amount = trimAndConvert(amount, "<>\"");
	}

	public void setAction(String action) {
		this.action = trimAndConvert(action, "<>\"");
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (this.amount == null) {
			errors.add("Check Amount is required.");
		}

		if (username == null || username.length() == 0) {
			errors.add("Customer Username is required.");
		}
		if (!action.equals("Deposit")) {
			errors.add("Invalid button.");
			return errors;
		}

		try {
			Double num;
			if (this.amount == "") {
				num = 0.0;
			} else if (!Pattern.matches("\\d*(\\.\\d{1,2})?", this.amount)) {
				errors.add("Please double check your input. Only two digits after decimal are allowed.");
			}

			num = Double.parseDouble(this.amount);

			if (!(num > 0 && num < (Double.MAX_VALUE / 100))) {
				errors.add("Please double check your input.");
			}
			if (num == 0) {
				errors.add("You can't deposit $0.");
			}

			return errors;
		} catch (NumberFormatException e) {
			errors.add("Please double check your input.");
			return errors;
		}
	}
}
