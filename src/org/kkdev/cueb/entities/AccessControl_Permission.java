package org.kkdev.cueb.entities;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: AccessControl_Permission
 *
 */
@Entity
@Table(name="ACCESSCONTROL_PERMISSION")
public class AccessControl_Permission implements Serializable {

	@Id
	private String Name;
	@Column(name="BasicConstraint")
	private String Constraint;
	private Integer elevationLevel;
	private static final long serialVersionUID = 1L;

	public AccessControl_Permission() {
		super();
	}   
	public String getName() {
		return this.Name;
	}

	public void setName(String Name) {
		this.Name = Name;
	}   
	public String getConstraint() {
		return this.Constraint;
	}

	public void setConstraint(String Constraint) {
		this.Constraint = Constraint;
	}   
	public Integer getElevationLevel() {
		return this.elevationLevel;
	}

	public void setElevationLevel(Integer elevationLevel) {
		this.elevationLevel = elevationLevel;
	}
   
}
