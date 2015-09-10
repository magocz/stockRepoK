package neldam.share;

public class BrokerShare {
	private Share share;
	private Double cost;
	private Double provision;
	
	public BrokerShare(Share share, Double cost, Double provision){
		this.setShare(share);
		this.setCost(cost);
		this.setProvision(provision);
	}

	public Share getShare() {
		return share;
	}

	public void setShare(Share share) {
		this.share = share;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public Double getProvision() {
		return provision;
	}

	public void setProvision(Double provision) {
		this.provision = provision;
	}

	public Double getProfit() {
		return this.cost;
	}
}
