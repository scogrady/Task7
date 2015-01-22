package model;

import java.util.Date;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import databeans.TransactionBean;

public class TransactionDAO extends GenericDAO<TransactionBean> {
	
	public TransactionDAO(String tableName, ConnectionPool pool) throws DAOException {
		super(TransactionBean.class, tableName, pool);
	}
//TODO
	public TransactionBean[] getFunds() throws RollbackException {
		TransactionBean[] transactions = match();
		return transactions;
	}
	
	public boolean transactionExist() throws RollbackException {
		TransactionBean[] transaction = match();		
		return (transaction.length > 0);
	}
	
	public void create(TransactionBean bean) throws RollbackException {
		try {
			Transaction.begin();
			
			createAutoIncrement(bean);
			
			Transaction.commit();
			
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}		
	}
	
	public TransactionBean[] readByCustomerID(int customer_id) throws RollbackException {
		TransactionBean[] transactionList = match(MatchArg.equals("customer_id", customer_id));
		return transactionList;
	}
	public TransactionBean[] readByFundID(int fund_id) throws RollbackException {
		TransactionBean[] transactionList = match(MatchArg.equals("fund_id", fund_id));
		return transactionList;
	}
	public TransactionBean[] readByType(String transaction_type) throws RollbackException {
		TransactionBean[] transactionList = match(MatchArg.equals("transaction_type", transaction_type));
		return transactionList;
	}
	public TransactionBean[] readByStatus(String status) throws RollbackException {
		TransactionBean[] transactionList = match(MatchArg.equals("status", status));
		return transactionList;
	}
	public TransactionBean[] readByStatusBeforeDate(String status, Date date) throws RollbackException {
		TransactionBean[] transactionList = match(MatchArg.and(MatchArg.equals("status", status), MatchArg.lessThan("execute_date", date)));
		return transactionList;
	}
	public TransactionBean[] readByDate(Date date) throws RollbackException {
		TransactionBean[] transactionList = match(MatchArg.equals("execute_date", date));
		return transactionList;		
	}
}
