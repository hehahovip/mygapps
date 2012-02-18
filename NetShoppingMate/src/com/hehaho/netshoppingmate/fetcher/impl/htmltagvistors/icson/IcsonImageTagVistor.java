/**
 * 
 */
package com.hehaho.netshoppingmate.fetcher.impl.htmltagvistors.icson;

import org.htmlparser.Node;
import org.htmlparser.Tag;
import org.htmlparser.tags.ImageTag;
import org.htmlparser.util.NodeList;

import com.hehaho.netshoppingmate.Constants;
import com.hehaho.netshoppingmate.PriceImageManager;
import com.hehaho.netshoppingmate.fetcher.impl.IcsonPriceFetcher;
import com.hehaho.netshoppingmate.fetcher.impl.htmltagvistors.NetShoppingMateTagFindingVisitor;
import com.hehaho.netshoppingmate.utils.URLUtils;

/**
 * @author Kevin.Wang
 * 
 */
public class IcsonImageTagVistor extends NetShoppingMateTagFindingVisitor {

	public IcsonImageTagVistor(String[] tagsToBeFound) {
		super(tagsToBeFound);
	}

	public IcsonImageTagVistor(String id, String[] tagsToBeFound) {
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
//		System.out.println("--------" + tag.toHtml());
		System.out.println("--------" + tag.getAttribute("class"));
		if ("strong".equalsIgnoreCase(tag.getTagName())
				&& IcsonPriceFetcher.IDs.PRICE_IMG_CLASS_NAME
						.equalsIgnoreCase(tag.getAttribute("class"))) {
			
			Node parent = tag.getParent();
			NodeList nodes = parent.getChildren();

			for (int i = 0; i < nodes.size(); i++) {

//				if (nodes.elementAt(i) instanceof ImageTag) {
//					ImageTag imageTag = (ImageTag) nodes.elementAt(i);
//
//					String url = IcsonPriceFetcher.BASE_FETCH_URL
//							+ imageTag
//									.getAttribute(IcsonPriceFetcher.IDs.PRICE_IMG_SRC);
//					priceURL = URLUtils.replaceSpace(url);
//					PriceImageManager imageManager = new PriceImageManager();
//					this.imageData = imageManager.getImage(id, priceURL,
//							Constants.IMG_FILE_EXTENSIONS.PNG_IMG);
//					this.setSuccess(true);
//				}
				
				this.priceURL = tag.getText();
				this.setSuccess(true);
			}
		}
	}

}