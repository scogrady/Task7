//Name: Hanze Xu
//Andrew ID: hanzex
//Title: Homework #9 UserDAO.java
//Course Number: 08-600
//Date: 2014-12-3
package model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import databeans.UserBean;


public class UserDAO extends GenericDAO<UserBean> {
	
	public UserDAO(String tableName, ConnectionPool pool) throws DAOException {
		super(UserBean.class, tableName, pool);
	}

	public UserBean[] getUsers() throws RollbackException {
		UserBean[] users = match();
		return users;
	}
	
	public boolean userExist() throws RollbackException {
		UserBean[] users = match();		
		return (users.length > 0);
	}
	
	public void setPassword(String email, String fullName, String password) throws RollbackException {
        try {
        	Transaction.begin();
			UserBean dbUser = (UserBean)read(email);
			if (dbUser == null) {
				throw new RollbackException("User "+ fullName +" no longer exists");
			}
			
			dbUser.setPassword(password);
			
			update(dbUser);
			Transaction.commit();
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}
	
	public void create(UserBean bean) throws RollbackException {
		try {
			Transaction.begin();
			
			createAutoIncrement(bean);
			
			Transaction.commit();
			
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}		
	}
	
	public UserBean read(String email) throws RollbackException {
		UserBean[] userList = match(MatchArg.equals("email", email));
		if (userList.length == 0)
			return null;
		else 
			return userList[0];
	}
	
	public UserBean readFromID(int userID) throws RollbackException {
		UserBean[] userList = match(MatchArg.equals("userID", userID));
		if (userList.length == 0)
			return null;
		else 
			return userList[0];
	}
}
