package com.hehaho.googleapp.goldprice.jdo;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.hehaho.googleapp.goldprice.jdo.utils.PMF;

public class JDOServiceImpl implements JDOService{
	
	private static Logger Log = Logger.getLogger(JDOServiceImpl.class.getName());
	
	private PersistenceManager pm = PMF.get().getPersistenceManager();

	/* (non-Javadoc)
	 * @see com.hehaho.googleapp.goldprice.jdo.JDOService#save(java.lang.Object)
	 */
	@Override
	public <T> void save(T bean) {
		pm.makePersistent(bean);
	}


	/* (non-Javadoc)
	 * @see com.hehaho.googleapp.goldprice.jdo.JDOService#getDataBySpecifiedCondition(java.lang.String, java.lang.Class)
	 */
	@Override
	public <T> List<T> getDataBySpecifiedCondition(String condition, String order, String range, Class<T> clazz) {
		Log.info("Querying, condition: " + condition + ", order: " + order + ", range: " + range);
		Query query = pm.newQuery(clazz);
		query.setFilter(condition);
		query.setOrdering(order);
		query.setRange(range);
		
		List<T> results = null;
		try{
			 results = (List<T>) query.execute();
		} catch(Exception e){
			Log.log(Level.SEVERE, "Error excuting getDataBySpecifiedCondition()", e);
		}		finally{
			query.closeAll();
		}
		
		return results;
	}

}
