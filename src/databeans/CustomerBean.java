package databeans;

import java.util.Date;

import org.genericdao.PrimaryKey;

@PrimaryKey("customer_id")
public class CustomerBean {
	private int customer_id;
	private String username;
	private String password;
	private String firstname;
	private String lastname;
	private String addr_line1;
	private String addr_line2;
	private String city;
	private String state;
	private String zip;
	private long current_cash;
	private long available_cash;
	private Date last_login_time;
	private int status;
	public boolean isLoged(){
		return status==0 ? false:true;
	}
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	public int getCustomer_id() {
		return customer_id;
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

	public String getAddr_line1() {
		return addr_line1;
	}

	public String getAddr_line2() {
		return addr_line2;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String getZip() {
		return zip;
	}

	public long getCurrent_cash() {
		return current_cash;
	}
	public long getAvailable_cash() {
		return available_cash;
	}

	public Date getLast_login_time() {
		return last_login_time;
	}

	public void setCustomer_id(int s) {
		customer_id = s;
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

	public void setAddr_line1(String s) {
		addr_line1 = s;
	}

	public void setAddr_line2(String s) {
		addr_line2 = s;
	}

	public void setCity(String s) {
		city = s;
	}

	public void setState(String s) {
		state = s;
	}

	public void setZip(String s) {
		zip = s;
	}

	public void setCurrent_cash(long s) {
		current_cash = s;
	}
	
	public void setAvailable_cash(long s) {
		available_cash = s;
	}

	public void setLast_login_time(Date s) {
		last_login_time = s;
	}

}
