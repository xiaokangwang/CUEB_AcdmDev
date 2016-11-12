package org.kkdev.cueb.miscellaneous;

import javax.persistence.EntityManager;

import org.kkdev.cueb.db.PersisUtil;

public class LoggerMisc {
	public static Boolean Log(Integer LogLevel,String Action,String associated_Sessid,String Detail) {
		org.kkdev.cueb.entities.Log lg = new org.kkdev.cueb.entities.Log();
		lg.setTime(System.currentTimeMillis());
		lg.setAction(Action);
		lg.setAssociated_Sessid(associated_Sessid);
		lg.setLogLevel(LogLevel);
		lg.setDetail(Detail);
		EntityManager em=PersisUtil.getEntityManager();
		try {
			em.getTransaction().begin();
			em.merge(lg);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
		}
		return true;
	}
}
