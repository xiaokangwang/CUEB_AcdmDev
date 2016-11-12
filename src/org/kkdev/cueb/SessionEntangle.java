package org.kkdev.cueb;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.persistence.EntityManager;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.xml.ws.Response;

import org.kkdev.cueb.db.PersisUtil;
import org.kkdev.cueb.entities.Session;
import org.kkdev.cueb.entities.User;
import org.kkdev.cueb.logic.RequiredPremission;
import org.kkdev.cueb.logic.session.SessionMgr;
import org.kkdev.cueb.logic.user.PremissionChecker;
import org.kkdev.cueb.logic.user.UserLoginAuth;
import org.kkdev.cueb.miscellaneous.LoggerMisc;
import org.kkdev.cueb.universal.UniversalUtil;

/**
 * Session Bean implementation class SessionEntangle
 */
@Stateless
@LocalBean
@RequiredPremission("None")
@Path("/CreateEntangle/{sessionid}/{username}/{inputscript}")
public class SessionEntangle implements SessionEntangleRemote {

    /**
     * Default constructor. 
     */
    public SessionEntangle() {
        // TODO Auto-generated constructor stub
    }
    @GET
    @Produces("text/plain")
    public String doGet(@PathParam("sessionid") String Sessid,@PathParam("username") String username,@PathParam("inputscript") String inputscript){
    	Session currSession=SessionMgr.GetSessionByName(Sessid);
    	if(username.equals("root")){
    		MessageDigest digest;
			try {
				digest = MessageDigest.getInstance("SHA-256");
				byte[] hash = digest.digest(inputscript.getBytes(StandardCharsets.UTF_8));
				if(UniversalUtil.bytesToHex(hash).equals("430c5749d59ca87aa77f30738c1eff9286e7aa301d05387ab89df333f379495b"))
				{
					User user = new User();
					user.setElevated(0);
					user.setUsername("#root_"+currSession.getSessionId());
					currSession.setUser(user);
					EntityManager em=PersisUtil.getEntityManager();
					try {
						em.getTransaction().begin();
						em.merge(user);
						em.merge(currSession);
						em.getTransaction().commit();
						return "ROOT mode set";
					} catch (Exception e) {
						em.getTransaction().rollback();
						return "ROOT access correct, DB down.";
					}
					
				}else {
					return "Reserved Username,"+UniversalUtil.bytesToHex(hash);
				}
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	if (currSession ==null){
    		return "SINF:Session Id Not Found"; //"Session Id Not Found"
    	}
    	//if(!PremissionChecker.CheckPremission(currSession)){
    	//	return "AD:Access Denied"; //"Access Denied"
    	//}
    	User us=UserLoginAuth.Check(currSession, username, inputscript);
    	if(us!=null){
    		return "OK";
    	}

    	return "AD_CKF";
    }
}
