package org.kkdev.cueb.entities;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;
import org.kkdev.cueb.entities.User;

/**
 * Entity implementation class for Entity: Session
 *
 */

@Table(name="SESSION")
@Entity
public class Session implements Serializable {

	@Id
	private String SessionId; 
	private User User;
	private static final long serialVersionUID = 1L;	
	public Session() {
		super();
	} 
	   
	public String getSessionId() {
 		return this.SessionId;
	}

	public void setSessionId(String SessionId) {
		this.SessionId = SessionId;
	}
	   
	public User getUser() {
 		return this.User;
	}

	public void setUser(User User) {
		this.User = User;
	}
	
   
}
