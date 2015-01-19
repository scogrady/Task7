package formbeans;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class BuyForm extends FormBean {
	long num_1;
	int num_2;
	int fund_id;

	public long getNum_1() {
		return num_1;
	}

	public int getNum_2() {
		return num_2;
	}

	public int getFund_id() {
		return fund_id;
	}

	public void setNum_1(String s) {
		num_1 = Long.parseLong(s.trim());
	}

	public void setNum_2(String s) {
		num_2 = Integer.parseInt(s.trim());
	}

	public void fund_id(String s) {
		fund_id = Integer.parseInt(s.trim());
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		BigInteger max = new BigInteger("9999999999");
		boolean error = false;

		if (!(num_1 >= 0 && num_1 < Integer.MAX_VALUE)) {
			error = true;
		}
		if (!(num_2 >= 0 && num_2 < 1000)) {
			error = true;
		}
		
		if (fund_id == 0) {
			errors.add("Connection error. Please refresh the page and try again.");
		}
		
		if (error) {
			errors.add("Please double check your input.");
		}
		return errors;
	}
}
