package formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class SellForm extends FormBean {
	String num_1;
	String num_2;
	String fund_id;
	String shares;

	public String getNum_1() {
		return num_1;
	}

	public String getNum_2() {
		return num_2;
	}

	public String getFund_id() {
		return fund_id;
	}

	public String getShares() {
		return shares;
	}

	public void setNum_1(String s) {
		num_1 = s.trim();
	}

	public void setNum_2(String s) {
		num_2 = s.trim();
	}

	public void setFund_id(String s) {
		fund_id = s.trim();
	}

	public void setShares(String s) {
		shares = s.trim();
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (this.num_1 == null || this.num_2 == null || this.shares == null
				|| this.fund_id == null) {
			errors.add("Connection error. Please refresh the page and try again.");
		}

		try {
			long num_1;
			long num_2;
			if (this.num_1 == "") {
				num_1 = 0;
			} else {
				num_1 = Long.parseLong(this.num_1);
			}
			if (this.num_2 == "") {
				num_2 = 0;
			} else {
				num_2 = Long.parseLong(this.num_2);
			}

			long shares = Long.parseLong(this.shares);

			if (!(num_1 >= 0 && num_1 < Integer.MAX_VALUE)) {
				errors.add("Please double check your 1st input.");
			}
			if (!(num_2 >= 0 && num_2 < 1000)) {
				errors.add("Please double check your 2nd input.");
			}
			if (num_1 == 0 && num_2 == 0) {
				errors.add("You can't sell 0 share.");

			}

			if (num_1 + 1 > shares) {
				errors.add("You can't sell more than what you have.");
			}

			return errors;
		} catch (NumberFormatException e) {
			errors.add("Please double check your input.");
			return errors;
		}

	}
}
