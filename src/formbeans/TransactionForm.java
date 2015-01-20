package formbeans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.mybeans.form.FormBean;

public class TransactionForm extends FormBean {
	private Date date;
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (date == null) {
			errors.add("No Accurate Transaction Day");
		}
		
		return errors;
	}
}
