package org.kkdev.cueb.entities;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import javax.persistence.*;
import org.kkdev.cueb.entities.Role;
import org.kkdev.cueb.entities.User_Token;

/**
 * Entity implementation class for Entity: User
 *
 */
public class User implements Serializable {

	@Id
	private String Username; 
	private Integer Elevated; 
	private Role userRole; 
	private User_Token Token;
	private static final long serialVersionUID = 1L;	
	public User() {
		super();
		Elevated=100;
	} 
	   
	public String getUsername() {
 		return this.Username;
	}

	public void setUsername(String Username) {
		this.Username = Username;
	}
	   
	public Integer getElevated() {
 		return this.Elevated;
	}

	public void setElevated(Integer Elevated) {
		this.Elevated = Elevated;
	}
	   
	public Role getUserRole() {
 		return this.userRole;
	}

	public void setUserRole(Role userRole) {
		this.userRole = userRole;
	}
	   
	public User_Token getToken() {
 		return this.Token;
	}

	public void setToken(User_Token Token) {
		this.Token = Token;
	}
	
   
}
