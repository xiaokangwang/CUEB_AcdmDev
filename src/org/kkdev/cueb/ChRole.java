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
import org.kkdev.cueb.logic.user.RoleDefine;
import org.kkdev.cueb.logic.user.UserMgr;

/**
 * Session Bean implementation class ChRole
 */
@Stateless
@LocalBean
@RequiredPremission("Admin/UserChRole")
@Path("/UserChRole")
public class ChRole {

    /**
     * Default constructor. 
     */
    public ChRole() {
        // TODO Auto-generated constructor stub
    }

    @POST
    @Produces("text/plain")
    public String doPost(@FormParam("sessionid") String sessionid,@FormParam("username") String Username,@FormParam("role") String Role){
		Session session= SessionMgr.GetSessionByName(sessionid);
    	if(!PremissionChecker.CheckPremission(session)){
			return "ERR: Not Qualified.";
		}
    	org.kkdev.cueb.entities.Role rol=RoleDefine.getRoleByName(Role);
    	if(rol==null){
    		return "NO role";
    	}
    	User user=UserMgr.GetUserByName(Username);
    	if(user==null){
    		return "NO user";
    	}
    	user.setUserRole(rol);
    	user=UserMgr.UserSync(user);
    	if(user!=null){
    		return "OK";
    	}
    	return "FAIL";
    }
}
