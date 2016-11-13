package org.kkdev.cueb;

import java.util.Iterator;
import java.util.Set;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.kkdev.cueb.entities.AccessControl_Permission;
import org.kkdev.cueb.entities.Role;
import org.kkdev.cueb.entities.Session;
import org.kkdev.cueb.logic.RequiredPremission;
import org.kkdev.cueb.logic.session.SessionMgr;
import org.kkdev.cueb.logic.user.PremissionChecker;
import org.kkdev.cueb.logic.user.RoleDefine;

/**
 * Session Bean implementation class RoleAlterPermission
 */
@Stateless
@LocalBean
@RequiredPremission("GENESIS")
@Path("/RoleAlterPermission/")
public class RoleAlterPermission {

    /**
     * Default constructor. 
     */
    public RoleAlterPermission() {
        // TODO Auto-generated constructor stub
    }
    @POST
    @Produces("text/plain")
    public String doPost(@FormParam("sessionid") String sessionid, @FormParam("RoleName") String Rolename,@FormParam("Action") String Action,@FormParam("Ptm") String Ptm) {
		Session session= SessionMgr.GetSessionByName(sessionid);
    	if(!PremissionChecker.CheckPremission(session)){
			return "ERR: Not Qualified.";
		}
    	Role role =RoleDefine.getRoleByName(Rolename);
    	if(role==null){
    		return "Role name not found";
    	}
    	switch (Action) {
		case "List":
			return RoleListPremission(role);
		case "Revoke":
			return RoleRevokePremission(role,Ptm);
		case "Grant":
			return RoleGrantPremission(role,Ptm);
		default:
			return "Op1 unknown";
		}
    }
    
    private String RoleListPremission(Role ro) {
		JsonObjectBuilder jsonObject = Json.createObjectBuilder();
		JsonArrayBuilder initarr = Json.createArrayBuilder();
		Set<AccessControl_Permission> ac=ro.getPrivileges();
		for (Iterator<AccessControl_Permission> iterator = ac.iterator(); iterator.hasNext();) {
			AccessControl_Permission accessControl_Permission = (AccessControl_Permission) iterator.next();
			initarr.add(accessControl_Permission.getName());
		}
		jsonObject.add("Privs", initarr);
		return jsonObject.build().toString();
	}
    private String RoleRevokePremission(Role ro,String Ptm) {
    	Set<AccessControl_Permission> ac=ro.getPrivileges();
    	for (Iterator<AccessControl_Permission> iterator = ac.iterator(); iterator.hasNext();) {
			AccessControl_Permission accessControl_Permission = (AccessControl_Permission) iterator.next();
			if(accessControl_Permission.getName().equals(Ptm)){
				ac.remove(accessControl_Permission);
				break;
			}
		}
    	RoleDefine.RoleSynchronize(ro);
		return "OK";
	}
    private String RoleGrantPremission(Role ro,String Ptm) {
    	AccessControl_Permission accessControl_Permission=RoleDefine.getAcByName(Ptm);
    	if(accessControl_Permission==null){
    		accessControl_Permission = new AccessControl_Permission();
    		accessControl_Permission.setName(Ptm);
    	}
    	ro.getPrivileges().add(accessControl_Permission);
    	RoleDefine.RoleSynchronize(ro);
		return "OK";
	}

}
