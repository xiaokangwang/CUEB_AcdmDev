package org.kkdev.cueb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.kkdev.cueb.entities.Session;
import org.kkdev.cueb.logic.RequiredPremission;
import org.kkdev.cueb.logic.session.SessionMgr;
import org.kkdev.cueb.logic.user.PremissionChecker;
import org.kkdev.cueb.miscellaneous.LoggerMisc;

/**
 * Session Bean implementation class CreateSession
 */
@Stateless
@LocalBean
@RequiredPremission("none")
@Path("/CreateSession")
public class CreateSession implements CreateSessionRemote {

    /**
     * Default constructor. 
     */
    public CreateSession() {
        // TODO Auto-generated constructor stub
    }
    @GET
    @Produces("text/plain")
    public String doGet(){
    	Session session=SessionMgr.getNewSession();
    	LoggerMisc.Log(0, "Session Create", session.getSessionId(), "");
    	return session.getSessionId();
    }

}
