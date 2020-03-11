package internapps.courseapiapp.topic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class TopicService {
	/*
	 * SERVICE CLASS:
	 * The job this class has is to retrieve, process and perform any calculations
	 * on the data (in this simple example, the data is simply hardcoded into an arraylist),
	 * and to provide the methods required for the HTTP CRUD operations (GET/POST/PUT/DELETE)
	 * defined in the HomeController
	 */

	private List<Topic> topics = new ArrayList<>(Arrays.asList(
			new Topic("spring", "Spring Framework", "Spring Framework Description"),
			new Topic("java", "Core Java", "Core Java Description"),
			new Topic("javascript","JavaScript","JavaScript Description")));
	
	/*
	 *  NB - This initial array (created using Arrays.asList) at beginning of tutorial
	 *  to demo GET functionality - has been converted to an ArrayList, so as to make it
	 *  muteable. ie: So that items can be added, removed or changed from it.
	 *  Required now for POST ( .add() ) and PUT( .set() ) operations
	 */
	
	public List<Topic> getAllTopics() {
		return topics;
	}
	
	public Topic getTopic(String id) {
		return topics.stream().filter(t -> t.getId().equals(id)).findFirst().get();
	}
	
	public void addTopic(Topic topic) {
		 topics.add(topic);
	}
	
	
	public void updateTopic(String id, Topic topic) {
		for (int i=0; i < topics.size(); i++) {
			// Iterate across all topics in the list, comparing the id's for each
			Topic t = topics.get(i);
			/* If we find an id match set the input argument topic as the new topic
			 * at that list index
			 */
				if(t.getId().equals(id)) {
					topics.set(i, topic);
					return;
				}
		}
	}

	public void deleteTopic(String id) {
		// Java 1.8 & above lambda is equivalent to the for loop lines 38 - 48 above
		topics.removeIf(t -> t.getId().equals(id));
		
	}
		
	
}
