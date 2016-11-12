package org.kkdev.cueb.entities;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: User_Token
 *
 */
public class User_Token implements Serializable {

	@Id
	private Integer id; 
	private String Type; 
	private String Script;
	private static final long serialVersionUID = 1L;	
	public User_Token() {
		super();
	} 
	   
	public Integer getId() {
 		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	   
	public String getType() {
 		return this.Type;
	}

	public void setType(String Type) {
		this.Type = Type;
	}
	   
	public String getScript() {
 		return this.Script;
	}

	public void setScript(String Script) {
		this.Script = Script;
	}
	
   
}
