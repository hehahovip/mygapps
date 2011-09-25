/**
 * 
 */
package com.hehaho.metalpriceinfofetcher.impl.htmltagvistors;

import org.htmlparser.Node;
import org.htmlparser.Tag;
import org.htmlparser.visitors.TagFindingVisitor;

/**
 * @author Kevin.Wang
 *
 */
public class MetalPriceInfoTagFindingVisitor extends TagFindingVisitor {

	public MetalPriceInfoTagFindingVisitor(String[] tagsToBeFound) {
		super(tagsToBeFound);
		// TODO Auto-generated constructor stub
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

}
