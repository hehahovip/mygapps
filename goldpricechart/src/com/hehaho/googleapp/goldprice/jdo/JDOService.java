/**
 * 
 */
package com.hehaho.googleapp.goldprice.jdo;

import java.util.List;

/**
 * @author Kevin.Wang
 *
 */
public interface JDOService {
	
	public<T> void save(T bean);

	public <T> List<T> getDataBySpecifiedCondition(String condition, String order, String range, Class<T> clazz);
	
}
