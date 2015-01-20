package databeans;

public class BuyFundBean extends FundBean{
	
	private long price;
	private long change;
	private long chgPer;
	
	
	public BuyFundBean() {
		
	}
	
	public BuyFundBean(FundBean fund) {
		super.setFund_id(fund.getFund_id());
		super.setName(fund.getName());
		super.setSymbol(fund.getSymbol());
		super.setDescription(fund.getDescription());
	}
	
	
	public long getPrice() {
		return price;
	}
	public long getChange() {
		return change;
	}
	
	public long getChgPer(){
		return chgPer;
	}
	
	public void setChgPer(long s) {
		chgPer = s;
	}
	
	public void setChange(long s) {
		change = s;
	}
	public void setPrice(long s) {
		price = s;
	}

}
