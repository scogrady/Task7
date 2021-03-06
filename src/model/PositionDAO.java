package model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import databeans.PositionBean;
import databeans.TransactionBean;

public class PositionDAO extends GenericDAO<PositionBean> {
	
	public PositionDAO(String tableName, ConnectionPool pool) throws DAOException {
		super(PositionBean.class, tableName, pool);
	}

	public PositionBean[] getPostions() throws RollbackException {
		PositionBean[] positions = match();
		return positions;
	}
	
	public boolean positionExist() throws RollbackException {
		PositionBean[] position = match();		
		return (position.length > 0);
	}
	
	public PositionBean[] readByCustomerID(int customer_id) throws RollbackException {
			PositionBean[] positionList = match(MatchArg.equals("customer_id", customer_id));
			
			
			return positionList;
					
		
		
	}
	
	public PositionBean readByIdFundId(int fund_id, int customer_id) throws RollbackException{


			PositionBean[] positionList = match(MatchArg.equals("customer_id", customer_id),MatchArg.equals("fund_id", fund_id));
			
			
			return positionList[0];

				
		
	}
	
		
	
	public PositionBean[] readByCustomerIDAndFundId(int customer_id, int fund_id) throws RollbackException {
		PositionBean[] positionList = match(MatchArg.and(MatchArg.equals("customer_id", customer_id),MatchArg.equals("fund_id", fund_id)));
		return positionList;
	}
}
