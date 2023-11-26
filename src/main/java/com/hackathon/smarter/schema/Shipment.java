/**
 * 
 */
package com.hackathon.smarter.schema;

/**
 * @author veera
 *
 */
public class Shipment {
	
	private String goodsDescription;
	
	private Waybill waybill;
	
	private String errormsg;

	public String getGoodsDescription() {
		return goodsDescription;
	}

	public void setGoodsDescription(String goodsDescription) {
		this.goodsDescription = goodsDescription;
	}

	public Waybill getWaybill() {
		return waybill;
	}

	public void setWaybill(Waybill waybill) {
		this.waybill = waybill;
	}

	public String getErrormsg() {
		return errormsg;
	}

	public void setErrormsg(String errormsg) {
		this.errormsg = errormsg;
	}
	
	
	
	

}
