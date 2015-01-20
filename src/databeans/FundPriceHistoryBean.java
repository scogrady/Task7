package databeans;


import java.math.BigInteger;
import java.sql.Date;

import org.genericdao.PrimaryKey;

//PrimaryKey: fund_id & price_date
@PrimaryKey("fund_id,price_date")
public class FundPriceHistoryBean {
	private int fund_id;
	private Date price_date;
	private long price;

	public int getFund_id() {
		return fund_id;
	}

	public Date getPrice_date() {
		return price_date;
	}

	public long getPrice() {
		return price;
	}

	public void setFund_id(int fund_id) {
		this.fund_id = fund_id;
	}

	public void setPrice_date(Date price_date) {
		this.price_date = price_date;
	}

	public void setPrice(long price) {
		this.price = price;
	}

}
