package com.internapps.courseapiappderbydb.course;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.internapps.courseapiappderbydb.topic.Topic;

@Entity
public class Course {
	
	/*
	 * Course  MODEL:
	 * This class is the standard blueprint / definition of a Course, of which objects are created.
	 * 
	 * Note: See POST method in CourseController for @RequestBody tag - special Spring tag for casting to an
	 * object type. Very useful
	 */
	
	@Id
	private String id; // id is made primary key using @Id
	private String name;
	private String description;
	
	// One topic can have many courses, therefore:
	@ManyToOne
	private Topic topic;
	
	public Course () {
		
	}
	
	public Course (String id, String name, String description, String topicId) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.topic = new Topic(topicId, "", "");
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

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}
	

}
