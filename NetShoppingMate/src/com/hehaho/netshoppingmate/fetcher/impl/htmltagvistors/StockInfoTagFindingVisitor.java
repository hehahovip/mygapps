/**
 * 
 */
package com.hehaho.netshoppingmate.fetcher.impl.htmltagvistors;

import org.htmlparser.Node;
import org.htmlparser.Remark;
import org.htmlparser.Tag;
import org.htmlparser.Text;
import org.htmlparser.visitors.TagFindingVisitor;


/**
 * @author Kevin.Wang
 *
 */
public class StockInfoTagFindingVisitor extends TagFindingVisitor {

	private String stock_info;
	
	public StockInfoTagFindingVisitor(String[] tagsToBeFound) {
		super(tagsToBeFound);
	}

	/* (non-Javadoc)
	 * @see org.htmlparser.visitors.TagFindingVisitor#getEndTagCount(int)
	 */
	@Override
	public int getEndTagCount(int index) {
		return super.getEndTagCount(index);
	}

	/* (non-Javadoc)
	 * @see org.htmlparser.visitors.TagFindingVisitor#getTagCount(int)
	 */
	@Override
	public int getTagCount(int index) {
		return super.getTagCount(index);
	}

	/* (non-Javadoc)
	 * @see org.htmlparser.visitors.TagFindingVisitor#getTags(int)
	 */
	@Override
	public Node[] getTags(int index) {
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

	/* (non-Javadoc)
	 * @see org.htmlparser.visitors.NodeVisitor#beginParsing()
	 */
	@Override
	public void beginParsing() {
		// TODO Auto-generated method stub
		super.beginParsing();
	}

	/* (non-Javadoc)
	 * @see org.htmlparser.visitors.NodeVisitor#finishedParsing()
	 */
	@Override
	public void finishedParsing() {
		// TODO Auto-generated method stub
		super.finishedParsing();
	}

	/* (non-Javadoc)
	 * @see org.htmlparser.visitors.NodeVisitor#shouldRecurseChildren()
	 */
	@Override
	public boolean shouldRecurseChildren() {
		// TODO Auto-generated method stub
		return super.shouldRecurseChildren();
	}

	/* (non-Javadoc)
	 * @see org.htmlparser.visitors.NodeVisitor#shouldRecurseSelf()
	 */
	@Override
	public boolean shouldRecurseSelf() {
		return super.shouldRecurseSelf();
	}

	/* (non-Javadoc)
	 * @see org.htmlparser.visitors.NodeVisitor#visitRemarkNode(org.htmlparser.Remark)
	 */
	@Override
	public void visitRemarkNode(Remark remark) {
		super.visitRemarkNode(remark);
	}

	/* (non-Javadoc)
	 * @see org.htmlparser.visitors.NodeVisitor#visitStringNode(org.htmlparser.Text)
	 */
	@Override
	public void visitStringNode(Text string) {
		super.visitStringNode(string);
	}

	/**
	 * @return the stock_info
	 */
	public String getStock_info() {
		return stock_info;
	}

	/**
	 * @param stock_info the stock_info to set
	 */
	public void setStock_info(String stock_info) {
		this.stock_info = stock_info;
	}

}
