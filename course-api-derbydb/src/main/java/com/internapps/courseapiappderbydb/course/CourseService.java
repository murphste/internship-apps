package com.internapps.courseapiappderbydb.course;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService {
	
	/*
	 * SERVICE CLASS:
	 * It is this classes job to retrieve, process and perform any calculations
	 * on the data.
	 */
	
	@Autowired
	private CourseRepository courseRepository;

	
	/*
	 * A little bit of extra work required here to get all courses that relate
	 * to a particular topic. Had to create a method called findByTopicId() in our CRUD
	 * interface, and implement it here.
	 */
	public List<Course> getAllCourses(String topicId) {
		List<Course> courses = new ArrayList<>(); 
		courseRepository.findByTopicId(topicId)
		.forEach(courses::add);
		return courses; 
	}
	
	public Optional<Course> getCourse(String id) { 
		return courseRepository.findById(id);
	}
	
	
	public void addCourse(Course course) {
		 courseRepository.save(course);
	}
	
	
	public void updateCourse(Course course) {
		/*
		 * With this one - we can simply pass in the course object.
		 * Since our object will have an id set on it, Spring knows enough
		 * to search for any existing object with that id and either update it if found,
		 * or do an insert (POST) if it is a new id. - Both handled via .save()
		 */
		courseRepository.save(course);
	}

	public void deleteCourse(String id) {
		courseRepository.deleteById(id); 	
		
	}
		
	
}
