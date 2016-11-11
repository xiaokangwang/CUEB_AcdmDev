package org.kkdev.cueb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * Session Bean implementation class Mgr
 */
@Stateless
@LocalBean
@Path("/UserService")
public class Mgr implements MgrRemote {

    /**
     * Default constructor. 
     */
	
    public Mgr() {
        // TODO Auto-generated constructor stub
    }
    @GET
    @Produces("text/plain")
    public String run(){
    	return "StandBy";
    }

}
