/**
 * 
 */
package com.hehaho.netshoppingmate.fetcher.impl.htmltagvistors.buy360;

import org.htmlparser.Node;
import org.htmlparser.Tag;
import org.htmlparser.util.NodeList;

import com.hehaho.netshoppingmate.fetcher.impl.Buy360PriceFetcher;
import com.hehaho.netshoppingmate.fetcher.impl.htmltagvistors.StockInfoTagFindingVisitor;

/**
 * @author Kevin.Wang
 *
 */
public class Buy360StockInfoTagVistor extends StockInfoTagFindingVisitor {

	private boolean success;
	
	public Buy360StockInfoTagVistor(String[] tagsToBeFound) {
		super(tagsToBeFound);
	}

	/* (non-Javadoc)
	 * @see com.hehaho.netshoppingmate.fetcher.impl.htmltagvistors.StockInfoTagFindingVisitor#visitTag(org.htmlparser.Tag)
	 */
	@Override
	public void visitTag(Tag arg0) {
		super.visitTag(arg0);
		
		if(Buy360PriceFetcher.IDs.STOCK_CLASS_ID.equalsIgnoreCase(arg0.getAttribute("class"))){
			NodeList list = arg0.getChildren();
			
			if(list.size() > 0){
				Node stock = list.elementAt(0);
				this.setStock_info(stock.getText());
				this.setSuccess(true);
			}
		}
	}

	/**
	 * @return the success
	 */
	public boolean isSuccess() {
		return success;
	}

	/**
	 * @param success the success to set
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}

}
