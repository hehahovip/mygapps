/**
 * 
 */
package com.hehaho.googleapp.demo.jdo.util;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

/**
 * @author Kevin.Wang
 *
 */
public class PMF {
	private static final PersistenceManagerFactory pmfInstance =
        JDOHelper.getPersistenceManagerFactory("transactions-optional");

    private PMF() {}

    public static PersistenceManagerFactory get() {
        return pmfInstance;
    }
}
