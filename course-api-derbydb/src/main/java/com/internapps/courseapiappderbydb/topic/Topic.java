package com.internapps.courseapiappderbydb.topic;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Topic {
	
	/*
	 * TOPIC MODEL:
	 * This class is the standard blueprint / definition of a Topic, of which objects are created.
	 * 
	 * Note: See POST method in TC for @RequestBody tag - special Spring tag for casting to an
	 * object type. Very useful
	 */
	
	@Id
	private String id; // id is made primary key using @Id
	private String name;
	private String description;
	
	
	public Topic () {
		
	}
	
	public Topic (String id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	

}
