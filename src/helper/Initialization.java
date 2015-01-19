//Name: Hanze Xu
//Andrew ID: hanzex
//Title: Homework #9 Initialization.java
//Course Number: 08-600
//Date: 2014-12-3
package helper;

import model.Model;
import model.FavoriteDAO;
import model.UserDAO;
import model.CustomerDAO;

import org.genericdao.RollbackException;

import databeans.FavoriteBean;
import databeans.UserBean;
import databeans.CustomerBean;


/*
 * Hard code initialization, if user table is null or empty, then 
 * create users and favorites for users.
 */
public class Initialization {
	
	private FavoriteDAO favoriteDAO;
	private UserDAO  userDAO;
	private CustomerDAO customerDAO;
	
	public Initialization(Model model) {
		favoriteDAO = model.getFavoriteDAO();
    	userDAO  = model.getUserDAO();
    	customerDAO = model.getCustomerDAO();
	}


    public void init() {
        try {
			if (!customerDAO.customerExist()) {
				//initializeDB();
				System.out.println("InitializeDB called");
			}
		} catch (RollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
   
    		
    }

