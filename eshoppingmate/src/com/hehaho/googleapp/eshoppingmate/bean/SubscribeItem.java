/**
 * 
 */
package com.hehaho.googleapp.eshoppingmate.bean;

import java.util.Date;

/**
 * @author Kevin.Wang
 *
 */
public class SubscribeItem {

	private SubscribeItemPK pk;
	
	private ShoppingSite site;
	
	private Date createdTime;
	
	private Date updatedTime;

	/**
	 * @return the pk
	 */
	public SubscribeItemPK getPk() {
		return pk;
	}

	/**
	 * @param pk the pk to set
	 */
	public void setPk(SubscribeItemPK pk) {
		this.pk = pk;
	}
	
	/**
	 * @return the site
	 */
	public ShoppingSite getSite() {
		return site;
	}

	/**
	 * @param site the site to set
	 */
	public void setSite(ShoppingSite site) {
		this.site = site;
	}

	/**
	 * @return the createdTime
	 */
	public Date getCreatedTime() {
		return createdTime;
	}

	/**
	 * @param createdTime the createdTime to set
	 */
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	/**
	 * @return the updatedTime
	 */
	public Date getUpdatedTime() {
		return updatedTime;
	}

	/**
	 * @param updatedTime the updatedTime to set
	 */
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	
}
