package model;

import java.util.Date;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import databeans.CustomerBean;
import databeans.TransactionBean;

public class TransactionDAO extends GenericDAO<TransactionBean> {
	
	public TransactionDAO(String tableName, ConnectionPool pool) throws DAOException {
		super(TransactionBean.class, tableName, pool);
	}
//TODO
	public TransactionBean[] getTransactions() throws RollbackException {
		TransactionBean[] transactions = match();
		return transactions;
	}
	
	
	public boolean transactionExist() throws RollbackException {
		try {
		TransactionBean[] transaction = match();		
		return (transaction.length > 0);
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}
	
	public void create(TransactionBean bean) throws RollbackException {
			createAutoIncrement(bean);	
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
	public TransactionBean readByLastDate() throws RollbackException {
		TransactionBean[] transactionList = match(MatchArg.max("execute_date"));
		if (transactionList.length == 0) { 
			return null;
		} else {
			return transactionList[0];
		}
	}
}
