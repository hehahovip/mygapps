/**
 * 
 */
package com.hehaho.netshoppingmate.fetcher.impl.htmltagvistors;

import org.htmlparser.Tag;
import org.htmlparser.tags.ImageTag;

import com.hehaho.netshoppingmate.Constants;
import com.hehaho.netshoppingmate.PriceImageManager;
import com.hehaho.netshoppingmate.fetcher.impl.NeweggPriceFetcher;

/**
 * @author Kevin.Wang
 *
 */
public class NeweggPriceImageTagVistor extends NetShoppingMateTagFindingVisitor {

	public NeweggPriceImageTagVistor(String[] tagsToBeFound) {
		super(tagsToBeFound);
	}
	
	public NeweggPriceImageTagVistor(String id, String[] tagsToBeFound) {
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
		if("img".equalsIgnoreCase(tag.getTagName())
				&& NeweggPriceFetcher.IDs.PRICE_IMG_CLASS_NAME.equalsIgnoreCase(tag
						.getAttribute("class"))){

					ImageTag image = (ImageTag)tag;
					String imageSrc = image.getAttribute(NeweggPriceFetcher.IDs.PRICE_IMG_SRC);
					
					priceURL = NeweggPriceFetcher.ROOT_URL + imageSrc;
				
					PriceImageManager imageManager = new PriceImageManager();
					this.setSuccess(true);
					this.imageData = imageManager.getImage(id, priceURL, Constants.IMG_FILE_EXTENSIONS.GIF_IMG);

		}
	}
}