package org.kkdev.cueb.logic.user;

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
		return user;
	}
	return null;
}
}
