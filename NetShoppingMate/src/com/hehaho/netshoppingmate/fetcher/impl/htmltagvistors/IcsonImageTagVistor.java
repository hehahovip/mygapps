/**
 * 
 */
package com.hehaho.netshoppingmate.fetcher.impl.htmltagvistors;

import org.htmlparser.Tag;
import org.htmlparser.tags.ImageTag;
import org.htmlparser.util.NodeList;

import com.hehaho.netshoppingmate.Constants;
import com.hehaho.netshoppingmate.PriceImageManager;
import com.hehaho.netshoppingmate.fetcher.impl.IcsonPriceFetcher;
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
		if ("span".equalsIgnoreCase(tag.getTagName())
				&& IcsonPriceFetcher.IDs.PRICE_IMG_CLASS_NAME
						.equalsIgnoreCase(tag.getAttribute("class"))) {

			NodeList nodes = tag.getChildren();

			for (int i = 0; i < nodes.size(); i++) {

				if (nodes.elementAt(i) instanceof ImageTag) {
					ImageTag imageTag = (ImageTag) nodes.elementAt(i);

					String url = IcsonPriceFetcher.BASE_FETCH_URL
							+ imageTag
									.getAttribute(IcsonPriceFetcher.IDs.PRICE_IMG_SRC);
					priceURL = URLUtils.replaceSpace(url);
					PriceImageManager imageManager = new PriceImageManager();
					this.imageData = imageManager.getImage(id, priceURL,
							Constants.IMG_FILE_EXTENSIONS.PNG_IMG);
					this.setSuccess(true);
				}
			}
		}
	}

}