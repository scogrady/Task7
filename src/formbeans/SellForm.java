package formbeans;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.mybeans.form.FormBean;

public class SellForm extends FormBean {
	String num;
	String fund_id;
	String action;

	public String getNum() {
		return num;
	}

	public String getFund_id() {
		return fund_id;
	}

	
	public String getAction() {
		return action;
	}

	public void setNum(String s) {
		num = s.trim();
	}

	public void setFund_id(String s) {
		fund_id = s.trim();
	}


	public void setAction(String s) {
		action = s.trim();
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (this.num == null  || this.fund_id == null) {
			errors.add("Connection error. Please refresh the page and try again.");
		}
		if (!action.equals("Sell")) {
			errors.add("Invalid button.");
			return errors;
		}

		try {
			Double num;
			if (this.num == "") {
				num = 0.0;
			} else if (!Pattern.matches("\\d*(\\.\\d{1,3}0*)?", this.num)) {
				errors.add("Please double check your input. Only three digits after decimal are allowed.");
			}

			num = Double.parseDouble(this.num);


			if (!(num >= 0 && num < Double.MAX_VALUE)) {
				errors.add("Please double check your input.");
			}

			if (num == 0.0) {
				errors.add("You can't sell 0 share.");
			}

		

			return errors;
		} catch (NumberFormatException e) {
			errors.add("Please double check your input.");
			return errors;
		}

	}
}
