package formbeans;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import org.mybeans.form.FormBean;

public class PriceForm extends FormBean {
	private int[] id;
	private double[] price;
	private String date;
	private String action;

	public int[] getId() {
		return id;
	}

	public double[] getPrice() {
		return price;	
	}
	
	public String[] getFormatPrice() {
		String[] s = new String[price.length];
		
		for (int i = 0; i < price.length; i++) {
			s[i] = String.format("%.2f", price[i]);
		}
		return s;
	}
	
	public String getDate() {
		return date;
	}
	
	public String getAction() {
		return action;
	}

	public void setId(String[] s) {
		id = new int[s.length];
		for (int i = 0; i < s.length; i++) {
			id[i] = Integer.parseInt(s[i]);
		}
	}

	public void setPrice(String[] s ) {
		price = new double[s.length];
		for (int i = 0; i < s.length; i++) {
			try {
				price[i] = Double.parseDouble(s[i]);
			} catch(NumberFormatException e) {
				price[i] = -1;
			}				
		}
	}
	
	public void setDate(String s) {
		date = s;
	}
	
	public void setAction(String s) {
		action = s;
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		System.out.println(price);
		if (price != null) {
			for (int i = 0; i < price.length; i++) {
				if (price[i] == -1) {
					errors.add("Price of the" + (i + 1) + "th fund should not be empty");
					continue;
				}
				if (price[i] < 5 || price[i] > 10000) {
					errors.add("Price of the" + (i + 1)+ "th fund should be between $5 and $10000");
					continue;
				}
				if (!Double.toString(price[i]).matches("\\d+(\\.\\d{1,2})?")) {
					errors.add("Price of the " +(i + 1) + "th fund should be float and have at most two decimal");
				}
			}
		}
		if (!action.equals("Start Transition Day!")) {
			errors.add("Invalid Button");
		}
		if (!date.matches("\\d{4}-\\d{2}-\\d{2}")) {
			errors.add("Invalid Date");
		}
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			formatter.parse(date);
		} catch(ParseException e) {
			errors.add("Invalid Date");
		}
		return errors;
	}

}
