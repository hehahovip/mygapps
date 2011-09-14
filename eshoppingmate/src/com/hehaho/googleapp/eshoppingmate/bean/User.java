/**
 * 
 */
package com.hehaho.googleapp.eshoppingmate.bean;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**
 * @author Kevin.Wang
 *
 */
public class User {

	private String userid;
	
	private int fetchFlag;
	
	private int mailFlag;

	/**
	 * @return the userid
	 */
	public String getUserid() {
		return userid;
	}

	/**
	 * @param userid the userid to set
	 */
	public void setUserid(String userid) {
		this.userid = userid;
	}

	/**
	 * @return the fetchFlag
	 */
	public int getFetchFlag() {
		return fetchFlag;
	}

	/**
	 * @param fetchFlag the fetchFlag to set
	 */
	public void setFetchFlag(int fetchFlag) {
		this.fetchFlag = fetchFlag;
	}

	/**
	 * @return the mailFlag
	 */
	public int getMailFlag() {
		return mailFlag;
	}

	/**
	 * @param mailFlag the mailFlag to set
	 */
	public void setMailFlag(int mailFlag) {
		this.mailFlag = mailFlag;
	}
	
}
