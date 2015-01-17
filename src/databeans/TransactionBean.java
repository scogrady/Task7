package databeans;

import java.util.Date;

import org.genericdao.PrimaryKey;

@PrimaryKey("transaction_id")
public class TransactionBean {

	private int transaction_id;
	private int customer_id;
	private int fund_id;
	private Date execute_date;
	private long shares;
	/**
	 * Transaction Type: Sell Fund Buy Fund Request Check Deposit Check
	 * */
	private String transaction_type;
	/**
	 * Status Type: Pending Completed
	 * */
	private String status;
	private long amount;

	public int getTransaction_id() {
		return transaction_id;
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public int getFund_id() {
		return fund_id;
	}

	public Date getExecute_date() {
		return execute_date;
	}

	public long getShares() {
		return shares;
	}

	public String getTransaction_type() {
		return transaction_type;
	}

	public String getStatus() {
		return status;
	}

	public long getAmount() {
		return amount;
	}

	public void setTransaction_id(int transaction_id) {
		this.transaction_id = transaction_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public void setFund_id(int fund_id) {
		this.fund_id = fund_id;
	}

	public void setExecute_date(Date execute_date) {
		this.execute_date = execute_date;
	}

	public void setShares(long shares) {
		this.shares = shares;
	}

	public void setTransaction_type(String transaction_type) {
		this.transaction_type = transaction_type;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

}
