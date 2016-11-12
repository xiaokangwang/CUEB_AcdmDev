package org.kkdev.cueb.entities;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Log
 *
 */
@Entity
@Table(name="LOG")
public class Log implements Serializable {

    private Long id;
	private Integer time; 
	private String Action; 
	private Integer LogLevel; 
	private String Detail;
	private static final long serialVersionUID = 1L;	
	public Log() {
		super();
	} 
	   
	/**
	 * @return the id
	 */
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	public Integer getTime() {
 		return this.time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}
	   
	public String getAction() {
 		return this.Action;
	}

	public void setAction(String Action) {
		this.Action = Action;
	}
	   
	public Integer getLogLevel() {
 		return this.LogLevel;
	}

	public void setLogLevel(Integer LogLevel) {
		this.LogLevel = LogLevel;
	}
	   
	public String getDetail() {
 		return this.Detail;
	}

	public void setDetail(String Detail) {
		this.Detail = Detail;
	}
	
   
}
