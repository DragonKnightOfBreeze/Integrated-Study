package com.windea.study.designpattern.responsibilitychain;

public class PurchaseRequest {
	private int id;
	private int type;
	private int price;

	public PurchaseRequest(int id, int type, int price) {
		this.id = id;
		this.type = type;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public int getType() {
		return type;
	}

	public int getPrice() {
		return price;
	}
}
