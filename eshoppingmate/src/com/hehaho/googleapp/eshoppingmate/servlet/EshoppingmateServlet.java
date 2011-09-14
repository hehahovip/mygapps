package com.hehaho.googleapp.eshoppingmate.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.UUIDUtils;

import com.hehaho.googleapp.eshoppingmate.bean.Item;
import com.hehaho.googleapp.eshoppingmate.bean.ShoppingSite;
import com.hehaho.googleapp.eshoppingmate.bean.SubscribeItem;
import com.hehaho.googleapp.eshoppingmate.bean.SubscribeItemPK;
import com.hehaho.googleapp.eshoppingmate.jpa.EntityManagerFactory;

@SuppressWarnings("serial")
public class EshoppingmateServlet extends HttpServlet {
	
	private static Logger Log = Logger.getLogger(EshoppingmateServlet.class.getSimpleName());
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		testShoppingSite();
		
		resp.setContentType("text/plain");
		resp.getWriter().println("Hello, world");
		
		
	}
	
	private void testShoppingSite(){
		EntityManager manager = EntityManagerFactory.getEntityManager();
		ShoppingSite site = new ShoppingSite();
		
		site.setSiteId(UUIDUtils.generateUUIDString());
		site.setSiteName("Test Site Name");
		site.setSiteClass("TestSiteClass");
		site.setSiteURL("www.testsite.com");
		site.setComments("Test Comments");
		
		manager.persist(site);
		
		Log.fine("Site : " + site);
		
		
//		manager.createNamedQuery("select ")
		
		String id = site.getSiteId();
		
		ShoppingSite testsite = manager.find(ShoppingSite.class, id);
		
		Log.fine("Test ShoppingSite : " + testsite);
	}
	
	private void testSubcribeItem(){
		EntityManager manager = EntityManagerFactory.getEntityManager();
		Item item = new Item();
		item.setItemId(UUIDUtils.generateUUIDString());
		item.setName("TestItemSave");
		item.setComments("ForTest Coments");
		
		manager.persist(item);
		
		SubscribeItemPK pk = new SubscribeItemPK();
		pk.setUserId("yingkang.wang@gmail.com");
		pk.setItem(item);
		
		SubscribeItem subscribeItem = new SubscribeItem();
		
		subscribeItem.setPk(pk);
		
		Date createdTime = new Date();
		subscribeItem.setCreatedTime(createdTime);
		Log.fine("SubscribeItem createdTime = " + subscribeItem.getCreatedTime());
		manager.persist(subscribeItem);
		
		
		// Test result.
		String id = item.getItemId();
		
		Log.fine("id = " + id);
		
		Item testItem = manager.getReference(Item.class, id);
		
		Log.fine("testItem = " + testItem.toString());
		
		SubscribeItem testSubsribeItem = manager.getReference(SubscribeItem.class, pk);
		
		Log.fine("SubscribeItem id = " + pk.getUserId());
		Log.fine("TestSubscribeItem createdTime = " + testSubsribeItem.getCreatedTime());
		
		
		manager.close();
	}
}
