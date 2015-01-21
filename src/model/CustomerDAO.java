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
				System.out.println("in cus dao");

	}

	public  CustomerBean[] getCustomers() throws RollbackException {
		CustomerBean[] customers = match();
		return customers;
	}
	
	public boolean customerExist() throws RollbackException {
		System.out.println("in cus exist dao");

		CustomerBean[] customers = match();	
		System.out.println(customers.length);

		return (customers.length > 0);
	}
	
	public void setPassword(int customer_id,  String password) throws RollbackException {
        try {
        	Transaction.begin();
        	
        	CustomerBean[] customerList = match(MatchArg.equals("customer_id", customer_id));
        	CustomerBean dbUser = customerList[0];
        	
			if (dbUser == null) {
				throw new RollbackException("Customer ID does not  exists");
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
	public CustomerBean readFromID(int customer_id) throws RollbackException {
		CustomerBean[] customerList = match(MatchArg.equals("customer_id", customer_id));
		if (customerList.length == 0){
			return null;}
		else{
			return customerList[0];
		}
		

	}
	}

	
	


