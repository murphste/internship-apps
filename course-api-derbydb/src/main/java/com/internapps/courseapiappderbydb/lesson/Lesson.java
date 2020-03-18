package com.internapps.courseapiappderbydb.lesson;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.internapps.courseapiappderbydb.course.Course;
import com.internapps.courseapiappderbydb.topic.Topic;

@Entity
public class Lesson {
	
	/*
	 * Lesson  MODEL:
	 * This class is the standard blueprint / definition of a Lesson, of which objects are created.
	 * 
	 * Note: See POST method in CourseController for @RequestBody tag - special Spring tag for casting to an
	 * object type. Very useful
	 */
	
	@Id
	private String id; // id is made primary key using @Id
	private String name;
	private String description;
	
	// One course can have many lessons, therefore:
	@ManyToOne
	private Course course;
	

	@ManyToOne
	private Topic topic;
	
	public Lesson () {
		
	}
	
	public Lesson (String id, String name, String description, String topicId, String courseId) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.course = new Course(topicId, courseId, "", "");
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
	
	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
	

}
