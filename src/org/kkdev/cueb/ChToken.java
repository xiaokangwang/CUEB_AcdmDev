package org.kkdev.cueb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.kkdev.cueb.entities.Session;
import org.kkdev.cueb.entities.User;
import org.kkdev.cueb.entities.User_Token;
import org.kkdev.cueb.logic.RequiredPremission;
import org.kkdev.cueb.logic.session.SessionMgr;
import org.kkdev.cueb.logic.user.PremissionChecker;
import org.kkdev.cueb.logic.user.TokenCreate;
import org.kkdev.cueb.logic.user.UserMgr;

/**
 * Session Bean implementation class ChToken
 */
@Stateless
@LocalBean
@RequiredPremission("Admin/UserChToken")
@Path("/ChToken")
public class ChToken {

    /**
     * Default constructor. 
     */
    public ChToken() {
        // TODO Auto-generated constructor stub
    }
    
    @POST
    @Produces("text/plain")
    public String doPost(@FormParam("sessionid") String sessionid,@FormParam("username") String Username,@FormParam("token") String Token){
		Session session= SessionMgr.GetSessionByName(sessionid);
    	if(!PremissionChecker.CheckPremission(session)){
			return "ERR: Not Qualified.";
		}
    	User user=UserMgr.GetUserByName(Username);
    	if(user==null){
    		return "ERR: Username inv";
    	}
    	User_Token tk = TokenCreate.GenerateToken(user, "INSECURE_password_plain", Token);
    	User user2=UserMgr.SetUserToken(user, tk);
    	if(user2!=null){
    		return "OK";
    	}
    	return "FAIL";
    }

}
