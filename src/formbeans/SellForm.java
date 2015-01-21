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
	
	public String getShares(){
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
		
		
		return errors;
	}
}
