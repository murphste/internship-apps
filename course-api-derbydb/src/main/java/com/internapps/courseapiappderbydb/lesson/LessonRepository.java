 package com.internapps.courseapiappderbydb.lesson;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface LessonRepository extends CrudRepository<Lesson, String> {
	
	/* 
	 * DATA SERVICE CLASS:
	 */
	
	/* getAllTopics()
	 * getAllTopics(String id)
	 * updateTopic(Topic t)
	 * deleteTopic(String id)
	 * 
	 * All of the above STANDARD CRUD methods are included by extending the CrudRepository
	 * interface. No need to redefine them here. Would only need to define any custom requests
	 * eg: some custom method like "Find all topics containing x" - ie: non standard CRUD operation
	*/
	
	
	
	/*
	 * IN THIS INSTANCE, TopicRepository is simply an alias for the standard CRUDRepository - as there is
	 * no special implementations. See TopicService for the various method calls to perform CRUD operations.
	 */
	
	
	/* OTHER FUNCTIONS:
	 * These "non-standard" CRUD functions operate on items that are listed under a parent ID. ie: Get a course,
	 * which has a parent topic (linked by topic id Primary Key reference).
	 * 
	 * There is a naming convention here which enables us to simply "use" the method, without requiring further implementation
	 * in the CourseService class.
	 * 
	*/	
	public List<Lesson> findByName (String name);
	
	public List<Lesson> findByDescription (String description);
	
	// Required in order to be able to "go down a level" and get all lessons within a course
	public List<Lesson> findByCourseId(String courseId);

}
