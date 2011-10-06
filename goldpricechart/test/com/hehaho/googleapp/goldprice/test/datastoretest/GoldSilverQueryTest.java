package com.hehaho.googleapp.goldprice.test.datastoretest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import com.hehaho.googleapp.goldprice.bean.GoldBean;
import com.hehaho.googleapp.goldprice.jdo.GoldSilverBeanDBService;
import com.hehaho.googleapp.goldprice.jdo.GoldSilverBeanJDOServiceImpl;

public class GoldSilverQueryTest extends LocalDataStoreTest {

	private GoldSilverBeanDBService svc = new GoldSilverBeanJDOServiceImpl();

	@Test
	public void testSave() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = sdf.parse("2011-10-01");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < 10; i++) {
			GoldBean bean = new GoldBean();
			bean.setClosedPrice((float) (i + 0.1));
			if (i > 0) {
				Calendar c = Calendar.getInstance();
				c.setTime(date);
				bean.setDate(sdf.format(new Date(date.getTime() + i * 1000 * 60 * 60 * 24)));
			} else {
				bean.setDate(sdf.format(date));
			}
			bean.setTotalAsset(i * 10000);
			bean.setValueOnOunces(i * 100);
			bean.setValueOnTonners(i * 10);
			svc.saveGoldBean(bean);
		}
		
		
		List<GoldBean> list = svc.getDataByCondition(null, "date", null, GoldBean.class);
		
		Assert.assertEquals(10, list.size());
		
		for(GoldBean bean : list){
			System.out.println("-------------");
			System.out.println(bean.getDate());
			System.out.println(bean.getTotalAsset());
		}
	}
	
	@Test
	public void testSaveGold(){
		GoldBean last = new GoldBean();
		last.setDate("2011-10-01");
		last.setValueOnOunces(1.0);
		last.setValueOnTonners(10.0);
		svc.saveGoldBean(last);
		
		GoldBean last_one = svc.getLastestData(GoldBean.class);
		
		Assert.assertEquals((double)0, last_one.getDiffOnOunces());
		Assert.assertEquals((double)0, last_one.getDiffOnTonners());
		
		GoldBean newone = new GoldBean();
		newone.setDate("2011-10-02");
		newone.setValueOnOunces(5);
		newone.setValueOnTonners(50);
		svc.saveGoldBean(newone);
		
		GoldBean new_one = svc.getLastestData(GoldBean.class);
		
		Assert.assertEquals(newone.getValueOnOunces(), new_one.getValueOnOunces());
		Assert.assertEquals(newone.getValueOnTonners(), new_one.getValueOnTonners());
		
		Assert.assertEquals((double)4, new_one.getDiffOnOunces());
		Assert.assertEquals((double)40, new_one.getDiffOnTonners());
	}
	
	@Test
	public void testQueryRange(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = sdf.parse("2011-10-01");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < 10; i++) {
			GoldBean bean = new GoldBean();
			bean.setClosedPrice((float) (i + 0.1));
			if (i > 0) {
				Calendar c = Calendar.getInstance();
				c.setTime(date);
				bean.setDate(sdf.format(new Date(date.getTime() + i * 1000 * 60 * 60 * 24)));
			} else {
				bean.setDate(sdf.format(date));
			}
			bean.setTotalAsset(i * 10000);
			bean.setValueOnOunces(i * 100);
			bean.setValueOnTonners(i * 10);
			svc.saveGoldBean(bean);
		}
		
		List<GoldBean> list = svc.getDataByCondition(null, "date", "0,1", GoldBean.class);
		
		Assert.assertEquals(1, list.size());
		
	}
	
	@Test
	public void testIndex(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = sdf.parse("2011-10-01");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < 10; i++) {
			GoldBean bean = new GoldBean();
			bean.setClosedPrice((float) (i + 0.1));
			if (i > 0) {
				Calendar c = Calendar.getInstance();
				c.setTime(date);
				bean.setDate(sdf.format(new Date(date.getTime() + i * 1000 * 60 * 60 * 24)));
			} else {
				bean.setDate(sdf.format(date));
			}
			bean.setTotalAsset(i * 10000);
			bean.setValueOnOunces(i * 100);
			bean.setValueOnTonners(i * 10);
			svc.saveGoldBean(bean);
		}
		
		List<GoldBean> list = svc.getDataByCondition(null, "date desc", "0, 1", GoldBean.class);
		
		Assert.assertEquals(1, list.size());
		
		Assert.assertEquals("2011-10-10", list.get(0).getDate());
		
	}
	
	@Test
	public void testGetLastestData(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = sdf.parse("2011-10-01");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < 10; i++) {
			GoldBean bean = new GoldBean();
			bean.setClosedPrice((float) (i + 0.1));
			if (i > 0) {
				Calendar c = Calendar.getInstance();
				c.setTime(date);
				bean.setDate(sdf.format(new Date(date.getTime() + i * 1000 * 60 * 60 * 24)));
			} else {
				bean.setDate(sdf.format(date));
			}
			bean.setTotalAsset(i * 10000);
			bean.setValueOnOunces(i * 100);
			bean.setValueOnTonners(i * 10);
			svc.saveGoldBean(bean);
		}
		
		GoldBean bean = svc.getLastestData(GoldBean.class);
		
		Assert.assertEquals("2011-10-10", bean.getDate());
	}
	
	@Test
	public void testDoubleSave(){
		GoldBean one = new GoldBean();
		one.setDate("2011-10-01");
		one.setTotalAsset(1000);
		
		GoldBean two = new GoldBean();
		two.setDate("2011-10-01");
		two.setTotalAsset(2000);
		
		svc.saveGoldBean(one);
		
		svc.saveGoldBean(two);
		
		GoldBean result = svc.getLastestData(GoldBean.class);
		
		
		Assert.assertEquals(two.getDate(), result.getDate());
		Assert.assertEquals(two.getTotalAsset(), result.getTotalAsset());
	}

}
