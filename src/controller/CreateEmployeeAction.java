//Name: Hanze Xu
//Andrew ID: hanzex
//Title: Homework #9 ManageAction.java
//Course Number: 08-600
//Date: 2014-12-3


//oh my god
package controller;
//try pull
//try push
//try push helen
//try push from vic
//try push from vic 1
//try push avanti
//push from dell
//push try from dell
//try push vic 2
//try push vic 3
//push by Iris
// push helen2

//push helen3

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.Model;
import model.FavoriteDAO;
import model.UserDAO;

import org.genericdao.RollbackException;

/*
 * Sets up the request attributes for manage.jsp.

 * Sets the "userList" request attribute in order to display
 * the list of users on the navbar.
 * 
 * Sets the "favoriteList" request attribute in order to display
 * the list of user's favorites for management.
 * 
 * Forwards to manage.jsp.
 */
public class CreateEmployeeAction extends Action {

	private FavoriteDAO favoriteDAO;
	private UserDAO  userDAO;

	public CreateEmployeeAction(Model model) {
    	favoriteDAO = model.getFavoriteDAO();
    	userDAO  = model.getUserDAO();
	}

	public String getName() { return "CreateEmployee.do"; }

	public String perform(HttpServletRequest request) {
        // Set up the errors list
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
		try {
            // Set up user list for nav bar
			request.setAttribute("userList",userDAO.getUsers());
			//UserBean user = (UserBean) request.getSession(false).getAttribute("user");
        	//FavoriteBean[] favoriteList = favoriteDAO.getUserFavorites(user.getUserID());
	        //request.setAttribute("favoriteList",favoriteList);

	        return "employee/create-employee.jsp";
        } catch (RollbackException e) {
        	errors.add(e.getMessage());
        	return "error.jsp";
        }
    }
}
