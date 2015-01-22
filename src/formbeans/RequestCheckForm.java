package formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class RequestCheckForm extends FormBean {
	String num;
	String avail_cash;

	public String getNum() {
		return num;
	}

	public String getAvail_cash() {
		return avail_cash;
	}

	public void setNum(String s) {
		num = s.trim();
	}

	public void setAvail_cash(String s) {
		avail_cash = s.trim();
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (this.num == null || this.avail_cash == null) {
			errors.add("Connection error. Please refresh the page and try again.");
		}

		try {
			long num = Long.parseLong(this.num);
			long avail_cash = Long.parseLong(this.avail_cash);

			if (num * 100 > avail_cash) {
				errors.add("Not enough money in Available Cash");
			}

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
