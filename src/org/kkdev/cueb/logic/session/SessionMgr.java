package org.kkdev.cueb.logic.session;

import javax.persistence.EntityManager;

import org.kkdev.cueb.db.PersisUtil;
import org.kkdev.cueb.entities.Session;
import org.kkdev.cueb.universal.UniversalUtil;

public class SessionMgr {
	public static Session getNewSession() {
		Session newsess = new Session();
		String newid=UniversalUtil.generateId(128);
		newsess.setSessionId(newid);
		EntityManager em=PersisUtil.getEntityManager();
		try {
			em.getTransaction().begin();
			em.merge(newsess);
			em.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.toString());
			em.getTransaction().rollback();
		}
		return newsess;
	}
	public static Session GetSessionByName(String name) {
		//TODO
		EntityManager em=PersisUtil.getEntityManager();
		return em.find(Session.class, "name");
	}
}
