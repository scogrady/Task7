package formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class SellForm extends FormBean {
	long num_1;
	int num_2;
	int fund_id;
	long shares;

	public long getNum_1() {
		return num_1;
	}

	public int getNum_2() {
		return num_2;
	}

	public int getFund_id() {
		return fund_id;
	}
	
	public long getShares(){
		return shares;
	}

	public void setNum_1(String s) {
		num_1 = Long.parseLong(s.trim());
	}

	public void setNum_2(String s) {
		num_2 = Integer.parseInt(s.trim());
	}

	public void setFund_id(String s) {
		fund_id = Integer.parseInt(s.trim());
	}
	public void setShares(String s) {
		shares = Long.parseLong(s.trim());
	}
	

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		
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
		
		if( num_1 + 1 > shares ) {
			errors.add("You can't sell more than what you have.");
		}
		
		if (error) {
			errors.add("Please double check your input.");
		}
		return errors;
	}
}
