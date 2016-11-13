package org.kkdev.cueb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.kkdev.cueb.entities.Session;
import org.kkdev.cueb.entities.User;
import org.kkdev.cueb.logic.RequiredPremission;
import org.kkdev.cueb.logic.session.SessionMgr;
import org.kkdev.cueb.logic.user.PremissionChecker;
import org.kkdev.cueb.logic.user.UserMgr;


/**
 * Session Bean implementation class CreateUser
 */
@Stateless
@LocalBean
@RequiredPremission("Admin/UserCreate")
@Path("/UserCreate")
public class CreateUser {

    /**
     * Default constructor. 
     */
    public CreateUser() {
        // TODO Auto-generated constructor stub
    }
    @POST
    @Produces("text/plain")
    public String doPost(@FormParam("sessionid") String sessionid,@FormParam("username") String Username){
		Session session= SessionMgr.GetSessionByName(sessionid);
    	if(!PremissionChecker.CheckPremission(session)){
			return "ERR: Not Qualified.";
		}
    	User user=UserMgr.CreateUser(Username);
    	if(user!=null){
    		return "OK";
    	}
    	return "FAIL";
    }

}
