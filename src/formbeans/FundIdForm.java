package formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class FundIdForm extends FormBean {
	private String fund_id = "";

	public void setFundId(String id) {
		fund_id = id;
	}

	public String getFundId() {
		System.out.println("id=" + fund_id);
		return fund_id;
	}

	public int getIdAsInt() {
		try {
			return Integer.parseInt(fund_id);
		} catch (NumberFormatException e) {
			return -1;
		}
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (fund_id == null || fund_id.length() == 0) {
			errors.add("Fund ID is required");
			return errors;
		}

		try {
			Integer.parseInt(fund_id);
		} catch (NumberFormatException e) {
			errors.add("Fund ID is not an integer");
		}

		return errors;
	}

}
