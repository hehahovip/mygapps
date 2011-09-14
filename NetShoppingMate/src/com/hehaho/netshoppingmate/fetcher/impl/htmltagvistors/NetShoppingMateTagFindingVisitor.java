/**
 * 
 */
package com.hehaho.netshoppingmate.fetcher.impl.htmltagvistors;

import org.htmlparser.Node;
import org.htmlparser.Tag;
import org.htmlparser.visitors.TagFindingVisitor;

/**
 * @author Kevin.Wang
 *
 * Do nothing, just add two fields, and getter methods.
 */
public class NetShoppingMateTagFindingVisitor extends TagFindingVisitor {


	protected String id;
	
	protected String priceURL;
	
	protected byte[] imageData;
	
	private boolean success;
	
	protected String title;
	
	public NetShoppingMateTagFindingVisitor(String[] tagsToBeFound) {
		super(tagsToBeFound);
	}
	
	/* (non-Javadoc)
	 * @see org.htmlparser.visitors.TagFindingVisitor#getEndTagCount(int)
	 */
	@Override
	public int getEndTagCount(int index) {
		// TODO Auto-generated method stub
		return super.getEndTagCount(index);
	}

	/* (non-Javadoc)
	 * @see org.htmlparser.visitors.TagFindingVisitor#getTagCount(int)
	 */
	@Override
	public int getTagCount(int index) {
		// TODO Auto-generated method stub
		return super.getTagCount(index);
	}

	/* (non-Javadoc)
	 * @see org.htmlparser.visitors.TagFindingVisitor#getTags(int)
	 */
	@Override
	public Node[] getTags(int index) {
		// TODO Auto-generated method stub
		return super.getTags(index);
	}

	/* (non-Javadoc)
	 * @see org.htmlparser.visitors.TagFindingVisitor#visitEndTag(org.htmlparser.Tag)
	 */
	@Override
	public void visitEndTag(Tag arg0) {
		// TODO Auto-generated method stub
		super.visitEndTag(arg0);
	}

	/* (non-Javadoc)
	 * @see org.htmlparser.visitors.TagFindingVisitor#visitTag(org.htmlparser.Tag)
	 */
	@Override
	public void visitTag(Tag arg0) {
		// TODO Auto-generated method stub
		super.visitTag(arg0);
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return the priceURL
	 */
	public String getPriceURL() {
		return priceURL;
	}

	/**
	 * @return the imageData
	 */
	public byte[] getImageData() {
		return imageData;
	}

	/**
	 * @param success the success to set
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}

	/**
	 * @return the success
	 */
	public boolean isSuccess() {
		return success;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

}
