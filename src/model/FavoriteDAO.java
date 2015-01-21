//Name: Hanze Xu
//Andrew ID: hanzex
//Title: Homework #9 FavoriteDAO.java
//Course Number: 08-600
//Date: 2014-12-3
package model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import databeans.FavoriteBean;

public class FavoriteDAO extends GenericDAO<FavoriteBean> {
	public FavoriteDAO(String tableName, ConnectionPool pool) throws DAOException {
		super(FavoriteBean.class, tableName, pool);
	}

	public FavoriteBean[] getUserFavorites(int userID) throws RollbackException {
		
		FavoriteBean[] beans = match(MatchArg.equals("userID", userID));
        
        return beans;
	}
	
	public void addClickCount(int favoriteID) throws RollbackException {
		try {
			Transaction.begin();
			FavoriteBean bean = read(favoriteID);
			
			bean.setClickCount(bean.getClickCount() + 1);
			update(bean);
			
			Transaction.commit();			
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}
	public void create(FavoriteBean bean) throws RollbackException {
		try {
			Transaction.begin();
			
			createAutoIncrement(bean);
			
			Transaction.commit();
			
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}
}
