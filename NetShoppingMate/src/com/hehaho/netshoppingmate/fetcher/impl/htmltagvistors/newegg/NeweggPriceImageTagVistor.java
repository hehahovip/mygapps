/**
 * 
 */
package com.hehaho.netshoppingmate.fetcher.impl.htmltagvistors.newegg;

import org.htmlparser.Tag;
import org.htmlparser.tags.ImageTag;
import org.htmlparser.util.NodeList;

import com.hehaho.netshoppingmate.Constants;
import com.hehaho.netshoppingmate.PriceImageManager;
import com.hehaho.netshoppingmate.fetcher.impl.NeweggPriceFetcher;
import com.hehaho.netshoppingmate.fetcher.impl.htmltagvistors.NetShoppingMateTagFindingVisitor;

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
		if(NeweggPriceFetcher.IDs.PRICE_IMG_CLASS_NAME.equalsIgnoreCase(tag
						.getAttribute("class"))){

			NodeList nodes = tag.getChildren();
			
			if(nodes.size() >=2){
				NodeList subNodes = nodes.elementAt(1).getChildren();
				for (int i = 0; i < subNodes.size(); i++) {
					if (subNodes.elementAt(i) instanceof ImageTag) {
						ImageTag image = (ImageTag)subNodes.elementAt(i);
						String imageSrc = image.getAttribute(NeweggPriceFetcher.IDs.PRICE_IMG_SRC);
						
						priceURL = imageSrc;
					
						PriceImageManager imageManager = new PriceImageManager();
						this.setSuccess(true);
						this.imageData = imageManager.getImage(id, priceURL, Constants.IMG_FILE_EXTENSIONS.GIF_IMG);
						break;
					}
				}
			}
			
		}
	}
}