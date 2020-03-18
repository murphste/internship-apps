package com.internapps.courseapiappderbydb.lesson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LessonService {
	
	/*
	 * SERVICE CLASS:
	 * It is this classes job to retrieve, process and perform any calculations
	 * on the data.
	 */
	
	@Autowired
	private LessonRepository lessonRepository;

	
	/*
	 * A little bit of extra work required here to get all lessons that relate
	 * to a particular course. Had to create a method called findByCourseId() in our CRUD
	 * interface, and implement it here.
	 */
	public List<Lesson> getAllLessons(String courseId) {
		List<Lesson> lessons = new ArrayList<>(); 
		lessonRepository.findByCourseId(courseId)
		.forEach(lessons::add);
		return lessons; 
	}
	
	public Optional<Lesson> getLesson(String id) { 
		return lessonRepository.findById(id);
	}
	
	
	public void addLesson(Lesson lesson) {
		 lessonRepository.save(lesson);
	}
	
	
	public void updateLesson(String id, Lesson lesson) {
		/*
		 * With this one - we can simply pass in the lesson object.
		 * Since our object will have an id set on it, Spring knows enough
		 * to search for any existing object with that id and either update it if found,
		 * or do an insert (POST) if it is a new id. - Both handled via .save()
		 */
		lessonRepository.save(lesson);
	}

	public void deleteLesson(String id) {
		lessonRepository.deleteById(id); 	
	}
		
	
}
