package com.internapps.courseapiappderbydb.course;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.internapps.courseapiappderbydb.topic.Topic;

@RestController
public class CourseController {
	
	/*
	 * TOPIC CONTROLLER:
	 * The job this class has is to map each URL route to a particular method,
	 * and using Spring specific annotations, calls methods which pass arguments
	 * to methods defined in CourseService class, to perform the CRUD operations
	 * 
	 * CourseService class is "Autowired", ie: injected so that it can be accessed and used
	 * here. This is because user never instantiates CourseController themselves, so we need
	 * a way to access the functionality inside CourseService.
	 * 
	 * 
	 * NB :: Topics contain courses. For a particular topic (defined by its id) there will be a list of courses.
	 */
	
	@Autowired
	private CourseService courseService;

	
	
	/* --GET--
	 * Note: The @PathVariable tag is used to specify a specific topic by its id
	 * Note: Get ALL courses that correspond to a topic. NOT every single course of any type in the database.
	 */
	@RequestMapping("/topics/{id}/courses")
	public List<Course> getAllCourses(@PathVariable String id) {
		return courseService.getAllCourses(id); 
	}
	
	@RequestMapping("/topics/{topicId}/courses/{id}") 
	public Optional<Course> getCourse(@PathVariable String id) {
		return courseService.getCourse(id);
	}
	
	
	
	
	/* --POST--
	 * Notice POST mapping is annotated differently
	 * Here we're saying, map this method to handle a POST request on /courses
	 * Remember, a topic will have a list of courses within it.
	 * 
	 * Also, @RequestBody tag is saying to Spring, please convert the body of our
	 * request to a Course object, so we can add it to the Courses list. (A bit like a cast)
	 * 
	 * Note: Can also use @PostMapping here in place of @RequestMapping(method=RequestMethod....)
	 */
	@RequestMapping(method=RequestMethod.POST, value="/topics/{topicId}/courses")
	public void addCourse(@RequestBody Course course, @PathVariable String topicId) {
		/*
		 * We include a Topic member variable in course so that when user posts a new course,
		 * system can tell which Topic it should be as signed under. See course model. 
		 */
		course.setTopic(new Topic(topicId, "", ""));
		courseService.addCourse(course);
	}
	
	
	
	
	/* -- PUT --
	 * To update a course
	 * Method should take a @RequestBody course obj argument (which will contain some sort of update) and a topicId
	 * @PathVariable String id is for an individual course
	 * We use topicId to set the topic for a course (if the course object does not define that itself)
	 * Note: Can also use @PutMapping here in place of @RequestMapping(method=RequestMethod....)
	 */
	@RequestMapping(method=RequestMethod.PUT, value="/topics/{topicId}/courses/{id}")
	public void updateCourse(@RequestBody Course course, @PathVariable String topicId, @PathVariable String id) {
		course.setTopic(new Topic(topicId, "", ""));
		courseService.updateCourse(course);
	}
	
	
	
	
	/* -- DELETE --
	 * To delete a course
	 * Method should take a @RequestBody Topic object argument
	 * and also the @PathVariable String id for an individual topic
	 * Note: Can also use @DeleteMapping here in place of @RequestMapping(method=RequestMethod....)
	 */
	@RequestMapping(method=RequestMethod.DELETE, value="/topics/{topicId}/courses/{id}")
	public void deleteCourse(@PathVariable String id) {
		courseService.deleteCourse(id);
	}
}
