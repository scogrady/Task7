package databeans;

public class SellFundBean extends PositionBean {
	//share
	private String name;
	private long price;

	public long getPrice() {
		return price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(long price) {
		this.price = price;
	}

}
