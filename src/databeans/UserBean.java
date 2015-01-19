//Name: Hanze Xu

//Andrew ID: hanzex
//Title: Homework #9 UserBean.java
//Course Number: 08-600
//Date: 2014-12-3
//test
package databeans;
import org.genericdao.PrimaryKey;

@PrimaryKey("userID")
public class UserBean {
    private int    userID;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    
    public int    getUserID()        { return userID; }
    public String getEmail()         { return email; }
    public String getFirstName()     { return firstName; }
    public String getLastName()      { return lastName; }
    public String getPassword()      { return password; }

    public void setPassword(String s)  { password = s;    }
    public void setUserID(int i)       { userID = i;    }
    public void setEmail(String s)     { email = s;    }
    public void setFirstName(String s) { firstName = s;    }
    public void setLastName(String s)  { lastName = s;    }
    
    public String getFullName() {
    	return firstName + " " + lastName;
    }
}
