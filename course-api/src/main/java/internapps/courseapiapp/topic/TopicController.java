package internapps.courseapiapp.topic;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TopicController {
	
	/*
	 * TOPIC CONTROLLER:
	 * The job this class has is to map each URL route to a particular method,
	 * and using Spring specific annotations, calls methods which pass arguments
	 * to methods defined in TopicService class, to perform the CRUD operations
	 * 
	 * TopicService class is "Autowired", ie: injected so that it can be accessed and used
	 * here. This is because user never instantiates TopicController themselves, so we need
	 * a way to access the functionality inside TopicService.
	 */
	
	@Autowired
	private TopicService topicService;

	
	/* --GET--
	 * Note: The @PathVariable tag is used to specify a specific topic by its id
	 * Note: Can also use @GetMapping here in place of @RequestMapping(method=RequestMethod....)
	 */
	@RequestMapping("/topics")
	public List<Topic> getAllTopics() {
		return topicService.getAllTopics();
	}
	
	@RequestMapping("/topics/{id}")
	public Topic getTopic(@PathVariable String id) {
		return topicService.getTopic(id);
	}
	
	
	
	/* --POST--
	 * Notice POST mapping is annotated differently
	 * Here we're saying, map this method to handle a POST request on /topics
	 * 
	 * Also, @RequestBody tag is saying to Spring, please convert the body of our
	 * request to a Topic object, so we can add it to the topics list. (A bit like a cast)
	 * 
	 * Note: Can also use @PostMapping here in place of @RequestMapping(method=RequestMethod....)
	 */
	@RequestMapping(method=RequestMethod.POST, value="/topics")
	public void addTopic(@RequestBody Topic topic) {
		topicService.addTopic(topic);
	}
	
	
	
	
	/* -- PUT --
	 * To update a topic
	 * Method should take a @RequestBody Topic obj argument 
	 * (which will contain some sort of update)
	 * and also the @PathVariable String id for an individual topic
	 * Note: Can also use @PutMapping here in place of @RequestMapping(method=RequestMethod....)
	 */
	@RequestMapping(method=RequestMethod.PUT, value="/topics/{id}")
	public void updateTopic(@RequestBody Topic topic, @PathVariable String id) {
		topicService.updateTopic(id,topic);
	}
	
	
	
	/* -- DELETE --
	 * To delete a topic
	 * Method should take a @RequestBody Topic object argument
	 * and also the @PathVariable String id for an individual topic
	 * Note: Can also use @DeleteMapping here in place of @RequestMapping(method=RequestMethod....)
	 */
	@RequestMapping(method=RequestMethod.DELETE, value="/topics/{id}")
	public void deleteTopic(@PathVariable String id) {
		topicService.deleteTopic(id);
	}
}
