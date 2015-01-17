//Name: Hanze Xu
//Andrew ID: hanzex
//Title: Homework #9 Initialization.java
//Course Number: 08-600
//Date: 2014-12-3
package helper;

import model.Model;
import model.FavoriteDAO;
import model.UserDAO;

import org.genericdao.RollbackException;

import databeans.FavoriteBean;
import databeans.UserBean;


/*
 * Hard code initialization, if user table is null or empty, then 
 * create users and favorites for users.
 */
public class Initialization {
	
	private FavoriteDAO favoriteDAO;
	private UserDAO  userDAO;
	
	public Initialization(Model model) {
		favoriteDAO = model.getFavoriteDAO();
    	userDAO  = model.getUserDAO();
	}


    public void init() {
        try {
			if (!userDAO.userExist()) {
				initializeDB();
			}
		} catch (RollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void initializeDB() {
    	String[] names = {"Boat Yang", "Edison He", "BBC Xie", "Stella Long"};
    	String[] webPages = {"www.google.com", "www.amazon.com", "www.baidu.com", "blackboard.andrew.cmu.edu"};
    	try {
        	for (String name : names) {
        		UserBean user = new UserBean();
        		user.setEmail(name + "@gmail.com");
        		user.setPassword(name);
        		String[] fullName = name.split(" ");
        		user.setFirstName(fullName[0]);
        		user.setLastName(fullName[1]);
    			userDAO.create(user);
    			for (String webPage : webPages) {
    				FavoriteBean favorite = new FavoriteBean();
    				favorite.setUrl(webPage);
    				favorite.setComment("hi" + webPage);
    				favorite.setClickCount(0);
    				favorite.setUserID(user.getUserID());
    				favoriteDAO.create(favorite);
    			}
    		}
    		
    	} catch (RollbackException e) {
    		e.printStackTrace();
    	} 
    }
}
