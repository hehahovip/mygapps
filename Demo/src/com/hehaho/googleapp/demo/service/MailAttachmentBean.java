/**
 * 
 */
package com.hehaho.googleapp.demo.service;

/**
 * @author Kevin.Wang
 *
 */
public class MailAttachmentBean {

	private String filename;
	
	private byte[] attachmentData;
	
	private String type;

	public MailAttachmentBean() {
		super();
	}

	public MailAttachmentBean(String filename, byte[] attachmentData,
			String type) {
		super();
		this.filename = filename;
		this.attachmentData = attachmentData;
		this.type = type;
	}

	/**
	 * @return the filename
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * @param filename the filename to set
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}

	/**
	 * @return the attachmentData
	 */
	public byte[] getAttachmentData() {
		return attachmentData;
	}

	/**
	 * @param attachmentData the attachmentData to set
	 */
	public void setAttachmentData(byte[] attachmentData) {
		this.attachmentData = attachmentData;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	
}
