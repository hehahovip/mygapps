/**
 * 
 */
package com.hehaho.googleapp.eshoppingmate.bean;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Kevin.Wang
 *
 */
@Entity
public class ShoppingSite implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String siteId;
	
	@Basic
	private String siteName;
	
	@Basic
	private String siteURL;
	
	@Basic
	private String siteClass;
	
	@Basic
	private String comments;

	/**
	 * @return the siteId
	 */
	public String getSiteId() {
		return siteId;
	}

	/**
	 * @param siteId the siteId to set
	 */
	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}

	/**
	 * @return the siteName
	 */
	public String getSiteName() {
		return siteName;
	}

	/**
	 * @param siteName the siteName to set
	 */
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	/**
	 * @return the siteURL
	 */
	public String getSiteURL() {
		return siteURL;
	}

	/**
	 * @param siteURL the siteURL to set
	 */
	public void setSiteURL(String siteURL) {
		this.siteURL = siteURL;
	}

	/**
	 * @return the siteClass
	 */
	public String getSiteClass() {
		return siteClass;
	}

	/**
	 * @param siteClass the siteClass to set
	 */
	public void setSiteClass(String siteClass) {
		this.siteClass = siteClass;
	}

	/**
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * @param comments the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ShoppingSite [siteId=" + siteId + ", siteName=" + siteName
				+ ", siteURL=" + siteURL + ", siteClass=" + siteClass
				+ ", comments=" + comments + "]";
	}
	
}
