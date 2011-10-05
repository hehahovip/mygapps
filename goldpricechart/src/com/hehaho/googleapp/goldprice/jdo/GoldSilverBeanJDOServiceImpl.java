/**
 * 
 */
package com.hehaho.googleapp.goldprice.jdo;

import java.util.Date;
import java.util.List;

import com.hehaho.googleapp.goldprice.bean.GoldBean;
import com.hehaho.googleapp.goldprice.bean.SilverBean;
import com.hehaho.googleapp.goldprice.utils.DateUtils;

/**
 * @author Kevin.Wang
 *
 */
public class GoldSilverBeanJDOServiceImpl extends JDOServiceImpl implements GoldSilverBeanDBService {

	/* (non-Javadoc)
	 * @see com.hehaho.googleapp.goldprice.jdo.GoldSilverBeanDBService#getDataByCondition(java.lang.String, java.lang.String, java.lang.String, java.lang.Class)
	 */
	@Override
	public <T> List<T> getDataByCondition(String condition, String order, String range, Class<T> clazz) {
		return super.getDataBySpecifiedCondition(condition, order, range, clazz);
	}

	/* (non-Javadoc)
	 * @see com.hehaho.googleapp.goldprice.jdo.GoldSilverBeanDBService#saveGoldBean(com.hehaho.googleapp.goldprice.bean.GoldBean)
	 */
	@Override
	public void saveGoldBean(GoldBean bean) {
		GoldBean last = this.getLastestData(GoldBean.class);
		if(last != null){
			bean.setDiffOnOunces(bean.getValueOnOunces() - last.getValueOnOunces());
			bean.setDiffOnTonners(bean.getValueOnTonners() - last.getValueOnTonners());
		}
		
		super.save(bean);
	}

	/* (non-Javadoc)
	 * @see com.hehaho.googleapp.goldprice.jdo.GoldSilverBeanDBService#saveSilverBean(com.hehaho.googleapp.goldprice.bean.SilverBean)
	 */
	@Override
	public void saveSilverBean(SilverBean bean) {
		SilverBean last = this.getLastestData(SilverBean.class);
		if(last != null){
			bean.setDiffOnOunces(bean.getValueOnOunces() - last.getValueOnOunces());
			bean.setDiffOnTonners(bean.getValueOnTonners() - last.getValueOnTonners());
		}
		
		super.save(bean);
	}
	
	/* (non-Javadoc)
	 * @see com.hehaho.googleapp.goldprice.jdo.JDOService#getCurrentMonthData(java.lang.Class)
	 */
	@Override
	public <T> List<T> getDataByMonth(Class<T> clazz, Date date) {
		Date firstDayOfMonth = DateUtils.getFirstDayofMonth(date);
		Date lastDayOfMonth = DateUtils.getLastMonthofMonth(date);
		String first = DateUtils.formatDate(firstDayOfMonth);
		String last = DateUtils.formatDate(lastDayOfMonth);
		String condition = "select from " + clazz.getName() + " where date <= '" + last +"' && date >= '" + first + "' ";
		
		return this.getDataBySpecifiedCondition(condition, "date", null, clazz);
	}

	/* (non-Javadoc)
	 * @see com.hehaho.googleapp.goldprice.jdo.JDOService#getLastMonthData(java.lang.Class)
	 */
	@Override
	public <T> List<T> getLastMonthData(Class<T> clazz) {
		Date date = new Date();
		return this.getDataByMonth(clazz, date);
	}

	/* (non-Javadoc)
	 * @see com.hehaho.googleapp.goldprice.jdo.GoldSilverBeanDBService#getLastestData(java.lang.Class)
	 */
	@Override
	public <T> T getLastestData(Class<T> clazz) {
		List<T> list = this.getDataBySpecifiedCondition(null, "date desc", "0, 1", clazz);
		
		if(list.size() == 1){
			return list.get(0);
		}
		
		return null;
	}
}
