package org.kkdev.cueb.db;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public final class PersisUtil {
	private static EntityManagerFactory emf = null;
	public static EntityManager getEntityManager() {
		return getEntityManagerFactory().createEntityManager();
	}
	public static EntityManagerFactory getEntityManagerFactory(){
		if(emf == null){
			emf = Persistence.createEntityManagerFactory("CUEB_AcdmDev");
		}
		return emf;
	}
}
