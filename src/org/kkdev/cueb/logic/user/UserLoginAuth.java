package org.kkdev.cueb.logic.user;

import javax.persistence.EntityManager;

import org.kkdev.cueb.db.PersisUtil;
import org.kkdev.cueb.entities.Session;
import org.kkdev.cueb.entities.User;
import org.kkdev.cueb.universal.TokenVerifier;

public class UserLoginAuth {
public static User Check(Session currentSession,String username,String InputScript) {
	User user=UserMgr.GetUserByName(username);
	if(user==null){
		return null;
	}
	if(TokenVerifier.Verify(currentSession, user, InputScript)){
		currentSession.setUser(user);
		EntityManager em=PersisUtil.getEntityManager();
		try {
			em.getTransaction().begin();
			em.merge(currentSession);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
		}
		return user;
	}
	return null;
}
}
