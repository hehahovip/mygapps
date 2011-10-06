/**
 * 
 */
package com.hehaho.googleapp.goldprice.bean;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.hehaho.metalpriceinfofetcher.bean.MetalPriceBean;

/**
 * @author Kevin.Wang
 *
 */
@PersistenceCapable(identityType=IdentityType.APPLICATION)
public class SilverBean {

	@Persistent
	private double closedPrice;
	
	@Persistent
	private double totalAsset;
	
	@Persistent
	private double valueOnOunces;
	
	@Persistent
	private double valueOnTonners;
	
	@PrimaryKey
	@Persistent(valueStrategy=IdGeneratorStrategy.IDENTITY)
	private String date;
	
	@Persistent
	private double diffOnOunces;
	
	@Persistent
	private double diffOnTonners;

	public SilverBean() {
	}

	public SilverBean(MetalPriceBean bean){
		this.setClosedPrice(bean.getClosedPrice());
		this.setDate(bean.getDate());
		this.setTotalAsset(bean.getTotalAsset());
		this.setValueOnOunces(bean.getValueOnOunces());
		this.setValueOnTonners(bean.getValueOnTonners());
	}
	
	public MetalPriceBean getMetalPriceBean(){
		MetalPriceBean bean = new MetalPriceBean();
		bean.setClosedPrice(closedPrice);
		bean.setDate(date);
		bean.setTotalAsset(totalAsset);
		bean.setValueOnOunces(valueOnOunces);
		bean.setValueOnTonners(valueOnTonners);
		bean.setDiffOnOunces(diffOnOunces);
		bean.setDiffOnTonners(diffOnTonners);
		
		return bean;
	}
	
	/**
	 * @return the closedPrice
	 */
	public double getClosedPrice() {
		return closedPrice;
	}

	/**
	 * @param closedPrice the closedPrice to set
	 */
	public void setClosedPrice(double closedPrice) {
		this.closedPrice = closedPrice;
	}

	/**
	 * @return the totalAsset
	 */
	public double getTotalAsset() {
		return totalAsset;
	}

	/**
	 * @param totalAsset the totalAsset to set
	 */
	public void setTotalAsset(double totalAsset) {
		this.totalAsset = totalAsset;
	}

	/**
	 * @return the valueOnOunces
	 */
	public double getValueOnOunces() {
		return valueOnOunces;
	}

	/**
	 * @param valueOnOunces the valueOnOunces to set
	 */
	public void setValueOnOunces(double valueOnOunces) {
		this.valueOnOunces = valueOnOunces;
	}

	/**
	 * @return the valueOnTonners
	 */
	public double getValueOnTonners() {
		return valueOnTonners;
	}

	/**
	 * @param valueOnTonners the valueOnTonners to set
	 */
	public void setValueOnTonners(double valueOnTonners) {
		this.valueOnTonners = valueOnTonners;
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param String the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @return the diffOnOunces
	 */
	public double getDiffOnOunces() {
		return diffOnOunces;
	}

	/**
	 * @param diffOnOunces the diffOnOunces to set
	 */
	public void setDiffOnOunces(double diffOnOunces) {
		this.diffOnOunces = diffOnOunces;
	}

	/**
	 * @return the diffOnTonners
	 */
	public double getDiffOnTonners() {
		return diffOnTonners;
	}

	/**
	 * @param diffOnTonners the diffOnTonners to set
	 */
	public void setDiffOnTonners(double diffOnTonners) {
		this.diffOnTonners = diffOnTonners;
	}
	
	
}
