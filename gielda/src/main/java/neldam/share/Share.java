package neldam.share;

import java.text.DecimalFormat;

import javassist.expr.NewArray;

public class Share {
	private Double price;
	private Integer number;
	
	private DecimalFormat decimalFormat;

	public Share(Double price, Integer number) {
		this.price = price;
		this.number = number;
		createFormater();
	}
	
	public String getShareInfo(){
		return decimalFormat.format(this.price) + " " + this.number;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Share getShare(Integer shareNumber) {
		if(this.number == shareNumber){
			return this;
		}
		if(this.number > shareNumber){
			this.number = this.number - shareNumber;
			return new Share(this.price, shareNumber);
		}
		return null;
	}
	
	public Share getShare() {
		return this;
	}
	
	public Double getValuation(){
		return this.price * this.number;
	}
	
	private void createFormater() {
		decimalFormat = new java.text.DecimalFormat();
		decimalFormat.setMaximumFractionDigits(2);
		decimalFormat.setMinimumFractionDigits(2);
	}

}
