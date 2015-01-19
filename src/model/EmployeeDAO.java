package model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import databeans.CustomerBean;
import databeans.EmployeeBean;

public class EmployeeDAO extends GenericDAO<EmployeeBean> {
	
	public EmployeeDAO(String tableName, ConnectionPool pool) throws DAOException {
		super(EmployeeBean.class, tableName, pool);
	}

	public EmployeeBean[] getEmployees() throws RollbackException {
		EmployeeBean[] users = match();
		return users;
	}
	
	public boolean employeeExist() throws RollbackException {
		EmployeeBean[] customers = match();		
		return (customers.length > 0);
	}
	
	public void setPassword(String username, String fullName, String password) throws RollbackException {
        try {
        	Transaction.begin();
        	
        	EmployeeBean[] userList = match(MatchArg.equals("username", username));
        	EmployeeBean dbUser = userList[0];
        	
			if (dbUser == null) {
				throw new RollbackException("Employee "+ fullName +" no longer exists");
			}
			
			dbUser.setPassword(password);
			
			update(dbUser);
			Transaction.commit();
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}
	
	public void create(EmployeeBean bean) throws RollbackException {
		try {
			Transaction.begin();
			
			createAutoIncrement(bean);
			
			Transaction.commit();
			
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}		
	}
	
	public EmployeeBean read(String username) throws RollbackException {
		EmployeeBean[] userList = match(MatchArg.equals("username", username));
		if (userList.length == 0)
			return null;
		else 
			return userList[0];
	}


}
