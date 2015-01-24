//Name: Hanze Xu
//Andrew ID: hanzex
//Title: Homework #9 ChangePwdForm.java
//Course Number: 08-600
//Date: 2014-12-3
package formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class ChangePwdForm extends FormBean {
	private String confirmPassword;
	private String newPassword;
	private String oldPassword;
	public String getConfirmPassword() { return confirmPassword; }
	public String getNewPassword()     { return newPassword;     }
	public String getOldPassword()     { return oldPassword;     }
	
	public void setOldPassword(String s) {oldPassword = s.trim();}
	public void setConfirmPassword(String s) { confirmPassword = s.trim(); }
	public void setNewPassword(String s)     { newPassword     = s.trim(); }
	

	public List<String> getValidationErrors() {
		System.out.println("coming to form validation");
		List<String> errors = new ArrayList<String>();
		
		if (newPassword == null || newPassword.length() == 0) {
			errors.add("New Password is required");
		}
		
		if (confirmPassword == null || confirmPassword.length() == 0) {
			errors.add("Confirm Pwd is required");
		}
		if (oldPassword == null || oldPassword.length() == 0) {
			errors.add("Old Pwd is required");
		}
		
		if (errors.size() > 0) {
			return errors;
		}
		
		if (!newPassword.equals(confirmPassword)) {
			errors.add("Passwords do not match");
		}

		return errors;
	}
}
