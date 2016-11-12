package org.kkdev.cueb.logic.user;

import org.kkdev.cueb.entities.AccessControl_Permission;
import org.kkdev.cueb.entities.Session;
import org.kkdev.cueb.logic.RequiredPremission;
import org.kkdev.cueb.universal.UniversalUtil;


import java.lang.annotation.Annotation;
//import java.lang.reflect.AnnotatedElement;
import java.util.HashSet;
import java.util.Set;

public class PremissionChecker {
	// True = pass
	public static Boolean CheckPremission(Session currentSess) {
		if (currentSess.getUser().getElevated() == 0) {
			return true;
		}

		for (int i = 0; i < 6; i++) {
			// Get User Accessing Class
			Class<?> caller = UniversalUtil.GetCaller(i);

			if (caller.isAnnotationPresent(RequiredPremission.class)) {
				Annotation ReqPremiss = caller.getAnnotation(RequiredPremission.class);
				// if currentSess.getUser().getUserRole().getPrivileges()
				RequiredPremission ReqPremissrp = (RequiredPremission) ReqPremiss;
				String[] prem = ReqPremissrp.value();
				Set<String> prems = new HashSet<String>();
				for (int j = 0; j < prem.length; j++) {
					String string = prem[j];
					prems.add(string);
				}
				Set<AccessControl_Permission> premsOwn = null;
				if(currentSess.getUser()!=null){
					premsOwn=currentSess.getUser().getUserRole().getPrivileges();
				}

				for (String prReq : prems) {
					if(prReq=="None"){
						return true;
					}else{
						if(premsOwn==null){
							return false;
						}
					}
					Boolean isSatisf = false;
					for (AccessControl_Permission accessControl_Permission : premsOwn) {
						if (accessControl_Permission.getName() == prReq) {
							isSatisf = true;
						}
					}
					if (!isSatisf) {
						return false;
					}
				}
				return true;
			}

		}
		return false;

	}

}
