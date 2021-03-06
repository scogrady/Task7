package formbeans;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.mybeans.form.FormBean;

public class RequestCheckForm extends FormBean {
	String num;
	String action;

	public String getNum() {
		return num;
	}

	public String getAction() {
		return action;
	}

	public void setNum(String s) {
		num = s.trim();
	}

	public void setAction(String s) {
		action = s.trim();
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (this.num == null ) {
			errors.add("Connection error. Please refresh the page and try again.");
		}
		if (!action.equals("Request Check")) {
			errors.add("Invalid button.");
			return errors;
		}

		try {
			Double num;
			if (this.num == "") {
				num = 0.0;
			} else if (!Pattern.matches("\\d*(\\.\\d{1,2}0*)?", this.num)) {
				errors.add("Please double check your input. Only two digits after decimal are allowed.");
			}

			num = Double.parseDouble(this.num);



			if (num == 0) {
				errors.add("You can't request $0.");

			}

			if (!(num >= 0 && num < Long.MAX_VALUE / 100)) {
				errors.add("Please double check your input.");
			}
			return errors;
		} catch (NumberFormatException e) {
			errors.add("Please double check your input.");
			return errors;
		}
	}

}
