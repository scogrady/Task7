package formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class RequestCheckForm extends FormBean {
	long num;

	public long getNum() {
		return num;
	}

	public void setNum(String s) {
		num = Long.parseLong(s.trim());
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (!(num >= 0 && num < Integer.MAX_VALUE)) {
			errors.add("Please double check your input.");
		}

		return errors;
	}

}
