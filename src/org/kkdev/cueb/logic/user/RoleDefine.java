package org.kkdev.cueb.logic.user;

import javax.persistence.EntityManager;

import org.kkdev.cueb.db.PersisUtil;
import org.kkdev.cueb.entities.AccessControl_Permission;
import org.kkdev.cueb.entities.Role;

public class RoleDefine {
	public static Role getRoleByName(String name) {
		EntityManager em = PersisUtil.getEntityManager();
		return em.find(Role.class, name);
	}
	
	public static AccessControl_Permission getAcByName(String name) {
		EntityManager em = PersisUtil.getEntityManager();
		return em.find(AccessControl_Permission.class, name);
	}

	public static Role RoleSynchronize(Role role) {
		EntityManager em = PersisUtil.getEntityManager();
		try {
			em.getTransaction().begin();
			em.merge(role);
			em.getTransaction().commit();
			return role;
		} catch (Exception e) {
			em.getTransaction().rollback();
			return null;
		}
	}
}
