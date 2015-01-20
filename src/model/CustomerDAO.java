package model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import databeans.CustomerBean;

public class CustomerDAO extends GenericDAO<CustomerBean> {
	
	public CustomerDAO(String tableName, ConnectionPool pool) throws DAOException {
		super(CustomerBean.class, tableName, pool);
	}

	public CustomerBean[] getCustomers() throws RollbackException {
		CustomerBean[] users = match();
		return users;
	}
	
	public boolean customerExist() throws RollbackException {
		CustomerBean[] customers = match();		
		return (customers.length > 0);
	}
	
	public void setPassword(int user_id, String password) throws RollbackException {
        try {
        	Transaction.begin();
        	
        	CustomerBean[] userList = match(MatchArg.equals("customer_id", user_id));
        	CustomerBean dbUser = userList[0];
			if (dbUser == null) {
				throw new RollbackException("Customer ID = "+ user_id +" no longer exists");
			}
			dbUser.setPassword(password);
			update(dbUser);
			Transaction.commit();
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}
	
	public void create(CustomerBean bean) throws RollbackException {
		try {
			Transaction.begin();
			
			createAutoIncrement(bean);
			
			Transaction.commit();
			
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}		
	}
	
	public CustomerBean read(String username) throws RollbackException {
		CustomerBean[] userList = match(MatchArg.equals("username", username));
		if (userList.length == 0)
			return null;
		else 
			return userList[0];
	}
	
	public CustomerBean readFromID(int userID) throws RollbackException {
		CustomerBean[] userList = match(MatchArg.equals("customer_id", userID));
		if (userList.length == 0)
			return null;
		else 
			return userList[0];
	}
	
	

}
