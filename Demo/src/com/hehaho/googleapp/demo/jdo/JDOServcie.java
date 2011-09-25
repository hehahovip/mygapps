/**
 * 
 */
package com.hehaho.googleapp.demo.jdo;

import java.util.Date;
import java.util.UUID;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.hehaho.googleapp.demo.jdo.util.PMF;

/**
 * @author Kevin.Wang
 * 
 */
public class JDOServcie {

	private static Logger log = Logger.getLogger(JDOServcie.class.getName());

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JDOServcie s = new JDOServcie();
		s.savePerson();
	}

	public void savePerson() {
		String id = UUID.randomUUID().toString();

		PersistenceManager pm = PMF.get().getPersistenceManager();
		Person p = new Person();
		p.setId(id);
		p.setAge(100);
		p.setName("Kevin Wang");
		p.setCreateDate(new Date());
		p.setModifyDate(new Date());

		try {
			pm.makePersistent(p);
		} finally {
			pm.close();
		}

	}

	public String saveFile(String fileName, byte[] content) {
		File file = new File();
		file.setId(UUID.randomUUID().toString());
		file.setFileName(fileName);
		file.setContent(content);
		file.setCreatedDate(new Date());
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			log.info("Save file : " + fileName);
			log.info("File id : " + file.getId());
			pm.makePersistent(file);
		} finally {
//			pm.close();
			log.info("Save succefully!");
		}
		return file.getId();
	}

	public File getFileByID(String id) {
		File file = null;
		Key k = KeyFactory.createKey(File.class.getSimpleName(), id);
		PersistenceManager pm = PMF.get().getPersistenceManager();
		file = pm.getObjectById(File.class, k);
		pm.close();
		return file;
	}

}
