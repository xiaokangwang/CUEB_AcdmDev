package org.kkdev.cueb.logic.user;

import javax.persistence.EntityManager;

import org.kkdev.cueb.db.PersisUtil;
import org.kkdev.cueb.entities.User;
import org.kkdev.cueb.entities.User_Token;
import org.kkdev.cueb.universal.TokenVerifier;



public class TokenCreate {
	public static User_Token CreateToken(String type,String script) {
		User_Token ut = new User_Token();
		ut.setScript(script);
		ut.setType(type);
		EntityManager em=PersisUtil.getEntityManager();
		try {
			em.getTransaction().begin();
			em.merge(ut);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
		}
		return ut;
	}
	
	public static User_Token GenerateToken(User proposedUser,String type,String inputScript) {
		String uToken= TokenVerifier.GenerateScript(proposedUser, type, inputScript);
		User_Token ut = new User_Token();
		ut.setScript(uToken);
		ut.setType(type);
		EntityManager em=PersisUtil.getEntityManager();
		try {
			em.getTransaction().begin();
			em.merge(ut);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
		}
		return ut;
	}
	
}
