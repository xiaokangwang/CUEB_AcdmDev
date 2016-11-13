package org.kkdev.cueb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.kkdev.cueb.entities.Role;
import org.kkdev.cueb.entities.Session;
import org.kkdev.cueb.logic.RequiredPremission;
import org.kkdev.cueb.logic.session.SessionMgr;
import org.kkdev.cueb.logic.user.PremissionChecker;
import org.kkdev.cueb.logic.user.RoleDefine;


/**
 * Session Bean implementation class CreateRole
 */
@Stateless
@LocalBean
@RequiredPremission("GENESIS")
@Path("/CreateRole")
public class CreateRole {

    /**
     * Default constructor. 
     */
    public CreateRole() {
        // TODO Auto-generated constructor stub
    }
    @POST
    @Produces("text/plain")
    public String doPost(@FormParam("sessionid") String sessionid, @FormParam("RoleName") String Rolename) {
		Session session= SessionMgr.GetSessionByName(sessionid);
    	if(!PremissionChecker.CheckPremission(session)){
			return "ERR: Not Qualified.";
		}
    	Role nRole = new Role();
    	nRole.setName(Rolename);
    	if(RoleDefine.RoleSynchronize(nRole)!=null){
    		return "OK";
    	}
    	return "SYNC FAIL";
	}

}
