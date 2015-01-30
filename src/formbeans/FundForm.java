package formbeans;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.mybeans.form.FormBean;

public class FundForm extends FormBean {

	private int fund_id;
	private String description;
	private String name;
	private String symbol;
	private String action;
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public int getFund_id() {
		return fund_id;
	}
	public String getDescription() {
		return description;
	}
	public String getName() {
		return name;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setFund_id(int fund_id) {
		this.fund_id = fund_id;
	}
	public void setDescription(String description) {
		this.description = trimAndConvert(description, "<>\"");
	}
	public void setName(String name) {
		this.name = trimAndConvert(name, "<>\"");
	}
	public void setSymbol(String symbol) {
		this.symbol = trimAndConvert(symbol, "<>\"");
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (name == null || name.length() == 0)
			errors.add("Fund name is required");
		if (symbol == null || symbol.length() == 0)
			errors.add("Fund symbol is required");

		if (!Pattern.matches("[A-Za-z0-9 ]+", name)) {
			errors.add("Fund name should be all characters and numbers.");
		}
		if(name.length()>50||name.length()<3){
			errors.add("Fund name should be more than 3 digit and less than 50 characters.");
		}

		if (!Pattern.matches("[A-Za-z0-9 ]+", symbol)) {
			errors.add("Fund symbol should be all characters and numbers.");
		}
		
		if(symbol.length()>50||symbol.length()<3){
			errors.add("Fund symbol should be more than 3 digit and less than 50 characters.");
		}
		if(description !=null && description.length()>250){
			errors.add("Fund description should be less than 250 characters.");
		}
		
		
		if (action == null)
			errors.add("Button is required");

		if (!action.equals("Create"))
			errors.add("Invalid button");

		return errors;
	}
}
