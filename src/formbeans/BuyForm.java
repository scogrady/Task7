package formbeans;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.mybeans.form.FormBean;

public class BuyForm extends FormBean {
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
		if (this.num == null || this.fund_id == null) {
			errors.add("Connection error. Please refresh the page and try again.");
		}
		if (!action.equals("Buy")) {
			errors.add("Invalid button.");
			return errors;
		}

		try {
			Double num;
			if (this.num == "") {
				num = 0.0;
			} else if (!Pattern.matches("\\d+(\\.\\d{1,2})?", this.num)) {
				errors.add("Please double check your input. Only two digits after decimal are allowed.");
			}

			num = Double.parseDouble(this.num);



			if (num < 10) {
				errors.add("The minimum amount is $10 when buying a fund.");
			}
			
			
			if (num == 0 ) {
				errors.add("You can't buy 0 share.");

			}


			return errors;
		} catch (NumberFormatException e) {
			errors.add("Please double check your input.");
			return errors;
		}
	}

}
