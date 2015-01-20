package model;

import java.sql.Date;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;
import databeans.FundPriceHistoryBean;

public class  FundPriceHistoryDAO extends GenericDAO<FundPriceHistoryBean> {

	public FundPriceHistoryDAO(String tableName, ConnectionPool pool)
			throws DAOException {
		super(FundPriceHistoryBean.class, tableName, pool);
	}

	public FundPriceHistoryBean[] getFundPriceHistorys()
			throws RollbackException {
		FundPriceHistoryBean[] fundHistory = match();
		return fundHistory;
	}

	public boolean fundHistoryExist() throws RollbackException {
		FundPriceHistoryBean[] fundHistory = match();
		return (fundHistory.length > 0);
	}

	public void create(FundPriceHistoryBean bean) throws RollbackException {
		try {
			Transaction.begin();

			createAutoIncrement(bean);

			Transaction.commit();

		} finally {
			if (Transaction.isActive())
				Transaction.rollback();
		}
	}



	public FundPriceHistoryBean[] readByFundID(int fund_id)
			throws RollbackException {
		FundPriceHistoryBean[] fundList = match(MatchArg.equals("fund_id",
				fund_id));
		return fundList;
	}
	

	public FundPriceHistoryBean readLastPrice(int fund_id)
			throws RollbackException {
		FundPriceHistoryBean[] fundList = match(MatchArg.equals("fund_id",
				fund_id));
		return fundList[fundList.length - 1];
	}
	

	public long readChange(int fund_id)
			throws RollbackException {
		FundPriceHistoryBean[] fundList = match(MatchArg.equals("fund_id",
				fund_id));
		return fundList[fundList.length - 1].getPrice() - fundList[fundList.length - 2].getPrice();
	}
	
	public FundPriceHistoryBean[] readByDate(Date price_date)
			throws RollbackException {
		FundPriceHistoryBean[] fundList = match(MatchArg.equals("price_date",
				price_date));
		return fundList;
	}

	public FundPriceHistoryBean[] readByDateAndFundID(Date price_date,
			int fund_id) throws RollbackException {
		FundPriceHistoryBean[] fundList = match(
				MatchArg.equals("price_date", price_date),
				MatchArg.equals("fund_id", fund_id));
		return fundList;
	}

}
