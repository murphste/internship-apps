package com.internapps.courseapiappderbydb.lesson;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.internapps.courseapiappderbydb.course.Course;
import com.internapps.courseapiappderbydb.topic.Topic;

@RestController
public class LessonController {
	
	/*
	 * TOPIC CONTROLLER:
	 * The job this class has is to map each URL route to a particular method,
	 * and using Spring specific annotations, calls methods which pass arguments
	 * to methods defined in LessonService class, to perform the CRUD operations
	 * 
	 * lessonService class is "Autowired", ie: injected so that it can be accessed and used
	 * here. This is because user never instantiates LessonController themselves, so we need
	 * a way to access the functionality inside LessonService.
	 * 
	 * 
	 * NB :: Courses contain lessons. For a particular course (defined by its id) there will be a list of courses.
	 * See @OneToMany mapping in model.
	 */
	
	@Autowired
	private LessonService lessonService;

	
	
	/* --GET--
	 * Note: The @PathVariable tag is used to specify a specific course by its id
	 * Note: Get ALL lessons that correspond to a course. NOT every single lesson of any type in the database.
	 */
	@RequestMapping("/topics/{topicId}/courses/{courseId}/lessons")
	public List<Lesson> getAllLessons(@PathVariable String courseId) {
		return lessonService.getAllLessons(courseId); 
	}
	
	@RequestMapping("/topics/{topicId}/courses/{courseId}/lessons/{id}") 
	public Optional<Lesson> getLesson(@PathVariable String id) {
		return lessonService.getLesson(id);
	}
	
	
	
	
	/* --POST--
	 * Notice POST mapping is annotated differently
	 * Here we're saying, map this method to handle a POST request on /lessons
	 * Remember, a course will have a list of lessons within it.
	 * 
	 * Also, @RequestBody tag is saying to Spring, please convert the body of our
	 * request to a Lesson object, so we can add it to the Lessons list. (As before)
	 * 
	 * Note: Can also use @PostMapping here in place of @RequestMapping(method=RequestMethod....)
	 */
	@RequestMapping(method=RequestMethod.POST, value="/topics/{topicId}/courses/{courseId}/lessons")
	public void addCourse(@RequestBody Lesson lesson, @PathVariable String topicId, @PathVariable String courseId) {
		/*
		 * We include a Topic member variable in course so that when user posts a new course,
		 * system can tell which Topic it should be as signed under. See course model. 
		 */
		lesson.setCourse(new Course(courseId, topicId, "", ""));
		lessonService.addLesson(lesson);
	}
	
	
	
	
	/* -- PUT --
	 * To update a lesson
	 * Method should take a @RequestBody lesson obj argument (which will contain some sort of update),
	 * plus @PathVariable topicId & a courseId (both taken into the Course constructor).
	 * Note: Can also use @PutMapping here in place of @RequestMapping(method=RequestMethod....)
	 */
	@RequestMapping(method=RequestMethod.PUT, value="/topics/{topicId}/courses/{courseId}/lessons/{id}")
	public void updateLesson(@RequestBody Lesson lesson, @PathVariable String topicId, @PathVariable String courseId, @PathVariable String id) {
		lesson.setCourse(new Course(topicId, courseId, "", ""));
		lessonService.updateLesson(lesson);
	}
	
	
	
	
	/* -- DELETE --
	 * To delete a lesson
	 * Method should take a @PathVariable String lessonId for an individual lesson
	 * Note: Can also use @DeleteMapping here in place of @RequestMapping(method=RequestMethod....)
	 */
	@RequestMapping(method=RequestMethod.DELETE, value="/topics/{topicId}/courses/{id}/lessons/{id}")
	public void deleteLesson(@PathVariable String id) {
		lessonService.deleteLesson(id);
	}
}
