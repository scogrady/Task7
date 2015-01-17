//Name: Hanze Xu
//Andrew ID: hanzex
//Title: Homework #9 RegisterForm.java
//Course Number: 08-600
//Date: 2014-12-3
package formbeans;
import java.util.ArrayList;
import java.util.List;
import org.mybeans.form.FormBean;

public class RegisterForm extends FormBean{
    private String email;
    private String firstName;
    private String lastName;
    private String password;
	private String confirm;
    private String action;
	
    public String getEmail()  { return email; }
    public String getFirstName()  {return firstName; }
    public String getLastName()  {return lastName; }
    public String getPassword()  { return password; }
    public String getAction()    { return action; }
	public String getConfirm()   { return confirm; }
	
    public void setEmail(String s)  { email = s.trim(); }
    public void setFirstName(String s)  {firstName = s.trim(); }
    public void setLastName(String s)  {lastName = s.trim(); }
    public void setPassword(String s)  { password = s.trim(); }
	public void setConfirm(String s)   { confirm   = s.trim(); }
    public void setAction(String s)    { action   = s; }

    public List<String> getValidationErrors() {
        List<String> errors = new ArrayList<String>();

        if (email == null || email.length() == 0) errors.add("Email address is required");
        if (firstName == null || firstName.length() == 0) errors.add("First name is required");
        if (lastName == null || lastName.length() == 0) errors.add("Last name is required");
        if (password == null || password.length() == 0) errors.add("Password is required");
        if (confirm == null || confirm.length() == 0) errors.add("Confirm Password is required");

        if (action == null) errors.add("Button is required");
        
        if (!action.equals("Register")) errors.add("Invalid button");

        if (errors.size() > 0) return errors;

		if (!password.equals(confirm)) {
			errors.add("Passwords are not the same");
		}
        return errors;
    }
}
