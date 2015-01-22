package formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class DepositForm extends FormBean {
	private String amount;
	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = trimAndConvert(username, "<>\"");
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = trimAndConvert(amount, "<>\"");
	}



	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		
		if (this.amount == null) {
			errors.add("Check Amount is required.");
		}

		if(username == null || username.length() == 0){
			errors.add("Customer Username is required.");
		}
		try {
			long num = Long.parseLong(this.amount);
			if (!(num >= 0 && num < Integer.MAX_VALUE)) {
				errors.add("Please double check your input.");
			}
			return errors;
		} catch (NumberFormatException e) {
			errors.add("Please double check your input.");
			return errors;
		}
	}
}
