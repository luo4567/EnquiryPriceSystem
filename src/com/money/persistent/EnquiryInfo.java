package com.money.persistent;

import com.litesuits.orm.db.annotation.Column;

public class EnquiryInfo extends QuickEnquiryInfo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;

	// / 用途
	// / <summary>
	// / 用途
	// / </summary>
	@Column("UseType")
	private String useType;

	// / 评估目的
	// / <summary>
	// / 评估目的
	// / </summary>
	@Column("AppraisalPurpose")
	private String appraisalPurpose;

	// / 建筑面积
	// / <summary>
	// / 建筑面积
	// / </summary>
	@Column("Area")
	private double area;

	// / 建成年份
	// / <summary>
	// / 建成年份
	// / </summary>
	@Column("BuiltYear")
	private String builtYear;

	// / 电梯情况
	// / <summary>
	// / 电梯情况
	// / </summary>
	@Column("Elevator")
	private String elevator;

	// / 朝向
	// / <summary>
	// / 朝向
	// / </summary>
	@Column("Toward")
	private String toward;

	// / 户型
	// / <summary>
	// / 户型
	// / </summary>
	@Column("HouseType")
	private String houseType;

	// / 询价状态
	// / <summary>
	// / 询价状态
	// / </summary>
	@Column("EnquiryStatus")
	private EnquiryStatus enquiryStatus;

	// / 询价备注
	// / <summary>
	// / 询价备注
	// / </summary>
	@Column("Remark")
	private String remark;

	@Column("EnquiryResult")
	private byte[] enquiryResult;

	public String getUseType() {
		return useType;
	}


	public void setUseType(String useType) {
		this.useType = useType;
	}


	public String getAppraisalPurpose() {
		return appraisalPurpose;
	}


	public void setAppraisalPurpose(String appraisalPurpose) {
		this.appraisalPurpose = appraisalPurpose;
	}


	public double getArea() {
		return area;
	}


	public void setArea(double area) {
		this.area = area;
	}


	public String getBuiltYear() {
		return builtYear;
	}


	public void setBuiltYear(String builtYear) {
		this.builtYear = builtYear;
	}


	public String getElevator() {
		return elevator;
	}


	public void setElevator(String elevator) {
		this.elevator = elevator;
	}


	public String getToward() {
		return toward;
	}


	public void setToward(String toward) {
		this.toward = toward;
	}


	public String getHouseType() {
		return houseType;
	}


	public void setHouseType(String houseType) {
		this.houseType = houseType;
	}

	public EnquiryStatus getEnquiryStatus() {
		return enquiryStatus;
	}


	public void setEnquiryStatus(EnquiryStatus enquiryStatus) {
		this.enquiryStatus = enquiryStatus;
	}

	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}


	public byte[] getEnquiryResult() {
		return enquiryResult;
	}


	public void setEnquiryResult(byte[] enquiryResult) {
		this.enquiryResult = enquiryResult;
	}
}