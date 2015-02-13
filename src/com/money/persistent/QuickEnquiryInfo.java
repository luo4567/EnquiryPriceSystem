package com.money.persistent;

import java.io.Serializable;
import java.sql.Date;

import android.graphics.Bitmap;

import com.litesuits.orm.db.annotation.Column;
import com.litesuits.orm.db.annotation.Mapping;
import com.litesuits.orm.db.annotation.Mapping.Relation;
import com.litesuits.orm.db.annotation.NotNull;
import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Unique;
import com.litesuits.orm.db.annotation.PrimaryKey.AssignType;

public class QuickEnquiryInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Id 主键，自增长
	@PrimaryKey(AssignType.AUTO_INCREMENT)
	private long id;

	// / 询价编码
	// / <summary>
	// / 询价编码
	// / </summary>
	@NotNull
	@Unique
	@Column("BM")
	private String bM;

	// / 地址
	// / <summary>
	// / 地址
	// / </summary>
	@Column("Address")
	private String address;

	// / 答复价格
	// / <summary>
	// / 答复价格，每个用途匹配一个价格
	// / </summary>
	@Mapping(Relation.OneToOne)
	private  BasePrice basePrice;

	// / 匹配的地址
	// / <summary>
	// / 匹配的地址
	// / </summary>
	@Column("MatchAddress")
	private String matchAddress;
	
	// / 询价时间
	// / <summary>
	// / 询价时间
	// / </summary>
	@Column("ManualTime")
	private Date manualTime;
	
	@Column("Photo")
	private Bitmap Photo;
	
	public long getId() {
		return id;
	}

	public String getbM() {
		return bM;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public BasePrice getPrice() {
		return basePrice;
	}

	public void setPrice(BasePrice price) {
		this.basePrice = price;
	}

	public String getMatchAddress() {
		return matchAddress;
	}

	public void setMatchAddress(String matchAddress) {
		this.matchAddress = matchAddress;
	}

	public Date getManualTime() {
		return manualTime;
	}

	public void setManualTime(Date manualTime) {
		this.manualTime = manualTime;
	}

	public Bitmap getPhoto() {
		return Photo;
	}

	public void setPhoto(Bitmap photo) {
		Photo = photo;
	}
	
	
}
