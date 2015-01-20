package model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import databeans.FundBean;
import databeans.PositionBean;


public class FundDAO extends GenericDAO<FundBean> {
	
	public FundDAO(String tableName, ConnectionPool pool) throws DAOException {
		super(FundBean.class, tableName, pool);
	}

	public FundBean[] getFunds() throws RollbackException {
		FundBean[] fund = match();
		return fund;
	}
	
	public boolean fundExist() throws RollbackException {
		FundBean[] funds = match();		
		return (funds.length > 0);
	}
	
	public void create(FundBean bean) throws RollbackException {
		try {
			Transaction.begin();
			
			createAutoIncrement(bean);
			
			Transaction.commit();
			
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}		
	}
	
	public FundBean readBySymbol(String symbol) throws RollbackException {
		FundBean[] fundList = match(MatchArg.equals("symbol", symbol));
		if (fundList.length == 0)
			return null;
		else 
			return fundList[0];
	}
	
	public FundBean readByName(String name) throws RollbackException {
		FundBean[] fundList = match(MatchArg.equals("name", name));
		if (fundList.length == 0)
			return null;
		else 
			return fundList[0];
	}
	public FundBean readById(int id) throws RollbackException {
		FundBean[] fundList = match(MatchArg.equals("fund_id", id));
		if (fundList.length == 0)
			return null;
		else 
			return fundList[0];
	}
	
	
}
