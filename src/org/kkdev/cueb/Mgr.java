package org.kkdev.cueb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.kkdev.cueb.entities.Log;


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
    	//Persistence.generateSchema("CUEB_AcdmDev", null);
    	//EntityManager entityManager = PersisUtil.getEntityManager();
    	WriteLog();
    	return "StandBy";
    }
    private Log WriteLog() {
    	Log lg = new Log();
    	lg.setAction("Access");
    	lg.setLogLevel(0);
    	EntityManager entityManager = PersisUtil.getEntityManager();
    	 try {
    	      entityManager.getTransaction().begin();
    	      lg = entityManager.merge(lg);
    	      entityManager.getTransaction().commit();
    	    } catch (Exception e) {
    	      entityManager.getTransaction().rollback();
    	    }
    	 return lg;
		}


}
