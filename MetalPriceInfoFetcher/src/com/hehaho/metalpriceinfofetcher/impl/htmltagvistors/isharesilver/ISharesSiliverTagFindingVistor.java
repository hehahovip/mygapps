/**
 * 
 */
package com.hehaho.metalpriceinfofetcher.impl.htmltagvistors.isharesilver;

import org.htmlparser.Node;
import org.htmlparser.Tag;
import org.htmlparser.tags.BodyTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.visitors.NodeVisitor;
import org.htmlparser.visitors.TagFindingVisitor;

import com.hehaho.metalpriceinfofetcher.impl.IShareSilverPriceFetcher;
import com.hehaho.metalpriceinfofetcher.impl.htmltagvistors.MetalPriceInfoTagFindingVisitor;

/**
 * @author Kevin.Wang
 *
 */
public class ISharesSiliverTagFindingVistor extends
		MetalPriceInfoTagFindingVisitor {

	public ISharesSiliverTagFindingVistor(String[] tagsToBeFound) {
		super(tagsToBeFound);
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
		if (IShareSilverPriceFetcher.CSS_CLASS_STYLE
						.equalsIgnoreCase(tag.getAttribute("class"))) {
			
			// Get tbody
			Node node = tag.getFirstChild();
			
			Node table = tag.getFirstChild().getParent();
			
			System.out.println("table.html = " + table.toHtml());
			NodeList head_body = node.getParent().getChildren();
			for(int i = 0; i < head_body.size(); i++){
				System.out.println("body = " + head_body.elementAt(i).toHtml());
			}
			String[] target = { "TBODY" };
			ISharesSilverTBodyVistor1 v = new ISharesSilverTBodyVistor1(target);
			node.getParent().accept(v);
			
			
		}
	}
}

class ISharesSilverTBodyVistor1 extends TagFindingVisitor{

	public ISharesSilverTBodyVistor1(String[] tagsToBeFound) {
		super(tagsToBeFound);
	}

	/* (non-Javadoc)
	 * @see org.htmlparser.visitors.TagFindingVisitor#visitTag(org.htmlparser.Tag)
	 */
	@Override
	public void visitTag(Tag tag) {
		super.visitTag(tag);
		if("tbody".equalsIgnoreCase(tag.getTagName())){
			
			
			if(tag instanceof BodyTag){
				System.out.println(true);
			}
			
			Node tbody = tag.getFirstChild().getParent();
			System.out.println(tbody.toHtml());
			
			System.out.println("tag :" + tbody.toHtml());
			try {
				Node cloneNode = (Node)tag.clone();
				System.out.println(cloneNode.toHtml());
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			NodeList nodes = ((Node)tag).getChildren();
			for(int i = 0; i < nodes.size(); i++){
				Node tr = nodes.elementAt(i);
				if(i == 0){
					// Total Net Assets
					System.out.println(tr.getLastChild().getText());
				} else if(i == 4){
					// Ounces of Silver
					System.out.println(tr.getLastChild().getText());
				} else if(i == 5){
					// Tonnes of Silver
					System.out.println(tr.getLastChild().getText());
				} else if(i == 11){
					// Price & Date
					System.out.println(tr.getLastChild().toHtml());
				}
			}
		}
	}
	
}

class ISharesSilverTBodyVistor extends NodeVisitor{

	/* (non-Javadoc)
	 * @see org.htmlparser.visitors.NodeVisitor#beginParsing()
	 */
	@Override
	public void beginParsing() {
		// TODO Auto-generated method stub
		super.beginParsing();
	}

	/* (non-Javadoc)
	 * @see org.htmlparser.visitors.NodeVisitor#visitTag(org.htmlparser.Tag)
	 */
	@Override
	public void visitTag(Tag tag) {
		super.visitTag(tag);
		if("tbody".equalsIgnoreCase(tag.getTagName())){
			
			System.out.println(tag.toHtml());
			Node tbody = tag.getFirstChild().getParent();
			
			System.out.println("tag :" + tbody.toHtml());
			try {
				Node cloneNode = (Node)tag.clone();
				System.out.println(cloneNode.toHtml());
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			NodeList nodes = ((Node)tag).getChildren();
			for(int i = 0; i < nodes.size(); i++){
				Node tr = nodes.elementAt(i);
				if(i == 0){
					// Total Net Assets
					System.out.println(tr.getLastChild().getText());
				} else if(i == 4){
					// Ounces of Silver
					System.out.println(tr.getLastChild().getText());
				} else if(i == 5){
					// Tonnes of Silver
					System.out.println(tr.getLastChild().getText());
				} else if(i == 11){
					// Price & Date
					System.out.println(tr.getLastChild().toHtml());
				}
			}
		}
	}
	
}
