package databeans;

import org.genericdao.PrimaryKey;

@PrimaryKey("username")

public class EmployeeBean {
	private String username;
	private String password;
	private String firstname;
	private String lastname;
	private int status;
	
	public boolean checkPassword(String password){
		return this.password.equals(password) ? true:false;
	}
	
	public boolean isLoged(){
		return status==0 ? false:true;
	}
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}
	

	public void setUsername(String s) {
		username = s;
	}

	public void setPassword(String s) {
		password = s;
	}

	public void setFirstname(String s) {
		firstname = s;
	}

	public void setLastname(String s) {
		lastname = s;
	}
}
