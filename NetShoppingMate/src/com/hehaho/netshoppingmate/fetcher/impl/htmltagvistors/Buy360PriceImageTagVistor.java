/**
 * 
 */
package com.hehaho.netshoppingmate.fetcher.impl.htmltagvistors;

import org.htmlparser.Node;
import org.htmlparser.Tag;
import org.htmlparser.tags.ImageTag;

import com.hehaho.netshoppingmate.Constants;
import com.hehaho.netshoppingmate.PriceImageManager;
import com.hehaho.netshoppingmate.fetcher.impl.Buy360PriceFetcher;

/**
 * @author Kevin.Wang
 * 
 */
public class Buy360PriceImageTagVistor extends NetShoppingMateTagFindingVisitor {

	public Buy360PriceImageTagVistor(String[] tagsToBeFound) {
		super(tagsToBeFound);
	}

	public Buy360PriceImageTagVistor(String id, String[] tagsToBeFound) {
		super(tagsToBeFound);
		this.id = id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.htmlparser.visitors.TagFindingVisitor#visitTag(org.htmlparser.Tag)
	 */
	@Override
	public void visitTag(Tag tag) {
		super.visitTag(tag);
		
		if(Buy360PriceFetcher.IDs.TITLE_DIV_ID.equalsIgnoreCase(tag.getAttribute("id"))){
				this.title = tag.getChildren().toHtml();
		}
		
		if ("strong".equalsIgnoreCase(tag.getTagName())
				&& Buy360PriceFetcher.IDs.PRICE_STRONG_CLASS_NAME
						.equalsIgnoreCase(tag.getAttribute("class"))) {

			Node parent = tag.getParent();
			for(int i = 0; i < parent.getChildren().size(); i++){
				
				if (parent.getChildren().elementAt(i) instanceof ImageTag) {
					ImageTag imageTag = (ImageTag) parent.getChildren().elementAt(i);
					priceURL = imageTag
							.getAttribute(Buy360PriceFetcher.IDs.PRICE_IMG_SRC);
					PriceImageManager imageManager = new PriceImageManager();
					this.setSuccess(true);
					this.imageData = imageManager.getImage(id, priceURL, Constants.IMG_FILE_EXTENSIONS.PNG_IMG);
				}
			}
		}
	}

}