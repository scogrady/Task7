package formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class ResearchFundForm extends FormBean {

	private int fund_id;
	private String name;
	private String symbol;
	private String description;
	private String action;
	public int getFund_id() {
		return fund_id;
	}
	public String getName() {
		return name;
	}
	public String getSymbol() {
		return symbol;
	}
	public String getDescription() {
		return description;
	}
	public String getAction() {
		return action;
	}
	public void setFund_id(int fund_id) {
		this.fund_id = fund_id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setAction(String action) {
		this.action = action;
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (symbol == null || symbol.length() == 0) {
			errors.add("Fund name is required");
		}
        if (!action.equals("Search Fund")) errors.add("Invalid action");

		return errors;
	}
}
