package com.money.persistent;

import java.io.Serializable;

import com.litesuits.orm.db.annotation.Column;

public class BasePrice  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 用途
	@Column("UseType")
	private String useType;
	
	// 价格
	@Column("Price")
	private double price;

	public String getUseType() {
		return useType;
	}

	public void setUseType(String useType) {
		this.useType = useType;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}
