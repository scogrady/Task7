package databeans;

import org.genericdao.PrimaryKey;

//PrimaryKey: customer_id & fund_id
@PrimaryKey("customer_id,fund_id")
public class PositionBean {

	private int customer_id;
	private int fund_id;
	private long shares;
	private long avail_Shares;

	public int getCustomer_id() {
		return customer_id;
	}

	public int getFund_id() {
		return fund_id;
	}

	public long getShares() {
		return shares;
	}

	public long getAvailShares() {
		return avail_Shares;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public void setFund_id(int fund_id) {
		this.fund_id = fund_id;
	}

	public void setShares(long shares) {
		this.shares = shares;
	}

	public void setAvailShares(long availShares) {
		this.avail_Shares = availShares;
	}

}
