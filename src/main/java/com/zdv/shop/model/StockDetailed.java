package com.zdv.shop.model;


public class StockDetailed {
	
	private OtProduct otProduct;
	
	private int stackNumber;
	private double stackMoney;
	
	private int boxNumber;
	private double boxMoney;
	
	private int singleNumber;
	private double singleMoney;
	public OtProduct getOtProduct() {
		return otProduct;
	}
	public void setOtProduct(OtProduct otProduct) {
		this.otProduct = otProduct;
	}
	public double getStackNumber() {
		return stackNumber;
	}
	public void setStackNumber(int stackNumber) {
		this.stackNumber = stackNumber;
	}
	public double getStackMoney() {
		return stackMoney;
	}
	public void setStackMoney(int stackMoney) {
		this.stackMoney = stackMoney;
	}
	public int getBoxNumber() {
		return boxNumber;
	}
	public void setBoxNumber(int boxNumber) {
		this.boxNumber = boxNumber;
	}
	public double getBoxMoney() {
		return boxMoney;
	}
	public void setBoxMoney(double boxMoney) {
		this.boxMoney = boxMoney;
	}
	public int getSingleNumber() {
		return singleNumber;
	}
	public void setSingleNumber(int singleNumber) {
		this.singleNumber = singleNumber;
	}
	public double getSingleMoney() {
		return singleMoney;
	}
	public void setSingleMoney(double singleMoney) {
		this.singleMoney = singleMoney;
	}
	@Override
	public String toString() {
		return "StockDetailed [otProduct=" + otProduct + ", 垛数=" + stackNumber + ", stackMoney=" + stackMoney
				+ ", 箱数=" + boxNumber + ", boxMoney=" + boxMoney + ", 个数=" + singleNumber
				+ ", singleMoney=" + singleMoney + "]";
	}
	
}
