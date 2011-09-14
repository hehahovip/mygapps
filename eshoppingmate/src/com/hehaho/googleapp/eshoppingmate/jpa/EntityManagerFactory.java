package com.hehaho.googleapp.eshoppingmate.jpa;

import javax.persistence.EntityManager;

public class EntityManagerFactory {

	public static EntityManager getEntityManager(){
		return EMF.get().createEntityManager();
	}
	
}
