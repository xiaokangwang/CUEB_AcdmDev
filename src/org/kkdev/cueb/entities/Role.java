package org.kkdev.cueb.entities;

import java.io.Serializable;
import java.lang.String;
import java.util.Set;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Role
 *
 */
public class Role implements Serializable {

	 
	private Set<AccessControl_Permission> Privileges; 
	@Id
	private String Name;
	private static final long serialVersionUID = 1L;	
	public Role() {
		super();
	} 
	   
	public Set<AccessControl_Permission> getPrivileges() {
 		return this.Privileges;
	}

	public void setPrivileges(Set<AccessControl_Permission> Privileges) {
		this.Privileges = Privileges;
	}
	   
	public String getName() {
 		return this.Name;
	}

	public void setName(String Name) {
		this.Name = Name;
	}
	
   
}
