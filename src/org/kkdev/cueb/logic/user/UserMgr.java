package org.kkdev.cueb.logic.user;

import javax.persistence.EntityManager;

import org.kkdev.cueb.db.PersisUtil;
import org.kkdev.cueb.entities.User;
import org.kkdev.cueb.entities.User_Token;


public class UserMgr {
	public static User CreateUser(String username) {
		User newUser = new User();
		newUser.setUsername(username);
		EntityManager em=PersisUtil.getEntityManager();
		try {
			em.getTransaction().begin();
			em.merge(newUser);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
		}
		return newUser;
	}
	
	public static User SetUserToken(User currentUser,User_Token tk) {
		currentUser.setToken(tk);
		EntityManager em=PersisUtil.getEntityManager();
		try {
			em.getTransaction().begin();
			em.merge(currentUser);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
		}
		return currentUser;
	}
	public static User GetUserByName(String Name) {
		//TODO
		EntityManager em=PersisUtil.getEntityManager();
		return em.find(User.class, "name");
	}
}
