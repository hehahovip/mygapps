/**
 * 
 */
package com.hehaho.googleapp.goldprice.jdo;

import java.util.Date;
import java.util.List;

import com.hehaho.googleapp.goldprice.bean.GoldBean;
import com.hehaho.googleapp.goldprice.bean.SilverBean;

/**
 * @author Kevin.Wang
 *
 */
public interface GoldSilverBeanDBService {

	public void saveGoldBean(GoldBean bean);
	
	public void saveSilverBean(SilverBean bean);
	
	public <T> List<T> getDataByMonth(Class<T> clazz, Date date);
	
	public <T> List<T> getLastMonthData(Class<T> clazz);
	
	public <T> List<T> getDataByCondition(String condition, String order, String range, Class<T> clazz);
	
	public <T> T getLastestData(Class<T> clazz);
	
}
