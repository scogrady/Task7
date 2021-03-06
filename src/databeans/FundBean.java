package databeans;

import org.genericdao.PrimaryKey;

@PrimaryKey("fund_id")
public class FundBean {
	private int fund_id;
	private String name;
	private String symbol;
	private String description;

	public int getFund_id() {
		return fund_id;
	}

	public String getName() {
		return name;
	}

	public String getSymbol() {
		return symbol;
	}

	public String getDescription() {
		return description;
	}

	public void setFund_id(int fund_id) {
		this.fund_id = fund_id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
